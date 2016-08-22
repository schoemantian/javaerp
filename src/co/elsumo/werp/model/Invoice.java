package co.elsumo.werp.model;

import java.util.*;
import javax.persistence.*;
import org.openxava.annotations.*;

@View(extendsView="super.DEFAULT",
members="salesOrder { salesOrder }"
)
@Entity
@Tabs({
@Tab(baseCondition = "deleted = false"),
@Tab(name="Deleted", baseCondition = "deleted = true")
})
public class Invoice extends SalesParentDocument {

//************************************************Link Back To Sales Order ****************************************
	@OneToMany(mappedBy="invoice")
	private Collection<SalesOrder> salesOrder;
	// Collection of Order entities added
	public Collection<SalesOrder> getSalesOrder() {
	return salesOrder;
	}
	public void setSalesOrder(Collection<SalesOrder> salesOrder) {
	this.salesOrder = salesOrder;
	}
}
