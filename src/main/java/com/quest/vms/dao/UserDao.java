package com.quest.vms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.vms.entity.Visitor;
import com.quest.vms.repository.UserRepository;

@Service
public class UserDao implements IUserDao {
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public Visitor save(Visitor user){
		return userRepo.save(user);
	}

	@Override
	public Visitor getUserById(long id) {
		return userRepo.findById(id);
	}

//	@Override
//	public User update(int id, User user) throws InternalServerError {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void delete(Visitor user) {
		userRepo.delete(user);
	}	
}
