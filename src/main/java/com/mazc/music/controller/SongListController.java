package com.mazc.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mazc.music.domain.SongList;
import com.mazc.music.service.SongListService;
import com.mazc.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 歌单控制类
 */
@RestController
@RequestMapping("/songList")
public class SongListController {
    @Autowired
    private SongListService songListService;

    /**
     * 添加歌单
     */
    @PostMapping("/add")
    public Object addSongList(@RequestBody SongList songList) {
        JSONObject jsonObject = new JSONObject();
//        String name = songList.getName();
//        Byte sex = songList.getSex();
//        String pic = songList.getPic();
//        Date birth = songList.getBirth();
//        String location = songList.getLocation();
//        String introduction = songList.getIntroduction();
//        // 把生日转换为 Date 格式
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            birth = dateFormat.parse(String.valueOf(birth));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        boolean flag = songListService.insert(songList);
        if (flag) { // 保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "添加失败");
        return jsonObject;
    }

    /**
     * 修改歌单
     */
    @PutMapping("/update")
    public Object updateSongList(@RequestBody SongList songList) {
        JSONObject jsonObject = new JSONObject();

        boolean flag = songListService.update(songList);
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
     * 删除歌单
     */
    @DeleteMapping("/delete/{id}")
    public Object deleteSongList(@PathVariable("id") Integer id) {
//        JSONObject jsonObject = new JSONObject();

        boolean flag = songListService.delete(id);
        return flag;
//        if (flag) { // 保存成功
//            jsonObject.put(Consts.CODE, 1);
//            jsonObject.put(Consts.MSG, "删除成功");
//            return jsonObject;
//        }
//        jsonObject.put(Consts.CODE, 0);
//        jsonObject.put(Consts.MSG, "删除失败");
//        return jsonObject;
    }

    /**
     * 根据主键查询真个对象
     */
    @GetMapping("/selectById/{id}")
    public Object selectById(Integer id) {
        SongList songList = songListService.selectById(id);
        return songList;
    }

    /**
     * 查询所有歌单
     */
    @GetMapping("/allSongList")
    public Object allSongList() {
        List<SongList> songLists = songListService.allSongList();
        return songLists;
    }

    /**
     * 根据歌单标题精确查询列表
     */
    @GetMapping("/songListOfTitle/{title}")
    public Object songListOfTitle(@PathVariable("title") String title) {
        return songListService.songListOfTitle(title);
    }

    /**
     * 根据歌单标题模糊查询列表
     */
    @GetMapping("/likeTitle/{title}")
    public Object likeTitle(@PathVariable("title") String title) {
        return songListService.likeTitle("%" + title + "%");
    }

    /**
     * 根据歌风格题模糊查询列表
     */
    @GetMapping("/likeStytle/{style}")
    public Object likeStytle(@PathVariable("style") String style) {
        return songListService.likeStytle("%" + style + "%");
    }

    /**
     * 更新歌单图片
     */
    @PostMapping("/updateSongListPic/{id}")
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @PathVariable("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }

        // 文件名 = 当前毫秒时间 + 原来的文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songListPic";
        // 如果文件路径不存在，新增改路径
        System.out.println(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        // 存到数据库里的相对文件地址
        String storeAvatorPath = "/img/songListPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
//            FileUtils.copyInputStreamToFile(avatorFile.getInputStream(), dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            boolean flag = songListService.update(songList);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("pic", storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败" + e.getMessage());
            return jsonObject;
        }
    }
}
