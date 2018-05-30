package cn.bd.dailyReport.controller;

import cn.bd.dailyReport.service.RoleService;
import cn.bd.dailyReport.utils.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 赵传志 on 2018/2/5.
 */
@Controller
@RequestMapping("roles")
public class RoleController {

    @Autowired
    @Qualifier(value = "roleService")
    private RoleService roleService;


    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<Message> getAll(){
        return roleService.getAll();
    }
}
