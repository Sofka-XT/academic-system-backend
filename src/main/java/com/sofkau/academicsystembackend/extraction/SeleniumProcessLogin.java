package com.sofkau.academicsystembackend.extraction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SeleniumProcessLogin implements ProcessLogin {
    protected WebDriver driver;
    private static final String URL_BASE = "https://campus.sofka.com.co";
    private boolean isLogin;
    public SeleniumProcessLogin(){
        driver = new HtmlUnitDriver();
        isLogin = false;
    }
    @Override
    public void login() {
        if(!isLogin) {
            driver.get(URL_BASE + "/index");
            driver.findElement(By.name("login")).sendKeys("raul.alzate@sofka.com.co");
            driver.findElement(By.name("password")).sendKeys("Rauloko250360.");
            driver.findElement(By.name("submit")).click();
            isLogin = true;
        }
    }

    @Override
    public Map<String, String> cookies() {
        Map<String, String> cookies = new HashMap<>();
        driver.manage().getCookies().forEach(cookie -> cookies.put(cookie.getName(), cookie.getValue()));
        return cookies;
    }

    @Override
    public void logout() {
        if (driver != null) {
           // driver.quit();
        }
    }
}