package DBTools2;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;



public class DBTool {
	 
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/airsystem";
	    Connection conn=null;
	    PreparedStatement ps=null;//PreparedStatement,Statement
	    ResultSet rs;
	    
	    
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
	    public void getAirline(int CurrentDate,String CurrentOrigin,String CurrentDestination){
	        try {
	        	String sql="";
	        	
	        	sql = "select * from airline  "
	        	+"where airline.date = ?  "
	    		+"and airline.origin =?  "
	    		+"and airline.destination =?  ";
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
	            int time=0;
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
					time=rs.getInt("time");
					first_num=rs.getInt("first_num");
					business_num=rs.getInt("business_num");
					economic_num=rs.getInt("economic_num");
					type = rs.getString("type");
					System.out.println((count)+"  "+aid+"  "+origin+"  "
							+destination+"  "+date+"  "+time+"  "+
							first_num+"  "+business_num+"  "+economic_num+"  "+type
							);
					
					
				}
				if(count!=0)
					System.out.println("共有 "+count+" 条信息");
				else{
					System.out.println("没有直达航班，建议您通过转机到达目的地");
					sql = "select * "
							+"from airline as A inner join airline as B   "
				        	+"on A.destination=B.origin";
				        	//+"and A.date = ?  "
				        	//+"and B.date = ?  ";
				        	//+"and B.date>=A.date and B.date<=A.date+1"
				    		//+"and A.origin =?  "
				    		//+"and B.destination =?  ";
					ps = conn.prepareStatement(sql);
		            //ps.setInt(1, CurrentDate);
		            //ps.setInt(2, CurrentDate);
		            //ps.setString(3, CurrentOrigin);
		           // ps.setString(4, CurrentDestination);
		            
		            String aid2="";
		            String origin2 = "";
		            String destination2 = "";
		            int date2=0;
		            int time2=0;
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
						time=rs.getInt("A.time");
						first_num=rs.getInt("A.first_num");
						business_num=rs.getInt("A.business_num");
						economic_num=rs.getInt("A.economic_num");
						type = rs.getString("A.type");
						
						aid2=rs.getString("B.aid");
						origin2 = rs.getString("B.origin");
						destination2 = rs.getString("B.destination");
						//name = new String(name.getBytes("ISO-8859-1"), "GB2312");
						date2=rs.getInt("B.date");
						time2=rs.getInt("B.time");
						first_num2=rs.getInt("B.first_num");
						business_num2=rs.getInt("B.business_num");
						economic_num2=rs.getInt("B.economic_num");
						type2= rs.getString("B.type");
						System.out.println((count)+"  "+aid+"  "+origin+"  "
								+destination+"  "+date+"  "+time+"  "+
								first_num+"  "+business_num+"  "+economic_num+"  "+type
								);
						System.out.println((count)+"  "+aid2+"  "+origin2+"  "
								+destination2+"  "+date2+"  "+time2+"  "+
								first_num2+"  "+business_num2+"  "+economic_num2+"  "+type2
								);
						System.out.println("");
						
					}
		            if(count==0)
		            	System.out.println("没有匹配信息");
		            else
		            	System.out.println("共有 "+count+" 种组合");
		            	
		            	
		            	
		            	
		            
		            
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
	    
	    /*
	    public void Book(String aid,String seat_type,int cid,int tid,int did){
	        try {
	        	
	        	String sql = "select * from airline  "
	        	+"where airline.date = ?  "
	    		+"and airline.origin =?  "
	    		+"and airline.destination =?  ";
	        	String sql="insert (aid,)"
	        	*/
	        	
	        	/*
	        	String sql = "select * from airline  "+
	        			"where airline.date = ?  "
	    				+"and airline.time =?  "
	    		+"and airline.origin =?  "
	    				+"and airline.destination =?  ";
	    				*/
	    /*
	        	ps = conn.prepareStatement(sql);
	            ps.setInt(1, CurrentDate);
	            ps.setString(2, CurrentOrigin);
	            ps.setString(3, CurrentDestination);
	            
	            ResultSet rs = ps.executeQuery();  
	            
	            String aid="";
	            String origin = "";
	            String destination = "";
	            int date=0;
	            int time=0;
	            int first_num=0;
	            int business_num=0;
	            int economic_num=0;
	            String type="";
	            
				int id = 0;
				while (rs.next()) {
					aid=rs.getString("aid");
					origin = rs.getString("origin");
					destination = rs.getString("destination");
					//name = new String(name.getBytes("ISO-8859-1"), "GB2312");
					date=rs.getInt("date");
					time=rs.getInt("time");
					first_num=rs.getInt("first_num");
					business_num=rs.getInt("business_num");
					economic_num=rs.getInt("economic_num");
					type = rs.getString("type");
					System.out.println(aid+"  "+origin+"  "
							+destination+"  "+date+"  "+time+"  "+
							first_num+"  "+business_num+"  "+economic_num+"  "+type
							);
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
	    */
	    public static void main(String[] args) {
	        DBTool myhandler = new DBTool();
	        myhandler.getConn("root","127127");
	        myhandler.getAirline(20100908,"北京","上海");
	        
	        
	    }
	}
