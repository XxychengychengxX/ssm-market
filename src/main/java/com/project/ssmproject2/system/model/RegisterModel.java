/**
 * @author Valar Morghulis
 * @Date 2023/7/3
 */
package com.project.ssmproject2.system.model;

import lombok.Data;

@Data
public class RegisterModel {
    //登录账号
    private String userCode;
    //用户名
    private String userName;
    //用户密码
    private String userPassword;
    //手机号
    private String phone;
    //工作地址
    private String address;

    //用户身份(用于管理员手动添加客户）
    private int UserRole;

    //性别
    private int gender;
}
