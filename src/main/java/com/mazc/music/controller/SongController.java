package com.mazc.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mazc.music.domain.Song;
import com.mazc.music.service.SongService;
import com.mazc.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 *
 */
@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;

    /**
     * 添加歌曲
     * @param
     * @return
     */
    @PostMapping("/add")
    public Object addSinger(HttpServletRequest req, @RequestParam("file") MultipartFile mpFile) {
        JSONObject jsonObject = new JSONObject();
        String singer_id = req.getParameter("singerId").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String lyric = req.getParameter("lyric").trim();
        String pic = "/img/songPic/tubiao.jpg";
        if (mpFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "歌曲上传失败");
            return jsonObject;
        }

        // 文件名 = 当前毫秒时间 + 原来的文件名
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        // 如果文件路径不存在，新增改路径
        System.out.println(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        // 存到数据库里的相对文件地址
        String storeSongPath = "/song/" + fileName;
        try {
            mpFile.transferTo(dest);
//            FileUtils.copyInputStreamToFile(avatorFile.getInputStream(), dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singer_id));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setCreateTime(new Date());
            song.setUpdateTime(new Date());
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeSongPath);
            boolean flag = songService.insert(song);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "保存成功");
                jsonObject.put("avator", storeSongPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "保存失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "保存失败" + e.getMessage());
            return jsonObject;
        }
    }

    /**
     * 根据歌手id查询歌曲
     */
    @GetMapping("/singer/detail/{id}")
    public Object songOfSingerId(@PathVariable("id") Integer id) {
        return songService.songOfSingerId(id);
    }

    /**
     * 修改歌曲
     */
    @PutMapping("/update")
    public Object updateSong(@RequestBody Song song) {
        JSONObject jsonObject = new JSONObject();

        boolean flag = songService.update(song);
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
     * 删除歌手
     */
    @DeleteMapping("/delete/{id}")
    public Object deleteSinger(@PathVariable("id") Integer id) {
        boolean flag = songService.delete(id);
        return flag;
    }

    /**
     * 更新歌曲图片
     */
    @PostMapping("/updateSongPic/{id}")
    public Object updateSongPic(@RequestParam("file") MultipartFile avatorFile, @PathVariable("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }

        // 文件名 = 当前毫秒时间 + 原来的文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songPic";
        // 如果文件路径不存在，新增改路径
        System.out.println(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        // 存到数据库里的相对文件地址
        String storeAvatorPath = "/img/songPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
//            FileUtils.copyInputStreamToFile(avatorFile.getInputStream(), dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeAvatorPath);
            boolean flag = songService.update(song);
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

    /**
     * 更新歌曲
     */
    @PostMapping("/updateSongUrl/{id}")
    public Object updateSongUrl(@RequestParam("file") MultipartFile avatorFile, @PathVariable("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }

        // 文件名 = 当前毫秒时间 + 原来的文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        // 如果文件路径不存在，新增改路径
        System.out.println(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        // 存到数据库里的相对文件地址
        String storeAvatorPath = "/song/" + fileName;
        try {
            avatorFile.transferTo(dest);
//            FileUtils.copyInputStreamToFile(avatorFile.getInputStream(), dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeAvatorPath);
            boolean flag = songService.update(song);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("avator", storeAvatorPath);
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

    /**
     * 根据歌曲id查询歌曲对象
     */
    @GetMapping("/detail/{songId}")
    public Object detail(@PathVariable("songId") Integer songId) {
        return songService.selectById(songId);
    }

    /**
     * 根据歌名精确查询歌曲对象
     */
    @GetMapping("/songOfSongName/{songName}")
    public Object songOfSongName(@PathVariable("songName") String songName) {
        return songService.SongOfName(songName);
    }

    /**
     * 根据歌名精模糊询歌曲对象
     */
    @GetMapping("/likeSongOfSongName/{songName}")
    public Object likeSongOfSongName(@PathVariable("songName") String songName) {
        return songService.LikeSongOfName(songName);
    }

    /**
     * 查询所有歌曲
     */
    @GetMapping("/allSong")
    public Object allSong() {
        return songService.allSong();
    }
}
