package DBBasic;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import Tools.SeatTranslator;
import com.ibm.icu.text.DecimalFormat;

/**
 * 
 * 提供客户界面所需的功能，如：查航班、定航班、取消航班、打印个人行程、修改个人信息
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 主要函数清单：
 * bookTicket(String, String, String, String)
 * cancelTicket(String)
 * getAircraft(String)
 * getAirline(int, String, String)
 * printSchedule(String)
 * profileUpdate_idn(String, String)
 * profileUpdate_name(String, String)
 * profileUpdate_phone(String, String)
 * 
 */

public class DBHandler {

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
	public DBHandler() {
		handler = new DBUtil();
		handler.getConn("root", "127127");
		conn = handler.conn;
		ps = null;
	}

	/**
	 * 
	 * 给定日期，起点，终点，查询航班
	 * 查找airline表
	 * 优先查询匹配的直达航班，否则查询匹配的转机组合
	 * 
	 * @param CurrentDate
	 *            日期
	 *        CurrentOrigin
	 *            起点
	 *        CurrentDestination
	 *            终点
	 * 
	 * @return String 
	 *         2：没有匹配信息
	 *         0+航班信息：有匹配的直达航班
	 *         1+航班信息：有匹配的转机组合
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String getAirline(int CurrentDate, String CurrentOrigin, String CurrentDestination) {
		String result = "";
		String sql = "";
		try {
			/*查找直达航班*/
			sql = "select * from airline  " + "where airline.date = ?  " + "and airline.origin = ?  "
					+ "and airline.destination = ?  ";
			
			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setInt(1, CurrentDate);//取代占位符
			ps.setString(2, CurrentOrigin);
			ps.setString(3, CurrentDestination);

			ResultSet rs = ps.executeQuery();//执行sql查询语句

			String aid = "";//航班号
			String origin = "";//起点
			String destination = "";//终点
			int date = 0;//日期
			int hour = 0;//时
			int minute = 0;//分
			int first_num = 0;//头等舱可选座位数
			int business_num = 0;//商务舱可选座位数
			int economic_num = 0;//经济舱可选座位数
			String type = "";//机型

