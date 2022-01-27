package com.mazc.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mazc.music.service.AdminService;
import com.mazc.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 判断是否登录成功
     */
    @PostMapping("/admin/login/status")
    public Object loginStatus(HttpSession session, @RequestBody Map map) {
        JSONObject jsonObject = new JSONObject();
        String name = (String) map.get(Consts.NAME);
        String password = (String) map.get("password");
        boolean flag = adminService.verifyPassword(name, password);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "登录成功");
            session.setAttribute(Consts.NAME, name);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "用户名或密码错误");
        return jsonObject;
    }
}
