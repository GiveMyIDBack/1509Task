package DBTools3.copy;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;



public class DBToolPro {
	 
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/airsystem";
	    Connection conn=null;
	    PreparedStatement ps=null;//PreparedStatement,Statement
	    ResultSet rs;
	    DBUtil handler;
	    
	    public DBToolPro(){
	    	handler=new DBUtil();
	    	handler.getConn("root", "127127");
	    }
	    
	    
	    public void getConn(String User,String Password){
	        try {
	        	System.out.println(driver);
	            Class.forName(driver);
	            conn = DriverManager.getConnection(url, User, Password);
	            if (!conn.isClosed())
					System.out.println("Succeeded connecting to the Database!");
	        } catch (ClassNotFoundException e) {
	        	System.out.println("Sorry,can`t find the Driver!");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }catch (Exception e) {
				e.printStackTrace();
			}
	        
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
	   
	    public void searchClient(String CurrentPhone){
	        try {
	        	String sql="";
	        	
	        	sql = "select * from client  "
	        	+"where phone = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentPhone);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	            String cid="";
	            String identify_number = "";	                
	            String name="";
	            
				int count=0;
				while (rs.next()) {
					count++;
					cid=rs.getString("cid");
					identify_number = rs.getString("identify_number");
					name = rs.getString("name");
					
					System.out.println(cid+"  "+identify_number+"  "
							+name);
					
					
					
				}
				if(count==0)
					System.out.println("没有匹配结果");
			
				
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
	   
	    public void addAirline(String CurrentAid,String CurrentOrigin,
	    		String CurrentDest,int CurrentDate,String CurrentTime,
	    		int CurrentFirNum,int CurrentBusiNum,int CurrentEcoNum,
	    		String CurrentType){
	        try {
	        
	        	
	        	String sql="";
	        	
	        	sql=" INSERT INTO airline VALUES ( ? , ?  ,?  ,? ,?  ,?  ,?  ,?  ,?  )  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentAid);
	            ps.setString(2, CurrentOrigin);
	            ps.setString(3, CurrentDest);
	            ps.setInt(4,CurrentDate);
	            ps.setString(5,CurrentTime);
	            ps.setInt(6,CurrentFirNum);
	            ps.setInt(7,CurrentBusiNum);
	            ps.setInt(8,CurrentEcoNum);
	            ps.setString(9,CurrentType);

	
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
	    
	    public void addTicketBox(String CurrentName){
	        try {
	        
	        	String sql="";
	        	
	        	sql=" INSERT INTO deliveryman VALUES ( ? , ?  )  ";
	        	String CurrentTid="T"+handler.getAndUpdateTail("tid");
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            ps.setString(2, CurrentName);
	            
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
	        	
	        	sql=" DELETE FROM deliveryman WHERE sid =?  ";
	        		        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            	
	            int rs=ps.executeUpdate();  
	            
	            if(rs!=0)
	            	System.out.println("成功删除送票员");
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
	    
	    public void addOperator(String CurrentName,String CurrentPassword){
	        try {
	        
	        	String sql="";
	        	
	        	sql=" INSERT INTO operator VALUES ( ? , ?  ,?  )  ";
	        	String CurrentOid="O"+handler.getAndUpdateTail("oid");
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentOid);
	            ps.setString(2, CurrentName);
	            ps.setString(3, CurrentPassword);
	            
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
	            String seat_type="";
	            
	            
				int count = 0;
				while (rs.next()) {
					count++;
					orderid=rs.getString("orderid");
					aid = rs.getString("aid");
					seat_type = rs.getString("seat_type");
					
					System.out.println((count)+"  "+orderid+"  "+aid+"  "
							+seat_type);
					System.out.println("");
					
					
				}
				
					System.out.println("共有 "+count+" 条信息");
					
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
	    
	    public void profileUpdate(String CurrentCid,String CurrentIDNum,String CurrentPhone,String CurrentName){
	        try {
	        	String sql="";
	        	
	        	sql = "update  client  "
	        	+"set identify_number= ?  , phone= ? , name= ?  "
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
	    
	    
	    public static void main(String[] args) {
	        DBToolPro myhandler = new DBToolPro();
	        myhandler.getConn("root","127127");
	       //myhandler.getAirline(20100908,"北京","上海");
	        //myhandler.bookTicket("003","MU375","ECONOMIC","17A","002","001");
	        //myhandler.cancelTicket("4F38B03F67E5493A977439AF8893B581");
	        //myhandler.profileUpdate("C00000000","003","003","003");
	        //myhandler.addAirline("MS331", "济南", "天津", 20130310, "1212", 11, 11, 11, "波音767");
	        //myhandler.cancelAirline("MS331");
	        myhandler.addSalesman("李明");
	        try {
               if(myhandler.conn!=null) myhandler.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
	       
	        
	        
	    }
	}
