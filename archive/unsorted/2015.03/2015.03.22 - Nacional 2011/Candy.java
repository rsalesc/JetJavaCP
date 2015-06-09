package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

public class Candy {
    long c[];
    int n;
    long sum;

    long solve(long s){ // s is package size
        if(s % n != 0) return 0;

        long rest = c[0] % s;
        for(int i = 1; i < n; i++) {
            if (c[i] % s != rest) return 0; // if remainders are different, its impossible
        }

        if(rest * n % s != 0) return 0; // must-be variety packages cant be constructed

        // long variety = rest / s2; // how many must-be variety packages
        long minf = c[0] / s;

        for(int i = 1; i < n; i++) {
            minf = Math.min(minf, c[i] / s);
        }

        if(rest == 0) minf--;
        return minf;
    }

    public void solve(int testNumber, FastInput in, FastOutput out) {
        while(( n = in.readInt()) != 0) {

            c = new long[n];
            sum = 0;

            for (int i = 0; i < n; i++) {
                c[i] = in.readInt();
                sum += c[i];
            }

            long ans = 0;

            for (long i = n; i * i <= sum; i++) { // i is package count
                if (sum % i != 0) continue;

                ans += solve(sum / i);
                if (i * i != sum) ans += solve(i);
            }

            out.printLine(ans);
        }
    }
}
