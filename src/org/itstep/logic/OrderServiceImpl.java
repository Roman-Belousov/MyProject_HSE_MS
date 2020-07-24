package org.itstep.logic;

import java.util.List;

import org.itstep.domain.User;

import org.itstep.domain.Order.Order;
import org.itstep.domain.Order.OrderType;
import org.itstep.postgres.DaoException;
import org.itstep.postgres.OrderDao;
import org.itstep.postgres.OrderTypeDao;
import org.itstep.postgres.UserDao;


public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;
	private UserDao userDao;
	private OrderTypeDao ordertypeDao;
	
	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setOrderTypeDao(OrderTypeDao ordertypeDao) {
		this.ordertypeDao = ordertypeDao;
	}
	
	
	
	@Override
	public List<Order> findAll() throws LogicException {
		try {
			List<Order> orders = orderDao.read();
			for(Order order : orders) {
				OrderType ordertype = order.getOrdertype();
				ordertype = ordertypeDao.read(ordertype.getId());
				order.setOrdertype(ordertype);
				User role = order.getRole();
				role = userDao.read(role.getId());
				order.setRole(role);				
			}
			return orders;
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public Order findById(Long id) throws LogicException {
		try {
			Order order = orderDao.read(id);	
			if(order != null) {
				OrderType ordertype = order.getOrdertype();
				ordertype = ordertypeDao.read(ordertype.getId());
				order.setOrdertype(ordertype);
				User role = order.getRole();
				role = userDao.read(role.getId());
				order.setRole(role);
				
			}
			return order;
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}
	
	@Override
	public void save(Order order) throws LogicException {
		try {
			if(order.getId() == null) {
				Long id = orderDao.create(order);
				order.setId(id);
			} else {
				orderDao.update(order);
			}
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void delete(List<Long> ids) throws LogicException{
		try {
			for(Long id : ids) {
				userDao.delete(id);
			}
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	
	}
	
}

	


