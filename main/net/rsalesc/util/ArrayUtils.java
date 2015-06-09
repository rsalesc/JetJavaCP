package net.rsalesc.util;

/**
 * Created by Roberto on 02/04/2015.
 */
public class ArrayUtils {
    public static char[] charsFromString(String s, int padding){
        int n = s.length();
        char [] res = new char[n+padding];
        s.getChars(0, n, res, padding);
        return res;
    }

    public static char[] charsFromString(String s){
        return charsFromString(s, 0);
    }
}
