package by.barilo.tgbotsservices.enums;

public enum MenuItemsAttribute {
    ID("id"),
    TITLE("title"),
    MODULE_ID("moduleId"),
    PARENT("parent");

    String name;

    MenuItemsAttribute(String command) {
        this.name = command;
    }

    public static MenuItemsAttribute fromString(String value) {
        for(MenuItemsAttribute menuItemsAttribute : MenuItemsAttribute.values()) {
            if(menuItemsAttribute.name.equals(value)) return menuItemsAttribute;
        }
        throw new IllegalArgumentException("MenuItemsAttribute with name='" + value + "' not found.");
    }
}
