package com.kosmo.spring_mybatis_demo.basic.ioc.ex01;

public interface MessageBean {

    void sayHello(String ... names);
        //가변 인수  names=>String[]
    void sayHi();
}
