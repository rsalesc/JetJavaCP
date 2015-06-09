package net.rsalesc.util;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by rsalesc on 31/01/15.
 */
public class MaxPriorityQueue<T extends Comparable> extends PriorityQueue<T> {
    public MaxPriorityQueue(){
        super(11, new Comparator<T>(){

            @Override
            public int compare(T t, T t1) {
                int ans = t.compareTo(t1);
                if(ans > 0) return -1;
                if(ans < 0) return 1;
                return 0;
            }
        });
    }
}
