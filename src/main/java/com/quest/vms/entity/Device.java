package com.quest.vms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "devices")
@Data
public class Device {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deviceId;
	
	private String deviceSN;
	
	private String deviceType;
	
	private String deviceMake;
	
//	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name="id",referencedColumnName="deviceId")
// 	private VisitorMappings deviceId;
	
}
