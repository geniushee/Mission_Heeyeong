package org.example.utils;

import org.example.Quotation;

import java.util.List;
import java.util.Map;

public class util {
    public static int getKeyValue(Map<String, String> opt, String key) {
        return Integer.parseInt(opt.get(key));
    }

    public static int quoteToIndex(List<Quotation> quotations, int num) {
        int index = -1;
        for (int i = 0; i < quotations.size(); i++) {

            if (quotations.get(i).getId() == num) {
                index = i;
                break;
            }
        }
        return index;
    }
}
