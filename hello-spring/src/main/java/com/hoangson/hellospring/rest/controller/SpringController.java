package com.hoangson.hellospring.rest.controller;


import com.hoangson.hellospring.entity.Product;
import com.hoangson.hellospring.entity.TestEntity;
import com.hoangson.hellospring.interceptor.SecretKeyRequired;
import com.hoangson.hellospring.mcache.CacheService;
import com.hoangson.hellospring.redis.ProductRedisCache;
import com.hoangson.hellospring.repository.ProductRepository;
import com.hoangson.hellospring.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@Transactional
@RequestMapping("/son")
public class SpringController {

    @Autowired
    TestRepository testRepository;

    @Autowired
    CacheService cacheService;

    @Autowired
    ProductRedisCache productRedisCache;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/name")
    public String getName() {
        return " Hoang Van Son";
    }

    @GetMapping("list")
    public ResponseEntity<List<TestEntity>> getList() {

        List<TestEntity> testEntities = new ArrayList<>();

        testEntities = testRepository.findAll();

        return new ResponseEntity<List<TestEntity>>(testEntities, HttpStatus.OK);

    }

    @GetMapping("/getbyid")
    public ResponseEntity<TestEntity> getTestById(@RequestParam (name = "id") Long id) {

        System.out.println("id ---------------> " + id);
        TestEntity testEntity = testRepository.findUserById(id);

        return new ResponseEntity<TestEntity>(testEntity, HttpStatus.OK);
    }

    @PostMapping("/editname")
    @SecretKeyRequired
    public ResponseEntity<TestEntity> updateName(@RequestParam (name = "id") Long id, @RequestParam (name = "name") String name) {

        System.out.println("id ---------------> " + id);

        System.out.println("name ---------------> " + name);


        testRepository.updateName(id, name);

        testRepository.updateName(id, name + "1989");
        TestEntity testEntity = testRepository.findUserById(id);

        return new ResponseEntity<TestEntity>(testEntity, HttpStatus.OK);
    }

    @GetMapping("/testCache")
    public ResponseEntity<TestEntity> getTestByIdCache(@RequestParam (name = "id") Long id) {

        System.out.println("getTestByIdCache ---------------> " + id);

        TestEntity testEntity = cacheService.findUserById(id);

        return new ResponseEntity<TestEntity>(testEntity, HttpStatus.OK);
    }

    @PostMapping("/refreshCache/{name}")
    public String refeshCache(@PathVariable("name") String name) {

        cacheService.evictCache();
        return "Refesh Cache";
    }


    @GetMapping("/testRedis")
    public ResponseEntity<Product> getProductRedis(@RequestParam (name = "id") Long id) {

        System.out.println("getProductRedis ---------------> " + id);
        Product product = productRedisCache.find(id.toString());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/refreshProductRedis")
    public ResponseEntity<List<Product>> refreshProductRedis() {
        productRedisCache.init();
        List<Product> listProduct = new ArrayList<>();
        return new ResponseEntity<>(listProduct, HttpStatus.OK);
    }

}
