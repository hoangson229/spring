package com.hoangson.hellospring.redis;

import java.util.Map;

public interface RedisCache< T> {

    public void add(final T t) ;
    public void delete(final String id);
    public T find(final String id);
    public Map<Object, Object> findAll();
}
