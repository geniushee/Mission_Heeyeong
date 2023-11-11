package org.example.controller;

import java.util.HashMap;
import java.util.Map;

public class Request {
    String actcmd; // 활동 찾기
    Map<String, String> options; // 옵션 정리


    Request(String cmd) {
        // 활동 분리
        String[] s = cmd.split("\\?");
        actcmd = s[0];
        options = new HashMap<>();

        if (s.length > 1) {
            String[] optionsArray = s[1].split("=");
            for (int i = 0; i < optionsArray.length; i = i + 2) {
                this.options.put(optionsArray[i], optionsArray[i + 1]);
            }
        }
    }
}
