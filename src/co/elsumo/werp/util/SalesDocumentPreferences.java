package co.elsumo.werp.util;

import java.io.*;
import java.math.*;
import java.util.*;
import org.apache.commons.logging.*;
import org.openxava.util.*;

public class SalesDocumentPreferences {
	private final static String
	FILE_PROPERTIES="werpProperties.properties";
	
	private static Log log = LogFactory.getLog(SalesDocumentPreferences.class);
	
	private static Properties properties;
	
	private static Properties getProperties() {
		if (properties == null) { 
			PropertiesReader reader = 
					new PropertiesReader(SalesDocumentPreferences.class, FILE_PROPERTIES);
			try {properties = reader.get();
	}
			
	catch (IOException ex) {log.error(XavaResources.getString( "properties_file_error",FILE_PROPERTIES),
			ex);
		properties = new Properties();
		}
	}
		
	return properties;
	}
	
	public static BigDecimal getDefaultVatPercentage() { 
		return new BigDecimal(getProperties().getProperty("defaultVatPercentage"));
	}


}
