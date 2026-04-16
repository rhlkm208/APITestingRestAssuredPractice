package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop;

	static {
		load();
	}

	public static void load() {

		String configPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "config.properties";
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(configPath);
			prop.load(fis);
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

}
