package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

import java.util.Arrays;

public class TaskD {
    double p[];

    public void solve(int testNumber, FastInput in, FastOutput out) {
        int n = in.readInt();

        p = new double[n];
        in.readDoubleArray(p);

        for(int i = 0; i < n; i++){
            if(p[i] == 1.0){
                out.printLine(1.0);
                return;
            }
        }

        Arrays.sort(p);

        double sum = 0.0;
        double product = 1.0;
        for(int i = n-1; i >= 0; i--){
            if(sum >= 1.0) break;
            sum += p[i]/(1.0-p[i]);
            product *= (1.0-p[i]);
        }

        out.printLine(sum * product);
    }
}
