package com.kosmo.spring_mybatis_demo.basic.ioc.ex01;

import lombok.Setter;

import java.util.Date;
import java.util.Random;

@Setter //setter 메서드 구성
public class MessageBeanImpl implements MessageBean{

    //property, field, 멤버변수
    private String greeting;//null <=default 값
    private String name;//null

    private Date today;//null
    private Random random;//null
    //프로퍼티 값을 설정파일(appContext.xml)에 넣어줄 예정(injection)
    //setter injection, constructor injection
    public void sayHello(String ... names){
        System.out.println(greeting);
        if (names != null){
            for (String nm: names){
                System.out.print(nm + ", ");
            }//for--
        }//if----
    }//--------
    public void sayHi(){
        System.out.println(greeting + " " + name + " ^^");
        System.out.println("오늘 날짜는 : " + today.toString());
        System.out.println("행운의 숫자 : " + random.nextInt(100));
    }
}