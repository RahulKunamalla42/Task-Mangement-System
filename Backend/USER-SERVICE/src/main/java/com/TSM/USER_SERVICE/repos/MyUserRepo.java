package com.TSM.USER_SERVICE.repos;

import com.TSM.USER_SERVICE.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepo extends JpaRepository<MyUser,String> {
    Optional<MyUser> findByUsername(String username);
}
