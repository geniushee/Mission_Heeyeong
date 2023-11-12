package org.example.domain.quotation.quotation.datas;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 이걸 사용해서 생성자를 만들자
public class Quotation {
    private final int id; // 명언 Id
    private final String content; // 명언 내용
    private final String author; // 명언 작가
}
