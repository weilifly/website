package com.weili.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shove.Convert;
import com.shove.base.BaseService;
import com.shove.data.DataException;
import com.shove.data.dao.MySQL;
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
	public long addConsumers(String cName,String cTelephone,String address,String needContent,Integer needId) throws SQLException{
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
	 * 根据条件分页查询潜在用户
	 * @param userName 用户名
	 * @param enable 状态
	 * @param pageBean
	 * @throws SQLException 
	 * @throws DataException
	 */
	public void queryConsumersPage(PageBean<Map<String,Object>> pageBean,StringBuffer condition, String table, String order,String fieldList ) throws SQLException, DataException{
		Connection conn = connectionManager.getConnection();
		try {
			dataPage(conn, pageBean, table, fieldList, order, condition.toString());
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
	
	
	//待用1
	public Map<String,Object> updateConsumer(long id,String title,String source,Long views,String image,String content,Integer status,Integer isRecommended,Integer isIndex,Integer sortIndex,String seoTitle,String seoKeywords,String seoDescription,String addTime) throws Exception{
		Connection conn = null;
		
		long returnId = -1;
		String error = "修改失败";
		
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			conn = MySQL.getConnection();
		//	returnId = consumerDao.update(conn, id, title, source, views, image,  content, status, isRecommended, isIndex, sortIndex, seoTitle, seoKeywords, seoDescription,addTime);
			if(returnId <= 0){
				conn.rollback();
				return map;
			}
			
			conn.commit();
			error = "修改成功";
		}catch (Exception e) {
			if(conn != null){
				conn.close();
			}
			log.error(e);
			e.printStackTrace();
			throw e;
		}finally{
			if(conn != null){
				conn.close();
			}
			
			map.put("returnId", returnId);
			map.put("error", error);
		}
		
		return map;
	}
}
