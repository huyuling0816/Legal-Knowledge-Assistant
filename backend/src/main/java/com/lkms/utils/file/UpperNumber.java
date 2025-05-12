package com.lkms.utils.file;

import java.util.HashMap;

public class UpperNumber {
    private static final HashMap<Integer, String> digitMap = new HashMap<>();

    static {
        digitMap.put(0, "零");
        digitMap.put(1, "一");
        digitMap.put(2, "二");
        digitMap.put(3, "三");
        digitMap.put(4, "四");
        digitMap.put(5, "五");
        digitMap.put(6, "六");
        digitMap.put(7, "七");
        digitMap.put(8, "八");
        digitMap.put(9, "九");
    }

    public static String convertToChinese(int number) {
        if (number < 0 || number > 9999) {
            throw new IllegalArgumentException("Number out of range. Please provide a number between 0 and 9999.");
        }

        StringBuilder sb = new StringBuilder();

        if (number == 0) {
            return digitMap.get(0);
        }

        if (number >= 1000) {
            int thousands = number / 1000;
            sb.append(digitMap.get(thousands)).append("千");
            number %= 1000;
        }

        if (number >= 100) {
            int hundreds = number / 100;
            sb.append(digitMap.get(hundreds)).append("百");
            number %= 100;
        }

        if (number >= 10) {
            int tens = number / 10;
            if (tens != 1) {
                sb.append(digitMap.get(tens));
            }
            sb.append("十");
            number %= 10;
        }

        if (number > 0) {
            sb.append(digitMap.get(number));
        }

        return sb.toString();
    }
}
