package org.example.simpleDb;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article {
    private long id;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private String title;
    private String body;
    private boolean isBlind;
}
