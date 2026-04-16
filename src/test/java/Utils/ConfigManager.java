package Utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties = new Properties();

    // Static block → loads config automatically
    static {
        load();
    }

    // Load environment-specific config file
    private static void load() {

        String env = System.getProperty("env", "qa"); // default = qa
        String fileName = "config-" + env + ".properties";

        try (InputStream is = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (is == null) {
                throw new RuntimeException("❌ Config file not found: " + fileName);
            }

            properties.load(is);

            System.out.println("✅ Loaded config file: " + fileName);

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to load config file: " + fileName, e);
        }
    }

    // Get property value
    public static String get(String key) {

        String value = properties.getProperty(key);

        if (value == null || value.isEmpty()) {
            throw new RuntimeException("❌ Key not found in config: " + key);
        }

        return value;
    }

    // Optional: Reload config (useful in runtime switching)
    public static void reload() {
        load();
    }
}