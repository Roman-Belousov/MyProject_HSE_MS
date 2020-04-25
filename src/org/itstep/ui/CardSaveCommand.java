package org.itstep.ui;

import org.itstep.domain.EmployeeCard.EmployeeCard;
import org.itstep.logic.LogicException;

public class CardSaveCommand extends EmployeeCardCommand {
	@Override
	public boolean exec(String[] args) throws LogicException {
		if (args.length == 2) {

			EmployeeCard employeecard = new EmployeeCard();
			Integer offset = 0;
			if (args.length == 2) {
				employeecard.setPersonnelnumber(Long.valueOf(args[0]));
				offset = 1;
				
			}
			employeecard.setName(String.valueOf(args[offset]));
				getCardService().save(employeecard);
				System.out.println("Счет успешно сохранен!");
				
			

		} else {
			System.out.println("Неверное количество аргументов!");
		}
		return true;
	}
}
