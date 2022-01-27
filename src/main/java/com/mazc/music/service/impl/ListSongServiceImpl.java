package com.mazc.music.service.impl;

import com.mazc.music.dao.ListSongMapper;
import com.mazc.music.domain.ListSong;
import com.mazc.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌单里的歌曲service接口实现类
 */
@Service
public class ListSongServiceImpl implements ListSongService {
    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public boolean insert(ListSong listSong) {
        return listSongMapper.insert(listSong) > 0;
    }

    @Override
    public boolean update(ListSong listSong) {
        return listSongMapper.update(listSong) > 0;
    }

    @Override
    public boolean delete(Integer songId, Integer songListId) {
        return listSongMapper.delete(songId, songListId) > 0;
    }

    @Override
    public ListSong selectById(Integer id) {
        return listSongMapper.selectById(id);
    }

    @Override
    public List<ListSong> allListSong() {
        return listSongMapper.allListSong();
    }

    @Override
    public List<ListSong> listSongOfSongListId(Integer songListId) {
        return listSongMapper.listSongOfSongListId(songListId);
    }
}
