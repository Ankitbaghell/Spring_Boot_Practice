package com.joogDemoMaven.joogDemoMaven.controller;

import com.joogDemoMaven.joogDemoMaven.service.BookService;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getSingleBook(@PathVariable("id") int id){
        return bookService.getSingleBook(id);
    }

    @PostMapping("/books")
    public void insertBook(@RequestBody Book book){
        bookService.insertBook(book);
    }

    @PutMapping("/books/{id}")
    public void updateBook(@RequestBody Book book, @PathVariable("id") int id){
        bookService.updateBook(book,id);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable("id") int id){
        bookService.deleteBook(id);
    }

}
