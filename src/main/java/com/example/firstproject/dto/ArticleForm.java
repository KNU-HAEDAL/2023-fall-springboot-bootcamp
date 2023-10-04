package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title; //제목을 받을 필드
    private String content; //내용을 받을 필드

//전송받은 제목과 내용을 필드에 저장하는 생성자 추가 (어노테이션)



    public Article toEntity() {
        return new Article (null,title, content);
    }
}
