package net.rsalesc.lib.graphs;

/**
 * Created by Roberto on 10/06/2015.
 */
public abstract class Edge {
    public abstract int source();
    public abstract int destination();
    public int next(){
        return destination();
    }
    public abstract long weight();
    public abstract void remove();
    public abstract void restore();
    public abstract boolean isRemoved();
    public abstract Edge transposed();
    public boolean isTransposed(){
        return false;
    }
}
