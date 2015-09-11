package DemoCS4;


import java.io.*;
import java.util.*;
import java.net.*; 
 
public class Client { 

	  

    public static void main(String[] args) throws Exception{ 

        Socket s = new Socket("localhost", 5000); 

        InputStream in = s.getInputStream(); 

        OutputStream out = s.getOutputStream(); 

        ObjectOutputStream oos = new ObjectOutputStream(out); 

        ObjectInputStream ois = new ObjectInputStream(in); 
        
        //给server 发消息

        SocketMessage message = new SocketMessage(); 

        //message.setMessageId(2); 

        message.setContent("客户端消息2"); 

        oos.writeObject(message); 

        oos.flush(); 
        //从server 处收消息

        SocketMessage temp = (SocketMessage) ois.readObject(); 

        System.out.println("收到服务端消息=" + temp); 

          

    } 

  

}
/*
public class Client {

    private Socket s;
    private BufferedReader br;
    //private BufferedReader line;
    private PrintWriter pw;
    private String line = "";
    public Client() {
        try{
            s = new Socket("127.0.1.2",8189);
            pw = new PrintWriter(s.getOutputStream(),true);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw.println("hello");
            line = br.readLine();
            System.out.println("client:"+line);

            br.close();
            pw.close();
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        new Client();
    }
}
 */