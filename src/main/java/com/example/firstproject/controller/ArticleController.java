package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j //로깅 기능을 위한 어노테이션 추가
@Controller// 1. 컨트롤러 선언
public class ArticleController {

    @Autowired //의존성 주입!
    private ArticleRepository articleRepository;
    //스프링 부트에서는 객체를 만들지 않아도 됩니다. 알아서 마들어줌
    @GetMapping("/articles/new") //url 요청 접수
    public String newAtricleForm(){ //메서드 생성 및 반환갑 작성
        return "articles/new";
    }

    @PostMapping("/articles/create")
    //뷰 페이지에서 폼 데이터를 post방식으로 전송했으므로 바을때 @PostMapping(), 괄호 안에는 받는 URL주소 넣기
    public String createArticle(ArticleForm form){//폼 데이터를 DTO로 받기
        log.info(form.toString());
        //System.out.println(form.toString());
        //1. DTO를 엔티티로 변환
        Article article=form.toEntity();
        log.info(article.toString());
        //2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //  System.out.println(saved.toString());
        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        //매개변수로 id 받아오기
        log.info("id = "+id);//id를 잘 받았는지 확인하는 로그 찍기
        //1. id를 조회해 데이터 가져오기
        Optional<Article> articleEntity = articleRepository.findById(id);//??
        //2. 모델에 데이터 등록하기
        model.addAttribute("artile",articleEntity);
        //3. 뷰 페이지 반환하기
        return "/articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        log.info("저도 동작해요");
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList);
        //3. 뷰 페이지 설정하기
        return "/articles/index";
    }
}
