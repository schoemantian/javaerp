package co.elsumo.werp.model;

import javax.persistence.*;
import org.openxava.annotations.*;
import org.openxava.util.*;
import co.elsumo.werp.validators.*;

@View(extendsView="super.DEFAULT",
members="accept; salesOrder { salesOrder } "
)
@EntityValidator(
		value=AcceptToBeInSalesOrderValidator.class, properties= {
				@PropertyValue(name="year"),
				@PropertyValue(name="number"),
				@PropertyValue(name="salesOrder"),
				@PropertyValue(name="accept")
		}
		)
@Entity
@Tabs({
@Tab(baseCondition = "deleted = false"),
@Tab(name="Deleted", baseCondition = "deleted = true")
})
public class Quotation extends SalesParentDocument{

//************************************************ Link to Sales Order****************************************
	@ManyToOne
	private SalesOrder salesOrder;
	public SalesOrder getSalesOrder() {
	return salesOrder;
	}
	public void setSalesOrder(SalesOrder salesOrder) {
	this.salesOrder = salesOrder;
	}

//************************************************ Accept Validation ****************************************
	private boolean accept;
		public boolean isAccept() {
		return accept;
	}
	
	public void setAccept(boolean accept) {
		this.accept = accept;
	}
	
//****************************************** Confirm to Delete Off of GUI ***********************************
	@PreRemove
	private void validateOnRemove() { 
		if (salesOrder != null) {
			throw new IllegalStateException(
					XavaResources.getString(
							"cannot delete quotation with sales order"));
		}
	}
	
	public void setDeleted(boolean deleted) {
		if (deleted) validateOnRemove(); 
		super.setDeleted(deleted);
	}
	
}