package com.nucleus.cacheassignment.cache.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ObjectDataCache<T> {

    private Map<Integer, T> dataMap = new HashMap<>();

    private Map<Integer, LocalDateTime> timeMap = new HashMap<>();


    public void add(T obj,LocalDateTime dateTime){

        if(dataMap.get(obj.hashCode()) == null){
            dataMap.put(obj.hashCode(),obj);
            timeMap.put(obj.hashCode(),dateTime);
        }
    }

    public void delete(T obj){

        T object = dataMap.get(obj.hashCode());
        if(object != null){
            dataMap.remove(obj.hashCode());
            timeMap.remove(obj.hashCode());
        }
    }

    public void deleteByHashCode(int hashCode){

        T object = dataMap.get(hashCode);
        if(object != null){
            dataMap.remove(hashCode);
        }
    }

    public T get(int id){

        T object = dataMap.get(id);
        if(object!=null){
            return object;
        } else {
            return null;
        }
    }

    public int getSize(){
        return dataMap.size();
    }



    public void deleteFromEnd(int count) {
        Map<Integer,LocalDateTime> sorted=
                timeMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).
                        collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        int i=0;
        for(Integer key:sorted.keySet()){
         if(i==count){
               break;
           }
            deleteByHashCode(key);
            i++;
        }

    }
}
