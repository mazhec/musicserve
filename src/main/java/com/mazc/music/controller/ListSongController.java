package com.mazc.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mazc.music.domain.ListSong;
import com.mazc.music.service.ListSongService;
import com.mazc.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 歌单的歌曲管理 Controller
 */
@RestController
@RequestMapping("/listSong")
public class ListSongController {
    @Autowired
    private ListSongService listSongService;

    /**
     * 添加歌曲
     *
     * @param
     * @return
     */
    @PostMapping("/add")
    public Object addSinger(@RequestBody ListSong listSong) {
        JSONObject jsonObject = new JSONObject();

        boolean flag = listSongService.insert(listSong);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "保存成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "保存失败");
        return jsonObject;
    }

    /**
     * 根据歌单id查询歌曲
     */
    @GetMapping("/detail/{songListId}")
    public Object detail(@PathVariable("songListId") Integer songListId) {
        List<ListSong> listSongs = listSongService.listSongOfSongListId(songListId);
        return listSongs;
    }

    /**
     * 删除歌单里的歌曲
     */
    @DeleteMapping("/delete/{songId}/{songListId}")
    public Object deleteSinger(@PathVariable("songId") Integer songId, @PathVariable("songListId") Integer songListId) {
        boolean flag = listSongService.delete(songId, songListId);
        return flag;
    }
}
