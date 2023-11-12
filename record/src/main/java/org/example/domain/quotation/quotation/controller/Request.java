package org.example.domain.quotation.quotation.controller;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public class Request {
    private String actcmd; // 활동 찾기
    private Map<String, String> options; // 옵션 정리


    public Request(String cmd) {
        // 활동 분리
        String[] s = cmd.split("\\?");
        actcmd = s[0].trim(); // 공백 잘못 입력 깨끗하게 처리
        options = new HashMap<>();

        // id=1&url=https://&...
        if (s.length > 1) {
            options = Arrays.stream(s[1].split("&")) // '&'으로 split
                    .map(entry -> entry.split("=", 2)) // '&'로 분리한 Stream<String[]>을 '='으로 split
                    .collect(HashMap::new, (map, entry) -> map.put(entry[0], entry[1]), HashMap::putAll); // 분리된 배열을 map으로 collect
        }
        checkId();
    }

    private void checkId() {
        Optional<String> checker = Optional.ofNullable(options.get("id")); // id를 Optional에 넣기
        if (checker.isPresent()) {
            if (checker.filter(s -> s.matches("\\d+")) // 정수의 배열인지 필터링
                    .isEmpty()) {
                actcmd = "입력오류"; // 비어있다면 오류 출력
            }
        }
    }
}
