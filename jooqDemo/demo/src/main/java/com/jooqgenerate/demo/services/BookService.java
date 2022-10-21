package com.jooqgenerate.demo.services;

import com.jooqDemo.jooqDemo.tables.pojos.Book;
import com.jooqgenerate.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }

    public Book getSingleBook(int id){
        return bookRepository.getSingleBook(id);
    }

    public void insertBook(Book book) {
        bookRepository.insertBook(book);
    }

    public void updateRecord(Book book, int id) {
        bookRepository.updateRecord(book,id);
    }

    public void deleteRecord(int id) {
        bookRepository.deleteRecord(id);
    }

}
