package CS;

import java.io.*;
import java.net.*;
import java.util.*;

import DBBasic.DBLoginHandler;
class ThreadedHandler implements Runnable
{
  private Socket incoming;
  private DataInputStream in;
  private DataOutputStream out;
  DBLoginHandler handler;
	
	
  public ThreadedHandler(Socket i) throws IOException
   {
      incoming = i;
      in=new DataInputStream(incoming.getInputStream());
      out=new DataOutputStream(incoming.getOutputStream());
      handler=new DBLoginHandler();

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

   



