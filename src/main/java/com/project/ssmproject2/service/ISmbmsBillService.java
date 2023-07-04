package com.project.ssmproject2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.ssmproject2.entity.SmbmsBill;
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
public interface ISmbmsBillService extends IService<SmbmsBill> {
    /**
     * 获得总页数，一页五个
     *
     * @return 返回有多少页数的总量
     */
    NormalUpdateResponse getBillPageCount();

    /**
     * 按页来返回当前页的user
     * @param page 所查询的页
     * @param authorization 令牌token
     * @return NormalResponse中的NormalSelectResponse封装成的对象
     */
    NormalSelectResponse selectBillInPage(Integer page, String authorization);

    /**
     * 管理员删除供应商的服务方法
     *
     * @param authorization 身份令牌
     * @param id            要删除的供应商的id
     * @return 成功返回200对应的公共相应，反之返回出错原因
     */
    NormalUpdateResponse billDelete(String authorization, Long id);

    /**
     * 管理员更新订单的服务方法
     *
     * @param authorization 身份令牌
     * @param bill          用于封装请求体的对象
     * @return 成功返回200对应的公共相应，反之返回出错原因
     */
    NormalUpdateResponse billUpdate(String authorization, SmbmsBill bill);

    /**
     * 管理员添加供应商
     *
     * @param authorization 身份令牌
     * @param bill          用于封装请求体的对象
     * @return 成功返回200对应的公共相应，反之返回出错原因
     */
    NormalUpdateResponse billAdd(String authorization, SmbmsBill bill);
}
