package by.barilo.tgbotsservices.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Egor Barilo
 * @version 1.0
 */
public final class ServicesPropertiesUtil {
    public static final String PROPERTIES_FILE_NAME = "config/services.properties";

    private static Properties properties;

    private ServicesPropertiesUtil() {
    }

    /**
     * Загрузка свойств из файла "config/services.properties".
     * @return true - если свойства загружены, иначе false.
     */
    static boolean loadProperties() {
        properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(PROPERTIES_FILE_NAME)){
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Получение проперти.
     * @param key ключ свйоства
     * @return значение свойства
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
