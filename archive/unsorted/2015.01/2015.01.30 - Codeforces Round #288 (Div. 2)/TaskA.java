package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

public class TaskA {
    public void solve(int testNumber, FastInput in, FastOutput out) {
        int n = in.readInt();
        int m = in.readInt();
        int k = in.readInt();
        boolean a[][] = new boolean[n+2][m+2];
        boolean ok = false;
        int i;
        for(i = 1; i <=k; i++){
            int y = in.readInt();
            int x = in.readInt();
            a[y][x] = true;
            for(int k1 = -1; k1 <= 0; k1++)
                for(int k2 = -1; k2 <= 0; k2++)
                    ok |= a[y+k1][x+k2] && a[y+k1+1][x+k2] && a[y+k1][x+k2+1] && a[y+k1+1][x+k2+1];
            if(ok) break;
        }
        if(!ok)
            out.printLine("0");
        else
            out.printLine(i);
    }
}
