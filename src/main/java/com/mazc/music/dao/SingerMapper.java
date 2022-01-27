package com.mazc.music.dao;

import com.mazc.music.domain.Singer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 歌手Dao
 */
@Mapper
public interface SingerMapper {
    /**
     * 增加
     */
    public int insert(Singer singer);

    /**
     * 修改
     */
    public int update(Singer singer);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 根据主键查询真个对象
     */
    public Singer selectById(Integer id);

    /**
     * 查询所有歌手
     */
    public List<Singer> allSinger();

    /**
     * 根据歌手名字模糊查询列表
     */
    public List<Singer> singerOfName(String name);

    /**
     * 根据性别查询
     */
    public List<Singer> singerOfSex(Integer sex);
}