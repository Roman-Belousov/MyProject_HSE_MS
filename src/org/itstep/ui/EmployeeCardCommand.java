package org.itstep.ui;


import org.itstep.logic.CardService;

abstract public class EmployeeCardCommand implements Command {
	public CardService cardService;

	public void setCardService(CardService cardService) {
		this.cardService = cardService;
	}

	protected CardService getCardService() {
		return cardService;
	}
}