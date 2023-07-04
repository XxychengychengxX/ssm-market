package com.project.ssmproject2.controller;

import com.project.ssmproject2.entity.SmbmsUser;
import com.project.ssmproject2.service.ISmbmsUserService;
import com.project.ssmproject2.system.config.WithAuthorizationHeader;
import com.project.ssmproject2.system.model.LoginModel;
import com.project.ssmproject2.system.model.RegisterModel;
import com.project.ssmproject2.system.response.NormalSelectResponse;
import com.project.ssmproject2.system.response.NormalUpdateResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * user相关前端控制器
 *
 * @author ychengycheng
 * @since 2023-07-01
 */
@RestController
@RequestMapping("/smbms-user")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5173/", "https://localhost:5173"
        ,"http://192.168.0" +
        ".107:5173", "http://192.168.0.107:5173/"}, allowCredentials =
        "true")
//http://localhost:5173
public class SmbmsUserController {

    @Resource
    ISmbmsUserService iSmbmsUserService;


    /*@GetMapping("/getUserPageCount")
    public NormalUpdateResponse getUserPageCount(@RequestHeader("Authorization") String
    authorization) {
        return iSmbmsUserService.gerUserPageCount();
    }*/

    /**
     * 用户查看当前用户表
     *
     * @param page 查询的当前页数
     * @return 查询数组的JSON字符串
     */
    @GetMapping("/usersGet/{page}")
    @CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5173/"}, allowCredentials =
            "true")
    public NormalSelectResponse userSelectInPage(@PathVariable("page") Integer page) {

        return iSmbmsUserService.selectUserInPage(page, null);
    }

    /**
     * 用户登录
     *
     * @param loginModel 用于封装application/json 的请求
     * @return 成功返回200, 反之返回500
     */
    @PostMapping(value = "/login")
    public NormalUpdateResponse userLogin(@RequestBody LoginModel loginModel) {
        return iSmbmsUserService.userLogin(loginModel);
    }

    /**
     * 用户注册
     *
     * @param registerLoginModel 用entity封装的提交信息
     * @return 成功返回200, 反之返回500
     */
    @PostMapping("/regiter")
    public NormalUpdateResponse userRegister(@RequestBody RegisterModel registerLoginModel) {
        return iSmbmsUserService.userRegister(registerLoginModel);
    }

    /**
     * 用户修改密码
     *
     * @param authorization 用户令牌
     * @param userPassword  用户新密码
     * @return NormalResponse中的NormalUpdateResponse字符串
     */
    @PutMapping("/updateUserPassword")
    @WithAuthorizationHeader
    public NormalUpdateResponse userUpdatePassword(@RequestHeader("Authorization") String authorization,
                                                   @RequestParam("userPassword") String userPassword) {
        return iSmbmsUserService.updatePassword(authorization, userPassword);
    }

    /**
     * 管理员查看具体用户
     *
     * @param id            用户id
     * @param authorization 令牌
     * @return NormalResponse中的NormalSelectResponse字符串
     */
    @GetMapping("/userSelect/{Id}")
    @WithAuthorizationHeader
    public NormalSelectResponse userSelectById(@PathVariable("Id") Long id,
                                               @RequestHeader("Authorization") String authorization) {
        return iSmbmsUserService.selectUser(id, authorization);
    }

    /**
     * 管理员添加用户
     *
     * @param registerModel 封装的请求
     * @param authorization 令牌
     * @return NormalResponse中的NormalUpdateResponse
     */
    @PostMapping("/userAdd")
    @WithAuthorizationHeader
    public NormalUpdateResponse userAdd(@RequestBody RegisterModel registerModel,
                                        @RequestHeader("Authorization") String authorization) {
        return iSmbmsUserService.addUser(registerModel, authorization);
    }

    /**
     * 管理员删除用户
     *
     * @param id            要删除的用户id
     * @param authorization 令牌token
     * @return NormalResponse中的NormalUpdateResponse的JSON字符串
     */
    @DeleteMapping("/userDelete/{Id}")
    @WithAuthorizationHeader
    public NormalUpdateResponse userDelete(@PathVariable("Id") Long id,
                                           @RequestHeader("Authorization") String authorization) {
        return iSmbmsUserService.deleteUser(id, authorization);
    }

    /**
     * 管理员更新用户
     *
     * @param smbmsUser     此次更新的对象
     * @param authorization 令牌token
     * @return NormalResponse中的NormalUpdateResponse的JSON字符串
     */
    @PutMapping("/userUpdate")
    @WithAuthorizationHeader
    public NormalUpdateResponse userUpdate(@RequestBody SmbmsUser smbmsUser,
                                           @RequestHeader("Authorization") String authorization) {
        return iSmbmsUserService.updateUser(smbmsUser, authorization);
    }
}
