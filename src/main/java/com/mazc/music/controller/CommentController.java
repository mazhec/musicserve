package com.mazc.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mazc.music.domain.Comment;
import com.mazc.music.service.CollectService;
import com.mazc.music.service.CommentService;
import com.mazc.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 评论控制类
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 添加歌手
     */
    @PostMapping("/add")
    public Object addComment(@RequestBody Comment comment) {
        JSONObject jsonObject = new JSONObject();
        boolean flag = commentService.insert(comment);
        if (flag) { // 保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "评论成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "评论失败");
        return jsonObject;
    }

    /**
     * 修改歌手
     */
    @PutMapping("/update")
    public Object updateComment(@RequestBody Comment comment) {
        JSONObject jsonObject = new JSONObject();

        boolean flag = commentService.update(comment);
        if (flag) { // 保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "修改失败");
        return jsonObject;
    }

    /**
     * 删除评论
     */
    @DeleteMapping("/delete/{id}")
    public Object deleteComment(@PathVariable("id") Integer id) {
        boolean flag = commentService.delete(id);
        return flag;
    }

    /**
     * 根据主键查询真个对象
     */
    @GetMapping("/selectById/{id}")
    public Object selectById(Integer id) {
        Comment comment = commentService.selectById(id);
        return comment;
    }

    /**
     * 查询所有歌手
     */
    @GetMapping("/allComment")
    public Object allComment() {
        List<Comment> comments = commentService.allComment();
        return comments;
    }

    /**
     * 查询某个歌曲下的所有评论
     */
    @GetMapping("/commentOfSongId/{songId}")
    public Object commentOfSongId(@PathVariable("songId") Integer songId) {
        return commentService.commentOfSongId(songId);
    }

    /**
     * 查询某个歌单下的所有评论
     */
    @GetMapping("/commentOfSongListId/{songListId}")
    public Object commentOfSongListId(@PathVariable("songListId") Integer songListId) {
        return commentService.commentOfSongListId(songListId);
    }

    /**
     * 给某个评论点赞
     */
    @PostMapping("/like")
    public Object like(@RequestBody Comment comment) {
        JSONObject jsonObject = new JSONObject();

        boolean flag = commentService.update(comment);
        if (flag) { // 保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "点赞成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "点赞失败");
        return jsonObject;
    }
}
