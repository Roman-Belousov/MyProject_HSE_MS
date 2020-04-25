package org.itstep.storage;

import java.util.ArrayList;
import java.util.List;
import org.itstep.domain.EmployeeCard.EmployeeCard;
import org.itstep.postgres.DaoException;
import org.itstep.postgres.EmployeeCardDao;

public class CardMemoryDaoImpl implements EmployeeCardDao {
	private List<EmployeeCard> employeecards = new ArrayList<>();	
	private Long lastCreatedId = 0L;

	@Override
	public Long create(EmployeeCard employeecard) {
		lastCreatedId++;
		employeecard.setId(lastCreatedId);
		employeecards.add(employeecard);
		return lastCreatedId;
	}
	@Override
	public EmployeeCard read(Long id) {
		for(int i = 0; i < employeecards.size(); i++) {
			if(employeecards.get(i).getId().equals(id)) {
				return employeecards.get(i);
			}
		}
		return null;
	}
	
	@Override
	public List<EmployeeCard> read () {
		return employeecards;
	}
	
	@Override
	public void update(EmployeeCard employeecard) throws DaoException {
		for(int i = 0; i < employeecards.size(); i++) {
			if(employeecards.get(i).getId().equals(employeecard.getId())) {
				employeecards.set(i, employeecard);
				return;
			}
		}
		throw new DaoException();
	}

	@Override
	public void delete(Long id) {
		for(int i = 0; i < employeecards.size(); i++) {
			if(employeecards.get(i).getId().equals(id)) {
				employeecards.remove(i);
				break;
			}
		}
	}
}
	