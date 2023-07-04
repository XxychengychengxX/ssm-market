package com.project.ssmproject2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.ssmproject2.entity.SmbmsProvider;
import com.project.ssmproject2.system.model.ProviderAddModel;
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
public interface ISmbmsProviderService extends IService<SmbmsProvider> {

    /**
     * 获得总页数，一页五个
     *
     * @return 返回有多少页数的总量
     */
    NormalUpdateResponse gerProviderPageCount();
    /**
     * 按页来返回当前页的user
     * @param page 所查询的页
     * @param authorization 令牌token
     * @return NormalResponse中的NormalSelectResponse封装成的对象
     */
    NormalSelectResponse selectProviderInPage(Integer page, String authorization);
    /**
     * 管理创建供应商对象的服务方法
     *
     * @param authorization 用户令牌
     * @param provider      用于封装请求的provider对象
     * @return 对应响应的JSON字符串用于controller直接返回
     */
    NormalUpdateResponse providerAdd(String authorization, ProviderAddModel provider);

    /**
     * 管理创建供应商对象的服务方法
     *
     * @param authorization 用户令牌
     * @param provider      用于封装请求的provider对象
     * @return 对应响应的JSON字符串用于controller直接返回
     */
    NormalUpdateResponse providerUpdate(String authorization, SmbmsProvider provider);

    /**
     * 管理创建供应商对象的服务方法
     *
     * @param authorization 用户令牌
     * @param id            要删除的供应商id
     * @return 对应响应的JSON字符串用于controller直接返回
     */
    NormalUpdateResponse providerDelete(String authorization, Long id);
}
