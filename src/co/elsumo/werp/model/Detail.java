package co.elsumo.werp.model;

import java.math.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import co.elsumo.werp.calculators.*;

@Embeddable
@View(members="product; quantity, pricePerUnit, amount")
public class Detail{	
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
			name="productNumber",from="product.stockCode"))
	
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
	
	
//************************************ JPA Callback Method for Total Amount ***********************************************	
		@PrePersist
		private void onTotalPersist(SalesParentDocument o) {
			o.recalculateAmount();
			o.recalculateVat();
			o.recalculateBase();
			}
		
		@PreUpdate
		private void onTotalUpdate(SalesParentDocument o) {
			o.recalculateAmount();
			o.recalculateVat();
			o.recalculateBase();
			}
		
		@PreRemove
		private void onTotalRemove(SalesParentDocument o) {
			o.recalculateAmount();
			o.recalculateVat();
			o.recalculateBase();
			}

		
		
}