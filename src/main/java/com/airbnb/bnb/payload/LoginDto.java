package com.airbnb.bnb.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String username;
    private String email;
    private String password;

}
