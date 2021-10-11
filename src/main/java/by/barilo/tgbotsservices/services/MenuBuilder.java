package by.barilo.tgbotsservices.services;

import by.barilo.tgbotsservices.enums.ReplyButton;
import by.barilo.tgbotsservices.objects.MenuItem;
import org.springframework.util.ObjectUtils;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;
import java.util.stream.Collectors;

public final class MenuBuilder {

    private static ReplyKeyboardMarkup mainKeyboard;

    private MenuBuilder() {
    }

    public static boolean build() {
        if(ObjectUtils.isEmpty(MenuLoader.getMenuItems())) return false;
        mainKeyboard = KeyboardFactory.buildReply(2, false, true,
                getParents(MenuLoader.getMenuItems()).stream()
                        .map(i -> ReplyButton.builder().text(i.getTitle()).build())
                        .collect(Collectors.toList()));
        return true;
    }

    private static List<MenuItem> getParents(List<MenuItem> menuItems) {
        return menuItems.stream().filter(ObjectUtils::isEmpty).collect(Collectors.toList());
    }

    public static ReplyKeyboardMarkup getMain() {
        return mainKeyboard;
    }

    public static boolean isParent(String title) {
        return MenuLoader.getMenuItems().stream()
                .anyMatch(i -> title.equals(i.getParent()));
    }

    public static ReplyKeyboardMarkup getMenuByTitle(String title) {
        return KeyboardFactory.buildReply(MenuLoader.getNumberOfColumns(), true, true,
                getMenuItemsByParent(title).stream()
                        .map(i -> ReplyButton.builder().text(i.getTitle())
                                .build())
                        .collect(Collectors.toList()));
    }

    private static List<MenuItem> getMenuItemsByParent(String parent) {
        return MenuLoader.getMenuItems().stream()
                .filter(i -> parent.equals(i.getParent()))
                .collect(Collectors.toList());
    }
}
