package com.essencia.essencia.controller.mappers;

import com.essencia.essencia.controller.DTO.users.CreateUsersDTO;
import com.essencia.essencia.model.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    Users toEntity(CreateUsersDTO dto);

    CreateUsersDTO toDTO(Users users);

}
