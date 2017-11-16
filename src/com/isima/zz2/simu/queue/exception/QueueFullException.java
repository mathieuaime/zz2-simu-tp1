package com.isima.zz2.simu.queue.exception;

/**
 * Created by Mathieu on 16/11/2017.
 */
public class QueueFullException extends RuntimeException {
    public QueueFullException(String e) {
        super(e);
    }
}
