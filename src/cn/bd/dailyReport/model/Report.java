package cn.bd.dailyReport.model;

import java.util.Date;

/**
 * Created by 赵传志 on 2018/3/7.
 * 日报实体类
 */
public class Report {

    private Long id; // 数据库序号

    private Date report_time; // 日报时间

    private String report_type; // 日报类型 以部门为类

    private String report_todayWork; // 日报今日工作记录

    private String report_todayProblem; // 日报今日遇到的问题

    private String report_tomorrowPlan; // 日报明日计划

    private String remark; // 备注

    private String report_writer; // 日报作者

    private String report_status; // 日报状态 01 未提交; 02 已提交；03 补写

    public Report() {
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

    public String getReport_writer() {
        return report_writer;
    }

    public String getReport_status() {
        return report_status;
    }

    public void setReport_status(String report_status) {
        this.report_status = report_status;
    }

    public void setReport_writer(String report_writer) {
        this.report_writer = report_writer;
    }
}
