package com.samsung.service;

import com.samsung.domain.Author;
import com.samsung.domain.Book;
import com.samsung.domain.Comment;
import com.samsung.repository.AuthorRepository;
import com.samsung.repository.BookRepository;
import com.samsung.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibDemoService {

        private final AuthorService authorService;
        private final BookService bookService;
        private final GenreService genreService;
        private final BookRepository bookRepository;
        private final CommentRepository commentRepository;
        public void authorDemo()
        {
            Author newAuthor = Author.builder()
                    .name("Новый Автор")
                    .build();

            authorService.insert(newAuthor);

            System.out.println("======Все авторы======");

            for (Author author : authorService.getAll()) {

                System.out.println(author);
            }

            System.out.println("======================\n");

        }


    @Transactional
    public void bookDemo() {

        bookService.insert("Новая книга", "Новый Жанр", "Новый Автор");

        System.out.println("======Все книги======");

        for (Book book1 : bookService.getAll()) {

            System.out.println(
                    book1.getName() + " : " +
                            book1.getAuthor().getName() + ", " +
                            book1.getGenre().getName()
            );
        }

        System.out.println("======================\n");

    }

@Transactional
    public void commentDemo() {

       commentRepository.updateCommentById(1, "vwrv");
        List<Comment> a = commentRepository.findAll();
        for (Comment comment : a) {

            System.out.println(comment.getBook().getName() + " : " + comment.getContent());
        }

        System.out.println("======================\n");
        a = commentRepository.findByBookId(2);
    for (Comment comment : a) {

        System.out.println(comment.getBook().getName() + " : " + comment.getContent());
    }

    }


}
