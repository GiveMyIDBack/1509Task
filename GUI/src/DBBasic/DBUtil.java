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


public class DBUtil {
	

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
	    public static String getFormat(int num){
	    	 DecimalFormat template=new DecimalFormat("00000000"); 
	    	 String StrNum=template.format(num);  
	    	 System.out.println(StrNum);
	    	 return StrNum;
	    }
	    
	    
	    public String getAndUpdateTail(String CurrentVar){
	    	int tail=0;
	    	String sql="";
	        try {
	        	
	        	sql = "select tail from metadata  "
	        	+" where var = ?  ";
	    		
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentVar);
	            
	            
	            rs = ps.executeQuery();  
	            
	           
				while (rs.next()) {
					tail = rs.getInt("tail");
					
					
					
				}
				rs.close();
				
				//更新tail
				
				sql = "update metadata set tail= ?  "
			        	+"where var = ?  ";
			    		
			        	
			        	ps = conn.prepareStatement(sql);
			        	ps.setInt(1, tail+1);
			            ps.setString(2, CurrentVar);
			            
			            
			            int rs = ps.executeUpdate();  
			            
						
						if(rs!=0)
							
							System.out.println("tail查询成功");
						else
							System.out.println("tail查询失败");
				
	            
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
	        return DBUtil.getFormat(tail);
	    }
	    
