package com.project.ssmproject2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.ssmproject2.entity.SmbmsBill;
import com.project.ssmproject2.mapper.SmbmsBillMapper;
import com.project.ssmproject2.service.ISmbmsBillService;
import com.project.ssmproject2.system.response.NormalUpdateResponse;
import com.project.ssmproject2.system.util.MyToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

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
public class SmbmsBillServiceImpl extends ServiceImpl<SmbmsBillMapper, SmbmsBill> implements ISmbmsBillService {

    /**
     * 管理员删除供应商的服务方法
     *
     * @param authorization 身份令牌
     * @param id            要删除的供应商的id
     * @return 成功返回200对应的公共相应，反之返回出错原因
     */
    @Override
    public NormalUpdateResponse billDelete(String authorization, Long id) {
        Long userID = MyToken.parseJWTGetUserID(authorization);
        int userRole = MyToken.parseJWTGetUserRole(authorization);
        if (userRole == 1) {
            boolean b = removeById(id);
            if (b) {
                log.warn("用户id为 " + userID + " 的管理员  删除  了供应商id为 " + id + " 的订单");
                return NormalUpdateResponse.generate(b, 0, "删除成功");
            }
            return NormalUpdateResponse.generate(true, 2, "删除失败,记录不存在");

        }
        return NormalUpdateResponse.generate(true, 1, "权限不足");
    }


    /**
     * 管理员更新订单的服务方法
     *
     * @param authorization 身份令牌
     * @param bill          用于封装请求体的对象
     * @return 成功返回200对应的公共相应，反之返回出错原因
     */
    @Override
    public NormalUpdateResponse billUpdate(String authorization, SmbmsBill bill) {
        Long userID = MyToken.parseJWTGetUserID(authorization);
        int userRole = MyToken.parseJWTGetUserRole(authorization);
        if (userRole == 1) {
            boolean b = updateById(bill);
            if (b) {
                log.warn("用户id为 " + userID + " 的管理员  更新  了供应商id为 " + bill.getId() + " 的供应商");
                return NormalUpdateResponse.generate(b, 0, "更新成功");

            }else {
                //query不用wrapper().query生成的话，就是这么链式使用的，
                SmbmsBill smbmsBill = query().eq("id", bill.getId()).one();
                return NormalUpdateResponse.generate(smbmsBill == null, 2, "订单id不存在,更新失败");
            }
        } else {
            return NormalUpdateResponse.generate(true, 1, "权限不足");
        }
    }

    /**
     * 管理员添加供应商
     *
     * @param authorization 身份令牌
     * @param bill          用于封装请求体的对象
     * @return 成功返回200对应的公共相应，反之返回出错原因
     */
    @Override
    public NormalUpdateResponse billAdd(String authorization, SmbmsBill bill) {
        Long userID = MyToken.parseJWTGetUserID(authorization);
        int userRole = MyToken.parseJWTGetUserRole(authorization);
        //获取用户名,用户密码,用户中文名,手机号码,居住地址等等;
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        //获取当前的订单序列名
        if (bill.getBillCode()==null)
            return NormalUpdateResponse.generate(true, 2, "所需订单名为空,添加失败");
        String yearBuilder = "BILL" + year + "_" + bill.getBillCode();
        //query不用wrapper().query生成的话，就是这么链式使用的，
        SmbmsBill smbmsBill = query().eq("billCode", yearBuilder).one();
        //而不是以下这么使用z
        /*6. this.lambdaUpdate()方法和this.lambdaQuery()方法是做什么的?
        这两个方法的返回值是LambdaUpdateChainWrapper和LambdaQueryChainWrapper类型(都有Chain字符串)

        再结合 [指南->核心功能->CRUD接口]里面的[Chain]小节,可以推断他们是用来链式查询并修改和删除的(以下是原文):

            ————————————————
            版权声明：本文为CSDN博主「you_get_me_there」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
            原文链接：https://blog.csdn.net/you_get_me_there/article/details/107781362*/
        /*QueryChainWrapper<SmbmsBill> query = query();
        query.eq("billCode",yearBuilder);
        getOne(query);*/
        if (smbmsBill != null)
            return NormalUpdateResponse.generate(true, 2, "订单序列已存在,添加失败");
        if (userRole == 1) {
            //设置一系列的属性
            bill.setBillCode(yearBuilder);
            bill.setCreatedBy(userID);
            bill.setModifyBy(userID);
            boolean save = this.save(bill);
            if (save)
                log.warn("用户id为 " + userID + " 的管理员  添加  了新的订单 : " + bill);
            return NormalUpdateResponse.generate(save, 0, "添加成功");
        }
        return NormalUpdateResponse.generate(true, 1, "权限不足");

    }
}
