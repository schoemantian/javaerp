package co.elsumo.werp.actions;

import java.util.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.model.*;

import co.elsumo.werp.model.*;

public class DeleteButKeepAction extends ViewBaseAction implements IChainAction {  
		
	private String nextAction = null;
	
	public void execute() throws Exception {
		if (getView().getKeyValuesWithValue().isEmpty()){
			addError("There is obviously nothing to delete, DUH");
		return;
		}
		
		if (!getView().getMetaModel().containsMetaProperty("deleted")){
				addMessage( "Not deleted, it has no deleted property");
				nextAction = "CRUD.delete";
				return;
				}
	
		Map values = new HashMap();
		values.put("deleted", true);
		MapFacade.setValues(
				getModelName(),
				getView().getKeyValues(),
				values);
		resetDescriptionsCache();
		addMessage("object_deleted", getModelName());
		getView().clear();
		getView().setEditable(false);
		}

	public String getNextAction() throws Exception{
			return nextAction; 
			}
}
