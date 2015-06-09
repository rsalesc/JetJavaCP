package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

import java.util.Stack;

public class TaskD {
    public void solve(int testNumber, FastInput in, FastOutput out) {
        int n = in.readInt();
        int a[] = new int[n+1];
        int turn[] = new int[n+1];
        for(int i = 1; i <= n; i++) a[i] = in.readInt();

        Stack<Integer> s = new Stack();
        s.push(1);
        turn[1] = -1;  // turns are 1 indexed

        int ans = 0;
        for(int i = 2; i <= n; i++){
            while(!s.empty() && a[s.peek()] < a[i]) {
                turn[i] = Math.max(turn[i], turn[s.peek()]+1);
                s.pop();
            }

            if(s.empty()){
                turn[i] = -1;
            }
            ans = Math.max(ans, turn[i]+1);
            s.push(i);
        }

        out.printLine(ans);
    }
}
