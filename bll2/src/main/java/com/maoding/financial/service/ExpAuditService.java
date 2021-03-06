package com.maoding.financial.service;

import com.maoding.core.base.service.BaseService;
import com.maoding.financial.dto.SaveExpMainDTO;
import com.maoding.financial.entity.ExpAuditEntity;

import java.util.List;
import java.util.Map;

/**
 * 深圳市设计同道技术有限公司
 * 类    名 : ExpAuditService
 * 描    述 : 报销审核Service
 * 作    者 : LY
 * 日    期 : 2016/7/26-15:56
 */
public interface ExpAuditService extends BaseService<ExpAuditEntity>{

    String getAuditPerson(String mainId,String accountId);

    /**
     * 保存审批记录
     */
    ExpAuditEntity saveExpAudit(String mainId,String auditPerson,String submitAuditId,String accountId,String parentId) throws Exception;


    int completeAudit(SaveExpMainDTO dto) throws Exception;

    /**
     * 用于保存流程中同意后，给下一个人推送审批任务
     */
    String saveAudit(SaveExpMainDTO dto) throws Exception;
}
