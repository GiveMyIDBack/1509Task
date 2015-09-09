package DBTools;

import java.sql.*;  
import java.net.*;
import java.util.*;



public class DBMain {   
		
	public void uno(String CurrentUser,String CurrentPassword){  
		
		// 驱动程序名
		String driver = "com.mysql.jdbc.Driver";

		// URL指向要访问的数据库名
		String url = "jdbc:mysql://localhost:3306/airsystem";
		
		// MySQL配置时的用户名
		String user = CurrentUser;

		// Java连接MySQL配置时的密码
		String password = CurrentPassword;

		try {
			
			// 加载驱动程序
			Class.forName(driver);

			// 连接数据库
			Connection conn = DriverManager.getConnection(url, user, password);

			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			// statement用来执行SQL语句
			Statement statement = conn.createStatement();
			String sql="";
			
			/*
			 * if A button is activated
			 * sql="******"
			 * DBTOOL.A(sql);
			 * 
			 * 
			 * 
			 * 
			*/	
			
			
			
			
			
			

			// 要执行的SQL语句(要换行，句末留够2空格)
			
			sql = "SELECT aid, origin  " + "FROM airline  ";

			
			ResultSet rs = statement.executeQuery(sql);
			String name = null;
			int id = 0;
			while (rs.next()) {
				name = rs.getString("origin");
				name = new String(name.getBytes("ISO-8859-1"), "GB2312");
				System.out.println(rs.getString("aid") + "\t" + name);
			}
			rs.close();
			conn.close();
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){  
		DBMain handle=new DBMain();
		handle.uno("root", "127127");
		
	}
}
