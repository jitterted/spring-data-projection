package com.jitterted.reddit.springdataprojection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AggregateProjectionTest {

  @Autowired
  private BookRepository bookRepository;

  @Test
  public void threeBooksSavedIsSimpleCountOfThree() throws Exception {
    bookRepository.save(new BookEntity("Lord of the Rings"));
    bookRepository.save(new BookEntity("Lord of the Rings"));
    bookRepository.save(new BookEntity("Harry Potter"));

    assertThat(bookRepository.count())
        .isEqualTo(3);
  }

  @Test
  public void threeBooksWithSameNameGroupingByNameIsCountOfThree() throws Exception {
    bookRepository.save(new BookEntity("Lord of the Rings"));
    bookRepository.save(new BookEntity("Harry Potter"));
    bookRepository.save(new BookEntity("Harry Potter"));
    bookRepository.save(new BookEntity("Lord of the Rings"));
    bookRepository.save(new BookEntity("Lord of the Rings"));


    assertThat(bookRepository.countByGroupedName())
        .filteredOn("name", "Lord of the Rings")
        .extracting(BookCount::getCount)
        .containsOnly(3);
  }
}
