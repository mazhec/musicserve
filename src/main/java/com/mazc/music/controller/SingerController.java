package com.mazc.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mazc.music.domain.Singer;
import com.mazc.music.service.SingerService;
import com.mazc.music.utils.Consts;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 歌手控制类
 */
@RestController
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;

    /**
     * 添加歌手
     */
    @PostMapping("/add")
    public Object addSinger(@RequestBody Singer singer) {
        JSONObject jsonObject = new JSONObject();
//        String name = singer.getName();
//        Byte sex = singer.getSex();
//        String pic = singer.getPic();
//        Date birth = singer.getBirth();
//        String location = singer.getLocation();
//        String introduction = singer.getIntroduction();
//        // 把生日转换为 Date 格式
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            birth = dateFormat.parse(String.valueOf(birth));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        boolean flag = singerService.insert(singer);
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
     * 修改歌手
     */
    @PutMapping("/update")
    public Object updateSinger(@RequestBody Singer singer) {
        JSONObject jsonObject = new JSONObject();

        boolean flag = singerService.update(singer);
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
//        JSONObject jsonObject = new JSONObject();

        boolean flag = singerService.delete(id);
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
        Singer singer = singerService.selectById(id);
        return singer;
    }

    /**
     * 查询所有歌手
     */
    @GetMapping("/allSinger")
    public Object allSinger() {
        List<Singer> singers = singerService.allSinger();
        return singers;
    }

    /**
     * 根据歌手名字模糊查询列表
     */
    @GetMapping("/singerOfName/{name}")
    public Object singerOfName(@PathVariable("name") String name) {
        return singerService.singerOfName("%" + name + "%");
    }

    /**
     * 根据歌手性别模糊查询列表
     */
    @GetMapping("/singerOfSex/{sex}")
    public Object singerOfSex(@PathVariable("sex") Integer sex) {
        return singerService.singerOfSex(sex);
    }

    /**
     * 更新歌手图片
     */
    @PostMapping("/updateSingerPic/{id}")
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @PathVariable("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }

        // 文件名 = 当前毫秒时间 + 原来的文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "singerPic";
        // 如果文件路径不存在，新增改路径
        System.out.println(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        // 存到数据库里的相对文件地址
        String storeAvatorPath = "/img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
//            FileUtils.copyInputStreamToFile(avatorFile.getInputStream(), dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean flag = singerService.update(singer);
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
