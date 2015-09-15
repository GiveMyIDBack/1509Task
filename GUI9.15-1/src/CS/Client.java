package CS;


import java.io.*;
import java.util.*;

import GUI.Login;

import java.net.*; 
 
public class Client { 
	
	Socket s ; 
	private DataInputStream in;
	private DataOutputStream out;

    public Client(){
    	
    	
    	
    	try {
			s = new Socket("localhost", 5008);
			in=new DataInputStream(s.getInputStream());
		    out=new DataOutputStream(s.getOutputStream());
			
	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
    }
    
   
    
    public void sendMessage(String mes){
    	try {
    		System.out.println("i am client's sendmes,the mes is  "+mes);

    		out.writeUTF(mes);
    		
    		

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	System.out.println("i finish "+ mes);
  
    	
    }
      public String receiveMessage(){
    	  String line="";
    	
        try {
        	
        	line=in.readUTF();
            System.out.println("client receiver from  server:"+line);
        	
			  

            //System.out.println("收到服务端消息=" + recemessage); 
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
  
    	return line;
    }
	
	

	  

    public static void main(String[] args) throws Exception{ 
    	
    	/*

    	Socket s = new Socket("localhost", 5000); 

        InputStream in = s.getInputStream(); 

        OutputStream out = s.getOutputStream(); 

        ObjectOutputStream oos = new ObjectOutputStream(out); 

        ObjectInputStream ois = new ObjectInputStream(in); 
        
        //给server 发消息

        SocketMessage message = new SocketMessage(); 

        message.setMessageId(2); 

        message.setContent("客户端消息2"); 

        oos.writeObject(message); 

        oos.flush(); 
        //从server 处收消息

        SocketMessage temp = (SocketMessage) ois.readObject(); 

        System.out.println("收到服务端消息=" + temp); 
        */
    	/*
    	Client me=new Client();
    	//System.out.println("关了没啊" );
    	System.out.println( me.s.isClosed());
    	String sql1="hi,i am client";
    	String sql2="hi,i am client again";
    	me.sendMessage(sql1);
    	//me.sendMessage(sql2);
    	me.receiveMessage();
    	me.sendMessage(sql2);
    	
    	Client me=new Client();
    	Login log=new Login();
    	String idpw=log.getID()+"-"+log.getPassword();
    	me.sendMessage(idpw);
    	*/

          

    } 

  

}
