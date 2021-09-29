package by.barilo.tgbotsservices.services;

import by.barilo.tgbotsservices.enums.MenuItemsAttribute;
import by.barilo.tgbotsservices.objects.MenuItem;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Egor Barilo
 * @version 1.0
 */
final class MenuLoader {
    private MenuLoader() {
    }

    private static final String MENU_ITEM_ATTRIBUTE_NAME = "item";

    private static List<MenuItem> menuItems;

    /**
     * Загружает меню из xml-файла.<br/>
     * <b>Теги: </b> <br/>
     * menu - общий тег <br/>
     * item - кнопка <br/>
     * <b>Атрибуты:</b> <br/>
     * title - наименование кнопки <br/>
     * id - идентификатор кнопки (нужен для дочерних кнопок) <br/>
     * parent - ссылка на родительскую кнопку <br/>
     * moduleId - идентификатор модуля, который следует запустить по нажатию <br/>
     * @param fileName относительный (к директории проекта/jar-архива) путь с названием.
     *                 К примеру: "config/menu.xml".
     */
    public static void load(String fileName) throws ParserConfigurationException, IOException, SAXException {
        menuItems = parseMenuItems(DocumentBuilderFactory.newInstance().newDocumentBuilder()
                                    .parse(new File(fileName)).getElementsByTagName(MENU_ITEM_ATTRIBUTE_NAME));
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
