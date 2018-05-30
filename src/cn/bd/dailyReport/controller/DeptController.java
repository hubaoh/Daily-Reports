package cn.bd.dailyReport.controller;

import cn.bd.dailyReport.model.Dept;
import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.service.DeptService;
import cn.bd.dailyReport.utils.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by 赵传志 on 2018/2/4.
 */
@RestController
@RequestMapping("depts")
public class DeptController {

    @Autowired
    @Qualifier(value = "deptService")
    private DeptService deptService;

    /**
     * 从数据库中获取所有的部门信息
     * @return ResponseEntity
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<Message> getAllDept(){
       return deptService.getAllDept();
    }

    /**
     * 分页获取所有的部门信息
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Message> list(@RequestParam int page, @RequestParam int pageSize){
        return deptService.list(page, pageSize);
    }

    /**
     * 获取一个部门
     * @param id
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> findOne(@PathVariable Long id){
        return deptService.findOne(id);
    }

    /**
     * 修改部门信息
     * @param dept
     * @param id
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Message> update(@RequestBody Dept dept, @PathVariable Long id){
        return deptService.update(dept, id);
    }

    /**
     * 删除一个部门
     * @param id
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Message> delete(@PathVariable Long id){
        return deptService.delete(id);
    }

    /**
     * 新建一个部门
     * @param dept
     * @param session
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Message> create(@RequestBody Dept dept, HttpSession session){
        User currentUser = (User) session.getAttribute("loginer");
        return  deptService.create(dept, currentUser);
    }


}
