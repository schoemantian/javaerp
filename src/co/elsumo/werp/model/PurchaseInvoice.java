package co.elsumo.werp.model;

import java.util.*;
import javax.persistence.*;
import org.openxava.annotations.*;

@View(extendsView="super.DEFAULT",
members="purchaseOrder { purchaseOrder }"
)
@Entity
@Tabs({
@Tab(baseCondition = "deleted = false"),
@Tab(name="Deleted", baseCondition = "deleted = true")
})
public class PurchaseInvoice extends PurchaseParentDocument {

//************************************************Link Back To Sales Order ****************************************
	@OneToMany(mappedBy="purchaseInvoice")
	private Collection<PurchaseOrder> purchaseOrder;
	
	public Collection<PurchaseOrder> getpurchaseOrder() {
	return purchaseOrder;
	}
	public void setPurchaseOrder(Collection<PurchaseOrder> purchaseOrder) {
	this.purchaseOrder = purchaseOrder;
	}
		
}
