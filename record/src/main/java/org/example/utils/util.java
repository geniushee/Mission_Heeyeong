package org.example.utils;

import lombok.Setter;
import org.example.datas.Quotation;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class util {
    @Setter
    public static Scanner sc = new Scanner(System.in);
    public static String scanner(){
        return sc.nextLine();
    }

    public static int getKeyValue(Map<String, String> opt, String key) {
        return Integer.parseInt(opt.get(key));
    }

    public static int quoteToIndex(List<Quotation> quotations, int num) {
        for (int i = 0; i < quotations.size(); i++) {
            if (quotations.get(i).getId() == num) {
                return i;
            }
        }
        return -1;
    }

    public static String printAndScan(String print){
        System.out.print(print); // 명언 입력
        return scanner();
    }
}
