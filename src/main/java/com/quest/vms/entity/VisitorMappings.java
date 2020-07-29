package com.quest.vms.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "visitor_mappings")
@Data
public class VisitorMappings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id;	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="visitorId",referencedColumnName="id")
 	private Visitor visitorId;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="deviceId",referencedColumnName="id")
 	private Device deviceId;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="timeslotId",referencedColumnName="id")
 	private TimeSlot timeslotId;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="contactPersonId",referencedColumnName="id")
 	private ContactPerson contactPersonId;
	
	 
		
	
}
