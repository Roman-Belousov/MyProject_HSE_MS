package org.itstep.ui;

import java.util.List;
import org.itstep.domain.EmployeeCard.EmployeeCard;
import org.itstep.logic.LogicException;

public class CardListCommand extends EmployeeCardCommand {

	@Override
	public boolean exec(String[] args) throws LogicException {
		List<EmployeeCard> employeecards = getCardService().findAll();
		if (employeecards.size() > 0) {
			for (EmployeeCard employeecard : employeecards) {
				System.out.println(employeecard);
			}
		} else {
			System.out.println("Список счетов пуст!");
		}
		return true;
	}
}
