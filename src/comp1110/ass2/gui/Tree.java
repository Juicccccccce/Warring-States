package comp1110.ass2.gui;

import comp1110.ass2.WarringStatesGame;

public class Node {
    public class Board {
        int depth;
        boolean maxPlayer;
        String move;
        int score;
    }
    public Node generateTree(Board board, int depth, String placement) {
        Node rootNode = new Node(board);
        generateSubtree()

    }

    private void generateSubtree(Node subRootNode, int depth, String placement) {
        Board board = subRootNode.getBoard();
        String moveArray = WarringStatesGame.generateAllMove(placement);
        if (depth == 0 || moveArray.length() == 0 ) {
            subRootNode.setValue(board.evaluateBoard());
            return;
        }

        for (Move move : board.generateMoves()) {
            Board tempBoard = board.makeMove(move);
            Node tempNode = new Node(tempBoard);
            subRootNode.addChild(tempNode);
            generateSubtree(tempNode, depth - 1);
        }
    }
}
