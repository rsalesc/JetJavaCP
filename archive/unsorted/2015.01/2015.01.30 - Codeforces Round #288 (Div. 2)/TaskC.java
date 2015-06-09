package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

public class TaskC {
    static int MID = 500;
    int n, t, r;
    int w[];
    int c[];
    boolean lightAt(int x){
        boolean ok = false;
        for(int i = 0; i < n; i++)
            if(w[i] <= x+t) {
                c[i]++;
                ok = true;
            }
        return ok;
    }
    public void solve(int testNumber, FastInput in, FastOutput out) {
        n = in.readInt();
        t = in.readInt();
        r = in.readInt();
        w = new int[n];
        c = new int[n];
        for(int i = 0; i < n; i++){
            w[i] = in.readInt() + MID;
        }

        int ans = 0;
        for(int i = 0; i < n; i++)
            for(; c[i] < r;){
                if(!lightAt(w[i] - r + c[i])){
                    out.printLine("-1");
                    return;
                }
                ans++;
            }

        out.printLine(ans);
    }
}
