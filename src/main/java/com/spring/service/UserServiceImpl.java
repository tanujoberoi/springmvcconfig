package com.spring.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.User;
import com.spring.repos.UserRepo;

@Service
@Transactional
public class UserServiceImpl { 

	
	@Autowired
	UserRepo repo;
	
	public User save(com.spring.model.User user) {
		return repo.save(user);
	}
	
	
	public User findUserByUsername(String username){
		return repo.findByUsername(username);
	}
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findUserById(Long id) {
		return repo.findOne(id);
	}
	
	public User login(String email, String password) {
		//return userDao.login(email,password);
		//return userRepository.login(email,password);
		return repo.login(email, password);
	}
	
	public User update(User user) {
		return repo.save(user);
	}

	public void deleteUser(Long id) {
		repo.delete(id);
	}
}
