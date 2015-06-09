package net.rsalesc.graphs;

/**
 * Created by rsalesc on 15/04/15.
 */
public class GraphEdge {
    public int id, current, next, weight, cost;

    public GraphEdge(int id, int start, int end, int weight, int cost){
        this.id = id;
        this.current = start;
        this.next = end;
        this.weight = weight;
        this.cost = cost;
    }

    public GraphEdge(int start, int end){
        this(0, start, end, 1, 0);
    }
}
