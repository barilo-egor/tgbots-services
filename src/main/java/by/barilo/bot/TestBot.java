package by.barilo.bot;

import by.barilo.services.MenuLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@PropertySource("classpath:bot.properties")
public class TestBot extends TelegramLongPollingBot {
    @Value("${bot.username}")
    public String botUsername;
    @Value("${bot.token}")
    public String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(SendMessage.builder().chatId(update.getMessage().getChatId().toString()).text("qwe").replyMarkup(MenuLoader.getMain()).build());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
