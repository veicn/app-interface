package com.maoding.dynamic.dao;

import com.maoding.dynamic.dto.ZCostDTO;
import com.maoding.dynamic.dto.ZProcessNodeDTO;
import com.maoding.dynamic.dto.ZProjectDTO;
import com.maoding.dynamic.dto.ZTaskDTO;
import com.maoding.project.entity.ProjectDesignContentEntity;
import com.maoding.project.entity.ProjectEntity;
import com.maoding.project.entity.ProjectProcessNodeEntity;
import com.maoding.projectcost.entity.ProjectCostPaymentDetailEntity;
import com.maoding.projectcost.entity.ProjectCostPointDetailEntity;
import com.maoding.projectcost.entity.ProjectCostPointEntity;
import com.maoding.task.dto.TaskWithFullNameDTO;
import com.maoding.task.entity.ProjectProcessTimeEntity;
import com.maoding.task.entity.ProjectTaskEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by Chengliang.zhang on 2017/6/5.
 * 用于查找用户名和用户雇员ID，临时使用，最终应放入用户管理服务模块中
 */
public interface ZInfoDAO {
    String getUserNameByUserId(String userId);
    String getUserNameByCompanyUserId(String companyUserId);
    String getUserIdByCompanyUserId(String companyUserId);
    String getCompanyUserIdByCompanyIdAndUserId(@Param("companyId") String companyId, @Param("userId") String userId);
    String getCompanyUserIdByCompanyIdAndUserId(Map<String,String> query);
    String getProjectNameByProjectId(String projectId);
    String getProjectAddressByProject(ProjectEntity project);
    String getProjectBuiltFloorByProject(ProjectEntity project);
    String getProjectStatusNameByStatus(String status);
    String getContractCompanyNameByCompanyId(String companyId);
    String getCompanyNameByTaskId(String taskId);
    String getCompanyNameByCompanyId(String companyId);
    String getCompanyIdByTaskId(String taskId);
    String getProduceTaskFullNameByTask(ProjectTaskEntity task);
    String getProduceTaskNameByTask(ProjectTaskEntity task);
    String getProduceTaskNameWithLeaderByTask(ProjectTaskEntity task);
    String getProduceTaskNameWithMembersByTask(ProjectTaskEntity task);
    String getPhaseTaskFullNameByTask(ProjectTaskEntity task);
    String getPhaseTaskNameByTask(ProjectTaskEntity task);
    String getPhaseTaskFullNameByDesignContent(ProjectDesignContentEntity designContent);
    String getPhaseTaskNameByDesignContent(ProjectDesignContentEntity designContent);
    String getIssueTaskFullNameByTask(ProjectTaskEntity task);
    String getIssueTaskNameByTask(ProjectTaskEntity task);
    String getIssueTaskNameWithToCompanyByTask(ProjectTaskEntity task);
    String getIssueTaskNameWithPlanByTask(ProjectTaskEntity task);
    String getPeriodByTime(ProjectProcessTimeEntity time);
    String getPeriodByTaskId(String taskId);

    String getPeriodByDesignContentId(String designContentId);
    ProjectProcessTimeEntity getTimeByTaskId(String taskId);
    String getMembersByTaskId(String taskId);

    ZCostDTO getCostByPoint(ProjectCostPointEntity point);
    ZCostDTO getCostByDetail(ProjectCostPointDetailEntity detail);

    ZCostDTO getCostByPay(ProjectCostPaymentDetailEntity pay);
    TaskWithFullNameDTO getTaskByTaskId(String id);
    TaskWithFullNameDTO getTaskByTask(ProjectTaskEntity task);
    TaskWithFullNameDTO getTaskByTime(ProjectProcessTimeEntity time);
    ZProcessNodeDTO getProcessNodeByProcessNode(ProjectProcessNodeEntity node);
    ZProjectDTO getProjectByProject(ProjectEntity project);
    Integer getLastQueryCount();
}
