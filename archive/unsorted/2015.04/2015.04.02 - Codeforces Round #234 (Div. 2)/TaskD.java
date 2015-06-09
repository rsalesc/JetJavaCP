package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskD {
    class Edge{
        int next, cost;
        public Edge(int a, int b) { this.next = a; this.cost = b;}
    }

    ArrayList<Edge> g[];
    boolean visited[];
    FastOutput oute;

    void dfs(int i){
        visited[i] = true;

        for(Edge e : g[i]){
            if(!visited[e.next]) dfs(e.next);
        }
    }

    public void solve(int testNumber, FastInput in, FastOutput out) {
        oute = out;
        int n = in.readInt();
        int m = in.readInt();
        int k = in.readInt();

        int c[] = new int[k+1];
        int from[] = new int[n+1];
        int group[] = new int[k+1];
        for(int i = 1; i <= k; i++) c[i] = in.readInt();

        int lastBegin = 1;
        from[1] = 1;
        group[1] = 1;
        for(int i = 2; i <= k; i++) {
            lastBegin += c[i-1];
            from[lastBegin] = i;
            group[i] = lastBegin;
        }

        for(int i = 2; i <= n; i++) if(from[i] == 0) from[i] = from[i-1];
        // groups filled

        g = new ArrayList[n+1];
        visited = new boolean[n+1];
        for(int i = 1; i <= n; i++) g[i] = new ArrayList<Edge>();

        int adj[][] = new int[k+1][k+1];
        for(int i = 1; i <= k; i++) {
            Arrays.fill(adj[i], 0x1f1f1f1f);
            adj[i][i] = 0;
        }

        // read edges
        for(int i = 0; i < m; i++){
            int a = in.readInt();
            int b = in.readInt();
            int x = in.readInt();

            if(x == 0) {
                g[a].add(new Edge(b, x));
                g[b].add(new Edge(a, x));
            }// same type

            if(from[a] != from[b]){
                adj[from[a]][from[b]] = adj[from[b]][from[a]] = Math.min(adj[from[a]][from[b]], x);
            }
        }

        /*for(int i = 1; i <= n; i++){
            for(Edge e : g[i])
                out.print(e.next, "");
            out.printLine();
        }*/

        // check first condition
        for(int i = 1; i <= k; i++){
            Arrays.fill(visited, false);
            dfs(group[i]);
            for(int j = group[i]; j < group[i]+c[i]; j++){
                if(!visited[j]){
                    out.printLine("No");
                    return;
                }
            }
        }

        // apsp
        for(int p = 1; p <= k; p++)
            for(int i = 1; i <= k; i++)
                for(int j = 1; j <= k; j++)
                    adj[i][j] = Math.min(adj[i][j], adj[i][p] + adj[p][j]);

        out.printLine("Yes");
        for(int i = 1; i <= k; i++) {
            for (int j = 1; j <= k; j++) {
                if (adj[i][j] == 0x1f1f1f1f) out.print("-1 ");
                else out.print(adj[i][j], "");
            }
            out.printLine();
        }

    }
}
