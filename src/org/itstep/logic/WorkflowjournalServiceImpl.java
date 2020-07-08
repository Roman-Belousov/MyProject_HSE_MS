package org.itstep.logic;

import java.util.List;

import org.itstep.domain.EmployeeCard.EmployeeCard;
import org.itstep.domain.Instruction.Instruction;
import org.itstep.domain.Workflowjournal.BriefingType;
import org.itstep.domain.Workflowjournal.Workflowjournal;
import org.itstep.postgres.BriefingTypeDao;
import org.itstep.postgres.DaoException;
import org.itstep.postgres.EmployeeCardDao;
import org.itstep.postgres.InstructionDao;
import org.itstep.postgres.WorkflowjournalDao;


public class WorkflowjournalServiceImpl implements WorkflowjournalService {
	private WorkflowjournalDao workflowjournalDao;
	private BriefingTypeDao briefingtypeDao;
	private InstructionDao instructionDao;
	private EmployeeCardDao employeecardDao;
	


	public void setEmployeecardDao(EmployeeCardDao employeecardDao) {
		this.employeecardDao = employeecardDao;
	}

	public void setInstructionDao(InstructionDao instructionDao) {
		this.instructionDao = instructionDao;
	}

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
				Instruction instruction = workflowjournal.getInstruction();
				instruction = instructionDao.read(instruction.getId());
				workflowjournal.setInstruction(instruction);
				EmployeeCard employeecard = workflowjournal.getEmployeecard();
				employeecard = employeecardDao.read(employeecard.getId());
				workflowjournal.setEmployeecard(employeecard);
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
				Instruction instruction = workflowjournal.getInstruction();
				instruction = instructionDao.read(instruction.getId());
				workflowjournal.setInstruction(instruction);
				EmployeeCard employeecard = workflowjournal.getEmployeecard();
				employeecard = employeecardDao.read(employeecard.getId());
				workflowjournal.setEmployeecard(employeecard);
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

	


