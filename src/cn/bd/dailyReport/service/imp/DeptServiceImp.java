package cn.bd.dailyReport.service.imp;

import cn.bd.dailyReport.dao.DeptDao;
import cn.bd.dailyReport.dao.UserDao;
import cn.bd.dailyReport.model.Dept;
import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.service.DeptService;
import cn.bd.dailyReport.utils.dto.DeptDto;
import cn.bd.dailyReport.utils.messages.Message;
import cn.bd.dailyReport.utils.messages.MessageType;
import cn.bd.dailyReport.utils.pages.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 赵传志 on 2018/2/4.
 */
@Service("deptService")
public class DeptServiceImp implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private UserDao userDao;


    /**
     * 获取所有的部门
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Message> getAllDept() {
        List<Dept> list = deptDao.getAllDept();
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, list), HttpStatus.OK);
    }

    /**
     * 分页获取所有的部门
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Message> list( int page, int pageSize) {
        int pageNumber = (page-1)*pageSize;
        List<DeptDto> deptsDtoList = deptDao.list(pageNumber, pageSize);
        List<Dept> deptAll = deptDao.getAllDept();
        Page<DeptDto> depts = new Page<>(deptsDtoList, deptAll.size());
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS,depts), HttpStatus.OK);
    }

    /**
     * 修改部门名称
     * @param dept
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Message> update(Dept dept, Long id) {
        Dept oldDept = deptDao.findOne(id);
        userDao.updateDeptName(oldDept.getDept_name(),dept.getDept_name());
        deptDao.update(dept);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS), HttpStatus.OK);
    }

    /**
     * 获取单个部门
     * @param id
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Message> findOne(Long id) {
        Dept dept = deptDao.findOne(id);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS, dept), HttpStatus.OK);
    }

    /**
     * 删除一个部门
     * @param id
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Message> delete(Long id) {
        deptDao.delete(id);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS), HttpStatus.OK);
    }

    /**
     * 创建一个部门
     * @param dept
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Message> create(Dept dept, User currentUser) {
        dept.setCreateTime(new Date());
        dept.setCreateUser(currentUser.getUserName());
        deptDao.create(dept);
        return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS), HttpStatus.OK);
    }
}
