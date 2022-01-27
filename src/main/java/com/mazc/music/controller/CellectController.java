package com.mazc.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mazc.music.domain.Collect;
import com.mazc.music.service.CollectService;
import com.mazc.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收藏控制类
 */
@RestController
@RequestMapping("/collect")
public class CellectController {
    @Autowired
    private CollectService collectService;

    /**
     * 添加收藏
     */
    @PostMapping("/add")
    public Object addCollect(@RequestBody Collect collect) {
        JSONObject jsonObject = new JSONObject();
        if (collect.getSongId() == null || "".equals(collect.getSongId())) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "收藏收藏歌曲为空");
            return jsonObject;
        }
        if (collectService.existSongId(collect.getUserId(), collect.getSongId())) {
            jsonObject.put(Consts.CODE, 2);
            jsonObject.put(Consts.MSG, "收藏收藏歌曲为空");
            return jsonObject;
        }
        boolean flag = collectService.insert(collect);
        if (flag) { // 保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "已收藏");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "收藏失败");
        return jsonObject;
    }

    /**
     * 删除收藏
     */
    @DeleteMapping("/delete/{id}")
    public Object deleteCollect(@PathVariable("id") Integer id) {
        boolean flag = collectService.delete(id);
        return flag;
    }

    /**
     * 根据用户id和歌曲id删除
     */
    @DeleteMapping("/delete/{userId}/{songId}")
    public Object deleteByUserIdAndSongId(@PathVariable("userId") Integer userId, @PathVariable("songId") Integer songId) {
        boolean flag = collectService.deleteByUserIdAndSongId(userId, songId);
        return flag;
    }

    /**
     * 查询所有收藏
     */
    @GetMapping("/allCollect")
    public Object allCollect() {
        List<Collect> collects = collectService.allCollect();
        return collects;
    }

    /**
     * 查询某个用户的收藏列表
     */
    @GetMapping("/collectOfUserId/{userId}")
    public Object collectOfSongId(@PathVariable("userId") Integer userId) {
        return collectService.collectOfUserId(userId);
    }
}
