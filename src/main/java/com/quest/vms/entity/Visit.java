package com.quest.vms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "visit")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visit {
	
	public static final String VISIT_ID = "visit_id";
	public static final String VISIT_DATE = "visit_date";
	public static final String CAME_FROM = "cameFrom";
	public static final String PASS_NUMBER = "passNumber";
	public static final String CONTACT_PERSON = "contact_person";
	public static final String TIME_SLOT = "time_slot";
	public static final String APPROVALSTATUS = "approvalStatus";
	public static final String ISVISITCOMPLETED = "isVisitCompleted";
	public static final String PLACE_OF_VISIT = "place_of_visit";
	public static final String REASON_FOR_VISIT = "reason_for_visit";	

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = VISIT_ID)
	private Integer visitId;

	@Column(name = VISIT_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date visitDate;
	
	@Column(name = APPROVALSTATUS)
	private String approvalStatus;
	
	@Column(name = ISVISITCOMPLETED)
	private Boolean isVisitCompleted;
	
	@Column(name = PLACE_OF_VISIT)
	private String placeOfVisit;
	
	@Column(name = CAME_FROM)
	private String cameFrom;

	@Column(name = PASS_NUMBER)
	private String passNumber;

	@Column(name = REASON_FOR_VISIT, nullable = false)
	private String reasonForVisit;

	@OneToOne(cascade = CascadeType.ALL)
	private ContactPerson contactPerson;

	@OneToOne(cascade = CascadeType.ALL)
	private TimeSlot timeSlot;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "visits_devices", joinColumns = @JoinColumn(name = "visit_id"), inverseJoinColumns = @JoinColumn(name = "device_id"))
	private List<Device> devices;

}
