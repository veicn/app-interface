package com.maoding.financial.dto;

import com.maoding.core.base.dto.BaseDTO;

import java.math.BigDecimal;

/**
 * 深圳市设计同道技术有限公司
 * 类    名 : ExpFixedEntity
 * 描    述 : 固定支出DTO
 * 作    者 : LY
 * 日    期 : 2016/8/4 10:59
 */
public class ExpFixedDTO extends BaseDTO{


    /**
     * 支出类别
     */
    private String expType;

    /**
     * 账期
     */
    private String expDate;

    /**
     * 支出金额
     */
    private BigDecimal expAmount;

    /**
     * 录入人id
     */
    private String userId;

    /**
     * 录入人姓名
     */
    private String userName;

    /**
     * 所属公司
     */
    private String companyId;

    /**
     * 所属部门
     */
    private String departId;

    /**
     * 备注
     */
    private String remark;


    public String getExpType() {
        return expType;
    }

    public void setExpType(String expType) {
        this.expType = expType == null ? null : expType.trim();
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate == null ? null : expDate.trim();
    }

    public BigDecimal getExpAmount() {
        return expAmount;
    }

    public void setExpAmount(BigDecimal expAmount) {
        this.expAmount = expAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId == null ? null : departId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

}