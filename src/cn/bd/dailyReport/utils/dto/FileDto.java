package cn.bd.dailyReport.utils.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 赵传志 on 2018/4/25.
 */
public class FileDto {

    private MultipartFile multipartFile;

    private String uuIdName;

    public FileDto() {
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getUuIdName() {
        return uuIdName;
    }

    public void setUuIdName(String uuIdName) {
        this.uuIdName = uuIdName;
    }
}
