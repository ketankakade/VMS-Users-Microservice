package com.quest.vms.dao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.quest.vms.dto.VisitorDto;
import com.quest.vms.entity.Visitor;

public interface VisitorDao {

	public VisitorDto addVisitor(final VisitorDto visitor);

	public VisitorDto getVisitorById(final Integer id);

	public void delete(final Visitor visitor);
	
	public VisitorDto update(final VisitorDto visitorDto);

	public List<VisitorDto> listVisitors(String pageNo, String pageSize, String sortProperty);

}
