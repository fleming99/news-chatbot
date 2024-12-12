package com.devfleming.news.config;

import com.devfleming.news.controller.TelegramBotController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BotConfiguration.class);

    @Bean
    public TelegramBotController telegramBotController(@Value("${bot.name}") String botName,
                                                       @Value("${bot.token}") String botToken){

        TelegramBotController telegramBotController = new TelegramBotController(botName, botToken);

        try {
            var telegramApi = new TelegramBotsApi(DefaultBotSession.class);

            telegramApi.registerBot(telegramBotController);

        } catch (TelegramApiException e) {
            log.error("Exception during registration of the bot.");
        }
        return telegramBotController;
    }
}
