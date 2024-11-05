package com.kosmo.spring_mybatis_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
//POJO : Plain Old Java Object
@Controller
public class HomeController {

    @RequestMapping(value="/home", method= RequestMethod.GET)
    public String home(){

        return "hello";//뷰 네임
        //물리적 경로: "/WEB-INF/views/hello.jsp"
        //application.properties파일에 접두어,접미어 설정을 하였음
    }
    // "/test" url pattern ==> test.jsp를 보여주도록 메서드 구성
    @GetMapping("/test")
    public String showTest(Model model){
        //request저장 ==> 스프링에서는 Model 또는 ModelAndView객체에
        //저장
        model.addAttribute("msg","반가워 스프링부트");

        return "test";
    }
    //뷰네임을 반환하지 않으면 @RequestMapping/@GetMapping ...
    // url에 접두어,접미어를 붙여 이동
    // WEB-INF/views/main.jsp를 찾는다
    @GetMapping("/main")
    public void main(){
    }

}
