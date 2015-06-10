package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

import java.math.BigInteger;

public class FindTheStudents {
    BigInteger a[];

    BigInteger maximum(){
        BigInteger max = BigInteger.ZERO;
        for(int i = 0; i < a.length; i++) max = max.max(a[i]);
        return max;
    }

    BigInteger minimize(){
        BigInteger min = BigInteger.ZERO;
        int count = 0;
        for(int i = 0; i < a.length; i++)
            if(a[i].compareTo(BigInteger.ZERO) == 1) {
                if (min == BigInteger.ZERO)
                    min = a[i];
                else
                    min = min.min(a[i]);
                count++;
            }

        for(int i = 0; i < a.length; i++) a[i] = a[i].subtract(min).max(BigInteger.ZERO);
        return min.multiply(BigInteger.valueOf(count));
    }

    public void solve(int testNumber, FastInput in, FastOutput out) {
        int n = in.readInt();

        a = new BigInteger[n];
        for(int i = 0; i < n; i++) a[i] = new BigInteger(in.next());

        while(maximum().compareTo(BigInteger.ZERO) > 0){
            BigInteger min = minimize();
            out.printLine(min.toString());
        }
    }
}
