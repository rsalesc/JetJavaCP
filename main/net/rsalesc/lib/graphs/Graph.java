package net.rsalesc.lib.graphs;

import net.rsalesc.lib.util.ArrayUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Roberto on 10/06/2015.
 */
public class Graph {
    protected int vertexCount;
    protected int edgeCount;

    protected int firstOut[];
    protected int firstIn[];
    protected int nextIn[];
    protected int nextOut[];
    protected int inDegree[];
    protected int outDegree[];

    protected Edge edges[]; // just a interface with iterable class to graph actual values
    protected int from[];
    protected int to[];
    protected long weight[];
    protected boolean removed[];
    protected int reverse[];

    public Graph(int vertexCount){
        this(vertexCount, vertexCount);
    }

    public Graph(int vertexCount, int edgeCapacity){
        this.vertexCount = vertexCount;
        firstOut = new int[vertexCount];
        Arrays.fill(firstOut, -1);

        from = new int[edgeCapacity];
        to = new int[edgeCapacity];
        nextOut = new int[edgeCapacity];
    }

    public static Graph createGraph(int vertexCount, int from[], int to[]){
        Graph graph = new Graph(vertexCount, from.length);
        for(int i = 0; i < from.length; i++){
            graph.addEdge(from[i], to[i]);
        }
        return graph;
    }

    public static Graph createWeightedGraph(int vertexCount, int from[], int to[], long weight[]){
        Graph graph = new Graph(vertexCount, from.length);
        for(int i = 0; i < from.length; i++){
            graph.addEdge(from[i], to[i], weight[i]);
        }
        return graph;
    }

    public static Graph createUndirectedGraph(int vertexCount, int from[], int to[]){
        Graph graph = new Graph(vertexCount, from.length);
        for(int i = 0; i < from.length; i++){
            graph.addUndirectedEdge(from[i], to[i]);
        }
        return graph;
    }

    public static Graph createUndirectedWeightedGraph(int vertexCount, int from[], int to[], int weight[]){
        Graph graph = new Graph(vertexCount, from.length);
        for(int i = 0; i < from.length; i++){
            graph.addUndirectedEdge(from[i], to[i], weight[i]);
        }
        return graph;
    }

    public static Graph createTree(int parent[]){
        Graph graph = new Graph(parent.length, parent.length);
        for(int i = 0; i < parent.length; i++){
            graph.addUndirectedEdge(parent[i], i);
        }
        return graph;
    }

    public static Graph createRootedTree(int parent[]){
        Graph graph = new Graph(parent.length, parent.length);
        for(int i = 0; i < parent.length; i++){
            graph.addEdge(parent[i], i);
        }
        return graph;
    }

    public static Graph createWeightedTree(int parent[], int weight[]){
        Graph graph = new Graph(parent.length, parent.length);
        for(int i = 0; i < parent.length; i++){
            graph.addUndirectedEdge(parent[i], i, weight[i]);
        }
        return graph;
    }

    public static Graph createWeightedRootedTree(int parent[], int weight[]){
        Graph graph = new Graph(parent.length, parent.length);
        for(int i = 0; i < parent.length; i++){
            graph.addEdge(parent[i], i, weight[i]);
        }
        return graph;
    }

    public int addEdge(int from, int to){
        return addEdge(from, to, 0);
    }

    public int addEdge(int from, int to, long weight){
        return addEdge(from, to, weight, -1);
    }

    public int addEdge(int from, int to, long weight, int reverse){
        ensureEdgeCapacity(edgeCount+1);

        if(firstOut[from] != -1)
            nextOut[edgeCount] = firstOut[from];
        else
            nextOut[edgeCount] = -1;
        firstOut[from] = edgeCount;

        if(firstIn!= null){
            if(firstIn[to] != -1)
                nextIn[edgeCount] = firstIn[to];
            else
                nextIn[edgeCount] = -1;
            nextIn[to] = edgeCount;
        }

        this.from[edgeCount] = from;
        this.to[edgeCount] = to;

        if(weight != 0){
            initWeights();
            this.weight[edgeCount] = weight;
        }

        if(inDegree != null){
            inDegree[to]++;
            outDegree[from]++;
        }

        if(edges != null)
            edges[edgeCount] = new GraphEdge(edgeCount);

        if(reverse != -1){
            initReverse();
            this.reverse[edgeCount] = reverse;
        }

        return edgeCount++;
    }

    public void addUndirectedEdge(int from, int to){
        addUndirectedEdge(from, to, 0);
    }

    public void addUndirectedEdge(int from, int to, int weight){
        addEdge(from, to, 0);
        addEdge(to, from, 0);
    }

    public final int vertexCount(){
        return vertexCount;
    }

    public final int edgeCount(){
        return edgeCount;
    }

    public int firstOutbound(int vert){
        return firstOut[vert];
    }

    public int nextOutbound(int id){
        return nextOut[id];
    }

    public int firstInbound(int vert){
        initInbound();
        return firstIn[vert];
    }

    public int nextInbound(int id){
        return nextIn[id];
    }

    public Edge firstEdge(int vert){
        if(firstOut[vert] == -1) return null;
        initEdges();
        return edges[firstOut[vert]];
    }

    public Edge nextEdge(Edge edge){
        if(nextOut[edge.id()] == -1) return null;
        return edges[nextOut[edge.id()]];
    }

    public Edge firstTransposedEdge(int vert){
        initInbound();
        if(firstIn[vert] == -1) return null;
        initEdges();
        return new TransposedGraphEdge(firstIn[vert]);
    }

    public Edge nextTransposedGraphEdge(Edge edge){
        if(nextIn[edge.id()] == -1) return null;
        return new TransposedGraphEdge(nextIn[edge.id()]);
    }

