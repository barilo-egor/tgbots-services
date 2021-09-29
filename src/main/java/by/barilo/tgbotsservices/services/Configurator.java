package by.barilo.tgbotsservices.services;

import by.barilo.tgbotsservices.objects.Configuration;

/**
 * @author Egor Barilo
 * @version 1.0
 */
public final class Configurator {
    private Configurator() {
    }

    /**
     * Первичный метод настройки библиотеки.
     * @param configuration - объект с параметрами конфигурации
     * @return true - если конфигурация прошла успешно, иначе false
     */
    public static boolean configure(Configuration configuration) {
        return ServicesPropertiesUtil.loadProperties()
                && configureMenuLoader(configuration.getMenuFile());
    }

    private static boolean configureMenuLoader(String filename) {
        try {
            MenuLoader.load(filename);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
