package com.project.ssmproject2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.ssmproject2.entity.SmbmsUser;
import com.project.ssmproject2.mapper.SmbmsUserMapper;
import com.project.ssmproject2.service.ISmbmsUserService;
import com.project.ssmproject2.system.model.LoginModel;
import com.project.ssmproject2.system.model.RegisterModel;
import com.project.ssmproject2.system.response.NormalSelectResponse;
import com.project.ssmproject2.system.response.NormalUpdateResponse;
import com.project.ssmproject2.system.util.MySetAttribute;
import com.project.ssmproject2.system.util.MyToken;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ychengycheng
 * @since 2023-07-01
 */
@Service
@Slf4j
@Transactional
public class SmbmsUserServiceImpl extends ServiceImpl<SmbmsUserMapper, SmbmsUser> implements ISmbmsUserService {

    @Resource
    SmbmsUserMapper smbmsUserMapper;

    /**
     * 用户登录服务方法
     *
     * @param loginModel 用entity封装的提交信息
     * @return 成功返回200, 反之返回500
     */
    @Override
    public NormalUpdateResponse userLogin(LoginModel loginModel) {
        String userCode = loginModel.getUserCode();
        String inputPassword = loginModel.getUserPassword();
        QueryWrapper<SmbmsUser> query = Wrappers.query();
        query.eq("userCode", userCode);
        SmbmsUser user = null;
        //进行各种情况的判定
        try {
            user = this.getOne(query, true);
            if (user == null)
                return NormalUpdateResponse.generate(true, 2, "用户名不存在");
            else {
                if (user.getUserPassword().equals(inputPassword)) {
                    Long id = user.getId();
                    String userName = user.getUserName();
                    Integer userRole = user.getUserRole();
                    String jwt = MyToken.createJWT(id, userName, userRole);
                    return NormalUpdateResponse.generate(true, 0,
                            "Bearer= " + jwt);
                } else
                    return NormalUpdateResponse.generate(true, 2, "密码不正确");
            }
        } catch (Exception e) {
            return NormalUpdateResponse.generate(false, 1000, "");
        }

    }

