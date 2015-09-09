package DBTools3.copy;


import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;



public class DBLogin {


	 
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/airsystem";
	    Connection conn=null;
	    PreparedStatement ps=null;//PreparedStatement,Statement
	    ResultSet rs;
	    DBUtil handler;
	    
	    public DBLogin(){
	    	handler=new DBUtil();
	    	handler.getConn("root","127127");
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
	    public void login(String CurrentCid,String CurrentPassword){
	    	try {
	        	
	        	String sql = "select cid, password from membership  "
	        	+"where cid = ?  "
	    		+"and password = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentCid);
	            ps.setString(2, CurrentPassword);
	            
	            ResultSet rs = ps.executeQuery();  
	            
				if (rs.next()) {
					
					System.out.println("WELCOME  "+CurrentCid);
				}
				else
					System.out.println("密码错误");
				rs.close();
				
	     
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
					e.printStackTrace();
			}finally{

	            try {
	                if (ps!=null) ps.close();
	                
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    	
	    public void register(String CurrentIDNum,String CurrentPhone,String CurrentName,String CurrentPassword ){
	    	try {
	        	
	        	String sql="";
	        	
	        	sql=" INSERT INTO membership VALUES ( ? , ?  )  ";
	        	
	        	ps = conn.prepareStatement(sql);
	        	
	        	String CurrentCid= "C" + handler.getAndUpdateTail("cid");
	        	
	            ps.setString(1, CurrentCid);
	           
	            ps.setString(2, CurrentPassword);
	             
	            int rs=ps.executeUpdate();  
	            
	            sql=" INSERT INTO client VALUES ( ? , ? ,? , ? )  ";
	        	
	        	ps = conn.prepareStatement(sql);
	        	
	            ps.setString(1, CurrentCid);
	           	ps.setString(2, CurrentIDNum);
	            ps.setString(3, CurrentPhone);		           
	            ps.setString(4, CurrentName);
	            
	            int rs2=ps.executeUpdate();  
	            
	            if((rs!=0)&&(rs2!=0))
	            	
	            	System.out.println("成功注册新用户");
	            else
	            	System.out.println("错误，请重新注册");
				
	     
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
					e.printStackTrace();
			}finally{

	            try {
	                if (ps!=null) ps.close();
	                
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    public static void main(String[] args) {
	        
	        
	       //myhandler.getAirline(20100908,"北京","上海");
	        //myhandler.bookTicket("003","MU375","ECONOMIC","17A","002","001");
	        //myhandler.cancelTicket("4F38B03F67E5493A977439AF8893B581");
	        DBLogin myhandler=new DBLogin();
	        myhandler.getConn("root","127127");
	        
	        myhandler.login("333" , "333");
	        myhandler.register("440106199402031823", "15957772389", "刘花花", "311");
	        try {
               if(myhandler.conn!=null) myhandler.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
	       
	        
	        
	    }
	    

}