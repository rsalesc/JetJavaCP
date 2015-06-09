package net.rsalesc.io;

import java.io.*;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class FastInput{
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

    public void readStringArray(String[] array){
        for(int i = 0; i < array.length; i++)
            array[i] = readString();
    }
}