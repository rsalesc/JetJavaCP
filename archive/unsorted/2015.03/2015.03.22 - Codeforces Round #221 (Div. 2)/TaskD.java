package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastInput in, FastOutput out) {
        int n = in.readInt();
        int m = in.readInt();

        String mat[] = new String[n];

        for(int i = 0; i < n; i++){
            mat[i] = in.read();
        }

        int dp[][] = new int[m+2][n+2];

        for(int j = m; j > 0; j--){
            for(int i = 1; i <= n; i++) {
                if (mat[i-1].charAt(j-1) == '1')
                    dp[j][i] = 1 + dp[j + 1][i];
            }
        }

        int ans = 0;

        for(int j = 1; j <= m; j++){
            Arrays.sort(dp[j], 1, n+1);
            for(int i = 1; i <= n; i++){
                ans = Math.max(ans, dp[j][i] * (n-i+1));
            }
        }

        out.printLine(ans);
    }
}
