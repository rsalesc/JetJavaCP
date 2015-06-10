package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;
import net.rsalesc.lib.util.pair.Pair;
import net.rsalesc.lib.util.pair.IntPair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class TaskE {
    static int INF = 1000000;
    class Edge{
        public int x, y, id;
        boolean z;
        public Edge(int id, int x, int y, boolean z){
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    class PQPair extends Pair<IntPair, Integer> {
        public PQPair(IntPair first, Integer second){
            super(first, second);
        }
    }

    ArrayList<Edge>[] adj;

    public void solve(int testNumber, FastInput in, FastOutput out) {
        int n = in.readInt();
        int m = in.readInt();
        adj = new ArrayList[n+1];
        int malfunc = 0;

        Edge[] edges = new Edge[m*2];
        for(int i = 0; i < 2*m; i+=2){
            int x = in.readInt();
            int y = in.readInt();
            boolean z = in.readBool();
            if(!z) malfunc++;
            if(adj[x] == null) adj[x] = new ArrayList<Edge>();
            Edge a = new Edge(i, x, y, z);
            adj[x].add(a);
            edges[i] = a;
            if(adj[y] == null) adj[y] = new ArrayList<Edge>();
            Edge b= new Edge(i+1, y, x, z);
            adj[y].add(b);
            edges[i+1] = b;
        }

        int[] dist = new int[n+1];
        int[] broken = new int[n+1];
        int[] from = new int[n+1];
        Arrays.fill(broken, INF);
        Arrays.fill(dist, INF);
        Arrays.fill(from, -1);
        dist[1] = 0;
        broken[1] = 0;
        PriorityQueue<PQPair> pq = new PriorityQueue<PQPair>();
        pq.add(new PQPair(new IntPair(0, 0), 1));
        while(!pq.isEmpty()){
            PQPair i = pq.poll();
            for(int j = 0; j < adj[i.second].size(); j++){
                Edge e = adj[i.second].get(j);
                int c = e.z ? 0 : 1;
                if(i.first.first+1 < dist[e.y] || (i.first.first+1 == dist[e.y] && i.first.second+c < broken[e.y])){
                    dist[e.y] = i.first.first+1;
                    broken[e.y] = i.first.second+c;
                    from[e.y] = e.id;
                    if(adj[e.y] != null) pq.add(new PQPair(new IntPair(dist[e.y], broken[e.y]), e.y));
                }
            }
        }

        boolean[] inPath = new boolean[m];
        // backtrack
        int act = n;
        while(from[act] != -1){
            inPath[from[act]/2] = true;
            act = edges[from[act]].x;
        }

        int ans = m - (malfunc-broken[n]) -  (dist[n]-broken[n]);
        out.printLine(ans);

        for(int i = 0; i < m; i++){
            int ii = i*2;
            if(inPath[i] && !edges[ii].z)
                out.printLine(edges[ii].x, edges[ii].y, 1);
            else if(!inPath[i] && edges[ii].z)
                out.printLine(edges[ii].x, edges[ii].y, 0);
        }
    }
}
