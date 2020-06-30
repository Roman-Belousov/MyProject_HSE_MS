package org.itstep.postgres;

import java.util.List;

import org.itstep.domain.Workflowjournal.Workflowjournal;


public interface WorkflowjournalDao extends Dao<Workflowjournal> {
	List<Workflowjournal> read() throws DaoException;
}
