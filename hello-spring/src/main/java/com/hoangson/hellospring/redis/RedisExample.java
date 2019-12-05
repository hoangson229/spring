package com.hoangson.hellospring.redis;

import com.hoangson.hellospring.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RedisExample implements CommandLineRunner {
    @Autowired
    private RedisTemplate template;
    private HashOperations hashOperations;

    @PostConstruct
    private void init(){
        hashOperations = template.opsForHash();
    }

    @Override
    public void run(String... args) throws Exception {
        template.opsForValue().set("hoangvanson","hello world");
        System.out.println("Value of key hoangvanson: "+template.opsForValue().get("hoangvanson"));


//        hashOperations.put("PRODUCT", 1, "Tivi");
//
//        System.out.println("Value of PRODUCT has id 1:  " + hashOperations.get("PRODUCT", 1));

        Product product = new Product(2L, "Frieze", 1000L );

        hashOperations.put("PRODUCT", 2L, product);

        Product product1 = (Product) hashOperations.get("PRODUCT", 2L);

        System.out.println("Value product" + product.getName());

        System.out.println("Value of PRODUCT has id 2:  " + hashOperations.get("PRODUCT", 2L));



    }
}
