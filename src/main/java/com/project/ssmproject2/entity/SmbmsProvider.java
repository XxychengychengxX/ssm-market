package com.project.ssmproject2.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ychengycheng
 * @since 2023-07-01
 */
@TableName("smbms_provider")
public class SmbmsProvider implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 供应商编码
     */
    @TableField("proCode")
    private String proCode;

    /**
     * 供应商名称
     */
    @TableField("proName")
    private String proName;

    /**
     * 供应商详细描述
     */
    @TableField("proDesc")
    private String proDesc;

    /**
     * 供应商联系人
     */
    @TableField("proContact")
    private String proContact;

    /**
     * 联系电话
     */
    @TableField("proPhone")
    private String proPhone;

    /**
     * 地址
     */
    @TableField("proAddress")
    private String proAddress;

    /**
     * 微信
     */
    @TableField("proFax")
    private String proFax;

    /**
     * 创建者（userId）
     */
    @TableField("createdBy")
    private Long createdBy;

    /**
     * 创建时间
     */
    @TableField("creationDate")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    /**
     * 更新时间
     */
    @TableField("modifyDate")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyDate;

    /**
     * 更新者（userId）
     */
    @TableField("modifyBy")
    private Long modifyBy;

    /**
     * 营业执照
     */
    @TableField("companyLicPicPath")
    private String companyLicPicPath;

    /**
     * 组织机构代码证
     */
    @TableField("orgCodePicPath")
    private String orgCodePicPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }
    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }
    public String getProContact() {
        return proContact;
    }

    public void setProContact(String proContact) {
        this.proContact = proContact;
    }
    public String getProPhone() {
        return proPhone;
    }

    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }
    public String getProAddress() {
        return proAddress;
    }

    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
    }
    public String getProFax() {
        return proFax;
    }

    public void setProFax(String proFax) {
        this.proFax = proFax;
    }
    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }
    public String getCompanyLicPicPath() {
        return companyLicPicPath;
    }

    public void setCompanyLicPicPath(String companyLicPicPath) {
        this.companyLicPicPath = companyLicPicPath;
    }
    public String getOrgCodePicPath() {
        return orgCodePicPath;
    }

    public void setOrgCodePicPath(String orgCodePicPath) {
        this.orgCodePicPath = orgCodePicPath;
    }

    @Override
    public String toString() {
        return "SmbmsProvider{" +
            "id=" + id +
            ", proCode=" + proCode +
            ", proName=" + proName +
            ", proDesc=" + proDesc +
            ", proContact=" + proContact +
            ", proPhone=" + proPhone +
            ", proAddress=" + proAddress +
            ", proFax=" + proFax +
            ", createdBy=" + createdBy +
            ", creationDate=" + creationDate +
            ", modifyDate=" + modifyDate +
            ", modifyBy=" + modifyBy +
            ", companyLicPicPath=" + companyLicPicPath +
            ", orgCodePicPath=" + orgCodePicPath +
        "}";
    }
}
