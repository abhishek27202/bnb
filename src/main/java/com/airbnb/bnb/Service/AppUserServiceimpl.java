package com.airbnb.bnb.Service;

import com.airbnb.bnb.Entity.AppUser;
import com.airbnb.bnb.Repository.AppUserRepository;
import com.airbnb.bnb.payload.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceimpl {
    private AppUserRepository appUserRepository;
    private JWTService jwTService;
   @Autowired
    public AppUserServiceimpl(AppUserRepository appUserRepository , JWTService jwTService) {
       this.appUserRepository = appUserRepository;
       this.jwTService = jwTService;
    }
    public  AppUser createUser(
            AppUser user
    ){
       String hashpw= BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12));
        user.setPassword( hashpw);
        return appUserRepository.save(user);
    }


    public String verifyLogin(LoginDto loginDto) {
        Optional<AppUser> opUser = appUserRepository.findByUsernameOrEmail(loginDto.getUsername(),loginDto.getEmail());
        if (opUser.isPresent()) {
            AppUser appUser = opUser.get(); // Retrieve the AppUser object
           if (BCrypt.checkpw(loginDto.getPassword(), appUser.getPassword())){
               return jwTService.generateToken(appUser);
            }

        }
       return null;
    }
}
