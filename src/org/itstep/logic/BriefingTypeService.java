package org.itstep.logic;

import java.util.List;

import org.itstep.domain.Workflowjournal.BriefingType;


public interface BriefingTypeService {
	
	List<BriefingType> findAll() throws LogicException;

	void save(BriefingType briefingType) throws LogicException;

	void delete(Long id) throws LogicException;
}
