package com.mapstructwithkotlin.mapstructWithKotlin.mapper;

import com.mapstructwithkotlin.mapstructWithKotlin.entities.User;
import com.mapstructwithkotlin.mapstructWithKotlin.entities.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserMapper {
//    @Mapping(target = "password", ignore = true)
//    @Mapping(source = "username", target = "dtoName")
//    @Mapping(source = "useremail", target = "dtoEmail")
//    UserDto userDtoFromUser(User user);
//
//    @InheritInverseConfiguration(name = "userDtoFromUser")
//    User toUser(UserDto userDto);
//
//
//    List<UserDto> userDtoListFromUserList(List<User> users);

}
