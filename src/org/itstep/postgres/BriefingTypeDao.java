package org.itstep.postgres;

import java.util.List;

import org.itstep.domain.Workflowjournal.BriefingType;

public interface BriefingTypeDao extends Dao<BriefingType> {
	List<BriefingType> read() throws DaoException;
}
