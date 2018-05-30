package cn.bd.dailyReport.service;

import cn.bd.dailyReport.model.Report;
import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.utils.messages.Message;
import org.springframework.http.ResponseEntity;

/**
 * Created by 赵传志 on 2018/3/7.
 */
public interface ReportService {

    /**
     * 分页获取当前登录用户的所有工作日报
     * @param currentUser 当前登录用户
     * @param page 页数
     * @param pageSize 页面大小
     * @return ResponseEntity
     */
    ResponseEntity<Message> findOwnerReports(User currentUser, int page, int pageSize,String report_time);

    /**
     * 分页获取当前登录用户所在部门的所有员工的工作日报
     * @param currentUser 当前登录用户
     * @param page 页数
     * @param pageSize 页面大小
     * @param report_time 日报时间
     * @param userName 员工名称
     * @return
     */
    ResponseEntity<Message> findAllReports(User currentUser, int page, int pageSize, String report_time, String userName);

    /**
     * 提交日报
     * @param report
     * @return
     */
    ResponseEntity<Message> writeReport(Report report);

    /**
     * 获取单个日报
     * @param id
     * @return
     */
    ResponseEntity<Message> findOne(Long id);

    /**
     * 新建日报
     * @return
     */
    ResponseEntity<Message> createReport(Report report, User currentUser);
}
