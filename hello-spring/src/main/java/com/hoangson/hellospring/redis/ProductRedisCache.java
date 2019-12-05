package com.hoangson.hellospring.redis;

import com.hoangson.hellospring.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class ProductRedisCache implements RedisCache<Product> {
    @Autowired
    private RedisTemplate template;
    private HashOperations hashOperations;
    private final String KEY = "PRODUCT";

    @PostConstruct
    private void init(){
        hashOperations = template.opsForHash();
        Product product = new Product(2L, "Frieze", 1000L );
        hashOperations.put(KEY, KEY + '-' + product.getId(), product);
    }

    @Override
    public void add(Product product) {
        hashOperations.put(KEY, KEY + '-' + product.getId(), product);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(KEY, id);
    }

    @Override
    public Product find(String id) {
        return (Product) hashOperations.get(KEY, KEY + '-' + id);
    }

    @Override
    public Map<Object, Object> findAll() {
        return hashOperations.entries(KEY);
    }
}
