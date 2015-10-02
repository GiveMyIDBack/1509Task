package DBBasic;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import Tools.SeatMaker;
import Tools.bitTranslator;

import com.ibm.icu.text.DecimalFormat;

/**
 * 
 * 提供管理员界面额外需要的功能，如：增删航班、增删售票点、增删业务员送票员操作员、打印客户报表、打印业务员业绩报表
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 主要函数清单：
 * addAirline(String, String, String, int, int, int, String)
 * addDeliveryman(String, String)
 * addEmployment_t2d(String, String)
 * addEmployment_t2s(String, String)
 * addOperator(String)
 * addSalesman(String, String)a
 * ddTicketBox(String, String)
 * cancelAirline(String)
 * cancelDeliveryman(String)
 * cancelEmployment_t2d(String, String)
 * cancelEmployment_t2s(String, String)
 * cancelOperator(String)
 * cancelSalesman(String)
 * cancelTicketBox(String)
 * printClientChart()
 * printSalesmanChart(String)
 * 
 */
public class DBHandlerPro {

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
	public DBHandlerPro() {
		handler = new DBUtil();
		handler.getConn("root", "127127");
		conn = handler.conn;
		ps = null;
	}

	/**
	 * 
	 * 添加航班
	 * 
	 * @param CurrentAid 
	 *            航班号
	 *        CurrentOrigin
	 *            起点 
	 *        CurrentDest
	 *            终点
	 *        CurrentDate
	 *            日期
	 *        CurrentHour
	 *            出发时点
	 *        CurrentMinute
	 *            出发分钟
	 *        CurrentType
	 *            机型
	 * 
	 * @return String 
	 *         2：航班号已存在
	 *         0：机型不存在
	 *         1：成功
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
	public String addAirline(String CurrentAid, String CurrentOrigin, String CurrentDest, int CurrentDate,
			int CurrentHour, int CurrentMinute, String CurrentType) {
		
		String result = "";
		
		/*检查航班号是否已存在*/
		if (handler.checkA_exist(CurrentAid)) {
			System.out.println("航班号已存在，请检查后再输入");
			return "2";
		}
		/*检查机型是否有效*/
		if (!handler.checkTYPE_exist(CurrentType)) {
			System.out.println("机型不存在，请检查后再输入");
			return "0";
		}

		int[] seat_data = handler.getSeat(CurrentType);//获取该机型航班各个舱位的座位数
		int CurrentFirNum = seat_data[0];
		int CurrentBusiNum = seat_data[1];
		int CurrentEcoNum = seat_data[2];

		/*创建代表各舱位选座情况的八进制字符串*/
		String first_seat = bitTranslator.bi2hex(SeatMaker.MakeSeatTable(CurrentFirNum), CurrentFirNum);
		String business_seat = bitTranslator.bi2hex(SeatMaker.MakeSeatTable(CurrentBusiNum), CurrentBusiNum);
		String economic_seat = bitTranslator.bi2hex(SeatMaker.MakeSeatTable(CurrentEcoNum), CurrentEcoNum);

