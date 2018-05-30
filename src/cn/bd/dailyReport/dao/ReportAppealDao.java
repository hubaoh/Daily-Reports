package cn.bd.dailyReport.dao;

import cn.bd.dailyReport.model.ReportAppeal;
import cn.bd.dailyReport.utils.dto.ReportAppealDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 赵传志 on 2018/3/14.
 * 日报申诉dao
 */

public interface ReportAppealDao {

    // 新建日报申诉
    @Insert("insert into report_appeal (id,report_writer, report_type, handlingScore,appealReason, appeal_status, report_time) values(NULL,#{report_writer}, #{report_type}, #{handlingScore}, #{appealReason}, #{appeal_status}, #{report_time})")
    void create(ReportAppeal reportAppeal);

    // 查询当前登录用户所有申诉日报申诉
    @Select("select * from report_appeal where report_writer=#{report_writer} order by report_time desc limit #{page},#{pageSize} ")
    List<ReportAppeal> findAllReportAppeal(@Param("report_writer") String report_writer, @Param("page") int page, @Param("pageSize") int pageSize);

    // 查询所有申诉未处理的日报申诉信息
    @Select("SELECT * FROM " +
            "report_appeal AS ra INNER JOIN report AS r ON r.`report_writer` = ra.`report_writer` AND r.`report_time` = ra.`report_time` " +
            "inner JOIN report_checked AS rc ON r.`report_writer` = rc.`report_writer` AND r.`report_time` = rc.`report_time`  " +
            "WHERE ra.`appeal_status` = '01' ORDER BY r.`report_time` DESC limit #{page},#{pageSize}")
    List<ReportAppealDto> findAllReportAppealing(@Param("page") int page, @Param("pageSize")int pageSize);

    // 寻找当前未处理的日报申诉的数量
    @Select("SELECT COUNT(*) AS number FROM report_appeal WHERE appeal_status = '01'")
    int findReportAppealingNumber();

    // 通过系统管理员对申诉的日报中的信息进行修改
    @Update("update report_appeal set handledScore=#{handledScore}, handleView=#{handleView},appealer=#{appealer}, appeal_time=#{appeal_time}, appeal_status=#{appeal_status} where id=#{id}")
    void updateByAppealReason(ReportAppealDto reportAppealDto);

    // 根据id 来查找日报申诉实体类
    @Select("select * from report_appeal where id=#{id}")
    ReportAppeal findOne(Long id);

    // 获取所有的日报申诉记录
    @Select("select * from report_appeal where report_writer=#{report_writer}")
    List<ReportAppeal> getAll(@Param("report_writer") String report_writer);

    // 查找所有未处理的申诉日报的数量
    @Select("select * from report_appeal where appeal_status = '01'")
    List<ReportAppeal> getAllReportAppealing();
}
