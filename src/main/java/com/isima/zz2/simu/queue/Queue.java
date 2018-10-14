package com.isima.zz2.simu.queue;

import com.isima.zz2.simu.queue.exception.QueueEmptyException;
import com.isima.zz2.simu.queue.exception.QueueFullException;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Template queue.
 * <p>
 * Created by Mathieu on 16/11/2017.
 */
@ToString
public class Queue<T> {
    private int start;

    private int end;

    private int size;

    private int capacity;

    private List<T> objects;

    public Queue(int queueSize) {
        this.capacity = queueSize;
        objects = new ArrayList<>(Collections.nCopies(queueSize + 1, null));
    }

    public boolean isFull() {
        return (end + 1) % capacity == start;
    }

    boolean isEmpty() {
        return start == end;
    }

    public void push(T t) {
        if (!isFull()) {
            objects.set(end, t);
            end = (end % capacity) + 1;
            ++size;
        } else {
            throw new QueueFullException("Queue is full");
        }
    }

    public T pop() {
        if (!isEmpty()) {
            T t = objects.get(start);
            start = (start % capacity) + 1;
            --size;
            return t;
        } else {
            throw new QueueEmptyException("Queue is empty");
        }
    }
}
