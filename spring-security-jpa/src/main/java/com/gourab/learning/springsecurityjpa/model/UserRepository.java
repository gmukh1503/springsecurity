package com.gourab.learning.springsecurityjpa.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
	Optional<User> findUserByName(String userName);
}
