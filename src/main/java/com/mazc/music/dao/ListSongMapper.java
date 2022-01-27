package com.mazc.music.dao;

import com.mazc.music.domain.ListSong;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 歌曲歌单Dao
 */
@Mapper
public interface ListSongMapper {
    /**
     * 增加
     */
    public int insert(ListSong listSong);

    /**
     * 修改
     */
    public int update(ListSong listSong);

    /**
     * 删除
     */
    public int delete(Integer songId, Integer songListId);

    /**
     * 根据主键查询整个对象
     */
    public ListSong selectById(Integer id);

    /**
     * 查询所有歌曲
     */
    public List<ListSong> allListSong();

    /**
     * 根据歌单id查询所有的歌曲
     */
    public List<ListSong> ListSongOfName(String name);

    /**
     * 根据歌手id查询
     */
    public List<ListSong> listSongOfSongListId(Integer songListId);
}
