package DBBasic;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;



public class DBRetrieve {

	    DBUtil handler;
	    Connection conn;
	    PreparedStatement ps;// PreparedStatement,Statement
		ResultSet rs;

		public DBRetrieve(){
		handler=new DBUtil();
		handler.getConn("root", "127127");
		conn=handler.conn;
		ps=null;
	    }
	    
	    public String getClientName(String CurrentCid){
	    	System.out.println(CurrentCid);
	    	String name = "";
	        try {
	        	
	        	String sql = " select Name from client where cid =  ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentCid);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
				
				while (rs.next()) {
					name = rs.getString("name");
					
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
	       
			return name;
	       
	    }
	    public String getClientIDNum(String CurrentCid){
	    	
	    	String identity_number = "";
	        try {
	        	
	        	String sql = " select identity_number from client where cid = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentCid);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
				
				while (rs.next()) {
					identity_number = rs.getString("identity_number");
					
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
	       
			return identity_number;
	       
	    }
	    
	    public String getClientPhone(String CurrentCid){
	    	String phone = "";
	        try {
	        	
	        	String sql = " select phone from client where cid =  ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentCid);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
				
				while (rs.next()) {
					phone = rs.getString("phone");
					
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
	       
			return phone;
	       
	    }
	    
	    
	    public static void main(String[] args) {
	        
	        
	       //myhandler.getAirline(20100908,"北京","上海");
	        //myhandler.bookTicket("003","MU375","ECONOMIC","17A","002","001");
	        //myhandler.cancelTicket("4F38B03F67E5493A977439AF8893B581");
	        DBLoginHandler myhandler=new DBLoginHandler();
	        
	        
	        //myhandler.login("333" , "333");
	        //myhandler.register("440106199402031829", "159543272389", "刘花花", "3112334");
	        myhandler.register("1", "1", "1", "1111");
	        try {
               if(myhandler.conn!=null) myhandler.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
	       
	        
	        
	    }
	    

}
