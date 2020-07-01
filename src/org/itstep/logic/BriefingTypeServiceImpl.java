package org.itstep.logic;

import java.util.List;

import org.itstep.domain.Workflowjournal.BriefingType;
import org.itstep.postgres.BriefingTypeDao;
import org.itstep.postgres.DaoException;



public class BriefingTypeServiceImpl implements BriefingTypeService {
	private BriefingTypeDao briefingtypeDao;

	public void setBriefingTypeDao(BriefingTypeDao briefingtypeDao) {
		this.briefingtypeDao = briefingtypeDao;
	}

	@Override
	public List<BriefingType> findAll() throws LogicException {
		try {
			return briefingtypeDao.read();
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void save(BriefingType briefingtype) throws LogicException {
		try {
			if(briefingtype.getId() == null) {
				Long id = briefingtypeDao.create(briefingtype);
				briefingtype.setId(id);
			} else {
				briefingtypeDao.update(briefingtype);
			}
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void delete(Long id) throws LogicException {
		try {
			briefingtypeDao.delete(id);
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}
}
