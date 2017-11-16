package com.isima.zz2.simu.tp1.queue;

import com.isima.zz2.simu.tp1.queue.exception.QueueEmptyException;
import com.isima.zz2.simu.tp1.queue.exception.QueueFullException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathieu on 16/11/2017.
 */
public class Queue<T> {
    private int start;

    private int end;

    private int size;

    private int capacity;

    private List<T> objects;

    public Queue() {
        this(10);
    }

    public Queue(int queueSize) {
        objects = new ArrayList<>(queueSize);
    }

    public boolean isFull() {
        return ((end % capacity) + 1 == start);
    }

    public boolean isEmpty() {
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

    @Override
    public String toString() {
        return "Queue{" +
                "objects=" + objects +
                '}';
    }
}
