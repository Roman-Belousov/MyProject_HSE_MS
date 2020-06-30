package org.itstep.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.itstep.domain.Instruction.Instruction;
import org.itstep.domain.Instruction.InstructionType;


public class InstructionDbDaoImpl implements InstructionDao {
	private Connection c;

	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public Long create(Instruction instruction) throws DaoException {
		String sql = "INSERT INTO \"instruction\"(\"instruction_type_id\", \"name\", \"serial_number\", "
				+ "\"date_of_creation\", \"expiration_date\" , \"filename\") VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement s = null;
		ResultSet r = null;
		try {
			s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // просим, чтобы statement МОГ получить ключи
			s.setLong(1, instruction.getInstructionType().getId());
			s.setString(2, instruction.getName());
			s.setLong(3, instruction.getSerialnumber());
			s.setDate(4, new java.sql.Date(instruction.getDateofcreation().getTime()));
			s.setDate(5, new java.sql.Date(instruction.getExpirationdate().getTime()));
			s.setString(6, instruction.getFilename());
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
	public Instruction read(Long id) throws DaoException {
		String sql = "SELECT \"instruction_type_id\", \"name\", \"serial_number\", \"date_of_creation\", \"expiration_date\" , \"filename\"FROM \"instruction\" WHERE \"id\" = ?";
		PreparedStatement s = null;
		ResultSet r = null;
		try {
			s = c.prepareStatement(sql);
			s.setLong(1, id);
			r = s.executeQuery();
			Instruction instruction = null;
			if(r.next()) {
				instruction = new Instruction();
				instruction.setId(id);
				instruction.setInstructionType(new InstructionType());
				instruction.getInstructionType().setId(r.getLong("instruction_type_id"));
				instruction.setName(r.getString("name"));
				instruction.setSerialnumber(r.getLong("serial_number"));
				instruction.setDateofcreation(r.getDate("date_of_creation"));
				instruction.setExpirationdate(r.getDate("expiration_date"));
				instruction.setFilename(r.getString("filename"));
			}
			return instruction;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { r.close(); } catch(Exception e) {}
			try { s.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void update(Instruction instruction) throws DaoException {
		String sql = "UPDATE \"instruction\" SET \"instruction_type_id\" = ?, \"name\" = ?,"
				+ " \"serial_number\" = ?, \"date_of_creation\" = ?, \"expiration_date\" = ?, \"filename\" = ? WHERE \"id\" = ?";
		PreparedStatement s = null;
		try {
			s = c.prepareStatement(sql);
			s.setLong(1, instruction.getInstructionType().getId());
			s.setString(2, instruction.getName());
			s.setLong(3, instruction.getSerialnumber());
			s.setDate(4, new java.sql.Date(instruction.getDateofcreation().getTime()));
			s.setDate(5, new java.sql.Date(instruction.getExpirationdate().getTime()));			
			s.setLong(6, instruction.getId());
			s.setString(7, instruction.getFilename());
			s.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { s.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"instruction\" WHERE \"id\" = ?";
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
	public List<Instruction> read() throws DaoException {
		String sql = "SELECT \"id\", \"instruction_type_id\", \"name\", \"serial_number\", \"date_of_creation\", \"expiration_date\" , \"filename\" FROM \"instruction\" ORDER BY \"date_of_creation\"";
		Statement s = null;
		ResultSet r = null;
		try {
			s = c.createStatement();
			r = s.executeQuery(sql);
			List<Instruction> instructions = new ArrayList<>();
			while(r.next()) {
				Instruction instruction = new Instruction();
				instruction.setId(r.getLong("id"));
				instruction.setInstructionType(new InstructionType());
				instruction.getInstructionType().setId(r.getLong("instruction_type_id"));
				instruction.setName(r.getString("name"));
				instruction.setSerialnumber(r.getLong("serial_number"));
				instruction.setDateofcreation(r.getDate("date_of_creation"));
				instruction.setExpirationdate(r.getDate("expiration_date"));
				instruction.setFilename(r.getString("filename"));
				instructions.add(instruction);
			}
			return instructions;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { r.close(); } catch(Exception e) {}
			try { s.close(); } catch(Exception e) {}
		}
	}
}
