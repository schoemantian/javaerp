package co.elsumo.werp.model;

import javax.persistence.*;
import org.openxava.annotations.*;
import org.openxava.util.*;

import co.elsumo.werp.validators.*;

@View(extendsView="super.DEFAULT",
members="approve; purchaseOrder { purchaseOrder } "
)
@EntityValidator(
		value=ApproveToBeInPurchaseOrderValidator.class, properties= {
				@PropertyValue(name="year"),
				@PropertyValue(name="number"),
				@PropertyValue(name="purchaseOrder"),
				@PropertyValue(name="approve")
		}
		)
@Entity
@Tabs({
@Tab(baseCondition = "deleted = false"),
@Tab(name="Deleted", baseCondition = "deleted = true")
})
public class Requisition extends PurchaseParentDocument{

//************************************************Link To Sales Order ****************************************
	@ManyToOne
	private PurchaseOrder purchaseOrder;
	public PurchaseOrder getPurchaseOrder() {
	return purchaseOrder;
	}
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
	this.purchaseOrder = purchaseOrder;
	}
	
//************************************************ Accept Validation ****************************************
	private boolean approve;
		public boolean isApprove() {
		return approve;
	}
		
	public void setApprove(boolean approve) {
		this.approve = approve;
	}
	
//************************************************ Confirm to delete off of GUI ****************************************
	@PreRemove
	private void validateOnRemove() { 
		if (purchaseOrder != null) {
			throw new IllegalStateException(
					XavaResources.getString("cannot delete requisition with purchase order"));
		}
	}
	
	public void setDeleted(boolean deleted) {
		if (deleted) validateOnRemove(); 
		super.setDeleted(deleted);
	}
}