package com.essencia.essencia.controller;

import com.essencia.essencia.controller.DTO.users.LoginDTO;
import com.essencia.essencia.controller.DTO.users.ResponseLoginDTO;
import com.essencia.essencia.controller.DTO.users.CreateUsersDTO;
import com.essencia.essencia.controller.mappers.UsersMapper;
import com.essencia.essencia.model.Users;
import com.essencia.essencia.service.AuthenticationService;
import com.essencia.essencia.service.TokenService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final TokenService tokenService;
    private final UsersMapper usersMapper;

    public AuthenticationController(AuthenticationManager authenticationManager, AuthenticationService authenticationService, TokenService tokenService, UsersMapper usersMapper) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.tokenService = tokenService;
        this.usersMapper = usersMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDTO data) {
        var usernamePassWord = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassWord);
        var token = tokenService.generateToken((Users) auth.getPrincipal());
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + token)
                .body(new ResponseLoginDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid CreateUsersDTO createUsersDTO) {

        Users usersEntity = usersMapper.toEntity(createUsersDTO);
        authenticationService.register(usersEntity);
        return new ResponseEntity<Object>("Usuario criado com sucesso!", HttpStatus.CREATED);


    }
}
