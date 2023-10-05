package com.example.firstproject.controller;

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
    //"articles/{{article.id}}/edit"
    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", article);

        return "/articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleUpdateForm dto){
        // 1. DTO에서 ID 추출
        Long id = dto.getId();

        // 2. ID로 (이미 저장 되어 있던)Article 받기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. Article update
        target.update(dto);

        // 4. save(Article)
        articleRepository.save(target);

        return "redirect:/articles";
    }
}
