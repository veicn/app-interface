package com.maoding.message.dao;


import com.maoding.core.base.dao.BaseDao;
import com.maoding.message.dto.MessageDTO;
import com.maoding.message.dto.QueryMessageDTO;
import com.maoding.message.entity.MessageEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Idccapp21 on 2016/10/18.
 */
public interface MessageDao extends BaseDao<MessageEntity> {

    /**
     * 方法描述：获取消息
     * 作者：MaoSF
     * 日期：2017/3/17
     */
    List<MessageDTO> getMessage(Map<String,Object> map);

    /**
     * 方法描述：
     * 作者：MaoSF
     * 日期：2017/3/18
     */
    int getMessageCount(Map<String, Object> map);

    /**
     * 方法描述：标示为已读
     * 作者：MaoSF
     * 日期：2017/3/18
     */
    int updateRead(String userId);

    /**
     * 方法描述：标示为已读
     * 作者：MaoSF
     * 日期：2017/3/18
     */
    int updateMessageStatus(String userId);

    /**
     * 方法描述：根据关键字删除
     * 作者：MaoSF
     * 日期：2017/3/25
     */
    int deleteMessage(String field);

    /**
     * 查询
     */
    List<MessageEntity> selectByParam(QueryMessageDTO query);
}
