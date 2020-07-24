package org.itstep.postgres;

import java.util.List;

import org.itstep.domain.Order.OrderType;


public interface OrderTypeDao extends Dao<OrderType> {
	List<OrderType> read() throws DaoException;
}
