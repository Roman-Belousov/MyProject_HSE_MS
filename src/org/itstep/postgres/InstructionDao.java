package org.itstep.postgres;

import java.util.List;

import org.itstep.domain.Instruction.Instruction;


public interface InstructionDao extends Dao<Instruction> {
	List<Instruction> read() throws DaoException;
}
