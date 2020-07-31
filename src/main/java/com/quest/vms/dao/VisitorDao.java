package com.quest.vms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.vms.entity.Visitor;
import com.quest.vms.repository.VisitorRepository;

@Service
public class VisitorDao implements IVisitorDao {
	
	@Autowired
	VisitorRepository visitorRepo;
	
	@Override
	public Visitor save(Visitor visitor){
		return visitorRepo.save(visitor);
	}

	@Override
	public Visitor getVisitorById(long id) {
		return visitorRepo.findById(id);
	}

//	@Override
//	public User update(int id, User user) throws InternalServerError {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void delete(Visitor visitor) {
		visitorRepo.delete(visitor);
	}	
}
