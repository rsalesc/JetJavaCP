package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;
import net.rsalesc.lib.strings.StringAlgos;
import net.rsalesc.util.ArrayUtils;

public class TaskD {
    private static int M = (int)1e9+7;

    public void solve(int testNumber, FastInput in, FastOutput out) {
        char [] s = ArrayUtils.charsFromString(in.read(), 1);
        char [] t = ArrayUtils.charsFromString(in.read(), 1);

        int n = s.length-1;
        int m = t.length-1;

        int p[] = new int[m+1];
        StringAlgos.prefixFunction(t, m, p);

        int kmp[] = new int[n+1];
        StringAlgos.kmp(t, s, n, p, kmp);

        int dp[] = new int[n+1];
        int sum[]= new int[n+1];
        int sum2[] = new int[n+1];

        for(int i = 1; i <= n; i++){
            if(kmp[i] != m){
                dp[i] = dp[i-1];
            }else{
                dp[i] = (sum2[i-m] + (i-m+1)) % M;
            }
            sum[i] = (sum[i-1] + dp[i]) % M;
            sum2[i] = (sum2[i-1] + sum[i]) % M;
        }

        out.printLine(sum[n]);
    }
}
