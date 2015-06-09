package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

import java.util.ArrayList;
import java.util.Collections;

public class TaskD {
    static int BASE = 100;
    static int SIZE = (BASE+1)*BASE;
    int n;
    ArrayList<Integer> adj[];
    ArrayList<Integer> path;

    int numify(char x){
        if(Character.isDigit(x))
            return x - '0' + 52;
        return Character.isUpperCase(x) ? x - 'A' : x - 'a' + 26;
    }
    int numify2(char a, char b){
        return numify(a)*BASE + numify(b);
    }
    void dfs(int i){
        while(adj[i] != null && !adj[i].isEmpty()){
            int j = adj[i].remove(adj[i].size()-1);
            dfs(j);
        }
        path.add(i);
    }
    public void solve(int testNumber, FastInput in, FastOutput out) {
        n = in.readInt();
        adj = new ArrayList[SIZE];
        int[] din = new int[SIZE];
        int[] dout = new int[SIZE];
        char[][] mem = new char[SIZE][];
        for(int i = 0; i < n; i++){
            String s = in.read();
            char a = s.charAt(0);
            char b = s.charAt(1);
            char c = s.charAt(2);
            int u = numify2(a, b);
            int v = numify2(b, c);
            mem[u] = new char[]{a,b};
            mem[v] = new char[]{b,c};
            if(adj[u] == null) adj[u] = new ArrayList<Integer>();
            adj[u].add(v);
            dout[u]++;
            din[v]++;
        }

        int nin = 0, nout =0, start = -1;
        for(int i = 0; i < SIZE; i++){
            if(din[i] == dout[i]+1)
                nin++;
            else if(dout[i] == din[i]+1) {
                nout++;
                start = i;
            }
            else if(dout[i] != din[i])
                nin = 2;
            else if(dout[i] > 0 && start == -1)
                start = i;
        }

        if(nin > 1 || nout > 1){
            out.printLine("NO");
            return;
        }

        path = new ArrayList<Integer>();
        dfs(start);

        if(path.size() != n+1)
            out.printLine("NO");
        else{
            Collections.reverse(path);
            out.printLine("YES");
            out.print(mem[path.get(0)][0]);
            out.print(mem[path.get(0)][1]);
            int path_size = path.size();
            for(int i = 1; i < path_size; i++)
                out.print(mem[path.get(i)][1]);
            out.printLine();
        }
    }
}
