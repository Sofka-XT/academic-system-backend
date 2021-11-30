package com.sofkau.academicsystembackend.extraction;

import java.util.Map;

public interface ProcessLogin {
    void login();
    Map<String, String> cookies();
    void logout();
}