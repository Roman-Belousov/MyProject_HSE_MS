package org.itstep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.itstep.logic.BriefingTypeService;
import org.itstep.logic.BriefingTypeServiceImpl;
import org.itstep.logic.CardService;
import org.itstep.logic.CardServiceImpl;
import org.itstep.logic.InstructionService;
import org.itstep.logic.InstructionServiceImpl;
import org.itstep.logic.InstructionTypeService;
import org.itstep.logic.InstructionTypeServiceImpl;
import org.itstep.logic.LogicException;
import org.itstep.logic.UserService;
import org.itstep.logic.UserServiceImpl;
import org.itstep.logic.WorkflowjournalService;
import org.itstep.logic.WorkflowjournalServiceImpl;
import org.itstep.postgres.BriefingTypeDao;
import org.itstep.postgres.BriefingTypeDbDaoImpl;
import org.itstep.postgres.CardDbDaoImpl;
import org.itstep.postgres.EmployeeCardDao;
import org.itstep.postgres.InstructionDao;
import org.itstep.postgres.InstructionDbDaoImpl;
import org.itstep.postgres.InstructionTypeDao;
import org.itstep.postgres.InstructionTypeDbDaoImpl;
import org.itstep.postgres.UserDao;
import org.itstep.postgres.UserDbDaoImpl;
import org.itstep.postgres.WorkflowjournalDao;
import org.itstep.postgres.WorkflowjournalDbDaoImpl;
import org.itstep.web.action.Action;
import org.itstep.web.action.LoginAction;
import org.itstep.web.action.LogoutAction;
import org.itstep.web.action.MainAction;
import org.itstep.web.action.employeecard.CardDeleteAction;
import org.itstep.web.action.employeecard.CardEditAction;
import org.itstep.web.action.employeecard.CardListAction;
import org.itstep.web.action.employeecard.CardSaveAction;
import org.itstep.web.action.instruction.InstructionDeleteAction;
import org.itstep.web.action.instruction.InstructionEditAction;
import org.itstep.web.action.instruction.InstructionListAction;
import org.itstep.web.action.instruction.InstructionSaveAction;
import org.itstep.web.action.workflowjournal.WorkflowjournalDeleteAction;
import org.itstep.web.action.workflowjournal.WorkflowjournalEditAction;
import org.itstep.web.action.workflowjournal.WorkflowjournalListAction;
import org.itstep.web.action.workflowjournal.WorkflowjournalSaveAction;

public class Factory implements AutoCloseable {

	private Map<String, ActionFactory> actions = new HashMap<>();
	{
		ActionFactory mainActionFactory = () -> getMainAction();
		actions.put("/", mainActionFactory);
		actions.put("/template", mainActionFactory);
		actions.put("/login", () -> getLoginAction());
		actions.put("/logout", () -> getLogoutAction());
		actions.put("/employeecard/list", () -> getCardListAction());
		actions.put("/employeecard/edit", () -> getCardEditAction());
		actions.put("/employeecard/save", () -> getCardSaveAction());
		actions.put("/employeecard/delete", () -> getCardDeleteAction());
		actions.put("/instruction/list", () -> getInstructionListAction());
		actions.put("/instruction/edit", () -> getInstructionEditAction());
		actions.put("/instruction/save", () -> getInstructionSaveAction());
		actions.put("/instruction/delete", () -> getInstructionDeleteAction());
		actions.put("/workflowjournal/list", () -> getWorkflowjournalListAction());
		actions.put("/workflowjournal/edit", () -> getWorkflowjournalEditAction());
		actions.put("/workflowjournal/save", () -> getWorkflowjournalSaveAction());
		actions.put("/workflowjournal/delete", () -> getWorkflowjournalDeleteAction());
		actions.put("/managerinstructionlist", () -> getInstructionListAction());
		actions.put("/manageremployeecardlist", () -> getCardListAction());
		actions.put("/managerworkflowjournallist", () -> getWorkflowjournalListAction());
	}

	public Action getAction(String url) throws LogicException {
		ActionFactory factory = actions.get(url);
		if (factory != null) {
			return factory.getInstance();
		}
		return null;
	}

	private Action mainAction = null;

	public Action getMainAction() {
		if (mainAction == null) {
			mainAction = new MainAction();
		}
		return mainAction;
	}

	private Action loginAction = null;

	public Action getLoginAction() throws LogicException {
		if (loginAction == null) {
			LoginAction loginActionImpl = new LoginAction();
			loginAction = loginActionImpl;
			loginActionImpl.setUserService(getUserService());
		}
		return loginAction;
	}

	private Action logoutAction = null;

	public Action getLogoutAction() {
		if (logoutAction == null) {
			logoutAction = new LogoutAction();
		}
		return logoutAction;
	}

	private Action cardListAction = null;

	public Action getCardListAction() throws LogicException {
		if (cardListAction == null) {
			CardListAction cardListActionImpl = new CardListAction();
			cardListAction = cardListActionImpl;
			cardListActionImpl.setCardService(getCardService());
		}
		return cardListAction;
	}

