package dev.ritobina.BookMyShow.controllers;

import dev.ritobina.BookMyShow.dtos.UserLoginRespDTO;
import dev.ritobina.BookMyShow.dtos.UserSignUpReqDTO;
import dev.ritobina.BookMyShow.mappers.UserMapperUtil;
import dev.ritobina.BookMyShow.models.User;
import dev.ritobina.BookMyShow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody UserSignUpReqDTO userReqDTO){
        User user = UserMapperUtil.dtoToModel(userReqDTO);
        User savedUser = userService.createUser(user);
        UserLoginRespDTO respDTO = UserMapperUtil.modelToDto(savedUser);
        return ResponseEntity.ok(respDTO);

    }
}
