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

import java.util.List;
import java.util.Optional;

@Slf4j // 로깅 기능을 위한 어노테이션 추가
@Controller
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해놓은 리퍼지터리 객체 주입(DI, 의존성 주입)
    private ArticleRepository articleRepository;
    // 스프링 부트에서는 객체를 만들지 않아도 된다.
    // new ArticlerPositoryImpl() 하지 않아도 된다. 대신 @Autowired 어노테이션
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    // new.mustache에서 action="/articles/create"로 작성했으므로
    public String createArticle(ArticleForm form){ // dto package에 정의된 ArticleForm class
        log.info(form.toString());
        // System.out.println(form.toString()); // 메서드 생성 및 반환값 작성

        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        // System.out.println(article.toString());

        // 2. 리퍼지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());
        // System.out.println(saved.toString());
        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){ //@PathVariable 어노테이션으로 id를 매개변수로 받음
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        log.info("저도 동작해요.");
        // 1. 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }
}
