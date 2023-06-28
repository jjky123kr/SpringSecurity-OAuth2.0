package com.cos.security1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security1.Model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	//findBy 규칙 -> Username 문법
	// select*from user where username=?
	public User findByUsername(String username);
}
