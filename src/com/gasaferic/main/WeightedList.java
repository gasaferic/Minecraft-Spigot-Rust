package com.gasaferic.main;

import java.util.HashMap;
import java.util.Random;

public class WeightedList<K> extends HashMap<K,Integer>{
	
	private static final long serialVersionUID = 2867689873875899886L;
	private int total;
 
    @Override
    public Integer put(K a, Integer b){
        Integer i=super.put(a,b);
        total=0;
        for(Integer in:values()){
            total+=in;
        }
        return i;
    }
 
    public K get(Random rand){
        if (total<=0)return null;
        int i=rand.nextInt(total);
        for(java.util.Map.Entry<K, Integer> entry:entrySet()){
            i-=entry.getValue();
            if (i<0)return entry.getKey();
        }
        return null;
    }
}