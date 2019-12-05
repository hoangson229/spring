package com.hoangson.hellospring.redis;

import com.hoangson.hellospring.entity.Product;
import com.hoangson.hellospring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProductRedisCache implements RedisCache<Product> {
    @Autowired
    private RedisTemplate template;
    private HashOperations hashOperations;
    private final String KEY = "PRODUCT";
    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void init(){
        hashOperations = template.opsForHash();

        //Load data from Data base
        List<Product> listProduct;
        listProduct = productRepository.findAll();
        System.out.println(listProduct);
        listProduct.forEach(product -> add(product));
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
