package DBBasic;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;



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
	   
	    
	   
	    public void addAirline(String CurrentAid,String CurrentOrigin,
	    		String CurrentDest,int CurrentDate,int CurrentHour,int CurrentMinute,
	    		int CurrentFirNum,int CurrentBusiNum,int CurrentEcoNum,
	    		String CurrentType){
	        try {
	        	
	        
	        	
	        	String sql="";
	        	
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
	            
	            if(rs!=0)
	            	System.out.println("成功插入 新航班");
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
	    public void cancelAirline(String CurrentAid){
	        try {
	        	
	        	
	        	String sql="";
	        	
	        	sql=" DELETE FROM airline WHERE aid =?  ";
	        		        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentAid);
	            	
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0)
	            	System.out.println("成功删除航班");
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
	    
	    public void addSalesman(String CurrentName){
	        try {
	        
	        	String sql="";
	        	
	        	sql=" INSERT INTO salesman VALUES ( ? , ?  )  ";
	        	
	        	String CurrentSid="S"+handler.getAndUpdateTail("sid");
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentSid);
	            ps.setString(2, CurrentName);
	            
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0)
	            	System.out.println("成功插入 新销售员");
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
	    public void cancelSalesman(String CurrentSid){
	        try {
	        	
	        	
	        	String sql="";
	        	
	        	sql=" DELETE FROM salesman WHERE sid =?  ";
	        		        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentSid);
	            	
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0)
	            	System.out.println("成功删除销售员");
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
	    
	    public void addDeliveryman(String CurrentName){
	        try {
	        
	        	String sql="";
	        	
	        	sql=" INSERT INTO deliveryman VALUES ( ? , ?  )  ";
	        	
	        	String CurrentDid="D"+handler.getAndUpdateTail("did");
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentDid);
	            ps.setString(2, CurrentName);
	            
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0)
	            	System.out.println("成功插入 新送票员");
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
	    public void cancelDeliveryman(String CurrentDid){
	        try {
	        	
	        	
	        	String sql="";
	        	
	        	sql=" DELETE FROM deliveryman WHERE sid =?  ";
	        		        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentDid);
	            	
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0)
	            	System.out.println("成功删除销售员");
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
	    
	    public void addTicketBox(String CurrentAdd,String CurrentName){
	        try {
	        
	        	String sql="";
	        	
	        	sql=" INSERT INTO ticketbox VALUES ( ? , ? , ?  )  ";
	        	String CurrentTid="T"+handler.getAndUpdateTail("tid");
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            ps.setString(2, CurrentAdd);
	            ps.setString(3, CurrentName);
	            
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0)
	            	System.out.println("成功插入 新售票处");
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
	    public void cancelTiketBox(String CurrentTid){
	        try {
	        	
	        	
	        	String sql="";
	        	
	        	sql=" DELETE FROM ticketbox WHERE sid =?  ";
	        		        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            	
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0)
	            	System.out.println("成功删除售票点");
	            else
	            	System.out.println("错误，请重试");
	            
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
	            
	            if((rs1!=0)&&(rs2!=0))
	            	System.out.println("成功删除相关雇佣关系");
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
	    
	    public void addEmployment_t2s(String CurrentTid,String CurrentSid){
	        try {
	        
	        	String sql="";
	        	
	        	sql=" INSERT INTO employment_t2s VALUES ( ? , ?  )  ";
	        	
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            ps.setString(2, CurrentSid);
	            
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
	    public void addOperator(String CurrentPassword){
	        try {
	        
	        	String sql="";
	        	
	        	sql=" INSERT INTO operator VALUES ( ? , ?  )  ";
	        	String CurrentOid="O"+handler.getAndUpdateTail("oid");
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentOid);
	            ps.setString(2, CurrentPassword);
	            
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0)
	            	System.out.println("成功插入 新操作员");
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
	    public void cancelOperator(String CurrentOid){
	        try {
	        	
	        	
	        	String sql="";
	        	
	        	sql=" DELETE FROM operator WHERE oid =?  ";
	        		        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentOid);
	            	
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0)
	            	System.out.println("成功删除操作员");
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
	    
	    public void printSalesmanChart(String CurrentSid){
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
	            String seat_type="";
	            
	            
				int count = 0;
				while (rs.next()) {
					count++;
					orderid=rs.getString("orderid");
					cid = rs.getString("cid");
					aid = rs.getString("aid");
					seat_type = rs.getString("seat_type");
					
					System.out.println((count)+"  "+orderid+"  "+cid+"  "+aid+"  "
							+seat_type);
					System.out.println("");
					
					
				}
				
					System.out.println("共有 "+count+" 笔订单");
					
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
	    }
	    
	    
	    
	    
	    public static void main(String[] args) {
	        DBHandlerPro myhandler = new DBHandlerPro();
	        
	       //myhandler.getAirline(20100908,"北京","上海");
	        //myhandler.bookTicket("003","MU375","ECONOMIC","17A","002","001");
	        //myhandler.cancelTicket("4F38B03F67E5493A977439AF8893B581");
	        //myhandler.profileUpdate("C00000000","003","003","003");
	        //myhandler.addAirline("MS331", "济南", "天津", 20130310, "1212", 11, 11, 11, "波音767");
	        
	        
	        try {
               if(myhandler.conn!=null) myhandler.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
	       
	        
	        
	    }
	}

