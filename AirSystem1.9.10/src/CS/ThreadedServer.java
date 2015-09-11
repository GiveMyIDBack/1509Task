package CS;

import java.io.*;
import java.net.*;
import java.util.*;
import GUI.Login;


public class ThreadedServer
{
   public static void main(String[] args )
   {
     try
     {
         int i = 1;
        ServerSocket s = new ServerSocket(5008);
        
        
        while (true)
        {
           Socket incoming = s.accept();
           System.out.println("Spawning " + i);
           Runnable r = new ThreadedHandler(incoming);
           Thread t = new Thread(r);
           t.start();
           i++;
         }
    }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
}
