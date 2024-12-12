package com.devfleming.news.controller;

import com.devfleming.news.usecases.NewsScrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelegramBotController extends TelegramLongPollingBot {

    private String botName;

    private SendMessage sendMessage = new SendMessage();

    @Autowired
    private NewsScrapper newsScrapper;

    private Map<Long, String> userState = new HashMap<>();

    public TelegramBotController(String botName, String botToken){
        super(botToken);
        this.botName = botName;
    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();

        long chatId = message.getChatId();

        String currentState = userState.getOrDefault(chatId, "START");

        switch (currentState){
            case "START":

                List<List<String>> jacNews = newsScrapper.newsFromJac();

                List<List<String>> sjcNews = newsScrapper.newsFromSjc();

                sendMessage.setChatId(chatId);
                sendMessage.setText("Olá! Aqui estão as notícias mais recentes da região de Jacareí e São José dos Campos.");
                sendMessageToUser(sendMessage);

                for(int i = 0; i < sjcNews.size(); i++){

                    sendMessage.setChatId(chatId);
                    sendMessage.setText(scrapNewsFromSjc(sjcNews, i));
                    sendMessageToUser(sendMessage);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                for(int i = 0; i < jacNews.size(); i++){

                    sendMessage.setChatId(chatId);
                    sendMessage.setText(scrapNewsFromJac(jacNews, i));
                    sendMessageToUser(sendMessage);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
        }
    }

    public String scrapNewsFromJac(List<List<String>> news, int index){
        List<String> onlyNewsJac = news.getFirst();

        List<String> onlyLinksJac = news.get(1);
        return onlyNewsJac.get(index) + "\n" +
                onlyLinksJac.get(index);
    }

    public String scrapNewsFromSjc(List<List<String>> news, int index){
        List<String> onlyNewsSjc = news.getFirst();

        List<String> onlyLinksSjc = news.get(1);
        return onlyNewsSjc.get(index) + "\n" +
                onlyLinksSjc.get(index);
    }

    public void sendMessageToUser(SendMessage sendMessage){
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return this.botName;
    }
}
