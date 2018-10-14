package com.isima.zz2.simu.queue.exception;

/**
 * Exception when a queue is full.
 * <p>
 * Created by Mathieu on 16/11/2017.
 */
public class QueueFullException extends RuntimeException {
    public QueueFullException(String e) {
        super(e);
    }
}
