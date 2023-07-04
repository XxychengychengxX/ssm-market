/**
 * @author Valar Morghulis
 * @Date 2023/7/3
 */
package com.project.ssmproject2.system.model;

import lombok.Data;

@Data
public class ProviderAddModel {
    /**
     * 地址
     */
    private String proAddress;
    /**
     * 供应商编码
     */
    private String proCode;
    /**
     * 供应商联系人
     */
    private String proContact;
    /**
     * 供应商详细描述
     */
    private String proDesc;
    /**
     * 微信
     */
    private String proFax;
    /**
     * 供应商名称
     */
    private String proName;
    /**
     * 联系电话
     */
    private String proPhone;
}

