package com.jitterted.reddit.springdataprojection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final BookRepository bookRepository;

  @Autowired
  public DataLoader(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    bookRepository.save(new BookEntity("Harry Potter"));
    bookRepository.save(new BookEntity("Harry Potter"));
    bookRepository.save(new BookEntity("Harry Potter"));
    bookRepository.save(new BookEntity("Lord of the Rings"));

    bookRepository.findAll().forEach(System.out::println);

    for (BookCount bookCount : bookRepository.countByGroupedName()) {
      System.out.println(bookCount.getName() + " = " + bookCount.getCount());
    }
  }
}
