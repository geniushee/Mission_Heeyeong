package org.example.datas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quotation {
    private int id; // 명언 Id
    private String content; // 명언 내용
    private String author; // 명언 작가

    public Quotation(){
    }
    public Quotation(int id, String content, String author){
        // 초기화
        this.id = id;
        this.content = content;
        this.author = author;
    }
}
