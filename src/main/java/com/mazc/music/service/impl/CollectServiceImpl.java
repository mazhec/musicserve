package com.mazc.music.service.impl;

import com.mazc.music.dao.CollectMapper;
import com.mazc.music.domain.Collect;
import com.mazc.music.service.CollectService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收藏实现类
 */
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public boolean insert(Collect collect) {
        return collectMapper.insert(collect) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return collectMapper.delete(id) > 0;
    }

    @Override
    public boolean deleteByUserIdAndSongId(@Param("userId") Integer userId, @Param("songId") Integer songId) {
        return collectMapper.deleteByUserIdAndSongId(userId, songId) > 0;
    }

    @Override
    public List<Collect> allCollect() {
        return collectMapper.allCollect();
    }

    @Override
    public List<Collect> collectOfUserId(Integer userId) {
        return collectMapper.collectOfUserId(userId);
    }

    @Override
    public boolean existSongId(Integer userId, Integer songId) {
        return collectMapper.existSongId(userId, songId) > 0;
    }
}
