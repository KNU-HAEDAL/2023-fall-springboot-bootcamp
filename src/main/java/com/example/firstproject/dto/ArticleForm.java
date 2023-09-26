package com.example.firstproject.dto;

public class ArticleForm {
    private String title; //제목을 받을 필드
    private String content; //내용을 받을 필드

//전송받은 제목과 내용을 필드에 저장하는 생성자 추가
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}