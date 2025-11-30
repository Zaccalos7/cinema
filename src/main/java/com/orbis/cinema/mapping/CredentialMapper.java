package com.orbis.cinema.mapping;

import com.orbis.cinema.dto.CredentialDto;
import com.orbis.cinema.model.Credential;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CredentialMapper extends BaseMapper<CredentialDto, Credential>{

}
