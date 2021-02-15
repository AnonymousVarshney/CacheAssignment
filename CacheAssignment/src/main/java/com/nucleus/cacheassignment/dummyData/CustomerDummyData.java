package com.nucleus.cacheassignment.dummyData;


import com.nucleus.cacheassignment.vo.CustomerData;
import com.nucleus.cacheassignment.vo.CustomerData;

import java.util.HashMap;
import java.util.Map;

public class CustomerDummyData implements DummyDataInterface<CustomerData>{

    private Map<Long, CustomerData> testMap;

    public CustomerDummyData() {
        testMap = new HashMap<>();
        testMap.put(new Long(1), new CustomerData(new Long(1), "prakhar"));
        testMap.put(new Long(2), new CustomerData(new Long(2), "prakhar2"));
        testMap.put(new Long(3), new CustomerData(new Long(3), "prakhar3"));
        testMap.put(new Long(4), new CustomerData(new Long(4), "prakhar4"));
        testMap.put(new Long(5), new CustomerData(new Long(5), "prakhar5"));
        testMap.put(new Long(6), new CustomerData(new Long(6), "prakhar6"));
        testMap.put(new Long(7), new CustomerData(new Long(7), "prakhar7"));
        testMap.put(new Long(8), new CustomerData(new Long(8), "prakhar8"));
        testMap.put(new Long(9), new CustomerData(new Long(9), "prakhar9"));
        testMap.put(new Long(10),new CustomerData(new Long(10), "prakhar10"));
        testMap.put(new Long(11), new CustomerData(new Long(11), "prakhar11"));
        testMap.put(new Long(12),new CustomerData(new Long(12), "prakhar12"));
        testMap.put(new Long(13),new CustomerData(new Long(13), "prakhar13"));
        testMap.put(new Long(14), new CustomerData(new Long(14), "prakhar14"));
        testMap.put(new Long(15), new CustomerData(new Long(15), "prakhar15"));

    }

    public CustomerData getFromDb(Long id) {
        return testMap.get(id);
    }

}
