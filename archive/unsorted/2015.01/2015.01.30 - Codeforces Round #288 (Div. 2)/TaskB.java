package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

public class TaskB {
    public void solve(int testNumber, FastInput in, FastOutput out) {
        String num = in.read();
        int n = num.length();
        int[] digits = new int[n+1];
        for(int i = 1; i <= n; i++)
            digits[i] = num.charAt(n-i) - '0';
        int pos = 0;
        for(int i = 2; i <= n; i++)
            if(digits[i] < digits[1] && digits[i] % 2 == 0)
                pos = i;
            else if(digits[i] % 2 == 0 && pos == 0)
                pos = i;

        if(pos == 0)
            out.print("-1");
        else{
            int tmp = digits[pos];
            digits[pos] = digits[1];
            digits[1] = tmp;
            for(int i = n; i > 0; i--)
                out.print(digits[i]);
            out.printLine();
        }
    }
}
