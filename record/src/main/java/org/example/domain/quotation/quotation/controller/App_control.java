package org.example.domain.quotation.quotation.controller;

import org.example.domain.quotation.exportquotation.Export;
import org.example.domain.quotation.quotation.servie.Acts;
import org.example.global.Massage;
import org.example.global.OutputAndSwitch;

public class App_control {
    private final Acts acts; // 서비스 부분
    private Export Export; // 추출 부분
    private Request Rq; // 명령어 정리 객체

    // 초기화
    public App_control() {
        acts = new Acts();
    }

    public OutputAndSwitch process(String cmd, OutputAndSwitch outputAndSwitch) {
        Rq = new Request(cmd);
        // 기능별 명령어
        switch (Rq.getActcmd()) {
            case "종료":
                return acts.actExit(); // 종료
            case "등록":
                return acts.actEnroll(outputAndSwitch); // 등록 기능
            case "목록":
                return acts.actList(outputAndSwitch); // 목록 기능
            case "삭제":
                return acts.actDel(Rq.getOptions(), outputAndSwitch); // 삭제 기능
            case "수정":
                return acts.actModify(Rq.getOptions(), outputAndSwitch); // 수정 기능
            case "빌드":
                return acts.quotationToJson(outputAndSwitch);
            case "저장":
                Export = new Export(outputAndSwitch);
                return Export.ExportToTxt();
            case "입력오류":
                break;
        }
        outputAndSwitch.setOutput(Massage.commandError + "\n" + Massage.commandList); // 틀린 명령어 입력시 실행
        return outputAndSwitch;
    }

}
