package cn.bd.dailyReport.dao;

import cn.bd.dailyReport.model.ReportChecked;
import cn.bd.dailyReport.utils.dto.ReportAppealDto;
import cn.bd.dailyReport.utils.dto.ReportCheckedDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 赵传志 on 2018/3/9.
 */
public interface ReportCheckedDao {

    // 提交日报考评
    @Insert("insert into report_checked (id,report_writer, reportChecked_status, report_time, report_type, submit_time) values(NULL,#{report_writer}, #{reportChecked_status}, #{report_time}, #{report_type}, #{submit_time})")
    void create(ReportChecked reportChecked);

    // 查询当前部门管理员所在部门员工提交的所有未考评的日报
    @Select("SELECT * FROM report_checked AS rc left JOIN report AS r ON rc.`report_time` = r.`report_time` AND rc.`report_writer` = r.`report_writer` where rc.`reportChecked_status` = '01'  AND rc.report_type=#{report_type} order by submit_time desc limit #{page},#{pageSize} ")
    List<ReportCheckedDto> findReportCheckedByReportType( @Param("report_type") String report_type, @Param("page") int page, @Param("pageSize") int pageSize);

    // 获取当前正在考评日报的实体类
    @Select("select * from report_checked where id=#{id}")
    ReportChecked findOneByChecking(@Param("id") Long id);

    // 将考评后的分数添加到数据库中
    @Update("update report_checked set report_workScore=#{report_workScore}, report_planScore=#{report_planScore}, totalScore=#{totalScore}, report_remark=#{report_remark}, reportChecked_status=#{reportChecked_status}, checked_time=#{checked_time}, checker=#{checker} where id=#{id}")
    void updateByChecckedScore(ReportChecked reportChecked);

    // 查询当前登录用户所有已经提交的日报考评
    @Select("SELECT * FROM report_checked AS rc LEFT JOIN report AS r ON rc.`report_time` = r.`report_time` AND rc.`report_writer` = r.`report_writer` WHERE rc.`report_type` = #{report_type} and rc.report_writer=#{userName} and rc.reportChecked_status like concat('%',#{reportChecked_status},'%') and rc.report_time like concat('%',#{report_time},'%') ORDER BY r.report_time DESC limit #{page},#{pageSize}")
    List<ReportCheckedDto> findAllByCurrentUser(@Param("userName") String userName, @Param("report_type") String report_type, @Param("page") int page, @Param("pageSize") int pageSize, @Param("reportChecked_status") String reportChecked_status,@Param("report_time") String report_time);

    // 查询未考评日报的数量
    @Select("select count(*) from report_checked where report_type=#{report_type} and reportChecked_status = '01'")
    int findReportCheckingCount(@Param("report_type") String report_type);

    // 根据id查询日报考评
    @Select("select * from report_checked where id=#{id}")
    ReportChecked findOneById(Long id);

    // 根据id修改日报考评的状态
    @Update("update report_checked set reportChecked_status=#{reportChecked_status} where id=#{id}")
     void updateReportCheckedStatus(@Param("reportChecked_status") String reportChecked_status,@Param("id") Long id);

    // 将申诉结果存入数据库中
    @Update("update report_checked set report_workScore=#{report_workScore}, report_planScore=#{report_planScore}, totalScore=#{handledScore},reportChecked_status=#{reportChecked_status} where report_writer=#{report_writer} and report_time=#{report_time}")
    void updateReportTotalScore(ReportAppealDto reportAppealDto);

    // 查询当前登录用户所在部门日报考评分数前十的人员
    @Select("select * from report_checked where report_type=#{report_type} and report_time like concat('%',#{report_time},'%') order by totalScore desc limit 10 ")
    List<ReportChecked> findUsersByTotalScoreDesc(@Param("report_type") String report_type, @Param("report_time") String report_time);

    // 获取当前登录用户所有日报考评的记录条数
    @Select("select * from report_checked where report_writer=#{userName}")
    List<ReportChecked> getAll(@Param("userName") String userName);

    // 所有未审核的日报考评条数
    @Select("select * from report_checked where reportChecked_status = '01'")
    List<ReportChecked> getAllReportCheckeding();
}
