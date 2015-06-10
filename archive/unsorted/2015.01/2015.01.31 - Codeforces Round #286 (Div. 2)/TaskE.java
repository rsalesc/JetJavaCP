package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;
import net.rsalesc.lib.util.pair.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;

public class TaskE {

    int n;
    private long m;
    private long k;
    private long p;
    private long[] a;
    private long[] h;
    private long[] hh;

    class PairLI extends Pair<Long,Integer>{
        public PairLI(Long a, Integer b){
            super(a,b);
        }
    }

    boolean ok(long x){
        Arrays.fill(hh, x);
        PriorityQueue<PairLI> pq = new PriorityQueue<PairLI>();
        for(int i = 0; i < n; i++) {
            hh[i] = x;
            if(hh[i] - a[i]*m >= 0) continue;
            pq.add(new PairLI(hh[i]/a[i]+1, i));
        }

        long hits = 0;
        while(!pq.isEmpty()){
            if(hits >= m*k) return false;
            PairLI li = pq.poll();
            if(li.first-1 <= hits/k) return false;
            int i = li.second;
            hh[i] += p;
            hits++;
            li.first = hh[i]/a[i]+1;
            if(hh[i] - a[i]*m < 0) pq.add(li);
        }
        if(hits > m*k) return false;
        for(int i = 0; i < n; i++) {
            long target = hh[i] - a[i]*m;
            if (target < h[i]) {
                if(hits >= m*k) return false;
                long dif = h[i] - target;
                long givenHits = (dif+p-1)/p;
                hits += givenHits;
            }
        }

        return (hits <= m*k);
    }

    public void solve(int testNumber, FastInput in, FastOutput out) {
        n = in.readInt();
        m = in.readInt();
        k = in.readInt();
        p = in.readInt();
        a = new long[n];
        h = new long[n];
        hh = new long[n];
        for(int i = 0; i < n; i++){
            h[i] = in.readInt();
            a[i] = in.readInt();
        }

        long l = 0, r = 1L << 55;
        while(l < r){
            long mid = (l+r)/2;
            if(ok(mid))
                r = mid;
            else
                l = mid+1;
        }

        out.printLine(l);
    }
}
