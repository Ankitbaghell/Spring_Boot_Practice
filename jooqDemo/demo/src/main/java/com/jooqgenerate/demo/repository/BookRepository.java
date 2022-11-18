package com.jooqgenerate.demo.repository;

import com.jooqDemo.jooqDemo.Tables;
import com.jooqDemo.jooqDemo.tables.pojos.Book;
import com.jooqgenerate.demo.exceptions.BookNotFoundException;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {
    @Autowired
    private DSLContext dslContext;

    // get All records
    public List<Book> getAllBooks(){
        List<Book> books = dslContext.selectFrom(Tables.BOOK).fetchInto(Book.class);
        books.stream().findAny().orElseThrow(()->new BookNotFoundException("No Book Available"));
        return books;
    }

    // get single record
    public Book getSingleBook(int id){
        List<Book> books = dslContext.selectFrom(Tables.BOOK).where(Tables.BOOK.ID.eq(id)).fetchInto(Book.class);
        return books.stream().findAny().orElseThrow(()->new RuntimeException("No Book found for id: "+id));
    }

    // insert a record
    public void insertBook(Book book){
        dslContext.insertInto(Tables.BOOK, Tables.BOOK.ID, Tables.BOOK.TITLE, Tables.BOOK.AUTHOR)
                .values(book.getId(), book.getTitle(), book.getAuthor()).execute();
    }

    // update a record
    public void updateRecord(Book book, int id){

        List<Book> books = dslContext.selectFrom(Tables.BOOK).where(Tables.BOOK.ID.eq(id)).fetchInto(Book.class);
        books.stream().findAny().orElseThrow(()->new BookNotFoundException("No Book with id "+id +" is available for update"));

        dslContext.update(Tables.BOOK)
                .set(Tables.BOOK.ID,id)
                .set(Tables.BOOK.TITLE, book.getTitle())
                .set(Tables.BOOK.AUTHOR, book.getAuthor())
                .where(Tables.BOOK.ID.eq(id))
                .execute();
    }

    //delete a record
    public void deleteRecord(int id){
        List<Book> books = dslContext.selectFrom(Tables.BOOK).where(Tables.BOOK.ID.eq(id)).fetchInto(Book.class);
        books.stream().findAny().orElseThrow(()->new BookNotFoundException("No Book with id "+id +" is available for Deletion"));

        dslContext.deleteFrom(Tables.BOOK).where(Tables.BOOK.ID.eq(id)).execute();
    }


}
