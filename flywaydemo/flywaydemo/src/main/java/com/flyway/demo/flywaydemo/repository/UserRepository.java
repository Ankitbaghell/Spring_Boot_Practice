package com.flyway.demo.flywaydemo.repository;

import com.flyway.demo.flywaydemo.entities.User;
import com.jooqDemo.jooqDemo.Tables;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private DSLContext dslContext;
    public String createUser(User user) {
        dslContext.insertInto(Tables.USERDETAILS, Tables.USERDETAILS.USER_ID, Tables.USERDETAILS.USERNAME, Tables.USERDETAILS.USEREMAIL, Tables.USERDETAILS.USERPASSWORD, Tables.USERDETAILS.AGE)
                .values(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getAge())
                .execute();
        return "user created";
    }
}
