package cn.bd.dailyReport.dao;

import cn.bd.dailyReport.model.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

	// 通过权限代码来查询权限
	public Role getByRoleValue(@Param("roleValue") String roleValue);

	@Select("select * from role")
	List<Role> getAll();

	@Select("select * from role where roleValue = #{roleValue}")
	Role findByRoleValue(@Param("roleValue") String roleValue);

}