    public Edge edge(int id){
        initEdges();
        return edges[id];
    }

    public Edge edge(int u, int v){
        initEdges();
        int id = firstOut[u];
        while(id != -1){
            if(to[id] == v) return edges[id];
            id = nextOut[id];
        }
        return null;
    }

    public class GraphEdge extends Edge{
        protected final int id;

        public GraphEdge(int id){
            this.id = id;
        }

        public Edge next(){
            if(nextOut[id] == -1) return null;
            return edges[id];
        }

        public int id(){
            return id;
        }

        public int source(){
            return from[id];
        }

        public int destination(){
            return to[id];
        }

        public long weight(){
            if(weight == null)
                return 0;
            return weight[id];
        }

        public void remove(){
            initRemoved();
            removed[id] = true;
        }

        public void restore(){
            if(removed != null)
                removed[id] = false;
        }

        public boolean isRemoved(){
            if(removed == null) return false;
            else return removed[id];
        }

        public Edge transposed(){
            return new TransposedGraphEdge(id);
        }
    }

    public class TransposedGraphEdge extends GraphEdge{

        public TransposedGraphEdge(int id){
            super(id);
        }

        public Edge next(){
            if(nextIn[id] == -1) return null;
            return new TransposedGraphEdge(nextIn[id]);
        }

        public int source(){
            return to[id];
        }

        public int destination(){
            return from[id];
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        public void restore(){
            throw new UnsupportedOperationException();
        }

        public boolean isRemoved(){
            throw new UnsupportedOperationException();
        }

        public boolean isTransposed(){
            return true;
        }
    }

    protected void initRemoved(){
        if(removed == null){
            removed = new boolean[from.length];
        }
    }

    protected void initEdges(){
        if(edges == null){
            edges = new Edge[from.length];
            for(int i = 0; i < edgeCount; i++)
                edges[i] = new GraphEdge(i);
        }
    }

    protected void initWeights(){
        if(weight == null){
            weight = new long[from.length];
        }
    }

    protected void initReverse(){
        if(reverse == null){
            reverse = new int[from.length];
        }
    }

    protected void initInbound(){
        if(firstIn == null){
            firstIn = new int[firstOut.length];
            Arrays.fill(firstIn, -1);
            nextIn = new int[from.length];

            for(int i = 0; i < edgeCount; i++){
                nextIn[i] = firstIn[to[i]];
                firstIn[to[i]] = i;
            }
        }
    }

    protected void initDegree(){
        if(inDegree == null){
            inDegree = new int[vertexCount];
            outDegree = new int[vertexCount];
            for(int i = 0; i < edgeCount; i++){
                inDegree[to[i]]++;
                outDegree[from[i]]++;
            }
        }
    }

    public Iterable<Edge> outbound(final int vert){
        initEdges();
        return new Iterable<Edge>(){
            public Iterator<Edge> iterator(){
                return new EdgeIterator(vert, firstOut, nextOut);
            }
        };
    }

    public Iterable<Edge> inbound(final int vert){
        initEdges();
        initInbound();
        return new Iterable<Edge>(){
            public Iterator<Edge> iterator(){
                return new EdgeIterator(vert, firstIn, nextIn);
            }
        };
    }

    public Iterable<Edge> transposedOutbound(final int vert){
        initEdges();
        initInbound();
        return new Iterable<Edge>(){
            public Iterator<Edge> iterator(){
                return new TransposedEdgeIterator(vert, firstIn, nextIn);
            }
        };
    }

    public Graph transposed(){
        if(weight != null)
            return Graph.createWeightedGraph(vertexCount, to, from, weight);
        return Graph.createGraph(vertexCount, to, from);
    }

    public int inDegree(int vert){
        initDegree();
        return inDegree[vert];
    }

    public int outDegree(int vert){
        initDegree();
        return outDegree[vert];
    }

    protected void ensureEdgeCapacity(int requestedCapacity){
        if(requestedCapacity > edges.length){
            int newCapacity = Math.max(2*edges.length, requestedCapacity);
            if(edges != null)
                ArrayUtils.resize(edges, newCapacity);
            ArrayUtils.resize(from, newCapacity);
            ArrayUtils.resize(to, newCapacity);
            ArrayUtils.resize(nextOut ,newCapacity);
            if(nextIn != null)
                ArrayUtils.resize(nextIn, newCapacity);
            if(weight != null)
                ArrayUtils.resize(weight, newCapacity);
            if(removed != null)
                ArrayUtils.resize(removed, newCapacity);
            if(reverse != null)
                ArrayUtils.resize(reverse, newCapacity);
        }
    }

    protected boolean isSparse(){
        return vertexCount == 0 || edgeCount*30 <= vertexCount*vertexCount;
    }

    public class EdgeIterator implements Iterator<Edge>{
        protected int id;
        protected final int next[];

        public EdgeIterator(int vert, int first[], int next[]){
            this.next = next;
            id = firstValidEdge(first[vert]);
        }

        protected int firstValidEdge(int id){
            if(removed == null) return id;
            while(id != -1 && removed[id])
                id = next[id];
            return id;
        }

        public boolean hasNext(){
            return id != -1;
        }

        public Edge next(){
            if(id == -1) throw new NoSuchElementException();
            id = firstValidEdge(next[id]);
            return edges[id];
        }
    }

    public class TransposedEdgeIterator extends EdgeIterator{

        public TransposedEdgeIterator(int vert, int first[], int next[]){
            super(vert, first, next);
        }

        public Edge next(){
            if(id == -1) throw new NoSuchElementException();
            id = firstValidEdge(next[id]);
            return new TransposedGraphEdge(id);
        }
    }
}
