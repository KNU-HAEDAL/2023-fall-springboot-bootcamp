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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "/articles/new";
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
        return "/articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //log.info("저도 동작해요.");
        // 1. 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰 페이지 설정하기
        return "/articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 뷰 페이지 설정하기
        return "articles/edit";
    }

    // DTO는 전송할 때, Entity는 받아서 사용할 때 쓰는 형태이다.
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        // 2. 엔티티를 DB에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2. 기존 데이터 값을 갱신하기
        if(target != null){
            articleRepository.save(articleEntity); // 엔티티를 DB에 저장(갱신)
        }
        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!");
        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2. 대상 엔티티 삭제하기
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!"); // 휘발성 데이터 등록
        }
        // 3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }
}
