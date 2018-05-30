package cn.bd.dailyReport.utils.dto;

import cn.bd.dailyReport.model.Report;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * Created by 赵传志 on 2018/3/7.
 * 日报的Dto实体类
 */
public class ReportDto {

    private Long id;

    private Date report_time; // 日报时间

    private String report_type; // 日报类型

    private JSONObject report_content; // 日报内容

    private String report_writer; // 日报作者

    public ReportDto() {
    }

    public ReportDto(Report report) {
        this.id = report.getId();
        this.report_time = report.getReport_time();
        this.report_type = report.getReport_type();
        this.report_writer = report.getReport_writer();
//        this.report_content = JSON.parseObject(report.getReport_content()); // 将数据库中的字符串类型转换成json数据类型
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReport_time() {
        return report_time;
    }

    public void setReport_time(Date report_time) {
        this.report_time = report_time;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public JSONObject getReport_content() {
        return report_content;
    }

    public void setReport_content(JSONObject report_content) {
        this.report_content = report_content;
    }

    public String getReport_writer() {
        return report_writer;
    }

    public void setReport_writer(String report_writer) {
        this.report_writer = report_writer;
    }
}
