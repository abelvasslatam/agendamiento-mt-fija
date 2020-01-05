package tdp.backend.mt.fija.common.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertiesUtil {

	private static final Logger logger = LogManager.getLogger(PropertiesUtil.class);

	public static String getPropertyFromFile(final String strFileName, final String strProperty) {

		final Properties prop = new Properties();
		InputStream input = null;

		try {
			input = PropertiesUtil.class.getClassLoader().getResourceAsStream(strFileName);
			if (input == null) {
				logger.warn("No encontrado: " + strFileName);
				return null;
			}
			prop.load(input);
			return prop.getProperty(strProperty);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";

	}

}
