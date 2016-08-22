package co.elsumo.werp.model;

import java.math.*;
import java.util.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

import co.elsumo.werp.calculators.*;

@Entity
@EntityListeners(Detail.class)
@View(members=
"number, date;" +
"data {" +
"customer;" +
"details;" +
"remarks;" +
"amounts [ " 
+ "baseAmount; "
+ "vatPercentage;"
+ "vat; "
+ "totalAmount;" +
"];" +
"}"
)
abstract public class SalesParentDocument extends Deletable{
	
//************************************************Year Method Added *********************************************
	@Hidden
	@Column(length = 4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int year;
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

//************************************************ Calculate the next number Method Added *********************
	@PrePersist
	public void calculateNumber() throws Exception {
	Query query = XPersistence.getManager()
	.createQuery("select max(i.number) from " +
			getClass().getSimpleName() + 
			" i where i.year = :year");
			query.setParameter("year", year);
			Integer lastNumber = (Integer) query.getSingleResult();
			this.number = lastNumber == null?1:lastNumber + 1;
	}
	
//************************************************Number Method Added *********************************************
	@ReadOnly
	private int number;
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

//************************************************ Date Method Added *********************************************
	@Required
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
//************************************************Link To Customer *********************************************
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@ReferenceView("Simple")
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
//************************************************ Business Logic Calculations *********************************
	@ElementCollection
	@ListProperties(
			"product.stockCode, product.description, " +
			"quantity, pricePerUnit, amount")
			private Collection<Detail> details = new ArrayList<Detail>();
	
//************************************************ Link To Details of Product *************************************
	public Collection<Detail> getDetails() {
		return details;
	}

	public void setDetails(Collection<Detail> details) {
		this.details = details;
	}
	
//************************************************ Business Base Amount Calculation *****************************
	@Stereotype("MONEY")
	public BigDecimal getBaseAmount() {
	BigDecimal result = new BigDecimal("0.00");
	for (Detail detail: getDetails()) {
	result = result.add(detail.getAmount());
	}
	return result;
	}
	
//************************************ JPA Callback Method for Base Amount ***********************************************	
	public BigDecimal recalculateBase() {
		setAmountBase(getBaseAmount());
		return amountBase;
	}
					
	@Stereotype("MONEY")
	private BigDecimal amountBase;
					
	public void setAmountBase(BigDecimal amountBase) {
		this.amountBase = amountBase;
	}
	
//************************************************ Business Vat Percentage Calculation *****************************
	@ReadOnly
	@Column(length = 15)
	@Required
	@DefaultValueCalculator(VatPercentageCalculator.class)
	private BigDecimal vatPercentage;
	public BigDecimal getVatPercentage() {
		return vatPercentage==null?
		BigDecimal.ZERO:vatPercentage;
		}
		public void setVatPercentage(BigDecimal vatPercentage) {
		this.vatPercentage = vatPercentage;
		}
		
		@Stereotype("MONEY")
		@Depends("vatPercentage")
		public BigDecimal getVat() {
		return getBaseAmount().multiply(getVatPercentage()).divide(new BigDecimal("100"));
		}
		
//************************************ JPA Callback Method for VAT Amount ***********************************************	
		public BigDecimal recalculateVat() {
			setAmountVat(getVat());
			return amountVat;
		}
				
		@Stereotype("MONEY")
			private BigDecimal amountVat;
				
		public void setAmountVat(BigDecimal amountVat) {
			this.amountVat = amountVat;
		}
	
//************************************************ Business Total Amount Calculation *****************************
		@org.hibernate.annotations.Formula("AMOUNT * 0.10")
		@Stereotype("MONEY")
		@Depends("baseAmount, vat")
		public BigDecimal getTotalAmount() {
		return getBaseAmount().add(getVat());
		}
		
//************************************ JPA Callback Method for Total Amount ***********************************************	
		public BigDecimal recalculateAmount() {
			setAmountTotal(getTotalAmount());
			return amountTotal;
			}
		
		@Stereotype("MONEY")
		private BigDecimal amountTotal;
		
		public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
		}
		
//************************************************ Notes at the Bottom *******************************************
	@Stereotype("MEMO")
	private String remarks;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

//************************************************ Code to show ifRemoving from DB *************************************	
	@Transient
	private boolean removing = false;
		boolean isRemoving() {
			return removing;
		}
		
	@PreRemove
		private void markRemoving() {
			this.removing = true;
		}
	
	@PostRemove
		private void unmarkRemoving() {
			this.removing = false;
		}

}