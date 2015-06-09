package net.rsalesc.io;

import java.io.*;
import java.text.DecimalFormat;

/**
 * Created by rsalesc on 30/01/15.
 */
public class FastOutput {
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

    public void printf(String format, Object...args){
        writer.printf(format, args);
    }

    public void close(){
        writer.close();
    }
    public void flush() {
        writer.flush();
    }
}