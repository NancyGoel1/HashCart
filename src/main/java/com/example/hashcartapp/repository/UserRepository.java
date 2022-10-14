package com.example.Demo_func1.repository;

import com.example.Demo_func1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u where u.email= :email")
    public User getUserByUserName(@Param("email") String email);

}
