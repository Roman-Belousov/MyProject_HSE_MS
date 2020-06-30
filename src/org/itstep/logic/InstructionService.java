package org.itstep.logic;

import java.util.List;
import org.itstep.domain.Instruction.Instruction;

public interface InstructionService {

	List <Instruction> findAll() throws LogicException;
	
	Instruction findById(Long id) throws LogicException;
	
	void save(Instruction instruction) throws LogicException;
	
	void delete(List<Long> ids) throws LogicException;


	
}
