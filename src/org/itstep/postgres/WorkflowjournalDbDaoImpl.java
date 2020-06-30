package org.itstep.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.itstep.domain.Instruction.Instruction;
import org.itstep.domain.Workflowjournal.Workflowjournal;


public class WorkflowjournalDbDaoImpl implements WorkflowjournalDao {
	private Connection c;

	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public Long create(Workflowjournal workflowjournal) throws DaoException {
		String sql = "INSERT INTO \"workflow_journal\"(\"instruction_id\", \"briefing_type_id\", \"workers_personal_card_id\", "
				+ "\"briefing_date\") VALUES (?, ?, ?, ?)";
		PreparedStatement s = null;
		ResultSet r = null;
		try {
			s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // просим, чтобы statement МОГ получить ключи
			s.setLong(1, workflowjournal.getInstructionid().getId());
			s.setLong(2, workflowjournal.getBriefingtype().getId());
			s.setLong(3, workflowjournal.getWorkerspersonalcardid().getId());
			s.setDate(4, new java.sql.Date(workflowjournal.getBriefingdate().getTime()));			
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
	public Workflowjournal read(Long id) throws DaoException {
		String sql = "SELECT \"instruction_id\", \"briefing_type_id\", \"workers_personal_card_id\", \"briefing_date\" FROM \"workflow_journal\" WHERE \"id\" = ?";
		PreparedStatement s = null;
		ResultSet r = null;
		try {
			s = c.prepareStatement(sql);
			s.setLong(1, id);
			r = s.executeQuery();
			Workflowjournal workflowjournal = null;
			if(r.next()) {
				workflowjournal = new Workflowjournal();
				workflowjournal.setId(id);
				workflowjournal.setInstructionid(new Instruction());
				workflowjournal.getInstructionid().setId(r.getLong("instruction_id"));;
				workflowjournal.getBriefingtype().setId(r.getLong("briefing_type_id"));
				workflowjournal.getWorkerspersonalcardid().setId(r.getLong("workers_personal_card_id"));
				workflowjournal.setBriefingdate(r.getDate("briefing_date"));
			}
			return workflowjournal;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { r.close(); } catch(Exception e) {}
			try { s.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void update(Workflowjournal workflowjournal) throws DaoException {
		String sql = "UPDATE \"workflow_journal\" SET \"instruction_id\" = ?, \"briefing_type_id\" = ?,"
				+ " \"workers_personal_card_id\" = ?, \"briefing_date\" = ? WHERE \"id\" = ?";
		PreparedStatement s = null;
		try {
			s = c.prepareStatement(sql);
			s.setLong(1, workflowjournal.getInstructionid().getId());
			s.setLong(2, workflowjournal.getBriefingtype().getId());
			s.setLong(3, workflowjournal.getWorkerspersonalcardid().getId());
			s.setDate(4, new java.sql.Date(workflowjournal.getBriefingdate().getTime()));
			s.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { s.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"workflow_journal\" WHERE \"id\" = ?";
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
	public List<Workflowjournal> read() throws DaoException {
		String sql = "SELECT \"id\", \"instruction_id\", \"briefing_type_id\", \"workers_personal_card_id\", \"briefing_date\" FROM \"workflow_journal\" ORDER BY \"workers_personal_card_id\"";
		Statement s = null;
		ResultSet r = null;
		try {
			s = c.createStatement();
			r = s.executeQuery(sql);
			List<Workflowjournal> workflowjournals = new ArrayList<>();
			while(r.next()) {
				Workflowjournal workflowjournal = new Workflowjournal();
				workflowjournal.setId(r.getLong("id"));
				workflowjournal.getInstructionid().setId(r.getLong("instruction_id"));;
				workflowjournal.getBriefingtype().setId(r.getLong("briefing_type_id"));
				workflowjournal.getWorkerspersonalcardid().setId(r.getLong("workers_personal_card_id"));
				workflowjournal.setBriefingdate(r.getDate("briefing_date"));				
				workflowjournals.add(workflowjournal);
			}
			return workflowjournals;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { r.close(); } catch(Exception e) {}
			try { s.close(); } catch(Exception e) {}
		}
	}
}
