package org.itstep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import org.itstep.logic.CardService;
import org.itstep.logic.CardServiceImpl;
import org.itstep.logic.LogicException;

import org.itstep.postgres.CardDbDaoImpl;
import org.itstep.postgres.EmployeeCardDao;

import org.itstep.ui.CardDeleteCommand;
import org.itstep.ui.CardListCommand;
import org.itstep.ui.CardSaveCommand;
import org.itstep.ui.Command;

public class Factory implements AutoCloseable{
	
	private Command cardDeleteCommand = null;
	public Command getCardDeleteCommand() throws LogicException{
		if(cardDeleteCommand == null) {
			CardDeleteCommand command = new CardDeleteCommand();
			cardDeleteCommand = command;
			command.setCardService(getCardService());
		}
		return cardDeleteCommand;
	}

	private Command cardListCommand = null;
	public Command getCardListCommand() throws LogicException{
		if(cardListCommand == null) {
			CardListCommand command = new CardListCommand();
			cardListCommand = command;
			command.setCardService(getCardService());
		}
		return cardListCommand;
	}

	private Command cardSaveCommand = null;
	public Command getCardSaveCommand() throws LogicException{
		if(cardSaveCommand == null) {
			CardSaveCommand command = new CardSaveCommand();
			cardSaveCommand = command;
			command.setCardService(getCardService());
		}
		return cardSaveCommand;
	}
	
	
	
	private CardService cardService = null;
	public CardService getCardService() throws LogicException{
		if(cardService == null) {
			CardServiceImpl service = new CardServiceImpl();
			cardService = service;
			service.setCardDao(getEmployeeCardDao());
						
		}
		return cardService;
	}

	private EmployeeCardDao cardDao = null;
	public EmployeeCardDao getEmployeeCardDao() throws LogicException {
		if(cardDao == null) {
			CardDbDaoImpl cardDaoImpl = new CardDbDaoImpl();
			cardDao = cardDaoImpl;
			cardDaoImpl.setConnection(getConnection());
		}
		return cardDao;
	}
	private Connection connection = null;
	public Connection getConnection() throws LogicException {
		if(connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://localhost/HSE_MS", "postgres", "postgresroot");
			} catch(SQLException e) {
				throw new LogicException(e);
			}
		}
		return connection;
	}

	@Override
	public void close() {
		try { connection.close(); } catch(Exception e) {}
	}
}
