package co.elsumo.werp.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class Warehouse extends Identifiable {
 
	@Column(length=50) @Required
    private String name;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy="warehouse")
	@ElementCollection
	@ListProperties(
			"product.stockCode, product.description, " +
			"quantity")
			private Collection<Stock> stock = new ArrayList<Stock>();
	
	public Collection<Stock> getStock() {
		return stock;
	}

	public void setstock(Collection<Stock> stock) {
		this.stock = stock;
	}
}