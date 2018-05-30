package cn.bd.dailyReport.model;

import java.util.Date;

/**
 * Created by 赵传志 on 2018/3/9.
 * 正在考评的日报实体类
 */
public class ReportChecking {

    private Long id; // 数据库序号

    private String report_writer; // 日报作者

    private Date report_time; // 日报日期

    private Long report_workScore; // 今日工作情况评分

    private Long report_planScore; // 明日计划评分

    private Long totalScore; // 考评总分

    private String report_remark; // 日报评语


}
