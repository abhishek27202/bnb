package com.airbnb.bnb.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTToken {
    private String token;
    private String tokenType;
}
