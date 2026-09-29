package br.com.example.hexagon_example.adapter.out.persistence.mapper;

import br.com.example.hexagon_example.adapter.out.persistence.entity.UserEntity;
import br.com.example.hexagon_example.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);

}