	    public String getBusiSalesman(String CurrentCid){
	    	String sid= "";
	        try {
	        	
	        	String sql = " select sid from business_c2s where cid =  ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentCid);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
				
				while (rs.next()) {
					sid = rs.getString("sid");
					
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
	       
			return sid;
	       
	    }
	    
	    public int[] getSeat(String CurrentType){
	    	int [] seat=new int[3];
	        try {
	        	
	        	String sql = " select first_num , business_num , economic_num  "
	        	+" from aircraft where type  =  ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentType);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
				
				while (rs.next()) {
					seat[0] = rs.getInt("first_num");
					seat[1] = rs.getInt("business_num");
					seat[2]= rs.getInt("economic_num");
					
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
	       
			return seat;
	       
	    }
	    
	    public boolean checkIDNDup(String CurrentIDNum){
	    	if(CurrentIDNum.length()!=18){
	    		System.out.println("请正确输入身份证号");
	    		return true;
	    	}
	    	boolean flag=false;
	    	String sql="";
	        try {
	        	
	        	sql = "select * from client  "
	        	+" where identity_number = ?  ";
	    		
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentIDNum);
	            
	            
	            rs = ps.executeQuery();  
	            
	           
				if (rs.next()) {
					flag=true;
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
	        return flag;
	    }
	    
	    public boolean checkPhoneDup(String CurrentPhone){
	    	boolean flag=false;
	    	String sql="";
	        try {
	        	
	        	sql = "select * from client  "
	        	+" where phone = ?  ";
	    		
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentPhone);
	            
	            
	            rs = ps.executeQuery();  
	            
	           
				if (rs.next()) {
					flag=true;
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
	        return flag;
	    }
	    
	    public boolean checkIDNDupExceptSelf(String CurrentIDNum,String CurrentCid){
	    	if(CurrentIDNum.length()!=18){
	    		System.out.println("请正确输入身份证号");
	    		return true;
	    	}
	    	
	    	boolean flag=false;
	    	String sql="";
	        try {
	        	
	        	sql = "select * from client  "
	        	+" where identity_number = ? and cid <> ?   ";
	    		
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentIDNum);
	            ps.setString(2, CurrentCid);
	            
	            
	            rs = ps.executeQuery();  
	            
	           
				if (rs.next()) {
					flag=true;
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
	        return flag;
	    }
	    
	    public boolean checkPhoneDupExceptSelf(String CurrentPhone,String CurrentCid){
	    	boolean flag=false;
	    	String sql="";
	        try {
	        	
	        	sql = "select * from client  "
	        	+" where phone = ?  "
	        	+" and cid <> ?  ";
	    		
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentPhone);
	            ps.setString(2, CurrentCid);
	            
	            
	            rs = ps.executeQuery();  
	            
	           
				if (rs.next()) {
					flag=true;
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
	        return flag;
	    }
	    
	    public boolean checkS_exist(String CurrentSid){
	    	boolean flag=false;
	        try {
	        	String sql="";
	        	
	        	sql = "select * from salesman  "
	        	+"where sid = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentSid);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	           
	            
				
				if (rs.next()) {
					
					flag=true;
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
	        return flag;
	    }
	    
	    public boolean checkTYPE_exist(String CurrentType){
	    	boolean flag=false;
	        try {
	        	String sql="";
	        	
	        	sql = "select * from aircraft  "
	        	+"where type = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentType);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	           
	            
				
				if (rs.next()) {
					
					flag=true;
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
	        return flag;
	    }
	    
	    public boolean checkD_exist(String CurrentDid){
	    	boolean flag=false;
	        try {
	        	String sql="";
	        	
	        	sql = "select * from deliveryman  "
	        	+"where did = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentDid);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	           
	            
				
				if (rs.next()) {
					
					flag=true;
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
	        return flag;
	    }
	    
	    public boolean checkT_exist(String CurrentTid){
	    	boolean flag=false;
	        try {
	        	String sql="";
	        	
	        	sql = "select * from ticketbox  "
	        	+"where tid = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentTid);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	           
	            
				
				if (rs.next()) {
					
					flag=true;
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
	        return flag;
	    }
	    
	    public boolean checkA_exist(String CurrentAid){
	    	boolean flag=false;
	        try {
	        	String sql="";
	        	
	        	sql = "select * from airline  "
	        	+"where aid = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentAid);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	           
	            
				
				if (rs.next()) {
					
					flag=true;
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
	        return flag;
	    }
	    
	    public boolean checkC_exist(String CurrentCid){
	    	boolean flag=false;
	        try {
	        	String sql="";
	        	
	        	sql = "select * from client  "
	        	+"where cid = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentCid);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	           
	            
				
				if (rs.next()) {
					
					flag=true;
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
	        return flag;
	    }
	    
	    public boolean checkO_exist(String CurrentOid){
	    	boolean flag=false;
	        try {
	        	String sql="";
	        	
	        	sql = "select * from operator  "
	        	+"where oid = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentOid);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	           
	            
				
				if (rs.next()) {
					
					flag=true;
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
	        return flag;
	    }
	    
	    public boolean checkAdmin_exist(String CurrentID){
	    	boolean flag=false;
	        try {
	        	String sql="";
	        	
	        	sql = "select * from admin  "
	        	+"where admin_id = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentID);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	           
	            
				
				if (rs.next()) {
					
					flag=true;
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
	        return flag;
	    }
	    
	    public boolean checkOrder_exist(String CurrentOrderid){
	    	boolean flag=false;
	        try {
	        	String sql="";
	        	
	        	sql = "select * from orders  "
	        	+"where orderid = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentOrderid);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	           
	            
				
				if (rs.next()) {
					
					flag=true;
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
	        return flag;
	    }
	    
	    public String randomDeliveryman(){
	    	
	    	
	    	String sql="";
	    	int num=0;
	    	String did="";
	        try {
	        	
	        	sql="select count(*) from deliveryman";
	        	ps = conn.prepareStatement(sql);
	        	rs = ps.executeQuery();  
	        	
	        	while (rs.next()) {
	        		num=rs.getInt("count(*)");
	        	}
	        	
	        	sql="select did from deliveryman";
	    		
	        	
	        	ps = conn.prepareStatement(sql);
	           
	            int stop=(int)(1+Math.random()*(num-1+1));
	            
	            rs = ps.executeQuery();  
	            
	           
				while (rs.next()&&stop>=1) {
					stop--;
					did = rs.getString("did");
					
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
	        return did;
	    }
	    	
	    	
	    
	    

	    
	    
	    
	    public static void main(String[] args) {
	        DBUtil myhandler = new DBUtil();
	        myhandler.getConn("root","127127");
	       System.out.println(myhandler.checkA_exist("ZH333"));
	        //myhandler.bookTicket("003","MU375","ECONOMIC","17A","002","001");
	        //myhandler.cancelTicket("4F38B03F67E5493A977439AF8893B581");
	        
	        
	        try {
               if(myhandler.conn!=null) myhandler.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
	       
	        
	        
	    }
	    
}
