package com.quest.vms.dao;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.quest.vms.dto.VisitorDTO;
import com.quest.vms.dto.VisitorsCountDTO;
import com.quest.vms.entity.Visitor;

public interface VisitorDAO {

	public VisitorDTO addVisitor(final VisitorDTO visitor);

	public VisitorDTO getVisitorById(final Integer id);

	public void delete(final Visitor visitor);

	public VisitorDTO update(VisitorDTO visitorDto);

	public List<VisitorDTO> listVisitors(final String pageNo, final String pageSize, final String sortProperty,
			Direction orderBy);

	public VisitorsCountDTO listVisitorsCount();

	public List<VisitorDTO> searchVisitor(String visitorType, String startDate, String endDate, String visitorName,
			String contactPersonName, String isActive);

}
