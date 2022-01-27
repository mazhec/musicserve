package com.mazc.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mazc.music.domain.Rank;
import com.mazc.music.service.RankService;
import com.mazc.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RankController {
    @Autowired
    private RankService rankService;

    /**
     * 新增评价
     */
    @PostMapping("/rank/add")
    public Object add(@RequestBody Rank rank) {
        JSONObject jsonObject = new JSONObject();
        boolean flag = rankService.insert(rank);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "评价成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "评价失败");
        return jsonObject;
    }

    /**
     * 计算平均分
     */
    @GetMapping("/rank/{songListId}")
    public Object rankOfSongListId(@PathVariable("songListId") Integer songListId) {
        return rankService.rankOfSongListId(songListId);
    }
}
