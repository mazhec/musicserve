package com.mazc.music.service;

import com.mazc.music.domain.Song;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌曲service接口
 */
public interface SongService {
    /**
     * 增加
     */
    public boolean insert(Song song);

    /**
     * 修改
     */
    public boolean update(Song song);

    /**
     * 删除
     */
    public boolean delete(Integer id);

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
     * 根据歌名模糊查询列表
     */
    public List<Song> LikeSongOfName(String name);

    /**
     * 根据歌手id查询
     */
    public List<Song> songOfSingerId(Integer singerId);
}
