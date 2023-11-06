package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    @Setter
    @Getter
    static Scanner scanner; // 표준입력 클래스
    static boolean running; // 스위치

    int idCounter; // id 개수 표기

    List<Quotation> quotations; // 명언 저장소

    // 초기화
    public App() {
        scanner = new Scanner(System.in);
        running = true;
        idCounter = 1;
        quotations = new ArrayList<>();
    }

    public void run() {
        String cmd; // 명령어 받을 객체

        System.out.println("== 명언 앱 ==");

        //명언앱 구동스위치
        while (running) {
            System.out.print("명령)");
            cmd = scanner.nextLine();
            // 종료 조건
            switch (cmd){
                case "종료":
                    exitApp(); // 종료
                    break;
                case "등록":
                    enroll(); // 등록 기능
                    break;
                case "목록":
                    list(); // 목록 기능
                    break;
            }
        }
    }

    void exitApp() {
        running = false;
    }

    public void enroll() {
        System.out.print("명언 : "); // 명언 입력
        String content = scanner.nextLine();
        System.out.print("작가 : "); // 작가 입력
        String author = scanner.nextLine();

        Quotation q = new Quotation(idCounter, content, author); // id, 명언, 작가 객체에 저장
        idCounter++;
        quotations.add(q);  // 저장소에 저장
        System.out.println(q.id + "번 명언이 등록되었습니다.");
    }

    void list() {
        System.out.println("번호 / 작가 / 명언\n----------------------");
        for (Quotation q : quotations) {
            System.out.println(q.id + " / " + q.author + " / " + q.content);
        }
    }


}
