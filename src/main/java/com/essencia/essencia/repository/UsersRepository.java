package com.essencia.essencia.repository;

import com.essencia.essencia.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {


    Optional<Users> findByEmail(String email);

}
