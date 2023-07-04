/**
 * @author Valar Morghulis
 * @Date 2023/7/3
 */
package com.project.ssmproject2.system.util;

import com.project.ssmproject2.entity.SmbmsBill;
import com.project.ssmproject2.entity.SmbmsProvider;
import com.project.ssmproject2.entity.SmbmsUser;
import com.project.ssmproject2.system.model.ProviderAddModel;
import com.project.ssmproject2.system.model.RegisterModel;

public class MySetAttribute {
    /**
     * 设置登录请求或者管理员添加用户请求的封装方法
     *
     * @param smbmsUser     即将返回的用户实例(用于MP的更新或者添加操作)
     * @param registerModel 从前端接受到的请求model封装实例
     */
    public static void setLoginAttribute(SmbmsUser smbmsUser, RegisterModel registerModel) {
        String userCode = registerModel.getUserCode();
        String inputPassword = registerModel.getUserPassword();
        String userName = registerModel.getUserName();
        String phone = registerModel.getPhone();
        String address = registerModel.getAddress();
        int userRole1 = 3;
        if (smbmsUser.getUserRole() != null)
            userRole1 = registerModel.getUserRole();
        if (smbmsUser.getGender() != null)
            registerModel.setGender(smbmsUser.getGender());
        smbmsUser.setUserCode(userCode);
        smbmsUser.setUserPassword(inputPassword);
        smbmsUser.setUserName(userName);
        smbmsUser.setAddress(address);
        smbmsUser.setPhone(phone);
        smbmsUser.setUserRole(userRole1);
    }

    /**
     * 添加供应商或者改变供应商请求或者管理员添加供应商请求的封装方法
     *
     * @param smbmsProvider    即将返回的供应商实例(用于MP的更新或者添加操作)
     * @param providerAddModel 从前端接受到的请求model封装实例
     */
    public static void setProviderAttribute(SmbmsProvider smbmsProvider,
                                            ProviderAddModel providerAddModel) {
        String proAddress = providerAddModel.getProAddress();
        String proContact = providerAddModel.getProContact();
        String proDesc = providerAddModel.getProDesc();
        String proFax = providerAddModel.getProFax();
        String proName = providerAddModel.getProName();
        String proCode = providerAddModel.getProCode();
        String proPhone = providerAddModel.getProPhone();

        smbmsProvider.setProAddress(proAddress);
        smbmsProvider.setProCode(proCode);
        smbmsProvider.setProContact(proContact);
        smbmsProvider.setProDesc(proDesc);
        smbmsProvider.setProFax(proFax);
        smbmsProvider.setProName(proName);
        smbmsProvider.setProPhone(proPhone);

    }

    /**
     * 添加订单或者改变订单请求的封装方法
     *
     * @param updateBill 即将返回的供应商实例(用于MP的更新或者添加操作)
     * @param acceptBill 从前端接受到的请求model封装实例
     */
    public static void setBillAttribute(SmbmsBill updateBill, SmbmsBill acceptBill) {

    }
}
