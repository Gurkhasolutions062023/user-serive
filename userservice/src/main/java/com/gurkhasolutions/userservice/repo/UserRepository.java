package com.gurkhasolutions.userservice.repo;

import com.gurkhasolutions.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findUserByLastName(String lastName);
    @Query(value = "select * from user where user.role= ?",nativeQuery = true)
    List<User> findUserWithRole(String role);
}
