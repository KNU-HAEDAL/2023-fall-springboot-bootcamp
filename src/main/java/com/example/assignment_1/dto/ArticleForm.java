package com.example.assignment_1.dto;

import com.example.assignment_1.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //생성자 간소화
@ToString //리팩터링

public class ArticleForm {
    private String title; //제목 받을 필드
    private String content; //내용 받을 필드


    public Article toEntity() {
        return new Article(null,title,content);
    }
}
