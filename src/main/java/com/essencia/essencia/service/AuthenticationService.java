package com.essencia.essencia.service;

import com.essencia.essencia.model.Users;
import com.essencia.essencia.repository.UsersRepository;
import com.essencia.essencia.validator.UsersValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {


   private final UsersRepository usersRepository;
    private final UsersValidator usersValidator;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UsersRepository usersRepository, UsersValidator usersValidator, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.usersValidator = usersValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com e-mail: " + username));
    }

    public void register (Users users){
        String encryptedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(encryptedPassword);
        usersValidator.validator(users);
        usersRepository.save(users);
    }
}
