package by.barilo.tgbotsservices.services;

import by.barilo.tgbotsservices.enums.MenuItemsAttribute;
import by.barilo.tgbotsservices.objects.MenuItem;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Egor Barilo
 * @version 1.0
 */
public class MenuLoader {
    private static final String MENU_ITEM_ATTRIBUTE_NAME = "item";

    private static List<MenuItem> menuItems;

    /**
     * Загружает меню из xml-файла.
     * @param fileName относительный (к директории проекта/jar-архива) путь с названием.
     *                 К примеру: "config/menu.xml".
     * @return Если парсинг прошел успешно, возвращает true, иначе false.
     */
    public static boolean load(String fileName) {
        try {
            menuItems = parseMenuItems(DocumentBuilderFactory.newInstance().newDocumentBuilder()
                                    .parse(new File(fileName)).getElementsByTagName(MENU_ITEM_ATTRIBUTE_NAME));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static List<MenuItem> parseMenuItems(NodeList nodeList) {
        List<MenuItem> menuItems = new ArrayList<>();
        for(int i = 0; i < nodeList.getLength(); i++) {
            menuItems.add(createMenuItem(nodeList.item(i).getAttributes()));
        }
        return menuItems;
    }

    private static MenuItem createMenuItem(NamedNodeMap attributes) {
        MenuItem menuItem = new MenuItem();
        for(int i = 0; i < attributes.getLength(); i++) {
            switch (MenuItemsAttribute.fromString(attributes.item(i).getNodeName())) {
                case ID:
                    menuItem.setId(attributes.item(i).getNodeValue());
                    break;
                case TITLE:
                    menuItem.setTitle(attributes.item(i).getNodeValue());
                    break;
                case MODULE_ID:
                    menuItem.setModule(ModuleLoader.getById(attributes.item(i).getNodeValue()));
                    break;
                case PARENT:
                    menuItem.setParent(attributes.item(i).getNodeValue());
                    break;
            }
        }
        return menuItem;
    }

    /**
     * Возвращает сформированные пункты меню. Перед использованием метода следует сначала их загрузить
     * с помощью метода "load()".
     * @return сформированные пункты меню
     */
    public static List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
