package com.mazc.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mazc.music.domain.Consumer;
import com.mazc.music.service.ConsumerService;
import com.mazc.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 前端用户控制类
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    /**
     * 添加歌手
     */
    @PostMapping("/add")
    public Object addConsumer(@RequestBody Consumer consumer) {
        JSONObject jsonObject = new JSONObject();

        if (consumer.getUsername() == null || "".equals(consumer.getUsername())) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名不能为空");
            return jsonObject;
        }

        Consumer user = consumerService.getByUsername(consumer.getUsername());
        if (user != null) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户已存在");
            return jsonObject;
        }

        if (consumer.getPassword() == null || "".equals(consumer.getPassword())) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "密码不能为空");
            return jsonObject;
        }

        boolean flag = consumerService.insert(consumer);
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
     * 登录
     */
    @PostMapping("/login")
    public Object login(@RequestBody Consumer consumer) {
        JSONObject jsonObject = new JSONObject();

        if (consumer.getUsername() == null || "".equals(consumer.getUsername())) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名不能为空");
            return jsonObject;
        }

        if (consumer.getPassword() == null || "".equals(consumer.getPassword())) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "密码不能为空");
            return jsonObject;
        }

        boolean flag = consumerService.verifyPassword(consumer.getUsername(), consumer.getPassword());
        if (flag) { // 验证登录成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "登录成功");
            jsonObject.put("userMsg", consumerService.getByUsername(consumer.getUsername()));
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "用户名或密码错误");
        return jsonObject;
    }

    /**
     * 修改歌手
     */
    @PutMapping("/update")
    public Object updateConsumer(@RequestBody Consumer consumer) {
        JSONObject jsonObject = new JSONObject();

        if (consumer.getUsername() == null || "".equals(consumer.getUsername())) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名不能为空");
            return jsonObject;
        }

        if (consumer.getPassword() == null || "".equals(consumer.getPassword())) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "密码不能为空");
            return jsonObject;
        }

        boolean flag = consumerService.update(consumer);
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
    public Object deleteConsumer(@PathVariable("id") Integer id) {
        boolean flag = consumerService.delete(id);
        return flag;
    }

    /**
     * 根据主键查询真个对象
     */
    @GetMapping("/selectById/{id}")
    public Object selectById(@PathVariable("id") Integer id) {
        Consumer consumer = consumerService.selectById(id);
        return consumer;
    }

    /**
     * 查询所有歌手
     */
    @GetMapping("/allConsumer")
    public Object allConsumer() {
        List<Consumer> consumers = consumerService.allConsumer();
        return consumers;
    }

    /**
     * 更新歌手图片
     */
    @PostMapping("/updateConsumerPic/{id}")
    public Object updateConsumerPic(@RequestParam("file") MultipartFile avatorFile, @PathVariable("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }

        // 文件名 = 当前毫秒时间 + 原来的文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "avatorImages";
        // 如果文件路径不存在，新增改路径
        System.out.println(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        // 存到数据库里的相对文件地址
        String storeAvatorPath = "/avatorImages/" + fileName;
        try {
            avatorFile.transferTo(dest);
//            FileUtils.copyInputStreamToFile(avatorFile.getInputStream(), dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatorPath);
            boolean flag = consumerService.update(consumer);
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
