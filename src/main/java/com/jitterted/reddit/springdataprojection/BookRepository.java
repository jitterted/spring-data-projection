package com.jitterted.reddit.springdataprojection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
  @Query("SELECT book.name as name, COUNT(book.id) as count FROM BookEntity AS book GROUP BY book.name")
  List<BookCount> countByGroupedName();
}
