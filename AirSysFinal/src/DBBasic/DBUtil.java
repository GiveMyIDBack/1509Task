package DBBasic;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.Random;
import Tools.SeatTranslator;
import Tools.bitTranslator;

/**
 * 
 * 辅助系统完成数据库增删改查工具类 如：连接数据库、检查ID是否有效、选座方法、ID格式工具、随机选取送票员的方法
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 * 
 * 
 * 主要函数清单：
 * getConn(String, String)
 * randomDeliveryman()
 * checkA_exist(String) 
 * checkAdmin_exist(String)
 * checkC_exist(String)
 * checkD_exist(String)
 * checkIDNDup(String)
 * checkIDNDupExceptSelf(String, String)
 * checkO_exist(String)
 * checkOrder_exist(String)
 * checkPhoneDup(String)
 * checkPhoneDupExceptSelf(String, String)
 * checkS_exist(String)
 * checkSeat(String, String, String)
 * checkT_exist(String)
 * checkTYPE_exist(String)
 * getAndUpdateTail(String)
 * getBusiSalesman(String)
 * getSeat(String)
 * GiveupSeat_airlinet(String, String)
 * GiveupSeat_seatt(String, String, String)
 * ProcessSeat_airlinet(String, String)
 * ProcessSeat_seatt(String, String, String)
 * 
 *
 * 
 * 
 */

public class DBUtil {

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/airsystem";
	Connection conn = null;// 数据库连接对象
	PreparedStatement ps = null;//prepareStatement语句
	ResultSet rs;// 结果集

	/**
	 * 
	 * 建立数据库连接
	 * 
	 * @param User
	 *            数据库用户名 
	 *        Password 
	 *            数据库密码
	 * 
	 * @return void
	 * 
	 * @exception ClassNotFoundException
	 *            SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public void getConn(String User, String Password) {
		try {
			Class.forName(driver);// 加载驱动器
			conn = DriverManager.getConnection(url, User, Password);// 建立数据库连接
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 将int处理成八位字符串数字
	 * 
	 * @param num
	 *            要处理的int 
	 * 
	 * @return String 八位字符串数字
	 * 
	 * @exception null
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public static String getFormat(int num) {
		DecimalFormat template = new DecimalFormat("00000000");
		String StrNum = template.format(num);
		return StrNum;
	}
	
	/**
	 * 
	 * 分配CID TID DID SID OID 时，寻找每种ID当前已分配到了几号，并加一
	 * 查找metadata表
	 * 
	 * @param CurrentVar
	 *            要分配的ID类型 
	 * 
	 * @return String 指定类型的要分配的ID
	 * 
	 * @exception SQLException 
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String getAndUpdateTail(String CurrentVar) {
		int tail = 0;//当前类型ID的尾数
		String sql = "";
		try {
			/*获取所需尾数*/
			sql = "select tail from metadata  " + " where var = ?  ";
			ps = conn.prepareStatement(sql);//创建prepareStatement对象
			
			ps.setString(1, CurrentVar);//取代参数占位符

			rs = ps.executeQuery();//执行sql查找语句

			while (rs.next()) {
				tail = rs.getInt("tail");//获取所需尾数

			}
			rs.close();

