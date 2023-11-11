package org.example.controller;

import org.example.servie.Acts;
import org.example.view.Massage;
import org.example.view.OutputAndSwitch;

public class App_control {
    Acts acts; // 서비스 부분
    Request Rq; // 명령어 정리 객체

    // 초기화
    public App_control() {
        acts = new Acts();
    }

    public OutputAndSwitch process(String cmd, OutputAndSwitch outputAndSwitch) {
        Rq = new Request(cmd);
        // 기능별 명령어
        switch (Rq.actcmd) {
            case "종료":
                return acts.actExit(); // 종료
            case "등록":
                return acts.actEnroll(outputAndSwitch); // 등록 기능
            case "목록":
                return acts.actList(outputAndSwitch); // 목록 기능
            case "삭제":
                return acts.actDel(Rq.options, outputAndSwitch); // 삭제 기능
            case "수정":
                return acts.actModify(Rq.options, outputAndSwitch); // 수정 기능
            case "빌드":
                return acts.quotationToJson(outputAndSwitch);
        }
        outputAndSwitch.setOutput(Massage.commandError + "\n" + Massage.commandList);
        return outputAndSwitch;
//        System.out.println("파일을 저장합니다. 파일명 : " + path);
//        acts.fileSave(path); // txt file save
//        System.out.println("프로그램을 종료합니다.");
    }
}
