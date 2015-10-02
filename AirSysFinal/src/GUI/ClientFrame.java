package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import CS.Client;
import Tools.MakeMatrix;

/**
 * 客户界面
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 
 * 
 */
public class ClientFrame extends JFrame {

	private JPanel contentPane;// 窗体的contentPane对象
	private JPanel pSearchAirline;// 查询航班功能面板
	private JPanel pBookAirline;// 订票功能面板
	private JPanel pCancelAirline;// 退票功能面板
	private JPanel pProfileUpdate;// 修改个人信息功能面板
	private JPanel pPrintSchedule;// 打印个人行程功能面板
	private CS.Client me;// 客户端对象，用于与服务器交互
	private String cid;// 客户id

	/**
	 * 
	 * 构造方法
	 * 
	 * 创建客户窗体
	 * 
	 * 
	 */
	public ClientFrame(Client current, String currentcid) {
		me = current;// 获取客户端对象
		cid = currentcid;// 获取当前客户id

		/* 设置窗体属性 */
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1502, 734);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/* 设置背景图片 */
		ImageIcon bg = new ImageIcon("msn_background.jpg");
		JLabel label = new JLabel(bg);
		label.setBounds(0, 0, 1502, 734);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		/*新建底版，承载各功能面板*/
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(12, 158, 1502, 734);
		contentPane.add(panel);
		panel.setLayout(null);

		/*
		 * 
		 * 查询航班功能面板
		 * 
		 * 
		 */

		pSearchAirline = new JPanel();
		pSearchAirline.setOpaque(false);
		pSearchAirline.setBounds(12, 30, 1478, 677);
		panel.add(pSearchAirline);
		pSearchAirline.setLayout(null);

		pSearchAirline.setVisible(false);

		/* 子面板1：输入面板 */
		JPanel pSearchAirlineIn = new JPanel();
		pSearchAirlineIn.setOpaque(false);
		pSearchAirlineIn.setBounds(563, 22, 270, 205);
		pSearchAirline.add(pSearchAirlineIn);
		pSearchAirlineIn.setLayout(null);

		/* 日期标签 */
		JLabel pSAI_L_DATE = new JLabel("日期");
		pSAI_L_DATE.setBounds(43, 8, 81, 21);
		pSearchAirlineIn.add(pSAI_L_DATE);

		/* 日期文本域 */
		JTextField pSAI_T_DATE = new JTextField();
		pSAI_T_DATE.setBounds(144, 5, 96, 27);
		pSAI_T_DATE.setColumns(10);
		pSearchAirlineIn.add(pSAI_T_DATE);

		/* 起点标签 */
		JLabel pSAI_L_ORIGIN = new JLabel("起点");
		pSAI_L_ORIGIN.setBounds(43, 50, 81, 21);
		pSearchAirlineIn.add(pSAI_L_ORIGIN);

		/* 起点文本域 */
		JTextField pSAI_T_ORIGIN = new JTextField();
		pSAI_T_ORIGIN.setBounds(144, 47, 96, 27);
		pSAI_T_ORIGIN.setColumns(10);
		pSearchAirlineIn.add(pSAI_T_ORIGIN);

		/* 终点标签 */
		JLabel pSAI_L_DEST = new JLabel("终点");
		pSAI_L_DEST.setBounds(43, 92, 81, 21);
		pSearchAirlineIn.add(pSAI_L_DEST);

		/* 终点文本域 */
		JTextField pSAI_T_DEST = new JTextField();
		pSAI_T_DEST.setBounds(144, 89, 96, 27);
		pSAI_T_DEST.setColumns(10);
		pSearchAirlineIn.add(pSAI_T_DEST);

		/* 查询按钮 */
		JButton pSAI_SEARCH = new JButton("查询");
		pSAI_SEARCH.setBounds(68, 142, 123, 29);
		pSearchAirlineIn.add(pSAI_SEARCH);

		/* 子面板2：输出面板 */
		JScrollPane pSearchAirlineOut = new JScrollPane();
		pSearchAirlineOut.setBounds(15, 48, 1429, 177);
		pSearchAirline.add(pSearchAirlineOut);

