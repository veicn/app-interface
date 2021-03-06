package com.maoding.financial.dto;

import com.maoding.core.base.dto.QueryDTO;

public class QueryAuditDTO extends QueryDTO{

    /**我申请的条目数（报销，费用申请，请假，出差）用到的参数**/
    private String month;

    private String companyUserId;

    /*****getAuditData使用 type: (type=1:我提交的，type=2：待审核的,type=3:我已经审核的 type=4:我提交并审批完成的,type=5:我提交的正处于审核中的,type=6:我提交未审核的)******/
    private String type;

    private String auditPerson;

    private String userIds;

    /**（1：报销数据，2：费用申请数据，3：请假数据，4：出差数据，5：项目费用申请数据），如果是查询多个：比如报销+费用申请，传递的参数为 "1,2",用逗号隔开 **/
    private String expTypes;

    private String expType;

    private String mainId;

    private String keyword;

    private String startDate;

    private String endDate;

    /**
     * 抄送人
     */
    private String ccCompanyUserId;

    private String ignoreRecall;//忽略退回的记录

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(String companyUserId) {
        this.companyUserId = companyUserId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuditPerson() {
        return auditPerson;
    }

    public void setAuditPerson(String auditPerson) {
        this.auditPerson = auditPerson;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getExpTypes() {
        return expTypes;
    }

    public void setExpTypes(String expTypes) {
        this.expTypes = expTypes;
    }

    public String getExpType() {
        return expType;
    }

    public void setExpType(String expType) {
        this.expType = expType;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCcCompanyUserId() {
        return ccCompanyUserId;
    }

    public void setCcCompanyUserId(String ccCompanyUserId) {
        this.ccCompanyUserId = ccCompanyUserId;
    }

    public String getIgnoreRecall() {
        return ignoreRecall;
    }

    public void setIgnoreRecall(String ignoreRecall) {
        this.ignoreRecall = ignoreRecall;
    }
}
