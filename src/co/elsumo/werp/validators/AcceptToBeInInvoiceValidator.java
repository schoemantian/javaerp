package co.elsumo.werp.validators;

import org.openxava.validators.*;
import co.elsumo.werp.model.*;
import org.openxava.util.*;


public class AcceptToBeInInvoiceValidator implements IValidator {  
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
	private Invoice invoice;
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
//************************************************ Validation Message ****************************************
	public void validate(Messages errors)  
	throws Exception {
		if(invoice == null) 
			return;
		if(!accept) {
			errors.add("salesOrder_must_be_accepted_first", year, number);  
		}
	}

}