package com.licaibo.utils.lambada;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * JAVA8 Lambada表达式例子
 * @author licaibo
 * @create 2017-07-04 9:19
 */
public class LambadaUtils {

    public static void main(String[] args) {
        //java8 之前
		/*Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("11111111");

			}
		});
		thread.start();*/
        Thread thread1 = new Thread(() -> {
            System.out.println("2222222");
        });
        thread1.start();



        //java8之前
		/*Collections.sort(new ArrayList<>(),new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				return 0;
			}
		});*/
        Collections.sort(new ArrayList<>(), (o1, o2) -> {
                    return 0;
                }
        );





        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		/*for (String feature : features) {
			System.out.println(feature);
		}*/
        features.forEach((n) -> System.out.println(n));
        features.forEach(System.out::println);

        IntStream.range(0, features.size()).forEach(i -> {
            System.out.println(features.get(i));
        });




		/*
		 * 转大写
		 */
        List<String> wordList = Arrays.asList("a","b","c");
        List<String> output = wordList.stream().
                map(String::toUpperCase).
                collect(Collectors.toList());
        //System.out.println(output);

		/*
		 * 取偶数
		 */
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] newNums = Stream.of(sixNums).filter(i -> i%2==0).toArray(Integer[]::new);
        //System.out.println(newNums[0]+","+newNums[1]+","+newNums[2]);


		/*
		 * Optional,作为一个容器，它可能含有某值，或者不包含。使用它的目的是尽可能避免 NullPointerException
		 */
        System.out.println(getLength("abcd"));
        System.out.println(getLength(null));


    }



    public static int getLength(String str){
        //java8 之前
        //return str==null?-1:str.length();
        return Optional.ofNullable(str).map(String::length).orElse(-1);
    }


}
