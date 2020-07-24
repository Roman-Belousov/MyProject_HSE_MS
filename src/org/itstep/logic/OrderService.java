package org.itstep.logic;

import java.util.List;

import org.itstep.domain.Order.Order;


public interface OrderService {

	List <Order> findAll() throws LogicException;
	
	Order findById(Long id) throws LogicException;
	
	void save(Order order) throws LogicException;
	
	void delete(List<Long> ids) throws LogicException;


	
}
