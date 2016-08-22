package co.elsumo.werp.validators;

import org.openxava.validators.*;
import co.elsumo.werp.model.*;
import org.openxava.util.*;


public class ApproveToBeInPurchaseOrderValidator implements IValidator {  
//************************************************ Getter and Setter for Year *********************************
	private int year; 
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

//************************************************ Getter and Setter for Number *******************************
	private int number;
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
//************************************************ Getter and Setter for Accept *******************************
	private boolean approve;
	public boolean isApprove() {
		return approve;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
	}
	
//************************************************ Getter and Setter for Sales Order **************************
	private PurchaseOrder purchaseOrder;
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	
//************************************************ Validation Message ****************************************
	public void validate(Messages errors)  
	throws Exception {
		if(purchaseOrder == null) 
			return;
		if(!approve) {
			errors.add("requisition_must_be_approved_first", year, number);  
		}
	}

}