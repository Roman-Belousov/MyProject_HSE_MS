package org.itstep.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.itstep.domain.EmployeeCard.EmployeeCard;

public class CardDbDaoImpl implements EmployeeCardDao {
	private Connection c;

	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public Long create(EmployeeCard employeecard) throws DaoException {
		String sql = "INSERT INTO \"workers_personal_card\"(\"personnel_number\", \"name\", \"surname\", \"work_area\", \"position\") VALUES (?, ?, ?, ?, ?)";
		PreparedStatement s = null;
		ResultSet r = null;
		try {
			s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // просим, чтобы statement МОГ получить ключи
			s.setLong(1, employeecard.getPersonnelnumber());
			s.setString(2, employeecard.getName());	
			s.setString(3, employeecard.getSurname());
			s.setString(4, employeecard.getWorkarea());
			s.setString(5, employeecard.getPosition());
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
	public EmployeeCard read(Long id) throws DaoException {
		String sql = "SELECT \"personnel_number\", \"name\", \"surname\", \"work_area\", \"position\", \"briefing_type\" , \"date_of_briefing\"FROM \"workers_personal_card\" WHERE \"id\" = ?";
		PreparedStatement s = null;
		ResultSet r = null;
		try {
			s = c.prepareStatement(sql);
			s.setLong(1, id);
			r = s.executeQuery();
			EmployeeCard employeecard = null;
			if(r.next()) {
				employeecard = new EmployeeCard();
				employeecard.setId(id);				
				employeecard.setPersonnelnumber(r.getLong("personnel_number"));
				employeecard.setName(r.getString("name"));
				employeecard.setSurname(r.getString("surname"));
				employeecard.setWorkarea(r.getString("work_area"));
				employeecard.setPosition(r.getString("position"));
				employeecard.setBriefingtype(r.getString("briefing_type"));
				employeecard.setDateofbriefing(r.getDate("date_of_briefing"));
			}
			return employeecard;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { r.close(); } catch(Exception e) {}
			try { s.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void update(EmployeeCard employeecard) throws DaoException {
		String sql = "UPDATE \"workers_personal_card\" SET \"personnel_number\" = ?, \"name\" = ? , \"surname\" = ? , \"work_area\" = ? , \"position\" = ? WHERE \"id\" = ?";
		PreparedStatement s = null;
		try {
			s = c.prepareStatement(sql);
			
			s.setLong(1, employeecard.getPersonnelnumber());
			s.setString(2, employeecard.getName());	
			s.setString(3, employeecard.getSurname());
			s.setString(4, employeecard.getWorkarea());
			s.setString(5, employeecard.getPosition());
			s.setLong(6, employeecard.getId());
			s.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { s.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"workers_personal_card\" WHERE \"id\" = ?";
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
	public List<EmployeeCard> read() throws DaoException {
		String sql = "SELECT  \"id\",\"personnel_number\", \"name\", \"surname\" , \"work_area\" , \"position\", \"briefing_type\", \"date_of_briefing\" FROM \"workers_personal_card\" ORDER BY \"personnel_number\"";
		Statement s = null;
		ResultSet r = null;
		try {
			s = c.createStatement();
			r = s.executeQuery(sql);
			List<EmployeeCard> employeecards = new ArrayList<>();
			while(r.next()) {
				EmployeeCard employeecard = new EmployeeCard();
				
				employeecard.setId(r.getLong("id"));
				employeecard.setPersonnelnumber(r.getLong("personnel_number"));
				employeecard.setName(r.getString("name"));
				employeecard.setSurname(r.getString("surname"));
				employeecard.setWorkarea(r.getString("work_area"));
				employeecard.setPosition(r.getString("position"));
				employeecard.setBriefingtype(r.getString("briefing_type"));
				employeecard.setDateofbriefing(r.getDate("date_of_briefing"));
				employeecards.add(employeecard);
			}
			return employeecards;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { r.close(); } catch(Exception e) {}
			try { s.close(); } catch(Exception e) {}
		}
	}
}

