package cn.bd.dailyReport.dao;

import cn.bd.dailyReport.model.Dept;
import cn.bd.dailyReport.utils.dto.DeptDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DeptDao {

	// 获取所有的部门
	@Select("select * from dept")
	List<Dept> getAllDept();

	// 通过部门名称获得部门信息
	@Select("select * from dept where dept_name = #{deptName}")
	Dept findByDeptName(@Param("deptName") String deptName);


	// 分页返回所有的部门
	@Select("select d.id as id, d.dept_number as dept_number, d.dept_name as dept_name, count(employeeNumber) as staff_number  from dept as d left join user as u on d.dept_name = u.deptName group by d.dept_name order by staff_number desc limit #{page},#{pageSize}")
	List<DeptDto> list(@Param("page") int page, @Param("pageSize") int pageSize);

	// 修改部门名称
	@Update("update dept set dept_name = #{dept_name} where id = #{id}")
	void update(Dept dept);

	// 获得单个部门
	@Select("select * from dept where id = #{id}")
	Dept findOne(@Param("id") Long id);

	// 删除部门
	@Delete("delete from dept where id=#{id}")
	void delete(Long id);

	// 新增部门
	@Insert("insert into dept (id,dept_name, dept_number, createTime,createUser) values(NULL,#{dept_name}, #{dept_number}, #{createTime}, #{createUser})")
	void create(Dept dept);

}
