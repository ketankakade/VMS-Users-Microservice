package com.quest.vms.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "visitors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {

	public static final String VISITOR_ID = "visitor_id";
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	public static final String EMAIL = "email";
	public static final String CONTACT_NUMBER = "contact_number";
	public static final String ID_PROOF = "id_proof";
	public static final String PROOF_OF_VISIT = "proof_of_visit";
	public static final String REASON_FOR_VISIT = "reason_for_visit";
	public static final String VISITOR_TYPE = "visitor_type";
	public static final String CREATED_TS = "created_ts";
	public static final String UPDATED_TS = "updated_ts";
	public static final String FK_VISITS = "visits";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = VISITOR_ID)
	private Integer visitorId;

	@NotNull
	@Column(name = FIRST_NAME)
	private String firstName;

	@NotNull
	@Column(name = LAST_NAME)
	private String lastName;

	@NotNull
	@Email
	@Column(name = EMAIL)
	private String email;

	@NotNull
	@Column(name = CONTACT_NUMBER)
	private long contactNo;

	@NotNull
	@Column(name = ID_PROOF)
	private String idProof;

	@NotNull
	@Column(name = PROOF_OF_VISIT)
	private String placeOfVisit;

	@NotNull
	@Column(name = REASON_FOR_VISIT)
	private String reasonForVisit;

	@NotNull
	@Column(name = VISITOR_TYPE)
	private String visitorType;

	@CreationTimestamp
	@Column(name = CREATED_TS)
	private Timestamp createdTs;

	@UpdateTimestamp
	@Column(name = UPDATED_TS)
	private Timestamp updatedTs;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = FK_VISITS, nullable = false)
	private List<Visit> visits;

}
