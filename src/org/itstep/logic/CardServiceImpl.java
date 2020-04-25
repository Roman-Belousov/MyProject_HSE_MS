package org.itstep.logic;

import java.util.List;
import org.itstep.domain.EmployeeCard.EmployeeCard;
import org.itstep.postgres.DaoException;
import org.itstep.postgres.EmployeeCardDao;


public class CardServiceImpl implements CardService {
	private EmployeeCardDao employeecardDao;

	public void setCardDao(EmployeeCardDao employeecardDao) {
		this.employeecardDao = employeecardDao;
	}

	@Override
	public List<EmployeeCard> findAll() throws LogicException {
		try {
			List<EmployeeCard> employeecards = employeecardDao.read();
			return employeecards;
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void save(EmployeeCard employeecard) throws LogicException {
		try {
			if(employeecard.getId() == null) {
				Long id = employeecardDao.create(employeecard);
				employeecard.setId(id);
			} else {
				employeecardDao.update(employeecard);
			}
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void delete(Long id) throws LogicException{
		try {
			employeecardDao.delete(id);
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	
	}
	
}

	


