package com.samsung.repository;

import com.samsung.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс AuthorDaoJPA")
@DataJpaTest
class AuthorRepositoryTest {

    public static final int EXISTING_AUTHOR_COUNT = 3;
    public static final int EXISTING_ID1 = 1;
    public static final String EXISTING_NAME1 = "Сергей Лукьяненко";
    public static final int EXISTING_ID2 = 2;
    public static final int EXISTING_ID3 = 3;
    public static final String EXISTING_NAME2 = "Александр Сергеевич Грибоедов";
    public static final String EXISTING_NAME3 = "Гарри Гаррисон";

    @Autowired
    private AuthorRepository authorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @DisplayName("должен добавлять автора")
    @Test
    void shouldInsertAuthor() {

        Author expectedAuthor = Author.builder()
                .id(4)
                .name("Ivan")
                .build();

        authorRepository.save(expectedAuthor);
        Author actualAuthor = authorRepository.getById(4);

        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @DisplayName("должен обновлять автора")
    @Test
    void shouldUpdateAuthor() {
        Author expectedAuthor1 = Author.builder()
                .id(EXISTING_ID1)
                .name("Ivan")
                .build();

        authorRepository.save(expectedAuthor1);
        Author actualAuthor = authorRepository.getById(EXISTING_ID1);

        assertThat(actualAuthor).isEqualTo(expectedAuthor1);
    }

    @DisplayName("должен возвращать всех авторов")
    @Test
    void shouldGetAllAuthors() {

        Author expectedAuthor1 = Author.builder()
                .id(EXISTING_ID1)
                .name(EXISTING_NAME1)
                .build();
        Author expectedAuthor2 = Author.builder()
                .id(EXISTING_ID2)
                .name(EXISTING_NAME2)
                .build();
        Author expectedAuthor3 = Author.builder()
                .id(EXISTING_ID3)
                .name(EXISTING_NAME3)
                .build();

        assertThat(authorRepository.findAll().size()).isEqualTo(EXISTING_AUTHOR_COUNT);
        assertThat(authorRepository.findAll())
                .containsExactlyInAnyOrder(expectedAuthor1, expectedAuthor2, expectedAuthor3);
    }

    @DisplayName("должен возвращать автора по id")
    @Test
    void shouldGetAuthorById() {

        Author expectedAuthor = Author.builder()
                .id(EXISTING_ID1)
                .name(EXISTING_NAME1)
                .build();

        Author actualAuthor = authorRepository.getById(EXISTING_ID1);

        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @DisplayName("должен возвращать автора по имени")
    @Test
    void shouldGetAuthorByName() {

        Author expectedAuthor = Author.builder()
                .id(EXISTING_ID1)
                .name(EXISTING_NAME1)
                .build();

        Author actualAuthor = authorRepository.findByName(EXISTING_NAME1);

        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @DisplayName("должен удалять автора по id")
    @Test
    void shouldDeleteAuthorById() {

        int sizeBefore = authorRepository.findAll().size();

        authorRepository.deleteById(EXISTING_ID1);
        entityManager.flush();

        int sizeAfter = authorRepository.findAll().size();
        assertThat(sizeBefore - sizeAfter).isEqualTo(1);
    }
}
