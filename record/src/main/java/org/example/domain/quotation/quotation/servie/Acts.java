package org.example.domain.quotation.quotation.servie;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.quotation.quotation.datas.Quotation;
import org.example.standard.utils.util;
import org.example.global.Massage;
import org.example.global.OutputAndSwitch;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Acts {
    private int idCounter;

    public Acts(){
        idCounter = 1;
    }

    public OutputAndSwitch actExit() {
        OutputAndSwitch output = new OutputAndSwitch();
        output.setRunSwitch(false);
        return output; // 구동 스위치 off
    }


    public OutputAndSwitch actModify(Map<String, String> opt, OutputAndSwitch outputAndSwitch) {
        // 수정기능
        int num = util.getKeyValue(opt, "id"); // 수정을 원하는 Id 도출
        int index = util.quoteToIndex(outputAndSwitch.getQuotations(), num); // id가 일치하는 명언 index 찾기
        if (index == -1) {
            System.out.println(num + Massage.doNotPresent);
            } else{
        // 수정작업
        Quotation q = outputAndSwitch.getQuotations().get(index);
        String content = util.printAndScan(Massage.quoteExist + q.getContent() + "\n" + Massage.quoteRequest);
        String author =util.printAndScan(Massage.authorExist + q.getAuthor() + "\n" + Massage.authorRequest);
        // 수정값 저장
        q = new Quotation(num, content, author);
        outputAndSwitch.getQuotations().set(index, q);
        }
    return outputAndSwitch;
    }

    public OutputAndSwitch actDel(Map<String, String> opt, OutputAndSwitch outputAndSwitch) {
        int num = util.getKeyValue(opt, "id"); // 삭제를 원하는 Id 도출
        int index = util.quoteToIndex(outputAndSwitch.getQuotations(), num); // id가 일치하는 명언 index 찾기
        if (index == -1) {
            outputAndSwitch.setOutput(num + Massage.doNotPresent);
        } else {
            outputAndSwitch.getQuotations().remove(index); // 삭제
            outputAndSwitch.setOutput(num + Massage.quoteDelete);
        }
        return outputAndSwitch;
    }



    public OutputAndSwitch actEnroll(OutputAndSwitch outputAndSwitch) {
        String content = util.printAndScan(Massage.quoteRequest); // 명언 입력
        String author = util.printAndScan(Massage.authorRequest); // 작가 입력
        // id, 명언, 작가 객체에 저장
        outputAndSwitch.getQuotations().add(new Quotation(idCounter, content, author));
        outputAndSwitch.setOutput(idCounter + Massage.enrollQuote);
        idCounter++; // id 자동 증가
        return outputAndSwitch;
    }

    public OutputAndSwitch actList(OutputAndSwitch outputAndSwitch) {
        System.out.println(Massage.listTitle);
        // 모든 명언 스트림으로 변경
        outputAndSwitch.setStringStream(outputAndSwitch.getQuotations().stream()
                .filter(quotation -> Optional.ofNullable(quotation).isPresent())
                .map(quotation -> quotation.getId() + " / " + quotation.getAuthor() + " / " + quotation.getContent()));
        return outputAndSwitch;
    }


    public OutputAndSwitch quotationToJson(OutputAndSwitch outputAndSwitch) {
        List<String> jsonList = new ArrayList<>(); // 임시 list 생성
        try {
            ObjectMapper objectMapper = new ObjectMapper(); // json mapper 생성
            for (Quotation q : outputAndSwitch.getQuotations()) {
                // 객체를 jsonstring으로 변경 후 list에 저장
                jsonList.add(objectMapper.writeValueAsString(q));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // data.json에 json으로 데이터 저장
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Const.jsonFilePath))) {
            bufferedWriter.write(jsonList.toString());
            outputAndSwitch.setOutput(Massage.jsonSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputAndSwitch;
    }


}
