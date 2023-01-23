package com.api.testes.application.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.testes.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    @Query(nativeQuery = true, value = "select * from users where email = :email")
    Optional<User> findByEmail(String email);

}
