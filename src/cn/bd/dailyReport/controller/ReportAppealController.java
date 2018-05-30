package cn.bd.dailyReport.controller;

import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.service.ReportAppealService;
import cn.bd.dailyReport.utils.dto.ReportAppealDto;
import cn.bd.dailyReport.utils.dto.ReportCheckedDto;
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
 * Created by 赵传志 on 2018/3/14.
 */
@Controller
@RequestMapping("report_appeals")
public class ReportAppealController {

    @Autowired
    @Resource(name = "reportAppealService")
    private ReportAppealService reportAppealService;

    /**
     * 新建日报申诉
     * @param reportCheckedDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Message> create(@RequestBody ReportCheckedDto reportCheckedDto){
        return reportAppealService.create(reportCheckedDto);
    }

    /**
     * 获取当前登录用户的所有申诉日报
     * @param page
     * @param pageSize
     * @param session
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Message> findAllReportAppeal(int page, int pageSize, HttpSession session){
        User user = (User) session.getAttribute("loginer");
        return reportAppealService.findAllReportAppeal(user.getUserName(),page, pageSize);
    }

    /**
     * 获取所有未处理的申诉日报
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/appealing", method = RequestMethod.GET)
    public ResponseEntity<Message> findAllReportAppealing(int page, int pageSize){
        return reportAppealService.findAllReportAppealing(page, pageSize);
    }

    /**
     * 查询当前所有未处理的申诉日报的数量
     * @return
     */
    @RequestMapping(value = "/number",method = RequestMethod.GET)
    public ResponseEntity<Message> findReportAppealingNumber(){
        return reportAppealService.findReportAppealingNumber();
    }

    /**
     * 将申诉的结果存入数据库中
     * @param reportAppealDto
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Message> updateReportAppeal(@RequestBody ReportAppealDto reportAppealDto, HttpSession session){
        User currentUser = (User) session.getAttribute("loginer");
        return reportAppealService.updateReportAppeal(reportAppealDto, currentUser);
    }

    /**
     * 根据id来查询实体类
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> findOne(@PathVariable Long id){
        return reportAppealService.findOne(id);
    }
}
