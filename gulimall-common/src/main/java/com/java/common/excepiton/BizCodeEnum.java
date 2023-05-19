package com.java.common.excepiton;
/*
        错误码和错误信息定义类
        1 错误码定义规则为5位数字
        2 前两位表示业务场景，最后三位表示借误码。例如:100001; 10:通用 001:系统未知异常
        3，维护错误码后需要维护错误描述，将他们定义为枚举形式
        错误码列表:
        通用:10
          001: 参数格式校验
        商品11:
        订单12:
        购物车13:
        物流14;
 */
public enum BizCodeEnum {

    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    VAILE_EXCEPTION(10001,"参数格式校验失败");

    private int code;
    private String msg;

    BizCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
