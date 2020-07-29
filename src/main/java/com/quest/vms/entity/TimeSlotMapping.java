package com.quest.vms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "visitor_timeslots")
@Data

public class TimeSlotMapping {

	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="visitorId",referencedColumnName="id")
 	private Visitor visitorId;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="timeslotId",referencedColumnName="id")
 	private TimeSlot timeslotId;
}
