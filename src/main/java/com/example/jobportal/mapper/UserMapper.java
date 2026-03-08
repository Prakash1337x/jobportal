package com.example.jobportal.mapper;

import com.example.jobportal.dto.UserRegisterDTO;
import com.example.jobportal.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRegisterDTO userRegisterDTO);
    UserRegisterDTO toDTO(User toUser);
}
