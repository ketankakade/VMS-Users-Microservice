package com.quest.vms.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.quest.vms.entity.Visitor;

@Repository
public interface VisitorRepository
		extends JpaRepository<Visitor, Integer>, PagingAndSortingRepository<Visitor, Integer> {

	public Visitor findByEmailIgnoreCase(String email);

	@Query("select u from Visitor u JOIN u.visits v JOIN v.contactPerson cp JOIN v.timeSlot ts where "
			+ "u.firstName=?1 and v.approvalStatus=?2 and cp.firstName=?3 and ts.startDate=?4 and ts.endDate=?5")
	public List<Visitor> findByFilter(String visitorName, String visitType,	String contactPersonName, LocalDate startDate, LocalDate endDate);

	@Query("SELECT v FROM Visitor v JOIN v.visits visit WHERE visit.visitDate = CURRENT_DATE")
	List<Visitor> findTodaysVisitor();
}