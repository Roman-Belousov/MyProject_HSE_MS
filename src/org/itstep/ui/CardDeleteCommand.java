package org.itstep.ui;

import java.util.Arrays;

import org.itstep.logic.LogicException;

public class CardDeleteCommand extends EmployeeCardCommand {
	@Override
	public boolean exec(String[] args) throws LogicException{
		if (args.length == 1) {
			Long id = Long.valueOf(args[0]);
			getCardService().delete(Arrays.asList(id));
			System.out.println(" Карточка успешно удалена");

		} else {
			System.out.println("Неверное количество аргументов!");
		}
		return true;
	}
}

 