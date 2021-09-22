package by.barilo.tgbotsservices;

import by.barilo.bot.TestBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TgbotsServicesApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TgbotsServicesApplication.class, args);
//        TestBot testBot = context.getBean(TestBot.class);
    }

}
