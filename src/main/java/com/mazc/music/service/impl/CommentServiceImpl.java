package com.mazc.music.service.impl;

import com.mazc.music.dao.CommentMapper;
import com.mazc.music.domain.Comment;
import com.mazc.music.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论service实现类
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean insert(Comment comment) {
        return commentMapper.insert(comment) > 0;
    }

    @Override
    public boolean update(Comment comment) {
        return commentMapper.update(comment) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return commentMapper.delete(id) > 0;
    }

    @Override
    public Comment selectById(Integer id) {
        return commentMapper.selectById(id);
    }

    @Override
    public List<Comment> allComment() {
        return commentMapper.allComment();
    }

    @Override
    public List<Comment> commentOfSongId(Integer songId) {
        return commentMapper.commentOfSongId(songId);
    }

    @Override
    public List<Comment> commentOfSongListId(Integer songListId) {
        return commentMapper.commentOfSongListId(songListId);
    }
}
