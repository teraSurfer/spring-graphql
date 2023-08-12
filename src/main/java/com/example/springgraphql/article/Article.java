package com.example.springgraphql.article;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "article")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String title;
    @Column
    private String content;
}