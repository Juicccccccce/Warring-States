package comp1110.ass2.gui;

import comp1110.ass2.WarringStatesGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Minimax {
    static int numberOfPlayers = 2;

    public static char makeMove(String board, String moveSequence, String initialPlacement, int playerID) {
        String moves = WarringStatesGame.generateAllMove(board);
        List<Integer> scores = new ArrayList<>();

        for (int i = 0; i < moves.length(); i++) {
            String updatedBoard = WarringStatesGame.deleteEmptyLocation(board, moves.charAt(i));
            updatedBoard += "z9" + moves.charAt(i);
            scores.add(minimax(updatedBoard, moveSequence + moves.charAt(i), initialPlacement, playerID, 1, numberOfPlayers, 4));
        }

        int index = scores.indexOf(Collections.max(scores));
        return moves.charAt(index);
    }

    static int minimax(String board, String moveSequence, String initialPlacement, int playerID, int count, int numberOfPlayers, int depth) {
        if (depth == 0 || WarringStatesGame.generateMove(board) == '\0') {
            return Game.getScores(initialPlacement, moveSequence, numberOfPlayers, playerID);
        }
        if (count % numberOfPlayers == 0) {
            List<Integer> scores = new ArrayList<>();
            String moves = WarringStatesGame.generateAllMove(board);
            for (int i = 0; i < moves.length(); i++) {
                String updatedBoard = WarringStatesGame.deleteEmptyLocation(board, moves.charAt(i));
                updatedBoard += "z9" + moves.charAt(i);
                scores.add(minimax(updatedBoard, moveSequence + moves.charAt(i), initialPlacement, playerID, count + 1, numberOfPlayers, depth - 1));
            }
            return Collections.max(scores);
        } else {
            List<Integer> scores = new ArrayList<>();
            String moves = WarringStatesGame.generateAllMove(board);
            for (int i = 0; i < moves.length(); i++) {
                String updatedBoard = WarringStatesGame.deleteEmptyLocation(board, moves.charAt(i));
                updatedBoard += "z9" + moves.charAt(i);
                scores.add(minimax(updatedBoard, moveSequence + moves.charAt(i), initialPlacement, playerID, count + 1, numberOfPlayers, depth - 1));
            }
            return Collections.min(scores);
        }
    }
}
