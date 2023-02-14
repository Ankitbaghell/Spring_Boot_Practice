package com.mapstructwithkotlin.mapstructWithKotlin;

import com.mapstructwithkotlin.mapstructWithKotlin.entities.Student;
import com.mapstructwithkotlin.mapstructWithKotlin.entities.User;
import com.mapstructwithkotlin.mapstructWithKotlin.entities.UserDto;
import com.mapstructwithkotlin.mapstructWithKotlin.mapper.UserMapper;
import com.mapstructwithkotlin.mapstructWithKotlin.mapper.UserMapperKotlin;
import com.mapstructwithkotlin.mapstructWithKotlin.mapper.UserMapperKotlinImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MapstructWithKotlinApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapstructWithKotlinApplication.class, args);

		User user = new User(1, "Ankit", "ankit@gmail.com", 21);
//		UserMapper userMapper = new UserMapperImpl();
		UserMapperKotlin userMapper = new UserMapperKotlinImpl();

		UserDto userDto = userMapper.userDtoFromUser(user);

		System.out.println(userDto);

		UserDto userDto1 = new UserDto(2, "virat", "virat@gmail.com", 34,"ram","this is so good");
		User user1 = userMapper.toUser(userDto1);
		System.out.println(user1);

		User user2 = new User(2, "Sumit", "sumit@gmail.com", 34);
		User user3 = new User(3, "bassi", "bassi@gmail.com", 65);
		User user4 = new User(4, "harsh", "hasrh@gmail.com", 12);

		List<User> list = List.of(user2, user3, user4);

		List<UserDto> userDtos = userMapper.userDtoListFromUserList(list);
		System.out.println(userDtos);

//		val foodOrder = FoodOrder.Builder()
//				.bread("white bread")
//				.meat("bacon")
//				.condiments("olive oil")
//				.build()

	}


}
