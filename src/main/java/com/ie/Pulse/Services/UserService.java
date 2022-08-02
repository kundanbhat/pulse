package com.ie.Pulse.Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.Users;
import com.ie.Pulse.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	
	
	public  List<Users>  getUsersByEmail(String email)
	{
		return repo.findByUserName(email);
	}
	public void save(Users user)
	{
		repo.save(user);
	}
	public List<Users> getAllUsers()
	{
		return repo.findAll();
	}
	
	public Users getUser(long id)
	{
		return repo.findById(id).get();
	}
	public void delete(Users user)
	{
		repo.delete(user);
	}
}
