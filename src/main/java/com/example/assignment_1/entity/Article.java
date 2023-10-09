package com.example.assignment_1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter  //롬복으로 게터 추가
@AllArgsConstructor  //롬복으로 생성자 추가
@NoArgsConstructor  //롬복으로 기본생성자 추가
@ToString  //롬복으로 ToString 추가

public class Article {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

}
