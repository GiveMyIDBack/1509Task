package CS;

import java.io.*;
import java.net.*;
import java.util.*;


import DBBasic.*;
class ThreadedHandler implements Runnable
{
  private Socket incoming;
  private DataInputStream in;
  private DataOutputStream out;
  DBLoginHandler handler;
  CommandHelper helper;
	
	
  public ThreadedHandler(Socket i) throws IOException
   {
      incoming = i;
      in=new DataInputStream(incoming.getInputStream());
      out=new DataOutputStream(incoming.getOutputStream());
      handler=new DBLoginHandler();
      helper=new CommandHelper();

   }

   public void run()
   {
    
         try
         {
        	 while(true){
        		 //br = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
                 //pw = new PrintWriter(incoming.getOutputStream(),true);
                 String line = in.readUTF();
                 System.out.println(line);
                 System.out.println("i am server i get "+line);
                 
                 if(line.length()>0){
                	 System.out.println("begin to call helper");
                	 String result=helper.getRespond(line);
                	 System.out.println("helper gave me this :"+result);
                	 out.writeUTF(result+"");
                     out.flush();
                     System.out.println("2222");
                 }
                 
                 /*
                 
                 if(line.length()>1){
                	 String[] input = line.split("-");
                     
                     for (int i = 0; i < 2; i++) {
                         System.out.println(input[i]);
                     }
                     int result=handler.login(input[0], input[1]);
                     

                     //System.out.println("1");
                     //System.out.println(line);
                     
                     out.writeUTF(result+"");
                     out.flush();
                     System.out.println("2222");
                    
                 }
                  */
        	 }
        	 
             
             
        	
        	   

        
		} catch (IOException e){
	         e.printStackTrace();
	      }
        
        finally
        {
            try {
            	in.close();
                out.close(); 
				incoming.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     }
      
     
   }
}

   



