package org.itstep.postgres;

import java.util.List;

import org.itstep.domain.EmployeeCard.EmployeeCard;

public interface EmployeeCardDao extends Dao<EmployeeCard> {
	List<EmployeeCard> read() throws DaoException;
}
