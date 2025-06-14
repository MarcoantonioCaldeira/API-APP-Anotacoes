package com.apinote.model.repository;

import com.apinote.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT email FROM User u where u.email =:email")
    Optional<User> findByEmail(@Param("email") String email);

    UserDetails findByLogin(String login);
}
