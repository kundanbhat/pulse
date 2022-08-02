package com.ie.Pulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ie.Pulse.entity.Users;

public interface UserRepo extends JpaRepository<Users, Long>{
	
	
	public List<Users> findByUserName(String email);
	

}
