package com.isima.zz2.simu.tp1.model;

/**
 * Created by Mathieu on 16/11/2017.
 */
public class Piece {
    private static int cptr = 1;

    private int number;

    private int entryDate;

    private int exitDate;

    private int servorDate;

    public int getNumber() {
        return number;
    }

    public int getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(int entryDate) {
        this.entryDate = entryDate;
    }

    public int getExitDate() {
        return exitDate;
    }

    public void setExitDate(int exitDate) {
        this.exitDate = exitDate;
    }

    public int getServorDate() {
        return servorDate;
    }

    public void setServorDate(int servorDate) {
        this.servorDate = servorDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (number != piece.number) return false;
        if (entryDate != piece.entryDate) return false;
        if (exitDate != piece.exitDate) return false;
        return servorDate == piece.servorDate;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + entryDate;
        result = 31 * result + exitDate;
        result = 31 * result + servorDate;
        return result;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "number=" + number +
                ", entryDate=" + entryDate +
                ", exitDate=" + exitDate +
                ", servorDate=" + servorDate +
                '}';
    }
}
