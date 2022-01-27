package com.mazc.music.service;

import com.mazc.music.domain.ListSong;

import java.util.List;

/**
 * 歌单里的歌曲service接口
 */
public interface ListSongService {
    /**
     * 增加
     */
    public boolean insert(ListSong listSong);

    /**
     * 修改
     */
    public boolean update(ListSong listSong);

    /**
     * 删除
     */
    public boolean delete(Integer songId, Integer songListId);

    /**
     * 根据主键查询整个对象
     */
    public ListSong selectById(Integer id);

    /**
     * 查询所有歌曲
     */
    public List<ListSong> allListSong();

    /**
     * 根据歌手id查询
     */
    public List<ListSong> listSongOfSongListId(Integer songListId);
}
