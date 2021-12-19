package com.imamJmartMR;

/**
 * Predicate the argument
 * @param <T> generics argument to be predicated
 */

public interface Predicate<T> {

    public abstract boolean predicate(T arg);
}
