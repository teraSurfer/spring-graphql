package com.example.springgraphql.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleRepository repository;

    @Autowired
    public ArticleController(ArticleRepository repository) {
        this.repository = repository;
    }

    @QueryMapping("article")
    public Article getOne(@Argument("id") int id) {
        return repository.findById(id).orElseThrow();
    }

    @MutationMapping("createArticle")
    public Article createOne(@Argument("input") Article article) {
        return repository.save(article);
    }

    @QueryMapping("articles")
    public List<Article> getAll() {
        return repository.findAll();
    }

    @MutationMapping("deleteArticle")
    public Article deleteArticle(@Argument("id") int id) {
        Article article = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return article;
    }

    @MutationMapping("updateArticle")
    public Article updateArticle(@Argument("id") int id, @Argument("input") Article input) {
        boolean exists = repository.existsById(id);
        if (exists) {
            input.setId(id);
            return repository.save(input);
        }
        throw new RuntimeException("no such article");
    }

}
