package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.example.utils.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    @Setter
    @Getter
    static Scanner scanner; // 표준입력 클래스
    static boolean running; // 스위치
    int idCounter; // id 개수 표기
    List<Quotation> quotations; // 명언 저장소
    Request Rq; // 명령어 정리 객체
    String path; // 파일 저장 위치


    // 초기화
    public App() {
        // 초기화
        scanner = new Scanner(System.in);
        running = true;
        idCounter = 1;
        quotations = new ArrayList<>();
        path = "./";
    }

    public void run() {
        String cmd; // 명령어 받을 객체

        System.out.println("저장된 파일을 불러 옵니다.");
        load(path); // txt파일
//        jsonstringToQuotation(); // json file load
        list();
        System.out.println("== 명언 앱 ==\n명령어 : 등록," +
                "목록, 삭제?id=0, 수정?id=0, 종료");

        //명언앱 구동스위치
        while (running) {
            System.out.print("명령)");
            cmd = scanner.nextLine();
            Rq = new Request(cmd);
            // 기능별 명령어
            switch (Rq.act) {
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
                    del(Rq.opt); // 삭제 기능
                    break;
                case "수정":
                    modify(Rq.opt); // 수정 기능
                    break;
            }
        }
        System.out.println("파일을 저장합니다. 파일명 : " + path);
        save(path); // txt file save
//        quotationToJsonstring(); // json file save
        System.out.println("프로그램을 종료합니다.");
    }

    private void modify(Map<String, String> opt) {
        // 수정기능
        int num = util.getKeyValue(opt, "id"); // 수정을 원하는 Id 도출
        int index = util.quoteToIndex(quotations, num); // id가 일치하는 명언 index 찾기
        if (index == -1) {
            System.out.println(num + "번 명언은 존재하지 않습니다.");
            return;
        }
        // 수정작업
        Quotation q = quotations.get(index);
        System.out.println("명언(기존) : " + q.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가(기존) : " + q.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine();
        // 수정값 입력
        q = new Quotation(num, content, author);
        quotations.set(index, q);
    }

    private void del(Map<String, String> opt) {
        int num = util.getKeyValue(opt, "id"); // 삭제를 원하는 Id 도출
        int index = util.quoteToIndex(quotations, num); // id가 일치하는 명언 index 찾기
        if (index == -1) {
            System.out.println(num + "번 명언은 존재하지 않습니다.");
        } else {
            quotations.remove(index); // 삭제
            System.out.println(num + "번 명언이 삭제되었습니다.");
        }
    }

    void exitApp() {
        running = false; // 구동 스위치 off
    }

    public void enroll() {
        System.out.print("명언 : "); // 명언 입력
        String content = scanner.nextLine();
        System.out.print("작가 : "); // 작가 입력
        String author = scanner.nextLine();

        Quotation q = new Quotation(idCounter, content, author); // id, 명언, 작가 객체에 저장
        quotations.add(q);  // 저장소에 저장
        System.out.println(q.getId() + "번 명언이 등록되었습니다.");
        idCounter++; // id 자동 증가
    }

    void list() {
        System.out.println("번호 / 작가 / 명언\n----------------------");
        // 모든 명언 불러오기
        for (Quotation q : quotations) {
            System.out.println(q.getId() + " / " + q.getAuthor() + " / " + q.getContent());
        }
    }

    void save(String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath + "save.json"))) {
            bufferedWriter.write(quotationToJsonstring());// String을 저장
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void load(String path) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "save.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("/");
                int id = 0;
                String content = "";
                String author = "";
                for (int i = 0; i < data.length; i++) {
                    switch (i % 3) {
                        case 0:
                            id = Integer.parseInt(data[i]);
                            break;
                        case 1:
                            content = data[i];
                            break;
                        case 2:
                            author = data[i];
                            break;
                    }

                }
                quotations.add(new Quotation(id, content, author));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String quotationToJsonstring() {
        List<String> jsonList = new ArrayList<>(); // 임시 list 생성
        try {
            ObjectMapper objectMapper = new ObjectMapper(); // json mapper 생성
            for (Quotation q : quotations) {
                // 객체를 jsonstring으로 변경 후 list에 저장
                jsonList.add(objectMapper.writeValueAsString(q));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonList.toString();
    }

//    void jsonstringToQuotation(){
//        try{
//            ObjectMapper objectMapper = new ObjectMapper();
//            Quotation q;
//            q = objectMapper.readValue(new File(path + "json.json"), Quotation.class);
//            quotations.add(q);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
}
