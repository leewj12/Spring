package com.kosmo.spring_mybatis_demo.basic.ioc.ex03;

public class OracleDao implements Dao{

    @Override
    public String daoInfo() {
        return "나는 Oracle DB와 연동합니다";
    }
}
