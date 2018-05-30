package cn.bd.dailyReport.utils.dto;

import cn.bd.dailyReport.model.Report;
import cn.bd.dailyReport.model.ReportChecked;

import java.util.Date;

/**
 * Created by 赵传志 on 2018/3/11.
 * 日报考评实体类
 */
public class ReportCheckedDto {

    private Long id; // 数据库序号

    private String report_todayWork; // 日报今日工作记录

    private String report_todayProblem; // 日报今日遇到的问题

    private String report_tomorrowPlan; // 日报明日计划

    private String remark; // 备注

    private Long report_workScore; // 今日工作情况评分

    private Long report_planScore; // 明日计划评分

    private Long totalScore; // 考评总分

    private String report_remark; // 日报评语

    private String reportChecked_status; // 状态 1 未考评 、2 已考评 3、申诉中

    private Date checked_time; // 考评时间

    private String checker; // 审核人

    private Date report_time; // 日报时间

    private String report_type; // 日报类型 以部门为类

    private String report_writer; // 日报作者

    private String report_status; // 日报状态 01 未提交; 02 已提交；03 补写

    private Date submit_time; // 日报提交时间

    private String appealReason; // 申诉原因

    private String handleView; // 申诉处理意见

    public ReportCheckedDto() {
    }

    public ReportCheckedDto(Report report, ReportChecked reportChecked) {
        this.report_todayWork = report.getReport_todayWork();
        this.report_todayProblem = report.getReport_todayProblem();
        this.report_tomorrowPlan = report.getReport_tomorrowPlan();
        this.remark = report.getRemark();
        this.report_workScore = reportChecked.getReport_workScore();
        this.report_planScore = reportChecked.getReport_planScore();
        this.totalScore = reportChecked.getTotalScore();
        this.report_remark = reportChecked.getReport_remark();
        this.reportChecked_status = reportChecked.getReportChecked_status();
        this.checked_time = reportChecked.getChecked_time();
        this.checker = reportChecked.getChecker();
        this.report_time = report.getReport_time();
        this.report_type = report.getReport_type();
        this.report_writer = report.getReport_writer();
        this.report_status = report.getReport_status();
        this.submit_time = reportChecked.getSubmit_time();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReport_todayWork() {
        return report_todayWork;
    }

    public void setReport_todayWork(String report_todayWork) {
        this.report_todayWork = report_todayWork;
    }

    public String getReport_todayProblem() {
        return report_todayProblem;
    }

    public void setReport_todayProblem(String report_todayProblem) {
        this.report_todayProblem = report_todayProblem;
    }

    public String getReport_tomorrowPlan() {
        return report_tomorrowPlan;
    }

    public void setReport_tomorrowPlan(String report_tomorrowPlan) {
        this.report_tomorrowPlan = report_tomorrowPlan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getReport_remark() {
        return report_remark;
    }

    public void setReport_remark(String report_remark) {
        this.report_remark = report_remark;
    }

    public String getReportChecked_status() {
        return reportChecked_status;
    }

    public void setReportChecked_status(String reportChecked_status) {
        this.reportChecked_status = reportChecked_status;
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

    public String getReport_writer() {
        return report_writer;
    }

    public void setReport_writer(String report_writer) {
        this.report_writer = report_writer;
    }

    public String getReport_status() {
        return report_status;
    }

    public void setReport_status(String report_status) {
        this.report_status = report_status;
    }

    public Date getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(Date submit_time) {
        this.submit_time = submit_time;
    }

    public String getAppealReason() {
        return appealReason;
    }

    public void setAppealReason(String appealReason) {
        this.appealReason = appealReason;
    }

    public String getHandleView() {
        return handleView;
    }

    public void setHandleView(String handleView) {
        this.handleView = handleView;
    }
}
