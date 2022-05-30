package com.samsung.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book") // Вид связи один ко многим (у одной книги много комментов)
    private List<Comment> comments;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Вид связи многое к одному (у одного автора
    @JoinColumn(name = "author_id")                                 // много книг), выгружаем автора, когда необходимо
    private Author author;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Вид связи многое к одному (у одного жанра
    @JoinColumn(name = "genre_id")                                 // много книг), выгружаем жанр, когда необходимо
    private Genre genre;
}
