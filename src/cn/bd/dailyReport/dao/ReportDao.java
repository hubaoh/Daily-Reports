package cn.bd.dailyReport.dao;

import cn.bd.dailyReport.model.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 赵传志 on 2018/3/7.
 * 日报相关的数据库操作
 */
public interface ReportDao {

    // 分页获取当前登录用户的所有日报
    @Select("select * from report where report_writer = #{currentUser} and report_time like concat('%',#{report_time},'%') order by report_time desc limit #{page},#{pageSize} ")
    List<Report> findOwnerReports(@Param("currentUser") String currentUser, @Param("page") int page, @Param("pageSize") int pageSize, @Param("report_time") String report_time);

    // 分页获取当前登录用户所在部门的所有员工的工作日报
    @Select("select * from report where report_type = #{dept_name} and report_time like concat('%',#{report_time},'%') and report_writer like concat('%',#{userName},'%') order by report_time desc limit #{page},#{pageSize}")
    List<Report> findAllReports(@Param("dept_name") String dept_name, @Param("page") int page, @Param("pageSize") int pageSize, @Param("userName") String userName, @Param("report_time") String report_time);

    // 提交日报
    @Update("update report set report_todayWork=#{report_todayWork}, report_todayProblem=#{report_todayProblem}, report_tomorrowPlan=#{report_tomorrowPlan}, remark=#{remark}, report_status=#{report_status} where id = #{id}")
    void update(Report report);

    // 获取单个日报
    @Select("select * from report where id = #{id}")
    Report findOne(@Param("id") Long id);

    // 新建日报
    @Insert("insert into report (id, report_time, report_type, report_writer, report_todayWork, report_todayProblem, report_tomorrowPlan, report_status, remark) values(null,#{report_time},#{report_type},#{report_writer},#{report_todayWork},#{report_todayProblem},#{report_tomorrowPlan},#{report_status},#{remark})")
    void createReport(Report report);

    // 查询当前登录用户所有日报记录
    @Select("select * from report where report_writer = #{currentUser} and report_time like concat('%',#{report_time},'%')")
    List<Report> getAll(@Param("report_time") String report_time, @Param("currentUser") String currentUser);

    // 查询当前登录用户所在部门员工当日的全部日报数量
    @Select("select * from report where report_type = #{dept_name} and report_time like concat('%',#{report_time},'%') and report_writer like concat('%',#{userName},'%')")
    List<Report> getAllByDept(@Param("dept_name") String dept_name, @Param("userName") String userName, @Param("report_time") String report_time);
}
