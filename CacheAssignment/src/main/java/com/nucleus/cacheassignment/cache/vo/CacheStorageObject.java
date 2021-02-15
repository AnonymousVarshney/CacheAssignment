package com.nucleus.cacheassignment.cache.vo;

import java.time.LocalDateTime;

public class CacheStorageObject<T> implements Comparable<CacheStorageObject<T>>{

    private int id;
    private T object;
    private LocalDateTime date;

    public CacheStorageObject(int id, T obj, LocalDateTime d){
        this.id=id;
        this.object=obj;
        this.date=d;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public int compareTo(CacheStorageObject<T> o) {

        return this.getDate().compareTo(o.getDate());
    }
}
