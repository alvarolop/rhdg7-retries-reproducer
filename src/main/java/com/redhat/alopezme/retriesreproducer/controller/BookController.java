package com.redhat.alopezme.retriesreproducer.controller;


import com.redhat.alopezme.retriesreproducer.model.Book;
import com.redhat.alopezme.retriesreproducer.repository.BookRepository;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.spring.remote.provider.SpringRemoteCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tester")
public class BookController {

    @Autowired
    BookRepository bookRepository;


    /**
     * GET KEYS
     */
    @GetMapping("/cache/{cache}/keys")
    public String getKeys(
            @PathVariable(value = "cache") String cacheName) {

        return bookRepository.getKeys() + System.lineSeparator();
    }



    /**
     * GET
     */
    @GetMapping("/{id}")
    public String getByID(
            @PathVariable(value = "id") int id) {
        return bookRepository.findById(id).toString();
    }


    /**
     * PUT
     */
    @PutMapping("/{id}")
    public void putById(
            @PathVariable(value = "id") int id) {
        Book book = new Book(id,"Coding from home" ,"Álvaro López Medina",2021);
        bookRepository.insert(id,book);
    }

    /**
     * REMOVE
     */
    @DeleteMapping("/{id}")
    public void removeById(
            @PathVariable(value = "id") int id) {
        bookRepository.delete(id);
    }

}
