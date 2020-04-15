package Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ConfigProperties {
    private static final Properties PROPERTIES;

    public static final String BASE_URL;

    static {
        PROPERTIES = new Properties();
        URL properties = ClassLoader.getSystemResource("config.properties");
        try {
            PROPERTIES.load(properties.openStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        BASE_URL = PROPERTIES.getProperty("baseUrl");
    }

    public static String getProperty(String key){
        return PROPERTIES.getProperty(key);
    }
}
