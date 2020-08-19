package com.quest.vms.repository;

import java.time.LocalDate;
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

	@Query("select v from Visitor v join v.visits visits join visits.contactPerson cp join visits.timeSlot ts where "
			+ " ( v.firstName=?1 or ?1 is NULL or ?1 = '' )  and "
			+ " ( visits.approvalStatus=?2 or ?2 is NULL or ?2 = '' ) and "
			+ " ( cp.firstName=?3 or ?3 is NULL or ?3 = '' ) and "
			+ " ( ts.startDate=?4 or ?4 is NULL or ?4 = '' ) and " 
			+ " ( ts.endDate=?5 or ?5 is NULL or ?5 = '' ) ")
	public List<Visitor> findByFilter(@Param("firstName") String firstName,
			@Param("approvalStatus") String approvalStatus, @Param("firstName") String fName,
			@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	@Query("SELECT v FROM Visitor v JOIN v.visits visit WHERE visit.visitDate = CURRENT_DATE")
	List<Visitor> findTodaysVisitor();
}