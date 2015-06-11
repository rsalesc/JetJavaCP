import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
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
		RivalryOfCode solver = new RivalryOfCode();
		int testCount = Integer.parseInt(in.next());
		for (int i = 1; i <= testCount; i++)
			solver.solve(i, in, out);
		out.close();
	}
}

class RivalryOfCode {
    public void solve(int testNumber, FastInput in, FastOutput out) {
        String distort = in.readString();
        String actual = in.readString();

        int n = distort.length();
        int m = actual.length();

        int dp[][] = new int[n+1][m+1];

        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= m; j++){
                if(distort.charAt(i-1) == actual.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }

        out.printLine(n-dp[n][m]);
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

    public String next(){
        return read();
    }

    public String readString(){ return read(); }

}

class FastOutput {
    private PrintWriter writer;

    public FastOutput(OutputStream out){
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));
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

    public void close(){
        writer.close();
    }
}

