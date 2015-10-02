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
 * 提供客户登录、客户注册、注销方法
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 *主要函数清单：
 * login(String, String)
 * register(String, String, String, String)
 * logout(String) 
 * 
 */

public class DBLoginHandler {

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
	public DBLoginHandler() {
		handler = new DBUtil();
		handler.getConn("root", "127127");
		conn = handler.conn;
		ps = null;
	}

	/**
	 * 
	 * 登录
	 * 查找membership/operator/admin表
	 * 
	 * @param CurrentID
	 *            ID
	 *        CurrentPassword
	 *            密码
	 * 
	 * @return int 返回值  0：ID无效  1：注册客户登录成功   2：操作员登录成功  3：管理员登陆成功 8：登录失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public int login(String CurrentID, String CurrentPassword) {
		int flag = 0;//返回值
		/*如果该ID不属于任何注册客户、操作员、管理员，则该ID无效，返回0*/
		if (!handler.checkC_exist(CurrentID) && !handler.checkO_exist(CurrentID)
				&& !handler.checkAdmin_exist(CurrentID)) {
			System.out.println("该账户不存在");
			return flag;
		}

		try {
			/*检查该ID与密码是否属于注册客户*/
			String sql = "select * from membership  where cid = ?  and password = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentID);//取代占位符
			ps.setString(2, CurrentPassword);

			ResultSet rs = ps.executeQuery();//执行sql查询语句

			/*如果结果集不为空，则属于注册用户，返回值1*/
			if (rs.next()) {
				System.out.println("WELCOME  客户 " + CurrentID);
				flag = 1;
			} else {
				
				/*检查该ID与密码是否属于操作员*/
				sql = "select * from operator  where oid = ?  and password = ?  ";

				ps = conn.prepareStatement(sql);//创建prepareStatement语句
				ps.setString(1, CurrentID);//取代占位符
				ps.setString(2, CurrentPassword);

				rs = ps.executeQuery();//执行sql查询语句

				/*如果结果集不为空，则属于操作员，返回值2*/
				if (rs.next()) {
					System.out.println("WELCOME  操作员 " + CurrentID);
					flag = 2;
				} else {
					
					/*检查该ID与密码是否属于管理员*/
					sql = "select * from admin  where admin_id = ?  and password = ?  ";

					ps = conn.prepareStatement(sql);//创建prepareStatement语句
					ps.setString(1, CurrentID);//取代占位符
					ps.setString(2, CurrentPassword);

					rs = ps.executeQuery();//执行sql查询语句
					
					/*如果结果集不为空，则属于操作员，返回值3*/
					if (rs.next()) {

						System.out.println("WELCOME  管理员 " + CurrentID);
						flag = 3;

						/*如果结果集依旧为空，则登录失败，返回值8*/
					} else {
						System.out.println("登录失败，请重试 ");
						flag = 8;
					}
				}

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
		return flag;
	}

	/**
	 * 
	 * 注册
	 * 向membership和client表插入内容
	 * 
	 * @param CurrentName
	 *            姓名
	 *        CurrentIDNum
	 *            身份证号
	 *        CurrentPhone
	 *            手机号
	 *        CurrentPassword
	 *            密码
	 * 
	 * @return String 返回值  0：身份证号格式错误  1：身份证号已被注册   2：手机号已被注册  3：密码长度有误 8：登录失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String register(String CurrentName, String CurrentIDNum, String CurrentPhone, String CurrentPassword) {

		String result = "";
		/*如果身证份号长度不合法，返回0*/
		if (CurrentIDNum.length() != 18) {
			System.out.println("请正确输入身份证号");
			return "0";
		}
		/*如果身证份号已被注册，返回1*/
		if (handler.checkIDNDup(CurrentIDNum)) {
			System.out.println("该身份证号已注册");
			return "1";
		}
		/*如果手机号已被注册，返回2*/
		if (handler.checkPhoneDup(CurrentPhone)) {
			System.out.println("该手机号已注册");
			return "2";
		}
		/*如果密码长度不合法，返回3*/
		if (CurrentPassword.length() < 4 || CurrentPassword.length() > 10) {
			System.out.println("密码长度有误，请输入4~10位密码");
			return "3";
		}

		String sql = "";
		try {
			sql = " INSERT INTO membership VALUES ( ? , ?  )  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句

			String CurrentCid = "C" + handler.getAndUpdateTail("cid");//给新客户分配CID

			ps.setString(1, CurrentCid);//取代占位符
			ps.setString(2, CurrentPassword);

			int rs = ps.executeUpdate();//执行sql插入语句

			sql = " INSERT INTO client VALUES ( ? , ? ,? , ? )  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句

			ps.setString(1, CurrentCid);//取代占位符
			ps.setString(2, CurrentIDNum);
			ps.setString(3, CurrentPhone);
			ps.setString(4, CurrentName);

			int rs2 = ps.executeUpdate();//执行sql插入语句

			/*如果两语句均成功插入，返回4和CID，若插入失败返回8*/
			if ((rs != 0) && (rs2 != 0)) {
				result = "4" + CurrentCid;
				System.out.println("成功注册新用户");
			} else {
				result = "8";
				System.out.println("错误，请重新注册");
			}

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
		return result;
	}

	/**
	 * 
	 * 注销
	 * 从membership和client表中删除内容
	 * 
	 * @param CurrentCid
	 *            客户ID
	 * 
	 * @return String 返回值  0：注销成功  8：注销失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String logout(String CurrentCid) {
		String result="";//返回值
		String sql = "";
		
		try {

			sql = " DELETE FROM membership WHERE cid =?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentCid);//取代占位符

			int rs1 = ps.executeUpdate();//执行sql删除语句

			sql = " DELETE FROM client WHERE cid =?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentCid);//取代占位符

			int rs2 = ps.executeUpdate();//执行sql删除语句

			/*如果两语句均成功删除，返回0，若删除失败返回8*/
			if ((rs1 != 0) && (rs2 != 0)){
				System.out.println("成功删除客户");
				result="0";
			}
			else{
				System.out.println("错误，请重试");
				result="8";
			}

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
		return result;
	}

	

}
