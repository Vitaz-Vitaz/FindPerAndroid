package com.samsung.repository;

import com.samsung.domain.Book;
import liquibase.pro.packaged.B;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Override
    @EntityGraph(attributePaths = {"genre", "author", "comments"})
    List<Book> findAll();


    List<Book> findByName(String name);
}
