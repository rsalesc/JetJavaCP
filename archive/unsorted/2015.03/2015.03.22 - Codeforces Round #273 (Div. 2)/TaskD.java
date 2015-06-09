package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

import java.util.Arrays;

public class TaskD {
    private static int MOD = (int)1e9+7;

    public void solve(int testNumber, FastInput in, FastOutput out) {
        int r = in.readInt();
        int g = in.readInt();

        int h = (int)((-1.0 + Math.sqrt(8.0*(r+g) + 1.0)) / 2.0);

        int dp[][] = new int[2][r+1];
        dp[0][r] = 1;

        for(int i = 1; i <= h; i++){
            int ii = i&1;
            Arrays.fill(dp[ii], 0);
            int til = Math.min(i * (i + 1) / 2, r);
            for(int j = r - til; j <= r; j++){
                if(j + i <= r) dp[ii][j] = (dp[ii][j] + dp[ii^1][j+i]) % MOD;
                int k = i*(i+1)/2 - (r-j);
                if(k <= g) dp[ii][j] = (dp[ii][j] + dp[ii^1][j]) % MOD;
            }
        }

        int ans = 0;
        for(int j = 0; j <= r; j++) ans = (ans + dp[h&1][j]) % MOD;

        out.printLine(ans);
    }
}
