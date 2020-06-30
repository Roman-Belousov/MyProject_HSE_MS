package org.itstep.postgres;

import java.util.List;

import org.itstep.domain.Instruction.InstructionType;

public interface InstructionTypeDao extends Dao<InstructionType> {
	List<InstructionType> read() throws DaoException;
}
