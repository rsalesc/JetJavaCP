package net.rsalesc.lib.strings;

/**
 * Created by Roberto on 02/04/2015.
 */
public class StringAlgorithms {

    // 1 - indexed char arrays (have length n+1)
    // pattern size must be <= size of base
    public static void prefixFunction(char [] pattern, int n, int [] p){
        p[1] = 0;
        int k = 0;
        for(int i = 2; i <= n; i++){
            while(k > 0 && pattern[k+1] != pattern[i])
                k = p[k];

            if(pattern[k+1] == pattern[i]) k++;
            p[i] = k;
        }
    }

    public static void kmp(char [] pattern, char [] base, int n, int [] p, int [] res){
        int k = 0;
        for(int i = 1; i <= n; i++){

            if(k+1 >= pattern.length) k = p[k];
            while(k > 0 && pattern[k+1] != base[i]) {
                k = p[k];
            }

            if(pattern[k+1] == base[i]) k++;
            res[i] = k;
        }
    }
}
