package co.elsumo.werp.actions;

import org.openxava.actions.*;

public class OnChangeVoidAction extends OnChangePropertyBaseAction {

	public void execute() throws Exception {
		addMessage("on_change_void_executed");		
	}

}