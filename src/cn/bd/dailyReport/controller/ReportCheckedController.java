package cn.bd.dailyReport.controller;

import cn.bd.dailyReport.model.ReportChecked;
import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.service.ReportCheckedService;
import cn.bd.dailyReport.utils.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by 赵传志 on 2018/3/11.
 */
@Controller
@RequestMapping("report_checkeds")
public class ReportCheckedController {

    @Autowired
    @Resource(name = "reportCheckedService")
    private ReportCheckedService reportCheckedService;

    /**
     * 分页获取当前部门的所有未考评的日报
     * @param page
     * @param pageSize
     * @param session
     * @return
     */
    @RequestMapping(value = "/reportChecking", method = RequestMethod.GET)
    public ResponseEntity<Message> findReportChecking(int page, int pageSize, HttpSession session){
        User currentUser = (User) session.getAttribute("loginer");
        return reportCheckedService.findReportChecking(currentUser, page, pageSize);
    }

    /**
     * 获取当前正在考评的日报实体类
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> findOneByChecking(@PathVariable Long id) {
        return reportCheckedService.findOneByChecking(id);
    }

    /**
     * 将考评后的分数加入到数据库中
     * @param reportChecked
     * @param session
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity<Message> updateByCheckingScore(@RequestBody ReportChecked reportChecked, HttpSession session){
        User currentUser  = (User) session.getAttribute("loginer");
        return reportCheckedService.updateByCheckedScore(reportChecked, currentUser);
    }

    /**
     * 当前登录用户提交的所有日报考评
     * @param report_time
     * @param reportChecked_status
     * @param page
     * @param pageSize
     * @param session
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Message> findAllReportCheckedByCurrentUser(String report_time,String reportChecked_status, int page, int pageSize, HttpSession session){
        User currentUser = (User) session.getAttribute("loginer");
        return reportCheckedService.findAllByCurrentUser(report_time,reportChecked_status,page,pageSize, currentUser);
    }

    /**
     * 查询当前未考评日报的数量
     * @param session
     * @return ResponseEntity
     */
    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public ResponseEntity<Message> findReportCheckingCount(HttpSession session){
        User currentUser = (User) session.getAttribute("loginer");
        return reportCheckedService.findReportCheckingCount(currentUser.getDeptName());
    }

    /**
     * 查询当前登录用户所在部门的日报考核分数前十的员工
     * @param report_time
     * @param session
     * @return
     */
    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public ResponseEntity<Message> findUsersByTotalScoreDesc(String report_time, HttpSession session){
        User currentUser = (User) session.getAttribute("loginer");
        return reportCheckedService.findUsersByTotalScoreDesc(currentUser.getDeptName(),report_time);
    }
}

