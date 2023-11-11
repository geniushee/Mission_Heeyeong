package org.example.view;

import org.example.controller.App_control;
import org.example.utils.util;

import java.util.Optional;
import java.util.stream.Stream;

public class App {
    private OutputAndSwitch outputAndSwitch; // 출력 및 스위치
    private final App_control appControl; // 컨트롤러
    private String input; // 입력 명령

    public App() {
        appControl = new App_control();
        outputAndSwitch = new OutputAndSwitch();
        input = "";
    }

    public void run(){
        System.out.println(Massage.startMassage);
        while(outputAndSwitch.getRunSwitch()) {
            System.out.print(Massage.inputRequest);
            input = util.scanner();
            outputAndSwitch = appControl.process(input,outputAndSwitch);
            if (!outputAndSwitch.getRunSwitch()) break;
            if(checkOutput()){
                outputAndSwitch.printOutput();
            }else if(checkStringStream()){
                outputAndSwitch.printListToString();
            }
        }
    }


    private boolean checkOutput(){
        Optional<String> checker = Optional.ofNullable(outputAndSwitch.getOutput());
        return checker.isPresent();
    }

    private boolean checkStringStream(){
        Optional<Stream<String>> checker = Optional.ofNullable(outputAndSwitch.getStringStream());
        return checker.isPresent();
    }
//
//    public void run() {
//
//        System.out.println(Massage.startMassage);
//        System.out.println(Massage.commandList);
//        while (runSwitch) {
//            System.out.print(Massage.inputRequest);
//            input = scanner.nextLine();
//            runSwitch = appControl.process(input);
//        }
//
//    }
}
