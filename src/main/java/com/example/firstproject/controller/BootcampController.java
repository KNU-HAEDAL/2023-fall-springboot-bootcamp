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

import java.util.Optional;

@Controller
public class BootcampController {

    @Autowired
    ArticleRepository articleRepository;
    @GetMapping("articles/{id}edit")
    public String edit(@PathVariable Long id, Model model){

       Optional<Article> article =  articleRepository.findById(id);

       model.addAttribute("article",article);

       return "/articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleUpdateForm dto){
        //1. dto에서 id 추출
        Long id = dto.getId();
        //2. id로 (이미 저장이 되어있떤) Article받기
        Article target =articleRepository.findById(id).orElse(null);
        //3. Article update
        target.update(dto);
        //4. save(aricle)
        articleRepository.save(target);
        return "redirect:/articles";
    }
}
