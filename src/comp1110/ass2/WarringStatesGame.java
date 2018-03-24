package comp1110.ass2;

import gittest.A;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class provides the text interface for the Warring States game
 */
public class    WarringStatesGame {

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
        int a = (int)(cardArray[0]-'a') + Character.getNumericValue(cardArray[1]);
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
            } return loc;
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
        } return cards;
    }

    // Creates ArrayList<String> consisting of cards (two-character placements)
    // by removing every third element of input string
    public static ArrayList<String> dupCards(String pl) {
        String a = pl.replaceAll("(..).", "$1");
        ArrayList<String> cards = new ArrayList<>();
        for (int i = 0; i <a.length(); i+= 2){
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
       } return true;
   }

    /**
     * Determine whether a given move is legal given a provided valid placement:
     * - the location char is in the range A .. Z or 0..9
     * - there is a card at the chosen location;
     * - the location is in the same row or column of the grid as Zhang Yi's current position; and
     * - drawing a line from Zhang Yi's current location through the card at the chosen location,
     *   there are no other cards along the line from the same kingdom as the chosen card
     *   that are further away from Zhang Yi.
     * @param placement    the current placement string
     * @param locationChar a location for Zhang Yi to move to
     * @return true if Zhang Yi may move to that location
     */
    public static boolean isMoveLegal(String placement, char locationChar) {
        // FIXME Task 5: determine whether a given move is legal
        int a = placement.length();
        if (changeToNumber(locationChar) >= 65 && changeToNumber(locationChar) <= 101) {
            if (isCardExist(placement,locationChar)) {
                if(isSameColumn(placement,locationChar)||(isSameRow(placement,locationChar))) {
                   if(checkFurtherCard(placement,locationChar)) {
                       return true;
                   }
                }

            }
        }
        return false;}

    // check whether there is a card at the chosen location
    public static boolean isCardExist (String placement,char locationChar) {
        int c = placement.length();
        {for (int b = 0; b < c; b=b+3) {
            char d = placement.charAt(b+2);
           if (d == locationChar) {
            return true;
        }}
        return false;}}

    public static char findZhangPosition(String placement) {
        int j = placement.length();
        char z = 'a';
        for (int i= 0;i<j; i=i+3) {
            if(placement.charAt(i)=='z') {
                return z = placement.charAt(i+2);
            }
        }
    return z;}

    public static boolean isSameColumn(String placement,char locationChar) {
                  if (((findZhangPosition(placement) == 'A')||(findZhangPosition(placement)=='B')||(findZhangPosition(placement)=='C')||(findZhangPosition(placement)=='D')||(findZhangPosition(placement)=='E')||(findZhangPosition(placement)=='F'))&&((locationChar=='A')||(locationChar=='B')||(locationChar=='C')||(locationChar=='D')||(locationChar=='E')||(locationChar=='F'))) {
                      return true;
                  }
                  else {if (((findZhangPosition(placement) == 'G')||(findZhangPosition(placement)=='H')||(findZhangPosition(placement)=='I')||(findZhangPosition(placement)=='J')||(findZhangPosition(placement)=='K')||(findZhangPosition(placement)=='L'))&&((locationChar=='G')||(locationChar=='H')||(locationChar=='I')||(locationChar=='J')||(locationChar=='K')||(locationChar=='L'))) {
                      return true;
                  }
                  else {if (((findZhangPosition(placement) == 'M')||(findZhangPosition(placement)=='N')||(findZhangPosition(placement)=='O')||(findZhangPosition(placement)=='P')||(findZhangPosition(placement)=='Q')||(findZhangPosition(placement)=='R'))&&((locationChar=='M')||(locationChar=='N')||(locationChar=='O')||(locationChar=='P')||(locationChar=='Q')||(locationChar=='R'))) {
                      return true;
                  }
                       else {if (((findZhangPosition(placement) == 'S')||(findZhangPosition(placement)=='T')||(findZhangPosition(placement)=='U')||(findZhangPosition(placement)=='V')||(findZhangPosition(placement)=='W')||(findZhangPosition(placement)=='X'))&&((locationChar=='S')||(locationChar=='T')||(locationChar=='U')||(locationChar=='V')||(locationChar=='W')||(locationChar=='X'))) {
                      return true;
                  }
                             else {if (((findZhangPosition(placement) == 'Y')||(findZhangPosition(placement)=='Z')||(findZhangPosition(placement)=='0')||(findZhangPosition(placement)=='1')||(findZhangPosition(placement)=='2')||(findZhangPosition(placement)=='3'))&&((locationChar=='Y')||(locationChar=='Z')||(locationChar=='0')||(locationChar=='1')||(locationChar=='2')||(locationChar=='3'))) {
                                 return true;
                  }
                                   else if (((findZhangPosition(placement) == '4')||(findZhangPosition(placement)=='5')||(findZhangPosition(placement)=='6')||(findZhangPosition(placement)=='7')||(findZhangPosition(placement)=='8')||(findZhangPosition(placement)=='9'))&&((locationChar=='4')||(locationChar=='5')||(locationChar=='6')||(locationChar=='7')||(locationChar=='8')||(locationChar=='9'))) {
                                      return true;
                  }

                  }}

        }}
    return false;}


    public static boolean isSameRow(String placement,char locationChar) {
                if (((findZhangPosition(placement) == '4')||(findZhangPosition(placement)=='Y')||(findZhangPosition(placement)=='S')||(findZhangPosition(placement)=='M')||(findZhangPosition(placement)=='G')||(findZhangPosition(placement)=='A'))&&((locationChar=='4')||(locationChar=='Y')||(locationChar=='S')||(locationChar=='M')||(locationChar=='G')||(locationChar=='A'))) {
                    return true;
                }
                else {if (((findZhangPosition(placement) == '5')||(findZhangPosition(placement)=='Z')||(findZhangPosition(placement)=='T')||(findZhangPosition(placement)=='N')||(findZhangPosition(placement)=='H')||(findZhangPosition(placement)=='B'))&&((locationChar=='5')||(locationChar=='Z')||(locationChar=='T')||(locationChar=='N')||(locationChar=='H')||(locationChar=='B'))) {
                    return true;
                }
                else {if (((findZhangPosition(placement) == '6')||(findZhangPosition(placement)=='0')||(findZhangPosition(placement)=='U')||(findZhangPosition(placement)=='O')||(findZhangPosition(placement)=='I')||(findZhangPosition(placement)=='C'))&&((locationChar=='6')||(locationChar=='0')||(locationChar=='U')||(locationChar=='O')||(locationChar=='I')||(locationChar=='C'))) {
                    return true;
                }
                else {if (((findZhangPosition(placement) == '7')||(findZhangPosition(placement)=='1')||(findZhangPosition(placement)=='V')||(findZhangPosition(placement)=='P')||(findZhangPosition(placement)=='J')||(findZhangPosition(placement)=='D'))&&((locationChar=='7')||(locationChar=='1')||(locationChar=='V')||(locationChar=='P')||(locationChar=='J')||(locationChar=='D'))) {
                    return true;
                }
                else {if (((findZhangPosition(placement) == '8')||(findZhangPosition(placement)=='2')||(findZhangPosition(placement)=='W')||(findZhangPosition(placement)=='Q')||(findZhangPosition(placement)=='K')||(findZhangPosition(placement)=='E'))&&((locationChar=='8')||(locationChar=='2')||(locationChar=='W')||(locationChar=='Q')||(locationChar=='K')||(locationChar=='E'))) {
                    return true;
                }
                else if (((findZhangPosition(placement) == '9')||(findZhangPosition(placement)=='3')||(findZhangPosition(placement)=='X')||(findZhangPosition(placement)=='R')||(findZhangPosition(placement)=='L')||(findZhangPosition(placement)=='F'))&&((locationChar=='9')||(locationChar=='3')||(locationChar=='X')||(locationChar=='R')||(locationChar=='L')||(locationChar=='F'))) {
                    return true;
                }

                }}

                }}

        return false;}

    public static int changeToNumber(char locationChar) {
        int a = (int) (locationChar);
        int b = 0;
        if(Character.isDigit(locationChar)) {
            b = 91 + (locationChar-'0');
        }
        else {b = (int) locationChar;}
        return b;}

    public static boolean checkFurtherCard(String placement,char locationChar) {
        int j = placement.length();
        ArrayList<Character> rowlist = new ArrayList<>();
        ArrayList<Character> colmunlist = new ArrayList<>();
        ArrayList<Character> row = new ArrayList<>();
        ArrayList<Character> col = new ArrayList<>();
        int e = row.size();
        int f = col.size();
        boolean h = true;
        int k = rowlist.size();
        int l = colmunlist.size();
        char n = '1';
        char c = '1';
        {for (int i=0;i<j;i=i+3) {
            if (isSameColumn(Character.toString(placement.charAt(i+2)),locationChar)) {
                   rowlist.add(placement.charAt(i));
                   rowlist.add(placement.charAt(i+1));
                   rowlist.add(placement.charAt(i+2));
            }
            else {
                colmunlist.add(placement.charAt(i));
                colmunlist.add(placement.charAt(i+1));
                colmunlist.add(placement.charAt(i+2));
            }
        }}
        if (isSameRow(placement,locationChar)) {
            for (int m=0; m<k;m=m+3) {
                if( rowlist.get(m+2) ==locationChar) {
                    n = rowlist.get(m);
                    for (int a=0; a < k; a=a+3 ) {
                        if (rowlist.get(a)==n) {
                            row.add(rowlist.get(a+2));
                        }
                    }
                }
            }
        }
        else {if (isSameColumn(placement,locationChar)) {
            for (int b=0;b<l;b=b+3) {
                if (colmunlist.get(b+2) == locationChar) {
                    c = colmunlist.get(b);
                    for(int d =0; d<l;d=d+3){
                        if (colmunlist.get(d)==c) {
                            col.add(colmunlist.get(d+2));
                        }
                    }
                }
            }
        }


        if(isSameColumn(placement,locationChar)) {
            if (changeToNumber(findZhangPosition(placement)) > changeToNumber(locationChar)) {
                for (int g = 0; g<f;g++) {
                    if (changeToNumber(row.get(g)) < changeToNumber(locationChar)) {
                        h = false;
                    }
                }
            }
            else { if(changeToNumber(findZhangPosition(placement)) < changeToNumber(locationChar)) {
                for (int x = 0; x < f;x++) {
                    if (changeToNumber(row.get(x)) > changeToNumber(locationChar)) {
                        h = false;
                    }
                }
            }
        }


    }
        else {if (changeToNumber(findZhangPosition(placement)) > changeToNumber(locationChar)) {
            for (int o = 0; o<e;o++) {
                if (changeToNumber(col.get(o)) < changeToNumber(locationChar)) {
                    h = false;
                }
            }
        }
        else { if(changeToNumber(findZhangPosition(placement)) < changeToNumber(locationChar)) {
            for (int p = 0; p < f;p++) {
                if (changeToNumber(col.get(p)) > changeToNumber(locationChar)) {
                    h = false;
                }
            }
        }
        }}}
    return h;}

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
        return false;
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
        return null;
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
        return null;
    }

    /**
     * Generate a legal move, given the provided placement string.
     * A move is valid if:
     * - the location char is in the range A .. Z or 0..9
     * - there is a card at the chosen location;
     * - the destination location is different to Zhang Yi's current location;
     * - the destination is in the same row or column of the grid as Zhang Yi's current location; and
     * - drawing a line from Zhang Yi's current location through the card at the chosen location,
     * there are no other cards along the line from the same kingdom as the chosen card
     * that are further away from Zhang Yi.
     * If there is no legal move available, return the null character '\0'.
     * @param placement the current placement string
     * @return a location character representing Zhang Yi's destination for the move
     */
    public static char generateMove(String placement) {
        // FIXME Task 10: generate a legal move
        return '\0';
    }
}
