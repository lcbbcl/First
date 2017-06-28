package com.gitlab.ci.web;



import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;

import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class DataUtils {

    public static void main(String[] args) {

        //yyyy-MM-dd HH:mm:ss
        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


    }
}
