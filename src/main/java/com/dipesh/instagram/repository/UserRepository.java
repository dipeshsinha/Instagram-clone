package com.dipesh.instagram.repository;

import com.dipesh.instagram.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    @Query("select u from User u where u.firstName like %:query% or u.lastName like %:query% or u.email like %:query%")
    List<User> searchUser(@Param("query") String query);
}
