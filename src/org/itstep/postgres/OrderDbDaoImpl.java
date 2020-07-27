package org.itstep.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.itstep.domain.User;
import org.itstep.domain.Order.Order;
import org.itstep.domain.Order.OrderType;



public class OrderDbDaoImpl implements OrderDao {
	private Connection c;

	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public Long create(Order order) throws DaoException {
		String sql = "INSERT INTO \"order\"(\"serial_number\", \"order_type_id\", \"ordername\", "
				+ "\"date_of_creation\", \"expiration_date\", \"filename\", \"executer_id\") VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement s = null;
		ResultSet r = null;
		try {
			s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // просим, чтобы statement МОГ получить ключи
			s.setLong(1, order.getSerialnumber());
			s.setLong(2, order.getOrdertype().getId());
			s.setString(3, order.getOrdername());
			s.setDate(4, new java.sql.Date(order.getDate_of_creation().getTime()));	
			s.setDate(5, new java.sql.Date(order.getExpiration_date().getTime()));
			s.setString(6, order.getFilename());
			s.setLong(7, order.getRole().getId());
			s.executeUpdate();
			r = s.getGeneratedKeys(); // ПОЛУЧАЕМ сгенерированные ключи (не работает без Statement.RETURN_GENERATED_KEYS)
			r.next();
			return r.getLong(1);
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { s.close(); } catch(Exception e) {}
			try { r.close(); } catch(Exception e) {}
		}
	}

	@Override
	public Order read(Long id) throws DaoException {
		String sql = "SELECT \"serial_number\", \"order_type_id\", \"ordername\", \"date_of_creation\", \"expiration_date\", \"filename\", \"executer_id\" FROM \"order\" WHERE \"id\" = ?";
		PreparedStatement s = null;
		ResultSet r = null;
		try {
			s = c.prepareStatement(sql);
			s.setLong(1, id);
			r = s.executeQuery();
			Order order = null;
			if(r.next()) {
				order = new Order();
				order.setId(id);				
				order.setOrdertype(new OrderType());
				order.setRole(new User());
				order.setSerialnumber(r.getLong("serial_number"));
				order.getOrdertype().setId(r.getLong("order_type_id"));
				order.setOrdername(r.getString("ordername"));				
				order.setDate_of_creation(r.getDate("date_of_creation"));
				order.setExpiration_date(r.getDate("expiration_date"));
				order.setFilename(r.getString("filename"));
				order.getRole().setId(r.getLong("executer_id"));
			}
			return order;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { r.close(); } catch(Exception e) {}
			try { s.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void update(Order order) throws DaoException {
		String sql = "UPDATE \"order\" SET \"serial_number\" = ?, \"order_type_id\" = ?,"
				+ " \"ordername\" = ?, \"date_of_creation\" = ?, \"expiration_date\" = ?, \"filename\" = ?, \"executer_id\" = ? WHERE \"id\" = ?";
		PreparedStatement s = null;
		try {
			s = c.prepareStatement(sql);
			s.setLong(1, order.getSerialnumber());
			s.setLong(2, order.getOrdertype().getId());
			s.setString(3, order.getOrdername());
			s.setDate(4, new java.sql.Date(order.getDate_of_creation().getTime()));	
			s.setDate(5, new java.sql.Date(order.getExpiration_date().getTime()));
			s.setString(6, order.getFilename());
			s.setLong(7, order.getRole().getId());
			s.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { s.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"order\" WHERE \"id\" = ?";
		PreparedStatement s = null;
		try {
			s = c.prepareStatement(sql);
			s.setLong(1, id);
			s.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { s.close(); } catch(Exception e) {}
		}
	}

	@Override
	public List<Order> read() throws DaoException {
		String sql = "SELECT \"id\", \"serial_number\", \"order_type_id\", \"ordername\", "
				+ "\"date_of_creation\", \"expiration_date\", \"filename\", \"executer_id\" FROM \"order\" ORDER BY \"\"date_of_creation";
		Statement s = null;
		ResultSet r = null;
		try {
			s = c.createStatement();
			r = s.executeQuery(sql);
			List<Order> orders = new ArrayList<>();
			while(r.next()) {
				Order order = new Order();
				order.setId(r.getLong("id"));				
				order.setOrdertype(new OrderType());
				order.setRole(new User());
				order.setSerialnumber(r.getLong("serial_number"));
				order.getOrdertype().setId(r.getLong("order_type_id"));
				order.setOrdername(r.getString("ordername"));				
				order.setDate_of_creation(r.getDate("date_of_creation"));
				order.setExpiration_date(r.getDate("expiration_date"));
				order.setFilename(r.getString("filename"));
				order.getRole().setId(r.getLong("executer_id"));	
				orders.add(order);
			}
			return orders;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { r.close(); } catch(Exception e) {}
			try { s.close(); } catch(Exception e) {}
		}
	}
}
