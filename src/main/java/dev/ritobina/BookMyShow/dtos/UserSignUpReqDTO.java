package dev.ritobina.BookMyShow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpReqDTO {
    private String name;
    private String email;
    private String password;
}
