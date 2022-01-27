package com.mazc.music.dao;

import com.mazc.music.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 评论Dao
 */
@Mapper
public interface CommentMapper {
    /**
     * 增加
     */
    public int insert(Comment comment);

    /**
     * 修改
     */
    public int update(Comment comment);

    /**
     * 删除
     */
    public int delete(Integer id);

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
