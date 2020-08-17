package com.quest.vms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timeslots")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlot {
	
	public static final String SLOT_ID = "timeslot_id";
	public static final String START_TIME = "start_time";
	public static final String END_TIME = "end_time";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = SLOT_ID)
	private Integer timeSlotId;

	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate endDate;

}
