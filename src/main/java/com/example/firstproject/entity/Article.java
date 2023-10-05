package com.example.firstproject.entity;

import com.example.firstproject.dto.ArticleUpdateForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 추가 어노테이션
@ToString
@Entity // @Entity는 JPA에서 제공하는 어노테이션으로 이것이 붙은 클래스를 기반으로 DB에 테이블이 생성됨
public class Article {
    @Id // 엔티티의 대푯값 지정
    @GeneratedValue // 자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private Long id;
    @Column // title 필드 선언, DB 테이블의 title 열과 연결됨
    private String title;
    @Column // content 필드 선언, DB 테이블의 content 열과 연결됨
    private String content;

    public void update(ArticleUpdateForm dto) {
        id = dto.getId();
        title = dto.getTitle();
        content = dto.getContent();
    }
}
