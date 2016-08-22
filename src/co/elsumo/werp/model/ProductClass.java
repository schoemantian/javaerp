package co.elsumo.werp.model;

import java.util.*;
import javax.persistence.*;
import org.openxava.annotations.*;
 
@Entity
public class ProductClass extends Identifiable {
 
    @Column(length=50) @Required
    private String name;
    
    @OneToMany(mappedBy="productClass")
    @ListProperties("stockCode, description, price")
    private Collection<Product> products;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

}