package com.isima.zz2.simu.tp1.machine;

import com.isima.zz2.simu.tp1.model.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;

/**
 * Created by Mathieu on 16/11/2017.
 */
public class Output {

    private List<Piece> accepts;
    private List<Piece> rejects;

    public Output() {
        this.accepts = new ArrayList<>();
        this.rejects = new ArrayList<>();
    }

    public void accept(Piece p) {
        accepts.add(p);
    }

    public void reject(Piece p) {
        rejects.add(p);
    }

    public double avgServorTime() {
        return avgTime(Piece::servorTime);
    }

    public double avgQueueTime() {
        return avgTime(Piece::queueTime);
    }

    public double avgSystemTime() {
        return avgTime(Piece::systemTime);
    }

    private double avgTime(ToDoubleFunction<Piece> function) {
        return accepts.stream().mapToDouble(function).average().orElse(0);
    }

    public int getNumberAccepted() {
        return accepts.size();
    }

    public int getNumberReject() {
        return rejects.size();
    }
}
