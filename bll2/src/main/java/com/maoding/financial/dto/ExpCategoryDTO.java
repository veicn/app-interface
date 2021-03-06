package com.maoding.financial.dto;

import com.maoding.core.base.dto.BaseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 深圳市设计同道技术有限公司
 * 类    名 : ExpCategoryDTO
 * 描    述 : 报销类别
 * 作    者 : MaoSF
 * 日    期 : 2016/10/09 15:14
 */
public class ExpCategoryDTO extends BaseDTO{

    private String name;

    private String code;

    private String pid;

    private String rootId;

    private String companyId;

    private String status;

    private int seq;

    private String expTypeMemo;

    /**
     * 多条记录
     */
    private List<ExpCategoryDTO> expCategoryList = new ArrayList<ExpCategoryDTO>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId == null ? null : rootId.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public List<ExpCategoryDTO> getExpCategoryList() {
        return expCategoryList;
    }

    public void setExpCategoryList(List<ExpCategoryDTO> expCategoryList) {
        this.expCategoryList = expCategoryList;
    }

    public String getExpTypeMemo() {
        return expTypeMemo;
    }

    public void setExpTypeMemo(String expTypeMemo) {
        this.expTypeMemo = expTypeMemo;
    }
}