		/* 查询按钮响应事件 */
		pSAI_SEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* 检查日期格式是否合法 */
				if (pSAI_T_DATE.getText().trim().length() != 8) {
					JOptionPane.showMessageDialog(null, "请输入有效日期", "查询航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				/* 检查起点是否为空 */
				if (pSAI_T_ORIGIN.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "起点不能为空", "查询航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				/* 检查终点是否为空 */
				if (pSAI_T_DEST.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "终点不能为空", "查询航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				/* 编辑文本域获取的输入，发送给服务器 */
				String mes = "07" + pSAI_T_DATE.getText().trim() + "-" + pSAI_T_ORIGIN.getText().trim() + "-"
						+ pSAI_T_DEST.getText().trim();
				me.sendMessage(mes);
				/* 接收服务器回复的消息 */
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);// 提取返回值

				switch (sig) {
				/* 若返回值为2，没有匹配航班 */
				case "2":
					JOptionPane.showMessageDialog(null, "没有匹配航班", "查询航班", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为0，有直达航班，返回的结果串编辑后显示在输出面板上 */
				case "0":
					int count = Integer.parseInt(result.substring(1, 3));
					JOptionPane.showMessageDialog(null, "共有 " + count + " 条结果", "查询航班", JOptionPane.CLOSED_OPTION);
					String output = result.substring(3);
					/* 将结果串处理成矩阵，后处理成表结构显示 */
					String[][] table = MakeMatrix.makeMatrix(output, count, 10);
					String[][] prow = new String[0][10];
					String[] pcol = { "序号", "航班号", "起点", "终点", "日期", "时间", "头等舱剩余座位", "商务舱剩余座位", "经济舱剩余座位", "机型" };
					DefaultTableModel pmodel = new DefaultTableModel(prow, pcol);
					for (int i = 0; i < table.length; i++) {
						pmodel.addRow(table[i]);
					}
					JTable ptable = new JTable(pmodel);
					pSearchAirlineOut.setViewportView(ptable);
					/* 隐藏输入面板，显示输出面板 */
					pSearchAirlineIn.setVisible(false);
					pSearchAirlineOut.setVisible(true);
					/* 文本域清空 */
					pSAI_T_DATE.setText("");
					pSAI_T_ORIGIN.setText("");
					pSAI_T_DEST.setText("");
					break;
				/* 若返回值为1，有转机组合，返回的结果串编辑后显示在输出面板上 */
				case "1":
					/*消息框显示组合数*/
					int count2 = Integer.parseInt(result.substring(1, 3));
					JOptionPane.showMessageDialog(null, "没有直航航班，建议您转机前往，共有 " + count2 + " 种组合", "查询航班",
							JOptionPane.CLOSED_OPTION);
					String output2 = result.substring(3);
					/* 将结果串处理成矩阵，后处理成表结构显示 */
					String[][] table2 = MakeMatrix.makeMatrix(output2, count2 * 2, 10);
					String[][] prow2 = new String[0][10];
					String[] pcol2 = { "序号", "航班号", "起点", "终点", "日期", "时间", "头等舱剩余座位", "商务舱剩余座位", "经济舱剩余座位", "机型" };
					String[] blank2 = { " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };
					DefaultTableModel pmodel2 = new DefaultTableModel(prow2, pcol2);
					for (int i = 0; i < table2.length; i++) {
						if (i != 0 && i % 2 == 0)
							pmodel2.addRow(blank2);
						pmodel2.addRow(table2[i]);

					}
					JTable ptable2 = new JTable(pmodel2);
					pSearchAirlineOut.setViewportView(ptable2);
					/* 隐藏输入面板，显示输出面板 */
					pSearchAirlineIn.setVisible(false);
					pSearchAirlineOut.setVisible(true);
					/* 文本域清空 */
					pSAI_T_DATE.setText("");
					pSAI_T_ORIGIN.setText("");
					pSAI_T_DEST.setText("");
					break;
				/*若返回值为其他数字，查询失败*/
				default:
					JOptionPane.showMessageDialog(null, "查询失败，请重试", "查询航班", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});

		/*
		 * 
		 *  订票功能面板 
		 *  
		 *  
		 */
		pBookAirline = new JPanel();
		pBookAirline.setOpaque(false);
		pBookAirline.setBounds(12, 30, 1478, 677);
		panel.add(pBookAirline);
		pBookAirline.setLayout(null);
		pBookAirline.setVisible(false);

		/*航班号文本域*/
		JTextField pBA_T_AID = new JTextField();
		pBA_T_AID.setBounds(528, 47, 127, 29);
		pBookAirline.add(pBA_T_AID);
		pBA_T_AID.setColumns(10);

		/*航班号标签*/
		JLabel pBA_L_AID = new JLabel("航班号");
		pBA_L_AID.setBounds(415, 47, 71, 27);
		pBookAirline.add(pBA_L_AID);

		/*舱位标签*/
		JLabel pBA_L_SEATTYPE = new JLabel("舱位");
		pBA_L_SEATTYPE.setBounds(415, 86, 71, 26);
		pBookAirline.add(pBA_L_SEATTYPE);

		/*座位号标签*/
		JLabel pBA_L_SEATID = new JLabel("座位");
		pBA_L_SEATID.setBounds(415, 124, 53, 29);
		pBookAirline.add(pBA_L_SEATID);

		/*舱位文本域*/
		JTextField pBA_T_SEATTYPE = new JTextField();
		pBA_T_SEATTYPE.setColumns(10);
		pBA_T_SEATTYPE.setBounds(528, 88, 127, 29);
		pBookAirline.add(pBA_T_SEATTYPE);

		/*座位号文本域*/
		JTextField pBA_T_SEATID = new JTextField();
		pBA_T_SEATID.setColumns(10);
		pBA_T_SEATID.setBounds(528, 124, 127, 29);
		pBookAirline.add(pBA_T_SEATID);

		/*座位分布图面板*/
		JScrollPane SEATPIC = new JScrollPane();
		SEATPIC.setBounds(721, 35, 286, 252);
		pBookAirline.add(SEATPIC);
		JLabel lblNewLabel = new JLabel("");
		SEATPIC.add(lblNewLabel);

		/*预定航班按钮*/
		JButton pBA_BOOK = new JButton("预订航班");
		/*预定航班按钮响应事件*/
		pBA_BOOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*检查是否有文本域为空*/
				if (pBA_T_AID.getText().trim().length() == 0 || pBA_T_SEATTYPE.getText().trim().length() == 0
						|| pBA_T_SEATID.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请填入完整的订票信息", "订票", JOptionPane.CLOSED_OPTION);
					return;
				}
				
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "08" + cid + "-" + pBA_T_AID.getText().trim() + "-" + pBA_T_SEATTYPE.getText().trim() + "-"
						+ pBA_T_SEATID.getText().trim();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/*若返回值为0，航班号不存在*/
				case "0":
					JOptionPane.showMessageDialog(null, "航班号不存在，请检查后再输入", "订票", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为1，用户账号不存在*/
				case "1":
					JOptionPane.showMessageDialog(null, "用户账号不存在，请检查后再输入", "订票", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为2，座位类型错误*/
				case "2":
					JOptionPane.showMessageDialog(null, "座位类型错误，请检查后再输入", "订票", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为3，座位号错误*/
				case "3":
					JOptionPane.showMessageDialog(null, "座位号错误，请检查后再输入", "订票", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为5，订票成功，返回订单号*/
				case "5":
					String orderid = result.substring(1);
					JOptionPane.showMessageDialog(null, "成功   订单号为 " + orderid, "订票", JOptionPane.CLOSED_OPTION);
					//文本域清空
					pBA_T_AID.setText("");
					pBA_T_SEATTYPE.setText("");
					pBA_T_SEATID.setText("");
					//座位图片面板设为不可见
					SEATPIC.setVisible(false);
					break;
				/*若返回值为其他数字，订票失败*/
				default:
					JOptionPane.showMessageDialog(null, "订票失败，请重试", "订票", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pBA_BOOK.setBounds(434, 252, 146, 35);
		pBookAirline.add(pBA_BOOK);

		/*查看可选座位按钮*/
		JButton CHECKSEAT = new JButton("<html>查看可<br>选座位<html>");
		/*查看可选座位按钮响应事件*/
		CHECKSEAT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "40" + pBA_T_AID.getText().trim();
				/*接收服务器返回的消息*/
				me.sendMessage(mes);
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值

				switch (sig) {
				/*若返回值为0，航班号不存在*/
				case "0":
					JOptionPane.showMessageDialog(null, "航班号不存在，请检查后输入", "订票", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为1，显示座位分布图*/
				case "1":
					String output = result.substring(1);//机型
					// 向SEATPIC面板上贴图，显示该面板
					String picname = output + ".gif";
					ImageIcon plane = new ImageIcon(picname);
					lblNewLabel.setBounds(0, 0, 286, 252);
					lblNewLabel.setIcon(plane);
					SEATPIC.setVisible(true);
					SEATPIC.setViewportView(lblNewLabel);
					break;
					/*若返回值为其他数字，显示座位失败*/
				default:
					JOptionPane.showMessageDialog(null, "显示座位失败，请重试", "订票", JOptionPane.CLOSED_OPTION);
					break;

				}

			}
		});
		CHECKSEAT.setForeground(SystemColor.desktop);
		CHECKSEAT.setBackground(SystemColor.activeCaption);
		CHECKSEAT.setFont(new Font("宋体", Font.BOLD, 14));
		CHECKSEAT.setBounds(581, 170, 80, 48);
		pBookAirline.add(CHECKSEAT);

		
		
		/*
		 * 
		 * 退票功能面板
		 *
		 *
		 */
		pCancelAirline = new JPanel();
		pCancelAirline.setOpaque(false);
		pCancelAirline.setBounds(12, 30, 1478, 677);
		panel.add(pCancelAirline);
		pCancelAirline.setLayout(null);
		pCancelAirline.setVisible(false);
		
		/*订单号标签*/
		JLabel pCA_L_OID = new JLabel("订单号");
		pCA_L_OID.setBounds(582, 42, 65, 20);
		pCancelAirline.add(pCA_L_OID);
		
		
		JTextField pCA_T_OID = new JTextField();
		pCA_T_OID.setBounds(683, 41, 114, 22);
		pCancelAirline.add(pCA_T_OID);
		pCA_T_OID.setColumns(10);

		/*取消预订按钮*/
		JButton pCA_CANCEL = new JButton("取消预订");
		/*取消预订按钮响应事件*/
		pCA_CANCEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*检查订单号是否为空*/
				if (pCA_T_OID.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "订单号不能为空", "退票", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "09" + pCA_T_OID.getText();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				
				String sig = result.substring(0, 1);//提取返回值

				switch (sig) {
				/*若返回值为0，订单号不存在*/
				case "0":
					JOptionPane.showMessageDialog(null, "订单号不存在，请检查后再输入", "退票", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为1，退票成功*/
				case "1":
					JOptionPane.showMessageDialog(null, "退票成功", "退票", JOptionPane.CLOSED_OPTION);
					pCA_T_OID.setText("");
					break;
				/*若返回值为其他数字，退票失败*/
				default:
					JOptionPane.showMessageDialog(null, "退票失败，请重试", "退票", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pCA_CANCEL.setBounds(614, 108, 123, 29);
		pCancelAirline.add(pCA_CANCEL);

		/*
		 * 
		 * 修改个人信息面板
		 * 
		 * 
		 */
		pProfileUpdate = new JPanel();
		pProfileUpdate.setOpaque(false);
		pProfileUpdate.setBounds(12, 30, 1478, 677);
		panel.add(pProfileUpdate);
		pProfileUpdate.setLayout(null);
		pProfileUpdate.setVisible(false);

		/*姓名标签*/
		JLabel pPU_L_NAME = new JLabel("新姓名");
		pPU_L_NAME.setBounds(210, 66, 71, 35);
		pProfileUpdate.add(pPU_L_NAME);
		
		/*姓名文本域*/
		JTextField pPU_T_NAME = new JTextField();
		pPU_T_NAME.setText("");
		pPU_T_NAME.setBounds(333, 66, 126, 35);
		pProfileUpdate.add(pPU_T_NAME);
		pPU_T_NAME.setColumns(10);

		/*身份证号标签*/
		JLabel pPU_L_IDN = new JLabel("新身份证号");
		pPU_L_IDN.setBounds(210, 128, 104, 33);
		pProfileUpdate.add(pPU_L_IDN);

		/*身份证号文本域*/
		JTextField pPU_T_IDN = new JTextField();
		pPU_T_IDN.setBounds(333, 128, 126, 33);
		pProfileUpdate.add(pPU_T_IDN);
		pPU_T_IDN.setColumns(10);

		/*手机号标签*/
		JLabel pPU_L_PHONE = new JLabel("新手机号");
		pPU_L_PHONE.setBounds(210, 183, 105, 38);
		pProfileUpdate.add(pPU_L_PHONE);
		
		/*手机号文本域*/
		JTextField pPU_T_PHONE = new JTextField();
		pPU_T_PHONE.setBounds(333, 185, 126, 35);
		pProfileUpdate.add(pPU_T_PHONE);
		pPU_T_PHONE.setColumns(10);

		/*更改姓名按钮*/
		JButton pPU_UPDATE_NAME = new JButton("确认更改姓名");
		/*更改姓名按钮响应事件*/
		pPU_UPDATE_NAME.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*检查姓名是否为空*/
				if (pPU_T_NAME.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入新姓名", "个人信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "12" + cid + "-" + pPU_T_NAME.getText();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/*若返回值为2，客户ID不存在*/
				case "2":
					JOptionPane.showMessageDialog(null, "客户ID不存在，请检查后再输入", "个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为3，个人信息修改成功*/
				case "3":
					JOptionPane.showMessageDialog(null, "个人信息修改成功", "个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
                /*若返回值为其他数字，个人信息修改失败*/
				default:
					JOptionPane.showMessageDialog(null, "个人信息修改失败，请重试", "个人信息修改", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pPU_UPDATE_NAME.setBounds(548, 66, 199, 35);
		pProfileUpdate.add(pPU_UPDATE_NAME);

		/*更改身份证号按钮*/
		JButton pPU_UPDATE_IDN = new JButton("确认更改身份证号");
		/*更改身份证号按钮响应事件*/
		pPU_UPDATE_IDN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*检查身份证号是否为空*/
				if (pPU_T_IDN.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入新身份证号", "个人信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查身份证号格式是否合法*/
				if (pPU_T_IDN.getText().length() != 18) {
					JOptionPane.showMessageDialog(null, "请输入有效新身份证号", "个人信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "10" + cid + "-" + pPU_T_IDN.getText();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/*若返回值为2，客户ID不存在*/
				case "2":
					JOptionPane.showMessageDialog(null, "客户ID不存在，请检查后再输入", "个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为0，新身份证号已注册*/
				case "0":
					JOptionPane.showMessageDialog(null, "新身份证号已注册，修改个人信息失败，请重试", "个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为3，个人信息修改成功*/
				case "3":
					JOptionPane.showMessageDialog(null, "个人信息修改成功", "个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为其他数字，个人信息修改失败*/
				default:
					JOptionPane.showMessageDialog(null, "个人信息修改失败，请重试", "个人信息修改", JOptionPane.CLOSED_OPTION);
					break;

				}

			}
		});
		pPU_UPDATE_IDN.setBounds(548, 127, 199, 35);
		pProfileUpdate.add(pPU_UPDATE_IDN);

		/*更改手机号按钮*/
		JButton pPU_UPDATE_PHONE = new JButton("确认更改手机号");
		/*更改手机号按钮响应事件*/
		pPU_UPDATE_PHONE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*检查手机号是否为空*/
				if (pPU_T_PHONE.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入新手机号", "个人信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "11" + cid + "-" + pPU_T_PHONE.getText();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/*若返回值为2，客户ID不存在*/
				case "2":
					JOptionPane.showMessageDialog(null, "客户ID不存在，请检查后再输入", "个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为1，新手机号已注册*/
				case "1":
					JOptionPane.showMessageDialog(null, "新手机号已注册，修改个人信息失败，请重试", "个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为3，个人信息修改成功*/
				case "3":
					JOptionPane.showMessageDialog(null, "个人信息修改成功", "个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为其他数字，个人信息修改失败*/
				default:
					JOptionPane.showMessageDialog(null, "个人信息修改失败，请重试", "个人信息修改", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pPU_UPDATE_PHONE.setBounds(548, 183, 199, 38);
		pProfileUpdate.add(pPU_UPDATE_PHONE);

		

		/*
		 * 
		 * 打印个人行程功能面板
		 * 
		 * 
		 * */
		pPrintSchedule = new JPanel();
		pPrintSchedule.setOpaque(false);
		pPrintSchedule.setBounds(12, 30, 1478, 677);
		panel.add(pPrintSchedule);
		pPrintSchedule.setLayout(null);
		pPrintSchedule.setVisible(false);

		/*子面板：输出面板*/
		JScrollPane pPrintScheduleOut = new JScrollPane();
		pPrintScheduleOut.setBounds(12, 12, 1417, 173);
		pPrintScheduleOut.setVisible(false);
		pPrintSchedule.add(pPrintScheduleOut);

		
		
		
		
		/*进入查询航班功能面板*/
		JButton cp_SEARCH = new JButton("查询航班");
		cp_SEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*只显示查询航班功能面板的输入面板，关闭所有其他功能面板和子面板*/
				pSearchAirline.setVisible(true);
				pSearchAirlineIn.setVisible(true);
				pSearchAirlineOut.setVisible(false);
				pBookAirline.setVisible(false);
				pCancelAirline.setVisible(false);
				pProfileUpdate.setVisible(false);
				pPrintSchedule.setVisible(false);
			}
		});
		cp_SEARCH.setBounds(112, 12, 141, 87);
		contentPane.add(cp_SEARCH);

		/*进入订票功能面板*/
		JButton cp_BOOK = new JButton("订票");
		cp_BOOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*只显示订票功能面板，关闭所有其他功能面板和子面板*/
				pBookAirline.setVisible(true);
				pSearchAirline.setVisible(false);
				pCancelAirline.setVisible(false);
				pProfileUpdate.setVisible(false);
				pPrintSchedule.setVisible(false);

			}
		});

		cp_BOOK.setBounds(268, 12, 141, 87);
		contentPane.add(cp_BOOK);

		/*进入退票功能面板*/
		JButton cp_CANCEL = new JButton("退票");
		cp_CANCEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*只显示退票功能面板，关闭所有其他功能面板和子面板*/
				pCancelAirline.setVisible(true);
				pSearchAirline.setVisible(false);
				pBookAirline.setVisible(false);
				pProfileUpdate.setVisible(false);
				pPrintSchedule.setVisible(false);
			}

		});
		cp_CANCEL.setBounds(424, 12, 141, 87);
		contentPane.add(cp_CANCEL);

		/*进入修改个人信息功能面板*/
		JButton cp_UPDATE = new JButton("个人信息修改");
		cp_UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*只显示修改个人信息功能面板，关闭所有其他功能面板和子面板*/
				pProfileUpdate.setVisible(true);
				pSearchAirline.setVisible(false);
				pBookAirline.setVisible(false);
				pCancelAirline.setVisible(false);
				pPrintSchedule.setVisible(false);
			}
		});
		cp_UPDATE.setBounds(579, 12, 141, 87);
		contentPane.add(cp_UPDATE);

		/*进入打印个人行程功能面板*/
		JButton cp_PRINT = new JButton("打印个人行程");
		cp_PRINT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pPrintSchedule.setVisible(false);
				/*编辑消息，发送给服务器*/
				String mes = "13" + cid;
				/*接收服务器返回的消息*/
				me.sendMessage(mes);
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/*若返回值为0，客户ID不存在*/
				case "0":
					JOptionPane.showMessageDialog(null, "客户ID不存在，请检查后再输入", "打印个人行程", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为3，显示行程表*/
				case "3":
					int count = Integer.parseInt(result.substring(1, 3));//结果数目
					/*将结果串处理成矩阵，显示在输出面板上*/
					String output = result.substring(3);
					String[][] table = MakeMatrix.makeMatrix(output, count, 9);
					String[][] prow = new String[0][9];
					String[] pcol = { "序号", "订单号", "航班号", "日期", "时间", "座位类型", "座位号", "业务员", "送票员" };
					DefaultTableModel pmodel = new DefaultTableModel(prow, pcol);
					for (int i = 0; i < table.length; i++) {
						pmodel.addRow(table[i]);
					}
					JTable ptable = new JTable(pmodel);
					pPrintScheduleOut.setViewportView(ptable);
					pPrintScheduleOut.setVisible(true);//显示输出面板
					break;
				/*若返回值为4，没有结果*/
				case "4":
					JOptionPane.showMessageDialog(null, "没有既定行程", "打印个人行程", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为其他数字，失败*/
				default:
					JOptionPane.showMessageDialog(null, "失败，请重试", "打印个人行程", JOptionPane.CLOSED_OPTION);
					break;

				}
				/*只显示打印个人行程功能面板，关闭所有其他功能面板和子面板*/
				pPrintSchedule.setVisible(true);
				pSearchAirline.setVisible(false);
				pBookAirline.setVisible(false);
				pCancelAirline.setVisible(false);
				pProfileUpdate.setVisible(false);

			}
		});
		cp_PRINT.setBounds(735, 12, 141, 87);
		contentPane.add(cp_PRINT);

	}
}
