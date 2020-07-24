package org.itstep.logic;

import java.util.List;

import org.itstep.domain.Order.OrderType;
import org.itstep.postgres.DaoException;
import org.itstep.postgres.OrderTypeDao;



public class OrderTypeServiceImpl implements OrderTypeService {
	private OrderTypeDao ordertypeDao;

	public void setOrderTypeDao(OrderTypeDao ordertypeDao) {
		this.ordertypeDao = ordertypeDao;
	}

	@Override
	public List<OrderType> findAll() throws LogicException {
		try {
			return ordertypeDao.read();
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void save(OrderType ordertype) throws LogicException {
		try {
			if(ordertype.getId() == null) {
				Long id = ordertypeDao.create(ordertype);
				ordertype.setId(id);
			} else {
				ordertypeDao.update(ordertype);
			}
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void delete(Long id) throws LogicException {
		try {
			ordertypeDao.delete(id);
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}
}
