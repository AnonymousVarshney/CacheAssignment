package com.nucleus.cacheassignment;


import com.nucleus.cacheassignment.cache.manager.CacheManager;
import com.nucleus.cacheassignment.vo.CustomerData;
import com.nucleus.cacheassignment.vo.TestData;


public class MainClass {

	
	public static void main(String[] args) {
		//DummyData dummyData=new DummyData();
		CacheManager coObject = new CacheManager(
				TestData.class,2, 3,0.5);

		for(long i=1;i<5;i++) {
			TestData test = (TestData) coObject.get(i);
			System.out.println("Name"+test.getName());
		}


		CacheManager co2Object = new CacheManager(
				CustomerData.class,2, 3,0.5);
		for(long i=1;i<5;i++) {
			CustomerData test = (CustomerData) co2Object.get(i);
			System.out.println("CName"+test.getName());
		}

		for(long i=1;i<5;i++) {
			TestData test = (TestData) coObject.get(i);
			System.out.println("NameAgain"+test.getName());
		}

	}

	

}
