package org.itstep.web.action.employeecard;

import org.itstep.logic.CardService;
import org.itstep.web.action.Action;

public abstract class BaseEmployeeCardAction implements Action{
private CardService cardService;

protected CardService getCardService() {
	return cardService;
}

public void setCardService(CardService cardService) {
	this.cardService = cardService;
}

}
