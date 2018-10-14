package com.isima.zz2.simu.model;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

/**
 * Piece.
 * <p>
 * Created by Mathieu on 16/11/2017.
 */
@EqualsAndHashCode
@ToString
public class Piece {
    private static int cptr = 1;
    private final int number;

    @Setter
    private int entryDate;
    @Setter
    private int exitDate;
    @Setter
    private int servorDate;

    public Piece() {
        this.number = cptr++;
    }

    public int systemTime() {
        return exitDate - entryDate;
    }

    public int servorTime() {
        return exitDate - servorDate;
    }

    public int queueTime() {
        return servorDate - entryDate;
    }
}
