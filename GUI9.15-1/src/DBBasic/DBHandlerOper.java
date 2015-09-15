package DBBasic;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class DBHandlerOper {
        DBUtil handler;
        Connection conn;
        PreparedStatement ps;// PreparedStatement,Statement
        ResultSet rs;

     	public DBHandlerOper(){
     	handler=new DBUtil();
     	handler.getConn("root", "127127");
     	conn=handler.conn;
     	ps=null;
         }
     	
     	public String addClient(String CurrentIDNum,String CurrentPhone,String CurrentName,String CurrentSid){
     		String result="";
     		System.out.println("+++"+CurrentSid+"+++");
     		if(handler.checkIDNDup(CurrentIDNum)){
	    		System.out.println("该身份证号已注册");
	    		return "1";
	    	}
	    	
	    	if(handler.checkPhoneDup(CurrentPhone)){
	    		System.out.println("该手机号已注册");
	    		return "2";
	    	}
	    	
	    	if(CurrentSid.length()!=0){
	    		if(!handler.checkS_exist(CurrentSid)){
	    			System.out.println("该业务员不存在");
		    		return "3";
	    		}
	    	}
	    	
	    	String CurrentCid= "C" + handler.getAndUpdateTail("cid");
	    	
	    	String sql="";
	    	
	    	try {
	    		sql=" INSERT INTO client VALUES ( ? , ? , ? , ? )  ";
	        	
	        	ps = conn.prepareStatement(sql);
	        	
	            ps.setString(1, CurrentCid);
	           	ps.setString(2, CurrentIDNum);
	            ps.setString(3, CurrentPhone);		           
	            ps.setString(4, CurrentName);
	            
	            int rs1=ps.executeUpdate();  
	            int rs2=10;
	            if(CurrentSid.length()!=0){
	            	sql=" INSERT INTO business_c2s VALUES ( ? , ?  )  ";
		        	
		        	ps = conn.prepareStatement(sql);
		        	
		        	
		        	
		            ps.setString(1, CurrentCid);
		           
		            ps.setString(2, CurrentSid);
		             
		            rs2=ps.executeUpdate();  
	    		
	        	
	            }
	            
	            
	            if((rs1!=0)&&(rs2!=0)){
	            	
	            	System.out.println("成功添加新客户");
	            	result="4"+CurrentCid;
	            }
	            else{
	            	System.out.println("错误，请重新登记");
	            	result="8";
	            }
				
	     
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
	    	return result;
	    }
     	
     	public String searchClientByPhone(String CurrentPhone){
     		String result="";
	        try {
	        	String sql="";
	        	
	        	sql = "select * from client  "
	        	+"where phone = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentPhone);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	            String cid="";
	            String identity_number = "";	                
	            String name="";
	            
				int count=0;
				while (rs.next()) {
					count++;
					cid=rs.getString("cid");
					identity_number = rs.getString("identity_number");
					name = rs.getString("name");
					
					System.out.println(cid+"  "+identity_number+"  "
							+CurrentPhone+"  "+name);
					result+="0"+cid+"  "+identity_number+"-"
							+CurrentPhone+"-"+name;
					
					
					
				}
				if(count==0){
					System.out.println("没有匹配结果");
					result="1";
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
     	
     	public String searchClientByIDNum(String CurrentIDNum){
     		String result="";
     		
	        try {
	        	String sql="";
	        	
	        	sql = "select * from client  "
	        	+"where identity_number = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentIDNum);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	            String cid="";
	            String phone = "";	 
	            String name="";
	            
				int count=0;
				while (rs.next()) {
					count++;
					cid=rs.getString("cid");
					phone = rs.getString("phone");
					name = rs.getString("name");
					
					System.out.println(cid+"  "+CurrentIDNum+"  "
							+phone+"  "+name);
					result="0"+cid+"-"+CurrentIDNum+"-"
							+phone+"-"+name;
					
					
					
				}
				if(count==0){
					System.out.println("没有匹配结果");
					result="1";
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
     	
     	public String searchClientByCid(String CurrentCid){
     		String result="";
     		
	        try {
	        	String sql="";
	        	
	        	sql = "select * from client  "
	        	+"where cid = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentCid);
	            
	            
	            ResultSet rs = ps.executeQuery();  
	            
	            String identity_number="";
	            String phone = "";	 
	            String name="";
	            
				int count=0;
				while (rs.next()) {
					count++;
					identity_number=rs.getString("identity_number");
					phone = rs.getString("phone");
					name = rs.getString("name");
					
					System.out.println(CurrentCid+"  "+identity_number+"  "
							+phone+"  "+name);
					result="0"+CurrentCid+"-"+identity_number+"-"
							+phone+"-"+name;
					
					
					
				}
				if(count==0){
					System.out.println("没有匹配结果");
					result="1";
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
     	
}
