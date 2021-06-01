package com.redhat.alopezme.retriesreproducer.repository;


import com.redhat.alopezme.retriesreproducer.model.Book;
import org.infinispan.spring.remote.provider.SpringRemoteCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;


@EnableCaching
@CacheConfig(cacheNames = "test01")
@Service
public class BookRepository {

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

    @CacheEvict(key="#id")
    public void delete(int id){
    }

    public String getKeys(){
        return cacheManager.getNativeCacheManager().getCache("test01").keySet().toString();
    }

}
