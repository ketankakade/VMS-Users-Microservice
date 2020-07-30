package com.quest.vms.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "visitors")
@Data
public class Visitor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String firstName;

	private String lastName;

	@NotNull
	@Column(unique = true)
	@Email
	private String email;

	@NotNull
	private long contactNo;

	@NotNull
	private String idProof;

	@NotNull
	private String placeOfVisit;

	@NotNull
	private String reasonForVisit;

	@NotNull
	private String visitorType;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updatedOn;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "visitors_contactPerson", joinColumns = @JoinColumn(name = "visitorId"), inverseJoinColumns = @JoinColumn(name = "contactPersonId"))
	private Set<ContactPerson> contactPersons;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "visitors_device", joinColumns = @JoinColumn(name = "visitorId"), inverseJoinColumns = @JoinColumn(name = "deviceId"))
	private Set<Device> devices;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "visitors_timeSlots", joinColumns = @JoinColumn(name = "visitorId"), inverseJoinColumns = @JoinColumn(name = "timeSlotId"))
	private Set<TimeSlot> timeSlots;
}
