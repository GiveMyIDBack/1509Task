package CS;

import java.io.*;
import java.net.*;
import java.util.*;


/**
 * 服务器端线程类
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 主要函数清单：
 * run()
 * 
 */

class ThreadedHandler implements Runnable {
	private Socket incoming;//要接收的客户线程
	private DataInputStream in;//输入流
	private DataOutputStream out;//输出流
	CommandHelper helper;//解析接收到的消息

	/**
	 * 
	 * 构造方法
	 * 
	 * 初始化以及获取输入输出流
	 * 
	 * 
	 */
	public ThreadedHandler(Socket i) throws IOException {
		incoming = i;//接收到的客户端套接字
		in = new DataInputStream(incoming.getInputStream());//获取输入流
		out = new DataOutputStream(incoming.getOutputStream());//获取输出流
		helper = new CommandHelper();//Commandhelper实例

	}

	/**
	 * 
	 * 循环读取客户端发来的消息，处理并返回处理结果
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
	public void run() {
		try {
			while (true) {
				String line = in.readUTF();//读取来自客户端的消息
				/*若接收到的消息不为空，处理消息*/
				if (line.length() > 0) {
					String result = helper.getRespond(line);//调用helper解析并处理消息
					out.writeUTF(result);//向客户端发送处理结果
					out.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();//关闭输入流
				out.close();//关闭输出流
				incoming.close();//关闭客户端套接字
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
