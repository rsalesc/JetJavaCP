package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;
import net.rsalesc.lib.structures.intervaltree.Fenwick;

public class ArrangeBoxes {
    int given[];
    int map[];
    int array[];

    public void solve(int testNumber, FastInput in, FastOutput out) {
        int n = in.readInt();

        given = new int[n+1];
        map = new int[n+1];

        for(int i = 1; i <= n; i++){
            int x = in.readInt();
            given[x] = i;
        }

        for(int i = 1; i <= n; i++){
            int x = in.readInt();
            map[x] = i;
        }

        array = new int[n+1];

        for(int i = 1; i <= n; i++){
            array[given[i]] = map[i];
        }

        Fenwick bit = new Fenwick(n+2);
        bit.add(array[1], 1);

        int ans = 0;
        for(int i = 2; i <= n; i++){
            ans += bit.get(array[i]+1, n);
            bit.add(array[i], 1);
        }

        out.printLine(ans);
    }
}
