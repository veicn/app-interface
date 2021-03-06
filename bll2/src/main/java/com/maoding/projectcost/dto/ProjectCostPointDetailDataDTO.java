package com.maoding.projectcost.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 深圳市设计同道技术有限公司
 * 项目费用详情
 * 类    名：ProjectCostDetailDTO
 * 类描述：费用收付款明显详情记录（支付方金额，收款方金额，发票信息等）
 * 作    者：ChenZJ
 * 日    期：2016年7月19日-下午4:11:50
 */
public class ProjectCostPointDetailDataDTO {

    private String id;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 费用节点id
     */
    private String pointId;

    /**
     * 金额
     */
    private BigDecimal fee;

    /**
     * 到款金额，已收金额
     */
    private BigDecimal paidFee;

    /**
     * 到款金额，已付金额
     */
    private BigDecimal payFee;

    /**
     * 发票信息
     */
    private String invoice;


    private String type;

    /**
     * 应收未收
     */
    private BigDecimal notReceiveFee;

    /**
     * 应付未付
     */
    private BigDecimal notPayFee;

    /**
     * 对应的收款总额
     */
    private BigDecimal paymentFee;

    /**
     * 是否可以删除（0：可删除，1：不可删除）
     */
    private int deleteFlag;

    /**
     * 操作人
     */
    private String userName;

    /**
     * 操作人
     */
    private String userName2;

    /**
     * 操作人
     */
    private String userName3;

    private String field1;

    private int level = 1; //给app端区分父子节点的type值

    /**
     * 权限map:
     * 4.付款（技术审查费-确认付款款（经营负责人）），
     * 5.付款（技术审查费-确认付款款（企业负责人）），
     * 6.付款（合作设计费-付款确认（经营负责人）），
     * 7.付款（合作设计费-付款确认（企业负责人）），
     * 8.到款（技术审查费-确认到款），
     * 9.到款（合作设计费-到款确认）
     * 10.到款（合同回款-到款确认）
     * 根据map中的key中对应界面每个格子的权限操作,value对应任务的id。
    */
    private Map<String,Object> roleMap = new HashMap<String,Object>();

    /**
     * 节点明细
     */
    private List<ProjectCostPaymentDetailDataDTO> paymentList= new ArrayList<ProjectCostPaymentDetailDataDTO>();

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(BigDecimal paidFee) {
        this.paidFee = paidFee;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getNotReciveFee() {
        if(fee!=null && paidFee!=null){
            notReceiveFee = fee.subtract(paidFee);
        }else {
            notReceiveFee = fee;
        }
        return notReceiveFee;
    }

    public void setNotReceiveFee(BigDecimal notReceiveFee) {
        this.notReceiveFee = notReceiveFee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName2() {
        return userName2;
    }

    public void setUserName2(String userName2) {
        this.userName2 = userName2;
    }

    public String getUserName3() {
        return userName3;
    }

    public void setUserName3(String userName3) {
        this.userName3 = userName3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public Map<String, Object> getRoleMap() {
        return roleMap;
    }

    public void setRoleMap(Map<String, Object> roleMap) {
        this.roleMap = roleMap;
    }

    public List<ProjectCostPaymentDetailDataDTO> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<ProjectCostPaymentDetailDataDTO> paymentList) {
        this.paymentList = paymentList;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    public BigDecimal getNotReceiveFee() {
        if(fee!=null && paidFee!=null){
            notReceiveFee = fee.subtract(paidFee);
        }else {
            notReceiveFee = fee;
        }
        return notReceiveFee;
    }



    public BigDecimal getNotPayFee() {
        if(fee!=null && payFee!=null){
            notPayFee = fee.subtract(payFee);
        }else {
            notPayFee = fee;
        }
        return notPayFee;
    }

    public void setNotPayFee(BigDecimal notPayFee) {
        this.notPayFee = notPayFee;
    }

    public BigDecimal getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(BigDecimal paymentFee) {
        this.paymentFee = paymentFee;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
