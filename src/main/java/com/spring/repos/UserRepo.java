package com.spring.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.model.User;


public interface UserRepo extends JpaRepository<User, Long> {
	
	@Query("select u from User u where u.username=?1 and u.password=?2")
	User login(String email, String password);
	
	User findByUsername(String username);
 
}
