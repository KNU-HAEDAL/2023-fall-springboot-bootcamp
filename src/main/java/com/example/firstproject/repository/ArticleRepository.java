package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
// 제너릭 <T, ID>
    // T - 관리 대상 엔티티의 클래스 타입, 여기서는 Article
    // ID - 관리 대상 엔티티의 대푯값 타입, Article.java에서 id를 대푯값으로 지정했으므로 id 타입인 Long
}
