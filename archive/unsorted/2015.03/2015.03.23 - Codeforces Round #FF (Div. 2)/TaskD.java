package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;
import net.rsalesc.util.MaxPriorityQueue;

public class TaskD {
    public void solve(int testNumber, FastInput in, FastOutput out) {
        int n = in.readInt();
        int m = in.readInt();
        int k = in.readInt();
        int p = in.readInt();

        int mat[][] = new int[n][m];
        long col[] = new long[m];

        in.readIntArray(mat);

        MaxPriorityQueue<Long> rowPQ = new MaxPriorityQueue<Long>();
        MaxPriorityQueue<Long> colPQ = new MaxPriorityQueue<Long>();

        for(int i = 0; i < n; i++){
            long row = 0;
            for(int j = 0; j < m; j++){
                row += mat[i][j];
                col[j] += mat[i][j];
            }
            rowPQ.add(row);
        }

        for(int j = 0; j < m; j++) colPQ.add(col[j]);

        long bestRow[] = new long[k+1];
        long bestCol[] = new long[k+1];

        for(int i = 1; i <= k; i++){
            long rowElement = rowPQ.poll();
            long colElement = colPQ.poll();

            bestRow[i] = bestRow[i-1] + rowElement;
            bestCol[i] = bestCol[i-1] + colElement;

            rowElement -= (long)m*p;
            colElement -= (long)n*p;

            rowPQ.add(rowElement);
            colPQ.add(colElement);
        }

        long ans = Long.MIN_VALUE;

        for(int i = 0; i <= k; i++){
            ans = Math.max(ans, bestRow[i] + bestCol[k-i] - (long)i*(k-i)*p); // consider we pick all rows and the pick all cols (order does not matter)
        }

        out.printLine(ans);
    }
}
