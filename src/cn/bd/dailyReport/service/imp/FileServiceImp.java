package cn.bd.dailyReport.service.imp;

import cn.bd.dailyReport.dao.FileDao;
import cn.bd.dailyReport.model.UploadFile;
import cn.bd.dailyReport.service.FileService;
import cn.bd.dailyReport.utils.CommonUtils;
import cn.bd.dailyReport.utils.messages.Message;
import cn.bd.dailyReport.utils.messages.MessageType;
import cn.bd.dailyReport.utils.pages.Page;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 赵传志 on 2018/4/25.
 */
@Service("fileService")
public class FileServiceImp implements FileService {

    @Autowired
    private FileDao fileDao;


    /**
     * 文件上传
     * @param multipartFile 上传的文件
     * @return
     */
    @Override
    public ResponseEntity<Message> upload(MultipartFile multipartFile, String desc, String title, String uploader) {
        if (!multipartFile.isEmpty()){
            String uuIdName = UUID.randomUUID().toString() + CommonUtils.getFileSuffix(multipartFile.getOriginalFilename());
            UploadFile uploadFile = new UploadFile();
//          FileDto fileDto = new FileDto();
//        fileDto.setMultipartFile(multipartFile);
//        fileDto.setUuIdName(uuIdName);
            try {
                FileUtils.writeByteArrayToFile(new File("G:/upload/"+uuIdName), multipartFile.getBytes());
                uploadFile.setOriginalName(multipartFile.getOriginalFilename());
                uploadFile.setUuIdName(uuIdName);
                uploadFile.setTitle(title);
                uploadFile.setDesc(desc);
                uploadFile.setUploader(uploader);
                uploadFile.setUploadTime(new Date());
                fileDao.create(uploadFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, (Object) uuIdName), HttpStatus.OK);
        }else {
            return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_ERROR),HttpStatus.OK);
        }

    }

    /**
     * 查询所有的文件
     * @param uploader
     * @param uploadTime
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public ResponseEntity<Message> findAll(String uploader, String uploadTime, int page, int pageSize) {
        if (uploader == null) uploader="";
        if (uploadTime == null) uploadTime="";
        int pageNumber = (page-1)*pageSize;
        List<UploadFile> uploadFiles = fileDao.findAll(pageNumber, pageSize,uploadTime,uploader);
        List<UploadFile> uploadFileAll = fileDao.getAll(uploadTime,uploader);
        Page<UploadFile> uploadFilePage = new Page<>(uploadFiles, uploadFileAll.size());
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, uploadFilePage), HttpStatus.OK);
    }

    /**
     * 文件下载
     * @param response
     * @param fileName
     * @return
     */
    @Override
    public ResponseEntity<Message> download(HttpServletResponse response, String fileName) {

//        String filePath = System.getProperty("user.dir") + "/uploadFile";
        String filePath = "G:/upload";
        File file = new File(filePath + "/" + fileName);
        if (file.exists()) { // 判断文件父目录是否存在
            response.reset();// 重置 响应头
            response.setContentType("application/octet-stream");// 告知浏览器下载文件，而不是直接打开，浏览器默认为打开
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; // 文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; // 输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + fileName);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS),HttpStatus.OK);
    }

}
