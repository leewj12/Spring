package com.kosmo.user.controller;

import com.kosmo.user.domain.MemberDTO;
import com.kosmo.user.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@Autowired, @Inject, @Resource ==> DI 할때 사용하는 어노테이션
//@Inject, @Resource ==> 추가 라이브러리가 필요함. build.gradle에 등록해야 함
//DI 종류
//1. Field Injection
//2. 생성자 Injection ==> 스프링 3.2 정도부터 디폴트
//3. Setter Injection

@Controller
@Slf4j //log를 사용할 수 있음
public class MemberController {

    @Autowired
    private MemberService userService; //필드 인젝션

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String joinForm(){

        return "member/signup";
        //"WEB-INF/views/member/signup.jsp"
    }//------------------
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String joinProcess(MemberDTO user, Model model){
        //html의 input name 과 MemberDTO 객체의 property 명을 같게 준다면
        //스프링이 알아서 MemberDTO 객체에 사용자가 입력한 값을 넣어준다
        log.info("user: {}", user);
        //유효성 체크
        if(user.getName() == null || user.getUserId() == null || 
                user.getUserPw() == null || user.getName().trim().isBlank() ||
        user.getUserId().trim().isBlank() || user.getUserPw().trim().isBlank()){

            return "redirect:signup";
            //rediret: 을 뷰네임에 접두어로 붙여주면 redirect 방식으로 이동한다
            //디폴트 이동방식은 forward이다
        }
        //서비스 객체의 메서드를 호출
        int n = userService.insertMember(user);
        String msg = (n > 0) ? "회원가입 성공 - 로그인 페이지로 이동합니다" : "회원가입 실패";
        String loc = (n > 0) ? "/login" : "javascript:history.back()";
        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);

        return "message";
        //"WEB-INF/views/message.jsp"
    }//-----------------------

    @RequestMapping("/admin/users")
    public String memberList(Model model){
        List<MemberDTO> arrList = userService.listMember();
        model.addAttribute("members", arrList);

        return "member/memberAll";
    }//----------------------------------------
    @RequestMapping(value = "/admin/memberInfo", method = RequestMethod.POST)
    public String memberInfo(Model model, @RequestParam(defaultValue = "0") int idx){// -parameters
        log.info("idx: {}", idx);
        if (idx == 0){
            return "redirect:users";
        }
        //idx(PK) ==> 단일 객체를 반환
        MemberDTO user = userService.findMemberByIdx(idx);
        model.addAttribute("user", user);

        return "member/memberInfo";
    }

    @PostMapping("/admin/memberUpdate")
    public String memberUpdate(MemberDTO user, Model model){
        log.info("user = {}", user);
        if (user.getIdx() == 0 || user.getName().trim().isBlank()
                || user.getUserId().trim().isBlank()){
            return "redirect:users";
        }
        int n = userService.updateMember(user);
        String msg = (n > 0) ? "수정 성공" : "수정 실패";
        String loc = (n > 0) ? "users" : "javascript:history.back()";

        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);
        return "message";
    }//-----------------------------------

//    @PostMapping("/admin/memberDelete")
//    public String memberDelete(MemberDTO user, Model model){
//        log.info("user = {}", user);
//        if (user.getIdx() == 0){
//            return "redirect:users";
//        }
//        int n = userService.deleteMemberByIdx(user.getIdx());
//        String msg = (n > 0) ? "삭제 성공" : "삭제 실패";
//        String loc = (n > 0) ? "users" : "javascript:history.back()";
//
//        model.addAttribute("msg", msg);
//        model.addAttribute("loc", loc);
//        return "message";
//    }
    @PostMapping("/admin/memberDelete")
    public String memberDelete(Model model, @RequestParam(defaultValue = "0") int idx){
        log.info("idx: {}", idx);
        if (idx == 0){
            return "redirect:user";
        }
        int n = userService.deleteMemberByIdx(idx);
        String msg = (n > 0) ? "삭제 성공": "삭제 실패";
        String loc = "/admin/users";
        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);
        return "message";
    }

    @GetMapping("idCheck")
    public String idCheck(){
        return "member/idCheck";
    }

    @PostMapping("/idCheck")
    public String idCheckResult(Model model, @RequestParam(defaultValue = "")
                                    String  userId){

        if (userId.trim().isBlank()){
            model.addAttribute("msg", "아이디를 입력하세요");
            model.addAttribute("loc", "javascript:history.back()");
            return "message";
        }
        boolean isUse = userService.idCheck(userId.trim());
        String msg = (isUse) ? userId + "는 사용 가능합니다" : userId + "는 이미 사용중 입니다";
        String result = (isUse) ? "Ok" : "Fail";
        model.addAttribute("msg", msg);
        model.addAttribute("result", result);
        model.addAttribute("userId", userId);
        return "member/idCheckResult";
    }

}//class----------------
