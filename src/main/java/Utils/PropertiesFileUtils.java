package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {
	
	public static String readPropertiesFile(String launchAppUrl,String key) throws IOException {
		String sValue = "";
		FileInputStream fis = new FileInputStream(new File(launchAppUrl));
		Properties p = new Properties();
		p.load(fis);
		sValue = p.getProperty(key);
		return sValue;
	}

}
