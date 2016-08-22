package co.elsumo.werp.validators;

import org.openxava.validators.*;
import co.elsumo.werp.model.*;
import org.openxava.util.*;


public class AcceptToBeInSalesOrderValidator implements IValidator {  
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
	private boolean accept;
	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}
	
//************************************************ Getter and Setter for Sales Order **************************
	private SalesOrder salesOrder;
	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}
	
//************************************************ Validation Message ****************************************
	public void validate(Messages errors)  
	throws Exception {
		if(salesOrder == null) 
			return;
		if(!accept) {
			errors.add("quotation_must_be_accepted_first", year, number);  
		}
	}

}