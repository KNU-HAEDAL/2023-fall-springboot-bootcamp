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
@NoArgsConstructor
@ToString
@Entity//1.엔티티 선언
//no-arg constructor이거 왜 발생하지??
public class Article{
    @Id//엔티티의 대푯값 지정
    @GeneratedValue//자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private Long id;
    @Column//title 필드 선언, DB 테이블의 title열과 연결됨
    private String title;
    @Column//content 필드 선언, DB테이블의 content 열과 연결됨
    private String content;

    public void update(ArticleUpdateForm dto) {
        id = dto.getId();
        title =  dto.getTitle();
        content = dto.getContent();
    }

  /*  public Article() {//이거 없으면 오류발생 왜???

    }*/

   /* public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Article() {
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    } 롬복에 의해 삭제*/
}
