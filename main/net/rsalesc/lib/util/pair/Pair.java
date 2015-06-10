package net.rsalesc.lib.util.pair;

/**
 * Created by rsalesc on 30/01/15.
 */
public class Pair<T1 extends Comparable, T2 extends Comparable> implements Comparable{
    public T1 first;
    public T2 second;
    public Pair(T1 first, T2 second){
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Object o) {
        Pair b = (Pair)o;
        int ans = first.compareTo(b.first);
        if(ans == 0)
            return second.compareTo(b.second);
        return ans;
    }
}
