package com.airbnb.bnb.Service;



import com.airbnb.bnb.Entity.AppUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLOutput;
import java.util.Date;



@Service
public class JWTService {

    @Value("${jwt.algorithms.key}")
    private String algorithmsKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiry.duration}")
    private int  expiryTime;
    private Algorithm algorithm;


    @PostConstruct    // work on header part of the token
   public void postConstruct() throws UnsupportedEncodingException {
        algorithm= Algorithm.HMAC256( algorithmsKey);
    }
    public String generateToken(AppUser user){
              return JWT.create()
                        .withClaim("username", user.getUsername())  //this information is goes to paylod of data
                        .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))//
                        .withIssuer(issuer) //who is issuing this detail
                        .sign(algorithm); // which algorithm is used

    }



}

