package com.weili.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shove.base.BaseService;
import com.shove.data.DataException;
import com.shove.vo.PageBean;
import com.weili.dao.ConsumerDao;

public class ConsumerService extends BaseService {

	public static Log log = LogFactory.getLog(ConsumerService.class);
	
	private ConsumerDao consumerDao;
	
	
	public void setConsumerDao(ConsumerDao consumerDao) {
		this.consumerDao = consumerDao;
	}

	/**
	 * 潜在用户信息收集
	 * @param cName 用户名
	 * @param cTelephone 
	 * @param address
	 * @param needContent
	 * @param needId
	 * @throws SQLException 
	 * @throws DataException
	 */
	public Map addConsumers(String cName,String cTelephone,String address,String needContent,Integer needId) throws SQLException{
		Connection conn = connectionManager.getConnection();
		long returnId = -1;
		try {
			returnId = consumerDao.addConsumers(conn,cName,cTelephone,address,needContent,needId);
			return returnId;
		} finally{
			conn.close();
		}
	}
	
	/**
	 * 根据条件分页查询管理员信息
	 * @param userName 用户名
	 * @param enable 状态
	 * @param pageBean
	 * @throws SQLException 
	 * @throws DataException
	 */
	public void queryConsumersPage(StringBuffer condition, PageBean<Map<String,Object>> pageBean) throws SQLException, DataException{
		Connection conn = connectionManager.getConnection();
		try {
			dataPage(conn, pageBean, "v_t_consumers_needs", " * ", "", condition.toString());
		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
			throw e;
		} catch (DataException e) {
			log.error(e);
			e.printStackTrace();
			throw e;
		}finally{
			conn.close();
		}
	}
}
