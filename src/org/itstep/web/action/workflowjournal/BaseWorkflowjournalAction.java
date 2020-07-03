package org.itstep.web.action.workflowjournal;

import org.itstep.logic.WorkflowjournalService;
import org.itstep.web.action.Action;

public abstract class BaseWorkflowjournalAction implements Action{
private WorkflowjournalService workflowjournalService;

protected WorkflowjournalService getWorkflowjournalService() {
	return workflowjournalService;
}

public void setWorkflowjournalService(WorkflowjournalService workflowjournalService) {
	this.workflowjournalService = workflowjournalService;
}

}
