package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class TaskD {

    class Breeder{
        public int x;
        public int y;
        public int count;
        public Breeder(int x, int y, int count) {
            this. x = x;
            this. y = y;
            this. count = count;
        }
    }

    public void solve(int testNumber, FastInput in, FastOutput out) {
        int n = in.readInt();
        int m = in.readInt();

        String mat[] = new String[n];
        for(int i = 0; i < n; i++) mat[i] = in.readString();

        Breeder exit = new Breeder(0, 0, 0);
        Breeder me = new Breeder(0, 0, 0);
        ArrayList<Breeder> list = new ArrayList<Breeder>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                char c = mat[i].charAt(j);
                if(c == 'S')
                    me = new Breeder(i, j, 1);
                else if(Character.isDigit(c) && c > '0') {
                    list.add(new Breeder(i, j, c - '0'));
                }else if(c == 'E'){
                    exit = new Breeder(i, j, 1);
                }
            }
        }

        // bfs
        LinkedList<Breeder> q = new LinkedList<Breeder>();
        q.add(exit);
        int dist[][] = new int[n][m];
        for(int[] row : dist) Arrays.fill(row, -1);
        dist[exit.x][exit.y] = 0;

        while(!q.isEmpty()){
            Breeder top = q.poll();

            for(int i = -1; i <= 1; i++){
                for(int j = -1; j <= 1; j++){
                    int di = top.x + i;
                    int dj = top.y + j;
                    int manhattan = Math.abs(i) + Math.abs(j);
                    if(di < 0 || di >= n || dj < 0 || dj >= m || mat[di].charAt(dj) == 'T' || manhattan >= 2) continue;

                    if(dist[di][dj] == -1){
                        dist[di][dj] = dist[top.x][top.y]+1;
                        q.add(new Breeder(di, dj, 1));
                    }
                }
            }
        }

        int ans = 0;

        for(Breeder br : list){
            if(dist[br.x][br.y] != -1 && dist[br.x][br.y] <= dist[me.x][me.y]){
                ans += br.count;
            }
        }

        out.printLine(ans);
    }
}
