package cn.bd.dailyReport.service;

import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.utils.dto.UserDto;
import cn.bd.dailyReport.utils.messages.Message;
import org.springframework.http.ResponseEntity;

public interface UserService  {

    // 新增用户
    public ResponseEntity<Message> create(UserDto userDto);
    // 删除用户
    public ResponseEntity<Message> delete(Long id);
    // 修改用户信息
    public ResponseEntity<Message> update(UserDto userDto, Long id);

    // 获得所有用户
    public ResponseEntity<Message> list(int page, int pageSize, String employeeNumber, String userName, String deptName);

    // 获得单个用户
    public ResponseEntity<Message> findOne(Long id);

    // 完善用户信息
    ResponseEntity<Message> updatePersonalInfo(User user);

    // 修改密码
    ResponseEntity<Message> updatePassWord(Long id, String password);

    // 找回密码
    ResponseEntity<Message> findPassWord(String userName, String phoneNumber, String idCard, String likeNumber);
}
