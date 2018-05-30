package cn.bd.dailyReport.service;

import cn.bd.dailyReport.model.Dept;
import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.utils.messages.Message;
import org.springframework.http.ResponseEntity;

/**
 * Created by 赵传志 on 2018/2/4.
 */
public interface DeptService {

    // 获取数据库中当前所有的部门信息
    ResponseEntity<Message> getAllDept();

    // 分页获取部门信息
    ResponseEntity<Message> list(int page, int pageSize);

    // 修改部门名称
    ResponseEntity<Message> update(Dept dept, Long id);

    // 获得单个部门
    ResponseEntity<Message> findOne(Long id);

    // 删除部门
     ResponseEntity<Message> delete(Long id);

    // 新增部门
    ResponseEntity<Message> create(Dept dept, User currentUser);

}
