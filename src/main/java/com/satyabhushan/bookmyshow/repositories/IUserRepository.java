package com.satyabhushan.bookmyshow.repositories;

import com.satyabhushan.bookmyshow.models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);
    @Query("SELECT u.role FROM User u WHERE u.email = ?1")
    String findRoleByEmail(String email);
}
