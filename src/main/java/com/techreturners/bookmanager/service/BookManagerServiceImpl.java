package com.techreturners.bookmanager.service;

import com.techreturners.bookmanager.exception.BookNotFoundException;
import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.repository.BookManagerRepository;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookManagerServiceImpl implements BookManagerService {

    @Autowired
    BookManagerRepository bookManagerRepository;

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookManagerRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public Book insertBook(Book book) {
        return bookManagerRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookManagerRepository.findById(id).get();
    }

    //User Story 4 - Update Book By Id Solution
    @Override
    public void updateBookById(Long id, Book book) throws BookNotFoundException {
        Optional<Book> retrievedObject = bookManagerRepository.findById(id);

        if (retrievedObject.isPresent()) {
            Book retrievedBook = retrievedObject.get();
            retrievedBook.setTitle(book.getTitle());
            retrievedBook.setDescription(book.getDescription());
            retrievedBook.setAuthor(book.getAuthor());
            retrievedBook.setGenre(book.getGenre());

            bookManagerRepository.save(retrievedBook);
        }else {
            throw new BookNotFoundException("Book is not found for update .Please check the book Id");
        }
    }

    @Override
    public void deleteBookById(Long bookId) throws BookNotFoundException {
        Optional<Book> retrievedBook = bookManagerRepository.findById(bookId);
        if (retrievedBook.isPresent()) {
            bookManagerRepository.delete(retrievedBook.get());
        } else {
            throw new BookNotFoundException("Book is not found for delete .Please check the book Id");
        }

    }
}
