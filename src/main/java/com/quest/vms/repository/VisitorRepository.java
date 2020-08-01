package com.quest.vms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.quest.vms.entity.Visitor;

@Repository
public interface VisitorRepository
		extends JpaRepository<Visitor, Integer>, PagingAndSortingRepository<Visitor, Integer> {
}