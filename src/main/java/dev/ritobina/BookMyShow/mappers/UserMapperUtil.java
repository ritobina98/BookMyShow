package dev.ritobina.BookMyShow.mappers;

import dev.ritobina.BookMyShow.dtos.UserLoginRespDTO;
import dev.ritobina.BookMyShow.dtos.UserSignUpReqDTO;
import dev.ritobina.BookMyShow.models.User;

public class UserMapperUtil {

    public static User dtoToModel(UserSignUpReqDTO dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserLoginRespDTO modelToDto(User user){
        UserLoginRespDTO dto = new UserLoginRespDTO();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }
}
