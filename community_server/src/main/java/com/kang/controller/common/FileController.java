package com.kang.controller.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.kang.domain.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kang
 * @description
 * @date 2023/4/12 15:35
 */
@RestController
@RequestMapping("/common")
public class FileController {

    @Value("${file.upload.path}")
    private String uploadFilePath;

    @Value("${file.upload.url-pre}")
    private String urlPre;

    private String acceptType = ".png.jpg.zip.mp4";

    @PostMapping("/upload")
    public Result httpUpload(@RequestParam("file") MultipartFile file) {
        Map<String, String> fileUrlMap = new HashMap<>();
        String filename = file.getOriginalFilename();
        String fileType = filename.substring(filename.lastIndexOf("."));
        if (!acceptType.contains(fileType)) {
            return Result.error("上传失败:不支持该类型文件的上传!");
        }
        String filePath = "";
        //当前日期字符串:today = 2019-09-17
        String[] dateArr = DateUtil.today().split("-");
        switch (fileType) {
            case ".png":
            case ".jpg":
                filePath = uploadFilePath + "/img/";
                break;
            case ".zip":
                filePath = uploadFilePath + "/zip/";
                break;
            case ".mp4":
                filePath = uploadFilePath + "/mp4";
                break;
        }
        // 加个时间戳保证文件名唯一
        String newFileName = System.currentTimeMillis() + "_" + filename;
        StringBuilder path = StrUtil.builder(filePath, dateArr[0], "/", dateArr[1], "/", dateArr[2], "/", newFileName);
        File dest = new File(path.toString());
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            String url = urlPre + "/resource" + dest.getAbsolutePath().split("uploadFile")[1].replace("\\", "/");
            file.transferTo(dest);
            fileUrlMap.put("name", filename);
            fileUrlMap.put("url", url);
        } catch (Exception e) {
            return Result.error("上传失败！未知错误");
        }
        return Result.success(fileUrlMap);
    }


    @GetMapping("/download")
    public Result download(String url, HttpServletResponse response) throws UnsupportedEncodingException {
        if (null != url) {
            String path = uploadFilePath + url.split("resource/")[1];  // 文件名
            // 设置文件路径
            File file = new File(path);
            if (file.exists()) {
                String fileName = file.getName();
                int index = fileName.indexOf("_");
                fileName = fileName.substring(index + 1);
                response.setContentType("application/octet-stream");
                response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "utf-8")); // 设置文件名
                byte[] buffer = new byte[1024];
                OutputStream os = null;
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    os = response.getOutputStream();
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return Result.success();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return Result.error("资源加载失败!");
    }

    @GetMapping("/delete")
    public Result delete(String url) {
        if (url != null) {
            String Path = uploadFilePath + url.split("resource/")[1];// 文件名
            //设置文件路径
            File file = new File(Path);
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                FileUtil.del(file);
                return Result.success();
            }
        }
        return Result.error("删除失败");
    }


}
