package com.api.testes.application.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.testes.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
}
