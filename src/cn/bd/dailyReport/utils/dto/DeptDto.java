package cn.bd.dailyReport.utils.dto;

/**
 * Created by 赵传志 on 2018/3/5.
 */
public class DeptDto {

    private Long id; // 数据库序号

    private String dept_number; // 部门编号

    private String dept_name; // 部门名称

    private String staff_number; // 员工数量

    public DeptDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_number() {
        return dept_number;
    }

    public void setDept_number(String dept_number) {
        this.dept_number = dept_number;
    }

    public String getStaff_number() {
        return staff_number;
    }

    public void setStaff_number(String staff_number) {
        this.staff_number = staff_number;
    }
}
