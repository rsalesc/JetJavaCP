package net.rsalesc.lib.util;

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

    public static<T> void initObjectArray(T[] a) throws IllegalAccessException, InstantiationException {
        for (int i = 0; i < a.length; i++)
            a[i] = (T) a.getClass().getComponentType().newInstance();
    }

    public static<T> void initArray(T[] a, T b){
        for(int i = 0; i < a.length; i++)
            a[i] = b;
    }
}
