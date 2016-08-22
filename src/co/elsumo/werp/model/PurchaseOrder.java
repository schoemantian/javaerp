package co.elsumo.werp.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.util.*;

import co.elsumo.werp.validators.*;

@View(extendsView="super.DEFAULT",
members="approve; requisition { requisition }"
)
@EntityValidator(
		value=ApproveToBeInPurchaseInvoiceValidator.class, properties= {
				@PropertyValue(name="year"),
				@PropertyValue(name="number"),
				@PropertyValue(name="purchaseInvoice"),
				@PropertyValue(name="approve")
		}
		)
@Entity
@Tabs({
@Tab(baseCondition = "deleted = false"),
@Tab(name="Deleted", baseCondition = "deleted = true")
})
public class PurchaseOrder extends PurchaseParentDocument {

//************************************************Link To Invoice *********************************************
	@ManyToOne
	private PurchaseInvoice purchaseInvoice;

	public PurchaseInvoice getPurchaseInvoice() {
	return purchaseInvoice;
	}
	public void setPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
	this.purchaseInvoice = purchaseInvoice;
	}
	
//************************************************Link Back To Quotation ****************************************
	@OneToMany(mappedBy="purchaseOrder")
	private Collection<Requisition> requisition;

	public Collection<Requisition> getRequisition() {
	return requisition;
	}
	public void setRequisition(Collection<Requisition> requisition) {
	this.requisition = requisition;
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
		if (purchaseInvoice != null) {
			throw new IllegalStateException(
					XavaResources.getString(
							"cannot delete purchase order with purchase invoice"));
		}
	}
	
	public void setDeleted(boolean deleted) {
		if (deleted) validateOnRemove(); 
		super.setDeleted(deleted);
	}
	
}