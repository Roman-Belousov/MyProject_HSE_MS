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
import org.itstep.domain.Workflowjournal.BriefingType;


public class BriefingTypeDbDaoImpl implements BriefingTypeDao {
	private Connection c;

	public void setConnection(Connection c) {
		this.c = c;
	}

	private Map<Long, BriefingType> cache = new HashMap<>();

	@Override
	public Long create(BriefingType briefingtype) throws DaoException {
		String sql = "INSERT INTO \"briefing_type\"(\"name\") VALUES (?)";
		PreparedStatement s = null;
		ResultSet r = null;
		try {
			s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // просим, чтобы statement МОГ получить ключи
			s.setString(1, briefingtype.getName());
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
	public BriefingType read(Long id) throws DaoException {
		String sql = "SELECT \"name\" FROM \"briefing_type\" WHERE \"id\" = ?";
		BriefingType briefingtype = cache.get(id);
		if(briefingtype == null) {
			PreparedStatement s = null;
			ResultSet r = null;
			try {
				s = c.prepareStatement(sql);
				s.setLong(1, id);
				r = s.executeQuery();
				if(r.next()) {
					briefingtype = new BriefingType();
					briefingtype.setId(id);
					briefingtype.setName(r.getString("name"));
					cache.put(id, briefingtype);
				}
			} catch(SQLException e) {
				throw new DaoException(e);
			} finally {
				try { r.close(); } catch(Exception e) {}
				try { s.close(); } catch(Exception e) {}
			}
		}
		return briefingtype;
	}

	@Override
	public void update(BriefingType briefingtype) throws DaoException {
		String sql = "UPDATE \"briefing_type\" SET \"name\" = ? WHERE \"id\" = ?";
		PreparedStatement s = null;
		try {
			s = c.prepareStatement(sql);
			s.setString(1, briefingtype.getName());
			s.setLong(2, briefingtype.getId());
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
		String sql = "DELETE FROM \"briefing_type\" WHERE \"id\" = ?";
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
	public List<BriefingType> read() throws DaoException {
		String sql = "SELECT \"id\", \"name\" FROM \"briefing_type\"";
		Statement s = null;
		ResultSet r = null;
		try {
			s = c.createStatement();
			r = s.executeQuery(sql);
			List<BriefingType> briefingtypes = new ArrayList<>();
			while(r.next()) {
				BriefingType briefingtype = new BriefingType();
				briefingtype.setId(r.getLong("id"));
				briefingtype.setName(r.getString("name"));
				briefingtypes.add(briefingtype);
			}
			return briefingtypes;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { r.close(); } catch(Exception e) {}
			try { s.close(); } catch(Exception e) {}
		}
	}
}
