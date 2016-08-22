package co.elsumo.werp.model;

import javax.persistence.*;
import org.openxava.annotations.*;

@Entity
@View(members=
"Stock [" +
"stockCode, product, quantity, warehouse;" +
"]"
)
public class Stock{	
//********************************************* Product Stock Code ********************************************* 
	@Id @Column(length=8)
	private String stockCode;

	public String getStockCode() {
	    return stockCode;
	}
	public void setStockCode(String stockCode) {
	    this.stockCode = stockCode;
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
    @DescriptionsList
    private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
//******************************************** Stock Warehouse ********************************************     
    @ManyToOne(fetch=FetchType.LAZY,optional=true)
    @DescriptionsList
    private Warehouse warehouse;
    
    public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

 
}