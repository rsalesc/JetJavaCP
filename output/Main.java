import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.util.Collections;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.IOException;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		FastInput in = new FastInput(inputStream);
		FastOutput out = new FastOutput(outputStream);
		ShipTraffic solver = new ShipTraffic();
		solver.solve(1, in, out);
		out.close();
	}
}

class ShipTraffic {
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

class FastInput{
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public FastInput(InputStream in){
        reader = new BufferedReader(new InputStreamReader(in), 1<<16);
        tokenizer = null;
    }

    public String read(){
        while(tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
            }catch(Exception ex){
                throw new InputMismatchException();
            }
        }
        return tokenizer.nextToken();
    }

    public int readInt(){
        return Integer.parseInt(read());
    }
    public long readLong(){
        return Long.parseLong(read());
    }
    public double readDouble(){
        return Double.parseDouble(read());
    }
    public char readChar(){
        return read().charAt(0);
    }
    public String readString(){ return read(); }

    public boolean readBool(){
        String s = read();
        if("1".equals(s)) {
            return true;
        }
        return Boolean.parseBoolean(s);
    }

    public void readDoubleArray(double[]... array){
        for(int i = 0; i < array.length; i++)
            for(int j = 0; j < array[0].length; j++)
                array[i][j] = readDouble();
    }
    public void readIntArray(int[]... array) {
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[0].length; j++)
                array[i][j] = readInt();
    }

    public void readBoolArray(boolean[]... array){
        for(int i = 0; i < array.length; i++)
            for(int j = 0; j < array[0].length; j++)
                array[i][j] = readBool();
    }
}

class FastOutput {
    private PrintWriter writer;
    private DecimalFormat dformat;

    public FastOutput(OutputStream out){
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));
    }

    public FastOutput(Writer w){
        writer = new PrintWriter(w);
    }

    public void print(Object...args){
        for(int i = 0; i < args.length; i++){
            if(i > 0) writer.print(' ');
            writer.print(args[i]);
        }
    }

    public void printLine(Object...args){
        print(args);
        writer.println();
    }

    public void printMatrix(Object[][] m){
        for(int i = 0; i < m.length; i++)
            printLine(m[i]);
    }

    public void close(){
        writer.close();
    }
    public void flush() {
        writer.flush();
    }
}

