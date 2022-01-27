package com.mazc.music.service;

import com.mazc.music.domain.Consumer;

import java.util.List;

/**
 * 前端用户service接口
 */
public interface ConsumerService {
    /**
     * 增加
     */
    public boolean insert(Consumer consumer);

    /**
     * 修改
     */
    public boolean update(Consumer consumer);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询真个对象
     */
    public Consumer selectById(Integer id);

    /**
     * 查询所有用户
     */
    public List<Consumer> allConsumer();

    /**
     * 验证密码
     */
    public boolean verifyPassword(String username, String password);

    /**
     * 根据账号查询
     */
    public Consumer getByUsername(String username);
}