    /**
     * 用户注册服务方法
     *
     * @param registerModel 用entity封装的提交信息
     * @return 成功返回200, 反之返回500
     */
    @Override
    public NormalUpdateResponse userRegister(RegisterModel registerModel) {
        //获取用户名,用户密码,用户中文名,手机号码,居住地址等等
        String userCode = registerModel.getUserCode();
        QueryWrapper<SmbmsUser> query = Wrappers.query();
        query.eq("userCode", userCode);
        SmbmsUser user;
        //进行各种情况的判定
        try {
            user = this.getOne(query, true);
            if (user == null) {
                SmbmsUser smbmsUser = new SmbmsUser();
                MySetAttribute.setLoginAttribute(smbmsUser, registerModel);
                boolean save = this.save(smbmsUser);
                if (save)
                    return NormalUpdateResponse.generate(true, 0, "注册成功");
                else
                    throw new RuntimeException("服务器出错");
            } else {
                return NormalUpdateResponse.generate(true, 2, "用户名已存在");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return NormalUpdateResponse.generate(false, 0, "");
        }
    }

    /**
     * 管理员添加用户的服务方法
     *
     * @param registerModel 封装的注册对象
     * @param authorization 令牌token
     * @return NormalResponse中的NormalUpdateResponse的JSON字符串
     */
    @Override
    public NormalUpdateResponse addUser(RegisterModel registerModel, String authorization) {
        Long userID = MyToken.parseJWTGetUserID(authorization);
        int userRole = MyToken.parseJWTGetUserRole(authorization);
        //获取用户名,用户密码,用户中文名,手机号码,居住地址等等
        String userCode = registerModel.getUserCode();
        QueryWrapper<SmbmsUser> query = Wrappers.query();
        query.eq("userCode", userCode);
        SmbmsUser user;
        if (userRole == 1) {
            user = this.getOne(query, true);
            if (user == null) {
                //设置一系列属性
                SmbmsUser smbmsUser = new SmbmsUser();
                MySetAttribute.setLoginAttribute(smbmsUser, registerModel);
                smbmsUser.setCreatedBy(userID);
                smbmsUser.setModifyBy(userID);
                return NormalUpdateResponse.generate(this.save(smbmsUser), 0, "添加成功");


            } else {
                return NormalUpdateResponse.generate(true, 2, "用户名已存在,添加失败");
            }

        } else
            return NormalUpdateResponse.generate(true, 1,
                    "权限不足");
    }

    /**
     * 管理员更新用户的服务方法
     *
     * @param smbmsUser     此次更新的对象
     * @param authorization 令牌token
     * @return NormalResponse中的NormalUpdateResponse的JSON字符串
     */
    @Override
    public NormalUpdateResponse updateUser(SmbmsUser smbmsUser,
                                           String authorization) {
        if (MyToken.parseJWTGetUserRole(authorization) == 1) {
            Long userID = MyToken.parseJWTGetUserID(authorization);
            smbmsUser.setModifyBy(userID);
            return NormalUpdateResponse.generate(this.updateById(smbmsUser), 0,
                    "更新成功");
        }
        return NormalUpdateResponse.generate(true, 1,
                "权限不足");
    }

    /**
     * 管理员删除用户的服务方法
     *
     * @param id            要删除的用户id
     * @param authorization 令牌token
     * @return NormalResponse中的NormalUpdateResponse的JSON字符串
     */
    @Override
    public NormalUpdateResponse deleteUser(Long id, String authorization) {
        if (MyToken.parseJWTGetUserRole(authorization) == 1) {
            QueryWrapper<SmbmsUser> remove = Wrappers.query();
            remove.eq("id", id);
            boolean remove1 = this.remove(remove);
            if (remove1)
                return NormalUpdateResponse.generate(true, 0,
                        "删除成功");
            else
                return NormalUpdateResponse.generate(true, 2,
                        "记录不存在,删除失败");
        }
        return NormalUpdateResponse.generate(true, 1,
                "权限不足");
    }

    /**
     * 按页来返回当前页的user
     *
     * @param page          所查询的页
     * @param authorization 令牌token
     * @return NormalResponse中的NormalSelectResponse封装成的对象
     */
    @Override
    public NormalSelectResponse selectUserInPage(Integer page, String authorization) {
        Page<SmbmsUser> smbmsUserPage = new Page<>(page, 5);
        page(smbmsUserPage, null);
        return NormalSelectResponse.generate(true, 0, "查询成功", smbmsUserPage);
    }

    /**
     * 获得总页数，一页五个
     *
     * @return 返回有多少页数的总量
     */
    @Override
    public NormalUpdateResponse gerUserPageCount() {
        long count = (long) Math.ceil((double) count() / 5.0);
        return NormalUpdateResponse.generate(true, 0, String.valueOf(count));
    }

    /**
     * 管理员查看某个用户的具体
     *
     * @param id            用户id
     * @param authorization 令牌
     * @return NormalResponse中的NormalSelectResponse字符串
     */
    @Override
    public NormalSelectResponse selectUser(Long id, String authorization) {

        if (MyToken.parseJWTGetUserRole(authorization) == 1) {
            SmbmsUser smbmsUser = this.getById(String.valueOf(id));
            if (smbmsUser == null)
                return NormalSelectResponse.generate(true, 2,
                        "未找到相关信息,查询失败", "");
            else
                return NormalSelectResponse.generate(true, 0,
                        "查询成功", smbmsUser);
        }
        return NormalSelectResponse.generate(true, 1,
                "权限不足", "");
    }

    /**
     * 更新密码
     *
     * @param authorization 用户令牌
     * @param userPassword  用户新密码
     * @return NormalResponse中的NormalUpdateResponse字符串
     */
    @Override
    public NormalUpdateResponse updatePassword(String authorization, String userPassword) {
        Long userID = null;
        int userRole = 0;
        try {
            userID = MyToken.parseJWTGetUserID(authorization);
            userRole = MyToken.parseJWTGetUserRole(authorization);
        } catch (Exception e) {
            log.error(e.getMessage());
            return NormalUpdateResponse.generate(true, 3, "身份已过期");
        }
        UpdateWrapper<SmbmsUser> update = Wrappers.update();
        update.eq("id", userID);
        update.set("userPassword", userPassword);
        return NormalUpdateResponse.generate(this.update(update), 0, "密码更新成功");
    }
}
