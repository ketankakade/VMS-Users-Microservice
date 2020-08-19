package com.quest.vms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quest.vms.entity.Visitor;

@Repository
public interface VisitorRepository
		extends JpaRepository<Visitor, Integer>, PagingAndSortingRepository<Visitor, Integer> {

	public Visitor findByEmailIgnoreCase(String email);

	@Query("select v from Visitor v join v.visits visits join visits.contactPerson cp where "
			+ " ( v.firstName=?1 or ?1 is NULL or ?1 = '' )  and "
			+ " ( visits.approvalStatus=?2 or ?2 is NULL or ?2 = '' ) and "
			+ " ( cp.firstName=?3 or ?3 is NULL or ?3 = '' ) ")
	public List<Visitor> findByFilter(@Param("firstName") String firstName,
			@Param("approvalStatus") String approvalStatus, @Param("firstName") String fName);

	@Query("SELECT v FROM Visitor v JOIN v.visits visit WHERE visit.visitDate = CURRENT_DATE")
	List<Visitor> findTodaysVisitor();
}