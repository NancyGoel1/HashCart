package com.example.hashcartapp.repository;

import com.example.hashcartapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //public User getUserByUserName(@Param("email") String email);

}
