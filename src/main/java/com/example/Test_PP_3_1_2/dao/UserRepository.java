package com.example.Test_PP_3_1_2.dao;

import com.example.Test_PP_3_1_2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByEmail(String email);
    User save(User user);
    Optional<User> findById(Long id);
    void deleteById(Long id);
}