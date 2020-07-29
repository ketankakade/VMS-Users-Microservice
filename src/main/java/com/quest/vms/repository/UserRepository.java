package com.quest.vms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.vms.entity.Visitor;

public interface UserRepository extends JpaRepository<Visitor,Long> {

	public Visitor findById(long id);
}
