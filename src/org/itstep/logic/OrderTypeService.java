package org.itstep.logic;

import java.util.List;

import org.itstep.domain.Order.OrderType;



public interface OrderTypeService {
	
	List<OrderType> findAll() throws LogicException;

	void save(OrderType orderType) throws LogicException;

	void delete(Long id) throws LogicException;
}
