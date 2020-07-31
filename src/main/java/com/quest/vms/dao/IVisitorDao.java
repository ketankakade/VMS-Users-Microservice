package com.quest.vms.dao;

import java.util.List;

import com.quest.vms.entity.Visitor;

public interface IVisitorDao {

	public Visitor save(Visitor visitor);

	public Visitor getVisitorById(Integer id);

	public void delete(Visitor visitor);

	public List<Visitor> listVisitors();

}
