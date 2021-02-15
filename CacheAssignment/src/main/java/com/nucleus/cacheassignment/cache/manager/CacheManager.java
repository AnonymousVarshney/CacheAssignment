package com.nucleus.cacheassignment.cache.manager;

import com.nucleus.cacheassignment.cache.service.ObjectDataCache;
import com.nucleus.cacheassignment.cache.vo.CacheStorageObject;
import com.nucleus.cacheassignment.dummyData.CustomerDummyData;
import com.nucleus.cacheassignment.dummyData.TestDummyData;
import com.nucleus.cacheassignment.dummyData.DummyDataInterface;
import com.nucleus.cacheassignment.vo.CustomerData;
import com.nucleus.cacheassignment.vo.TestData;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
public class CacheManager<T>{
  
    /*  variable to check object expiry
     */
    private int objectLifeTimeSeconds;
    private double loadFactor;
    private int maxSize;
    private ObjectDataCache<CacheStorageObject<T>> objectDataCache;
    private DummyDataInterface dummyData;

    
    public CacheManager(Class<T> objClass, int objectLifeTimeSeconds, int maxSize, double loadFactor)
    {
        this.maxSize = maxSize;
        this.objectLifeTimeSeconds = objectLifeTimeSeconds;
        this.loadFactor = loadFactor;	//always less than 1
        this.objectDataCache = new ObjectDataCache<>();
        if(TestData.class.isAssignableFrom(objClass)) {
            dummyData= new TestDummyData();
        }else if(CustomerData.class.isAssignableFrom(objClass)) {
            dummyData=new CustomerDummyData();
    }
    }

    public T get(Long key){
        CacheStorageObject<T> obj = objectDataCache.get(key.intValue());
        //check if object is in cache
        if(obj==null){
            //if deleted recache
            return update(key.intValue());
        } else {
            //found in cache so updating the latest fetch timestamp
            LocalDateTime cacheDate = obj.getDate();
            LocalDateTime present = LocalDateTime.now();
            long seconds = ChronoUnit.SECONDS.between(cacheDate, present);
            if (seconds > (long) this.objectLifeTimeSeconds) {
                 updateTimeStamp(key.intValue(),obj.getObject());
            }
            return obj.getObject();
        }
    }

    public void put(T object,int id){
        LocalDateTime presentTime=LocalDateTime.now();

        CacheStorageObject<T> cacheMetadata = new CacheStorageObject<T>(id, object,presentTime);
        objectDataCache.add(cacheMetadata,presentTime);
    }

    private void updateTimeStamp(int key,T object){
        objectDataCache.deleteByHashCode(key);
        LocalDateTime presentTime=LocalDateTime.now();
        CacheStorageObject<T> cacheMetadata = new CacheStorageObject<T>(key, object,presentTime);
        objectDataCache.add(cacheMetadata,presentTime);
    }

    private T update(int key){

        T temp =(T)dummyData.getFromDb(Long.valueOf(key));//get data from database

        int size = objectDataCache.getSize();

        //Clearing least updated values by timestamp
        if(size==this.maxSize){
            objectDataCache.deleteFromEnd((int)(maxSize*(1-loadFactor)));

        }
        LocalDateTime presentTime=LocalDateTime.now();
        CacheStorageObject<T> cacheMetadata = new CacheStorageObject<T>(key, temp,presentTime);
        objectDataCache.add(cacheMetadata,presentTime);

        return temp;
    }
    

    
}
