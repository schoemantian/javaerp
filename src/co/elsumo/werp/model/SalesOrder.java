package co.elsumo.werp.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.util.*;

import co.elsumo.werp.validators.*;

@View(extendsView="super.DEFAULT",
members="accept; quotation { quotation }"
)
@EntityValidator(
		value=AcceptToBeInInvoiceValidator.class, properties= {
				@PropertyValue(name="year"),
				@PropertyValue(name="number"),
				@PropertyValue(name="invoice"),
				@PropertyValue(name="accept")
		}
		)
@Entity
@Tabs({
@Tab(baseCondition = "deleted = false"),
@Tab(name="Deleted", baseCondition = "deleted = true")
})
public class SalesOrder extends SalesParentDocument {

//************************************************Link To Invoice *********************************************
	@ManyToOne
	private Invoice invoice;

	public Invoice getInvoice() {
	return invoice;
	}
	public void setInvoice(Invoice invoice) {
	this.invoice = invoice;
	}
	
//************************************************Link Back To Quotation ****************************************
	@OneToMany(mappedBy="salesOrder")
	private Collection<Quotation> quotation;

	public Collection<Quotation> getQuotation() {
	return quotation;
	}
	public void setQuotation(Collection<Quotation> quotation) {
	this.quotation = quotation;
	}
	
//************************************************ Accept Validation ****************************************
	private boolean accept;
		public boolean isAccept() {
		return accept;
	}
		
	public void setAccept(boolean accept) {
		this.accept = accept;
	}
	

//************************************************ Confirm to delete off of GUI ****************************************
	@PreRemove
	private void validateOnRemove() { 
		if (invoice != null) {
			throw new IllegalStateException(
					XavaResources.getString(
							"cannot delete sales order with invoice"));
		}
	}
	
	public void setDeleted(boolean deleted) {
		if (deleted) validateOnRemove(); 
		super.setDeleted(deleted);
	}
	
}