package net.rsalesc.util;

import java.util.Map;

/**
 * Created by Roberto on 03/04/2015.
 */
public class CPP {
    public static <S, T> void mapReplace(Map<S, T> map, S key, T val){
        if(map.replace(key, val) == null){
            map.put(key, val);
        }
    }
}
