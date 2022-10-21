package com.joogDemoMaven.joogDemoMaven.service;

import com.tej.JooQDemo.jooq.sample.model.Tables;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Book;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private DSLContext dslContext;

    // fetch all records
    public List<Book> getAllBooks(){
        return dslContext.selectFrom(Tables.BOOK)
                .fetchInto(Book.class);
    }

    //fetch single record
    public Book getSingleBook(int id){
        List<Book> books = dslContext.selectFrom(Tables.BOOK)
                .where(Tables.BOOK.ID.eq(id))
                .fetchInto(Book.class);
        return books.get(0);
    }

    //create a record
    public void insertBook(Book book){
         dslContext.insertInto(Tables.BOOK, Tables.BOOK.ID, Tables.BOOK.TITLE, Tables.BOOK.AUTHOR)
                   .values(book.getId(), book.getTitle(), book.getAuthor())
                   .execute();
    }

    //update record
    public void updateBook(Book book, int id){
        dslContext.update(Tables.BOOK)
                .set(Tables.BOOK.ID , book.getId())
                .set(Tables.BOOK.TITLE, book.getTitle())
                .set(Tables.BOOK.AUTHOR, book.getAuthor())
                .where(Tables.BOOK.ID.eq(id))
                .execute();
    }

    //delete record
    public void deleteBook(int id){
        dslContext.deleteFrom(Tables.BOOK)
                .where(Tables.BOOK.ID.eq(id))
                .execute();
    }

}
