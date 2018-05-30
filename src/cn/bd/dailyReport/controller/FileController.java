package cn.bd.dailyReport.controller;

import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.service.FileService;
import cn.bd.dailyReport.utils.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 赵传志 on 2018/4/25.
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    @Qualifier(value = "fileService")
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<Message> upload(@RequestParam(value = "file" ) MultipartFile file, @RequestParam(value = "desc" ) String desc, @RequestParam(value = "title" ) String title, HttpSession session){
        User currentUser = (User) session.getAttribute("loginer");
        return fileService.upload(file,desc,title,currentUser.getUserName());
    }

    /**
     * 返回所有的文件
     * @param uploader
     * @param uploadTime
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Message> findAll(String uploader, String uploadTime, int page, int pageSize){
        return fileService.findAll(uploader,uploadTime,page,pageSize);
    }

    /**
     * 文件下载
     * @param filename
     * @param response
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<Message> download(@RequestParam("filename") String filename,HttpServletResponse response){
        return fileService.download(response, filename);
    }
}
