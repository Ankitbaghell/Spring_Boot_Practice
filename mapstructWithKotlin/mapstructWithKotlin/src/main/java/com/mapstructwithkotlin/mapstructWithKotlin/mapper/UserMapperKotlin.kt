package com.mapstructwithkotlin.mapstructWithKotlin.mapper

import com.mapstructwithkotlin.mapstructWithKotlin.entities.User
import com.mapstructwithkotlin.mapstructWithKotlin.entities.UserDto
import org.mapstruct.*

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
 interface UserMapperKotlin {

    @Mapping(source = "username", target = "dtoName")
    @Mapping(source = "useremail", target = "dtoEmail")
    fun userDtoFromUser(user : User) : UserDto

    @InheritInverseConfiguration(name = "userDtoFromUser")
    fun toUser(userDto: UserDto) : User

    fun userDtoListFromUserList(users: List<User>): List<UserDto>


}
