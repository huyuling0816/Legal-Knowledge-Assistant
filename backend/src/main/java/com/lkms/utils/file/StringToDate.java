package com.lkms.utils.file;

import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
public class StringToDate {
    public Date string2Date(String dateString) {
        String dateFormat = "yyyy年M月d日";

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println("日期解析失败: " + e.getMessage());
        }
        return null;
    }
}
