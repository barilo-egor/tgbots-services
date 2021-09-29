package by.barilo.tgbotsservices.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

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
        // for tests
    }
}
