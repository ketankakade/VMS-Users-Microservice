package com.quest.vms.dao;

import com.quest.vms.entity.Visitor;

public interface IUserDao {

	public Visitor save(Visitor user);
	public Visitor getUserById(long id);
	//public User update(int id, User user) throws InternalServerError;
	public void delete(Visitor user);
	
}
