package com.example.assignment_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //이 클래스는 컨트롤러 이다
public class FirstController {
    @GetMapping("/hi") //URL 입력
    public String niceToMeetYou(Model model){ //Model 객체 받아오기
        model.addAttribute("username","유정"); //변수 등록
        return "greetings"; //greatings 파일을 찾아서 웹으로 전송
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname","홍길동");
        return "goodbye";
    }

}
