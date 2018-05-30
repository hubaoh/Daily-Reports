package cn.bd.dailyReport.service.imp;

import cn.bd.dailyReport.dao.ReportCheckedDao;
import cn.bd.dailyReport.dao.ReportDao;
import cn.bd.dailyReport.model.Report;
import cn.bd.dailyReport.model.ReportChecked;
import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.service.ReportService;
import cn.bd.dailyReport.utils.messages.Message;
import cn.bd.dailyReport.utils.messages.MessageType;
import cn.bd.dailyReport.utils.pages.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 赵传志 on 2018/3/7.
 */
@Service("reportService")
public class ReportServiceImp implements ReportService {

    @Autowired
    private ReportDao reportDao;

    @Autowired
    private ReportCheckedDao reportCheckedDao;


    /**
     * 分页获取当前登录用户的所有工作日报
     * @param currentUser 当前登录用户
     * @param page 页数
     * @param pageSize 页面大小
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Message> findOwnerReports(User currentUser, int page, int pageSize, String report_time) {
       if (report_time == null) {
           report_time = "";
       }
        int pageNumber = (page-1)*pageSize;

        List<Report> reports = reportDao.findOwnerReports(currentUser.getUserName(),pageNumber, pageSize, report_time);
        List<Report> reportsAll = reportDao.getAll(report_time,currentUser.getUserName());
        Page<Report> reportDtoPage = new Page<>(reports,reportsAll.size());
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, reportDtoPage), HttpStatus.OK);
    }

    /**
     * 分页获取当前登录用户所在部门的所有员工的工作日报
     * @param currentUser 当前登录用户
     * @param page 页数
     * @param pageSize 页面大小
     * @param report_time 日报时间
     * @param userName 员工名称
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Message> findAllReports(User currentUser, int page, int pageSize, String report_time, String userName) {
        if (userName == null) userName = "";
        int pageNumber = (page-1)*pageSize;

        List<Report> reports = reportDao.findAllReports(currentUser.getDeptName(),pageNumber, pageSize, userName, report_time);
        List<Report> reportsAll = reportDao.getAllByDept(currentUser.getDeptName(), userName, report_time);
        Page<Report> reportDtoPage = new Page<>(reports,pageSize, reportsAll.size());
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, reportDtoPage), HttpStatus.OK);
    }

    /**
     * 提交日报
     * @param report
     * @return
     */
    @Override
    public ResponseEntity<Message> writeReport(Report report) {
        ReportChecked reportChecked = new ReportChecked();
        reportChecked.setReport_writer(report.getReport_writer());
        reportChecked.setReport_time(report.getReport_time());
        reportChecked.setReport_type(report.getReport_type());
        reportChecked.setSubmit_time(new Date()); // 日报提交时间
        reportChecked.setReportChecked_status("01"); // 01 未考评； 02 已考评； 03 申诉中；
        if (report.getReport_status().equals("01") ){ // 当日报正常提交的时候才将日报状态修改成已提交
            report.setReport_status("02"); // 01 未提交；02 已提交；03补写
        }

        reportDao.update(report);
        reportCheckedDao.create(reportChecked);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS),HttpStatus.OK);
    }

    /**
     * 获得单个日报
     * @param id
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Message> findOne(Long id) {
        Report report = reportDao.findOne(id);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, report), HttpStatus.OK);
    }

    /**
     * 创建日报
     * @param currentUser
     * @return
     */
    @Override
    public ResponseEntity<Message> createReport(Report report, User currentUser) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Long newTime = Long.parseLong(sdf.format(new Date()));
        Long reportTime = Long.parseLong(sdf.format(report.getReport_time()));
        if(reportTime >= newTime){
            report.setReport_status("01"); // 未提交
        }else{
            report.setReport_status("03"); // 补写
        }
        report.setReport_type(currentUser.getDeptName());
        report.setReport_writer(currentUser.getUserName());
        reportDao.createReport(report);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS),HttpStatus.OK);
    }
}
