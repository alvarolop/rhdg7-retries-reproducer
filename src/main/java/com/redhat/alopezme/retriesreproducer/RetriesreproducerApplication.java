package com.redhat.alopezme.retriesreproducer;

import com.redhat.alopezme.retriesreproducer.model.Book;
import com.redhat.alopezme.retriesreproducer.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class RetriesreproducerApplication {

	@Autowired
	BookRepository bookRepository;

	int iteration = 0;

	Logger logger = LoggerFactory.getLogger(RetriesreproducerApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(RetriesreproducerApplication.class, args);
	}

	/**
	 * This method introduces a new book with id [0,999] every 10 ms.
	 * Every 10 seconds, it overrides all the books in the cache (1000)
	 */
	@Scheduled(fixedDelay = 10, initialDelay = 10_000)
	public void scheduleFixedDelayPuts() {
		int bookId = iteration++ % 1_000;
		logger.trace("Fixed delay task - " + System.currentTimeMillis() / 1000 + " Iteration " + bookId);

		Book book = new Book(bookId,"Coding from home" ,"Álvaro López Medina",2021);
		logger.info("PUT - " + book.toString());
		bookRepository.insert(bookId, book);
	}

	/**
	 * This method retrieves a book with id [0,999] every 10 ms.
	 */
	@Scheduled(fixedDelay = 10, initialDelay = 20_000)
	public void scheduleFixedDelayGets() {
		int bookId = iteration++ % 1_000;
		logger.trace("Fixed delay task - " + System.currentTimeMillis() / 1000 + " Iteration " + bookId);
		logger.info("GET - " + bookRepository.findById(bookId));
	}
}
