package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

public class RivalryOfCode {
    public void solve(int testNumber, FastInput in, FastOutput out) {
        String distort = in.readString();
        String actual = in.readString();

        int n = distort.length();
        int m = actual.length();

        int dp[][] = new int[n+1][m+1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(distort.charAt(i-1) == actual.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = dp[i-1][j-1]+1;
                    if(j > i) dp[i][j] = Math.min(dp[i][j], dp[i][j-1]);
            }
        }

        out.printLine(dp[n][m]);
    }
}
