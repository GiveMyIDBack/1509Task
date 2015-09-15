package DBBasic;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;



public class DBLoginHandler {

	    DBUtil handler;
	    Connection conn;
	    PreparedStatement ps;// PreparedStatement,Statement
		ResultSet rs;

		public DBLoginHandler(){
		handler=new DBUtil();
		handler.getConn("root", "127127");
		conn=handler.conn;
		ps=null;
	    }
	    
	    
	    //自己注册的会员登录
	    public int login(String CurrentID,String CurrentPassword){
	    	int flag=0;
	    	System.out.println(CurrentID+"  "+CurrentPassword);
	    	
	    	if(!handler.checkC_exist(CurrentID)&&!handler.checkO_exist(CurrentID)&&!handler.checkAdmin_exist(CurrentID)){
	    		System.out.println("该账户不存在");
	    		return flag;
	    	}
	    	
	    	try {
	        	
	        	String sql = "select * from membership  "
	        	+"where cid = ?  "
	    		+"and password = ?  ";
	        	
	        	ps = conn.prepareStatement(sql);
	            ps.setString(1, CurrentID);
	            ps.setString(2, CurrentPassword);
	            
	            ResultSet rs = ps.executeQuery();  
	            
				if (rs.next()) {
					
					System.out.println("WELCOME  客户 "+CurrentID);
					flag=1;
				}
                else {
				// System.out.println("密码错误");
				// 是operator吗？
				    sql = "select * from operator  " 
				            + "where oid = ?  " 
				    		+ "and password = ?  ";
      
				    ps = conn.prepareStatement(sql);
				    ps.setString(1, CurrentID);
					ps.setString(2, CurrentPassword);

					rs = ps.executeQuery();

					if (rs.next()) {

						System.out.println("WELCOME  操作员 " + CurrentID);
						flag=2;
					}
					else{
						sql = "select * from admin  " 
					            + "where admin_id = ?  " 
					    		+ "and password = ?  ";
	      
					    ps = conn.prepareStatement(sql);
					    ps.setString(1, CurrentID);
						ps.setString(2, CurrentPassword);

						rs = ps.executeQuery();

						if (rs.next()) {

							System.out.println("WELCOME  管理员 " + CurrentID);
							flag=3;
							
						}
						else{
							System.out.println("登录失败，请重试 ");
							flag=8;
						}
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
	                
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    	return flag;
	    }
	    //用户自己注册成会员
	    public String register(String CurrentName,String CurrentIDNum,String CurrentPhone,String CurrentPassword ){
	    	System.out.println("i am dbhandler register");
	    	System.out.println(CurrentName);
	    	System.out.println(CurrentIDNum);
	    	System.out.println(CurrentPhone);
	    	System.out.println(CurrentPassword);
	    	String result="";
	    	if(CurrentIDNum.length()!=18){
	    		System.out.println("请正确输入身份证号");
	    		return "0";
	    	}
	    	if(handler.checkIDNDup(CurrentIDNum)){
	    		System.out.println("该身份证号已注册");
	    		return "1";
	    	}
	    	
	    	if(handler.checkPhoneDup(CurrentPhone)){
	    		System.out.println("该手机号已注册");
	    		return "2";
	    	}
	    	
	    	if(CurrentPassword.length()<4||CurrentPassword.length()>10){
	    		System.out.println("密码长度有误，请输入4~10位密码");
	    		return "3";
	    	}
	    	
	    	
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
	            
	            if((rs!=0)&&(rs2!=0)){
	            	result="4"+CurrentCid;
	            	System.out.println("成功注册新用户");
	            }
	            else{
	            	result="8";
	            	System.out.println("错误，请重新注册");
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
