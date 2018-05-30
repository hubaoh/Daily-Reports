package cn.bd.dailyReport.service;

import cn.bd.dailyReport.model.ReportChecked;
import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.utils.messages.Message;
import org.springframework.http.ResponseEntity;

/**
 * Created by 赵传志 on 2018/3/10.
 */
public interface ReportCheckedService {

    /**
     * 新建日报考评
     * @param reportChecked
     */
    ResponseEntity<Message> create(ReportChecked reportChecked);

    /**
     * 分页返回当前部门需要考评的日报
     * @param currentUser
     * @param page
     * @param pageSize
     * @return
     */
    ResponseEntity<Message> findReportChecking(User currentUser,int page, int pageSize);

    /**
     * 获取当前正在考评的日报实体类
     * @param id
     * @return
     */
    ResponseEntity<Message> findOneByChecking(Long id);

    /**
     * 将考评后的分数加入到数据库中
     * @param reportChecked
     * @param currentUser
     * @return
     */
    ResponseEntity<Message> updateByCheckedScore(ReportChecked reportChecked,User currentUser);

    /**
     * 获取当前登录用户的所有已经提交的日报考评情况
     * @param report_time 日报的时间
     * @param reportChecked_status 日报考评的审核状态
     * @param page 页码
     * @param pageSize 页面大小
     * @param currentUser 当前登录用户
     * @return ResponseEntity
     */
    ResponseEntity<Message> findAllByCurrentUser( String report_time,String reportChecked_status, int page, int pageSize, User currentUser);

    /**
     * 查询未考评日报的数量
     * @param report_type 日报的类型
     * @return
     */
    ResponseEntity<Message> findReportCheckingCount(String report_type);

    /**
     * 查询当前登录用户所在部门的日报考评前十的人员
     * @param report_type
     * @param report_time
     * @return
     */
    ResponseEntity<Message> findUsersByTotalScoreDesc(String report_type, String report_time);
}
