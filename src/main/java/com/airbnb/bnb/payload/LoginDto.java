package com.airbnb.bnb.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String Username;
    private String Email;
    private String Password;

}
