package com.techreturners.bookmanager.repository;

import com.techreturners.bookmanager.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookManagerRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
}
