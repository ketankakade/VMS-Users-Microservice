package com.quest.vms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Table(name = "visitor_contact_person")
@Data
public class ContactPerson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotNull
	//@Size(max=10,min=10)
	private long contactNo;

	@NotBlank
	@Email
	@Column(name = "email")
	private String email;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="id",referencedColumnName="contactPersonId")
 	private VisitorMappings contactPersonId;
	
//	@JsonIgnore
//	@ManyToOne(cascade = CascadeType.ALL)
//	private User user;
}
