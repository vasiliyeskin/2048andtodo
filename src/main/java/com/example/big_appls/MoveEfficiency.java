package com.example.big_appls;

import java.util.Comparator;

/**
 * Created by Vaisiliy Es'kin on 02/19/17.
 */
public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles, score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        if(this.numberOfEmptyTiles > o.numberOfEmptyTiles)
            return 1;
        else if(this.numberOfEmptyTiles < o.numberOfEmptyTiles)
            return -1;
        else if(this.score > o.score)
            return 1;
        else if(this.score < o.score)
            return -1;

        return 0;
    }
}