			/*更新当前类型尾数*/
			sql = "update metadata set tail= ?  " + "where var = ?  ";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, tail + 1);//取代占位符
			ps.setString(2, CurrentVar);

			int rs = ps.executeUpdate();//执行sql更新语句
			if (rs != 0)
				System.out.println("tail查询成功");
			else
				System.out.println("tail查询失败");

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
		return DBUtil.getFormat(tail);//结果处理成八位字符串
	}

	
	/**
	 * 
	 * 查找某客户所对应的业务员
	 * 查找business_c2s表
	 * 
	 * @param CurrentCid
	 *            客户ID
	 * 
	 * @return String 业务员ID
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String getBusiSalesman(String CurrentCid) {
		String sid = "";
		try {

			String sql = " select sid from business_c2s where cid =  ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement对象
			ps.setString(1, CurrentCid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行SQL查找语句

			while (rs.next()) {
				sid = rs.getString("sid");//获取业务员ID
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

		return sid;

	}

	
	/**
	 * 
	 * 根据机型查找该飞机头等舱、商务舱、经济舱分别有多少座位
	 * 查找aircraft表
	 * 
	 * @param CurrentType
	 *            机型
	 * 
	 * @return int[] [0]:头等舱座位数 [1]:商务舱座位数 [2]:经济舱座位数
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public int[] getSeat(String CurrentType) {
		int[] seat = new int[3];
		try {

			String sql = " select first_num , business_num , economic_num  " 
			             + " from aircraft where type  =  ?  ";
			ps = conn.prepareStatement(sql);//创建prepareStatement对象
			ps.setString(1, CurrentType);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查找语句

			while (rs.next()) {
				seat[0] = rs.getInt("first_num");//获取头等舱座位数
				seat[1] = rs.getInt("business_num");//获取商务舱座位数
				seat[2] = rs.getInt("economic_num");//获取经济舱座位数

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

		return seat;

	}

	/**
	 * 
	 * 检查当前身份证号是否已存在
	 * 查找client表
	 * 
	 * @param CurrentIDNum
	 *            当前身份证号
	 * 
	 * @return boolean false 不存在，可以用于注册；true 已存在，已被注册 
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	boolean checkIDNDup(String CurrentIDNum) {
		boolean flag = false;
		String sql = "";
		try {

			sql = "select * from client where identity_number = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentIDNum);//取代占位符

			rs = ps.executeQuery();//执行sql查找语句

			if (rs.next()) {
				flag = true;//如果查找结果不为空，flag=true，说明该身份证号已被注册过
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();//关闭preparedStatement语句
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	
	/**
	 * 
	 * 检查当前手机号是否已存在
	 * 查找client表
	 * 
	 * @param CurrentPhone
	 *            当前手机号
	 * 
	 * @return boolean false 不存在，可以用于注册；true 已存在，已被注册 
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public boolean checkPhoneDup(String CurrentPhone) {
		boolean flag = false;
		String sql = "";
		try {

			sql = "select * from client  where phone = ?  ";
			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentPhone);//取代占位符

			rs = ps.executeQuery();//执行sql查询语句

			if (rs.next()) {
				flag = true;//如果查找结果不为空，flag=true，说明该手机号已被注册过
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();//关闭preparedStatement语句
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	
	/**
	 * 
	 * 检查该身份证号是否属于除了客户本人以外的其他客户。
	 * 查找client表
	 * 用于以下情景：当修改客户个人信息中的身份证号时，所输入的新身份证号与旧身份证号相同，此时不能报错。
	 * 
	 * @param CurrentIDNum
	 *            当前身份证号
	 *        CurrentCid
	 *            当前客户ID
	 * 
	 * @return boolean false 不存在，可以使用；true 已存在，已被注册 
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public boolean checkIDNDupExceptSelf(String CurrentIDNum, String CurrentCid) {
		boolean flag = false;
		String sql = "";
		try {

			sql = "select * from client  where identity_number = ? and cid <> ?   ";
			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentIDNum);//取代占位符
			ps.setString(2, CurrentCid);

			rs = ps.executeQuery();//执行sql查找语句。是否存在某个客户，身份证号等于CurrentIDNum，但客户id不等于CurrentCid
			if (rs.next()) {
				flag = true;//如果结果不为空，那么该身份证号属于非当前客户以外的其他客户
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
	 * 检查该手机号是否属于除了客户本人以外的其他客户。
	 * 查找client表
	 * 用于以下情景：当修改客户个人信息中的手机号时，所输入的新手机号与旧手机号相同，此时不能报错。
	 * 
	 * @param CurrentPhone
	 *            当前手机号
	 *        CurrentCid
	 *            当前客户ID
	 * 
	 * @return boolean false 不存在，可以使用；true 已存在，已被注册 
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public boolean checkPhoneDupExceptSelf(String CurrentPhone, String CurrentCid) {
		boolean flag = false;
		String sql = "";
		try {

			sql = "select * from client  where phone = ?  and cid <> ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentPhone);//取代占位符
			ps.setString(2, CurrentCid);

			rs = ps.executeQuery();//执行sql查找语句。是否存在某个客户，手机号等于CurrentPhone，但客户id不等于CurrentCid

			if (rs.next()) {
				flag = true;//如果结果不为空，那么该手机号属于非当前客户以外的其他客户
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
	 * 检查拥有某SID的业务员是否存在
	 * 查找salesman表
	 * 
	 * @param CurrentSid
	 *            当前业务员ID
	 * 
	 * @return boolean false 不存在，CurrentSid无效；true 存在，CurrentSid有效
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public boolean checkS_exist(String CurrentSid) {
		boolean flag = false;
		String sql = "";
		try {
			
			sql = "select * from salesman where sid = ?  ";
			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentSid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查找语句

			if (rs.next()) {
				flag = true;//如果有结果，说明CurrentSid存在，有效
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
	 * 检查某机型是否存在
	 * 查找aircraft表
	 * 
	 * @param CurrentType
	 *            当前机型
	 * 
	 * @return boolean false 不存在，CurrentType无效；true 存在，CurrentType有效
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public boolean checkTYPE_exist(String CurrentType) {
		boolean flag = false;
		String sql = "";
		try {
			
			sql = "select * from aircraft  where type = ?  ";
			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentType);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查找语句

			if (rs.next()) {
				flag = true;//如果结果不为空，则CurrentType存在，有效
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
	 * 检查拥有某DID的送票员是否存在
	 * 查找deliveryman表
	 * 
	 * @param CurrentDid
	 *            当前送票员ID
	 * 
	 * @return boolean false 不存在，CurrentDid无效；true 存在，CurrentDid有效
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public boolean checkD_exist(String CurrentDid) {
		boolean flag = false;
		String sql = "";
		try {
			sql = "select * from deliveryman  where did = ?  ";
			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentDid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查找语句

			if (rs.next()) {
				flag = true;//如果结果不为空，则CurrentDid存在，有效
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
	 * 检查拥有某TID的售票点是否存在
	 * 查找ticketbox表
	 * 
	 * @param CurrentTid
	 *            当前售票点ID
	 * 
	 * @return boolean false 不存在，CurrentTid无效；true 存在，CurrentTid有效
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public boolean checkT_exist(String CurrentTid) {
		boolean flag = false;
		String sql = "";
		try {
			sql = "select * from ticketbox  where tid = ?  ";
			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentTid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查找语句

			if (rs.next()) {

				flag = true;//如果结果不为空，CurrentTid存在，有效
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
	 * 检查拥有某AID的航班是否存在
	 * 查找airline表
	 * 
	 * @param CurrentAid
	 *            当前航班ID
	 * 
	 * @return boolean false 不存在，CurrentAid无效；true 存在，CurrentAid有效
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public boolean checkA_exist(String CurrentAid) {
		boolean flag = false;
		String sql = "";
		try {
			sql = "select * from airline  where aid = ?  ";
			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentAid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查找语句

			if (rs.next()) {

				flag = true;//如果结果不为空，则CurrentAid存在，有效
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
	 * 检查拥有某CID的客户是否存在
	 * 查找client表
	 * 
	 * @param CurrentCid
	 *            当前客户ID
	 * 
	 * @return boolean false 不存在，CurrentCid无效；true 存在，CurrentCid有效
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public boolean checkC_exist(String CurrentCid) {
		boolean flag = false;
		String sql = "";
		try {
			sql = "select * from client  where cid = ?  ";
			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentCid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查找语句

			if (rs.next()) {

				flag = true;//如果结果不为空，则CurrentCid存在，有效
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
	 * 检查拥有某OID的操作员是否存在
	 * 查找operator表
	 * 
	 * @param CurrentOid
	 *            当前操作员ID
	 * 
	 * @return boolean false 不存在，CurrentOid无效；true 存在，CurrentOid有效
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public boolean checkO_exist(String CurrentOid) {
		boolean flag = false;
		String sql = "";
		try {
			sql = "select * from operator  " + "where oid = ?  ";
			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentOid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查找语句

			if (rs.next()) {

				flag = true;//如果结果不为空，则CurrentOid存在，有效
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
	 * 检查拥有某adminID的管理员是否存在
	 * 查找admmin表
	 * 
	 * @param CurrentID
	 *            当前管理员ID
	 * 
	 * @return boolean false 不存在，CurrentID无效；true 存在，CurrentID有效
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public boolean checkAdmin_exist(String CurrentID) {
		boolean flag = false;
		String sql = "";
		try {
			sql = "select * from admin  where admin_id = ?  ";
			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentID);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查询语句

			if (rs.next()) {

				flag = true;//如果结果不为空，则CurrentID存在，有效
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
	 * 检查拥有某OrderID的订单是否存在
	 * 查找orders表
	 * 
	 * @param CurrentOrderid
	 *            当前订单ID
	 * 
	 * @return boolean false 不存在，CurrentOrderid无效；true 存在，CurrentOrderid有效
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
 	public boolean checkOrder_exist(String CurrentOrderid) {
		boolean flag = false;
		String sql = "";
		try {
			sql = "select * from orders where orderid = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement对象
			ps.setString(1, CurrentOrderid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查询语句

			if (rs.next()) {

				flag = true;//如果结果不为空，则CurrentOrderid存在，有效
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
	 * 随机选择送票员
	 * 
	 * @return String 管理员id
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String randomDeliveryman() {

		int num = 0;//记录送票员总数目
		String did = "";//记录本次随机派遣的送票员的ID
		String sql = "";
		try {

			sql = "select count(*) from deliveryman";
			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			rs = ps.executeQuery();//执行sql查询语句，统计deliveryman表的总条目数

			while (rs.next()) {
				num = rs.getInt("count(*)");//获取在职送票员总数目
			}

			sql = "select did from deliveryman";
			ps = conn.prepareStatement(sql);//创建prepareStatement语句

			int stop = (int) (1 + Math.random() * (num - 1 + 1));//在1~num区间内取一个随机数stop，将派遣第stop位送票员送票

			rs = ps.executeQuery();//执行sql查询语句，获取deliveryman表中的全部did条目，形成结果集rs

			while (rs.next() && stop >= 1) {
				stop--;
				did = rs.getString("did");

			}//获取第stop为送票员的did后，退出循环
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
		return did;
	}

	/**
	 * 
	 * 检查某座位是否可选
	 * 
	 * @param CurrentAid
	 *            当前航班号
	 *        SeatType
	 *            座位类型
	 *        CurrentSeat
	 *            待查座位号
	 * 
	 * @return boolean false 座位不可选；true 座位可选
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public boolean checkSeat(String CurrentAid, String SeatType, String CurrentSeat) {
		
		String plane_type = "";//该航班的机型
		String first_seat = "";//代表头等舱座位被选情况的字符串
		String business_seat = "";//代表商务舱座位被选情况的字符串
		String economic_seat = "";//代表经济舱座位被选情况的字符串
		int first_num = 0;//头等舱座位数
		int business_num = 0;//商务舱座位数
		int economic_num = 0;//经济舱座位数
		
		String sql = "";
		try {

			sql = "select  aircraft.first_num , aircraft.business_num , aircraft.economic_num   "
					+ "from airline inner join aircraft  " + " where airline.type  =  aircraft.type   "
					+ " and aid = ?   ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentAid);//取代占位符
			ResultSet rs = ps.executeQuery();//执行sql查询语句，内连接airline表和aircraft表，通过航班号获取该航班的座位参数

			while (rs.next()) {

				first_num = rs.getInt("aircraft.first_num");//获取头等舱座位数
				business_num = rs.getInt("aircraft.business_num");//获取商务舱座位数
				economic_num = rs.getInt("aircraft.economic_num");//获取经济舱座位数
			}

			sql = "select plane_type , first_seat , business_seat , economic_seat from seat " + " where aid =  ?   ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentAid);//取代占位符
			rs = ps.executeQuery();//执行sql查询语句，通过航班号获取该航班的选座情况和机型

			while (rs.next()) {
				plane_type = rs.getString("plane_type");//获取机型
				first_seat = rs.getString("first_seat");//获取代表头等舱选座情况的16进制字符串
				business_seat = rs.getString("business_seat");//获取代表商务舱选座情况的16进制字符串
				economic_seat = rs.getString("economic_seat");//获取代表经济舱选座情况的16进制字符串
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
		/*将代表选座情况的16进制字符串转为2进制字符串，转换后的2进制串可与线性化后的对应类型座位的座位号一一对应*/
		first_seat = bitTranslator.hex2bi(first_seat, first_num);//将代表头等舱选座情况的16进制字符串转为2进制字符串
		business_seat = bitTranslator.hex2bi(business_seat, business_num);//将代表商务舱选座情况的16进制字符串转为2进制字符串
		economic_seat = bitTranslator.hex2bi(economic_seat, economic_num);//将代表经济舱选座情况的16进制字符串转为2进制字符串

		int LinearID = SeatTranslator.trans_seat(plane_type, SeatType, CurrentSeat);//将当前座位号转化成线性编号LinearID
		
		
		/*检查代表对应类型选座情况的2进制串的第k位，0代表座位可选，返回true，1代表座位已占，不可选，返回false*/
		char bit = '2';//标志字符
		switch (SeatType) {
		case "FIRSTCLASS":
			bit = first_seat.charAt(LinearID);
			break;
		case "BUSINESS":
			bit = business_seat.charAt(LinearID);
			break;
		case "ECONOMIC":
			bit = economic_seat.charAt(LinearID);
			break;
		}
		boolean flag = false;
		switch (bit) {
		case '0':
			flag = true;
			break;

		case '1':
			flag = false;
			break;
		}
		return flag;
	}

	/**
	 * 
	 * 占有某座位后，更新seat表的代表选座情况的16进制字符串
	 * 
	 * @param CurrentAid
	 *            当前航班号
	 *        SeatType
	 *            座位类型
	 *        CurrentSeat
	 *            被选座位号
	 * 
	 * @return void
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public void ProcessSeat_seatt(String CurrentAid, String SeatType, String CurrentSeat) {
		
		String plane_type = "";//该航班的机型
		String first_seat = "";//代表头等舱座位被选情况的字符串
		String business_seat = "";//代表商务舱座位被选情况的字符串
		String economic_seat = "";//代表经济舱座位被选情况的字符串
		int first_num = 0;//头等舱座位数
		int business_num = 0;//商务舱座位数
		int economic_num = 0;//经济舱座位数
		
		String sql = "";
		try {
			sql = "select  aircraft.first_num , aircraft.business_num , aircraft.economic_num   "
					+ "from airline inner join aircraft  " + " where airline.type  =  aircraft.type    "
					+ " and aid = ?   ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentAid);//取代占位符
			ResultSet rs = ps.executeQuery();//执行sql查询语句，内连接airline表和aircraft表，通过航班号获取该航班的座位参数

			while (rs.next()) {

				first_num = rs.getInt("aircraft.first_num");//获取头等舱座位数
				business_num = rs.getInt("aircraft.business_num");//获取商务舱座位数
				economic_num = rs.getInt("aircraft.economic_num");//获取经济舱座位数
			}

			rs.close();

			sql = "select plane_type , first_seat , business_seat , economic_seat from seat " + " where aid =  ?   ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentAid);//取代占位符
			rs = ps.executeQuery();//执行sql查询语句

			while (rs.next()) {
				plane_type = rs.getString("plane_type");//获取机型
				first_seat = rs.getString("first_seat");//获取代表头等舱选座情况的16进制字符串
				business_seat = rs.getString("business_seat");//获取代表商务舱选座情况的16进制字符串
				economic_seat = rs.getString("economic_seat");//获取代表经济舱选座情况的16进制字符串
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
		/*将代表选座情况的16进制字符串转为2进制字符串，转换后的2进制串可与线性化后的对应类型座位的座位号一一对应*/
		first_seat = bitTranslator.hex2bi(first_seat, first_num);//将代表头等舱选座情况的16进制字符串转为2进制字符串
		business_seat = bitTranslator.hex2bi(business_seat, business_num);//将代表商务舱选座情况的16进制字符串转为2进制字符串
		economic_seat = bitTranslator.hex2bi(economic_seat, economic_num);//将代表经济舱选座情况的16进制字符串转为2进制字符串

		int LinearID = SeatTranslator.trans_seat(plane_type, SeatType, CurrentSeat);//将当前座位号转化成线性编号LinearID

		/*修改代表对应类型座位选座情况的2进制串，将第LinearID位字符置1，说明已被占。新字符串的拼接方法要分LinearID是字符串末位/中间位两种情况讨论（因为若LinearID是末位，substring(LinearID + 1)将越界）*/
		switch (SeatType) {
		case "FIRSTCLASS":
			if (LinearID == first_num - 1) {
				first_seat = first_seat.substring(0, LinearID) + "1";
			} else {
				first_seat = first_seat.substring(0, LinearID) + "1" + first_seat.substring(LinearID + 1);
			}

			break;
		case "BUSINESS":
			if (LinearID == business_num - 1) {
				business_seat = business_seat.substring(0, LinearID) + "1";
			} else {
				business_seat = business_seat.substring(0, LinearID) + "1" + business_seat.substring(LinearID + 1);
			}

			break;
		case "ECONOMIC":
			if (LinearID == economic_num - 1) {
				economic_seat = economic_seat.substring(0, LinearID) + "1";
			} else {
				economic_seat = economic_seat.substring(0, LinearID) + "1" + economic_seat.substring(LinearID + 1);
			}

			break;
		}
		/*将代表选座情况的2进制字符串转为16进制字符串*/
		first_seat = bitTranslator.bi2hex(first_seat, first_num);
		business_seat = bitTranslator.bi2hex(business_seat, business_num);
		economic_seat = bitTranslator.bi2hex(economic_seat, economic_num);

		try {

			sql = " UPDATE seat SET first_seat = ? ,business_seat =? ,economic_seat = ?  " + " where aid = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, first_seat);//取代占位符
			ps.setString(2, business_seat);
			ps.setString(3, economic_seat);
			ps.setString(4, CurrentAid);

			int rs2 = ps.executeUpdate();//执行sql更新语句，将修改后的代表选座情况的16进制字符串存入seat表

			if (rs2 != 0) {
				System.out.println("成功更新airline表的座位数");

			} else {
				System.out.println("更新airline表的座位数失败");
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

	}

	/**
	 * 
	 * 占有某座位后，更新airline表的航班的可选座位数
	 * 
	 * @param CurrentAid
	 *            当前航班号
	 *        SeatType
	 *            座位类型
	 * 
	 * @return void
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public void ProcessSeat_airlinet(String CurrentAid, String SeatType) {

		int first_num = 0;//当前航班头等舱的可选座位数
		int business_num = 0;//当前航班商务舱的可选座位数
		int economic_num = 0;//当前航班经济舱的可选座位数
		
		String sql = "";
		try {

			sql = "select  first_num , business_num , economic_num from airline  where aid =  ?   ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentAid);//取代占位符
			rs = ps.executeQuery();//执行sql查询语句

			while (rs.next()) {

				first_num = rs.getInt("first_num");//获取当前航班头等舱的可选座位数
				business_num = rs.getInt("business_num");//获取当前航班商务舱的可选座位数
				economic_num = rs.getInt("economic_num");//获取当前航班经济舱的可选座位数
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

		/*减少被选舱位的可选座位数*/
		switch (SeatType) {
		case "FIRSTCLASS":
			first_num--;
			break;
		case "BUSINESS":
			business_num--;
			break;
		case "ECONOMIC":
			economic_num--;
			break;
		}
		try {

			sql = " UPDATE airline SET first_num = ? ,business_num =? ,economic_num = ?  " + " where aid = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setInt(1, first_num);//取代占位符
			ps.setInt(2, business_num);
			ps.setInt(3, economic_num);
			ps.setString(4, CurrentAid);

			int rs2 = ps.executeUpdate();//执行sql更新语句，更新航班的各舱位的可选座位数

			if (rs2 != 0) {
				System.out.println("成功更新airline表的座位数");

			} else {
				System.out.println("更新airline表的座位数失败");
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

	}

	/**
	 * 
	 * 取消选座后，更新seat表的代表选座情况的16进制字符串
	 * 
	 * @param CurrentAid
	 *            当前航班号
	 *        SeatType
	 *            座位类型
	 *        CurrentSeat
	 *            要取消的座位号
	 * 
	 * @return void
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public void GiveupSeat_seatt(String CurrentAid, String SeatType, String CurrentSeat) {
		String plane_type = "";//该航班的机型
		String first_seat = "";//代表头等舱座位被选情况的字符串
		String business_seat = "";//代表商务舱座位被选情况的字符串
		String economic_seat = "";//代表经济舱座位被选情况的字符串
		int first_num = 0;//头等舱座位数
		int business_num = 0;//商务舱座位数
		int economic_num = 0;//经济舱座位数
		
		String sql = "";
		try {
			sql = "select  aircraft.first_num , aircraft.business_num , aircraft.economic_num   "
					+ "from airline inner join aircraft  " + " where airline.type  =  aircraft.type    "
					+ " and aid = ?   ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentAid);//取代占位符
			rs = ps.executeQuery();//执行sql查询语句，内连接airline表和aircraft表，通过航班号获取该航班的座位参数

			while (rs.next()) {

				first_num = rs.getInt("aircraft.first_num");//获取头等舱座位数
				business_num = rs.getInt("aircraft.business_num");//获取商务舱座位数
				economic_num = rs.getInt("aircraft.economic_num");//获取经济舱座位数
			}

			rs.close();

			sql = "select plane_type , first_seat , business_seat , economic_seat from seat  where aid =  ?   ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentAid);//取代占位符
			rs = ps.executeQuery();//执行sql查询语句

			while (rs.next()) {
				plane_type = rs.getString("plane_type");//获取机型
				first_seat = rs.getString("first_seat");//获取代表头等舱选座情况的16进制字符串
				business_seat = rs.getString("business_seat");//获取代表商务舱选座情况的16进制字符串
				economic_seat = rs.getString("economic_seat");//获取代表经济舱选座情况的16进制字符串
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
		/*将代表选座情况的16进制字符串转为2进制字符串，转换后的2进制串可与线性化后的对应类型座位的座位号一一对应*/
		first_seat = bitTranslator.hex2bi(first_seat, first_num);//将代表头等舱选座情况的16进制字符串转为2进制字符串
		business_seat = bitTranslator.hex2bi(business_seat, business_num);//将代表商务舱选座情况的16进制字符串转为2进制字符串
		economic_seat = bitTranslator.hex2bi(economic_seat, economic_num);//将代表经济舱选座情况的16进制字符串转为2进制字符串

		int LinearID = SeatTranslator.trans_seat(plane_type, SeatType, CurrentSeat);//将当前座位号转化成线性编号LinearID

		/*修改代表对应类型座位选座情况的2进制串，将第LinearID位字符置0，说明可选。新字符串的拼接方法要分LinearID是字符串末位/中间位两种情况讨论（因为若LinearID是末位，substring(LinearID + 1)将越界）*/
		switch (SeatType) {
		case "FIRSTCLASS":
			if (LinearID == first_num) {
				first_seat = first_seat.substring(0, LinearID) + "0";
			} else {
				first_seat = first_seat.substring(0, LinearID) + "0" + first_seat.substring(LinearID + 1);
			}
			break;
		case "BUSINESS":
			if (LinearID == business_num) {
				business_seat = business_seat.substring(0, LinearID) + "0";
			} else {
				business_seat = business_seat.substring(0, LinearID) + "0" + business_seat.substring(LinearID + 1);
			}

			break;
		case "ECONOMIC":
			if (LinearID == business_num) {
				economic_seat = economic_seat.substring(0, LinearID) + "0";
			} else {
				economic_seat = economic_seat.substring(0, LinearID) + "0" + economic_seat.substring(LinearID + 1);
			}

			break;
		}
		
		/*将代表选座情况的2进制字符串转为16进制字符串*/
		first_seat = bitTranslator.bi2hex(first_seat, first_num);
		business_seat = bitTranslator.bi2hex(business_seat, business_num);
		economic_seat = bitTranslator.bi2hex(economic_seat, economic_num);

		try {

			sql = " UPDATE seat SET first_seat = ? ,business_seat =? ,economic_seat = ?   where aid = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, first_seat);//取代占位符
			ps.setString(2, business_seat);
			ps.setString(3, economic_seat);
			ps.setString(4, CurrentAid);

			int rs2 = ps.executeUpdate();//执行sql更新语句，将修改后的代表选座情况的16进制字符串存入seat表

			if (rs2 != 0) {
				System.out.println("成功更新airline表的座位数");

			} else {
				System.out.println("更新airline表的座位数失败");
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

	}

	/**
	 * 
	 * 取消选座后，更新airline表的航班的可选座位数
	 * 
	 * @param CurrentAid
	 *            当前航班号
	 *        SeatType
	 *            座位类型
	 * 
	 * @return void
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public void GiveupSeat_airlinet(String CurrentAid, String SeatType) {
		int first_num = 0;//当前航班头等舱的可选座位数
		int business_num = 0;//当前航班商务舱的可选座位数
		int economic_num = 0;//当前航班经济舱的可选座位数
		
		String sql = "";
		try {
			sql = "select  first_num , business_num , economic_num from airline  where aid =  ?   ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentAid);//取代占位符
			rs = ps.executeQuery();//执行sql查询语句

			while (rs.next()) {

				first_num = rs.getInt("first_num");//获取当前航班头等舱的可选座位数
				business_num = rs.getInt("business_num");//获取当前航班商务舱的可选座位数
				economic_num = rs.getInt("economic_num");//获取当前航班经济舱的可选座位数
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

		/*增加被选舱位的可选座位数*/
		switch (SeatType) {
		case "FIRSTCLASS":
			first_num++;
			break;
		case "BUSINESS":
			business_num++;
			break;
		case "ECONOMIC":
			economic_num++;
			break;

		}
		try {

			sql = " UPDATE airline SET first_num = ? ,business_num =? ,economic_num = ?  " + " where aid = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setInt(1, first_num);//取代占位符
			ps.setInt(2, business_num);
			ps.setInt(3, economic_num);
			ps.setString(4, CurrentAid);

			int rs2 = ps.executeUpdate();//执行sql更新语句，更新航班的各舱位的可选座位数

			if (rs2 != 0) {
				System.out.println("成功更新airline表的座位数");

			} else {
				System.out.println("更新airline表的座位数失败");
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

	}
	

}
