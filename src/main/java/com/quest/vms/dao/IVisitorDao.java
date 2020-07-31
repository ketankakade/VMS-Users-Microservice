package com.quest.vms.dao;

import com.quest.vms.entity.Visitor;

public interface IVisitorDao {

	public Visitor save(Visitor visitor);
	public Visitor getVisitorById(long id);
	//public Visitor update(int id, Visitor visitor) throws InternalServerError;
	public void delete(Visitor visitor);
	
}
