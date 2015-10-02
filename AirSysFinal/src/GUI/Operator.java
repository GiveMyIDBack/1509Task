package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CS.Client;
import Tools.MakeMatrix;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * 操作员界面
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 
 * 
 */
public class Operator extends JFrame {

	private JPanel contentPane;// 窗体的contentPane对象
	private JPanel pSearchAirline;// 查询航班功能面板
	private JPanel pBookAirline;// 订票功能面板
	private JPanel pCancelAirline;// 退票功能面板
	private JPanel pProfileUpdate;// 修改客户信息功能面板
	private JPanel pPrintSchedule;// 打印客户行程功能面板
	private JPanel pAddClient;// 添加客户功能面板
	private JPanel pSearchClient;// 查询客户功能面板
	private CS.Client me;// 客户端对象，用于与服务器交互

	/**
	 * 
	 * 构造方法
	 * 
	 * 创建操作员窗体
	 * 
	 * 
	 */
	public Operator(Client current) {
		me = current;// 获取客户端对象

		/* 设置窗体属性 */
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1502, 734);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/* 添加背景图片 */
		ImageIcon bg = new ImageIcon("msn_background.jpg");
		JLabel label = new JLabel(bg);
		label.setBounds(0, 0, 1502, 734);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		/* 新建底版，承载各功能面板 */
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
		pSearchAirline.setBounds(-59, 57, 1478, 677);
		panel.add(pSearchAirline);
		pSearchAirline.setLayout(null);
		pSearchAirline.setVisible(false);

