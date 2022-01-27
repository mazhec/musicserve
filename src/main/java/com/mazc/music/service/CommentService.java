package com.mazc.music.service;

import com.mazc.music.domain.Comment;

import java.util.List;

/**
 * 评论service接口
 */
public interface CommentService {
    /**
     * 增加
     */
    public boolean insert(Comment comment);

    /**
     * 修改
     */
    public boolean update(Comment comment);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询真个对象
     */
    public Comment selectById(Integer id);

    /**
     * 查询所有评论
     */
    public List<Comment> allComment();

    /**
     * 查询某个歌曲下的所有评论
     */
    public List<Comment> commentOfSongId(Integer songId);

    /**
     * 查询某个歌单下的某个评论
     */
    public List<Comment> commentOfSongListId(Integer songListId);
}
