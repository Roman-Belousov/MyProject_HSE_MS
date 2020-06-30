package org.itstep.logic;

import java.util.List;
import org.itstep.domain.EmployeeCard.EmployeeCard;

public interface CardService {

	List <EmployeeCard> findAll() throws LogicException;
	
	EmployeeCard findById(Long id) throws LogicException;
	
	void save(EmployeeCard employeecard) throws LogicException;
	
	void delete(List<Long> ids) throws LogicException;


	
}
