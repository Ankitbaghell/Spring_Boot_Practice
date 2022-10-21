package com.jooqgenerate.demo.repository;

import com.jooqDemo.jooqDemo.Tables;
import com.jooqDemo.jooqDemo.tables.pojos.Book;
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
        return dslContext.selectFrom(Tables.BOOK).fetchInto(Book.class);
    }

    // get single record
    public Book getSingleBook(int id){
        var books = dslContext.selectFrom(Tables.BOOK).where(Tables.BOOK.ID.eq(id)).fetchOne();
//        return books.stream().findAny().orElseThrow(()->new RuntimeException("No Book found for id: "+id));
        return books.into(Book.class);
    }

    // insert a record
    public void insertBook(Book book){
        dslContext.insertInto(Tables.BOOK, Tables.BOOK.ID, Tables.BOOK.TITLE, Tables.BOOK.AUTHOR)
                .values(book.getId(), book.getTitle(), book.getAuthor()).execute();
    }

    // update a record
    public void updateRecord(Book book, int id){
        dslContext.update(Tables.BOOK)
                .set(Tables.BOOK.ID,id)
                .set(Tables.BOOK.TITLE, book.getTitle())
                .set(Tables.BOOK.AUTHOR, book.getAuthor())
                .where(Tables.BOOK.ID.eq(id))
                .execute();
    }

    //delete a record
    public void deleteRecord(int id){
        dslContext.deleteFrom(Tables.BOOK).where(Tables.BOOK.ID.eq(id)).execute();
    }


}
