package org.itstep.web.action.workflowjournal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itstep.logic.LogicException;
import org.itstep.web.action.ActionException;

public class WorkflowjournalDeleteAction extends BaseWorkflowjournalAction {
	@Override
	public Result exec(HttpServletRequest req, HttpServletResponse resp) throws LogicException {
		String idsString[] = req.getParameterValues("id");
		if(idsString != null) {
			try {
				List<Long> ids = new ArrayList<>(idsString.length);
				for(String id : idsString) {
					ids.add(Long.parseLong(id));
				}
				getWorkflowjournalService().delete(ids);
			} catch(NumberFormatException e) {
				throw new ActionException(e, 400);
			}
		}
		return new Result("/workflowjournal/list");
	}
}
