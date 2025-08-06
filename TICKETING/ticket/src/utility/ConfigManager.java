package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private Properties properties;

    public ConfigManager(String filePath) throws IOException {
        properties = new Properties();
        FileInputStream fis = new FileInputStream(filePath);
        properties.load(fis);
        fis.close();
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }
}
