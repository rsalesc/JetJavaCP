package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;
import net.rsalesc.io.GCJ;

public class TaskA {
    public void solve(int testNumber, FastInput in, FastOutput out) {
        int n = in.readInt();
        String s = in.read();

        int cnt = 0, ans = 0;
        for(int i = 0; i <= n; i++){
            int val = s.charAt(i)-'0';
            if(i > cnt){
                ans += i-cnt;
                cnt = i;
            }
            cnt += val;
        }

        out.print(GCJ.getCase(testNumber));
        out.printLine(ans);

    }
}
