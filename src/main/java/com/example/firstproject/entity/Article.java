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
@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;

    @Column
    private String content;


    public void update(ArticleUpdateForm dto) {
        id=dto.getId();
        title=dto.getTitle();
        content=dto.getContent();
    }
}
