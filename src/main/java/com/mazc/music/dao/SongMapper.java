package com.mazc.music.dao;

import com.mazc.music.domain.Song;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 歌手Dao
 */
@Mapper
public interface SongMapper {
    /**
     * 增加
     */
    public int insert(Song song);

    /**
     * 修改
     */
    public int update(Song song);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public Song selectById(Integer id);

    /**
     * 查询所有歌曲
     */
    public List<Song> allSong();

    /**
     * 根据歌名精确查查询列表
     */
    public List<Song> SongOfName(String name);

    /**
     * 根据歌名模糊查查询列表
     */
    public List<Song> LikeSongOfName(String name);

    /**
     * 根据歌手id查询
     */
    public List<Song> songOfSingerId(Integer singerId);
}
