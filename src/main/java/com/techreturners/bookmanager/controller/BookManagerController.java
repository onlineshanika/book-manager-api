package com.techreturners.bookmanager.controller;

import com.techreturners.bookmanager.dto.Error;
import com.techreturners.bookmanager.exception.BookAlreadyExistsException;
import com.techreturners.bookmanager.exception.BookNotFoundException;
import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.service.BookManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookManagerController {

    @Autowired
    BookManagerService bookManagerService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookManagerService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping({"/{bookId}"})
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        return new ResponseEntity<>(bookManagerService.getBookById(bookId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        Book newBook = null;
        try {
            newBook = bookManagerService.insertBook(book);
        } catch (BookAlreadyExistsException e) {
            Error error = new Error(e.getMessage(),new Date());
            return new ResponseEntity<>( error,HttpStatus.BAD_REQUEST);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("book", "/api/v1/book/" + newBook.getId().toString());
        return new ResponseEntity<>(newBook, httpHeaders, HttpStatus.CREATED);
    }

    //User Story 4 - Update Book By Id Solution
    @PutMapping({"/{bookId}"})
    public ResponseEntity<?> updateBookById(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        try {
            bookManagerService.updateBookById(bookId, book);
            return new ResponseEntity<>(bookManagerService.getBookById(bookId), HttpStatus.OK);
        } catch (BookNotFoundException e) {
            Error error = new Error(e.getMessage(),new Date());
            return new ResponseEntity<>( error,HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping({"/{bookId}"})
    public ResponseEntity<?> deleteBookById(@PathVariable("bookId") Long bookId) {
        try {
            bookManagerService.deleteBookById(bookId);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (BookNotFoundException e) {
            Error error = new Error(e.getMessage(),new Date());
            return new ResponseEntity<>( error,HttpStatus.BAD_REQUEST);
        }

    }

}
