package net.rsalesc;



import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

public class TaskB {
    public void solve(int testNumber, FastInput in, FastOutput out) {
        int n = in.readInt();
        int k = in.readInt();
        int [] p = new int[n];
        int min = 1000000;
        for(int i = 0; i < n; i++){
            p[i] = in.readInt();
            min = Math.min(p[i], min);
        }
        for(int i = 0; i < n; i++)
            p[i] -= min;

        int max = 0;
        for(int i = 0; i < n; i++)
            max = Math.max(max, p[i]);

        if(max <= k){
            out.printLine("YES");
            for(int i = 0; i < n; i++){
                for(int j = 0; j < min; j++){
                    if(j > 0) out.print(' ');
                    out.print(1);
                }
                if(min > 0 && p[i] > 0) out.print(' ');
                for(int j = 0; j < p[i]; j++){
                    if(j > 0) out.print(' ');
                    out.print(j+1);
                }
                out.printLine();
            }
        }else{
            out.printLine("NO");
        }
    }
}
