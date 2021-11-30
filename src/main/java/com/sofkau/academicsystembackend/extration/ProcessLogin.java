package com.sofkau.academicsystembackend.extration;

import java.util.Map;

public interface ProcessLogin {
    void login();
    Map<String, String> cookies();
    void logout();
}