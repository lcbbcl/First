package com.licaibo.utils.date;

import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class DateUtils {

    public static void main(String[] args) {

        //yyyy-MM-dd HH:mm:ss
        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


    }
}
