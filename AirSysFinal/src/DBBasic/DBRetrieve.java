package DBBasic;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * 
 * 提供从数据库提取客户信息的一组方法
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 主要函数清单：
 * getClientIDNum(String)
 * getClientName(String)
 * getClientPhone(String)
 * 
 */

public class DBRetrieve {

	DBUtil handler;// DBUtil手柄，以利用DBUtil提供的辅助方法
	Connection conn;// 数据库连接对象
	PreparedStatement ps;// PreparedStatement语句
	ResultSet rs;// 结果集

	/**
	 * 
	 * 构造方法
	 * 
	 * 连接数据库
	 * 
	 * 
	 */
	public DBRetrieve() {

		handler = new DBUtil();
		handler.getConn("root", "127127");
		conn = handler.conn;
		ps = null;
	}

	/**
	 * 
	 * 获取给定CID客户的姓名
	 * 查找client表
	 * 
	 * @param CurrentCid
	 *            当前客户ID
	 * 
	 * @return String 客户姓名
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String getClientName(String CurrentCid) {
		String name = "";//客户姓名
		try {

			String sql = " select Name from client where cid =  ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentCid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查询语句

			while (rs.next()) {
				name = rs.getString("name");//获取客户姓名

			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();//关闭prepareStatement语句
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return name;

	}

	/**
	 * 
	 * 获取给定CID客户的身份证号
	 * 查找client表
	 * 
	 * @param CurrentCid
	 *            当前客户ID
	 * 
	 * @return String 客户身份证号
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String getClientIDNum(String CurrentCid) {
		String identity_number = "";//客户身份证号
		try {

			String sql = " select identity_number from client where cid = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentCid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查询语句

			while (rs.next()) {
				identity_number = rs.getString("identity_number");//获取客户身份证号

			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();//关闭prepareStatement语句
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return identity_number;

	}

	/**
	 * 
	 * 获取给定CID客户的手机号
	 * 查找client表
	 * 
	 * @param CurrentCid
	 *            当前客户ID
	 * 
	 * @return String 客户手机号
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String getClientPhone(String CurrentCid) {
		String phone = "";//客户手机号
		try {

			String sql = " select phone from client where cid =  ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentCid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查询语句

			while (rs.next()) {
				phone = rs.getString("phone");//获取客户手机号

			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();//关闭prepareStatement语句
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return phone;

	}


}
