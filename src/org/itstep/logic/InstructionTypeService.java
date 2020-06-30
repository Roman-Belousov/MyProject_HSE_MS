package org.itstep.logic;

import java.util.List;

import org.itstep.domain.Instruction.InstructionType;


public interface InstructionTypeService {
	
	List<InstructionType> findAll() throws LogicException;

	void save(InstructionType instructiontype) throws LogicException;

	void delete(Long id) throws LogicException;
}
