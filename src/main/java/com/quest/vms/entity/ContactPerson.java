package com.quest.vms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contactpersonid;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotNull
	private long contactNo;

	@NotBlank
	@Email
	@Column(name = "email")
	private String email;

}
