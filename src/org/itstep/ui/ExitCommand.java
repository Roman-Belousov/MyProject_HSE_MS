package org.itstep.ui;

public class ExitCommand extends EmployeeCardCommand implements Command {
	@Override
	public boolean exec(String[] args) {
		System.out.println("Сеанс работы с карточками окончен! Всего доброго!");
		return false;
	}
}