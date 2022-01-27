package com.mazc.music.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 管理员Dao
 */
@Mapper
public interface AdminMapper {
    /**
     * 验证密码是否正确
     */
    public int verifyPassword(String name, String password);
}
