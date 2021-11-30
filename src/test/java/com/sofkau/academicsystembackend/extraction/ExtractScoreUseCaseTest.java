package com.sofkau.academicsystembackend.extraction;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class ExtractScoreUseCaseTest {

    @Test
    public void validation(){
        List<String> emails = new ArrayList<>();
        emails.add("ktns0930@gmail.com");
        emails.add("juanfth2001@gmail.com");
        emails.add("141013@unsaac.edu.pe");
        emails.add("sebasruigalle62@gmail.com");
        emails.add("yamsoncalapzu@gmail.com");
        var seleniumLogin = new SeleniumProcessLogin();
        var usecase = new ExtractScoreUseCase(seleniumLogin);
        var list = usecase.apply("reports/listtestusers/id:2121,type:Test,group:,branch:,completion_status:", emails);
        System.out.println(list);
    }

}