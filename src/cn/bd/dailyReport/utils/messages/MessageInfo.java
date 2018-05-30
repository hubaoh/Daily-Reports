package cn.bd.dailyReport.utils.messages;

public class MessageInfo {
    public static final String AUTH_LAPSE = "用户失效，请重新登陆";
    public static final String SERVER_INNER_ERROR = "服务器内部错误";
    public static final String CANNOT_MODIFY_BUILTINROLE = "内建角色不能修改";
    public static final String CANNOT_DELETE_BUILTINROLE = "内建角色不能删除";
    public static final String CANNOT_MODIFY_BUILTINUSER = "内建用户不能修改";
    public static final String CANNOT_DELETE_BUILTINUSER = "内建用户不能删除";
    public static final String ROLE_ALREADY_EXIST = "角色名已经存在";
    public static final String CANNOT_REMOVE_ADMIN = "管理员角色不可删除";
    public static final String CANNOT_MODIFY_ADMIN = "管理员信息不能修改";
    public static final String USERNAME_ALREADY_EXIST = "用户名已存在";
    public static final String WRONG_OLD_PASSWORD = "原密码错误";
    public static final String LOGOUT_EXCEPTION = "用户登出异常";
    public static final String ERROR_ON_READEXCEL = "Excel文件读取失败";
    public static final String DELETE_FAIL = "删除失败";
    public static final String LOOK_UP_ROUTE_FAIL = "查询常用路线失败";
    public static final String GET_TRIP_FAIL = "未获取到行程信息";

    public static final String  PHONENUMBER_OR_VERIFICATIONCODE_ERROR = "手机号或者验证码错误";
    public static final String CANNOT_DELETE_GOODSSTYPE = "该类型不能删除";

    public static final String PHONE_ALREADY_EXIST = "此号码已注册";
    public static final String PHONE_NOT_EXIST = "此号码未注册";
    public static final String LOST_CURRENT_TRIP = "未查到当前行程";
    public static final String CANNOT_CREATE_TRIP = "您已发布行程，不能重复发布";
    public static final String LOST_USER = "未查到此用户";
    public static final String LOST_USER_DETAILS = "未查到此用户详细信息";
    public static final String TRIP_OVERDUE = "行程已失效";

    public static final String MSG_RESERVE_ERROR = "该商品库存为0";
    public static final String CANNOT_FIND_GOODS = "未找到商品";
    public static final String LOST_GOODS_TYPE = "未找该商品类型";
    public static final String ILLEGALLY_PARKED = "违停";

    public static final String ONLY_ADMIN = "只有管理员才能访问";

    public static final String NULL_SHOPPING_CART = "购物车为空";
    public static final String NULL_IMAGE = "不存在该照片";
    public static final String NO_EXIST_CUSTOMER = "购物车中不存在该用户";

    public static final String LOST_ORG = "未查到此组织机构";

    public static String exceptionInfo(Exception e){
        if (e.getMessage() != null)
            return SERVER_INNER_ERROR + ":" + e.getMessage();
        else
            return SERVER_INNER_ERROR + ":" + e.toString();
    }

}
