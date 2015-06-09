package net.rsalesc;



import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

public class TaskA {
    public void solve(int testNumber, FastInput in, FastOutput out) {
        int n = in.readInt();
        int [][] m = new int[n+2][n+2];
        int ans = 1;
        for(int i = 1; i <= n; i++){
            m[i][1] = 1;
            m[1][i] = 1;
        }

        for(int i = 2; i <= n; i++)
            for(int j = 2; j <= n; j++){
                m[i][j] = m[i-1][j] + m[i][j-1];
                ans = Math.max(m[i][j], ans);
            }

        out.printLine(ans);
    }
}
