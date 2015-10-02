package CS;

import java.io.*;
import java.net.*;
import java.util.*;
import GUI.Login;

/**
 * 服务器端后台 接收客户端套接字，为每个客户新建线程
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 
 * 
 */
public class ThreadedServer {
	
	/**
	 * 
	 * 服务器端main方法
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public static void main(String[] args) {
		try {
			int i = 1;// 统计客户端数量
			ServerSocket s = new ServerSocket(5008);// 创建用来实现服务端套接字的对象

			while (true) {
				Socket incoming = s.accept();// 接收来自客户端套接字请求
				/* 为处理该客户请求新开一个线程 */
				Runnable r = new ThreadedHandler(incoming);
				Thread t = new Thread(r);
				t.start();// 启动线程
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
