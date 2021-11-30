package com.sofkau.academicsystembackend.extration;

import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class ExtractScoreUseCase implements BiFunction<String,List<String>, List<Score>> {
    private static final String URL_BASE = "https://campus.sofka.com.co";
    private final ProcessLogin processLogin;


    public ExtractScoreUseCase(ProcessLogin processLogin){
        this.processLogin = processLogin;
    }

    @Override
    public List<Score> apply(String pathIdentity, List<String> emails) {
        processLogin.login();
        try {
            Connection.Response response =
                    Jsoup.connect(URL_BASE+"/"+pathIdentity)
                            .userAgent("Mozilla/5.0")
                            .timeout(10 * 1000)
                            .cookies(processLogin.cookies())
                            .method(Connection.Method.POST)
                            .followRedirects(true)
                            .execute();
            processLogin.logout();
           return new Gson().fromJson(response.body(), DataResponse.class).getData().stream()
                    .filter(d -> d.get(5).contains("Terminado"))
                    .map(d -> new Score(d.get(2), d.get(6)))
                    .filter(e -> emails.stream().anyMatch(email -> email.equals(e.getName())))
                    .collect(Collectors.toList());
        } catch (IOException e) {
           throw new ExtractScoreException();
        }
    }


}