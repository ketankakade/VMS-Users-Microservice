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
	public static final String CONTACT_PERSON = "contact_person";
	public static final String TIME_SLOT = "time_slot";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = VISIT_ID)
	private Integer visitId;

	@Column(name = VISIT_DATE)
	private Date visitDate;

	@OneToOne(cascade = CascadeType.ALL)
	private ContactPerson contactPerson;

	@OneToOne(cascade = CascadeType.ALL)
	private TimeSlot timeSlot;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "visits_devices", joinColumns = @JoinColumn(name = "visit_id"), inverseJoinColumns = @JoinColumn(name = "device_id"))
	private List<Device> devices;

}
