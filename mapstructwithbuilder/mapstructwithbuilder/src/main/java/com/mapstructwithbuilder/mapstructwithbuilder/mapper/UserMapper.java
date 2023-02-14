package com.mapstructwithbuilder.mapstructwithbuilder.mapper;

import com.mapstructwithbuilder.mapstructwithbuilder.model.User;
import com.mapstructwithbuilder.mapstructwithbuilder.request.UserRequest;
import com.mapstructwithbuilder.mapstructwithbuilder.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User.Builder userRequestToUser(UserRequest userRequest);

    UserResponse userToUserResponse(User user);
}
