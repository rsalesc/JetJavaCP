package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

public class FlipFlops {
    public void solve(int testNumber, FastInput in, FastOutput out) {
        String s = in.readString();

        char lastChar = s.charAt(0);
        int n = s.length();

        int count = 1;
        for(int i = 1; i < n; i++){
            if(s.charAt(i) != lastChar) count++;
            lastChar = s.charAt(i);
        }

        out.printLine(n-count);
    }
}
