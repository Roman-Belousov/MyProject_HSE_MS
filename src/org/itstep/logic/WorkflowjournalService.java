package org.itstep.logic;

import java.util.List;
import org.itstep.domain.Workflowjournal.Workflowjournal;

public interface WorkflowjournalService {

	List <Workflowjournal> findAll() throws LogicException;
	
	Workflowjournal findById(Long id) throws LogicException;
	
	void save(Workflowjournal workflowjournal) throws LogicException;
	
	void delete(List<Long> ids) throws LogicException;


	
}
