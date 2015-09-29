package net.rsalesc;

public class LineMST {
    static int MOD = (int)1e9+7;

    long power(long i, int p){
        if(p == 0) return 1;
        if(p == 1) return i;
        long res = power(i, p/2);
        res = res*res%MOD;

        if(p % 2 != 0) res = res*i%MOD;
        return res;
    }

    public int count(int N, int L) {
        int M = N-1;
        long dp[][] = new long[N+1][L+1];
        // number of beautiful 1-to-N spanning paths in complete graph
        // with N vertices and edges with costs 1 <= x <= L

        dp[1][0] = 1;

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= L; j++){
                dp[i][j] = dp[i][j-1];
                for(int k = 1; k < i; k++){
                    dp[i][j] += dp[k][j-1]*dp[i-k][j]%MOD * power(L-j+1, k*(i-k)-1)%MOD;
                    dp[i][j] %= MOD;
                }
            }
        }

        long ans = dp[N][L];
        for(int i = 3; i <= N; i++) ans = ans * i % MOD;
        return (int)ans;
    }
}
