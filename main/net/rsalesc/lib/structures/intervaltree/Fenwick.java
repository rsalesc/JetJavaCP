package net.rsalesc.lib.structures.intervaltree;

/**
 * Created by Roberto on 10/06/2015.
 */
public class Fenwick {
    private final long array[];

    public Fenwick(int size){
        array = new long[size+1];
    }

    public long get(int idx){
        long sum = 0;
        while(idx > 0){
            sum += array[idx];
            idx -= (idx & -idx);
        }
        return sum;
    }

    public long get(int l, int r){
        return get(r) - get(l-1);
    }

    public void add(int idx, long value){
        while(idx < array.length) {
            array[idx] += value;
            idx += (idx & -idx);
        }
    }

    public void scale(long value){
        for(int i = 1; i < array.length; i++)
            array[i] *= value;
    }

    // TODO: implement fenwick 2d
}
