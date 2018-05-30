package cn.bd.dailyReport.service;

import cn.bd.dailyReport.utils.messages.Message;
import org.springframework.http.ResponseEntity;

/**
 * Created by 赵传志 on 2018/2/5.
 */
public interface RoleService {

    // 获取全部的用户权限
    ResponseEntity<Message> getAll();
}
