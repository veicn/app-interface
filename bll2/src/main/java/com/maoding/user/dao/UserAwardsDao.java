package com.maoding.user.dao;

import java.util.List;

import com.maoding.core.base.dao.BaseDao;
import com.maoding.user.entity.UserAwardsEntity;
import com.maoding.user.entity.UserEntity;

/**
 * 深圳市设计同道技术有限公司
 * 类    名：UserDao
 * 类描述：用户信息Dao
 * 作    者：MaoSF
 * 日    期：2015年11月23日-下午2:44:39
 */
public interface UserAwardsDao extends BaseDao<UserAwardsEntity>{
	
	/**
	 * 方法描述：根据参数查询个人获奖信息
	 * 作        者：MaoSF
	 * 日        期：2015年11月23日-下午4:48:56
	 * @param map
	 * @return
	 */
	public List<UserAwardsEntity> getUserAwardshonorsByUserId(String userId);
	
	/**
	 * 方法描述：根据参数删除个人获奖信息
	 * 作        者：MaoSF
	 * 日        期：2015年11月23日-下午4:52:17
	 * @param map
	 * @return
	 */
	public int delUserAwardshonorsByUserId(String userId);
}
