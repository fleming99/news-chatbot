package com.devfleming.news.usecases;

import java.util.List;

public interface NewsScrapper {

    List<List<String>> newsFromSjc();

    List<List<String>> newsFromJac();
}
