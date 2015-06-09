package net.rsalesc.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rsalesc on 15/04/15.
 */
public class SCC {

    protected int begin, end;
    protected List<GraphEdge> adj[], trans[];
    protected boolean visited[];
    protected LinkedList<Integer> sccStack;
    protected ArrayList<Integer> comp;
    protected ArrayList< ArrayList<Integer> > comps;

    public SCC(int begin, int end, List<GraphEdge> adj[]){
        this.begin = begin;
        this.end = end;
        this.adj = adj;
    }

    public ArrayList< ArrayList<Integer> > kosaraju(){
        sccStack = new LinkedList<Integer>();
        visited = new boolean[end+1];
        for(int i = begin; i <= end; i++){
            if(!visited[i]) firstDfs(i);
        }

        comp = new ArrayList<Integer>();
        comps = new ArrayList< ArrayList<Integer> >(); // vetor com as componentes conexas
        Arrays.fill(visited, false);

        // transponho o grafo
        trans = new List[end+1];
        for(int i = begin; i <= end; i++) trans[i] = new LinkedList<GraphEdge>();

        for(int i = begin; i <= end; i++){
            for(GraphEdge e : adj[i]){
                trans[e.next].add(new GraphEdge(e.next, i));
            }
        }

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

        for(GraphEdge e : adj[u]){
            int v = e.next;
            if(!visited[v]){
                firstDfs(v);
            }
        }

        sccStack.push(u);
    }

    protected void secondDfs(int u) {
        visited[u] = true;
        comp.add(u);

        for (GraphEdge e : trans[u]) {
            int v = e.next;
            if (!visited[v]) {
                secondDfs(v);
            }
        }
    }
}
