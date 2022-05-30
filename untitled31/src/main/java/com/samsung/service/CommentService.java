package com.samsung.service;

import com.samsung.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment insert(Comment comment);

    List<Comment> getAll();

    Comment getById(int id);

    List<Comment> getByBookId(int bookId);


    Comment update (int id, String content);
    void deleteById(int id);




}
