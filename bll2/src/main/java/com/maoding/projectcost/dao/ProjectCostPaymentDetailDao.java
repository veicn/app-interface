package com.maoding.projectcost.dao;

import com.maoding.core.base.dao.BaseDao;
import com.maoding.projectcost.dto.ProjectCostPaymentDetailDTO;
import com.maoding.projectcost.dto.ProjectCostPointReceiveOrPayInfoDTO;
import com.maoding.projectcost.dto.ProjectCostQueryDTO;
import com.maoding.projectcost.entity.ProjectCostPaymentDetailEntity;

import java.util.List;
import java.util.Map;


/**
 * 深圳市设计同道技术有限公司
 * 类    名：ProjectCostDetailDao
 * 类描述：项目费用详情
 * 作    者：MaoSF
 * 日    期：2015年8月10日-下午4:28:32
 */
public interface ProjectCostPaymentDetailDao extends BaseDao<ProjectCostPaymentDetailEntity> {


    /**
     * 方法描述：根据pointDetailId查询收款详情
     * 作者：MaoSF
     * 日期：2017/3/9
     * @param:
     * @return:
     */
    List<ProjectCostPaymentDetailDTO> selectByPointDetailId(Map<String, Object> map);


    /**
     * 方法描述：根据父节点，查询子节点或许自己节点的明细
     * 作者：MaoSF
     * 日期：2017/3/13
     * @param:
     * @return:
     */
    List<ProjectCostPaymentDetailEntity> getDetailByRootId(String pointDetailId);

    double getSumFee(String pointDetailId);

    /**
     * 方法描述：根据发起收款的id逻辑删除收款明细
     * 作者：MaoSF
     * 日期：2017/4/27
     * @param:
     * @return:
     */
    int updateCostPaymentDetailByPointDetailId(ProjectCostPaymentDetailEntity entity);

    /**
     * 获取收付款明细
     */
    List<ProjectCostPaymentDetailDTO> listProjectPaymentFeeByPointDetailId(ProjectCostQueryDTO query);

    /**
     * 收付款详情列表
     */
    List<ProjectCostPointReceiveOrPayInfoDTO> listProjectCostPointReceiveOrPayInfo(ProjectCostQueryDTO query);

    /**
     * 应收付款详情列表
     */
    List<ProjectCostPointReceiveOrPayInfoDTO> listProjectCostPointNotReceiveOrPayInfo(ProjectCostQueryDTO query);

}
