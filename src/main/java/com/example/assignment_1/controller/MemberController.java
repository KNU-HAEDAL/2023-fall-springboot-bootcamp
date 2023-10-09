package com.example.assignment_1.controller;

import com.example.assignment_1.dto.MemberForm;
import com.example.assignment_1.entity.Member;
import com.example.assignment_1.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j
@Controller //1. 컨트롤러임을 선언
public class MemberController {
    @Autowired //10.Autowired 선언
    private MemberRepository memberRepository; //9.repository 인터페이스 생성

    @GetMapping("/signup")
    public String signUpPage(){  //2. mapping
        return "members/new";
    }

    @PostMapping("/join")
    public String createMember(MemberForm form){//4. 폼 데이터 전송받기
        log.info(form.toString());
        //System.out.println(form.toString());
        Member member = form.toEntity(); //5. DTO를 엔티티로 변환
        log.info(member.toString());
        //System.out.println(member.toString());
        //8.레포지토리로 엔티티를 DB에 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        //System.out.println(saved.toString());

        return "redirect:/members/"+saved.getId();
    }

    @GetMapping("/members/{id}")  //특정 회원 조회
    public String show(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member",memberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        ArrayList<Member> memberEntityList = memberRepository.findAll();
        model.addAttribute("memberList",memberEntityList);
        return "members/index";
    }
    @GetMapping("/members/{id}/edit")  //수정1. 데이터 가져오기
    public String edit(@PathVariable Long id,Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member",memberEntity);
        return "members/edit";
    }
    @PostMapping("/members/update")  //수정3. 수정한 데이터 받아오기
    public String update(MemberForm form){
        //log.info(form.toString()); 왜 안뜨지?
        Member memberEntity = form.toEntity();
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        if (target != null){
            memberRepository.save(memberEntity);
        }
        return "redirect:/members/"+memberEntity.getId();
    }
    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){ //삭제
        Member target = memberRepository.findById(id).orElse(null);
        if (target != null){
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제되었습니다!");
        }
        return "redirect:/members";
    }

}
