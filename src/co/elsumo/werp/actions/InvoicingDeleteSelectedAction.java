package co.elsumo.werp.actions;

import java.util.*;

import javax.validation.*;

import org.openxava.actions.*;
import org.openxava.model.*;
import org.openxava.model.meta.*;

public class InvoicingDeleteSelectedAction extends TabBaseAction implements IChainAction {
	
	private String nextAction = null; 
	
	public void execute() throws Exception {
		if (!getMetaModel().containsMetaProperty("deleted")) {nextAction="CRUD.deleteSelected";
		return;
		}
		markSelectedEntitiesAsDeleted();
	}

	private MetaModel getMetaModel() {
		return MetaModel.get(getTab().getModelName());
	}
	
	public String getNextAction() throws Exception{
		return nextAction; 
	}
	
	private boolean restore;
	
	public boolean isRestore() {
		return restore;
		}

		public void setRestore(boolean restore) {
		this.restore = restore;
		}
	
	
	private void markSelectedEntitiesAsDeleted() throws Exception {
		Map values = new HashMap();
			values.put("deleted", !isRestore());
			for (int row: getSelected()) {
				Map key = (Map) getTab().getTableModel().getObjectAt(row);
				try {
					MapFacade.setValues(getTab().getModelName(), key, values);
				}
				
				catch (ValidationException ex) {
					addError("no_delete_row", row + 1, key);
				}
				
				catch (Exception ex) {
					addError("no_delete_row", row + 1, key);
				}
			}
			
			getTab().deselectAll();
			resetDescriptionsCache();	
	}
	
	
}