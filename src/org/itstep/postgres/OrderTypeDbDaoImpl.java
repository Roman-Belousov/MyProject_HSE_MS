package org.itstep.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.itstep.domain.Order.OrderType;


public class OrderTypeDbDaoImpl implements OrderTypeDao {
	private Connection c;

	public void setConnection(Connection c) {
		this.c = c;
	}

	private Map<Long, OrderType> cache = new HashMap<>();

	@Override
	public Long create(OrderType ordertype) throws DaoException {
		String sql = "INSERT INTO \"order_type\"(\"name\") VALUES (?)";
		PreparedStatement s = null;
		ResultSet r = null;
		try {
			s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // просим, чтобы statement МОГ получить ключи
			s.setString(1, ordertype.getName());
			s.executeUpdate();
			r = s.getGeneratedKeys(); // ПОЛУЧАЕМ сгенерированные ключи (не работает без Statement.RETURN_GENERATED_KEYS)
			r.next();
			cache.clear();
			return r.getLong(1);
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { s.close(); } catch(Exception e) {}
			try { r.close(); } catch(Exception e) {}
		}
	}

	@Override
	public OrderType read(Long id) throws DaoException {
		String sql = "SELECT \"name\" FROM \"order_type\" WHERE \"id\" = ?";
		OrderType ordertype = cache.get(id);
		if(ordertype == null) {
			PreparedStatement s = null;
			ResultSet r = null;
			try {
				s = c.prepareStatement(sql);
				s.setLong(1, id);
				r = s.executeQuery();
				if(r.next()) {
					ordertype = new OrderType();
					ordertype.setId(id);
					ordertype.setName(r.getString("name"));
					cache.put(id, ordertype);
				}
			} catch(SQLException e) {
				throw new DaoException(e);
			} finally {
				try { r.close(); } catch(Exception e) {}
				try { s.close(); } catch(Exception e) {}
			}
		}
		return ordertype;
	}

	@Override
	public void update(OrderType ordertype) throws DaoException {
		String sql = "UPDATE \"order_type\" SET \"name\" = ? WHERE \"id\" = ?";
		PreparedStatement s = null;
		try {
			s = c.prepareStatement(sql);
			s.setString(1, ordertype.getName());
			s.setLong(2, ordertype.getId());
			s.executeUpdate();
			cache.clear();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { s.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"order_type\" WHERE \"id\" = ?";
		PreparedStatement s = null;
		try {
			s = c.prepareStatement(sql);
			s.setLong(1, id);
			s.executeUpdate();
			cache.clear();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { s.close(); } catch(Exception e) {}
		}
	}

	@Override
	public List<OrderType> read() throws DaoException {
		String sql = "SELECT \"id\", \"name\" FROM \"order_type\"";
		Statement s = null;
		ResultSet r = null;
		try {
			s = c.createStatement();
			r = s.executeQuery(sql);
			List<OrderType> ordertypes = new ArrayList<>();
			while(r.next()) {
				OrderType ordertype = new OrderType();
				ordertype.setId(r.getLong("id"));
				ordertype.setName(r.getString("name"));
				ordertypes.add(ordertype);
			}
			return ordertypes;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { r.close(); } catch(Exception e) {}
			try { s.close(); } catch(Exception e) {}
		}
	}
}
