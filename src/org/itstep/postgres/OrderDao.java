package org.itstep.postgres;

import java.util.List;

import org.itstep.domain.Order.Order;



public interface OrderDao extends Dao<Order> {
	List<Order> read() throws DaoException;
}
