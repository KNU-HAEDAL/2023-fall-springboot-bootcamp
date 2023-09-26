package com.example.firstproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username","김민승");
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeyouNext(Model model){
        model.addAttribute("nickname","김민승");
        return "goodbye";
    }
}
