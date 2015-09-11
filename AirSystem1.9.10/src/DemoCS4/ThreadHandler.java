package DemoCS4;

import java.io.*;
import java.net.*;
import java.util.*;
class ThreadedHandler implements Runnable
{
  
  public ThreadedHandler(Socket i)
   {
      incoming = i;
   }

   public void run()
   {
     try
      {
         try
         {
           InputStream inStream = incoming.getInputStream();
           OutputStream outStream = incoming.getOutputStream();
           
           ObjectOutputStream oos = new ObjectOutputStream(outStream); 

           ObjectInputStream ois = new ObjectInputStream(inStream);
           
           SocketMessage message = new SocketMessage(); 
           
           //给client发消息

           message.setMessageId(1); 

           message.setContent("服务器消息1"); 

           oos.writeObject(message); 

           oos.flush(); 
           
           //收来自client的消息

           SocketMessage temp = (SocketMessage) ois.readObject();

           System.out.println("收到客户端消息=" + temp);

            Scanner in = new Scanner(inStream);
            PrintWriter out = new PrintWriter(outStream, true );

            //out.println( "Hello! Enter BYE to exit." );

            
       
        
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally
        {
            incoming.close();
     }
      }
     catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   private Socket incoming;
}


