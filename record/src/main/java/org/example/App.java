package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

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
        String act; // 어떤 활동을 할 것인가
        Map<String, String> opt; // 활동 외 다양한 옵션

        System.out.println("== 명언 앱 ==");

        //명언앱 구동스위치
        while (running) {
            System.out.print("명령)");
            cmd = scanner.nextLine();
            act = cmdSplit(cmd); // 활동 찾기
            opt = optionSplit(cmd); // 옵션 정리
            // 종료 조건
            switch (act){
                case "종료":
                    exitApp(); // 종료
                    break;
                case "등록":
                    enroll(); // 등록 기능
                    break;
                case "목록":
                    list(); // 목록 기능
                    break;
                case "삭제":
                    del(opt);
                    break;
            }
        }
    }

    String cmdSplit(String cmd){
        // 활동 분리
        String[] s = cmd.split("\\?");
        return s[0];
    }

    Map<String, String> optionSplit(String cmd){
        String[] s = cmd.split("\\?");
        Map<String, String> map = new HashMap<>();
        if (s.length > 1){
        String[] options = s[1].split("=");
        for (int i = 0; i<options.length;i+=2){
            map.put(options[i],options[i+1]);
        }}
        return map;
    }





    private void del(Map<String, String> opt) {
        int num = Integer.parseInt(opt.get("id"));
        int index = -1;
        for (int i = 0; i < quotations.size();i++){
            if (quotations.get(i).id == num){
                index = i;
                break;
            } else {
                index = -1;
            }
        }
        if (index == -1){
            System.out.println(num + "번 명언은 존재하지 않습니다.");
        } else{
            quotations.remove(index);
            System.out.println(num + "번 명언이 삭제되었습니다.");
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
