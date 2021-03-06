package com.maoding.financial.dto;

import com.maoding.core.base.dto.BaseDTO;

import java.util.Date;

/**
 * 深圳市设计同道技术有限公司
 * 类    名 : ExpAuditEntity
 * 描    述 : 报销审核DTO
 * 作    者 : LY
 * 日    期 : 2016/7/26 15:14
 */
public class ExpAuditDTO extends BaseDTO {

    /**
     * 原审核id
     */
    private String parentId;

    /**
     * 是否最新审核 Y是 N否
     */
    private String isNew;

    /**
     * 报销主单id
     */
    private String mainId;

    /**
     * 审批状态(0:待审核，1:同意，2，退回,3:撤回,4:删除）
     */
    private String approveStatus;

    /**
     * 审批日期
     */
    private Date approveDate;

    /**
     * 审核人id
     */
    private String auditPerson;

    /**
     * 审核人姓名
     */
    private String auditPersonName;

    /**
     * 审批意见
     */
    private String auditMessage;

    /**
     * 当前操作人（团队成员id）
     */
    private String companyUserId;


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew == null ? null : isNew.trim();
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId == null ? null : mainId.trim();
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus == null ? null : approveStatus.trim();
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public String getAuditPerson() {
        return auditPerson;
    }

    public void setAuditPerson(String auditPerson) {
        this.auditPerson = auditPerson == null ? null : auditPerson.trim();
    }

    public String getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage == null ? null : auditMessage.trim();
    }

    public String getAuditPersonName() {
        return auditPersonName;
    }

    public void setAuditPersonName(String auditPersonName) {
        this.auditPersonName = auditPersonName;
    }

    public String getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(String companyUserId) {
        this.companyUserId = companyUserId;
    }
}