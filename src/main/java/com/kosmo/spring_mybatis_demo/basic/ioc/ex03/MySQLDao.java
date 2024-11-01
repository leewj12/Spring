package com.kosmo.spring_mybatis_demo.basic.ioc.ex03;

public class MySQLDao implements Dao{

    @Override
    public String daoInfo() {
        return "나는 MySQL DB와 연동합니다~";
    }
}
