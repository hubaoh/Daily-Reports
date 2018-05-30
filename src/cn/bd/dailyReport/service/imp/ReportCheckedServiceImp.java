package cn.bd.dailyReport.service.imp;

import cn.bd.dailyReport.dao.ReportCheckedDao;
import cn.bd.dailyReport.model.ReportChecked;
import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.service.ReportCheckedService;
import cn.bd.dailyReport.utils.dto.ReportCheckedDto;
import cn.bd.dailyReport.utils.messages.Message;
import cn.bd.dailyReport.utils.messages.MessageType;
import cn.bd.dailyReport.utils.pages.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 赵传志 on 2018/3/10.
 */
@Service("reportCheckedService")
public class ReportCheckedServiceImp implements ReportCheckedService {

    @Autowired
    private ReportCheckedDao reportCheckedDao;


    /**
     * 新建日报考评
     * @param reportChecked
     * @return
     */
    @Override
    public ResponseEntity<Message> create(ReportChecked reportChecked) {
        reportCheckedDao.create(reportChecked);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS), HttpStatus.OK);
    }

    /**
     * 分页返回当前部门需要考评的日报
     * @param currentUser
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public ResponseEntity<Message> findReportChecking(User currentUser, int page, int pageSize) {
        int pageNumber = (page-1)*pageSize;
        List<ReportCheckedDto> reportCheckedDtos = reportCheckedDao.findReportCheckedByReportType(currentUser.getDeptName(), pageNumber, pageSize);
        List<ReportChecked> reportCheckedAll = reportCheckedDao.getAllReportCheckeding();
        Page<ReportCheckedDto> reportCheckedDtoPage = new Page<>(reportCheckedDtos, reportCheckedAll.size());
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS,reportCheckedDtoPage),HttpStatus.OK);
    }

    /**
     * 获取当前正在考评的日报实体类
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<Message> findOneByChecking(Long id) {
        ReportChecked reportChecked = reportCheckedDao.findOneByChecking(id);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, reportChecked), HttpStatus.OK);
    }

    /**
     * 将考评后的分数加入到数据库中
     * @param reportChecked
     * @param currentUser
     * @return
     */
    @Override
    public ResponseEntity<Message> updateByCheckedScore(ReportChecked reportChecked, User currentUser) {
        reportChecked.setReportChecked_status("02"); // 已考评
        reportChecked.setChecked_time(new Date());
        reportChecked.setChecker(currentUser.getUserName());
        reportChecked.setTotalScore(reportChecked.getReport_planScore()+reportChecked.getReport_workScore());
        reportCheckedDao.updateByChecckedScore(reportChecked);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS), HttpStatus.OK);
    }

    /**
     * 获取当前登录用户提交的所有日报考评
     * @param report_time 日报的时间
     * @param reportChecked_status 日报考评的审核状态
     * @param page 页码
     * @param pageSize 页面大小
     * @param currentUser 当前登录用户
     * @return
     */
    @Override
    public ResponseEntity<Message> findAllByCurrentUser(String report_time, String reportChecked_status, int page, int pageSize, User currentUser) {
        int pageNumber = (page - 1)*pageSize;
        if (report_time == null) report_time = "";
        if (reportChecked_status == null) reportChecked_status = "";
        List<ReportCheckedDto> reportCheckedDtosList = reportCheckedDao.findAllByCurrentUser(currentUser.getUserName(),currentUser.getDeptName(),pageNumber, pageSize, reportChecked_status, report_time);
        List<ReportChecked> reportCheckedAll = reportCheckedDao.getAll(currentUser.getUserName());
        Page<ReportCheckedDto> reportCheckedDtoPage = new Page<>(reportCheckedDtosList, reportCheckedAll.size());
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, reportCheckedDtoPage), HttpStatus.OK);
    }

    /**
     * 查询未考评的日报的数量
     * @param report_type 日报的类型
     * @return
     */
    @Override
    public ResponseEntity<Message> findReportCheckingCount(String report_type) {
       int reportChekingNumber =  reportCheckedDao.findReportCheckingCount(report_type);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS,reportChekingNumber),HttpStatus.OK);
    }

    /**
     * 查询当前登录用户所在部门日报考评分数排名前十的员工
     * @param report_type
     * @param report_time
     * @return
     */
    @Override
    public ResponseEntity<Message> findUsersByTotalScoreDesc(String report_type, String report_time) {
        if(report_time == null) report_time="";
        List<ReportChecked> reportCheckedList = reportCheckedDao.findUsersByTotalScoreDesc(report_type, report_time);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, reportCheckedList), HttpStatus.OK);
    }
}
