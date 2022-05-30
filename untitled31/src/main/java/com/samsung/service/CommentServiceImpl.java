package com.samsung.service;


import com.samsung.domain.Comment;
import com.samsung.repository.BookRepository;
import com.samsung.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentDao;
    private final BookRepository bookDao;

    @Override
    public Comment insert(Comment comment) {
       return commentDao.save(comment);
    }




    @Override
    public List<Comment> getAll() {

        return commentDao.findAll();
    }

    @Override
    public Comment getById(int id) {

        return commentDao.getById(id);
    }

    @Override
    public List<Comment> getByBookId(int id) {

        return commentDao.findByBookId(id);
    }

    @Override
    @Transactional
    public Comment update(int id, String content) {

        return commentDao.save(Comment.builder().id(id).content(content).build());
    }

    @Override
    @Transactional
    public void deleteById(int id) {

        commentDao.deleteById(id);
    }
}
