package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;
import net.rsalesc.util.Pair;
import net.rsalesc.util.PairII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class TaskE {
    static int INF = 1000000;
    class Edge{
        public int x, y;
        boolean z;
    }
    class PQPair extends Pair<PairII, Integer>{
        public PQPair(PairII first, Integer second){
            super(first, second);
        }
    }

    ArrayList<Edge>[] adj;
    public void solve(int testNumber, FastInput in, FastOutput out) {
        int n = in.readInt();
        int m = in.readInt();
        adj = new ArrayList[n+1];
        int malfunc = 0;

        for(int i = 0; i < m; i++){
            Edge e = new Edge();
            e.x = in.readInt();
            e.y = in.readInt();
            e.z = in.readBool();
            if(!e.z) malfunc++;
        }

        int[] dist = new int[n+1];
        int[] broken = new int[n+1];
        Arrays.fill(broken, INF);
        Arrays.fill(dist, INF);
        dist[1] = 0;
        broken[1] = 0;
        PriorityQueue<PQPair> pq = new PriorityQueue<PQPair>();
        pq.add(new PQPair(new PairII(0, 0), 1));
        while(!pq.isEmpty()){
            PQPair i = pq.poll();
            for(int j = 0; j < adj[i.second].size(); j++){
                int c = adj[i.second].get(j).z ? 0 : 1;
                if(i.first.first+1 < dist[j] || (i.first.first+1 == dist[j] && i.first.second+c < broken[j])){
                    dist[j] = dist[i.second]+1;
                    broken[j] = broken[j];
                    pq.add(new PQPair(new PairII(dist[j], broken[j]), j));
                }
            }
        }

        int ans = m - (malfunc-broken[n]) -  (dist[n]-broken[n]);
        out.printLine(ans);
    }
}
