package com.weili.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shove.base.BaseService;
import com.shove.data.DataException;
import com.shove.data.dao.MySQL;
import com.weili.dao.RightsDao;


public class RightsService extends BaseService {
	
	private Log log = LogFactory.getLog(RightsService.class);
	private RightsDao rightsDao;
	
	
	/**
	 * 添加数据到bt_rights
	 * @param data
	 * @return Long
	 * @throws SQLException
	 */
	public Long importRights(String [][] data) throws SQLException{
		 Connection conn = MySQL.getConnection();
		 Long returnId = -1L;
		try {
			rightsDao.importRights(conn, data);
			conn.commit();
		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
			throw e;
		}finally{
			conn.close();
		}
		 return returnId;
	}
	
	/**
	 * 查询权限集合
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String,Object>> queryRightsList() throws Exception{
		Connection conn = connectionManager.getConnection();
		List<Map<String,Object>> list;
		try{
			list = rightsDao.queryRightsList(conn);
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}
	
	/**
	 * 查询权限菜单
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String,Object>> queryRightsMenuList() throws Exception{
		Connection conn = connectionManager.getConnection();
		List<Map<String,Object>> list;
		try{
		 list = rightsDao.queryRightsMenuList(conn);
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			throw e;
		}finally{
			conn.close();
		}
		return list;
	}
	
	public void setRightsDao(RightsDao rightsDao) {
		this.rightsDao = rightsDao;
	}
	
	
}