		/* 子面板1：输入面板 */
		JPanel pSearchAirlineIn = new JPanel();
		pSearchAirlineIn.setOpaque(false);
		pSearchAirlineIn.setBounds(656, 26, 270, 205);
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
		pSearchAirlineOut.setBounds(91, 48, 1375, 245);
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
					/* 消息框显示组合数 */
					int count2 = Integer.parseInt(result.substring(1, 3));
					JOptionPane.showMessageDialog(null, "没有直航航班，建议您转机前往，共有 " + count2 + " 种组合", "查询航班",
							JOptionPane.CLOSED_OPTION);
					String output2 = result.substring(3);
					/* 将结果串处理成矩阵，后处理成表结构显示 */
					String[][] table2 = MakeMatrix.makeMatrix(output2, count2 * 2, 10);
					String[][] prow2 = new String[0][10];
					String[] pcol2 = { "序号", "航班号", "起点", "终点", "日期", "时间", "头等舱可选", "商务舱可选", "经济舱可选", "机型" };
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
				/* 若返回值为其他数字，查询失败 */
				default:
					JOptionPane.showMessageDialog(null, "查询失败，请重试", "查询航班", JOptionPane.CLOSED_OPTION);
					break;
				}

			}
		});

		
		/*
		 * 
		 * 订票功能面板 
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
		pBA_T_AID.setBounds(593, 55, 123, 29);
		pBookAirline.add(pBA_T_AID);
		pBA_T_AID.setColumns(10);

		/*航班号标签*/
		JLabel pBA_L_AID = new JLabel("航班号");
		pBA_L_AID.setBounds(474, 56, 79, 27);
		pBookAirline.add(pBA_L_AID);

		/*客户id文本域*/
		JTextField pBA_T_CID = new JTextField();
		pBA_T_CID.setColumns(10);
		pBA_T_CID.setBounds(593, 102, 123, 29);
		pBookAirline.add(pBA_T_CID);

		/*客户id标签*/
		JLabel pBA_L_CID = new JLabel("客户ID");
		pBA_L_CID.setBounds(474, 103, 61, 27);
		pBookAirline.add(pBA_L_CID);

		/*舱位标签*/
		JLabel pBA_L_SEATTYPE = new JLabel("舱位");
		pBA_L_SEATTYPE.setBounds(474, 149, 79, 29);
		pBookAirline.add(pBA_L_SEATTYPE);

		/*舱位文本域*/
		JTextField pBA_T_SEATTYPE = new JTextField();
		pBA_T_SEATTYPE.setColumns(10);
		pBA_T_SEATTYPE.setBounds(593, 149, 123, 29);
		pBookAirline.add(pBA_T_SEATTYPE);
		
		/*座位号标签*/
		JLabel pBA_L_SEATID = new JLabel("座位");
		pBA_L_SEATID.setBounds(474, 190, 73, 29);
		pBookAirline.add(pBA_L_SEATID);

		/*座位号文本域*/
		JTextField pBA_T_SEATID = new JTextField();
		pBA_T_SEATID.setColumns(10);
		pBA_T_SEATID.setBounds(593, 197, 123, 29);
		pBookAirline.add(pBA_T_SEATID);

		/*座位分布图面板*/
		JScrollPane SEATPIC = new JScrollPane();
		SEATPIC.setBounds(786, 43, 286, 252);
		pBookAirline.add(SEATPIC);
		JLabel lblNewLabel = new JLabel("");
		SEATPIC.add(lblNewLabel);

		/*预定航班按钮*/
		JButton pBA_BOOK = new JButton("预订航班");
		/*预定航班按钮响应事件*/
		pBA_BOOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*检查是否有文本域为空*/
				if (pBA_T_AID.getText().trim().length() == 0 || pBA_T_CID.getText().trim().length() == 0
						|| pBA_T_SEATTYPE.getText().trim().length() == 0
						|| pBA_T_SEATID.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请填入完整的订票信息", "订票", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "08" + pBA_T_CID.getText().trim() + "-" + pBA_T_AID.getText().trim() + "-"
						+ pBA_T_SEATTYPE.getText().trim() + "-" + pBA_T_SEATID.getText().trim();
				/*接收服务器返回的消息*/
				me.sendMessage(mes);
				String result = me.receiveMessage();String sig = result.substring(0, 1);//提取返回值

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
					pBA_T_CID.setText("");
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
		pBA_BOOK.setBounds(488, 289, 134, 35);
		pBookAirline.add(pBA_BOOK);

		/*查看可选座位按钮*/
		JButton CHECKSEAT = new JButton("<html>查看可<br>选座位<html>");
		/*查看可选座位按钮响应事件*/
		CHECKSEAT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "40" + pBA_T_AID.getText().trim();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
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
		CHECKSEAT.setBounds(655, 238, 79, 42);
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

		/*订单号文本域*/
		JTextField pCA_T_OID = new JTextField();
		pCA_T_OID.setBounds(490, 34, 114, 22);
		pCancelAirline.add(pCA_T_OID);
		pCA_T_OID.setColumns(10);

		/*订单号标签*/
		JLabel pCA_L_OID = new JLabel("订单号");
		pCA_L_OID.setBounds(389, 35, 65, 20);
		pCancelAirline.add(pCA_L_OID);

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
		pCA_CANCEL.setBounds(421, 101, 123, 29);
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

		/*客户id标签*/
		JLabel pPU_L_CID = new JLabel("客户ID");
		pPU_L_CID.setBounds(382, 51, 80, 28);
		pProfileUpdate.add(pPU_L_CID);

		/*客户id文本域*/
		JTextField pPU_T_CID = new JTextField();
		pPU_T_CID.setText("");
		pPU_T_CID.setColumns(10);
		pPU_T_CID.setBounds(519, 50, 127, 30);
		pProfileUpdate.add(pPU_T_CID);

		/*姓名标签*/
		JLabel pPU_L_NAME = new JLabel("新姓名");
		pPU_L_NAME.setBounds(382, 121, 66, 28);
		pProfileUpdate.add(pPU_L_NAME);

		/*姓名文本域*/
		JTextField pPU_T_NAME = new JTextField();
		pPU_T_NAME.setText("");
		pPU_T_NAME.setBounds(519, 120, 127, 30);
		pProfileUpdate.add(pPU_T_NAME);
		pPU_T_NAME.setColumns(10);
		
		/*身份证号标签*/
		JLabel pPU_L_IDN = new JLabel("新身份证号");
		pPU_L_IDN.setBounds(382, 166, 119, 32);
		pProfileUpdate.add(pPU_L_IDN);

		/*身份证号文本域*/
		JTextField pPU_T_IDN = new JTextField();
		pPU_T_IDN.setBounds(519, 168, 127, 28);
		pProfileUpdate.add(pPU_T_IDN);
		pPU_T_IDN.setColumns(10);
		
		/*手机号标签*/
		JLabel pPU_L_PHONE = new JLabel("新手机号");
		pPU_L_PHONE.setBounds(382, 216, 90, 31);
		pProfileUpdate.add(pPU_L_PHONE);

		/*手机号文本域*/
		JTextField pPU_T_PHONE = new JTextField();
		pPU_T_PHONE.setBounds(519, 217, 127, 28);
		pProfileUpdate.add(pPU_T_PHONE);
		pPU_T_PHONE.setColumns(10);

		/*更改姓名按钮*/
		JButton pPU_UPDATE_NAME = new JButton("确认更改姓名");
		/*更改姓名按钮响应事件*/
		pPU_UPDATE_NAME.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*检查客户id是否为空*/
				if (pPU_T_CID.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入客户ID", "客户信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查姓名是否为空*/
				if (pPU_T_NAME.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入新姓名", "客户信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "12" + pPU_T_CID.getText() + "-" + pPU_T_NAME.getText();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/*若返回值为2，客户ID不存在*/
				case "2":
					JOptionPane.showMessageDialog(null, "客户ID不存在，请检查后再输入", "客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为3，客户信息修改成功*/
				case "3":
					JOptionPane.showMessageDialog(null, "客户信息修改成功", "客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为其他数字，客户信息修改失败*/
				default:
					JOptionPane.showMessageDialog(null, "客户信息修改失败，请重试", "客户信息修改", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pPU_UPDATE_NAME.setBounds(678, 121, 172, 28);
		pProfileUpdate.add(pPU_UPDATE_NAME);

		/*更改身份证号按钮*/
		JButton pPU_UPDATE_IDN = new JButton("确认更改身份证号");
		/*更改身份证号按钮响应事件*/
		pPU_UPDATE_IDN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*检查客户id是否为空*/
				if (pPU_T_CID.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入客户ID", "客户信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查身份证号是否为空*/
				if (pPU_T_IDN.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入新身份证号", "客户信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查身份证号格式是否合法*/
				if (pPU_T_IDN.getText().length() != 18) {
					JOptionPane.showMessageDialog(null, "请输入有效新身份证号", "客户信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "10" + pPU_T_CID.getText() + "-" + pPU_T_IDN.getText();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/*若返回值为2，客户ID不存在*/
				case "2":
					JOptionPane.showMessageDialog(null, "客户ID不存在，请检查后再输入", "客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为0，新身份证号已注册*/
				case "0":
					JOptionPane.showMessageDialog(null, "新身份证号已注册，修改客户信息失败，请重试", "客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为3，客户信息修改成功*/
				case "3":
					JOptionPane.showMessageDialog(null, "客户信息修改成功", "客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为其他数字，客户信息修改失败*/
				default:
					JOptionPane.showMessageDialog(null, "客户信息修改，请重试", "客户信息修改", JOptionPane.CLOSED_OPTION);
					break;

				}

			}
		});
		pPU_UPDATE_IDN.setBounds(678, 171, 172, 28);
		pProfileUpdate.add(pPU_UPDATE_IDN);

		/*更改手机号按钮*/
		JButton pPU_UPDATE_PHONE = new JButton("确认更改手机号");
		/*更改手机号按钮响应事件*/
		pPU_UPDATE_PHONE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*检查客户id是否为空*/
				if (pPU_T_CID.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入客户ID", "客户信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查手机号是否为空*/
				if (pPU_T_PHONE.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入新手机号", "客户信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}

				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "11" + pPU_T_CID.getText() + "-" + pPU_T_PHONE.getText();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/*若返回值为2，客户ID不存在*/
				case "2":
					JOptionPane.showMessageDialog(null, "客户ID不存在，请检查后再输入", "客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为1，新手机号已注册*/
				case "1":
					JOptionPane.showMessageDialog(null, "新手机号已注册，修改客户信息失败，请重试", "客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为3，个人信息修改成功*/
				case "3":
					JOptionPane.showMessageDialog(null, "客户信息修改成功", "客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为其他数字，个人信息修改失败*/
				default:
					JOptionPane.showMessageDialog(null, "客户信息修改失败，请重试", "客户信息修改", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pPU_UPDATE_PHONE.setBounds(678, 217, 172, 28);
		pProfileUpdate.add(pPU_UPDATE_PHONE);

		/*
		 * 
		 * 打印客户行程功能面板
		 * 
		 * 
		 * */
		pPrintSchedule = new JPanel();
		pPrintSchedule.setOpaque(false);
		pPrintSchedule.setBounds(12, 30, 1478, 677);
		panel.add(pPrintSchedule);
		pPrintSchedule.setLayout(null);

		/*子面板1：输入面板*/
		JPanel pPrintScheduleIn = new JPanel();
		pPrintScheduleIn.setOpaque(false);
		pPrintScheduleIn.setBounds(345, 79, 609, 209);
		pPrintSchedule.add(pPrintScheduleIn);
		pPrintScheduleIn.setLayout(null);

		/*子面板2：输出面板*/
		JScrollPane pPrintScheduleOut = new JScrollPane();
		pPrintScheduleOut.setBounds(12, 23, 1427, 169);
		pPrintSchedule.add(pPrintScheduleOut);
		pPrintSchedule.setVisible(false);

		/*客户id标签*/
		JLabel pPSI_L_CID = new JLabel("用客户ID查询：");
		pPSI_L_CID.setBounds(26, 30, 160, 21);
		pPrintScheduleIn.add(pPSI_L_CID);

		/*客户id文本域*/
		JTextField pPSI_T_CID = new JTextField();
		pPSI_T_CID.setBounds(176, 27, 237, 24);
		pPrintScheduleIn.add(pPSI_T_CID);
		pPSI_T_CID.setColumns(10);

		
		
		
		//以下为待补充内容，根据客户身份证号/手机号查找行程
		JLabel pPSI_L_IDN = new JLabel("用身份证号查询：");
		pPSI_L_IDN.setBounds(26, 91, 160, 21);
		// pPrintScheduleIn.add(pPSI_L_IDN);

		JTextField pPSI_T_IDN = new JTextField();
		pPSI_T_IDN.setColumns(10);
		pPSI_T_IDN.setBounds(176, 88, 237, 24);
		// pPrintScheduleIn.add(pPSI_T_IDN);

		JLabel pPSI_L_PHONE = new JLabel("用手机号查询：");
		pPSI_L_PHONE.setBounds(26, 152, 160, 21);
		// pPrintScheduleIn.add(pPSI_L_PHONE);

		JTextField pPSI_T_PHONE = new JTextField();
		pPSI_T_PHONE.setColumns(10);
		pPSI_T_PHONE.setBounds(176, 149, 237, 24);
		// pPrintScheduleIn.add(pPSI_T_PHONE);
		
		
		
		

		/*查询客户行程按钮*/
		JButton pPSI_CIDSEARCH = new JButton("查询");
		pPSI_CIDSEARCH.setBounds(428, 26, 123, 29);
		pPrintScheduleIn.add(pPSI_CIDSEARCH);
		/*查询客户行程按钮响应事件*/
		pPSI_CIDSEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*检查客户id是否为空*/
				if (pPSI_T_CID.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入客户ID", "打印客户行程", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "13" + pPSI_T_CID.getText();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/*若返回值为0，客户id不存在*/
				case "0":
					JOptionPane.showMessageDialog(null, "客户ID不存在，请检查后再输入", "打印客户行程", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为3，显示行程表*/
				case "3":
					int count = Integer.parseInt(result.substring(1, 3));//结果数目			
					String output = result.substring(3);
					/*将结果串处理成矩阵，显示在输出面板上*/
					String[][] table = MakeMatrix.makeMatrix(output, count, 9);
					String[][] prow = new String[0][9];
					String[] pcol = { "序号", "订单号", "航班号", "日期", "时间", "座位类型", "座位号", "业务员", "送票员" };
					DefaultTableModel pmodel = new DefaultTableModel(prow, pcol);
					for (int i = 0; i < table.length; i++) {
						pmodel.addRow(table[i]);
					}
					JTable ptable = new JTable(pmodel);
					pPrintScheduleOut.setViewportView(ptable);
					/*隐藏输入面板，显示输出面板*/
					pPrintScheduleIn.setVisible(false);
					pPrintScheduleOut.setVisible(true);
					/*文本域清空*/
					pPSI_T_CID.setText("");
					break;
				/*若返回值为4，没有结果*/
				case "4":
					JOptionPane.showMessageDialog(null, "没有既定行程", "打印客户行程", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为其他数字，失败*/
				default:
					JOptionPane.showMessageDialog(null, "失败，请重试", "打印客户行程", JOptionPane.CLOSED_OPTION);
					break;
				}
			}
		});

		
		
		
		
		//以下为待补充内容，根据客户身份证号/手机号查找行程
		JButton pPSI_IDNSEARCH = new JButton("查询");
		pPSI_IDNSEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pPrintScheduleIn.setVisible(false);
				pPrintScheduleOut.setVisible(true);
			}
		});
		pPSI_IDNSEARCH.setBounds(428, 87, 123, 29);
		// pPrintScheduleIn.add(pPSI_IDNSEARCH);

		JButton pPSI_PHONESEARCH = new JButton("查询");
		pPSI_PHONESEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pPrintScheduleIn.setVisible(false);
				pPrintScheduleOut.setVisible(true);
			}
		});
		pPSI_PHONESEARCH.setBounds(428, 148, 123, 29);
		// pPrintScheduleIn.add(pPSI_PHONESEARCH);

		
		
		
		
		/*
		 * 
		 * 添加客户功能面板
		 * 
		 * 
		 */
		pAddClient = new JPanel();
		pAddClient.setOpaque(false);
		pAddClient.setBounds(12, 30, 1478, 677);
		panel.add(pAddClient);
		pAddClient.setLayout(null);
		pAddClient.setVisible(false);

		/*姓名标签*/
		JLabel pAC_L_NAME = new JLabel("姓名");
		pAC_L_NAME.setBounds(550, 59, 88, 28);
		pAddClient.add(pAC_L_NAME);
		
		/*姓名文本域*/
		JTextField pAC_T_NAME = new JTextField();
		pAC_T_NAME.setText("");
		pAC_T_NAME.setBounds(656, 58, 114, 30);
		pAddClient.add(pAC_T_NAME);
		pAC_T_NAME.setColumns(10);

		/*身份证号标签*/
		JLabel pAC_L_IDN = new JLabel("身份证号");
		pAC_L_IDN.setBounds(550, 104, 88, 30);
		pAddClient.add(pAC_L_IDN);

		/*身份证号文本域*/
		JTextField pAC_T_IDN = new JTextField();
		pAC_T_IDN.setBounds(656, 104, 203, 30);
		pAddClient.add(pAC_T_IDN);
		pAC_T_IDN.setColumns(10);
		
		/*手机号标签*/
		JLabel pAC_L_PHONE = new JLabel("手机号");
		pAC_L_PHONE.setBounds(550, 150, 88, 28);
		pAddClient.add(pAC_L_PHONE);
		
		/*手机号文本域*/
		JTextField pAC_T_PHONE = new JTextField();
		pAC_T_PHONE.setBounds(656, 149, 114, 30);
		pAddClient.add(pAC_T_PHONE);
		pAC_T_PHONE.setColumns(10);

		/*业务员id标签*/
		JLabel pAC_L_SID = new JLabel("业务员ID");
		pAC_L_SID.setBounds(550, 191, 88, 28);
		pAddClient.add(pAC_L_SID);

		/*业务员id文本域*/
		JTextField pAC_T_SID = new JTextField();
		pAC_T_SID.setColumns(10);
		pAC_T_SID.setBounds(656, 191, 114, 28);
		pAddClient.add(pAC_T_SID);

		/*提示标签：业务员id可为空*/
		JLabel lblNewLabel2 = new JLabel("（可为空）");
		lblNewLabel2.setBounds(788, 199, 94, 18);
		pAddClient.add(lblNewLabel2);

		/*添加客户按钮*/
		JButton pAC_UPDATE = new JButton("添加客户");
		/*添加客户按钮监听事件*/
		pAC_UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*检查客户姓名是否为空*/
				if (pAC_T_NAME.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入客户姓名", "添加客户", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查客户身份证号是否为空*/
				if (pAC_T_IDN.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入客户 身份证号", "添加客户", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查客户身份证号格式是否正确*/
				if (pAC_T_IDN.getText().length() != 18) {
					JOptionPane.showMessageDialog(null, "请输入客户有效身份证号", "添加客户", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查手机号是否为空*/
				if (pAC_T_PHONE.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入客户手机号", "添加客户", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "16" + pAC_T_IDN.getText().trim() + "-" + pAC_T_PHONE.getText().trim() + "-"
						+ pAC_T_NAME.getText().trim() + "-" + pAC_T_SID.getText().trim();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/*若返回值为1，身份证号已注册*/
				case "1":
					JOptionPane.showMessageDialog(null, "该身份证号已注册", "添加客户", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为2，手机号已注册*/
				case "2":
					JOptionPane.showMessageDialog(null, "该手机号已注册", "添加客户", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为3，业务员不存在*/
				case "3":
					JOptionPane.showMessageDialog(null, "该业务员不存在", "添加客户", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为4，成功添加客户*/
				case "4":
					String currentcid = result.substring(1);
					JOptionPane.showMessageDialog(null, "成功添加客户  " + currentcid, "添加客户", JOptionPane.CLOSED_OPTION);
					/*清空文本域*/
					pAC_T_NAME.setText("");
					pAC_T_IDN.setText("");
					pAC_T_PHONE.setText("");
					pAC_T_SID.setText("");
					break;
				/*若返回值为其他数字，添加客户失败*/
				default:
					JOptionPane.showMessageDialog(null, "添加客户失败，请重试", "添加客户", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pAC_UPDATE.setBounds(579, 260, 114, 35);
		pAddClient.add(pAC_UPDATE);

		/*
		 * 
		 * 查询客户功能面板
		 * 
		 * 
		 */
		pSearchClient = new JPanel();
		pSearchClient.setOpaque(false);
		pSearchClient.setBounds(12, 30, 1478, 677);
		panel.add(pSearchClient);
		pSearchClient.setLayout(null);
		pSearchClient.setVisible(false);
		
		/*子面板1：输入面板*/
		JPanel pSearchClientIn = new JPanel();
		pSearchClientIn.setOpaque(false);
		pSearchClientIn.setBounds(387, 34, 609, 209);
		pSearchClient.add(pSearchClientIn);
		pSearchClientIn.setLayout(null);

		/*子面板2：输出面板*/
		JPanel pSearchClientOut = new JPanel();
		pSearchClientOut.setBounds(0, 0, 609, 209);
		pSearchClient.add(pSearchClientOut);
		pSearchClientOut.setOpaque(false);
		pSearchClientOut.setVisible(false);
		pSearchClientOut.setLayout(null);

		/*文本区*/
		JTextArea pSCO_A_RS = new JTextArea();
		pSCO_A_RS.setLineWrap(true);
		pSCO_A_RS.setRows(20);
		pSCO_A_RS.setBounds(88, 38, 475, 116);
		pSearchClientOut.add(pSCO_A_RS);

		/*客户id标签*/
		JLabel pSCI_L_CID = new JLabel("用客户ID查询：");
		pSCI_L_CID.setBounds(26, 30, 160, 21);
		pSearchClientIn.add(pSCI_L_CID);

		/*客户id文本域*/
		JTextField pSCI_T_CID = new JTextField();
		pSCI_T_CID.setBounds(176, 27, 237, 24);
		pSearchClientIn.add(pSCI_T_CID);
		pSCI_T_CID.setColumns(10);

		/*身份证号标签*/
		JLabel pSCI_L_IDN = new JLabel("用身份证号查询：");
		pSCI_L_IDN.setBounds(26, 91, 160, 21);
		pSearchClientIn.add(pSCI_L_IDN);

		/*身份证号文本域*/
		JTextField pSCI_T_IDN = new JTextField();
		pSCI_T_IDN.setColumns(10);
		pSCI_T_IDN.setBounds(176, 88, 237, 24);
		pSearchClientIn.add(pSCI_T_IDN);

		/*手机号标签*/
		JLabel pSCI_L_PHONE = new JLabel("用手机号查询：");
		pSCI_L_PHONE.setBounds(26, 152, 160, 21);
		pSearchClientIn.add(pSCI_L_PHONE);

		/*手机号文本域*/
		JTextField pSCI_T_PHONE = new JTextField();
		pSCI_T_PHONE.setColumns(10);
		pSCI_T_PHONE.setBounds(176, 149, 237, 24);
		pSearchClientIn.add(pSCI_T_PHONE);

		/*用客户ID查询客户按钮*/
		JButton pSCI_CIDSEARCH = new JButton("查询");
		pSCI_CIDSEARCH.setBounds(428, 26, 123, 29);
		pSearchClientIn.add(pSCI_CIDSEARCH);
		/*用客户ID查询客户按钮响应事件*/
		pSCI_CIDSEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*检查客户id是否为空*/
				if (pSCI_T_CID.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入客户ID", "查询客户信息", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "17" + pSCI_T_CID.getText();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/*若返回值为0，排版显示客户信息*/
				case "0":
					String[] output = result.substring(1).split("-");
					pSCO_A_RS.setText("客户ID: " + output[0] + '\n');
					pSCO_A_RS.append("姓名: " + output[3] + '\n');
					pSCO_A_RS.append("身份证号: " + output[1] + '\n');
					pSCO_A_RS.append("手机号: " + output[2] + '\n');
					
					pSearchClientOut.add(pSCO_A_RS);
					pSCO_A_RS.setVisible(true);
					/*隐藏输入面板，显示输出面板*/
					pSearchClientIn.setVisible(false);
					pSearchClientOut.setVisible(true);
					pSCI_T_CID.setText("");//清空文本域
					break;
				/*若返回值为1，该客户不存在*/
				case "1":
					JOptionPane.showMessageDialog(null, "该客户不存在", "查询客户信息", JOptionPane.CLOSED_OPTION);
					break;
				/*若返回值为其他数字，查询客户信息失败*/
				default:
					JOptionPane.showMessageDialog(null, "查询客户信息失败，请重试", "查询客户信息", JOptionPane.CLOSED_OPTION);
					break;

				}

			}
		});

		/*用客户ID查询客户按钮*/
		JButton pSCI_IDNSEARCH = new JButton("查询");
		/*用客户ID查询客户按钮响应事件*/
		pSCI_IDNSEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*检查客户身份证号是否为空*/
				if (pSCI_T_IDN.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入客户身份证号", "查询客户信息", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查客户身份证号格式是否合法*/
				if (pSCI_T_IDN.getText().length() != 18) {
					JOptionPane.showMessageDialog(null, "请输入有效身份证号", "查询客户信息", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "18" + pSCI_T_IDN.getText();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/*若返回值为0，排版显示客户信息*/
				case "0":
					String[] output = result.substring(1).split("-");
					pSCO_A_RS.setText("客户ID: " + output[0] + "\n");
					pSCO_A_RS.append("姓名: " + output[3] + "\n");
					pSCO_A_RS.append("身份证号: " + output[1] + "\n");
					pSCO_A_RS.append("手机号: " + output[2] + "\n");
					/*隐藏输入面板，显示输出面板*/
					pSearchClientIn.setVisible(false);
					pSearchClientOut.setVisible(true);
					pSCI_T_IDN.setText("");//清空文本域
					break;
				/*若返回值为1，该客户不存在*/
				case "1":
					JOptionPane.showMessageDialog(null, "该客户不存在", "查询客户信息", JOptionPane.CLOSED_OPTION);

					break;
				/*若返回值为其他数字，查询客户信息失败*/
				default:
					JOptionPane.showMessageDialog(null, "查询客户信息失败，请重试", "查询客户信息", JOptionPane.CLOSED_OPTION);
					break;

				}

			}
		});
		
		pSCI_IDNSEARCH.setBounds(428, 87, 123, 29);
		pSearchClientIn.add(pSCI_IDNSEARCH);
		
		/*用客户手机号查询客户按钮*/
		JButton pSCI_PHONESEARCH = new JButton("查询");
		/*用客户手机号查询客户按钮响应事件*/
		pSCI_PHONESEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*检查客户手机号是否为空*/
				if (pSCI_T_PHONE.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入客户手机号", "查询客户信息", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "19" + pSCI_T_PHONE.getText();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/*若返回值为0，排版显示客户信息*/
				case "0":
					String[] output = result.substring(1).split("-");
					pSCO_A_RS.setText("客户ID: " + output[0] + "\n");
					pSCO_A_RS.append("姓名: " + output[3] + "\n");
					pSCO_A_RS.append("身份证号: " + output[1] + "\n");
					pSCO_A_RS.append("手机号: " + output[2] + "\n");
					/*隐藏输入面板，显示输出面板*/
					pSearchClientIn.setVisible(false);
					pSearchClientOut.setVisible(true);
					pSCI_T_PHONE.setText("");//清空文本域
					break;
				/*若返回值为1，该客户不存在*/
				case "1":
					JOptionPane.showMessageDialog(null, "该客户不存在", "查询客户信息", JOptionPane.CLOSED_OPTION);

					break;
				/*若返回值为其他数字，查询客户信息失败*/
				default:
					JOptionPane.showMessageDialog(null, "查询客户信息失败，请重试", "查询客户信息", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pSCI_PHONESEARCH.setBounds(428, 148, 123, 29);
		pSearchClientIn.add(pSCI_PHONESEARCH);

		/*进入查询航班功能面板*/
		JButton cp_SEARCH = new JButton("<html>查询<br>航班<html>");
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
				pAddClient.setVisible(false);
				pSearchClient.setVisible(false);
			}
		});
		cp_SEARCH.setBounds(44, 12, 97, 87);
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
				pAddClient.setVisible(false);
				pSearchClient.setVisible(false);
			}
		});
		cp_BOOK.setBounds(157, 12, 97, 87);
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
				pAddClient.setVisible(false);
				pSearchClient.setVisible(false);
			}
		});
		cp_CANCEL.setBounds(270, 12, 97, 87);
		contentPane.add(cp_CANCEL);

		/*进入修改客户信息功能面板*/
		JButton cp_UPDATE = new JButton("<html>客户信<br>息修改<html>");
		cp_UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*只显示修改客户信息功能面板，关闭所有其他功能面板和子面板*/
				pProfileUpdate.setVisible(true);
				pSearchAirline.setVisible(false);
				pBookAirline.setVisible(false);
				pCancelAirline.setVisible(false);
				pPrintSchedule.setVisible(false);
				pAddClient.setVisible(false);
				pSearchClient.setVisible(false);
			}
		});
		cp_UPDATE.setBounds(384, 12, 97, 87);
		contentPane.add(cp_UPDATE);

		/*进入打印客户行程功能面板*/
		JButton cp_PRINT = new JButton("<html>打印客<br>户行程<html>");
		cp_PRINT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*只显示打印客户行程功能输入面板，关闭所有其他功能面板和子面板*/
				pPrintSchedule.setVisible(true);
				pPrintScheduleIn.setVisible(true);
				pPrintScheduleOut.setVisible(false);
				pSearchAirline.setVisible(false);
				pBookAirline.setVisible(false);
				pCancelAirline.setVisible(false);
				pProfileUpdate.setVisible(false);
				pAddClient.setVisible(false);
				pSearchClient.setVisible(false);

			}
		});
		cp_PRINT.setBounds(497, 12, 97, 87);
		contentPane.add(cp_PRINT);

		/*进入添加客户功能面板*/
		JButton cp_ADDCLIENT = new JButton("<html>添加<br>客户<html>");
		cp_ADDCLIENT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*只显示添加客户功能面板，关闭所有其他功能面板和子面板*/
				pAddClient.setVisible(true);
				pSearchAirline.setVisible(false);
				pBookAirline.setVisible(false);
				pCancelAirline.setVisible(false);
				pProfileUpdate.setVisible(false);
				pPrintSchedule.setVisible(false);
				pSearchClient.setVisible(false);

			}
		});
		cp_ADDCLIENT.setBounds(610, 12, 97, 87);
		contentPane.add(cp_ADDCLIENT);

		/*进入查询客户功能面板*/
		JButton cp_SEARCHCLIENT = new JButton("<html>查询客<br>户信息<html>");
		cp_SEARCHCLIENT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*只显示查询客户功能输入面板，关闭所有其他功能面板和子面板*/
				pSearchClient.setVisible(true);
				pSearchClientIn.setVisible(true);
				pSearchClientOut.setVisible(false);
				pSearchAirline.setVisible(false);
				pBookAirline.setVisible(false);
				pCancelAirline.setVisible(false);
				pProfileUpdate.setVisible(false);
				pPrintSchedule.setVisible(false);
				pAddClient.setVisible(false);

			}
		});
		cp_SEARCHCLIENT.setBounds(723, 12, 97, 87);
		contentPane.add(cp_SEARCHCLIENT);

	}
}