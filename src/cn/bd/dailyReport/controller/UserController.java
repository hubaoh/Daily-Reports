package cn.bd.dailyReport.controller;

import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.service.UserService;
import cn.bd.dailyReport.utils.dto.UserDto;
import cn.bd.dailyReport.utils.messages.Message;
import cn.bd.dailyReport.utils.messages.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    @Qualifier(value = "userService")
    private UserService userService;

    /**
     * 分页获取用户列表
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Message> list(@RequestParam int page, @RequestParam int pageSize, String employeeNumber, String userName, String deptName){
        return userService.list(page, pageSize, employeeNumber, userName, deptName);
    }

    /**
     * 新建一个用户
     * @param userDto
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Message> create(@RequestBody UserDto userDto, HttpSession session){
        User user = (User) session.getAttribute("loginer");
        userDto.setCreateUser(user.getUserName());
        return userService.create(userDto);
    }

    /**
     * 获取一个用户
     * @param id
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> findOne(@PathVariable Long id){
        return userService.findOne(id);
    }

    /**
     * 修改用户信息
     * @param userDto
     * @param id
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Message> update(@RequestBody UserDto userDto, @PathVariable Long id){
        return userService.update(userDto, id);
    }

    /**
     * 删除一个用户
     * @param id
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Message> delete(@PathVariable Long id){
        return userService.delete(id);
    }

    /**
     * 获得当前登录的用户
     * @param session
     * @return
     */
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public ResponseEntity<Message> getCurrentUser(HttpSession session){
        User currentUser = (User) session.getAttribute("loginer");
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS,currentUser), HttpStatus.OK);
    }

    //完善信息
    @RequestMapping(value = "/personal", method = RequestMethod.PUT)
    public ResponseEntity<Message> updatePersonalInfo(@RequestBody User user, HttpSession session){
        session.setAttribute("loginer",user);
        return userService.updatePersonalInfo(user);
    }

    // 修改密码
    @RequestMapping(value = "password/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Message> updatePassWord(@PathVariable Long id, @RequestParam("password") String password){
        return userService.updatePassWord(id, password);
    }

    // 找回密码
    @RequestMapping(value = "/findPassword", method = RequestMethod.GET)
    public ResponseEntity<Message> findPassWord(@RequestParam("userName") String userName,@RequestParam("phoneNumber") String phoneNumber,@RequestParam("idCard") String idCard,@RequestParam("likeNumber") String likeNumber){
        return userService.findPassWord(userName, phoneNumber,idCard,likeNumber);
    }
}