	private Action cardEditAction = null;

	public Action getCardEditAction() throws LogicException {
		if (cardEditAction == null) {
			CardEditAction cardEditActionImpl = new CardEditAction();
			cardEditAction = cardEditActionImpl;
			cardEditActionImpl.setCardService(getCardService());

		}
		return cardEditAction;
	}

	private Action cardSaveAction = null;

	public Action getCardSaveAction() throws LogicException {
		if (cardSaveAction == null) {
			CardSaveAction cardSaveActionImpl = new CardSaveAction();
			cardSaveAction = cardSaveActionImpl;
			cardSaveActionImpl.setCardService(getCardService());
		}
		return cardSaveAction;
	}

	private Action cardDeleteAction = null;

	public Action getCardDeleteAction() throws LogicException {
		if (cardDeleteAction == null) {
			CardDeleteAction cardDeleteActionImpl = new CardDeleteAction();
			cardDeleteAction = cardDeleteActionImpl;
			cardDeleteActionImpl.setCardService(getCardService());
		}
		return cardDeleteAction;
	}

	private CardService cardService = null;

	public CardService getCardService() throws LogicException {
		if (cardService == null) {
			CardServiceImpl service = new CardServiceImpl();
			cardService = service;
			service.setCardDao(getEmployeeCardDao());

		}
		return cardService;
	}

	private UserService userService = null;

	public UserService getUserService() throws LogicException {
		if (userService == null) {
			UserServiceImpl service = new UserServiceImpl();
			userService = service;
			service.setUserDao(getUserDao());
		}
		return userService;
	}

	private EmployeeCardDao cardDao = null;

	public EmployeeCardDao getEmployeeCardDao() throws LogicException {
		if (cardDao == null) {
			CardDbDaoImpl cardDaoImpl = new CardDbDaoImpl();
			cardDao = cardDaoImpl;
			cardDaoImpl.setConnection(getConnection());
		}
		return cardDao;
	}

	private UserDao userDao = null;

	public UserDao getUserDao() throws LogicException {
		if (userDao == null) {
			UserDbDaoImpl userDaoImpl = new UserDbDaoImpl();
			userDao = userDaoImpl;
			userDaoImpl.setConnection(getConnection());
		}
		return userDao;
	}

	// TODO Доработать CRUD операции в Instruction & Workflowjournal

	private InstructionDao instructionDao = null;

	public InstructionDao getInstructionDao() throws LogicException {
		if (instructionDao == null) {
			InstructionDbDaoImpl instructionDaoImpl = new InstructionDbDaoImpl();
			instructionDao = instructionDaoImpl;
			instructionDaoImpl.setConnection(getConnection());
		}
		return instructionDao;
	}

	private InstructionTypeDao instructiontypeDao = null;

	public InstructionTypeDao getInstructionTypeDao() throws LogicException {
		if (instructiontypeDao == null) {
			InstructionTypeDbDaoImpl instructiontypeDaoImpl = new InstructionTypeDbDaoImpl();
			instructiontypeDao = instructiontypeDaoImpl;
			instructiontypeDaoImpl.setConnection(getConnection());
		}
		return instructiontypeDao;
	}

	private InstructionService instructionService = null;

	public InstructionService getInstructionService() throws LogicException {
		if (instructionService == null) {
			InstructionServiceImpl service = new InstructionServiceImpl();
			instructionService = service;
			service.setInstructionDao(getInstructionDao());
			service.setInstructionTypeDao(getInstructionTypeDao());
		}
		return instructionService;
	}

	private InstructionTypeService instructiontypeService = null;

	public InstructionTypeService getInstructionTypeService() throws LogicException {
		if (instructiontypeService == null) {
			InstructionTypeServiceImpl service = new InstructionTypeServiceImpl();
			instructiontypeService = service;
			service.setInstructionTypeDao(getInstructionTypeDao());
		}
		return instructiontypeService;
	}

	private Action instructionListAction = null;

	public Action getInstructionListAction() throws LogicException {
		if (instructionListAction == null) {
			InstructionListAction instructionListActionImpl = new InstructionListAction();
			instructionListAction = instructionListActionImpl;
			instructionListActionImpl.setInstructionService(getInstructionService());
		}
		return instructionListAction;
	}

	private Action instructionEditAction = null;

	public Action getInstructionEditAction() throws LogicException {
		if (instructionEditAction == null) {
			InstructionEditAction instructionEditActionImpl = new InstructionEditAction();
			instructionEditAction = instructionEditActionImpl;
			instructionEditActionImpl.setInstructionService(getInstructionService());
			instructionEditActionImpl.setInstructionTypeService(getInstructionTypeService());

		}
		return instructionEditAction;
	}

	private Action instructionSaveAction = null;

	public Action getInstructionSaveAction() throws LogicException {
		if (instructionSaveAction == null) {
			InstructionSaveAction instructionSaveActionImpl = new InstructionSaveAction();
			instructionSaveAction = instructionSaveActionImpl;
			instructionSaveActionImpl.setInstructionService(getInstructionService());
		}
		return instructionSaveAction;
	}

