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
 * 提供操作员界面所需的额外功能，如：增添客户，用手机号查询客户
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 * 
 * 主要函数清单：
 * addClient(String, String, String, String)
 * searchClientByCid(String)
 * searchClientByIDNum(String)
 * searchClientByPhone(String)
 *
 * 
 * 
 */
public class DBHandlerOper {
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

	public DBHandlerOper() {
		handler = new DBUtil();
		handler.getConn("root", "127127");
		conn = handler.conn;
		ps = null;
	}

	/**
	 * 
	 * 添加新客户
	 * 
	 * @param CurrentIDNum
	 *            身份证号
	 *        CurrentPhone
	 *            手机号
	 *        CurrentName
	 *            姓名
	 *        CurrentSid
	 *            业务员
	 * 
	 * @return String 
	 *         1：身份证号已注册
	 *         2：手机号已注册
	 *         3：业务员id不存在
	 *         4：成功
	 *         8：失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String addClient(String CurrentIDNum, String CurrentPhone, String CurrentName, String CurrentSid) {
		String result = "";
		
		/*检查身份证号是否已被注册*/
		if (handler.checkIDNDup(CurrentIDNum)) {
			System.out.println("该身份证号已注册");
			return "1";
		}

		/*检查手机号是否已被注册*/
		if (handler.checkPhoneDup(CurrentPhone)) {
			System.out.println("该手机号已注册");
			return "2";
		}
		/*检查业务员id是否有效*/
		if (CurrentSid.length() != 0) {
			if (!handler.checkS_exist(CurrentSid)) {
				System.out.println("该业务员不存在");
				return "3";
			}
		}

		String CurrentCid = "C" + handler.getAndUpdateTail("cid");//生成新客户id

		String sql = "";

		try {
			sql = " INSERT INTO client VALUES ( ? , ? , ? , ? )  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句

			ps.setString(1, CurrentCid);//取代占位符
			ps.setString(2, CurrentIDNum);
			ps.setString(3, CurrentPhone);
			ps.setString(4, CurrentName);

			int rs1 = ps.executeUpdate();//执行sql插入语句
			
			int rs2 = 10;
			/*如果CurrentSid不为空，插入业务员与客户关系表*/
			
			if (CurrentSid.length() != 0) {
				sql = " INSERT INTO business_c2s VALUES ( ? , ?  )  ";

				ps = conn.prepareStatement(sql);//创建prepareStatement语句

				ps.setString(1, CurrentCid);//取代占位符

				ps.setString(2, CurrentSid);

				rs2 = ps.executeUpdate();//执行sql插入语句

			}

			/*成功，返回4和客户id，否则，返回8*/
			if ((rs1 != 0) && (rs2 != 0)) {

				System.out.println("成功添加新客户");
				result = "4" + CurrentCid;
			} else {
				System.out.println("错误，请重新登记");
				result = "8";
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
	 * 用手机号搜索客户
	 * 
	 * @param  CurrentPhone
	 *            手机号
	 *       
	 * 
	 * @return String 
	 *         0+客户信息：成功
	 *         1：无结果
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String searchClientByPhone(String CurrentPhone) {
		String result = "";
		String sql = "";
		try {
			
			String cid = "";//客户id
			String identity_number = "";//身份证号
			String name = "";//姓名

			sql = "select * from client  where phone = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentPhone);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查找语句

			int count = 0;
			while (rs.next()) {
				count++;
				cid = rs.getString("cid");//获取客户id
				identity_number = rs.getString("identity_number");//获取客户身份证号
				name = rs.getString("name");//获取客户姓名

				/*编辑返回信息串*/
				result += "0" + cid + "-" + identity_number + "-" + CurrentPhone + "-" + name;

			}
			/*如果没有匹配信息，返回1*/
			if (count == 0) {
				System.out.println("没有匹配结果");
				result = "1";
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
		return result;
	}

	/**
	 * 
	 * 用身份证号搜索客户
	 * 
	 * @param  CurrentIDNum
	 *            身份证号
	 *       
	 * 
	 * @return String 
	 *         0+客户信息：成功
	 *         1：无结果
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String searchClientByIDNum(String CurrentIDNum) {
		String result = "";
		String sql = "";

		try {
			String cid = "";//客户id
			String phone = "";//手机号
			String name = "";//姓名
			
			sql = "select * from client where identity_number = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentIDNum);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查询语句


			int count = 0;
			while (rs.next()) {
				count++;
				cid = rs.getString("cid");//获取客户id
				phone = rs.getString("phone");//获取手机号
				name = rs.getString("name");//获取姓名

				/*编辑返回信息串*/
				result = "0" + cid + "-" + CurrentIDNum + "-" + phone + "-" + name;

			}
			/*若没有结果，返回1*/
			if (count == 0) {
				System.out.println("没有匹配结果");
				result = "1";
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
		return result;
	}

	/**
	 * 
	 * 用客户id搜索客户
	 * 
	 * @param  CurrentCid
	 *            身份证号
	 *       
	 * 
	 * @return String 
	 *         0+客户信息：成功
	 *         1：无结果
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String searchClientByCid(String CurrentCid) {
		String result = "";
		String sql = "";
		
		try {
			String identity_number = "";//身份证号
			String phone = "";//手机号
			String name = "";//姓名

			
			sql = "select * from client  where cid = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentCid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查询语句

			
			int count = 0;
			while (rs.next()) {
				count++;
				identity_number = rs.getString("identity_number");//获取身份证号
				phone = rs.getString("phone");//获取手机号
				name = rs.getString("name");//获取姓名

				/*编辑返回结果串*/
				result = "0" + CurrentCid + "-" + identity_number + "-" + phone + "-" + name;

			}
			/*若没有结果，返回1*/
			if (count == 0) {
				System.out.println("没有匹配结果");
				result = "1";
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
		return result;
	}

}
