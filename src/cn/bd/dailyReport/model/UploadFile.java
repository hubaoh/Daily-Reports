package cn.bd.dailyReport.model;

import java.util.Date;

/**
 * Created by 赵传志 on 2018/4/25.
 * 文件类
 */
public class UploadFile {

    private Long id;

    private String title; // 文件的主题

    private String originalName; // 文件的原始名称

    private String uuIdName; // 文件上传之后名称；

    private String desc; // 文件的描述

    private String uploader; // 上传者

    private Date uploadTime; // 上传时间

    public UploadFile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getUuIdName() {
        return uuIdName;
    }

    public void setUuIdName(String uuIdName) {
        this.uuIdName = uuIdName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
