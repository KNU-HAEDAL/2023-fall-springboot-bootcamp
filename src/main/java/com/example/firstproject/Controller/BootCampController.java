package com.example.firstproject.Controller;

import com.example.firstproject.dto.ArticleUpdateForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BootCampController {

    @Autowired
    ArticleRepository articleRepository;
    //"articles/{{articles.id}}/edit"
    @GetMapping("articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article target=articleRepository.findById(id).orElse(null);
        model.addAttribute("article",target);

        return "/articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(ArticleUpdateForm dto){
        //1. dto에서 id추출
        long id=dto.getId();
        //2. id로 이미 저장되어있던 Article 받기
        Article target=articleRepository.findById(id).orElse(null);
        //3. article update
        target.update(dto);
        //4. save article
        articleRepository.save(target);
        //redirect
        return "redirect:/articles";
    }

}
