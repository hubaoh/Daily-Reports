package cn.bd.dailyReport.model;

import java.util.Date;

/**
 * Created by 赵传志 on 2018/3/14.
 * 日报申诉实体类
 */
public class ReportAppeal {

    private Long id;

    private Date report_time; // 日报时间

    private String report_writer; // 日报作者

    private String report_type; // 日报类型

    private String appealReason; // 申诉原因

    private Long handledScore; // 处理后分数

    private Long handlingScore; // 处理前分数

    private String handleView; // 处理意见

    private String appeal_status; // 申诉状态 01 待处理；02 已处理；03 已驳回

    private String appealer; // 申诉审核人

    private Date appeal_time; // 申诉解决时间

    public ReportAppeal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReport_writer() {
        return report_writer;
    }

    public void setReport_writer(String report_writer) {
        this.report_writer = report_writer;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getAppealReason() {
        return appealReason;
    }

    public void setAppealReason(String appealReason) {
        this.appealReason = appealReason;
    }

    public Long getHandledScore() {
        return handledScore;
    }

    public void setHandledScore(Long handledScore) {
        this.handledScore = handledScore;
    }

    public Long getHandlingScore() {
        return handlingScore;
    }

    public void setHandlingScore(Long handlingScore) {
        this.handlingScore = handlingScore;
    }

    public String getHandleView() {
        return handleView;
    }

    public void setHandleView(String handleView) {
        this.handleView = handleView;
    }

    public String getAppeal_status() {
        return appeal_status;
    }

    public void setAppeal_status(String appeal_status) {
        this.appeal_status = appeal_status;
    }

    public String getAppealer() {
        return appealer;
    }

    public void setAppealer(String appealer) {
        this.appealer = appealer;
    }

    public Date getAppeal_time() {
        return appeal_time;
    }

    public void setAppeal_time(Date appeal_time) {
        this.appeal_time = appeal_time;
    }

    public Date getReport_time() {
        return report_time;
    }

    public void setReport_time(Date report_time) {
        this.report_time = report_time;
    }
}
