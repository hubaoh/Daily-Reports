package cn.bd.dailyReport.service;

import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.utils.dto.ReportAppealDto;
import cn.bd.dailyReport.utils.dto.ReportCheckedDto;
import cn.bd.dailyReport.utils.messages.Message;
import org.springframework.http.ResponseEntity;

/**
 * Created by 赵传志 on 2018/3/14.
 */
public interface ReportAppealService {

    /**
     * 创建日报申诉并初始化
     * @param reportCheckedDto
     * @return
     */
    ResponseEntity<Message> create(ReportCheckedDto reportCheckedDto);

    /**
     * 获取当前登录用户所有的申诉日报
     * @param userName
     * @param page
     * @param pageSize
     * @return
     */
    ResponseEntity<Message> findAllReportAppeal(String userName, int page, int pageSize);

    /**
     * 查询所有未处理的日报申诉
     * @param page
     * @param pageSize
     * @return
     */
    ResponseEntity<Message> findAllReportAppealing(int page, int pageSize);

    /**
     * 寻找所有未处理的日报申诉的数量
     * @return
     */
    ResponseEntity<Message> findReportAppealingNumber();

    /**
     * 将申诉结果存入数据库
     * @param reportAppealDto
     * @return
     */
    ResponseEntity<Message> updateReportAppeal(ReportAppealDto reportAppealDto, User currentUser);

    /**
     * 根据id来查询实体类
     * @param id
     * @return
     */
    ResponseEntity<Message> findOne(Long id);
}
