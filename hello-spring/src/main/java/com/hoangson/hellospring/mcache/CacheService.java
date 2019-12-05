package com.hoangson.hellospring.mcache;


import com.hoangson.hellospring.entity.TestEntity;
import com.hoangson.hellospring.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {


    @Autowired
    TestRepository repository;

    @Cacheable(value = "testEntity" , unless="#result == null")
    public TestEntity findUserById(Long id) {
        System.out.println("CacheService start --------------------->");

        try
        {
            System.out.println("Going to sleep for 5 Secs.. to simulate backend call.");
            Thread.sleep(1000*5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        TestEntity testEntity =  repository.findUserById(id);

        System.out.println("CacheService end --------------------->");

        return testEntity;
    }


    @CacheEvict(value="testEntity",allEntries=true)
    public void evictCache() {
        System.out.println("Evicting all entries from fooCache.");
    }
}
