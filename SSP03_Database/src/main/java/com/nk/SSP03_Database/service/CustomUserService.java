package com.nk.SSP03_Database.service;



import com.nk.SSP03_Database.entity.UserEntity;
import com.nk.SSP03_Database.repo.UserRepo;
import com.nk.SSP03_Database.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not found"));
        return User.withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .roles(userEntity.getRole())
                .build();
    }
}
