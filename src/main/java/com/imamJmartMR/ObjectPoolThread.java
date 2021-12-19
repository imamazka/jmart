package com.imamJmartMR;

import java.util.Vector;
import java.util.function.Function;

/**
 * A class that implements multithreading to handle, and process user large data.
 * @param <T>
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

public class ObjectPoolThread<T> extends Thread {

    private boolean exitSignal;
    private Vector<T> objectPool = new Vector<>();
    private Function<T,Boolean> routine;

    /**
     * Create new thread
     * @param name thread name
     * @param routine thread routine
     */
    public ObjectPoolThread (String name, Function<T,Boolean> routine) {
        super(name);
        this.routine = routine;
    }

    /**
     * Create new thread
     * @param routine thread routine
     */
    public ObjectPoolThread (Function<T,Boolean> routine) {
        super();
        this.routine = routine;
    }

    /**
     * Add new object to thread
     * @param object object to be passed
     */
    public synchronized void add (T object) {
        objectPool.add(object);
    }

    /**
     * Exit from running thread
     */
    public synchronized void exit () {
        Thread.currentThread().interrupt();
    }

    /**
     * Run the created thread
     */
    @Override
    public void run () {
        try {
            for (int i = 0; i < size(); i++) {
                if (!routine.apply(objectPool.get(i)))
                    add(objectPool.get(i));
            }
            exitSignal = true;
            if (objectPool.isEmpty())
                synchronized (this) {Thread.currentThread().wait();}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (exitSignal) {
            exit();
        }
    }

    /**
     * Get size of the thread pool
     * @return size of the thread pool
     */
    public int size () {
        return objectPool.size();
    }
}
