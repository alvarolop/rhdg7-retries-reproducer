package com.redhat.alopezme.retriesreproducer.scheduler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.redhat.alopezme.retriesreproducer.model.Book;
import com.redhat.alopezme.retriesreproducer.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@ConditionalOnProperty(prefix = "reproducer", name = "async", havingValue = "false")
public class SyncRequestsScheduler {

    @Autowired
    BookRepository bookRepository;

    int iterationGet = 0;
    int iterationPut = 0;

    Logger logger = LoggerFactory.getLogger(SyncRequestsScheduler.class);

    /**
     * This method introduces a new book with id [0,999] every 1 ms.
     * Every 10 seconds, it overrides all the books in the cache (1000)
     */
    @Scheduled(fixedDelay = 1, initialDelay = 5_000)
    public void scheduleFixedDelayPuts() {
        int bookId = iterationPut++ % 1_000;
//		logger.trace("Fixed delay task - " + System.currentTimeMillis() / 1000 + " Iteration " + bookId);

        Book book = new Book(bookId,"Coding from home" ,"Álvaro López Medina",2021);
        logger.trace("PUT - " + book.toString());
        bookRepository.insert(bookId, book);
        if (bookId == 999){logger.info("PUT - New loop");}
    }

    /**
     * This method retrieves a book with id [0,999] every 1	 ms.
     */
    @Scheduled(fixedDelay = 1, initialDelay = 5_000)
    public void scheduleFixedDelayGets() {
        int bookId = iterationGet++ % 1_000;
//		logger.trace("Fixed delay task - " + System.currentTimeMillis() / 1000 + " Iteration " + bookId);
        logger.trace("GET - " + bookRepository.findById(bookId));
        if (bookId == 999){logger.info("GET - New loop");}
    }

}
