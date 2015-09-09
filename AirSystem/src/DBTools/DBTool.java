package DBTools;


import com.mysql.jdbc.*;
import com.mysql.jdbc.PreparedStatement;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.net.*;
import java.util.*;

public class DBTool {
	
	public  String SearchAirline(int date,int time,String origin,String destination){
		//String sql = "SELECT * FROM airline AS A  " + "WHERE A.DATE " + ";" + "";
		String sql = "select * from airline  "+
		"where airline.date = ?  "
				+"and airline.time =?  "
		+"and airline.origin =?  "
				+"and airline.destination =?  ";
		/*
		
		Connection conn = this.getconnection();
		PreparedStatement ps = Connection.PreparedStatement(sql);
		ps.setint(1, id);
		ps.setstring(2, name);
		ResultSet rs = ps.executequery();
	*/
		return null;
		
		
	}

}
