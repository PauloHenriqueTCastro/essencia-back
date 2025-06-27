package com.essencia.essencia.service;

import com.essencia.essencia.model.Users;
import com.essencia.essencia.repository.UsersRepository;
import com.essencia.essencia.validator.UsersValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersValidator usersValidator;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository repository, UsersValidator validator, PasswordEncoder passwordEncoder){
        this.usersRepository = repository;
        this.usersValidator = validator;
        this.passwordEncoder = passwordEncoder;
    }

}
