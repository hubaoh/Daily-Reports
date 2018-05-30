package cn.bd.dailyReport.service.imp;

import cn.bd.dailyReport.dao.DeptDao;
import cn.bd.dailyReport.dao.RoleDao;
import cn.bd.dailyReport.dao.UserDao;
import cn.bd.dailyReport.model.Dept;
import cn.bd.dailyReport.model.Role;
import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.service.UserService;
import cn.bd.dailyReport.utils.dto.UserDto;
import cn.bd.dailyReport.utils.messages.Message;
import cn.bd.dailyReport.utils.messages.MessageType;
import cn.bd.dailyReport.utils.pages.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private DeptDao deptDao;


    /**
     * 创建一个新用户
     * @param userDto
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Message> create(UserDto userDto) {
        User user = new User(userDto);
        userDao.create(user);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS), HttpStatus.OK);
    }

    /**
     * 删除一个用户
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<Message> delete(Long id) {
        userDao.delete(id);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS), HttpStatus.OK);
    }


    /**
     * 修改用户信息
     * @param userDto
     * @param id
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Message> update(UserDto userDto, Long id) {
        User user = new User(userDto,id);
        userDao.update(user);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS), HttpStatus.OK);
    }


    // 分页获取全部的用户
    @Override
    public ResponseEntity<Message> list(int page, int pageSize, String employeeNumber, String userName, String deptName) {

        if (employeeNumber == null) employeeNumber = "";
        if (userName == null) userName = "";
        if (deptName == null || deptName.equals("全部") ) deptName = "";
        int pageNumber = (page-1)*pageSize;
        List<User> list = userDao.list(pageNumber, pageSize, employeeNumber, userName, deptName);
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user:list){
            Role role = roleDao.getByRoleValue(user.getRoleValue());
            UserDto userDto = new UserDto(user, role);
            userDtoList.add(userDto);
        }
        List<User> userAll = userDao.getAll(employeeNumber, userName, deptName);
        Page<UserDto> users = new Page<>(userDtoList, userAll.size());

        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, users), HttpStatus.OK);
    }

    /**
     * 根据id查找一个用户的信息
     * @param id
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Message> findOne(Long id) {
        User user = userDao.findOne(id);
        Role role = roleDao.getByRoleValue(user.getRoleValue());
        Dept dept = deptDao.findByDeptName(user.getDeptName());
        UserDto userDto = new UserDto(user, role, dept);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, userDto), HttpStatus.OK);
    }


    // 完善信息
    @Override
    public ResponseEntity<Message> updatePersonalInfo(User user) {
        userDao.updatePersonalInfo(user);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS),HttpStatus.OK);
    }

    // 修改密码
    @Override
    public ResponseEntity<Message> updatePassWord(Long id, String password) {
        userDao.updatePassWord(id, password);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS),HttpStatus.OK);
    }

    // 找回密码
    @Override
    public ResponseEntity<Message> findPassWord(String userName, String phoneNumber, String idCard, String likeNumber) {
        User user = userDao.findPassWord(userName,phoneNumber,idCard,likeNumber);
        if (user != null){
            return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS,user),HttpStatus.OK);
        }else {
            return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_ERROR),HttpStatus.OK);
        }

    }


}
