package net.rsalesc.lib.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by rsalesc on 15/04/15.
 */
public class SCC {

    protected Graph graph;
    protected boolean visited[];
    protected LinkedList<Integer> sccStack;
    protected ArrayList<Integer> comp;
    protected ArrayList< ArrayList<Integer> > comps;

    public SCC(Graph graph){
        this.graph = graph;
    }

    public ArrayList< ArrayList<Integer> > kosaraju(){
        sccStack = new LinkedList<Integer>();
        visited = new boolean[graph.vertexCount()];
        for(int i = 0; i < graph.vertexCount(); i++){
            if(!visited[i]) firstDfs(i);
        }

        comp = new ArrayList<Integer>();
        comps = new ArrayList< ArrayList<Integer> >(); // vetor com as componentes conexas
        Arrays.fill(visited, false);

        while(!sccStack.isEmpty()){
            int u = sccStack.poll();
            if(!visited[u]) {
                secondDfs(u);
                comps.add(comp);
                comp.clear();
            }
        }

        return comps;
    }

    protected void firstDfs(int u){
        visited[u] = true;

        for(Edge e = graph.firstEdge(u); e != null; e = e.next()){
            int v = e.destination();
            if(!visited[v])
                firstDfs(v);
        }

        sccStack.push(u);
    }

    protected void secondDfs(int u) {
        visited[u] = true;
        comp.add(u);

        for(Edge e = graph.firstTransposedEdge(u); e != null; e = e.next()){
            int v = e.destination();
            if(!visited[v])
                secondDfs(v);
        }
    }
}
