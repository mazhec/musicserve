package com.mazc.music.dao;

import com.mazc.music.domain.SongList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 歌手Dao
 */
@Mapper
public interface SongListMapper {
    /**
     * 增加
     */
    public int insert(SongList songList);

    /**
     * 修改
     */
    public int update(SongList songList);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public SongList selectById(Integer id);

    /**
     * 查询所有歌曲
     */
    public List<SongList> allSongList();

    /**
     * 根据标题精确查询歌单列表
     */
    public List<SongList> songListOfTitle(String title);

    /**
     * 根据标题模糊查询歌单列表
     */
    public List<SongList> likeTitle(String title);

    /**
     * 根据风格模糊查询歌单列表
     */
    public List<SongList> likeStytle(String stytle);
}
