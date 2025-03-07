package com.techreturners.bookmanager.service;

import com.techreturners.bookmanager.exception.BookAlreadyExistsException;
import com.techreturners.bookmanager.exception.BookNotFoundException;
import com.techreturners.bookmanager.model.Book;

import java.util.List;

public interface BookManagerService {

    List<Book> getAllBooks();
    Book insertBook(Book book) throws BookAlreadyExistsException;
    Book getBookById(Long id);

    //User Story 4 - Update Book By Id Solution
    void updateBookById(Long id, Book book) throws BookNotFoundException;

    void deleteBookById(Long bookId)throws BookNotFoundException;
    Boolean existsByBookId(Long id);
}
