package com.weili.dao;

import java.sql.Connection;
import com.weili.database.Dao;

public class ConsumerDao {
	
	public long addConsumers(Connection conn,String cName,String cTelephone,String address,String needContent,Integer needId){
			
		Dao.Tables.t_consumers t_con = new Dao().new Tables().new t_consumers();
		
		t_con.c_name.setValue(cName);
		t_con.c_telephone.setValue(cTelephone);
		t_con.address.setValue(address);
		t_con.need_content.setValue(needContent);
		t_con.needs_status.setValue(needId);
		
		return t_con.insert(conn);
		
	}
}
