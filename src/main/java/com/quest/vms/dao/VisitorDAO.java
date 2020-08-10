package com.quest.vms.dao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.quest.vms.dto.VisitorDTO;
import com.quest.vms.entity.Visitor;

public interface VisitorDAO {

	public VisitorDTO addVisitor(final VisitorDTO visitor);

	public VisitorDTO getVisitorById(final Integer id);

	public void delete(final Visitor visitor);
	
	public VisitorDTO update(final VisitorDTO visitorDto);

	public List<VisitorDTO> listVisitors(String pageNo, String pageSize, String sortProperty);

}
