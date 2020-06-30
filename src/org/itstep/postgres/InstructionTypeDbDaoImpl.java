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
import org.itstep.domain.Instruction.InstructionType;


public class InstructionTypeDbDaoImpl implements InstructionTypeDao {
	private Connection c;

	public void setConnection(Connection c) {
		this.c = c;
	}

	private Map<Long, InstructionType> cache = new HashMap<>();

	@Override
	public Long create(InstructionType instructiontype) throws DaoException {
		String sql = "INSERT INTO \"instruction_type\"(\"name\") VALUES (?)";
		PreparedStatement s = null;
		ResultSet r = null;
		try {
			s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // просим, чтобы statement МОГ получить ключи
			s.setString(1, instructiontype.getName());
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
	public InstructionType read(Long id) throws DaoException {
		String sql = "SELECT \"name\" FROM \"instruction_type\" WHERE \"id\" = ?";
		InstructionType instructiontype = cache.get(id);
		if(instructiontype == null) {
			PreparedStatement s = null;
			ResultSet r = null;
			try {
				s = c.prepareStatement(sql);
				s.setLong(1, id);
				r = s.executeQuery();
				if(r.next()) {
					instructiontype = new InstructionType();
					instructiontype.setId(id);
					instructiontype.setName(r.getString("name"));
					cache.put(id, instructiontype);
				}
			} catch(SQLException e) {
				throw new DaoException(e);
			} finally {
				try { r.close(); } catch(Exception e) {}
				try { s.close(); } catch(Exception e) {}
			}
		}
		return instructiontype;
	}

	@Override
	public void update(InstructionType instructiontype) throws DaoException {
		String sql = "UPDATE \"instruction_type\" SET \"name\" = ? WHERE \"id\" = ?";
		PreparedStatement s = null;
		try {
			s = c.prepareStatement(sql);
			s.setString(1, instructiontype.getName());
			s.setLong(2, instructiontype.getId());
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
		String sql = "DELETE FROM \"category\" WHERE \"id\" = ?";
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
	public List<InstructionType> read() throws DaoException {
		String sql = "SELECT \"id\", \"name\" FROM \"instruction_type\"";
		Statement s = null;
		ResultSet r = null;
		try {
			s = c.createStatement();
			r = s.executeQuery(sql);
			List<InstructionType> instructiontypes = new ArrayList<>();
			while(r.next()) {
				InstructionType instructiontype = new InstructionType();
				instructiontype.setId(r.getLong("id"));
				instructiontype.setName(r.getString("name"));
				instructiontypes.add(instructiontype);
			}
			return instructiontypes;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { r.close(); } catch(Exception e) {}
			try { s.close(); } catch(Exception e) {}
		}
	}
}
