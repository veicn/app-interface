package com.maoding.financial.service.impl;

import com.maoding.core.base.dto.BaseDTO;
import com.maoding.core.base.service.GenericService;
import com.maoding.core.constant.SystemParameters;
import com.maoding.core.util.StringUtil;
import com.maoding.financial.dao.ExpAuditDao;
import com.maoding.financial.dao.ExpMainDao;
import com.maoding.financial.dto.AuditDTO;
import com.maoding.financial.dto.SaveExpMainDTO;
import com.maoding.financial.entity.ExpAuditEntity;
import com.maoding.financial.entity.ExpMainEntity;
import com.maoding.financial.service.ExpAuditService;
import com.maoding.financial.service.ExpMainService;
import com.maoding.message.dto.SendMessageDTO;
import com.maoding.message.service.MessageService;
import com.maoding.org.dao.CompanyUserDao;
import com.maoding.org.entity.CompanyUserEntity;
import com.maoding.process.dto.TaskDTO;
import com.maoding.process.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 深圳市设计同道技术有限公司
 * 类    名 : ExpAuditServiceImpl
 * 描    述 : 报销审核Serviceimpl
 * 作    者 : LY
 * 日    期 : 2016/7/26-16:03
 */

@Service("expAuditService")
public class ExpAuditServiceImpl extends GenericService<ExpAuditEntity> implements ExpAuditService {

    @Autowired
    private ExpAuditDao expAuditDao;

    @Autowired
    private ExpMainDao expMainDao;

    @Autowired
    private CompanyUserDao companyUserDao;

    @Autowired
    private ExpMainService expMainService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ProcessService processService;


    @Override
    public String getAuditPerson(String mainId,String accountId) {
        //查询报销单申请人
//        ExpMainEntity main = expMainDao.selectById(mainId);
//        String ignoreUserId = accountId;
//        if(main!=null){
//            CompanyUserEntity applyUser = companyUserDao.selectById(main.getCompanyUserId());
//            if(applyUser!=null){
//                ignoreUserId +=","+applyUser.getUserId();
//            }
//            Map<String,Object> map = new HashMap<>();
//            map.put("id",mainId);
//            List<AuditDTO> auditList = this.expAuditDao.selectAuditByMainId(map);
//            for(AuditDTO audit:auditList){
//                ignoreUserId +=","+audit.getAccountId();
//            }
//        }

        ExpMainEntity main = expMainDao.selectById(mainId);
        String ignoreUserId = accountId;
        if(main!=null){
            CompanyUserEntity applyUser = companyUserDao.selectById(main.getCompanyUserId());
            if(applyUser!=null){ //提交并继续，过滤申请人
                ignoreUserId = applyUser.getUserId();
            }
        }else { //如果是新增，则过来当前人，也就是报销人
            return accountId;
        }
        return ignoreUserId;
    }

    @Override
    public ExpAuditEntity saveExpAudit(String mainId, String auditPerson, String submitAuditId, String accountId,String parentId) throws Exception {
        ExpAuditEntity auditEntity = new ExpAuditEntity();
        //插入新审批人审批记录
        auditEntity.setId(StringUtil.buildUUID());
        auditEntity.setIsNew("Y");
        auditEntity.setMainId(mainId);
        auditEntity.setParentId(parentId);
        auditEntity.setApproveStatus("0");
        auditEntity.setAuditPerson(auditPerson);
        auditEntity.setSubmitAuditId(submitAuditId);
        auditEntity.set4Base(accountId, accountId, new Date(), new Date());
        expAuditDao.insert(auditEntity);
        return auditEntity;
    }

    @Override
    public int completeAudit(SaveExpMainDTO dto) throws Exception {
        String id = dto.getId();
        String approveStatus = dto.getApproveStatus();
        Integer versionNum = dto.getVersionNum();
        CompanyUserEntity u =  this.companyUserDao.getCompanyUserByUserIdAndCompanyId(dto.getAccountId(),dto.getAppOrgId());
        String companyUserId = u.getId();
        int i = 0;
        ExpMainEntity entity = this.expMainDao.selectById(id);
        ExpAuditEntity audit = expAuditDao.getLastAuditByMainId(id);
        if (entity == null || audit == null) {
            return 0;
        }
        //修改主记录状态
        entity.setId(id);
        if (versionNum != null) {
            entity.setVersionNum(versionNum);
        }
        entity.setApproveStatus("2".equals(approveStatus)?approveStatus:"5");
        i = expMainDao.updateById(entity);

        //根据报销单id查询最新审批记录id
        TaskDTO task = new TaskDTO();
        BaseDTO.copyFields(task,dto);
        task.setCurrentCompanyUserId(companyUserId);
        task.setNextCompanyUserId(dto.getAuditPerson());
        task.setApproveStatus(approveStatus);
        task.setBusinessKey(id);
        task.setId(audit.getId());
        boolean isContinueAudit = this.processService.completeTask2(task);
        //修改当前审批记录的状态及审批意见
        audit.setAuditMessage(dto.getAuditMessage());
        audit.setApproveStatus(approveStatus);
        if(isContinueAudit){
            audit.setIsNew("N");
        }
        expAuditDao.updateById(audit);
        //保存抄送人
        expMainService.saveCopy(dto.getCcCompanyUserList(),companyUserId,id,audit.getId());
        //表示完全审核通过，则提示抄送人
        if("1".equals(dto.getApproveStatus())){
            this.sendMessageForCopy(id,entity.getType(),dto.getCurrentCompanyId(),dto.getAccountId());
        }
        return i ;
    }

    @Override
    public String saveAudit(SaveExpMainDTO dto) throws Exception {
        String id = dto.getId();
        String companyUserId = dto.getCompanyUserId();
        String currentCompanyId = dto.getAppOrgId();
        String accountId = dto.getAccountId();
        String auditPerson = dto.getAuditPerson();
        ExpMainEntity entity = this.expMainDao.selectById(id);
        List<ExpAuditEntity> lastAudit = this.expAuditDao.selectByMainId(id);
        String approveStatus = "3";//同意并转发
        if(CollectionUtils.isEmpty(lastAudit)){
            approveStatus = "0";//提交
        }
        //插入新审批人审批记录
        ExpAuditEntity audit = this.saveExpAudit(id,auditPerson,companyUserId,accountId,dto.getParentAuditId());
        //推送消息
        expMainService.sendMessageForAudit(id,currentCompanyId,auditPerson,entity.getType(),accountId,audit.getId(),approveStatus);
        return audit.getId();
    }


    /**
     *
     * @param targetId 主记录id（expMainId）
     * @param type 类型（1：报销，2：费用，3：请假，4：出差）
     * @param currentCompanyId （当前用户所在组织的id）
     * @param accountId （当前用户id）
     */
    private void sendMessageForCopy(String targetId,int type,String currentCompanyId,String accountId) throws Exception{
        if(type>2){
            //推送
            //推送抄送消息
            SendMessageDTO m = new SendMessageDTO();
            m.setTargetId(targetId);
            m.setCurrentCompanyId(currentCompanyId);
            m.setAccountId(accountId);
            if(type==3 ){//请假
                m.setMessageType(SystemParameters.MESSAGE_TYPE_238);
            }
            if(type==4){//出差
                m.setMessageType(SystemParameters.MESSAGE_TYPE_239);
            }
            this.messageService.sendMessageForCopy(m);
        }
    }
}
