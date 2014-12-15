package com.weili.dao;

import java.sql.Connection;
import com.weili.database.Dao;

public class ConsumerDao {
	
	public long addConsumers(Connection conn,String cName,String cTelephone,String address,String needContent,Integer needId){
			
		Dao.Tables.t_consumers t_con = new Dao().new Tables().new t_consumers();
		return t_con.insert(conn);
		
	}
}
