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
	public static final String VISITOR_TYPE = "visitor_type";
	public static final String CREATED_TS = "created_ts";
	public static final String UPDATED_TS = "updated_ts";
	public static final String FK_VISITS = "visits";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = VISITOR_ID)
	private Integer visitorId;

	@Column(name = FIRST_NAME, nullable = false)
	private String firstName;

	@Column(name = LAST_NAME, nullable = false)
	private String lastName;

	@Column(name = EMAIL, nullable = false)
	private String email;

	@Column(name = CONTACT_NUMBER, length = 15, nullable = false)
	private String contactNo;

	@Column(name = ID_PROOF, nullable = false)
	private String idProof;

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
