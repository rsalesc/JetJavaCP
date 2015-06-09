package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

public class TaskD {
    boolean visited[][];
    long dp[][];
    int a[];
    int n;

    boolean dfs(int i, int j){
        if(visited[i][j]) return true;
        if(dp[i][j] != -2) return dp[i][j] == -1;

        visited[i][j] = true;
        int delta = a[i];
        if(j == 1) delta = -delta;

        boolean cycle = false;
        if(i+delta <= 0 || i+delta > n){
            dp[i][j] = a[i];
        }else{
            cycle = dfs(i+delta, j^1);
            if(cycle)
                dp[i][j] = -1;
            else
                dp[i][j] = dp[i+delta][j^1] + a[i];
        }

        visited[i][j] = false;
        return cycle;
    }

    public void solve(int testNumber, FastInput in, FastOutput out) {
        n = in.readInt();
        a = new int[n+1];

        for(int i = 2; i <= n; i++){
            a[i] = in.readInt();
        }

        visited = new boolean[n+1][2];
        dp = new long[n+1][2];
        for(int i = 0; i <= n; i++) dp[i][0] = dp[i][1] = -2;

        visited[1][0] = true;
        visited[1][1] = true;

        for(int i = 2; i <= n; i++){
            if(dp[i][0] == -2) dfs(i, 0);
            if(dp[i][1] == -2) dfs(i, 1);
        }

        for(int i = 1; i <= n-1; i++){
            if(dp[i+1][1] == -1)
                out.printLine(-1);
            else
                out.printLine(dp[i+1][1] + i);
        }
    }
}
