package cn.bd.dailyReport.model;

import java.util.Date;

/**
 * Created by 赵传志 on 2018/3/9.
 *
 * 日报考评实体类
 */
public class ReportChecked {

    private Long id; // 数据库序号

    private String report_writer; // 日报作者

    private Date report_time; // 日报日期

    private Long report_workScore; // 今日工作情况评分

    private Long report_planScore; // 明日计划评分

    private Long totalScore; // 考评总分

    private String report_remark; // 日报评语

    private String reportChecked_status; // 状态 1 未考评 、2 已考评 3、申诉中

    private Date checked_time; // 日报被考评时间

    private String checker; // 审核人

    private String report_type; // 日报类型

    private Date submit_time; // 日报提交时间

    public ReportChecked() {
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

    public Date getReport_time() {
        return report_time;
    }

    public void setReport_time(Date report_time) {
        this.report_time = report_time;
    }

    public Long getReport_workScore() {
        return report_workScore;
    }

    public void setReport_workScore(Long report_workScore) {
        this.report_workScore = report_workScore;
    }

    public Long getReport_planScore() {
        return report_planScore;
    }

    public void setReport_planScore(Long report_planScore) {
        this.report_planScore = report_planScore;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    public String getReportChecked_status() {
        return reportChecked_status;
    }

    public void setReportChecked_status(String reportChecked_status) {
        this.reportChecked_status = reportChecked_status;
    }

    public String getReport_remark() {
        return report_remark;
    }

    public void setReport_remark(String report_remark) {
        this.report_remark = report_remark;
    }

    public Date getChecked_time() {
        return checked_time;
    }

    public void setChecked_time(Date checked_time) {
        this.checked_time = checked_time;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public Date getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(Date submit_time) {
        this.submit_time = submit_time;
    }
}
