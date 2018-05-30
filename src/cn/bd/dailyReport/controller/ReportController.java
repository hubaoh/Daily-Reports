package cn.bd.dailyReport.controller;

import cn.bd.dailyReport.model.Report;
import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.service.ReportService;
import cn.bd.dailyReport.utils.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by 赵传志 on 2018/3/7.
 */
@Controller
@RequestMapping("reports")
public class ReportController {

    @Autowired
    @Resource(name="reportService")
    private ReportService reportService;

    /**
     * 分页获取当前登录用户的所有工作日报
     * @param page
     * @param pageSize
     * @param session
     * @return
     */
    @RequestMapping(value = "/personal",method = RequestMethod.GET)
    public ResponseEntity<Message> findOwnerReports(@RequestParam int page, @RequestParam int pageSize, String report_time, HttpSession session){
        User currentUser = (User) session.getAttribute("loginer");
        return reportService.findOwnerReports(currentUser,page,pageSize, report_time);
    }

    /**
     * 分页获取当前登录用户所在部门的所有员工的工作日报
     * @param page
     * @param pageSize
     * @param session
     * @param userName
     * @param report_time
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Message> findAllReports(@RequestParam int page, @RequestParam int pageSize,  String userName, String report_time, HttpSession session){
        User currentUser = (User) session.getAttribute("loginer");
        return reportService.findAllReports(currentUser,page, pageSize, report_time, userName);
    }

    /**
     * 提交日报
     * @param report
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Message> writeReport(@RequestBody Report report){
        return reportService.writeReport(report);
    }

    /**
     * 获取单个日报
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> findOne(@PathVariable Long id) {
        return reportService.findOne(id);
    }

    /**
     * 新建日报
     * @param session
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Message> createReport(@RequestBody Report report, HttpSession session){
        User currentUser = (User) session.getAttribute("loginer");
        return reportService.createReport(report,currentUser);
    }
}
