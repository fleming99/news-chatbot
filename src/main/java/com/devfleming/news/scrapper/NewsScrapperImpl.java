package com.devfleming.news.scrapper;

import com.devfleming.news.usecases.NewsScrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsScrapperImpl implements NewsScrapper {

    @Override
    public List<List<String>> newsFromSjc(){

        String url = "https://g1.globo.com/sp/vale-do-paraiba-regiao/cidade/sao-jose-dos-campos/";

        List<List<String>> htmlList = new ArrayList<>();

        List<String> linksList = new ArrayList<>();

        List<String> newsList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(url).get();

            Elements elements = document.select("a:has(p)");

            for(Element e : elements){
                newsList.add(e.text());
                linksList.add(e.attr("href"));
            }

            htmlList.add(newsList);
            htmlList.add(linksList);

            return htmlList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<List<String>> newsFromJac() {
        String url = "https://g1.globo.com/sp/vale-do-paraiba-regiao/cidade/jacarei/";

        List<List<String>> htmlList = new ArrayList<>();

        List<String> linksList = new ArrayList<>();

        List<String> newsList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(url).get();

            Elements elements = document.select("a:has(p)");

            for(Element e : elements){
                newsList.add(e.text());
                linksList.add(e.attr("href"));
            }

            htmlList.add(newsList);
            htmlList.add(linksList);

            return htmlList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
