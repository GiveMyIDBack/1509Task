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



public class DBHandlerPro {
	 
	    DBUtil handler;
	    Connection conn;
	    PreparedStatement ps;// PreparedStatement,Statement
		ResultSet rs;

		public DBHandlerPro(){
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
	   
	    
	   
	    public String addAirline(String CurrentAid,String CurrentOrigin,String CurrentDest,
	    		int CurrentDate,int CurrentHour,int CurrentMinute,String CurrentType){
	    	String result="";
	    	if(!handler.checkTYPE_exist(CurrentType)){
	    		System.out.println("机型不存在，请检查后再输入");
	    		return "0";
	    	}
	    	
	    	int[] seat_data=handler.getSeat(CurrentType);
	    	int CurrentFirNum=seat_data[0];
	    	int CurrentBusiNum=seat_data[1];
	    	int CurrentEcoNum=seat_data[2];
	    	
	    	String first_seat=bitTranslator.bi2hex(SeatMaker.MakeSeatTable(CurrentFirNum), CurrentFirNum);
	    	System.out.println("first_seat"+first_seat);
	    	String business_seat=bitTranslator.bi2hex(SeatMaker.MakeSeatTable(CurrentBusiNum), CurrentBusiNum);
	    	System.out.println("business_seat"+business_seat);
	    	String economic_seat=bitTranslator.bi2hex(SeatMaker.MakeSeatTable(CurrentEcoNum), CurrentEcoNum);
	    	System.out.println("economic_seat"+economic_seat);
	    	
	    	String sql="";
	    	//写一个方法，根据type调出来
	        try {
	        	
	        	sql=" INSERT INTO airline VALUES ( ? , ?  ,?  ,? ,?  ,?  ,?  ,?  ,? ,?  )  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentAid);
	            ps.setString(2, CurrentOrigin);
	            ps.setString(3, CurrentDest);
	            ps.setInt(4,CurrentDate);
	            ps.setInt(5,CurrentHour);
	            ps.setInt(6,CurrentMinute);
	            ps.setInt(7,CurrentFirNum);
	            ps.setInt(8,CurrentBusiNum);
	            ps.setInt(9,CurrentEcoNum);
	            ps.setString(10,CurrentType);

	
	            int rs=ps.executeUpdate();  
	            
	            sql=" INSERT INTO seat VALUES ( ? , ?  ,?  ,?  , ?  )  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentAid);
	            ps.setString(2, CurrentType);
	            ps.setString(3, first_seat);
	            ps.setString(4, business_seat);
	            ps.setString(5, economic_seat);
	            
	            int rs2=ps.executeUpdate();  
	            
	            if(rs!=0&&rs2!=0){
	            	System.out.println("成功插入 新航班");
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
	    public String cancelAirline(String CurrentAid){
	    	String result="";
	    	if(!handler.checkA_exist(CurrentAid)){
	    		System.out.println("航班不存在，请检查后再输入");
	    		return "0";
	    	}
	        try {
	        	
	        	
	        	String sql="";
	        	
	        	sql=" DELETE FROM airline WHERE aid =?  ";
	        		        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentAid);
	            	
	            int rs=ps.executeUpdate();  
	            
	            sql=" DELETE FROM seat WHERE aid =?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentAid);
	            	
	            int rs2=ps.executeUpdate(); 
	            
	            if(rs!=0&& rs2!=0){
	            	System.out.println("成功删除航班");
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
	    
	    public String addSalesman(String CurrentName,String CurrentTid){
	    	if(!handler.checkT_exist(CurrentTid)){
	    		System.out.println("售票点ID不存在，请检查后输入");
	    		return "3";
	    	}
	    	String result="";
	        try {
	        
	        	String sql="";
	        	
	        	sql=" INSERT INTO salesman VALUES ( ? , ?  )  ";
	        	
	        	String CurrentSid="S"+handler.getAndUpdateTail("sid");
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentSid);
	            ps.setString(2, CurrentName);
	            
	            int rs=ps.executeUpdate();  
	            
	            sql=" INSERT INTO employment_t2s VALUES ( ? , ?  )  ";
	        	
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            ps.setString(2, CurrentSid);
	            
	            int rs2=ps.executeUpdate();  
	            
	            if(rs!=0&&rs2!=0){
	            	System.out.println("成功插入 新销售员及雇佣关系");
	            	result="0"+CurrentSid;
	            }
	            else{
	            	System.out.println("错误，请重试");
	            	result="1";
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
	    public String  cancelSalesman(String CurrentSid){
	    	String result="";
	    	if(!handler.checkS_exist(CurrentSid)){
	    		System.out.println("业务员不存在，请检查后再输入");
	    		return "2";
	    	}
	        try {
	        	
	        	
	        	String sql="";
	        	
	        	sql=" DELETE FROM salesman WHERE sid =?  ";
	        		        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentSid);
	            	
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0){
	            	System.out.println("成功删除销售员");
	            	result="0";
	            }
	            else{
	            	System.out.println("错误，请重试");
	            	result="1";
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
	    
	    public String addDeliveryman(String CurrentName,String CurrentTid){
	    	if(!handler.checkT_exist(CurrentTid)){
	    		System.out.println("售票点ID不存在，请检查后输入");
	    		return "3";
	    	}
	    	String result="";
	        try {
	        
	        	String sql="";
	        	
	        	sql=" INSERT INTO deliveryman VALUES ( ? , ?  )  ";
	        	
	        	String CurrentDid="D"+handler.getAndUpdateTail("did");
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentDid);
	            ps.setString(2, CurrentName);
	            
	            int rs=ps.executeUpdate();  
	            
	            sql=" INSERT INTO employment_t2d VALUES ( ? , ?  )  ";
	        	
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            ps.setString(2, CurrentDid);
	            
	            int rs2=ps.executeUpdate();  
	            
	            if(rs!=0){
	            	System.out.println("成功插入 新送票员及相关雇佣关系");
	            	result="0"+CurrentDid;
	            }
	            else{
	            	System.out.println("错误，请重试");
	            	result="1";
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
	    public String cancelDeliveryman(String CurrentDid){
	    	String result="";
	    	if(!handler.checkD_exist(CurrentDid)){
	    		System.out.println("业务员不存在，请检查后再输入");
	    		return "2";
	    	}
	        try {
	        	
	        	
	        	String sql="";
	        	
	        	sql=" DELETE FROM deliveryman WHERE did =?  ";
	        		        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentDid);
	            	
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0){
	            	System.out.println("成功删除销售员");
	            	result="0";
	            }
	            else{
	            	System.out.println("错误，请重试");
	            	result="1";
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
	    
	    public String addTicketBox(String CurrentAdd,String CurrentName){
	    	String result="";
	        try {
	        
	        	String sql="";
	        	
	        	sql=" INSERT INTO ticketbox VALUES ( ? , ? , ?  )  ";
	        	String CurrentTid="T"+handler.getAndUpdateTail("tid");
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            ps.setString(2, CurrentAdd);
	            ps.setString(3, CurrentName);
	            
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0){
	            	System.out.println("成功插入 新售票处");
	            	result="0"+CurrentTid;
	            }
	            else{
	            	System.out.println("错误，请重试");
	            	result="1";
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
	    public String cancelTicketBox(String CurrentTid){
	    	String result="";
	    	if(!handler.checkT_exist(CurrentTid)){
	    		System.out.println("售票点不存在，请检查后再输入");
	    		return "2";
	    	}
	        try {
	        	
	        	
	        	String sql="";
	        	
	        	sql=" DELETE FROM ticketbox WHERE tid =?  ";
	        		        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            	
	            int rs=ps.executeUpdate();  
	            
	            
	            
	            //删除与该ticketbox相关的t2s雇佣关系
	            sql=" DELETE FROM employment_t2s WHERE tid =? ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            
	            //删除与该ticketbox相关的t2d雇佣关系
	            int rs1=ps.executeUpdate();  
	            
	            sql=" DELETE FROM employment_t2d WHERE tid =? ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            	            	
	            int rs2=ps.executeUpdate();  
	            
	            if(rs!=0){
	            	System.out.println("成功删除售票点与其相关雇佣关系");
	            	result="0";
	            }
	            else{
	            	System.out.println("错误，请重试");
	            	result="1";
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
	    
	    public void addEmployment_t2s(String CurrentTid,String CurrentSid){
	        try {
	        
	        	String sql="";
	        	
	        	sql=" INSERT INTO employment_t2s VALUES ( ? , ?  )  ";
	        	
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            ps.setString(2, CurrentSid);
	            
	            int rs=ps.executeUpdate();  
	            
	           
	            	
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
	    public void cancelEmployment_t2s(String CurrentTid,String CurrentSid){
	        try {
	        	
	        	
	        	String sql="";
	        	
	        	sql=" DELETE FROM employment_t2s WHERE tid =? ,sid= ?  ";
	        		        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            ps.setString(2, CurrentSid);
	            	
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0)
	            	System.out.println("成功删除雇佣关系");
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
	    
	    public void addEmployment_t2d(String CurrentTid,String CurrentDid){
	        try {
	        
	        	String sql="";
	        	
	        	sql=" INSERT INTO employment_t2d VALUES ( ? , ?  )  ";
	        	
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            ps.setString(2, CurrentDid);
	            
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0)
	            	System.out.println("成功插入 新雇佣关系");
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
	    public void cancelEmployment_t2d(String CurrentTid,String CurrentDid){
	        try {
	        	
	        	
	        	String sql="";
	        	
	        	sql=" DELETE FROM employment_t2d WHERE tid =? ,did= ?  ";
	        		        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            ps.setString(2, CurrentDid);
	            	
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0)
	            	System.out.println("成功删除雇佣关系");
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
	    public String addOperator(String CurrentPassword){
	    	String result="";
	        try {
	        
	        	String sql="";
	        	
	        	sql=" INSERT INTO operator VALUES ( ? , ?  )  ";
	        	String CurrentOid="O"+handler.getAndUpdateTail("oid");
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentOid);
	            ps.setString(2, CurrentPassword);
	            
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0){
	            	System.out.println("成功插入 新操作员");
	            	result="0"+CurrentOid;
	            }
	            else{
	            	System.out.println("错误，请重试");
	            	result="1";
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
	    public String cancelOperator(String CurrentOid){
	    	String result="";
	    	if(!handler.checkO_exist(CurrentOid)){
	    		System.out.println("操作员不存在，请检查后再输入");
	    		return "2";
	    	}
	        try {
	        	
	        	
	        	String sql="";
	        	
	        	sql=" DELETE FROM operator WHERE oid =?  ";
	        		        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentOid);
	            	
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0){
	            	System.out.println("成功删除操作员");
	            	result="0";
	            }
	            else{
	            	System.out.println("错误，请重试");
	            	result="1";
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
	    
	    
	    public void logout(String CurrentCid){
	        try {
	        	
	        	
	        	String sql="";
	        	
	        	sql=" DELETE FROM membership WHERE cid =?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentCid);
	            	
	            int rs1=ps.executeUpdate();  
	        	
	        	sql=" DELETE FROM client WHERE cid =?  ";
	        		        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentCid);
	            	
	            int rs2=ps.executeUpdate();  
	            
	            if((rs1!=0)&&(rs2!=0))
	            	System.out.println("成功删除客户");
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
	    
	    public String printSalesmanChart(String CurrentSid){
	    	String result="";
	    	if(!handler.checkS_exist(CurrentSid)){
	    		System.out.println("业务员ID不存在，请检查后再输入");
	    		return "1";
	    	}
	        try {
	        	String sql="";
	        	
	        	sql = "select * from orders  "
	        	+"where sid = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentSid);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	            String orderid="";
	            String aid = "";	   
	            String cid = "";
	            
	            
	            
				int count = 0;
				while (rs.next()) {
					count++;
					orderid=rs.getString("orderid");
					cid = rs.getString("cid");
					aid = rs.getString("aid");
					
					
					System.out.println((count)+"  "+orderid+"  "+cid+"  "+aid);
					System.out.println("");
					result+="-"+(count)+"-"+orderid+"-"+cid+"-"+aid;
					
					
					
				}
				if(count==0){
					result="2";
					System.out.println("无订单");
				}
				else{
					result=result.substring(1);
					String count_str =  new DecimalFormat("000").format(count);
					result="3"+count_str+result;
					
						System.out.println("共有 "+count+" 笔订单");
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
	    
	    public String printClientChart(){
	    	String result="";
	        try {
	        	String sql="";
	        	
	        	sql = "select * from client  ";
	        	
	        	
	        	ps = conn.prepareStatement(sql);
	            
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	            String cid="";
	            String identity_number = "";	   
	            String phone= "";
	            String name="";
	            
	            
				int count = 0;
				while (rs.next()) {
					count++;
					cid = rs.getString("cid");
					name = rs.getString("name");
					identity_number=rs.getString("identity_number");
					phone = rs.getString("phone");
					
					
					System.out.println((count)+"  "+cid+"  "+name+"  "+identity_number+"  "
							+phone);
					System.out.println("");
					result+="-"+(count)+"-"+cid+"-"+name+"-"+identity_number+"-"
							+phone;
					
					
				}
				if(count==0){
					System.out.println("无客户");
					result="2";
				}
				else{
					result=result.substring(1);
					String count_str =  new DecimalFormat("000").format(count);
					result="3"+count_str+result;
					
						System.out.println("共有 "+count+" 笔订单");
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
	    
	    
	    
	    public static void main(String[] args) {
	        DBHandlerPro myhandler = new DBHandlerPro();
	        
	       //myhandler.getAirline(20100908,"北京","上海");
	        
	        //myhandler.cancelTicket("4F38B03F67E5493A977439AF8893B581");
	        //myhandler.profileUpdate("C00000000","003","003","003");
	        myhandler.addAirline("HK103", "济南", "天津", 20130310,12,12, "A320");
	        
	        
	        try {
               if(myhandler.conn!=null) myhandler.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
	       
	        
	        
	    }
}
	    

