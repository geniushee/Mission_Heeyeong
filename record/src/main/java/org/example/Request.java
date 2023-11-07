package org.example;

import java.util.HashMap;
import java.util.Map;

public class Request {
    String act; // 활동 찾기
    Map<String, String> opt; // 옵션 정리


    Request(String cmd) {
        // 활동 분리
        String[] s = cmd.split("\\?");
        act = s[0];
        opt = new HashMap<>();

        if (s.length > 1) {
            String[] options = s[1].split("=");
            for (int i = 0; i < options.length; i = i + 2) {
                opt.put(options[i], options[i + 1]);
            }
        }
    }
}
