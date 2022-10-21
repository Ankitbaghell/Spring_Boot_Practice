package com.springboot.crud;

import com.springboot.crud.dao.UserRepository;
import com.springboot.crud.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {

		ApplicationContext entity = SpringApplication.run(CrudApplication.class, args);

		// Now I can get my bean/object
		UserRepository userRepository = entity.getBean(UserRepository.class);

		// Now my userRepository have all the CRUD methods
		User user1 = new User(1,"Ankit","Vidisha","Associate MTS");
		User user2 = new User(2,"Sachin","Mumbai","God of Cricket");
		User user3 = new User(3,"Virat","Delhi","King kohli");
		User user4 = new User(4,"Dhoni","Ranchi","captain cool");

		//Insert in DB
		//insert single object
		User user = userRepository.save(user1);
		System.out.println(user);

		//insert multiple objects
		List<User> list = List.of(user1, user2, user3, user4);
		Iterable<User> users = userRepository.saveAll(list);

		users.forEach(u-> System.out.println(u));

		// Update in DB
		Optional<User> oldUser = userRepository.findById(4);
		User user5 = oldUser.get();
		user5.setCity("chennai");
		user5.setName("Mahi");
		User newUser = userRepository.save(user5);
		System.out.println(newUser);

		// Read / Fetch data
		Iterable<User> all = userRepository.findAll();
		all.forEach(u-> System.out.println(u));

		//Delete
		userRepository.deleteById(3);
		System.out.println("DELETED");

	}

}
