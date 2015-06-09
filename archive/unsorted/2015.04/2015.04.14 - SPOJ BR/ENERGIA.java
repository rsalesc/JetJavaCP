package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

public class ENERGIA {
    int n, m;
    int adj[][];
    boolean visited[];

    int dfs(int v){
        visited[v] = true;
        int res = 1;

        for(int i = 1; i <= n; i++){
            if(!visited[i] && adj[v][i] == 1)
                res += dfs(i);
        }

        return res;
    }

    public void solve(int testNumber, FastInput in, FastOutput out) {
        n = in.readInt();
        m = in.readInt();
        if(n == 0) throw new UnknownError();

        adj = new int[n+1][n+1];
        for(int i = 0; i < m; i++){
            int a = in.readInt();
            int b = in.readInt();
            adj[a][b] = adj[b][a] = 1;
        }

        visited = new boolean[n+1];

        int cnt = dfs(1);

        out.printLine("Teste " + testNumber);
        if(cnt == n)
            out.printLine("normal");
        else
            out.printLine("falha");

        out.printLine();
    }
}
