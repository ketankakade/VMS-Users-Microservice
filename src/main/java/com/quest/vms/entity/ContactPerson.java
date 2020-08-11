package com.quest.vms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contactperson")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactPerson {

	public static final String CONTACT_PERSON_ID = "contact_person_id";
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	public static final String EMAIL = "email";
	public static final String CONTACT_NUMBER = "contact_number";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = CONTACT_PERSON_ID)
	private Integer contactPersonId;

	@Column(name = FIRST_NAME)
	private String firstName;

	@Column(name = LAST_NAME)
	private String lastName;

	@Column(name = CONTACT_NUMBER)
	private String contactNo;

	@Column(name = "email")
	private String email;

}
