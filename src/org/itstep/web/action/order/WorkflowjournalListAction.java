package org.itstep.web.action.workflowjournal;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.itstep.domain.Workflowjournal.Workflowjournal;
import org.itstep.logic.LogicException;


public class WorkflowjournalListAction extends BaseWorkflowjournalAction {
	@Override
	public Result exec(HttpServletRequest req, HttpServletResponse resp) throws LogicException {
		List<Workflowjournal> workflowjournals = getWorkflowjournalService().findAll();
		req.setAttribute("workflowjournals", workflowjournals);
		return null;
	}
}
