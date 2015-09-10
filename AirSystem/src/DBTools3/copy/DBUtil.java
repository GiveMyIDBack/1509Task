package DBTools3.copy;






import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;


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
	    
	    
	    public static void main(String[] args) {
	        DBUtil myhandler = new DBUtil();
	        myhandler.getConn("root","127127");
	       //myhandler.getAirline(20100908,"北京","上海");
	        //myhandler.bookTicket("003","MU375","ECONOMIC","17A","002","001");
	        //myhandler.cancelTicket("4F38B03F67E5493A977439AF8893B581");
	        
	        System.out.println(myhandler.getAndUpdateTail("cid"));
	        try {
               if(myhandler.conn!=null) myhandler.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
	       
	        
	        
	    }
}