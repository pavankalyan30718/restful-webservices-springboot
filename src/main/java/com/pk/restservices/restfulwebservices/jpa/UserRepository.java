package com.pk.restservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pk.restservices.restfulwebservices.user.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	

}