			int count = 0;
			while (rs.next()) {
				count++;
				aid = rs.getString("aid");//获取航班号
				origin = rs.getString("origin");//获取起点
				destination = rs.getString("destination");//获取终点
				date = rs.getInt("date");//获取日期
				hour = rs.getInt("hour");//获取时
				minute = rs.getInt("minute");//获取分
				first_num = rs.getInt("first_num");//获取头等舱可选座位数
				business_num = rs.getInt("business_num");//获取商务舱可选座位数
				economic_num = rs.getInt("economic_num");//获取经济舱可选座位数
				type = rs.getString("type");//获取机型
				
				/*拼接携带航班信息的字符串*/
				result += "-" + count + "-" + aid + "-" + origin + "-" + destination + "-" + date + "-" + hour + ":"
						+ minute + "-" + first_num + "-" + business_num + "-" + economic_num + "-" + type;

			}
			/*编辑返回字符串*/
			if (count != 0) {
				result = result.substring(1);
				String count_str = new DecimalFormat("00").format(count);//将数字处理成两位字符串
				result = "0" + count_str + result;
			} else {
				/*如果没有直航航班，检查是否存在二元航班组合满足查询条件*/
				sql = "select * from airline as A inner join airline as B   " + "on A.destination=B.origin  "
						+ "and A.date = ?  " + "and B.date = ?  " + "and A.hour < B.hour " + "and A.origin = ?  "
						+ "and B.destination = ?  ";
				
				ps = conn.prepareStatement(sql);//创建prepareStatement语句
				ps.setInt(1, CurrentDate);//取代占位符
				ps.setInt(2, CurrentDate);
				ps.setString(3, CurrentOrigin);
				ps.setString(4, CurrentDestination);

				String aid2 = "";//航班号
				String origin2 = "";//起点
				String destination2 = "";//终点
				int date2 = 0;//日期
				int hour2 = 0;//时
				int minute2 = 0;//分
				int first_num2 = 0;//头等舱可选座位数
				int business_num2 = 0;//商务舱可选座位数
				int economic_num2 = 0;//经济舱可选座位数
				String type2 = "";//机型

				rs = ps.executeQuery();//执行sql查询语句

				while (rs.next()) {
					count++;
					aid = rs.getString("A.aid");//获取组合中第一班航班的航班号
					origin = rs.getString("A.origin");//获取组合中第一班航班的起点
					destination = rs.getString("A.destination");//获取组合中第一班航班的终点
					// name = new String(name.getBytes("ISO-8859-1"), "GB2312");
					date = rs.getInt("A.date");//获取组合中第一班航班的日期
					hour = rs.getInt("A.hour");//获取组合中第一班航班的出发时点
					minute = rs.getInt("A.minute");//获取组合中第一班航班的出发分钟
					first_num = rs.getInt("A.first_num");//获取组合中第一班航班的头等舱可选座位数
					business_num = rs.getInt("A.business_num");//获取组合中第一班航班的商务舱可选座位数
					economic_num = rs.getInt("A.economic_num");//获取组合中第一班航班的经济舱可选座位数
					type = rs.getString("A.type");//获取组合中第一班航班的机型

					aid2 = rs.getString("B.aid");//获取组合中第二班航班的航班号
					origin2 = rs.getString("B.origin");//获取组合中第二班航班的起点
					destination2 = rs.getString("B.destination");//获取组合中第二班航班的终点
					// name = new String(name.getBytes("ISO-8859-1"), "GB2312");
					date2 = rs.getInt("B.date");//获取组合中第二班航班的日期
					hour2 = rs.getInt("B.hour");//获取组合中第二班航班的出发时点
					minute2 = rs.getInt("B.minute");//获取组合中第二班航班的出发分钟
					first_num2 = rs.getInt("B.first_num");//获取组合中第二班航班的头等舱可选座位数
					business_num2 = rs.getInt("B.business_num");//获取组合中第二班航班的商务舱可选座位数
					economic_num2 = rs.getInt("B.economic_num");//获取组合中第二班航班的经济舱可选座位数
					type2 = rs.getString("B.type");//获取组合中第二班航班的机型
					
					/*拼接携带组合中第一班航班信息的字符串*/
					result += "-" + count + "-" + aid + "-" + origin + "-" + destination + "-" + date + "-" + hour + ":"
							+ minute + "-" + first_num + "-" + business_num + "-" + economic_num + "-" + type;

					/*拼接携带组合中第二班航班信息的字符串*/
					result += "-" + count + "-" + aid2 + "-" + origin2 + "-" + destination2 + "-" + date2 + "-" + hour2
							+ ":" + minute2 + "-" + first_num2 + "-" + business_num2 + "-" + economic_num2 + "-"
							+ type2;


				}
				/*如果结果集为空，没有匹配组合，返回2*/
				if (count == 0) {
					System.out.println("没有匹配信息");
					result = "2";
				} else {/*如果结果集不为空，编辑返回字符串*/	
					result = result.substring(1);
					String count_str = new DecimalFormat("00").format(count);//将数字处理成两位字符串
					result = "1" + count_str + result;
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
		return result;
	}

	/**
	 * 
	 * 订航班
	 * orders表的插入
	 * 
	 * @param cid
	 *            客户ID
	 *        aid
	 *            航班号
	 *        seat_type
	 *            舱位
	 *        seatid
	 *            座位号
	 * 
	 * @return String 
	 *         1：客户id无效
	 *         0：航班号无效
	 *         2：座位类型不合法
	 *         3：所选座位不可选
	 *         5+订单号：下单成功
	 *         8：下单失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String bookTicket(String cid, String aid, String seat_type, String seatid) {
		
		String result = "";
		/*检查客户id是否有效*/
		if (!handler.checkC_exist(cid)) {
			System.out.println("用户账号不存在，请检查后再输入");
			return "1";
		}
		/*检查航班号是否有效*/
		if (!handler.checkA_exist(aid)) {
			System.out.println("航班不存在，请检查后再输入");
			return "0";
		}
		/*检查所选舱位是否合法*/
		if (!(seat_type.equals("ECONOMIC") || seat_type.equals("BUSINESS") || seat_type.equals("FIRSTCLASS"))) {
			System.out.println("座位类型错误，请检查后再输入");
			return "2";
		}
		/*检查所选座位号是否可选*/
		if (!handler.checkSeat(aid, seat_type, seatid)) {
			System.out.println("座位ID错误，请检查后再输入");
			return "3";
		}
		
		String orderid = java.util.UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();//随机生成唯一订单号
		String did = handler.randomDeliveryman();//随机派遣送票员
		String sid = handler.getBusiSalesman(cid);//获取该客户所对应的业务员
		String sql = "";

		try {

			sql = " INSERT INTO orders VALUES ( ? , ?  ,?  ,? ,?  ,?  ,?  )  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, orderid);//取代占位符
			ps.setString(2, aid);
			ps.setString(3, cid);
			ps.setString(4, seat_type);
			ps.setString(5, seatid);
			ps.setString(6, sid);
			ps.setString(7, did);

			int rs1 = ps.executeUpdate();//执行sql插入语句

			/*若插入成功，返回5和订单号，否则，返回8*/
			if (rs1 != 0) {
				System.out.println("成功插入");
				result = "5" + orderid;
			} else {
				System.out.println("错误，请重试");
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
		handler.ProcessSeat_seatt(aid, seat_type, seatid);//占有该座位，修改seat表代表选座情况的字符串
		handler.ProcessSeat_airlinet(aid, seat_type);//占有该座位，修改airline表的可选座位数
		return result;
	}
	
	/**
	 * 
	 * 获取机型(订票选座位时需要)
	 * 
	 * @param CurrentAid
	 *           航班号
	 * 
	 * @return String 
	 *         0：航班号无效
	 *         1+机型：查询成功
	 *         8：查询失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String getAircraft(String CurrentAid) {
		String result = "";
		/*检查航班号是否有效*/
		if (!handler.checkA_exist(CurrentAid)) {
			System.out.println("航班不存在，请检查后再输入");
			return "0";
		}

		String type = "";//当前航班号所对应航班的机型
		
		String sql = "";
		try {
			
			sql = "select type from airline  where aid = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentAid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查询语句

			/*如果结果不为空，返回1和机型；否则，返回8*/
			if (rs.next()) {
				type = rs.getString("type");//获取机型
				result = "1" + type;//编辑返回字符串
			} else {
				result = "8";
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
	 * 取消订票
	 * 
	 * @param CurrentOrderid
	 *            订单号     
	 * 
	 * @return String 
	 *         0：订单号无效
	 *         1：取消订单成功
	 *         8：取消订单失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String cancelTicket(String CurrentOrderid) {
		String result = "";
		/*查询订单号是否有效*/
		if (!handler.checkOrder_exist(CurrentOrderid)) {
			System.out.println("该订单不存在，请检查后再输入");
			return "0";
		}
		
		String sql = "";
		String seat_type = "";//该订单所选的舱位
		String seat_id = "";//该订单所选的座位号
		String aid = "";//该订单所选的航班号
		try {
			/*获取订单信息（舱位、座位号、航班号），用于修改seat表和airline表的座位选择情况*/
			sql = "select aid , seat_type , seat_id  from orders  where orderid = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentOrderid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查询语句

			while (rs.next()) {

				seat_type = rs.getString("seat_type");//获取该订单所选的舱位
				seat_id = rs.getString("seat_id");//获取该订单所选的座位号
				aid = rs.getString("aid");//获取该订单所选的航班号

			}
			handler.GiveupSeat_airlinet(aid, seat_type);//更新airline表中的可选座位数
			handler.GiveupSeat_seatt(aid, seat_type, seat_id);//更新seat表中的选座情况
			
			/*从orders表中删除该订单*/
			sql = " DELETE FROM orders WHERE orderid =?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentOrderid);//取代占位符

			int rs2 = ps.executeUpdate();//执行sql删除语句

			/*若成功删除订单，返回1，否则返回8*/
			if (rs2 != 0) {
				System.out.println("成功删除订单");
				result = "1";
			} else {
				System.out.println("错误，请重试");
				result = "8";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();//取消prepareStatement语句
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * 打印客户个人行程
	 * 
	 * @param CurrentCid
	 *            客户ID 
	 * 
	 * @return String 
	 *         0：客户id无效
	 *         4：没有行程
	 *         3+行程信息：搜索成功
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String printSchedule(String CurrentCid) {
		String result = "";
		
		/*检查客户id是否有效*/
		if (!handler.checkC_exist(CurrentCid)) {
			System.out.println("用户账号不存在，请检查后再输入");
			return "0";
		}
		
		String sql = "";
		try {

			String orderid = "";//订单号
			String aid = "";//航班号
			String seat_type = "";//舱位
			String seat_id = "";//座位号
			String sid = "";//业务员id
			String did = "";//送票员id
			int date = 0;//航班出发日期
			int hour = 0;//航班出发时点
			int minute = 0;//航班出发分钟

			sql = "select * from orders inner join airline  where cid = ?  and orders.aid = airline.aid ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentCid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查询语句，内连接orders表和airline表，获取该用户所下订单的信息

			int count = 0;//记录当前是第几条结果
			while (rs.next()) {
				count++;
				orderid = rs.getString("orderid");//获取订单号
				aid = rs.getString("orders.aid");//获取航班号
				seat_type = rs.getString("seat_type");//获取舱位
				seat_id = rs.getString("seat_id");//获取座位号
				sid = rs.getString("sid");//获取业务员id
				did = rs.getString("did");//获取送票员id
				date = rs.getInt("date");//获取航班出发日期
				hour = rs.getInt("hour");//获取航班出发时点
				minute = rs.getInt("minute");//获取航班出发分钟
				/*编辑信息字符串*/
				result += "-" + (count) + "-" + orderid + "-" + aid + "-" + date + "-" + hour + ":" + minute + "-"
						+ seat_type + "-" + seat_id + "-" + sid + "-" + did;

			}
			/*如果没有结果，返回4*/
			if (count == 0) {
				System.out.println("没有行程");
				result = "4";
			} else {
				/*如果没有结果不为空，编辑返回字符串*/
				System.out.println("共有 " + count + " 条信息");
				result = result.substring(1);
				String count_str = new DecimalFormat("00").format(count);//将数字处理成两位字符串
				result = "3" + count_str + result;
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
	 * 修改姓名
	 * 
	 * @param CurrentCid
	 *            客户ID
	 *        CurrentName
	 *            新名字
	 * 
	 * @return String 
	 *         2：客户id无效
	 *         3：修改成功
	 *         8：修改失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String profileUpdate_name(String CurrentCid, String CurrentName) {
		String result = "";
		/*检查客户id是否有效*/
		if (!handler.checkC_exist(CurrentCid)) {
			System.out.println("客户ID不存在，请检查后输入");
			return "2";
		}

		try {
			String sql = "";
			sql = "update  client  set  name= ?  where cid = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentName);//取代占位符
			ps.setString(2, CurrentCid);

			int rs = ps.executeUpdate();//执行sql更新语句

			/*或修改成功，返回3，否则，返回8*/
			if (rs != 0) {
				System.out.println("成功修改信息");
				result = "3";
			} else {
				System.out.println("错误，请重试");
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
	 * 修改身份证号
	 * 
	 * @param CurrentCid
	 *            客户ID
	 *        CurrentIDNum
	 *            新身份证号
	 * 
	 * @return String 
	 *         2：客户id无效
	 *         0：新身份证已被注册
	 *         3：修改成功
	 *         8：修改失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String profileUpdate_idn(String CurrentCid, String CurrentIDNum) {

		String result = "";
		
		/*检查客户id是否有效*/
		if (!handler.checkC_exist(CurrentCid)) {
			System.out.println("客户ID不存在，请检查后输入");
			return "2";
		}
		/*检查新身份证号是否被别人使用过（若输入的新身份证号与旧身份证号相同，不报错）*/
		if (handler.checkIDNDupExceptSelf(CurrentIDNum, CurrentCid)) {
			System.out.println("新身份证号已注册，修改个人信息失败，请重试");
			return "0";
		}

		String sql = "";
		try {
			
			sql = "update  client  set identity_number= ?  where cid = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句

			ps.setString(1, CurrentIDNum);//取代占位符

			ps.setString(2, CurrentCid);

			int rs = ps.executeUpdate();//执行sql更新语句

			/*或修改成功，返回3，否则，返回8*/
			if (rs != 0) {
				System.out.println("成功修改信息");
				result = "3";
			} else {
				System.out.println("错误，请重试");
				result = "8";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();//取消prepareStatement语句
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * 修改手机号
	 * 
	 * @param CurrentCid
	 *            客户ID
	 *        CurrentPhone
	 *            新手机号
	 * 
	 * @return String 
	 *         2：客户id无效
	 *         0：新手机号已被注册
	 *         3：修改成功
	 *         8：修改失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String profileUpdate_phone(String CurrentCid, String CurrentPhone) {

		String result = "";
		/*检查客户id是否有效*/
		if (!handler.checkC_exist(CurrentCid)) {
			System.out.println("客户ID不存在，请检查后输入");
			return "2";
		}
		/*检查新手机号是否被别人使用过（若输入的新手机号与旧手机号相同，不报错）*/
		if (handler.checkPhoneDupExceptSelf(CurrentPhone, CurrentCid)) {
			System.out.println("该手机号已注册，修改个人信息失败，请重试");
			return "1";
		}

		try {
			String sql = "";
			sql = "update  client  set phone= ?  where cid = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句

			ps.setString(1, CurrentPhone);//取代占位符

			ps.setString(2, CurrentCid);

			int rs = ps.executeUpdate();//执行sql更新语句

			/*或修改成功，返回3，否则，返回8*/
			if (rs != 0) {
				System.out.println("成功修改信息");
				result = "3";
			} else {
				System.out.println("错误，请重试");
				result = "8";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();//取消prepareStatement语句
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	

}
