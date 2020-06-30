package org.itstep.logic;

import java.util.List;


import org.itstep.domain.Instruction.InstructionType;
import org.itstep.postgres.DaoException;
import org.itstep.postgres.InstructionTypeDao;


public class InstructionTypeServiceImpl implements InstructionTypeService {
	private InstructionTypeDao instructiontypeDao;

	public void setInstructionTypeDao(InstructionTypeDao instructiontypeDao) {
		this.instructiontypeDao = instructiontypeDao;
	}

	@Override
	public List<InstructionType> findAll() throws LogicException {
		try {
			return instructiontypeDao.read();
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void save(InstructionType instructiontype) throws LogicException {
		try {
			if(instructiontype.getId() == null) {
				Long id = instructiontypeDao.create(instructiontype);
				instructiontype.setId(id);
			} else {
				instructiontypeDao.update(instructiontype);
			}
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void delete(Long id) throws LogicException {
		try {
			instructiontypeDao.delete(id);
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}
}
