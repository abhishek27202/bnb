package com.airbnb.bnb.Controller;

import com.airbnb.bnb.Entity.AppUser;
import com.airbnb.bnb.Exception.UserExists;
import com.airbnb.bnb.Repository.AppUserRepository;
import com.airbnb.bnb.Service.AppUserServiceimpl;
import com.airbnb.bnb.payload.JWTToken;
import com.airbnb.bnb.payload.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AppUserRepository appUserRepository;
    private AppUserServiceimpl appUserService;
    @Autowired
    public AuthController(AppUserRepository appUserRepository, AppUserServiceimpl appUserService) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
    }

@PostMapping("/createuser")
    public ResponseEntity<AppUser> create(
            @RequestBody AppUser user) {

        Optional<AppUser> opUserName = appUserRepository.findByUsernameOrEmail(user.getUsername(),user.getEmail());
        if (opUserName.isPresent()) {
            throw new UserExists("Username Exists");
        }
        user.setRole("ROLE_USER");
        AppUser savedUser =appUserService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> signIn(
            @RequestBody LoginDto loginDto
    ){
      String token =  appUserService.verifyLogin(loginDto);
       JWTToken jwtToken = new JWTToken();
        if(token != null){
         jwtToken.setTokenType("JWT");
         jwtToken.setToken(token);
         return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/createpropertyuser")
    public String createpropertyuser(){
        return "created";

    }
}


