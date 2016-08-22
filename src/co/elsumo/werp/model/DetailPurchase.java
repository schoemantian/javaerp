package co.elsumo.werp.model;

import java.math.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import co.elsumo.werp.calculators.*;

@Embeddable
@View(members="product; quantity, supplierProductCode, pricePerUnit, amount")
public class DetailPurchase {	
//************************************************ Calculated property for Amount ****************************************	
	@Stereotype("MONEY")
	@Depends("pricePerUnit, quantity")
	public BigDecimal getAmount() {
	return new BigDecimal(quantity).multiply(getPricePerUnit());
	}

//************************************************ Price Per Unit property for Product ****************************************	
	@DefaultValueCalculator(
			value=PricePerUnitCalculator.class,
			properties=@PropertyValue(
			name="productStockCode",from="product.stockCode"))
	
			@Stereotype("MONEY")
			private BigDecimal pricePerUnit;
			public BigDecimal getPricePerUnit() {
				return pricePerUnit==null?
				BigDecimal.ZERO:pricePerUnit;
				}	
			
			public void setPricePerUnit(BigDecimal pricePerUnit) {
			this.pricePerUnit = pricePerUnit;
			}
	
//************************************************ Quantity Editable field ***********************************************
    private int quantity;
    public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
 
//************************************************ Link to Product *******************************************************
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
//******************************************** Supplier Product Code ********************************************
	@Column(length=15)
	  private String supplierProductCode;
	  
	  public String getSupplierProductCode() {
	      return supplierProductCode;
	  }
	  public void setSupplierProductCode(String supplierProductCode) {
	      this.supplierProductCode = supplierProductCode;
	  }
	
	
//************************************ JPA Callback Method for Total Amount ***********************************************	
	@PrePersist
	private void onTotalPersist(PurchaseParentDocument p) {
		p.recalculateAmount();
		p.recalculateVat();
		p.recalculateBase();
	}
			
	@PreUpdate
	private void onTotalUpdate(PurchaseParentDocument p) {
		p.recalculateAmount();
		p.recalculateVat();
		p.recalculateBase();
	}
			
	@PreRemove
	private void onTotalRemove(PurchaseParentDocument p) {
		if (p.isRemoving())return; 
			p.recalculateAmount();
			p.recalculateVat();
			p.recalculateBase();
	}
 
}