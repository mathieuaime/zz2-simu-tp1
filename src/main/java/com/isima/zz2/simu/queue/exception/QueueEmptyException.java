package com.isima.zz2.simu.queue.exception;

/**
 * Exception when a queue is empty.
 * <p>
 * Created by Mathieu on 16/11/2017.
 */
public class QueueEmptyException extends RuntimeException {
    public QueueEmptyException(String e) {
        super(e);
    }
}
