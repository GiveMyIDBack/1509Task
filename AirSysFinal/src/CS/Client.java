package CS;

import java.io.*;
import java.util.*;
import GUI.Login;
import java.net.*;

/**
 * 客户端后台 创建套接字，输入输出流 提供向服务器发送消息、接受消息的方法
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 主要函数清单：
 * receiveMessage()
 * sendMessage(String)
 * 
 */
public class Client {

	Socket s;//连接端口
	private DataInputStream in;//输入流对象
	private DataOutputStream out;//输出流对象

	/**
	 * 
	 * 构造方法
	 * 
	 * 建立套接字，获取输入输出流
	 * 
	 * 
	 */
	public Client() {

		try {
			s = new Socket("localhost", 5008);// 与服务器建立套接字连接
			in = new DataInputStream(s.getInputStream());// 获取输入流
			out = new DataOutputStream(s.getOutputStream());//获取输出流

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 向服务器发送消息
	 * 
	 * @param mes
	 *            消息字符串
	 * 
	 * @return void
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public void sendMessage(String mes) {
		try {

			out.writeUTF(mes);//输出给服务器mes

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 从服务器端接收消息
	 * 
	 * @param null
	 * 
	 * @return line 接收到的字符串
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */

	public String receiveMessage() {
		String line = "";

		try {

			line = in.readUTF();// 读取服务器发送的内容

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return line;
	}

}
