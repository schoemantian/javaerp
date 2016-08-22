package co.elsumo.werp.calculators;

import org.openxava.calculators.*; 
import co.elsumo.werp.util.*;

public class VatPercentageCalculator implements ICalculator {
	public Object calculate() throws Exception {
		return SalesDocumentPreferences.getDefaultVatPercentage();
	}
}