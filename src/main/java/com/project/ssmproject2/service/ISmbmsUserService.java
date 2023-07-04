package com.project.ssmproject2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.ssmproject2.entity.SmbmsUser;
import com.project.ssmproject2.system.model.LoginModel;
import com.project.ssmproject2.system.model.RegisterModel;
import com.project.ssmproject2.system.response.NormalSelectResponse;
import com.project.ssmproject2.system.response.NormalUpdateResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ychengycheng
 * @since 2023-07-01
 */
public interface ISmbmsUserService extends IService<SmbmsUser> {
    /**
     * 用户登录服务方法
     *
     * @param smbmsUser 用entity封装的提交信息
     * @return 成功返回200, 反之返回500
     */
    NormalUpdateResponse userLogin(LoginModel smbmsUser);

    /**
     * 用户注册服务方法
     *
     * @param registerLoginModel 用entity封装的提交信息
     * @return 成功返回200, 反之返回500
     */
    NormalUpdateResponse userRegister(RegisterModel registerLoginModel);

    /**
     * 管理员添加用户的服务方法
     *
     * @param registerModel 封装的注册请求
     * @param authorization 令牌token
     * @return NormalResponse中的NormalUpdateResponse的JSON字符串
     */
    NormalUpdateResponse addUser(RegisterModel registerModel, String authorization);

    /**
     * 管理员更新用户的服务方法
     *
     * @param smbmsUser     此次更新的对象
     * @param authorization 令牌token
     * @return NormalResponse中的NormalUpdateResponse的JSON字符串
     */
    NormalUpdateResponse updateUser(SmbmsUser smbmsUser, String authorization);

    /**
     * 管理员删除用户的服务方法
     *
     * @param id            要删除的用户id
     * @param authorization 令牌token
     * @return NormalResponse中的NormalUpdateResponse的JSON字符串
     */
    NormalUpdateResponse deleteUser(Long id, String authorization);

    /**
     * 管理员查看某个用户的具体
     *
     * @param id            用户id
     * @param authorization 令牌
     * @return NormalResponse中的NormalSelectResponse字符串
     */
    NormalSelectResponse selectUser(Long id, String authorization);

    /**
     * 用户修改密码
     *
     * @param authorization 用户令牌
     * @param userPassword  用户新密码
     * @return NormalResponse中的NormalUpdateResponse字符串
     */
    NormalUpdateResponse updatePassword(String authorization, String userPassword);

    /**
     * 按页来返回当前页的user
     * @param page 所查询的页
     * @param authorization 令牌token
     * @return NormalResponse中的NormalSelectResponse封装成的对象
     */
    NormalSelectResponse selectUserInPage(Integer page, String authorization);

    /**
     * 获得总页数，一页五个
     * @return 返回有多少页数的总量
     */
    NormalUpdateResponse gerUserPageCount();

}
