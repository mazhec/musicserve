package com.mazc.music.service.impl;

import com.mazc.music.dao.AdminMapper;
import com.mazc.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员service实现类
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Boolean verifyPassword(String name, String password) {
        return adminMapper.verifyPassword(name, password) > 0;
    }
}
