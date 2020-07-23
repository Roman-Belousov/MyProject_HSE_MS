package org.itstep.logic;

import java.util.List;
import org.itstep.domain.Instruction.Instruction;
import org.itstep.domain.Instruction.InstructionType;
import org.itstep.postgres.DaoException;
import org.itstep.postgres.InstructionDao;
import org.itstep.postgres.InstructionTypeDao;


public class InstructionServiceImpl implements InstructionService {
	private InstructionDao instructionDao;
	private InstructionTypeDao instructiontypeDao;

	public void setInstructionDao(InstructionDao instructionDao) {
		this.instructionDao = instructionDao;
	}

	public void setInstructionTypeDao(InstructionTypeDao instructiontypeDao) {
		this.instructiontypeDao = instructiontypeDao;
	}
	
	@Override
	public List<Instruction> findAll() throws LogicException {
		try {
			List<Instruction> instructions = instructionDao.read();
			for(Instruction instruction : instructions) {
				InstructionType instructiontype = instruction.getInstructionType();
				instructiontype = instructiontypeDao.read(instructiontype.getId());
				instruction.setInstructionType(instructiontype);
				
				
			}
			return instructions;
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public Instruction findById(Long id) throws LogicException {
		try {
			Instruction instruction = instructionDao.read(id);	
			if(instruction != null) {
				InstructionType instructiontype = instruction.getInstructionType();
				instructiontype = instructiontypeDao.read(instructiontype.getId());
				instruction.setInstructionType(instructiontype);
			}
			return instruction;
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}
	
	@Override
	public void save(Instruction instruction) throws LogicException {
		try {
			if(instruction.getId() == null) {
				Long id = instructionDao.create(instruction);
				instruction.setId(id);
			} else {
				instructionDao.update(instruction);
			}
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void delete(List<Long> ids) throws LogicException{
		try {
			for(Long id : ids) {
				instructionDao.delete(id);
			}
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	
	}
	
}

	


