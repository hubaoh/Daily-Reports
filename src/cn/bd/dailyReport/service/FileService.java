package cn.bd.dailyReport.service;

import cn.bd.dailyReport.utils.messages.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 赵传志 on 2018/4/25.
 */
public interface FileService {

    /**
     * 文件上传
     * @param multipartFile
     * @return ResponseEntity
     */
    ResponseEntity<Message> upload(MultipartFile multipartFile, String desc, String title, String uploader);

//    /**
//     * 将文件相关信息保存到数据库中
//     * @param uploadFile
//     * @return
//     */
//    ResponseEntity<Message> create(UploadFile uploadFile, String uploader);

    /**
     * 查询所有文件
     * @param uploader
     * @param uploadTime
     * @param page
     * @param pageSize
     * @return
     */
    ResponseEntity<Message> findAll(String uploader, String uploadTime, int page, int pageSize);

    /**
     * 文件下载
     * @param response
     * @param fileName
     * @return
     */
    ResponseEntity<Message> download(HttpServletResponse response, String fileName);
}
