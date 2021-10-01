package by.barilo.tgbotsservices.services;

import by.barilo.tgbotsservices.enums.InlineType;
import by.barilo.tgbotsservices.enums.ReplyButton;
import by.barilo.tgbotsservices.objects.InlineButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public final class KeyboardFactory {
    private KeyboardFactory() {
    }

    public static InlineKeyboardMarkup buildInline(List<InlineButton> buttons) {
        return buildInline(buttons, 1, InlineType.CALLBACK_DATA);
    }

    public static InlineKeyboardMarkup buildInline(List<InlineButton> buttons, int numberOfColumns) {
        return buildInline(buttons, numberOfColumns, InlineType.CALLBACK_DATA);
    }

    public static InlineKeyboardMarkup buildInline(List<InlineButton> buttons, InlineType inlineType) {
        return buildInline(buttons, 1, inlineType);
    }

    public static InlineKeyboardMarkup buildInline(List<InlineButton> buttons, int numberOfColumns,
                                                   InlineType inlineType) {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        int j = 0;

        for(int i = 0; i < buttons.size(); i++) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(buttons.get(i).getText());
            String data = buttons.get(i).getData();
            switch (inlineType) {
                case URL:
                    inlineKeyboardButton.setUrl(data);
                    break;
                case CALLBACK_DATA:
                    inlineKeyboardButton.setCallbackData(data);
                    break;
                case SWITCH_INLINE_QUERY:
                    inlineKeyboardButton.setSwitchInlineQuery(data);
                    break;
                case SWITCH_INLINE_QUERY_CURRENT_CHAT:
                    inlineKeyboardButton.setSwitchInlineQueryCurrentChat(data);
                    break;
            }
            row.add(inlineKeyboardButton);
            j++;
            if(j == numberOfColumns || i == (buttons.size() - 1)) {
                rows.add(row);
                row = new ArrayList<>();
                j = 0;
            }
        }
        keyboard.setKeyboard(rows);
        return keyboard;
    }

    public static ReplyKeyboardMarkup buildReply(List<ReplyButton> buttons) {
        return buildReply(1, false, true, buttons);
    }

    public static ReplyKeyboardMarkup buildReply(int numberOfColumns, List<ReplyButton> buttons) {
        return buildReply(numberOfColumns, false, true, buttons);
    }

    public static ReplyKeyboardMarkup buildReply(int numberOfColumns, boolean oneTime, boolean resize, List<ReplyButton> buttons) {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setOneTimeKeyboard(oneTime);
        keyboard.setResizeKeyboard(resize);
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        int j = 0;

        for(int i = 0; i < buttons.size(); i++){
            KeyboardButton keyboardButton = new KeyboardButton();
            keyboardButton.setRequestContact(buttons.get(i).isRequestContact());
            keyboardButton.setRequestLocation(buttons.get(i).isRequestContact());
            keyboardButton.setText(buttons.get(i).getText());
            row.add(keyboardButton);
            j++;
            if(j == numberOfColumns || i == (buttons.size() - 1)) {
                rows.add(row);
                row = new KeyboardRow();
                j = 0;
            }
        }

        keyboard.setKeyboard(rows);
        return keyboard;
    }
}

