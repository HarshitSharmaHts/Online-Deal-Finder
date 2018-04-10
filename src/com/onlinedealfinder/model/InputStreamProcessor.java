package com.onlinedealfinder.model;

import java.io.InputStream;
import java.util.Scanner;

public class InputStreamProcessor {
    public static String getString(InputStream is) {
        String ret="";

        Scanner scanner = new Scanner(is);

        while (scanner.hasNext()) {
            ret += scanner.next() + " ";
        }

        ret = ret.trim();

        return ret;
    }
}
