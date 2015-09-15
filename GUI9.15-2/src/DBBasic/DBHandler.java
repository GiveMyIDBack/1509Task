package DBBasic;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.ibm.icu.text.DecimalFormat;





public class DBHandler {
	
	
	    DBUtil handler;
	    Connection conn;
	    
	    PreparedStatement ps;//PreparedStatement,Statement
	    ResultSet rs;

	    public DBHandler(){
    	handler=new DBUtil();
    	handler.getConn("root", "127127");
    	conn=handler.conn;
    	ps=null;
        }
	
	
	    
	    /*
	    
	      ************************** 
	      *TEMPLATE
	    public void callProc(){
	        try {
	        	
	        	String sql = "select aid, origin from airline  "
	        	+"where airline.date = ?  "
	    		+"and airline.origin =?  "
	    		+"and airline.destination =?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setInt(1, 20100908);
	            ps.setString(2, "GZ");
	            ps.setString(3, "BJ");
	            
	            ResultSet rs = ps.executeQuery();  
	            String name = null;
				int id = 0;
				while (rs.next()) {
					name = rs.getString("origin");
					name = new String(name.getBytes("ISO-8859-1"), "GB2312");
					//System.out.println(rs.getString("aid") + "\t" + name);
					System.out.println(name);
				}
				rs.close();
				
	            
	            
	            
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
					e.printStackTrace();
			}finally{

	            try {
	                if (ps!=null) ps.close();
	                if(conn!=null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    *******************
	    */
	    public String getAirline(int CurrentDate,String CurrentOrigin,String CurrentDestination){
	        String result="";
	    	try {
	        	String sql="";
	        	
	        	sql = "select * from airline  "
	        	+"where airline.date = ?  "
	    		+"and airline.origin = ?  "
	    		+"and airline.destination = ?  ";
	        	/*
	        	String sql = "select * from airline  "+
	        			"where airline.date = ?  "
	    				+"and airline.time =?  "
	    		+"and airline.origin =?  "
	    				+"and airline.destination =?  ";
	    				*/
	        	ps = conn.prepareStatement(sql);
	            ps.setInt(1, CurrentDate);
	            ps.setString(2, CurrentOrigin);
	            ps.setString(3, CurrentDestination);
	            
	            ResultSet rs = ps.executeQuery();  
	            
	            String aid="";
	            String origin = "";
	            String destination = "";
	            int date=0;
	            int hour=0;
	            int minute=0;
	            int first_num=0;
	            int business_num=0;
	            int economic_num=0;
	            String type="";
	            
				int count = 0;
				while (rs.next()) {
					count++;
					aid=rs.getString("aid");
					origin = rs.getString("origin");
					destination = rs.getString("destination");
					//name = new String(name.getBytes("ISO-8859-1"), "GB2312");
					date=rs.getInt("date");
					hour=rs.getInt("hour");
					minute=rs.getInt("minute");
					first_num=rs.getInt("first_num");
					business_num=rs.getInt("business_num");
					economic_num=rs.getInt("economic_num");
					type = rs.getString("type");
					System.out.println((count)+"  "+aid+"  "+origin+"  "
							+destination+"  "+date+"  "+hour+":"+minute+"  "+
							first_num+"  "+business_num+"  "+economic_num+"  "+type
							);
					result+="-"+count+"-"+aid+"-"+origin+"-"+destination+"-"+date+"-"+hour+":"+minute+"-"+
							first_num+"-"+business_num+"-"+economic_num+"-"+type;
					
					
					
				}
				if(count!=0){
					System.out.println("共有 "+count+" 条信息");
					result=result.substring(1);
					String count_str =  new DecimalFormat("00").format(count);
					result="0"+count_str+result;
				}
				else{
					System.out.println("没有直达航班，建议您通过转机到达目的地");
					sql = "select * "
							+"from airline as A inner join airline as B   "
				        	+"on A.destination=B.origin  "
				        	+"and A.date = ?  "
				        	+"and B.date = ?  "
				        	+"and A.hour < B.hour "
				        	+"and A.origin = ?  "
				    		+"and B.destination = ?  ";
					ps = conn.prepareStatement(sql);
		            ps.setInt(1, CurrentDate);
		            ps.setInt(2, CurrentDate);
		            ps.setString(3, CurrentOrigin);
		            ps.setString(4, CurrentDestination);
		            
		            String aid2="";
		            String origin2 = "";
		            String destination2 = "";
		            int date2=0;
		            int hour2=0;
		            int minute2=0;
		            int first_num2=0;
		            int business_num2=0;
		            int economic_num2=0;
		            String type2="";
		            
		            rs = ps.executeQuery();  
		            
		            while (rs.next()) {
		            	count++;
						aid=rs.getString("A.aid");
						origin = rs.getString("A.origin");
						destination = rs.getString("A.destination");
						//name = new String(name.getBytes("ISO-8859-1"), "GB2312");
						date=rs.getInt("A.date");
						hour=rs.getInt("A.hour");
						minute=rs.getInt("A.minute");
						first_num=rs.getInt("A.first_num");
						business_num=rs.getInt("A.business_num");
						economic_num=rs.getInt("A.economic_num");
						type = rs.getString("A.type");
						
						aid2=rs.getString("B.aid");
						origin2 = rs.getString("B.origin");
						destination2 = rs.getString("B.destination");
						//name = new String(name.getBytes("ISO-8859-1"), "GB2312");
						date2=rs.getInt("B.date");
						hour=rs.getInt("B.hour");
						minute=rs.getInt("B.minute");
						first_num2=rs.getInt("B.first_num");
						business_num2=rs.getInt("B.business_num");
						economic_num2=rs.getInt("B.economic_num");
						type2= rs.getString("B.type");
						System.out.println((count)+"  "+aid+"  "+origin+"  "
								+destination+"  "+date+"  "+hour+":"+minute+"  "+
								first_num+"  "+business_num+"  "+economic_num+"  "+type
								);
						result+="-"+count+"-"+aid+"-"+origin+"-"+destination+"-"+date+"-"+hour+":"+minute+"-"+
								first_num+"-"+business_num+"-"+economic_num+"-"+type;
						
						System.out.println((count)+"  "+aid2+"  "+origin2+"  "
								+destination2+"  "+date2+"  "+hour2+":"+minute2+"  "+
								first_num2+"  "+business_num2+"  "+economic_num2+"  "+type2
								);
						result+="-"+count+"-"+aid2+"-"+origin2+"-"+destination2+"-"+date2+"-"+hour2+":"+minute2+"-"+
								first_num2+"-"+business_num2+"-"+economic_num2+"-"+type2;
						
						System.out.println("");
						
					}
		            
		            if(count==0){
		            	System.out.println("没有匹配信息");
		            	
						result="2";
		            }
		            else{
		            	System.out.println("共有 "+count+" 种组合");
		            	result=result.substring(1);
						String count_str =  new DecimalFormat("00").format(count);
						result="1"+count_str+result;
		            }
		            	
		            	
		            	
		            	
		            
		            
				}
				  
					
				
					
				rs.close();           
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
					e.printStackTrace();
			}finally{

	            try {
	                if (ps!=null) ps.close();
	                //if(conn!=null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    	return result;
	    }
	    
	    //aid的该seat_type是否还有座位，该seatid是否没人要
	    public String bookTicket(String cid,String aid,String seat_type,String seatid){
	    	boolean flag=((seat_type=="ECONOMIC"||seat_type=="BUSINESS"||seat_type=="FIRSTCLASS"));
	    	System.out.println("hihihih");
	    	System.out.println(flag);
	    	
	    	System.out.println("+++"+seat_type+"++");
	    	boolean flag2=(seat_type=="ECONOMIC");
	    	System.out.println("hihihih2");
	    	System.out.println(flag2);
	    	String result="";
	    	if(!handler.checkC_exist(cid)){
	    		System.out.println("用户账号不存在，请检查后再输入");
	    		return "1";
	    	}
	    	
	    	if(!handler.checkA_exist(aid)){
	    		System.out.println("航班不存在，请检查后再输入");
	    		return "0";
	    	}
	    	
	    	if(!(seat_type.equals("ECONOMIC")||seat_type.equals("BUSINESS")||seat_type.equals("FIRSTCLASS"))){
	    		System.out.println("hi座位类型错误，请检查后再输入");
	    		return "2";
	    	}
	    	
	    	int row=Integer.parseInt(seatid.substring(0, 2));
	    	String col=seatid.substring(2, 3);
	    	if(!((row>10||row<40)&&(col.equals("A")||col.equals("A")||col.equals("B")||col.equals("C")||col.equals("D")||col.equals("E")))){
	    		System.out.println("座位号错误，请检查后再输入");
	    		return "3";
	    	}
	    	
	    	
	    	/*
	    	 * ????????????????????????????????????????????????
	    	 * ????????????????????????????????????????????????
	    	 * 是否还有座位
	    	 * 选中座位是否被占
	    	 * ????????????????????????????????????????????????
	    	 * ????????????????????????????????????????????????
	    	 */
	    		    	
	    	String did=handler.randomDeliveryman();
	    	String sid=handler.getBusiSalesman(cid);
	    	
	    	try {
	        	
	        	//生成订单号
	        	String orderid = java.util.UUID.randomUUID().toString().replaceAll("-","").toUpperCase(); 
	        	System.out.println(orderid);
	        	String sql="";
	        	
	        	sql=" INSERT INTO orders VALUES ( ? , ?  ,?  ,? ,?  ,?  ,?  )  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, orderid);
	            ps.setString(2, aid);
	            ps.setString(3, cid);
	            ps.setString(4,seat_type);
	            ps.setString(5,seatid );
	            ps.setString(6,sid);
	            ps.setString(7,did);

	
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0){
	            	System.out.println("成功插入");
	            	result="5"+orderid;
	            }
	            else{
	            	System.out.println("错误，请重试");
	            	result="8";
	            }
	            	
	        	
	            
	            } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
					e.printStackTrace();
			}finally{

	            try {
	                if (ps!=null) ps.close();
	                //if(conn!=null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    	return result;
	    }
	    public String cancelTicket(String CurrentOrderid){
	    	String result="";
	    	if(!handler.checkOrder_exist(CurrentOrderid)){
	    		System.out.println("该订单不存在，请检查后再输入");
	    		return "0";
	    	}
	    	
	    	
	        try {
	        	
	        	
	        	String sql="";
	        	
	        	sql=" DELETE FROM orders WHERE orderid =?  ";
	        	
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentOrderid);
	            

	
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0){
	            	System.out.println("成功删除订单");
	            	result="1";
	            }
	            else{
	            	System.out.println("错误，请重试");
	            	result="8";
	            }
	            	
	        	
	            
	            } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
					e.printStackTrace();
			}finally{

	            try {
	                if (ps!=null) ps.close();
	                //if(conn!=null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return result;
	    }
	    
	    public String printSchedule(String CurrentCid){
	    	String result="";
	    	if(!handler.checkC_exist(CurrentCid)){
	    		System.out.println("用户账号不存在，请检查后再输入");
	    		return "0";
	    	}
	    	
	    	
	        try {
	        	String sql="";
	        	
	        	sql = "select * from orders inner join airline  "
	        	+"where cid = ?  "
	        	+" and orders.aid = airline.aid ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentCid);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	            String orderid="";
	            String aid = "";	                  
	            String seat_type="";
	            String seat_id="";
	            String sid = "";	                  
	            String did="";
	            int date=0;
	            int hour=0;
	            int minute=0;
	            
				int count = 0;
				while (rs.next()) {
					count++;
					orderid=rs.getString("orderid");
					aid = rs.getString("orders.aid");
					seat_type = rs.getString("seat_type");
					seat_id = rs.getString("seat_id");
					sid=rs.getString("sid");
					did=rs.getString("did");
					date=rs.getInt("date");
					hour=rs.getInt("hour");
					minute=rs.getInt("minute");
					System.out.println((count)+"  "+orderid+"  "+aid+"  "+date+"  "+hour+":"+minute+"  "
							+seat_type+"  "+seat_id+"  "+sid+"  "+did);
					System.out.println("");
					result+="-"+(count)+"-"+orderid+"-"+aid+"-"+date+"-"+hour+":"+minute+"-"
							+seat_type+"-"+seat_id+"-"+sid+"-"+did;
					
					
				}
				if(count==0){
					System.out.println("没有行程");
					result="4";
				}
				else{
					System.out.println("共有 "+count+" 条信息");
					result=result.substring(1);
					String count_str =  new DecimalFormat("00").format(count);
					result="3"+count_str+result;
				}
				
				
				
					
					
					
				rs.close();           
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
					e.printStackTrace();
			}finally{

	            try {
	                if (ps!=null) ps.close();
	                //if(conn!=null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return result;
	    }
	    //重写……
	    //先删当前的，在拿着新数据插
	    //万一插的时候说新电话/新身份证号充了呢？旧id就没有了啊？！！
	    //这个不行……
	    //先插入新数据，看看IDNUM和phone有没有重
	    //万一只变了一个呢？那不是又要重？！
	    //select的where条件要变，排除cid一样的那一行
	    /*
	    
	    public void profileUpdate(String CurrentCid,String CurrentIDNum,String CurrentPhone,String CurrentName){
	        try {
	        	String sql="";
	        	
	        	sql = "update  client  "
	        	
	        	+"set identify_number= ?  , phone= ? , name= ? "
	        	+"where cid = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            
	            ps.setString(1, CurrentIDNum);
	            ps.setString(2, CurrentPhone);
	            ps.setString(3, CurrentName);
	            ps.setString(4, CurrentCid);
	            
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0)
	            	System.out.println("成功修改信息");
	            else
	            	System.out.println("错误，请重试");
 
	            } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
					e.printStackTrace();
			}finally{

	            try {
	                if (ps!=null) ps.close();
	                //if(conn!=null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    */
	public void profileUpdate(String CurrentCid, String CurrentIDNum, String CurrentPhone, String CurrentName) {

		if (handler.checkIDNDupExceptSelf(CurrentIDNum, CurrentCid)) {
			System.out.println("新身份证号已注册，修改个人信息失败，请重试");
			return;
		}

		if (handler.checkPhoneDupExceptSelf(CurrentPhone, CurrentCid)) {
			System.out.println("该手机号已注册，修改个人信息失败，请重试");
			return;
		}

		try {
			String sql = "";
			sql = "update  client  "

			+ "set identity_number= ?  , phone= ? , name= ? " + "where cid = ?  ";

			ps = conn.prepareStatement(sql);

			ps.setString(1, CurrentIDNum);
			ps.setString(2, CurrentPhone);
			ps.setString(3, CurrentName);
			ps.setString(4, CurrentCid);

			int rs = ps.executeUpdate();

			if (rs != 0)
				System.out.println("成功修改信息");
			else
				System.out.println("错误，请重试");

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
	}

	public String profileUpdate_name(String CurrentCid,String CurrentName) {
		String result="";
	

		if (!handler.checkC_exist(CurrentCid) ) {
			System.out.println("客户ID不存在，请检查后输入");
			return "2";
		}

		

		try {
			String sql = "";
			sql = "update  client  "

			+ "set  name= ? " + "where cid = ?  ";

			ps = conn.prepareStatement(sql);

			
			ps.setString(1, CurrentName);
			ps.setString(2, CurrentCid);

			int rs = ps.executeUpdate();

			if (rs != 0){
				System.out.println("成功修改信息");
				result="3";
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
					ps.close();
				// if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public String profileUpdate_idn(String CurrentCid,String CurrentIDNum){
		
		String result="";
		

		if (!handler.checkC_exist(CurrentCid) ) {
			System.out.println("客户ID不存在，请检查后输入");
			return "2";
		}
    	
    	if(handler.checkIDNDupExceptSelf(CurrentIDNum,CurrentCid)){
    		System.out.println("新身份证号已注册，修改个人信息失败，请重试");
    		return "0";
    	}
    	
    	
    	try {
    		String sql="";
        	sql = "update  client  "
        	
        	+"set identity_number= ?   "
        	+"where cid = ?  ";
        	
        	ps = conn.prepareStatement(sql);
            
            ps.setString(1, CurrentIDNum);
            
            ps.setString(2, CurrentCid);
            
            int rs=ps.executeUpdate();  
            
            if(rs!=0){
            	System.out.println("成功修改信息");
            	result="3";
            }
            else{
            	System.out.println("错误，请重试");
            	result="8";
            }

            } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
				e.printStackTrace();
		}finally{

            try {
                if (ps!=null) ps.close();
                //if(conn!=null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    	return result;
    }
    	
	public String profileUpdate_phone(String CurrentCid,String CurrentPhone){
    	
		String result="";
		

		if (!handler.checkC_exist(CurrentCid) ) {
			System.out.println("客户ID不存在，请检查后输入");
			return "2";
		}
    	
    	if(handler.checkPhoneDupExceptSelf(CurrentPhone,CurrentCid)){
    		System.out.println("该手机号已注册，修改个人信息失败，请重试");
    		return "1";
    	}
    	
    	try {
    		String sql="";
        	sql = "update  client  "
        	
        	+"set phone= ?  "
        	+"where cid = ?  ";
        	
        	ps = conn.prepareStatement(sql);
            
           
            ps.setString(1, CurrentPhone);
           
            ps.setString(2, CurrentCid);
            
            int rs=ps.executeUpdate();  
            
            if(rs!=0){
            	System.out.println("成功修改信息");
            	result="3";
            }
            else{
            	System.out.println("错误，请重试");
            	result="8";
            }

            } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
				e.printStackTrace();
		}finally{

            try {
                if (ps!=null) ps.close();
                //if(conn!=null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    	return result;
    }


	    public static void main(String[] args) {
	        DBHandler myhandler = new DBHandler();
	        //myhandler.getConn("root","127127");
	       //myhandler.getAirline(20100908,"北京","上海");
	        myhandler.bookTicket("C00000001","ZH333","ECONOMIC","17A");
	        //myhandler.cancelTicket("4F38B03F67E5493A977439AF8893B581");
	        //myhandler.printSchedule("003");
	        
	        //myhandler.profileUpdate("1", "1", "1", "3");
	        try {
               if(myhandler.conn!=null) myhandler.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
	       
	        
	        
	    }
	}

