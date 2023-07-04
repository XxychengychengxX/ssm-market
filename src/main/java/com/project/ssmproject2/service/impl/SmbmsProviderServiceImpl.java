package com.project.ssmproject2.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.ssmproject2.entity.SmbmsProvider;
import com.project.ssmproject2.mapper.SmbmsProviderMapper;
import com.project.ssmproject2.service.ISmbmsProviderService;
import com.project.ssmproject2.system.model.ProviderAddModel;
import com.project.ssmproject2.system.response.NormalSelectResponse;
import com.project.ssmproject2.system.response.NormalUpdateResponse;
import com.project.ssmproject2.system.util.MySetAttribute;
import com.project.ssmproject2.system.util.MyToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 供应商服务实现类
 *
 * @author ychengycheng
 * @since 2023-07-01
 */
@Service
@Transactional

@Slf4j
public class SmbmsProviderServiceImpl extends ServiceImpl<SmbmsProviderMapper, SmbmsProvider> implements ISmbmsProviderService {
    /**
     * 按页来返回当前页的user
     *
     * @param page          所查询的页
     * @param authorization 令牌token
     * @return NormalResponse中的NormalSelectResponse封装成的对象
     */
    @Override
    public NormalSelectResponse selectProviderInPage(Integer page, String authorization) {
        Page<SmbmsProvider> providerPage = new Page<>(page, 5);
        page(providerPage, null);
        return NormalSelectResponse.generate(true, 0, "查询成功", providerPage);

    }

    /**
     * 获得总页数，一页五个
     *
     * @return 返回有多少页数的总量
     */
    @Override
    public NormalUpdateResponse gerProviderPageCount() {
        long count = (long) Math.ceil((double) count() / 5.0);
        return NormalUpdateResponse.generate(true, 0, String.valueOf(count));
    }


    /**
     * 管理创建供应商对象的服务方法
     *
     * @param authorization 用户令牌
     * @param provider      用于封装请求的provider对象
     * @return 对应响应的对象用于controller直接返回
     */
    @Override
    public NormalUpdateResponse providerAdd(String authorization, ProviderAddModel provider) {
        Long userID = MyToken.parseJWTGetUserID(authorization);
        int userRole = MyToken.parseJWTGetUserRole(authorization);
        //获取用户名,用户密码,用户中文名,手机号码,居住地址等等;

        String proName = provider.getProName();
        String proCode = provider.getProCode();
        if (proName == null || proCode == null)
            return NormalUpdateResponse.generate(true, 2, "供应商名字或供应商编号缺失,添加失败");
        LambdaQueryChainWrapper<SmbmsProvider> smbmsProviderLambdaQueryChainWrapper = lambdaQuery();
        SmbmsProvider smbmsProvider =
                smbmsProviderLambdaQueryChainWrapper.eq(SmbmsProvider::getProCode,
                        proCode).or().eq(SmbmsProvider::getProName, proName).one();
        if (userRole == 1) {
            if (smbmsProvider == null) {
                //设置一系列的属性
                smbmsProvider = new SmbmsProvider();
                MySetAttribute.setProviderAttribute(smbmsProvider, provider);
                smbmsProvider.setCreatedBy(userID);
                smbmsProvider.setModifyBy(userID);
                boolean save = this.save(smbmsProvider);
                if (save)
                    log.warn("用户id为 " + userID + " 的管理员  添加  了新的供应商 : " + smbmsProvider);
                return NormalUpdateResponse.generate(save, 0, "添加成功");
            } else
                return NormalUpdateResponse.generate(true, 2, "供应商名称或者供应商编号已重复,添加失败");
        }
        return NormalUpdateResponse.generate(true, 1, "权限不足");
    }

    /**
     * 管理创建供应商对象的服务方法
     *
     * @param authorization 用户令牌
     * @param provider      用于封装请求的provider对象
     * @return 对应响应的对象用于controller直接返回
     */
    @Override
    public NormalUpdateResponse providerUpdate(String authorization, SmbmsProvider provider) {
        Long userID = MyToken.parseJWTGetUserID(authorization);
        int userRole = MyToken.parseJWTGetUserRole(authorization);
        if (userRole == 1) {
            provider.setModifyBy(userID);
            boolean b = updateById(provider);
            if (b) {
                log.warn("用户id为 " + userID + " 的管理员  更新  了供应商id为 " + provider.getId() + " 的供应商");
                return NormalUpdateResponse.generate(b, 0, "更新成功");
            } else
                return NormalUpdateResponse.generate(getById(provider.getId()) == null, 2, "更新失败," +
                        "记录未找到");

        } else {
            return NormalUpdateResponse.generate(true, 1, "权限不足");
        }
    }

    /**
     * 管理创建供应商对象的服务方法
     *
     * @param authorization 用户令牌
     * @param id            要删除的供应商id
     * @return 对应响应的对象用于controller直接返回
     */
    @Override
    public NormalUpdateResponse providerDelete(String authorization, Long id) {
        Long userID = MyToken.parseJWTGetUserID(authorization);
        int userRole = MyToken.parseJWTGetUserRole(authorization);
        if (userRole == 1) {
            boolean b = removeById(id);
            if (b) {
                log.warn("用户id为 " + userID + " 的管理员  删除  了供应商id为 " + id + " 的供应商");
                return NormalUpdateResponse.generate(b, 0, "删除成功");
            }
            return NormalUpdateResponse.generate(getById(id) == null, 2, "删除失败," +
                    "记录未找到");
        }
        return NormalUpdateResponse.generate(true, 1, "权限不足");
    }
}
