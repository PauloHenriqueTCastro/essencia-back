package com.essencia.essencia.validator;

import com.essencia.essencia.exceptions.DuplicatedRecordException;
import com.essencia.essencia.model.Users;
import com.essencia.essencia.repository.UsersRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersValidator {
    private final UsersRepository usersRepository;

    public UsersValidator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void validator(Users users) {
        if (usersExists(users)) {
            throw new DuplicatedRecordException("Usuário já cadastrado");
        }
    }

    public boolean usersExists(Users users) {
        Optional<Users> usersFound = usersRepository.findByEmail(users.getEmail());

        if (users.getId() == null) {
            return usersFound.isPresent();
        }
        return !users.getId().equals(usersFound.get().getId());

    }
}
