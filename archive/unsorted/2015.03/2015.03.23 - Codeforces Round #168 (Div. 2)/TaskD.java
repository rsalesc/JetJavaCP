package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

import java.util.ArrayList;

public class TaskD {
    ArrayList<Integer> adj[];
    int n;
    long v[];
    long inc[];
    long dec[];

    void dfs(int i, int p){
        long in = 0, de = 0;
        for(int k : adj[i]){
            if(k == p) continue;
            dfs(k, i);
            in = Math.max(in, inc[k]);
            de = Math.max(de, dec[k]);
        }

        long val = v[i] + (in-de);
        if(val > 0) de += val;
        else in -= val;

        inc[i] = in;
        dec[i] = de;
    }

    public void solve(int testNumber, FastInput in, FastOutput out) {
        n = in.readInt();

        adj = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<Integer>();

        for(int i = 0; i < n-1; i++){
            int a = in.readInt();
            int b = in.readInt();

            adj[a].add(b);
            adj[b].add(a);
        }

        v = new long[n+1];
        for(int i = 1; i<=n; i++) v[i] = in.readInt();

        inc = new long[n+1];
        dec = new long[n+1];
        dfs(1, -1);

        out.printLine(inc[1] + dec[1]);
    }
}
