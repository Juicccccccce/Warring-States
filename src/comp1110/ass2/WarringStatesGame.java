package comp1110.ass2;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import gittest.A;

import javax.crypto.AEADBadTagException;
import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.security.Key;
import java.util.*;

/**
 * This class provides the text interface for the Warring States game
 */
public class WarringStatesGame {

    /**
     * Determine whether a card placement is well-formed according to the following:
     * - it consists of exactly three characters agreed
     * - the first character is in the range a..g (kingdom) or is z (Zhang Yi)
     * - the second character is numeric, and is a valid character number for that kingdom (9 for Zhang Yi)
     * - the third character is in the range A .. Z or 0..9 (location)
     *
     * @param cardPlacement A string describing a card placement
     * @return true if the card placement is well-formed
     */
    static boolean isCardPlacementWellFormed(String cardPlacement) {
        char cardArray[] = cardPlacement.toCharArray();
        int a = (int) (cardArray[0] - 'a') + Character.getNumericValue(cardArray[1]);
        int ascii = (int) cardArray[2];
        if (cardPlacement.length() == 3) {
            if (Character.isDigit(cardArray[1])) {
                if (ascii >= 65 && ascii <= 90 || ascii >= 48 && ascii <= 57) {
                    if (cardArray[0] >= 'a' && cardArray[0] <= 'g') {
                        if (a >= 0 && a < 8) {
                            return true;
                        }
                    } else if (cardArray[0] == 'z') {
                        if (Character.getNumericValue(cardArray[1]) == 9) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N three-character card placements (where N = 1 .. 36);
     * - each card placement is well-formed
     * - no card appears more than once in the placement
     * - no location contains more than one card
     *
     * @param placement A string describing a placement of one or more cards
     * @return true if the placement is well-formed
     */
    static boolean isPlacementWellFormed(String placement) {
        if (placement != null) {
            if (placement.length() % 3 == 0 && placement.length() <= 108) {
                if (locationUnique(locationsString(placement))) {
                    if (checkDuplicates(dupCards(placement))) {
                        for (int z = 0; z < cardsPos(placement).size(); ++z) {
                            if (isCardPlacementWellFormed(cardsPos(placement).get(z))) {
                                return true;
                            }
                        }
                    }
                }

            }

        }
        return false;
    }

    // Create a string of locations of the card placements by
    // extracting the third character from the input string
    public static String locationsString(String str) {
        String loc = "";
        for (int z = 2; z <= str.length(); z += 3) {
            loc = loc + Character.toString(str.charAt(z));
        }
        return loc;
    }


    // Check if the string of locations created from locationsString
    // is unique
    public static boolean locationUnique(String locations) {
        boolean t = true;
        for (int y = 0; y < locations.length(); ++y) {
            for (int z = y + 1; z < locations.length(); ++z) {
                if (locations.charAt(y) == locations.charAt(z)) {
                    t = false;
                }
            }
        }
        return t;
    }

    // Splits the input string into ArrayList<String> of three-character elements
    public static ArrayList<String> cardsPos(String pl) {
        ArrayList<String> cards = new ArrayList<>();
        int j = 0;
        for (int n = 3; n <= pl.length(); n += 3) {
            cards.add(pl.substring(j, n));
            j += 3;
        }
        return cards;
    }

    // Creates ArrayList<String> consisting of cards (two-character placements)
    // by removing every third element of input string
    public static ArrayList<String> dupCards(String pl) {
        String a = pl.replaceAll("(..).", "$1");
        ArrayList<String> cards = new ArrayList<>();
        for (int i = 0; i < a.length(); i += 2) {
            cards.add(a.substring(i, Math.min(a.length(), i + 2)));
        }
        return cards;
    }

    // Check if there are any duplicate cards in a string
    public static boolean checkDuplicates(ArrayList<String> cards) {
        Set<String> s = new HashSet<>();
        for (String l : cards) {
            if (!(s.add(l))) {
                return false;
            }
        }
        return true;
    }


    /**
     * Determine whether a given move is legal given a provided valid placement:
     * - the location char is in the range A .. Z or 0..9
     * - there is a card at the chosen location;
     * - the location is in the same row or column of the grid as Zhang Yi's current position; and
     * - drawing a line from Zhang Yi's current location through the card at the chosen location,
     * there are no other cards along the line from the same kingdom as the chosen card
     * that are further away from Zhang Yi.
     *
     * @param placement    the current placement string
     * @param locationChar a location for Zhang Yi to move to
     * @return true if Zhang Yi may move to that location
     */
    public static boolean isMoveLegal(String placement, char locationChar) {
        // FIXME Task 5: determine whether a given move is legal
        int a = placement.length();
        if (changeToNumber(locationChar) >= 65 && changeToNumber(locationChar) <= 101) {
            if (isCardExist(placement, locationChar)) {
                if (isSameColumn(placement, locationChar) || (isSameRow(placement, locationChar))) {
                    if (checkFurtherCard(placement, locationChar)) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    // check whether there is a card at the chosen location
    public static boolean isCardExist(String placement, char locationChar) {
        int c = placement.length();
        {
            for (int b = 0; b < c; b = b + 3) {
                char d = placement.charAt(b + 2);
                if (d == locationChar) {
                    return true;
                }
            }
            return false;
        }
    }

    // find the location for ZhangYi
    public static char findZhangPosition(String placement) {
//        int j = placement.length();
//        char z = 'a';
//        for (int i = 0; i < j; i = i + 3) {
//            if (placement.charAt(i) == 'z') {
//                return z = placement.charAt(i + 2);
//            }
//        }
        int num = placement.indexOf('z');
        char z = placement.charAt(num+2);
        return z;
    }

    // check whether locationChar is in the same column with ZhangYi
    public static boolean isSameColumn(String placement, char locationChar) {
        if (((findZhangPosition(placement) >= 'A') && (findZhangPosition(placement) <= 'F')) && ((locationChar >= 'A') && (locationChar <= 'F'))) {
            return true;
        } else {
            if (((findZhangPosition(placement) >= 'G') && (findZhangPosition(placement) <= 'L')) && ((locationChar >= 'G') && (locationChar <= 'L'))) {
                return true;
            } else {
                if (((findZhangPosition(placement) >= 'M') && (findZhangPosition(placement) <= 'R')) && ((locationChar == 'M') || (locationChar >= 'N') && (locationChar <= 'R'))) {
                    return true;
                } else {
                    if (((findZhangPosition(placement) >= 'S') && (findZhangPosition(placement) <= 'X')) && ((locationChar >= 'S') && (locationChar <= 'X'))) {
                        return true;
                    } else {
                        if (((findZhangPosition(placement) == 'Y') || (findZhangPosition(placement) == 'Z') || (findZhangPosition(placement) >= '0') && (findZhangPosition(placement) <= '3')) && ((locationChar == 'Y') || (locationChar == 'Z') || (locationChar >= '0') && (locationChar <= '3'))) {
                            return true;
                        } else {
                            if (((findZhangPosition(placement) >= '4') && (findZhangPosition(placement) <= '9')) && ((locationChar >= '4') && (locationChar <= '9'))) {
                                return true;
                            }
                        }

                    }
                }

            }
        }
        return false;
    }


    //check whether locationChar and ZhangYi is in the same row
    public static boolean isSameRow(String placement, char locationChar) {
        if (((findZhangPosition(placement) == '4') || (findZhangPosition(placement) == 'Y') || (findZhangPosition(placement) == 'S') || (findZhangPosition(placement) == 'M') || (findZhangPosition(placement) == 'G') || (findZhangPosition(placement) == 'A')) && ((locationChar == '4') || (locationChar == 'Y') || (locationChar == 'S') || (locationChar == 'M') || (locationChar == 'G') || (locationChar == 'A'))) {
            return true;
        } else {
            if (((findZhangPosition(placement) == '5') || (findZhangPosition(placement) == 'Z') || (findZhangPosition(placement) == 'T') || (findZhangPosition(placement) == 'N') || (findZhangPosition(placement) == 'H') || (findZhangPosition(placement) == 'B')) && ((locationChar == '5') || (locationChar == 'Z') || (locationChar == 'T') || (locationChar == 'N') || (locationChar == 'H') || (locationChar == 'B'))) {
                return true;
            } else {
                if (((findZhangPosition(placement) == '6') || (findZhangPosition(placement) == '0') || (findZhangPosition(placement) == 'U') || (findZhangPosition(placement) == 'O') || (findZhangPosition(placement) == 'I') || (findZhangPosition(placement) == 'C')) && ((locationChar == '6') || (locationChar == '0') || (locationChar == 'U') || (locationChar == 'O') || (locationChar == 'I') || (locationChar == 'C'))) {
                    return true;
                } else {
                    if (((findZhangPosition(placement) == '7') || (findZhangPosition(placement) == '1') || (findZhangPosition(placement) == 'V') || (findZhangPosition(placement) == 'P') || (findZhangPosition(placement) == 'J') || (findZhangPosition(placement) == 'D')) && ((locationChar == '7') || (locationChar == '1') || (locationChar == 'V') || (locationChar == 'P') || (locationChar == 'J') || (locationChar == 'D'))) {
                        return true;
                    } else {
                        if (((findZhangPosition(placement) == '8') || (findZhangPosition(placement) == '2') || (findZhangPosition(placement) == 'W') || (findZhangPosition(placement) == 'Q') || (findZhangPosition(placement) == 'K') || (findZhangPosition(placement) == 'E')) && ((locationChar == '8') || (locationChar == '2') || (locationChar == 'W') || (locationChar == 'Q') || (locationChar == 'K') || (locationChar == 'E'))) {
                            return true;
                        } else if (((findZhangPosition(placement) == '9') || (findZhangPosition(placement) == '3') || (findZhangPosition(placement) == 'X') || (findZhangPosition(placement) == 'R') || (findZhangPosition(placement) == 'L') || (findZhangPosition(placement) == 'F')) && ((locationChar == '9') || (locationChar == '3') || (locationChar == 'X') || (locationChar == 'R') || (locationChar == 'L') || (locationChar == 'F'))) {
                            return true;
                        }

                    }
                }

            }
        }

        return false;
    }

    //change all locations in board to a number (For A to Z, using ascii number. 0 to 9, define new values. 0 is set to 92. 1 to 93.
    // and so on.
    public static int changeToNumber(char locationChar) {
        int a = (int) (locationChar);
        int b = 0;
        if (Character.isDigit(locationChar)) {
            b = 91 + (locationChar - '0');
        } else {
            b = (int) locationChar;
        }
        return b;
    }

    //get all cards in same column with ZhangYi
    public static ArrayList<Character> getRowlist(String placement) {
        int j = placement.length();
        ArrayList<Character> rowlist = new ArrayList<>();
        {
            for (int i = 0; i < j; i = i + 3) {
                if (isSameRow(placement, placement.charAt(i + 2))) {
                    rowlist.add(placement.charAt(i));
                    rowlist.add(placement.charAt(i + 1));
                    rowlist.add(placement.charAt(i + 2));
                }

            }
        }
        return rowlist;
    }

    //get all cards in same row with ZhangYi
    public static ArrayList<Character> getcolumnlist(String placement) {
        int j = placement.length();
        ArrayList<Character> colmunlist = new ArrayList<>();
        {
            for (int i = 0; i < j; i = i + 3) {
                if (isSameColumn(placement, placement.charAt(i + 2))) {
                    colmunlist.add(placement.charAt(i));
                    colmunlist.add(placement.charAt(i + 1));
                    colmunlist.add(placement.charAt(i + 2));
                }
            }
        }
        return colmunlist;
    }

    //get all cards'(which are in same row with ZhangYi and same kingdom with locationChar) location characters
    public static ArrayList<Character> getrow(String placement, char locationChar) {
        ArrayList<Character> row = new ArrayList<>();
        ArrayList<Character> rowlist = getRowlist(placement);
        int k = rowlist.size();
        char n = ' ';
        for (int m = 0; m < k; m = m + 3) {
            if ((rowlist.get(m + 2)) == locationChar) {
                n = rowlist.get(m);

        for (int a = 0; a < k; a = a + 3) {
            if (rowlist.get(a) == n) {
                row.add(rowlist.get(a + 2));
            }
        }}}
        return row;
    }

//    public static ArrayList<Character> getrow(String placement, char locationChar) {
//        ArrayList<Character> list = new ArrayList<>();
//        char kingdom = ' ';
//        char ah = 'a';
//        int k = placement.length();
//        for (int i = 0; i < k; i = i +3) {
//            if (placement.charAt(i+2) == locationChar) {
//                kingdom = placement.charAt(i);
//                break;
//            }
//        for (int j = 0; j < k; j = j + 3) {
//            if (isSameRow(placement,placement.charAt(j+2)) && placement.charAt(j)== kingdom) {
//                list.add(placement.charAt(j+2));
//            }
//        }}
//        return list;
//    }

    //get all cards'(which are in same column with ZhangYi and same kingdom with locationChar) location characters
    public static ArrayList<Character> getcol(String placement, char locationChar) {
        ArrayList<Character> colmunlist = getcolumnlist(placement);
        ArrayList<Character> col = new ArrayList<Character>();
        int l = colmunlist.size();
        char c = ' ';
        for (int b = 0; b < l; b = b + 3) {
            if (colmunlist.get(b + 2) == locationChar) {
                c = colmunlist.get(b);
                for (int d = 0; d < l; d = d + 3) {
                    if (colmunlist.get(d) == c) {
                        col.add(colmunlist.get(d + 2));
                    }
                }
            }
        }
        return col;
    }

    //to check whether there are some further cards for locationChar
    public static boolean checkFurtherCard(String placement, char locationChar) {
        int j = placement.length();
        boolean h = true;
        ArrayList<Character> row = getrow(placement, locationChar);
        ArrayList<Character> col = getcol(placement, locationChar);
        int e = col.size();
        int f = row.size();
        if (isSameColumn(placement, locationChar)) {
            if (changeToNumber(findZhangPosition(placement)) > changeToNumber(locationChar)) {
                if (e == 0) {
                    h = true;
                } else {
                    for (int g = 0; g < e; g++) {
                        if (changeToNumber(col.get(g)) < changeToNumber(locationChar)) {
                            h = false;
                        }
                    }
                }
            } else {
                if (changeToNumber(findZhangPosition(placement)) < changeToNumber(locationChar)) {
                    for (int x = 0; x < e; x++) {
                        if (changeToNumber(col.get(x)) > changeToNumber(locationChar)) {
                            h = false;
                        }
                    }
                }
            }
        }

        if (isSameRow(placement, locationChar)) {
            if (changeToNumber(findZhangPosition(placement)) > changeToNumber(locationChar)) {
                if (f == 0) {
                    h = true;
                } else {
                    for (int o = 0; o < f; o++) {
                        if (changeToNumber(row.get(o)) < changeToNumber(locationChar)) {
                            h = false;
                        }
                    }
                }
            } else {
                if (changeToNumber(findZhangPosition(placement)) < changeToNumber(locationChar)) {
                    for (int p = 0; p < f; p++) {
                        if (changeToNumber(row.get(p)) > changeToNumber(locationChar)) {
                            h = false;
                        }
                    }
                }
            }
        }
        return h;
    }

    /**
     * Determine whether a move sequence is valid.
     * To be valid, the move sequence must be comprised of 1..N location characters
     * showing the location to move for Zhang Yi, and each move must be valid
     * given the placement that would have resulted from the preceding sequence
     * of moves.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing moves
     * @return True if the placement sequence is valid
     */
    static boolean isMoveSequenceValid(String setup, String moveSequence) {
        // FIXME Task 6: determine whether a placement sequence is valid
        int j = moveSequence.length();
        for (int i = 0; i < j; i++) {
            if (setup.equals("")) {return false;}
            if (isMoveLegal(setup, moveSequence.charAt(i))) {
                setup = deleteEmptyLocation(setup, moveSequence.charAt(i));
                setup += "z9" + moveSequence.charAt(i);   //update ZhangYi's position
            } else {
                return false;

            }
        }
        return true;
    }

    //give a locationChar, return the index of the kingdom card stored in string
    // setup(e.g. if setput string is a01a19z9S, locationChar is 1, the return index should be 0.
    public static int getThePositionInSetup(String setup, char locationChar) {
        int a = setup.length();

        for (int b = 0; b < a; b += 3) {
            if (setup.charAt(b + 2) == locationChar) {
                return b;
            }
        }
        return -1;
    }

    //For a valid move, the location for ZhangYi before moving and the cards between ZhangYi and locationChar
    // should be deleted.
    public static String deleteEmptyLocation(String setup, char locationChar) {
        char zhangLocation = findZhangPosition(setup); // LocationChar

        ArrayList<Character> col = getcol(setup, locationChar);

        ArrayList<Character> row = getrow(setup, locationChar);

        if (isSameRow(setup, locationChar)) {
            row.add(zhangLocation);
            for (int e = 0; e < row.size(); e++) {
                    int index = getThePositionInSetup(setup,row.get(e));
                    if (index == -1) {
                        return "";
                    }
                if (changeToNumber(zhangLocation) > changeToNumber(locationChar)) {
                    if ((changeToNumber(zhangLocation) >= changeToNumber(row.get(e))) && (changeToNumber(locationChar) <= changeToNumber(row.get(e)))) {
                        setup = setup.substring(0,index) + setup.substring(index + 3);
                    }
                } else {
                    if ((changeToNumber(zhangLocation) <= changeToNumber(row.get(e))) && (changeToNumber(locationChar) >= changeToNumber(row.get(e)))) {
                        setup = setup.substring(0,index) + setup.substring(index + 3);
                    }
                }
            }

        }

        else {
            if (isSameColumn(setup, locationChar)) {
                col.add(zhangLocation);
                for (int f = 0; f < col.size(); f++) {
                    int index = getThePositionInSetup(setup, col.get(f));
                    if (index == -1) {
                        return "";
                    }
                    if (changeToNumber(zhangLocation) > changeToNumber(locationChar)) {
                        if ((changeToNumber(zhangLocation) >= changeToNumber(col.get(f))) && (changeToNumber(locationChar) <= changeToNumber(col.get(f)))) {
                            setup = setup.substring(0, index) + setup.substring(index + 3);
                        }
                    } else {
                        if ((changeToNumber(zhangLocation) <= changeToNumber(col.get(f))) && (changeToNumber(locationChar) >= changeToNumber(col.get(f)))) {
                            setup = setup.substring(0, index) + setup.substring(index + 3);
                        }
                    }
                }
            }
        }

        return setup;
    }


    /**
     * Get the list of supporters for the chosen player, given the provided
     * setup and move sequence.
     * The list of supporters is a sequence of two-character card IDs, representing
     * the cards that the chosen player collected by moving Zhang Yi.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing moves
     * @param numPlayers   the number of players in the game, must be in the range [2..4]
     * @param playerId     the player number for which to get the list of supporters, [0..(numPlayers-1)]
     * @return the list of supporters for the given player
     */
    public static String getSupporters(String setup, String moveSequence, int numPlayers, int playerId) {
        // FIXME Task 7: get the list of supporters for a given player after a sequence of moves
        String a = "";
        ArrayList<String> list = returnSupporters(setup,moveSequence,numPlayers);
        if (playerId == 0) {
            a = list.get(0);
        } else {if (playerId == 1) {
            a = list.get(1);
        } else {if (playerId == 2) {
            a = list.get(2);
        } else {if (playerId == 3) {
            a = list.get(3);
        }}}}
        a = rearrange(a);
        return a;
    }
    //return the list of supports for every player
    public static ArrayList<String> returnSupporters(String setup,String moveSequence, int numPlayers) {
        ArrayList<String> list = new ArrayList<>();
//       String Player1 = "";
//       String Player2 = "";
//       String Player3 = "";
//       String Player4 = "";
        ArrayList<String> Player1 = new ArrayList<>();
        ArrayList<String> Player2 = new ArrayList<>();
        ArrayList<String> Player3 = new ArrayList<>();
        ArrayList<String> Player4 = new ArrayList<>();
       int j = moveSequence.length();
       for (int i = 0; i < j; i++) {
           int a = getCurrentPlayer(i,numPlayers);
           if (a == 1) {
               Player1.add(allPosition(moveSequence.charAt(i),setup));
               setup = deleteEmptyLocation(setup,moveSequence.charAt(i));
               setup += "z9" + moveSequence.charAt(i);
           } else if (a == 2) {
               Player2.add(allPosition(moveSequence.charAt(i),setup));
               setup = deleteEmptyLocation(setup,moveSequence.charAt(i));
               setup += "z9" + moveSequence.charAt(i);
           } else if (a == 3) {
               Player3.add(allPosition(moveSequence.charAt(i),setup));
               setup = deleteEmptyLocation(setup,moveSequence.charAt(i));
               setup += "z9" + moveSequence.charAt(i);
           } else if (a == 4) {
               Player4.add(allPosition(moveSequence.charAt(i),setup));
               setup = deleteEmptyLocation(setup,moveSequence.charAt(i));
               setup += "z9" + moveSequence.charAt(i);
           }
       }
       list.add(returnList(Player1));
       list.add(returnList(Player2));
       list.add(returnList(Player3));
       list.add(returnList(Player4));
       return list;
    }

    public static String returnList(ArrayList<String> list) {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += list.get(i);
        }
        return str;
    }

    //get current player
    public static int getCurrentPlayer(int num,int numPlayers) {
        int d = 0;
        switch (numPlayers) {
            case 2:
                if (num % 2 == 0) {
                    d = 1;
                } else {d = 2;
        }
        break;
            case 3:
                if (num % 3 == 0) {
                    d = 1;
                } else {if (num % 3 == 1) {
                    d = 2;
                } else {d = 3;
    }}
        break;
            case 4:
                if (num % 4 == 0) {
                    d = 1;
                } else {if (num % 4 == 1) {
                    d = 2;
                } else {if (num % 4 == 2) {
                    d = 3;
                } else {
                    d = 4;
                }}}
                break;
             default:
                 d = -1;}
                return d;}

    public static String rearrange(String supportors) {
        ArrayList<Integer> a = new ArrayList();
        ArrayList<Integer> b = new ArrayList();
        ArrayList<Integer> c = new ArrayList();
        ArrayList<Integer> d = new ArrayList();
        ArrayList<Integer> e = new ArrayList();
        ArrayList<Integer> f = new ArrayList();
        ArrayList<Integer> g = new ArrayList();
        for (int i = 0; i < supportors.length(); i += 2) {
            if (supportors.charAt(i) == 'a') {
                a.add(Character.getNumericValue(supportors.charAt(i + 1)));
            } else {
                if (supportors.charAt(i) == 'b') {
                    b.add(Character.getNumericValue(supportors.charAt(i + 1)));
                } else {
                    if (supportors.charAt(i) == 'c') {
                        c.add(Character.getNumericValue(supportors.charAt(i + 1)));
                    } else {
                        if (supportors.charAt(i) == 'd') {
                            d.add(Character.getNumericValue(supportors.charAt(i + 1)));
                        } else {
                            if (supportors.charAt(i) == 'e') {
                                e.add(Character.getNumericValue(supportors.charAt(i + 1)));
                            } else {
                                if (supportors.charAt(i) == 'f') {
                                    f.add(Character.getNumericValue(supportors.charAt(i + 1)));
                                } else {
                                    if (supportors.charAt(i) == 'g') {
                                        g.add(Character.getNumericValue(supportors.charAt(i + 1)));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(a);
        Collections.sort(b);
        Collections.sort(c);
        Collections.sort(d);
        Collections.sort(e);
        Collections.sort(f);
        Collections.sort(g);
        String str = "";
        if(!(a.size()==0) ) {
            for (int i =0; i < a.size();i++) {
                str += "a";
                str += a.get(i);
            }
        }
        if(!(b.size()==0) ) {
            for (int i =0; i < b.size();i++) {
                str += "b";
                str += b.get(i);
            }
        }
        if(!(c.size()==0) ) {
            for (int i =0; i < c.size();i++) {
                str += "c";
                str += c.get(i);
            }
        }
        if(!(d.size()==0) ) {
            for (int i =0; i < d.size();i++) {
                str += "d";
                str += d.get(i);
            }
        }
        if(!(e.size()==0) ) {
            for (int i =0; i < e.size();i++) {
                str += "e";
                str += e.get(i);
            }
        }
        if(!(f.size()==0) ) {
            for (int i =0; i < f.size();i++) {
                str += "f";
                str += f.get(i);
            }
        }
        if(!(g.size()==0) ) {
            for (int i =0; i < g.size();i++) {
                str += "g";
                str += g.get(i);
            }
        }
        return str;
    }

    //get all supports between the initial location and desitination
    public static String allPosition(char locationChar, String setup) {
        char zhangLocation = findZhangPosition(setup); // LocationChar

        ArrayList<Character> col = getcol(setup, locationChar);

        ArrayList<Character> row = getrow(setup, locationChar);

        String str = "";

        if (isSameRow(setup, locationChar)) {
            for (int e = 0; e < row.size(); e++) {
                int index = getThePositionInSetup(setup,row.get(e));
                if (index == -1) {
                    return "";
                }
                if (changeToNumber(zhangLocation) > changeToNumber(locationChar)) {
                    if ((changeToNumber(zhangLocation) >= changeToNumber(row.get(e))) && (changeToNumber(locationChar) <= changeToNumber(row.get(e)))) {
                        str += setup.substring(index,index +2);
                    }
                } else {
                    if ((changeToNumber(zhangLocation) <= changeToNumber(row.get(e))) && (changeToNumber(locationChar) >= changeToNumber(row.get(e)))) {
                        str += setup.substring(index,index +2);
                    }
                }
            }

        }

        else {
            if (isSameColumn(setup, locationChar)) {
                for (int f = 0; f < col.size(); f++) {
                    int index = getThePositionInSetup(setup, col.get(f));
                    if (index == -1) {
                        return "";
                    }
                    if (changeToNumber(zhangLocation) > changeToNumber(locationChar)) {
                        if ((changeToNumber(zhangLocation) >= changeToNumber(col.get(f))) && (changeToNumber(locationChar) <= changeToNumber(col.get(f)))) {
                            str += setup.substring(index,index +2);
                        }
                    } else {
                        if ((changeToNumber(zhangLocation) <= changeToNumber(col.get(f))) && (changeToNumber(locationChar) >= changeToNumber(col.get(f)))) {
                            str += setup.substring(index,index +2);
                        }
                    }
                }
            }
        }
        return str;
    }

    /**
     * Given a setup and move sequence, determine which player controls the flag of each kingdom
     * after all the moves in the sequence have been played.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing a sequence of moves
     * @param numPlayers   the number of players in the game, must be in the range [2..4]
     * @return an array containing the player ID who controls each kingdom, where
     * - element 0 contains the player ID of the player who controls the flag of Qin
     * - element 1 contains the player ID of the player who controls the flag of Qi
     * - element 2 contains the player ID of the player who controls the flag of Chu
     * - element 3 contains the player ID of the player who controls the flag of Zhao
     * - element 4 contains the player ID of the player who controls the flag of Han
     * - element 5 contains the player ID of the player who controls the flag of Wei
     * - element 6 contains the player ID of the player who controls the flag of Yan
     * If no player controls a particular house, the element for that house will have the value -1.
     */
    public static int[] getFlags(String setup, String moveSequence, int numPlayers) {
        // FIXME Task 8: determine which player controls the flag of each kingdom after a given sequence of moves
//        ArrayList<String> list = returnSupporters(setup,moveSequence,numPlayers);
        String a = moveSequence;
        int[] array = new int[7];
        array[0] = getMostPlayer(setup,a,numPlayers,'a');
        array[1] = getMostPlayer(setup,a,numPlayers,'b');
        array[2] = getMostPlayer(setup,a,numPlayers,'c');
        array[3] = getMostPlayer(setup,a,numPlayers,'d');
        array[4] = getMostPlayer(setup,a,numPlayers, 'e');
        array[5] = getMostPlayer(setup,a,numPlayers,'f');
        array[6] = getMostPlayer(setup,a,numPlayers,'g');
        return array;}

    public static ArrayList<String> returnSupportersList(String setup,String moveSequence, int numPlayers,int PlyerID) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> Player1 = new ArrayList<>();
        ArrayList<String> Player2 = new ArrayList<>();
        ArrayList<String> Player3 = new ArrayList<>();
        ArrayList<String> Player4 = new ArrayList<>();
        int j = moveSequence.length();
        for (int i = 0; i < j; i++) {
            int a = getCurrentPlayer(i,numPlayers);
            if (a == 1) {
                Player1.add(allPosition(moveSequence.charAt(i),setup));
                setup = deleteEmptyLocation(setup,moveSequence.charAt(i));
                setup += "z9" + moveSequence.charAt(i);
            } else if (a == 2) {
                Player2.add(allPosition(moveSequence.charAt(i),setup));
                setup = deleteEmptyLocation(setup,moveSequence.charAt(i));
                setup += "z9" + moveSequence.charAt(i);
            } else if (a == 3) {
                Player3.add(allPosition(moveSequence.charAt(i),setup));
                setup = deleteEmptyLocation(setup,moveSequence.charAt(i));
                setup += "z9" + moveSequence.charAt(i);
            } else if (a == 4) {
                Player4.add(allPosition(moveSequence.charAt(i),setup));
                setup = deleteEmptyLocation(setup,moveSequence.charAt(i));
                setup += "z9" + moveSequence.charAt(i);
            }
        }
        if (PlyerID == 0) {
            list = Player1;
        } else if (PlyerID == 1) {
            list= Player2;
        } else if (PlyerID == 2) {
            list = Player3;
        } else {
            list= Player4;
        }
        return list;
    }

    public static int getMostPlayer(String setup, String moveSequence, int numPlayer, char kingdom) {
        int playerID = -6;
        ArrayList<Integer> num = new ArrayList<>();
        ArrayList<Integer> IDs = new ArrayList<>();
        ArrayList<Integer> a = holdNumbers(setup,moveSequence,numPlayer,kingdom);
        HashMap<Integer,Integer> map = new HashMap<>();
        int max = Collections.max(a);
        if (max == 0) {
            playerID = -1;
        } else if (count(a,max) == 1) {
            playerID = whenAppear(a,max).get(0);
        } else  if (count(a,max) > 1) {
            IDs = whenAppear(a,max);
            for (Integer id : IDs) {
                ArrayList<String> list = returnSupportersList(setup,moveSequence,numPlayer,id);
                for ( int j = list.size() -1; j >= 0; j --) {
                    if (list.get(j).indexOf(kingdom) >= 0) {
                        num.add(j);
                        break;
                    }
                }
            }
            int maxi = Collections.max(num);
//            for (int x = 0; x < num.size(); x ++) {
//                if (num.get(x) == maxi) {
//                    playerID = IDs.get(x);
//                }
//            }
            playerID = IDs.get(num.lastIndexOf(maxi));
        }
        return playerID;
    }

    public static ArrayList<Integer> holdNumbers(String setup, String moveSequence, int numPlayer, char kingdom) {
        ArrayList<String> list = returnSupporters(setup, moveSequence, numPlayer);
        int num = 0;
        ArrayList<Integer> a = new ArrayList<>(); // store the cards' number that player holds
        for (int x = 0; x < numPlayer; x ++) {
            for (int y = 0; y < list.get(x).length(); y ++) {
                if (list.get(x).charAt(y) == kingdom) {
                    num += 1;
                }
            }
            a.add(num);
            num = 0;
        }
        return a;
    }

    //when shown in a arraylist (the playerids)
    public static ArrayList<Integer> whenAppear(ArrayList<Integer> a, Integer b) {
        ArrayList<Integer> list = new ArrayList<>();
        if (a.size() > 0) {
        for (int i = 0; i < a.size(); i ++) {
            if (a.get(i) == b) {
                list.add(i);
            }
        }}
        return list;
    }

    //count how many times shown in a arraylist
    public static int count(ArrayList<Integer> a, Integer b) {
        int c = 0;
        for (int i = 0; i < a.size(); i ++ ) {
            if (a.get(i) == b) {
                c += 1;
            }
        }
        return c;}

    /**
     * Generate a legal move, given the provided placement string.
     * A move is valid if:
     *
     * - the location char is in the range A .. Z or 0..9
     * - there is a card at the chosen location;
     * - the destination location is different to Zhang Yi's current location;
     * - the destination is in the same row or column of the grid as Zhang Yi's current location; and
     * - drawing a line from Zhang Yi's current location through the card at the chosen location,
     * there are no other cards along the line from the same kingdom as the chosen card
     * that are further away from Zhang Yi.
     * If there is no legal move available, return the null character '\0'.
     *
     * @param placement the current placement string
     * @return a location character representing Zhang Yi's destination for the move
     */
    public static char generateMove(String placement) {
        // FIXME Task 10: generate a legal move
        char re = ' ';
        for (int i = 0; i < placement.length(); i = i+3) {
            if (isMoveLegal(placement,placement.charAt(i+2)) && placement.charAt(i) != 'z') {
                re = placement.charAt(i+2);
                break;
            }
        }
        if (re != ' ') {
            return re;
        } else {
        return '\0';
    }
}}
