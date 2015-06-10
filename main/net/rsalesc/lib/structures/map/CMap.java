package net.rsalesc.lib.structures.map;

import java.util.TreeMap;

/**
 * Created by Roberto on 10/06/2015.
 */
public class CMap<K, V> extends TreeMap<K, V> {

    public void set(K key, V value){
        if(this.containsKey(key))
            this.replace(key, value);
        else this.put(key, value);
    }
}
