package co.elsumo.werp.calculators;

import org.openxava.calculators.*;
import co.elsumo.werp.model.*;
import static org.openxava.jpa.XPersistence.*;

public class PricePerUnitCalculator implements ICalculator {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String productStockCode;
		
		public Object calculate() throws Exception {
			Product product = getManager().find(Product.class, productStockCode);
			return product.getPrice();
		}
			
		public void setProductStockCode(String productStockCode) {
			this.productStockCode = productStockCode;
		}
		
		public String getProductStockCode() {
			return productStockCode;
		}
	}