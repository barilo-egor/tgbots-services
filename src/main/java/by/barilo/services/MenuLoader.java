package by.barilo.services;

import by.barilo.enums.MenuItemsAttribute;
import by.barilo.objects.MenuItem;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuLoader {
    private static final String MENU_ITEM_ATTRIBUTE_NAME = "item";

    private static List<MenuItem> menuItems;

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
            menuItems.add(parseAttributes(nodeList.item(i).getAttributes()));
        }
        return menuItems;
    }

    private static MenuItem parseAttributes(NamedNodeMap attributes) {
        MenuItem menuItem = new MenuItem();
        for(int j = 0; j < attributes.getLength(); j++) {
            Node attribute = attributes.item(j);
            setAttributeToMenuItem(menuItem, attribute);
        }
        return menuItem;
    }

    private static void setAttributeToMenuItem(MenuItem menuItem, Node attribute) {
        switch (MenuItemsAttribute.fromString(attribute.getNodeName())) {
            case ID:
                menuItem.setId(attribute.getNodeValue());
                break;
            case TITLE:
                menuItem.setTitle(attribute.getNodeValue());
                break;
            case MODULE_ID:
                menuItem.setModule(ModuleLoader.getById(attribute.getNodeValue()));
                break;
            case PARENT:
                menuItem.setParent(attribute.getNodeValue());
                break;
        }
    }

    public static ReplyKeyboard getMain() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        for(MenuItem menuItem : menuItems) {
            KeyboardRow row = new KeyboardRow();
            row.add(menuItem.getTitle());
            rows.add(row);
        }
        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }
}
