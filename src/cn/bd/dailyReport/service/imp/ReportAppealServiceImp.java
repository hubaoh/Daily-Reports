package cn.bd.dailyReport.service.imp;

import cn.bd.dailyReport.dao.ReportAppealDao;
import cn.bd.dailyReport.dao.ReportCheckedDao;
import cn.bd.dailyReport.model.ReportAppeal;
import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.service.ReportAppealService;
import cn.bd.dailyReport.utils.dto.ReportAppealDto;
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
 * Created by 赵传志 on 2018/3/14.
 */
@Service("reportAppealService")
public class ReportAppealServiceImp implements ReportAppealService {

    @Autowired
    private ReportAppealDao reportAppealDao;

    @Autowired
    private ReportCheckedDao reportCheckedDao;


    /**
     * 新建日报申诉
     * @param reportCheckedDto
     * @return
     */
    @Override
    public ResponseEntity<Message> create( ReportCheckedDto reportCheckedDto) {
       ReportAppeal reportAppeal = new ReportAppeal();
        reportAppeal.setReport_time(reportCheckedDto.getReport_time());
        reportAppeal.setAppealReason(reportCheckedDto.getAppealReason());
        reportAppeal.setReport_type(reportCheckedDto.getReport_type());
        reportAppeal.setAppeal_status("01"); // 未处理
        reportAppeal.setReport_writer(reportCheckedDto.getReport_writer());
        reportAppeal.setHandlingScore(reportCheckedDto.getTotalScore());
        reportAppealDao.create(reportAppeal);
        reportCheckedDao.updateReportCheckedStatus("03",reportCheckedDto.getId()); // 修改日报考评的状态为申诉中
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS), HttpStatus.OK);
    }

    /**
     * 获取当前登录用户的所有申诉日报
     * @param userName
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public ResponseEntity<Message> findAllReportAppeal(String userName, int page, int pageSize) {
        int pageNumber = (page-1)*pageSize;
        List<ReportAppeal> reportAppealList = reportAppealDao.findAllReportAppeal(userName, pageNumber, pageSize);
        List<ReportAppeal> reportAppealAll = reportAppealDao.getAll(userName);
        Page<ReportAppeal> reportAppealPage = new Page<>(reportAppealList, reportAppealAll.size());

        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, reportAppealPage), HttpStatus.OK);
    }

    /**
     * 查询当前所有未处理的日报申诉
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public ResponseEntity<Message> findAllReportAppealing(int page, int pageSize) {
        int pageNumber = (page-1)*pageSize;
        List<ReportAppealDto> reportAppealDtoList = reportAppealDao.findAllReportAppealing(pageNumber, pageSize);
        List<ReportAppeal> reportAppealAll = reportAppealDao.getAllReportAppealing();
        Page<ReportAppealDto> reportAppealDtoPage = new Page<>(reportAppealDtoList, reportAppealAll.size());
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, reportAppealDtoPage), HttpStatus.OK);
    }

    /**
     * 查询未处理申诉日报的数量
     * @return
     */
    @Override
    public ResponseEntity<Message> findReportAppealingNumber() {
        int number = reportAppealDao.findReportAppealingNumber();
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS,number), HttpStatus.OK);
    }

    /**
     * 将申诉结果存入数据库
     * @param reportAppealDto
     * @return
     */
    @Override
    public ResponseEntity<Message> updateReportAppeal(ReportAppealDto reportAppealDto, User currentUser) {
        reportAppealDto.setHandledScore(reportAppealDto.getReport_planScore()+reportAppealDto.getReport_workScore());
        reportAppealDto.setAppeal_time(new Date());
        reportAppealDto.setAppealer(currentUser.getUserName());
        reportAppealDto.setAppeal_status("02"); // 已处理
        reportAppealDao.updateByAppealReason(reportAppealDto);
        reportAppealDto.setReportChecked_status("02"); // 已考评
        reportCheckedDao.updateReportTotalScore(reportAppealDto);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS),HttpStatus.OK);
    }

    /**
     * 根据id来查找实体类
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<Message> findOne(Long id) {
        ReportAppeal reportAppeal = reportAppealDao.findOne(id);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, reportAppeal),HttpStatus.OK);
    }
}
