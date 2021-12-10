package com.imamJmartMR;

import java.lang.*;
import java.util.*;

/**
 * This base class used for all complex logical usage especially involving a list of data.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

public class Algorithm {

    private Algorithm(){}

    // ###################### COLLECT ######################
    public static <T> List<T> collect(T[] array, T value){
        List<T> temp = new ArrayList<T>();
        for (T get : array) {
            if (get.equals(value))
                temp.add(get);
        }
        return temp;
    }
    public static <T> List<T> collect(Iterable<T> iterable, T value){
        List<T> temp = new ArrayList<T>();
        for (T get : iterable) {
            if (get.equals(value))
                temp.add(get);
        }
        return temp;
    }
    public static <T> List<T> collect(Iterator<T> iterator, T value){
        List<T> temp = new ArrayList<T>();
        while (iterator.hasNext()) {
            T get = iterator.next();
            if (get.equals(value))
                temp.add(get);
        }
        return temp;
    }
    public static <T> List<T> collect(T[] array, Predicate<T> pred){
        List<T> temp = new ArrayList<T>();
        for (T get : array) {
            if (pred.predicate(get) == true)
                temp.add(get);
        }
        return temp;
    }
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred){
        List<T> temp = new ArrayList<T>();
        for (T get : iterable) {
            if (pred.predicate(get) == true)
                temp.add(get);
        }
        return temp;
    }
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred){
        List<T> temp = new ArrayList<T>();
        while (iterator.hasNext()) {
            T get = iterator.next();
            if (pred.predicate(get) == true)
                temp.add(get);
        }
        return temp;
    }

    // ###################### COUNT ######################
    public static <T> int count(T[] array, T value) {
        int count = 0;
        for (T get : array) {
            if (get.equals(value))
                count++;
        }
        return count;
    }
    public static <T> int count(Iterable<T> iterable, T value) {
        int count = 0;
        for (T get : iterable) {
            if (get.equals(value))
                count++;
        }
        return count;
    }
    public static <T> int count(Iterator<T> iterator, T value) {
        int count = 0;
        while (iterator.hasNext()) {
            T get = iterator.next();
            if (get.equals(value))
                count++;
        }
        return count;
    }
    public static <T> int count(T[] array, Predicate<T> pred) {
        int count = 0;
        for (T get : array) {
            if (pred.predicate(get) == true)
                count++;
        }
        return count;
    }
    public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
        int count = 0;
        for (T get : iterable) {
            if (pred.predicate(get) == true)
                count++;
        }
        return count;
    }
    public static <T> int count(Iterator<T> iterator, Predicate<T> pred) {
        int count = 0;
        while (iterator.hasNext()) {
            T get = iterator.next();
            if (pred.predicate(get) == true)
                count++;
        }
        return count;
    }

    // ###################### EXISTS ######################
    public static <T> boolean exists(T[] array, T value)  {
        for(T get : array){
            if(get.equals(value))
                return true;
        }
        return false;
    }
    public static <T> boolean exists(Iterable<T> iterable, T value) {
        for(T get : iterable){
            if(get.equals(value))
                return true;
        }
        return false;
    }
    public static <T> boolean exists(Iterator<T> iterator, T value) {
        while(iterator.hasNext()){
            T get = iterator.next();
            if(get.equals(value))
                return true;
        }
        return false;
    }
    public static <T> boolean exists(T[] array, Predicate<T> pred) {
        for(T get : array){
            if(pred.predicate(get) == true)
                return true;
        }
        return false;
    }
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
        for(T get : iterable){
            if(pred.predicate(get) == true)
                return true;
        }
        return false;
    }
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
        while(iterator.hasNext()){
            T get = iterator.next();
            if(pred.predicate(get) == true)
                return true;
        }
        return false;
    }

    // ###################### FIND ######################
    public static <T> T find(T[] array, T value) {
        for(T get : array){
            if(get.equals(value))
                return get;
        }
        return null;
    }
    public static <T> T find(Iterable<T> iterable, T value) {
        for(T get : iterable){
            if(get.equals(value))
                return get;
        }
        return null;
    }
    public static <T> T find(Iterator<T> iterator, T value) {
        while(iterator.hasNext()){
            T get = iterator.next();
            if(get.equals(value))
                return get;
        }
        return null;
    }
    public static <T> T find(T[] array, Predicate<T> pred) {
        for(T get : array){
            if(pred.predicate(get) == true)
                return get;
        }
        return null;
    }
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
        for(T get : iterable){
            if(pred.predicate(get) == true)
                return get;
        }
        return null;
    }
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
        while(iterator.hasNext()){
            T get = iterator.next();
            if(pred.predicate(get) == true)
                return get;
        }
        return null;
    }

    // ###################### MAX ######################
    public static <T extends Comparable> T max(T first, T second) {
        if((first.compareTo(second)) > 0)
            return first;
        else
            return second;
    }
    public static <T extends Comparable> T max(T[] array){
        T max = array[0];
        for (int i = 1; i < array.length; i++){
            if (array[i].compareTo(max) > 0)
                max = array[i];
        }
        return max;
    }
    public static <T extends Comparable> T max(Iterable<T> iterable){
        T max = iterable.iterator().next();
        for (T get : iterable) {
            if (get.compareTo(max) > 0)
                max = get;
        }
        return max;
    }
    public static <T extends Comparable> T max(Iterator<T> iterator){
        T max = iterator.next();
        while (iterator.hasNext()) {
            T get = iterator.next();
            if (get.compareTo(max) > 0)
                max = get;
        }
        return max;
    }
    public static <T> T max(T first, T second, Comparator<? super T> comparator){
        if (comparator.compare(first, second) > 0)
            return first;
        else
            return second;
    }
    public static <T> T max(T[] array, Comparator<? super T> comparator){
        T max = array[0];
        for (int i = 1; i < array.length; i++){
            if (comparator.compare(array[i], max) > 0)
                max = array[i];
        }
        return max;
    }
    public static <T> T max(Iterable<T> iterable, Comparator<? super T> comparator){
        T max = iterable.iterator().next();
        for (T get : iterable) {
            if (comparator.compare(get, max) > 0)
                max = get;
        }
        return max;
    }
    public static <T> T max(Iterator<T> iterator, Comparator<? super T> comparator){
        T max = iterator.next();
        while (iterator.hasNext()) {
            T get = iterator.next();
            if (comparator.compare(get, max) > 0)
                max = get;
        }
        return max;
    }


    // ###################### MIN ######################
    public static <T extends Comparable> T min(T first, T second) {
        if((first.compareTo(second)) < 0)
            return first;
        else
            return second;
    }
    public static <T extends Comparable> T min(T[] array){
        T min = array[0];
        for (int i = 1; i < array.length; i++){
            if (array[i].compareTo(min) < 0)
                min = array[i];
        }
        return min;
    }
    public static <T extends Comparable> T min(Iterable<T> iterable){
        T min = iterable.iterator().next();
        for (T get : iterable) {
            if (get.compareTo(min) < 0)
                min = get;
        }
        return min;
    }
    public static <T extends Comparable> T min(Iterator<T> iterator){
        T min = iterator.next();
        while (iterator.hasNext()) {
            T get = iterator.next();
            if (get.compareTo(min) < 0)
                min = get;
        }
        return min;
    }
    public static <T> T min(T first, T second, Comparator<? super T> comparator){
        if(comparator.compare(first, second) < 0)
            return first;
        else
            return second;
    }
    public static <T> T min(T[] array, Comparator<? super T> comparator){
        T min = array[0];
        for (int i = 1; i < array.length; i++){
            if (comparator.compare(array[i], min) < 0)
                min = array[i];
        }
        return min;
    }
    public static <T> T min(Iterable<T> iterable, Comparator<? super T> comparator){
        T min = iterable.iterator().next();
        for (T get : iterable) {
            if (comparator.compare(get, min) < 0)
                min = get;
        }
        return min;
    }
    public static <T> T min(Iterator<T> iterator, Comparator<? super T> comparator){
        T min = iterator.next();
        while (iterator.hasNext()) {
            T get = iterator.next();
            if (comparator.compare(get, min) < 0)
                min = get;
        }
        return min;
    }

    // ###################### PAGINATE ######################
    public static <T extends Comparable> List<T> paginate (T[] array, int page, int pageSize, Predicate<T> pred) {

        List<T> filtered = new ArrayList<T>();

        if (page <= 0 || pageSize <= 0)
            return filtered;

        for (T get : array) {
            if (pred.predicate(get) == true)
                filtered.add(get);
        }
        int fromIndex = (page - 1) * pageSize;
        return filtered.subList(fromIndex, Math.min(fromIndex + pageSize, filtered.size()));
    }
    public static <T extends Comparable> List<T> paginate (Iterable<T> iterable, int page, int pageSize, Predicate<T> pred) {

        List<T> filtered = new ArrayList<T>();

        if (page <= 0 || pageSize <= 0)
            return filtered;

        for (T get : iterable) {
            if (pred.predicate(get) == true)
                filtered.add(get);
        }
        int fromIndex = (page - 1) * pageSize;
        return filtered.subList(fromIndex, Math.min(fromIndex + pageSize, filtered.size()));
    }
    public static <T extends Comparable> List<T> paginate (Iterator<T> iterator, int page, int pageSize, Predicate<T> pred) {

        List<T> filtered = new ArrayList<T>();

        if (page <= 0 || pageSize <= 0)
            return filtered;

        while (iterator.hasNext()) {
            T get = iterator.next();
            if (pred.predicate(get) == true)
                filtered.add(get);
        }
        int fromIndex = (page - 1) * pageSize;
        return filtered.subList(fromIndex, Math.min(fromIndex + pageSize, filtered.size()));
    }
}