package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;

import java.util.ArrayList;
import java.util.Collections;

public class ShipTraffic {
    public void solve(int testNumber, FastInput in, FastOutput out) {
        int n = in.readInt();
        double w = in.readDouble();
        double u = in.readDouble();
        double v = in.readDouble();
        double t1 = in.readDouble();
        double t2 = in.readDouble();
        double timeToCross = w/v;
        double x1 = -t2*u;
        double x2 = -t1*u;

        ArrayList<Double> start = new ArrayList<Double>();
        ArrayList<Double> end = new ArrayList<Double>();

        int ships = 0;
        for(int i = 0; i < n; i++){
            char orient = in.readChar();
            int m = in.readInt();
            ships += m;
            for(int j = 0; j < m; j++){
                double length = in.readDouble();
                double position = in.readDouble();
                double a, b = position;
                if(orient == 'E'){
                    a = position-length;
                }else{
                    a = position+length;
                    b = -b;
                    a = -a;
                }

                start.add(a+timeToCross*u*i);
                end.add(b+timeToCross*u*(i+1));
            }
        }

        Collections.sort(start);
        Collections.sort(end);

        double ans = Math.max(0.0, Math.max(x2-Math.max(x1, end.get(ships-1)), Math.min(x2, start.get(0))-x1));
        int a = 0, b = 0, pt = 0;
        while(a != ships && b != ships){
            if(start.get(a) <= end.get(b)){
                a++;
                pt++;
            }else{
                b++;
                pt--;
            }
            if(pt == 0 && a < ships) ans = Math.max(ans, Math.min(x2, start.get(a))-Math.max(x1, end.get(b-1)));
        }

        out.printLine(ans/u);
    }
}
