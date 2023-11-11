package org.example.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.datas.Quotation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
public class OutputAndSwitch {
    private boolean runSwitch;
    private List<Quotation> quotations;
    private String output;
    private Stream<String> stringStream;

    public OutputAndSwitch(){
        runSwitch = true;
        quotations = new ArrayList<>();
        output = "";
        stringStream = Stream.<String>builder().build();
    }

    public boolean getRunSwitch(){
        return runSwitch;
    }

    public void setRunSwitch(boolean bool){
        runSwitch = bool;
    }
//
//    public void printlnString(String str) {
//        output = str;
//        System.out.println(output);
//    }
//
//    public void printString(String str) {
//        output = str;
//        System.out.print(output);
//    }

    public void printListToString(){
        stringStream.filter(s -> !s.isBlank())
                .forEach(System.out::println);
        stringStream = null;
    }

    public void printOutput() {
        System.out.println(output);
        output = null;
    }
}
