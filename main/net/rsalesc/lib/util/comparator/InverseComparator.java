package net.rsalesc.lib.util.comparator;

import java.util.Comparator;

/**
 * Created by Roberto on 10/06/2015.
 */
public class InverseComparator<T extends Comparable<T>> implements Comparator<T> {
    public int compare(T o1, T o2) {
        return o2.compareTo(o1);
    }
}