	private Action instructionDeleteAction = null;

	public Action getInstructionDeleteAction() throws LogicException {
		if (instructionDeleteAction == null) {
			InstructionDeleteAction instructionDeleteActionImpl = new InstructionDeleteAction();
			instructionDeleteAction = instructionDeleteActionImpl;
			instructionDeleteActionImpl.setInstructionService(getInstructionService());
		}
		return instructionDeleteAction;
	}

	private WorkflowjournalDao workflowjournalDao = null;

	public WorkflowjournalDao getWorkflowjournalDao() throws LogicException {
		if (workflowjournalDao == null) {
			WorkflowjournalDbDaoImpl workflowjournalDaoImpl = new WorkflowjournalDbDaoImpl();
			workflowjournalDao = workflowjournalDaoImpl;
			workflowjournalDaoImpl.setConnection(getConnection());
		}
		return workflowjournalDao;
	}

	private BriefingTypeDao briefingtypeDao = null;

	public BriefingTypeDao getBriefingTypeDao() throws LogicException {
		if (briefingtypeDao == null) {
			BriefingTypeDbDaoImpl briefingtypeDaoImpl = new BriefingTypeDbDaoImpl();
			briefingtypeDao = briefingtypeDaoImpl;
			briefingtypeDaoImpl.setConnection(getConnection());
		}
		return briefingtypeDao;
	}

	private WorkflowjournalService workflowjournalService = null;

	public WorkflowjournalService getWorkflowjournalService() throws LogicException {
		if (workflowjournalService == null) {
			WorkflowjournalServiceImpl service = new WorkflowjournalServiceImpl();
			workflowjournalService = service;
			service.setWorkflowjournalDao(getWorkflowjournalDao());
			service.setBriefingTypeDao(getBriefingTypeDao());
			service.setInstructionDao(getInstructionDao());
			service.setEmployeecardDao(getEmployeeCardDao());
		}
		return workflowjournalService;
	}

	private BriefingTypeService briefingtypeService = null;

	public BriefingTypeService getBriefingTypeService() throws LogicException {
		if (briefingtypeService == null) {
			BriefingTypeServiceImpl service = new BriefingTypeServiceImpl();
			briefingtypeService = service;
			service.setBriefingTypeDao(getBriefingTypeDao());
		}
		return briefingtypeService;
	}

	private Action workflowjournalListAction = null;

	public Action getWorkflowjournalListAction() throws LogicException {
		if (workflowjournalListAction == null) {
			WorkflowjournalListAction workflowjournalListActionImpl = new WorkflowjournalListAction();
			workflowjournalListAction = workflowjournalListActionImpl;
			workflowjournalListActionImpl.setWorkflowjournalService(getWorkflowjournalService());
		}
		return workflowjournalListAction;
	}

	private Action workflowjournalEditAction = null;

	public Action getWorkflowjournalEditAction() throws LogicException {
		if (workflowjournalEditAction == null) {
			WorkflowjournalEditAction workflowjournalEditActionImpl = new WorkflowjournalEditAction();
			workflowjournalEditAction = workflowjournalEditActionImpl;
			workflowjournalEditActionImpl.setWorkflowjournalService(getWorkflowjournalService());
			workflowjournalEditActionImpl.setBriefingTypeService(getBriefingTypeService());
			workflowjournalEditActionImpl.setInstructionService(getInstructionService());

		}
		return workflowjournalEditAction;
	}

	private Action workflowjournalSaveAction = null;

	public Action getWorkflowjournalSaveAction() throws LogicException {
		if (workflowjournalSaveAction == null) {
			WorkflowjournalSaveAction workflowjournalSaveActionImpl = new WorkflowjournalSaveAction();
			workflowjournalSaveAction = workflowjournalSaveActionImpl;
			workflowjournalSaveActionImpl.setWorkflowjournalService(getWorkflowjournalService());
		}
		return workflowjournalSaveAction;
	}

	private Action workflowjournalDeleteAction = null;

	public Action getWorkflowjournalDeleteAction() throws LogicException {
		if (workflowjournalDeleteAction == null) {
			WorkflowjournalDeleteAction workflowjournalDeleteActionImpl = new WorkflowjournalDeleteAction();
			workflowjournalDeleteAction = workflowjournalDeleteActionImpl;
			workflowjournalDeleteActionImpl.setWorkflowjournalService(getWorkflowjournalService());
		}
		return workflowjournalDeleteAction;
	}

	private Connection connection = null;

	public Connection getConnection() throws LogicException {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://localhost/HSE_MS", "postgres",
						"postgresroot");
			} catch (SQLException e) {
				throw new LogicException(e);
			}
		}
		return connection;
	}

	@Override
	public void close() {
		try {
			connection.close();
		} catch (Exception e) {
		}
	}

	private static interface ActionFactory {
		Action getInstance() throws LogicException;
	}
}
