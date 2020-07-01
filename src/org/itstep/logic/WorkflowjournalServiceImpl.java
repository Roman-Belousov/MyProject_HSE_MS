package org.itstep.logic;

import java.util.List;
import org.itstep.domain.Workflowjournal.BriefingType;
import org.itstep.domain.Workflowjournal.Workflowjournal;
import org.itstep.postgres.BriefingTypeDao;
import org.itstep.postgres.DaoException;
import org.itstep.postgres.WorkflowjournalDao;


public class WorkflowjournalServiceImpl implements WorkflowjournalService {
	private WorkflowjournalDao workflowjournalDao;
	private BriefingTypeDao briefingtypeDao;

	public void setWorkflowjournalDao(WorkflowjournalDao workflowjournalDao) {
		this.workflowjournalDao = workflowjournalDao;
	}

	public void setBriefingTypeDao(BriefingTypeDao briefingtypeDao) {
		this.briefingtypeDao = briefingtypeDao;
	}
	
	@Override
	public List<Workflowjournal> findAll() throws LogicException {
		try {
			List<Workflowjournal> workflowjournals = workflowjournalDao.read();
			for(Workflowjournal workflowjournal : workflowjournals) {
				BriefingType briefingtype = workflowjournal.getBriefingtype();
				briefingtype = briefingtypeDao.read(briefingtype.getId());
				workflowjournal.setBriefingtype(briefingtype);
			}
			return workflowjournals;
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public Workflowjournal findById(Long id) throws LogicException {
		try {
			Workflowjournal workflowjournal = workflowjournalDao.read(id);	
			if(workflowjournal != null) {
				BriefingType briefingtype = workflowjournal.getBriefingtype();
				briefingtype = briefingtypeDao.read(briefingtype.getId());
				workflowjournal.setBriefingtype(briefingtype);
			}
			return workflowjournal;
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}
	
	@Override
	public void save(Workflowjournal workflowjournal) throws LogicException {
		try {
			if(workflowjournal.getId() == null) {
				Long id = workflowjournalDao.create(workflowjournal);
				workflowjournal.setId(id);
			} else {
				workflowjournalDao.update(workflowjournal);
			}
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void delete(List<Long> ids) throws LogicException{
		try {
			for(Long id : ids) {
				workflowjournalDao.delete(id);
			}
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	
	}
	
}

	