		String sql = "";
		
		
		try {
			/*向airline表插入新航班*/
			sql = " INSERT INTO airline VALUES ( ? , ?  ,?  ,? ,?  ,?  ,?  ,?  ,? ,?  )  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentAid);//取代占位符
			ps.setString(2, CurrentOrigin);
			ps.setString(3, CurrentDest);
			ps.setInt(4, CurrentDate);
			ps.setInt(5, CurrentHour);
			ps.setInt(6, CurrentMinute);
			ps.setInt(7, CurrentFirNum);
			ps.setInt(8, CurrentBusiNum);
			ps.setInt(9, CurrentEcoNum);
			ps.setString(10, CurrentType);

			int rs = ps.executeUpdate();//执行sql插入语句

			/*向seat表插入新航班的代表选座情况的16进制串*/
			sql = " INSERT INTO seat VALUES ( ? , ?  ,?  ,?  , ?  )  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentAid);//取代占位符
			ps.setString(2, CurrentType);
			ps.setString(3, first_seat);
			ps.setString(4, business_seat);
			ps.setString(5, economic_seat);

			int rs2 = ps.executeUpdate();//执行sql插入语句

			/*若成功插入，返回1，否则，返回8*/
			if (rs != 0 && rs2 != 0) {
				System.out.println("成功插入 新航班");
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
					ps.close();//关闭prepareStatement语句
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * 取消航班
	 * 
	 * @param CurrentAid 
	 *            航班号
	 *        
	 * 
	 * @return String 
	 *         0：航班号不存在
	 *         1：成功
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
	public String cancelAirline(String CurrentAid) {
		String result = "";
		/*检查航班号是否有效*/
		if (!handler.checkA_exist(CurrentAid)) {
			System.out.println("航班不存在，请检查后再输入");
			return "0";
		}
		String sql = "";
		
		try {
			/*从airline表删除航班*/
			sql = " DELETE FROM airline WHERE aid =?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentAid);//取代占位符

			int rs = ps.executeUpdate();//执行sql删除语句

			/*从seat表种，删除该航班的选座情况记录*/
			sql = " DELETE FROM seat WHERE aid =?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentAid);//取代占位符

			int rs2 = ps.executeUpdate();//执行sql删除语句

			/*若成功，返回1，否则，返回8*/
			if (rs != 0 && rs2 != 0) {
				System.out.println("成功删除航班");
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
					ps.close();//关闭prepareStatement语句
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * 添加业务员
	 * 
	 * @param CurrentName
	 *            业务员姓名
	 *        CurrentTid
	 *            售票点id
	 *        
	 * 
	 * @return String 
	 *         3：售票点不存在
	 *         0+业务员id：成功
	 *         1：失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String addSalesman(String CurrentName, String CurrentTid) {
		if (!handler.checkT_exist(CurrentTid)) {
			System.out.println("售票点ID不存在，请检查后输入");
			return "3";
		}
		String result = "";
		String sql = "";
		String CurrentSid = "S" + handler.getAndUpdateTail("sid");//生成新业务员id
		try {

			/*向salesman表添加新业务员记录*/
			sql = " INSERT INTO salesman VALUES ( ? , ?  )  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentSid);//取代占位符
			ps.setString(2, CurrentName);

			int rs = ps.executeUpdate();//执行sql插入语句

			/*向售票点与业务员的雇佣关系表中添加雇佣关系记录*/
			sql = " INSERT INTO employment_t2s VALUES ( ? , ?  )  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentTid);//取代占位符
			ps.setString(2, CurrentSid);

			int rs2 = ps.executeUpdate();//执行sql插入语句

			/*若插入成功，返回0和新业务员id，否额返回1*/
			if (rs != 0 && rs2 != 0) {
				System.out.println("成功插入 新销售员及雇佣关系");
				result = "0" + CurrentSid;
			} else {
				System.out.println("错误，请重试");
				result = "1";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();
				// if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * 删除业务员
	 * 
	 * @param CurrentSid
	 *           业务员id
	 *        
	 * 
	 * @return String 
	 *         2：业务员不存在
	 *         0：成功
	 *         1：失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String cancelSalesman(String CurrentSid) {
		String result = "";
		/*检查业务员id是否存在*/
		if (!handler.checkS_exist(CurrentSid)) {
			System.out.println("业务员不存在，请检查后再输入");
			return "2";
		}
		String sql = "";
		try {

			sql = " DELETE FROM salesman WHERE sid =?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentSid);//取代占位符

			int rs = ps.executeUpdate();//执行sql删除语句

			/*若删除成功，返回0，否则返回1*/
			if (rs != 0) {
				System.out.println("成功删除销售员");
				result = "0";
			} else {
				System.out.println("错误，请重试");
				result = "1";
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
	 * 添加送票员
	 * 
	 * @param CurrentName
	 *            送票员姓名
	 *        CurrentTid
	 *            售票点id
	 *        
	 * 
	 * @return String 
	 *         3：售票点不存在
	 *         0+送票员id：成功
	 *         1：失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String addDeliveryman(String CurrentName, String CurrentTid) {
		String result = "";
		
		/*检查售票点id是否有效*/
		if (!handler.checkT_exist(CurrentTid)) {
			System.out.println("售票点ID不存在，请检查后输入");
			return "3";
		}
		
		String sql = "";
		
		String CurrentDid = "D" + handler.getAndUpdateTail("did");//生成新送票员id
		try {

			/*向deliveryman表中插入新送票员记录*/
			sql = " INSERT INTO deliveryman VALUES ( ? , ?  )  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentDid);//取代占位符
			ps.setString(2, CurrentName);

			int rs = ps.executeUpdate();//执行sql插入语句

			/*向售票点和送票员的雇佣关系表中插入雇佣关系*/
			sql = " INSERT INTO employment_t2d VALUES ( ? , ?  )  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentTid);//取代占位符
			ps.setString(2, CurrentDid);

			int rs2 = ps.executeUpdate();//执行sql插入语句

			/*若插入成功，返回0和送票员id，否则返回1*/
			if (rs != 0) {
				System.out.println("成功插入 新送票员及相关雇佣关系");
				result = "0" + CurrentDid;
			} else {
				System.out.println("错误，请重试");
				result = "1";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();
				// if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * 删除送票员
	 * 
	 * @param CurrentDid
	 *           送票员id
	 *        
	 * 
	 * @return String 
	 *         2：送票员不存在
	 *         0：成功
	 *         1：失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String cancelDeliveryman(String CurrentDid) {
		String result = "";
		/*检查送票员id是否存在*/
		if (!handler.checkD_exist(CurrentDid)) {
			System.out.println("送票员不存在，请检查后再输入");
			return "2";
		}
		String sql = "";
		
		try {

			sql = " DELETE FROM deliveryman WHERE did =?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentDid);//取代占位符

			int rs = ps.executeUpdate();//执行sql删除语句

			/*若删除成功，返回0，否则，返回1*/
			if (rs != 0) {
				System.out.println("成功删除销售员");
				result = "0";
			} else {
				System.out.println("错误，请重试");
				result = "1";
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
	 * 添加售票点
	 * 
	 * @param CurrentAdd
	 *            售票点地址
	 *        CurrentName
	 *            售票点名称
	 *        
	 * 
	 * @return String 
	 *         0+售票点id：成功
	 *         1：失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String addTicketBox(String CurrentAdd, String CurrentName) {
		String result = "";
		
		String sql = "";
		
		String CurrentTid = "T" + handler.getAndUpdateTail("tid");//生成新售票点id
		try {

			sql = " INSERT INTO ticketbox VALUES ( ? , ? , ?  )  ";
			
			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentTid);//取代占位符
			ps.setString(2, CurrentAdd);
			ps.setString(3, CurrentName);

			int rs = ps.executeUpdate();//执行sql插入语句

			/*若插入成功，返回0；否则，返回1*/
			if (rs != 0) {
				System.out.println("成功插入 新售票处");
				result = "0" + CurrentTid;
			} else {
				System.out.println("错误，请重试");
				result = "1";
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
	 * 删除售票点
	 * 
	 * @param CurrentTid
	 *           售票点id
	 *        
	 * 
	 * @return String 
	 *         2：售票点不存在
	 *         0：成功
	 *         1：失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String cancelTicketBox(String CurrentTid) {
		String result = "";
		/*检查售票点是否存在*/
		if (!handler.checkT_exist(CurrentTid)) {
			System.out.println("售票点不存在，请检查后再输入");
			return "2";
		}
		
		String sql = "";
		try {

			sql = " DELETE FROM ticketbox WHERE tid =?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentTid);//取代占位符

			int rs = ps.executeUpdate();//执行sql删除语句

			/* 删除与该ticketbox相关的售票点与业务员雇佣关系*/
			sql = " DELETE FROM employment_t2s WHERE tid =? ";

			ps = conn.prepareStatement(sql);
			ps.setString(1, CurrentTid);

			/*删除与该ticketbox相关的售票点与送票员雇佣关系*/
			int rs1 = ps.executeUpdate();

			sql = " DELETE FROM employment_t2d WHERE tid =? ";

			ps = conn.prepareStatement(sql);
			ps.setString(1, CurrentTid);

			int rs2 = ps.executeUpdate();

			/*若删除成功，返回0，否则，返回1*/
			if (rs != 0) {
				System.out.println("成功删除售票点与其相关雇佣关系");
				result = "0";
			} else {
				System.out.println("错误，请重试");
				result = "1";
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
	 * 添加售票点与业务员的雇佣关系
	 * 
	 * @param CurrentTid
	 *            售票点id
	 *        CurrentSid
	 *            业务员id
	 *        
	 * 
	 * @return String 
	 *         0+业务员id：成功
	 *         1：失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public void addEmployment_t2s(String CurrentTid, String CurrentSid) {
		
		String sql = "";

		try {
			/*向售票点与业务员之间的雇佣关系表中添加新记录*/
			sql = " INSERT INTO employment_t2s VALUES ( ? , ?  )  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentTid);//取代占位符
			ps.setString(2, CurrentSid);

			int rs = ps.executeUpdate();//执行sql插入语句

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
	 * 删除售票点与业务员的雇佣关系
	 * 
	 * @param CurrentTid
	 *            售票点id
	 *        CurrentSid
	 *            业务员id
	 *        
	 * 
	 * @return void
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public void cancelEmployment_t2s(String CurrentTid, String CurrentSid) {
		String sql = "";
		
		try {


			sql = " DELETE FROM employment_t2s WHERE tid =? ,sid= ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentTid);//取代占位符
			ps.setString(2, CurrentSid);

			int rs = ps.executeUpdate();//执行sql删除语句

			if (rs != 0)
				System.out.println("成功删除雇佣关系");
			else
				System.out.println("错误，请重试");

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
	 * 添加售票点与送票员的雇佣关系
	 * 
	 * @param CurrentTid
	 *            售票点id
	 *        CurrentDid
	 *            送票员id
	 *        
	 * 
	 * @return String 
	 *         0+送票员id：成功
	 *         1：失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public void addEmployment_t2d(String CurrentTid, String CurrentDid) {
		String sql = "";
		try {

			/*向售票点与送票员之间的雇佣关系表中添加记录*/
			sql = " INSERT INTO employment_t2d VALUES ( ? , ?  )  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentTid);//取代占位符
			ps.setString(2, CurrentDid);

			int rs = ps.executeUpdate();//执行sql插入语句

			/*若插入成功，输出成功提示；否则，报错*/
			if (rs != 0)
				System.out.println("成功插入 新雇佣关系");
			else
				System.out.println("错误，请重试");

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
	 * 删除售票点与送票员的雇佣关系
	 * 
	 * @param CurrentTid
	 *            售票点id
	 *        CurrentDid
	 *            送票员id
	 *        
	 * 
	 * @return void
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public void cancelEmployment_t2d(String CurrentTid, String CurrentDid) {
		
		String sql = "";
		try {

			sql = " DELETE FROM employment_t2d WHERE tid =? ,did= ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentTid);//取代占位符
			ps.setString(2, CurrentDid);

			int rs = ps.executeUpdate();//执行sql删除语句

			if (rs != 0)
				System.out.println("成功删除雇佣关系");
			else
				System.out.println("错误，请重试");

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
	 * 添加操作员
	 * 
	 * @param CurrentPassword
	 *            密码
	 *        
	 * 
	 * @return String 
	 *         0+操作员id：成功
	 *         1：失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String addOperator(String CurrentPassword) {
		String result = "";
		String sql = "";
		
		String CurrentOid = "O" + handler.getAndUpdateTail("oid");//生成新操作员id
		try {

			/*插入operator表新记录*/
			sql = " INSERT INTO operator VALUES ( ? , ?  )  ";
			
			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentOid);//取代占位符
			ps.setString(2, CurrentPassword);

			int rs = ps.executeUpdate();//执行sql插入语句

			/*若插入成功，返回0和新操作员id，否则，返回1*/
			if (rs != 0) {
				System.out.println("成功插入 新操作员");
				result = "0" + CurrentOid;
			} else {
				System.out.println("错误，请重试");
				result = "1";
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
	 * 删除操作员
	 * 
	 * @param CurrentOid
	 *           操作员id
	 *        
	 * 
	 * @return String 
	 *         2：操作员不存在
	 *         0：成功
	 *         1：失败
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String cancelOperator(String CurrentOid) {
		String result = "";
		/*检查该操作员id是否存在*/
		if (!handler.checkO_exist(CurrentOid)) {
			System.out.println("操作员不存在，请检查后再输入");
			return "2";
		}
		String sql = "";

		try {

			sql = " DELETE FROM operator WHERE oid =?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentOid);//取代占位符

			int rs = ps.executeUpdate();//执行sql删除语句

			/*若删除成功，返回0，否则返回1*/
			if (rs != 0) {
				System.out.println("成功删除操作员");
				result = "0";
			} else {
				System.out.println("错误，请重试");
				result = "1";
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
	 * 打印业务员业绩报表
	 * 
	 * @param CurrentSid
	 *           业务员id
	 *        
	 * 
	 * @return String 
	 *         1：业务员不存在
	 *         2：没有业绩
	 *         3+业绩信息：该业务员的业绩
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String printSalesmanChart(String CurrentSid) {
		String result = "";
		/*检查业务员id是否有效*/
		if (!handler.checkS_exist(CurrentSid)) {
			System.out.println("业务员ID不存在，请检查后再输入");
			return "1";
		}
		
		String sql = "";
		try {
			String orderid = "";//订单号
			String aid = "";//航班号
			String cid = "";//客户id

			
			sql = "select * from orders where sid = ?  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句
			ps.setString(1, CurrentSid);//取代占位符

			ResultSet rs = ps.executeQuery();//执行sql查询语句，查找含有该业务员id的订单

			int count = 0;//第几条记录
			while (rs.next()) {
				count++;
				orderid = rs.getString("orderid");//获取订单号
				cid = rs.getString("cid");//获取客户id
				aid = rs.getString("aid");//获取航班号

				/*拼接订单信息串*/
				result += "-" + (count) + "-" + orderid + "-" + cid + "-" + aid;

			}
			/*如果结果集为空，该业务员没有业绩*/
			if (count == 0) {
				result = "2";
				System.out.println("无订单");
			} else {
				/*如果结果集不为空，编辑返回字符串*/
				result = result.substring(1);
				String count_str = new DecimalFormat("000").format(count);//将数字处理成三位字符串
				result = "3" + count_str + result;

				System.out.println("共有 " + count + " 笔订单");
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
	 * 打印客户信息报表
	 *        
	 * @return String 
	 *         2：没有客户
	 *         3+客户信息：成功
	 * 
	 * @exception SQLException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String printClientChart() {
		String result = "";
		
		String sql = "";
		try {
			String cid = "";//客户id
			String identity_number = "";//身份证号
			String phone = "";//手机号
			String name = "";//姓名
			
			sql = "select * from client  ";

			ps = conn.prepareStatement(sql);//创建prepareStatement语句

			ResultSet rs = ps.executeQuery();//执行sql查询语句，查找所有客户信息

			int count = 0;//第几条记录
			while (rs.next()) {
				count++;
				cid = rs.getString("cid");//获取客户id
				name = rs.getString("name");//获取客户姓名
				identity_number = rs.getString("identity_number");//获取客户身份证号
				phone = rs.getString("phone");//获取客户手机号

				/*拼接结果字符串*/
				result += "-" + (count) + "-" + cid + "-" + name + "-" + identity_number + "-" + phone;

			}
			/*如果结果集为空，则没有客户，返回2*/
			if (count == 0) {
				System.out.println("无客户");
				result = "2";
			} else {
				/*如果结果集不为空，编辑返回字符串*/
				result = result.substring(1);
				String count_str = new DecimalFormat("000").format(count);//将数字处理成三位字符串
				result = "3" + count_str + result;

				System.out.println("共有 " + count + " 笔订单");
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
