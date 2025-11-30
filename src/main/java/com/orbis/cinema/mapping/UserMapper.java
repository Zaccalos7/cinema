package com.orbis.cinema.mapping;

import com.orbis.cinema.dto.UserDto;
import com.orbis.cinema.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDto, User>{
}
