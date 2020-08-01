package com.quest.vms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quest.vms.entity.Visitor;
import com.quest.vms.repository.VisitorRepository;

@Service
public class VisitorDao implements IVisitorDao {

	@Autowired
	VisitorRepository visotorRepository;

	@Override
	public Visitor save(Visitor visitor) {
		return visotorRepository.save(visitor);
	}

	@Override
	public void delete(Visitor visitor) {
		visotorRepository.delete(visitor);
	}

	@Override
	public Visitor getVisitorById(Integer id) {
		Visitor vis = null;
		Optional<Visitor> visitor = visotorRepository.findById(id);
		if (visitor.isPresent()) {
			vis = Visitor.builder().id(visitor.get().getId()).firstName(visitor.get().getFirstName())
					.lastName(visitor.get().getLastName()).contactNo(visitor.get().getContactNo())
					.email(visitor.get().getEmail()).placeOfVisit(visitor.get().getPlaceOfVisit())
					.idProof(visitor.get().getIdProof()).reasonForVisit(visitor.get().getReasonForVisit())
					.createdOn(visitor.get().getCreatedOn()).contactPersons(visitor.get().getContactPersons())
					.devices(visitor.get().getDevices()).timeSlots(visitor.get().getTimeSlots()).build();
		}
		return vis;
	}

	@Override
	public List<Visitor> listVisitors(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Visitor> pagedResult = visotorRepository.findAll(paging);
		return pagedResult.toList();
	}
}
