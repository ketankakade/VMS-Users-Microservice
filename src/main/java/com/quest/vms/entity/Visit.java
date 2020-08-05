package com.quest.vms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer visitId;

	private Date visitDate;

	@OneToOne(cascade = CascadeType.ALL)
	private ContactPerson contactPerson;

	@OneToOne(cascade = CascadeType.ALL)
	private TimeSlot timeSlot;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "visits_device", joinColumns = @JoinColumn(name = "visitId"), inverseJoinColumns = @JoinColumn(name = "deviceId"))
	private List<Device> devices;

}
