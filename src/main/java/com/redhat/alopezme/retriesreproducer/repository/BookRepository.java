package com.redhat.alopezme.retriesreproducer.repository;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.redhat.alopezme.retriesreproducer.model.Book;
import org.infinispan.spring.remote.provider.SpringRemoteCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;


@EnableCaching
@CacheConfig(cacheNames = "test01")
@Service
public class BookRepository {

    @Value("${reproducer.read-timeout}")
    private int readTimeout;

    @Value("${reproducer.write-timeout}")
    private int writeTimeout;

    @Autowired
    private SpringRemoteCacheManager cacheManager;

    Logger logger = LoggerFactory.getLogger(BookRepository.class);

    @Cacheable(key="#id")
    public Book findById(int id){
        return null;
    }

    @CachePut(key="#id")
    public Book insert(int id, Book book){
        return book;
    }

    public Book findByIdAsync(int id){
        Book result = new Book();
        try {
            result =  (Book) cacheManager.getNativeCacheManager().getCache("test01").getAsync(id).get(readTimeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Book insertAsync(int id, Book book){
        Book result = new Book();
        try {
             result = (Book)cacheManager.getNativeCacheManager().getCache("test01").putAsync(id,book).get(writeTimeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return result;
    }

    @CacheEvict(key="#id")
    public void delete(int id){
    }

    public String getKeys(){
        return cacheManager.getNativeCacheManager().getCache("test01").keySet().toString();
    }

}
