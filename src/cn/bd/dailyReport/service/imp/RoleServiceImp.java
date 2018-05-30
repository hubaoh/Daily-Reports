package cn.bd.dailyReport.service.imp;

import cn.bd.dailyReport.dao.RoleDao;
import cn.bd.dailyReport.model.Role;
import cn.bd.dailyReport.service.RoleService;
import cn.bd.dailyReport.utils.messages.Message;
import cn.bd.dailyReport.utils.messages.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 赵传志 on 2018/2/5.
 */
@Service("roleService")
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 获得全部的用户权限
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Message> getAll() {
        List<Role> list = roleDao.getAll();
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, list), HttpStatus.OK);
    }
}
