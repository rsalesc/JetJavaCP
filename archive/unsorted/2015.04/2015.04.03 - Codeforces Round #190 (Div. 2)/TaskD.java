package net.rsalesc;

import net.rsalesc.io.FastInput;
import net.rsalesc.io.FastOutput;
import net.rsalesc.util.CPP;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class TaskD {
    Vector<Integer> atk, def;
    TreeMap<Integer, Integer> ciel;
    int ans;

    int hitAtk(boolean keepHitting){
        int dmg = 0;
        for(int k : atk){
            Map.Entry<Integer, Integer> entry = keepHitting ? ciel.ceilingEntry(k) : ciel.lastEntry();
            if(entry == null) return dmg;
            int strength = entry.getKey(), count = entry.getValue()-1;
            if(strength < k) return dmg;

            dmg += strength - k;
            if(count == 0)
                ciel.remove(strength);
            else
                CPP.mapReplace(ciel, strength, count);
        }

        if(keepHitting) {
            for (Map.Entry<Integer, Integer> entry : ciel.entrySet()) {
                dmg += entry.getKey() * entry.getValue();
            }
        }

        return dmg;
    }

    int hitDef(){
        for(int k : def){
            Map.Entry<Integer, Integer> entry = ciel.higherEntry(k);
            if(entry == null) return 0;
            int strength = entry.getKey(), count = entry.getValue()-1;
            if(count == 0)
                ciel.remove(strength);
            else
                CPP.mapReplace(ciel, strength, count);
        }

        int dmg = hitAtk(true);
        return dmg;
    }

    public void solve(int testNumber, FastInput in, FastOutput out) {
        int m = in.readInt();
        int n = in.readInt();

        atk = new Vector();
        def = new Vector();
        for(int i = 0; i < m; i++){
            String type = in.readString();
            int strength = in.readInt();
            if(type.equals("ATK"))
                atk.add(strength);
            else
                def.add(strength);
        }

        Collections.sort(atk);
        Collections.sort(def);

        ciel = new TreeMap();

        for(int i = 0; i < n; i++){
            int strength = in.readInt();
            CPP.mapReplace(ciel, strength, ciel.getOrDefault(strength, 0)+1);
        }

        ans = 0;

        TreeMap<Integer, Integer> cielBp = (TreeMap)ciel.clone();

        ans = Math.max(ans, hitDef());

        ciel = cielBp;
        ans = Math.max(ans, hitAtk(false));

        out.printLine(ans);
    }
}
