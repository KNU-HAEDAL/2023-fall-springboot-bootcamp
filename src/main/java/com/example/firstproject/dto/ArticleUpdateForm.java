package com.example.firstproject.dto;


import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ArticleUpdateForm {

    Long id;
    String title;
    String content;

    public Article toEntity() {
        return new Article(id,title,content);

    }
}
