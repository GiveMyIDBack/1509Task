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
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSplitPane;
import java.awt.Font;

import CS.Client;
import Tools.MakeMatrix;

/**
 * 管理员界面
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 
 * 
 */
public class Admin extends JFrame {

	private JPanel contentPane;// 窗体的contentPane对象

	/* 航班管理模块 */
	private JPanel pAirlineManagement;//航班管理模块底版
	private JPanel pAM_Display;//子面板显示区
	private JPanel pAddAirlineIn;//添加航班面板
	private JPanel pCancelAirlineIn;//取消航班面板
	private JPanel pSearchAirlineIn;//查询航班输入面板
	private JScrollPane pSearchAirlineOut;//查询航班输出面板

	/* 人事管理模块 */
	private JPanel pHRManagement;//人事管理模块底版
	private JPanel pHRM_Display;//子面板显示区
	private JPanel pd_SALESMAN;//业务员管理面板
	private JPanel pd_DELIVERYMAN;//送票员管理面板
	private JPanel pd_OPERATOR;//操作员管理面板

	/* 售票点管理模块 */
	private JPanel pTicketBoxManagement;//售票点管理模块 底版
	private JPanel pTBM_Display;//子面板显示区
	private JPanel pAddTBIn;//添加售票点面板
	private JPanel pCancelTBIn;//取消售票点面板

	/* 报表模块 */
	private JPanel pStatistics;//报表模块底版
	private JPanel pStatisticsIn;//报表模块输入面板
	private JPanel pStatisticsOut;//报表模块输出面板
	private JScrollPane pStatisticsTable;//表格显示区

	private Client me;// 客户端对象，用于与服务器交互

	/**
	 * 
	 * 构造方法
	 * 
	 * 创建管理员窗体
	 * 
	 * 
	 */
	public Admin(Client current) {
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
		 * 航班管理模块
		 * 
		 * 
		 */

		/*航班管理模块底版*/
		pAirlineManagement = new JPanel();
		pAirlineManagement.setOpaque(false);
		pAirlineManagement.setBounds(12, 30, 1478, 677);
		panel.add(pAirlineManagement);
		pAirlineManagement.setLayout(null);
		pAirlineManagement.setVisible(false);

		/*进入查询航班面板按钮*/
		JButton pAM_SELECT = new JButton("<html>查询<br>航班<html>");
		/*进入查询航班面板按钮监听事件*/
		pAM_SELECT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//显示查询航班输入面板，隐藏其他面板
				pSearchAirlineOut.setVisible(false);
				pAddAirlineIn.setVisible(false);
				pCancelAirlineIn.setVisible(false);
				pSearchAirlineIn.setVisible(true);
			}
		});
		pAM_SELECT.setBounds(15, 15, 70, 67);
		pAirlineManagement.add(pAM_SELECT);

		/*进入添加航班面板按钮*/
		JButton pAM_ADD = new JButton("<html>添加<br>航班<html>");
		/*进入添加航班面板按钮响应事件*/
		pAM_ADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//显示添加航班面板，隐藏其他面板
				pAddAirlineIn.setVisible(true);
				pCancelAirlineIn.setVisible(false);
				pSearchAirlineIn.setVisible(false);
				pSearchAirlineOut.setVisible(false);
			}
		});
		pAM_ADD.setBounds(15, 91, 70, 67);
		pAirlineManagement.add(pAM_ADD);

		/*进入取消航班面板按钮*/
		JButton pAM_CANCEL = new JButton("<html>取消<br>航班<html>");
		/*进入取消航班面板按钮响应事件*/
		pAM_CANCEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//显示取消航班面板，隐藏其他面板
				pAddAirlineIn.setVisible(false);
				pCancelAirlineIn.setVisible(true);
				pSearchAirlineIn.setVisible(false);
				pSearchAirlineOut.setVisible(false);

			}
		});
		pAM_CANCEL.setBounds(15, 173, 70, 67);
		pAirlineManagement.add(pAM_CANCEL);

		/*子面板显示区*/
		JPanel pAM_Display = new JPanel();
		pAM_Display.setOpaque(false);
		pAM_Display.setBounds(100, 15, 1343, 285);
		pAirlineManagement.add(pAM_Display);
		pAM_Display.setLayout(null);

		/*查询航班输入面板*/
		pSearchAirlineIn = new JPanel();
		pSearchAirlineIn.setBounds(0, 0, 1343, 285);
		pAM_Display.add(pSearchAirlineIn);
		pSearchAirlineIn.setOpaque(false);
		pSearchAirlineIn.setLayout(null);
		pSearchAirlineIn.setVisible(false);

		/*日期标签*/
		JLabel pSearch_L_DATE = new JLabel("日期");
		pSearch_L_DATE.setBounds(490, 37, 81, 21);
		pSearchAirlineIn.add(pSearch_L_DATE);

		/*日期文本域*/
		JTextField pSearch_T_DATE = new JTextField();
		pSearch_T_DATE.setBounds(591, 34, 96, 27);
		pSearch_T_DATE.setColumns(10);
		pSearchAirlineIn.add(pSearch_T_DATE);

		/*起点标签*/
		JLabel pSearch_L_ORIGIN = new JLabel("起点");
		pSearch_L_ORIGIN.setBounds(490, 79, 81, 21);
		pSearchAirlineIn.add(pSearch_L_ORIGIN);

		/*起点文本域*/
		JTextField pSearch_T_ORIGIN = new JTextField();
		pSearch_T_ORIGIN.setBounds(591, 76, 96, 27);
		pSearch_T_ORIGIN.setColumns(10);
		pSearchAirlineIn.add(pSearch_T_ORIGIN);

		/*终点标签*/
		JLabel pSearch_L_DEST = new JLabel("终点");
		pSearch_L_DEST.setBounds(490, 121, 81, 21);
		pSearchAirlineIn.add(pSearch_L_DEST);

		/*终点文本域*/
		JTextField pSearch_T_DEST = new JTextField();
		pSearch_T_DEST.setBounds(591, 118, 96, 27);
		pSearch_T_DEST.setColumns(10);
		pSearchAirlineIn.add(pSearch_T_DEST);

		/*确认查询按钮*/
		JButton pSearch_SEARCH = new JButton("查询");
		pSearch_SEARCH.setBounds(515, 171, 123, 29);
		pSearch_SEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* 检查日期格式是否合法 */
				if (pSearch_T_DATE.getText().trim().length() != 8) {
					JOptionPane.showMessageDialog(null, "请输入有效日期", "查询航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				/* 检查起点是否为空 */
				if (pSearch_T_ORIGIN.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "起点不能为空", "查询航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				/* 检查终点是否为空 */
				if (pSearch_T_DEST.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "终点不能为空", "查询航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				/* 编辑文本域获取的输入，发送给服务器 */
				String mes = "07" + pSearch_T_DATE.getText().trim() + "-" + pSearch_T_ORIGIN.getText().trim() + "-"
						+ pSearch_T_DEST.getText().trim();
				me.sendMessage(mes);
				/* 接收服务器回复的消息 */
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
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
					pSearch_T_DATE.setText("");
					pSearch_T_ORIGIN.setText("");
					pSearch_T_DEST.setText("");
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
					pSearch_T_DATE.setText("");
					pSearch_T_ORIGIN.setText("");
					pSearch_T_DEST.setText("");
					break;
				/*若返回值为其他数字，查询失败*/
				default:
					JOptionPane.showMessageDialog(null, "查询失败，请重试", "查询航班", JOptionPane.CLOSED_OPTION);
					break;

				}

			}
		});
		pSearchAirlineIn.add(pSearch_SEARCH);

		/*添加航班面板*/
		pAddAirlineIn = new JPanel();
		pAddAirlineIn.setBounds(0, 0, 1343, 285);
		pAM_Display.add(pAddAirlineIn);
		pAddAirlineIn.setOpaque(false);
		pAddAirlineIn.setLayout(null);
		pAddAirlineIn.setVisible(false);

		/*航班号标签*/
		JLabel pAdd_L_AID = new JLabel("航班号");
		pAdd_L_AID.setBounds(309, 15, 81, 21);
		pAddAirlineIn.add(pAdd_L_AID);

		/*航班号文本域*/
		JTextField pAdd_T_AID = new JTextField();
		pAdd_T_AID.setBounds(309, 40, 81, 27);
		pAddAirlineIn.add(pAdd_T_AID);
		pAdd_T_AID.setColumns(10);

		/*起点标签*/
		JLabel pAdd_L_ORIGIN = new JLabel("起点");
		pAdd_L_ORIGIN.setBounds(397, 15, 81, 21);
		pAddAirlineIn.add(pAdd_L_ORIGIN);

		/*起点文本域*/
		JTextField pAdd_T_ORIGIN = new JTextField();
		pAdd_T_ORIGIN.setColumns(10);
		pAdd_T_ORIGIN.setBounds(397, 40, 81, 27);
		pAddAirlineIn.add(pAdd_T_ORIGIN);

		/*终点标签*/
		JLabel pAdd_L_DEST = new JLabel("终点");
		pAdd_L_DEST.setBounds(484, 15, 81, 21);
		pAddAirlineIn.add(pAdd_L_DEST);

		/*终点文本域*/
		JTextField pAdd_T_DEST = new JTextField();
		pAdd_T_DEST.setColumns(10);
		pAdd_T_DEST.setBounds(484, 40, 81, 27);
		pAddAirlineIn.add(pAdd_T_DEST);

		/*日期标签*/
		JLabel pAdd_L_DATE = new JLabel("日期");
		pAdd_L_DATE.setBounds(572, 15, 81, 21);
		pAddAirlineIn.add(pAdd_L_DATE);

		/*日期文本域*/
		JTextField pAdd_T_DATE = new JTextField();
		pAdd_T_DATE.setColumns(10);
		pAdd_T_DATE.setBounds(572, 40, 81, 27);
		pAddAirlineIn.add(pAdd_T_DATE);

		/*时间标签*/
		JLabel pAdd_L_TIME = new JLabel("时间");
		pAdd_L_TIME.setBounds(656, 15, 81, 21);
		pAddAirlineIn.add(pAdd_L_TIME);

		/*时间文本域*/
		JTextField pAdd_T_TIME = new JTextField();
		pAdd_T_TIME.setColumns(10);
		pAdd_T_TIME.setBounds(656, 40, 81, 27);
		pAddAirlineIn.add(pAdd_T_TIME);

		/*机型标签*/
		JLabel pAdd_L_TYPE = new JLabel("机型");
		pAdd_L_TYPE.setBounds(744, 15, 81, 21);
		pAddAirlineIn.add(pAdd_L_TYPE);

		/*机型文本域*/
		JTextField pAdd_T_TYPE = new JTextField();
		pAdd_T_TYPE.setColumns(10);
		pAdd_T_TYPE.setBounds(744, 40, 81, 27);
		pAddAirlineIn.add(pAdd_T_TYPE);

		/*确认添加航班按钮*/
		JButton pAdd_ADD = new JButton("添加航班");
		/*确认添加航班按钮响应事件*/
		pAdd_ADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*检查航班号是否为空*/
				if (pAdd_T_AID.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入航班号", "添加航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查起点是否为空*/
				if (pAdd_T_ORIGIN.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入起点", "添加航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查终点是否为空*/
				if (pAdd_T_DEST.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入终点", "添加航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查日期格式是否合法*/
				if (pAdd_T_DATE.getText().trim().length() != 8) {
					JOptionPane.showMessageDialog(null, "请输入有效日期", "添加航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查时间是否合法*/
				if (pAdd_T_TIME.getText().trim().length() != 5||pAdd_T_TIME.getText().trim().charAt(2) != ':') {
					JOptionPane.showMessageDialog(null, "请输入有效时间", "添加航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查机型是否为空*/
				if (pAdd_T_TYPE.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入机型", "添加航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "22" + pAdd_T_AID.getText().trim() + "-" + pAdd_T_ORIGIN.getText().trim() + "-"
						+ pAdd_T_DEST.getText().trim() + "-" + pAdd_T_DATE.getText().trim() + "-"
						+ pAdd_T_TIME.getText().trim().substring(0, 2) + "-" + pAdd_T_TIME.getText().trim().substring(3)
						+ "-" + pAdd_T_TYPE.getText().trim();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/* 若返回值为0，没有匹配航班 */
				case "0":
					JOptionPane.showMessageDialog(null, "机型不存在，请检查后再输入", "添加航班", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为1，成功添加航班，显示新添加航班的航班号 */
				case "1":
					JOptionPane.showMessageDialog(null, "成功添加航班 " + pAdd_T_AID.getText().trim(), "添加航班",
							JOptionPane.CLOSED_OPTION);
					/*文本域清空*/
					pAdd_T_AID.setText("");
					pAdd_T_DATE.setText("");
					pAdd_T_TIME.setText("");
					pAdd_T_ORIGIN.setText("");
					pAdd_T_DEST.setText("");
					pAdd_T_TYPE.setText("");
					break;
				/* 若返回值为2，航班号已存在*/
				case "2":
					JOptionPane.showMessageDialog(null, "航班号已存在，请检查后再输入", "添加航班", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为0，添加航班失败 */
				default:
					JOptionPane.showMessageDialog(null, "添加航班失败，请重试", "添加航班", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pAdd_ADD.setBounds(507, 121, 123, 29);
		pAddAirlineIn.add(pAdd_ADD);

		

		/*取消航班面板*/
		pCancelAirlineIn = new JPanel();
		pCancelAirlineIn.setOpaque(false);
		pCancelAirlineIn.setBounds(0, 0, 1343, 285);
		pAM_Display.add(pCancelAirlineIn);
		pCancelAirlineIn.setLayout(null);
		pCancelAirlineIn.setVisible(false);

		/*航班号标签*/
		JLabel pCANCEL_L_AID = new JLabel("航班号");
		pCANCEL_L_AID.setBounds(399, 73, 61, 21);
		pCancelAirlineIn.add(pCANCEL_L_AID);

		/*航班号文本域*/
		JTextField pCANCEL_T_AID = new JTextField();
		pCANCEL_T_AID.setBounds(475, 70, 96, 27);
		pCancelAirlineIn.add(pCANCEL_T_AID);
		pCANCEL_T_AID.setColumns(10);

		/*确认取消航班按钮*/
		JButton pCANCEL_CANCEL = new JButton("取消航班");
		pCANCEL_CANCEL.setBounds(410, 132, 123, 29);
		pCancelAirlineIn.add(pCANCEL_CANCEL);
		/*确认取消航班按钮监听事件*/
		pCANCEL_CANCEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* 检查航班号是否为空 */
				if (pCANCEL_CANCEL.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "航班号不能为空", "取消航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "23" + pCANCEL_T_AID.getText().trim();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/* 若返回值为0，航班号不存在 */
				case "0":
					JOptionPane.showMessageDialog(null, "航班号不存在，请检查后再输入", "取消航班", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为1，取消航班成功 */
				case "1":
					JOptionPane.showMessageDialog(null, "取消航班成功", "取消航班", JOptionPane.CLOSED_OPTION);
					pCANCEL_T_AID.setText("");
					break;
				/* 若返回值为其他数字，取消航班失败 */
				default:
					JOptionPane.showMessageDialog(null, "取消航班失败，请重试", "取消航班", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});

		/*查询航班输出面板*/
		pSearchAirlineOut = new JScrollPane();
		pSearchAirlineOut.setOpaque(false);
		pSearchAirlineOut.setBounds(0, 0, 1343, 285);
		pAM_Display.add(pSearchAirlineOut);
		pSearchAirlineOut.setVisible(false);

		/*创建表结构，置于查询航班输出面板（？也许可以不要？）*/
		//String[][] pSearchAirlineRerow = new String[10][9];
		//String[] pSearchAirlineRecolumn = { "航班号", "起点", "终点", "日期", "时间", "头等舱剩余座位数", "商务舱剩余座位数", "经济舱剩余座位数", "机型" };
		//DefaultTableModel pSearchAirlineRe = new DefaultTableModel(pSearchAirlineRerow, pSearchAirlineRecolumn);
		//JTable pSearchAirlineResultTable = new JTable(pSearchAirlineRe);
		//pSearchAirlineOut.setViewportView(pSearchAirlineResultTable);

		/*
		 * 
		 * 人事管理模块
		 * 
		 *
		 */
		
		
		/*人事管理模块底版*/
		pHRManagement = new JPanel();
		pHRManagement.setOpaque(false);
		pHRManagement.setBounds(12, 30, 1478, 677);
		panel.add(pHRManagement);
		pHRManagement.setLayout(null);
		pHRManagement.setVisible(false);

		/*进入业务员管理面板按钮*/
		JButton pHRM_SALESMAN = new JButton("<html>业务员<br>管理<html>");
		/*进入业务员管理面板按钮监听事件*/
		pHRM_SALESMAN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*显示业务员管理面板，隐藏其他面板*/
				pd_SALESMAN.setVisible(true);
				pd_DELIVERYMAN.setVisible(false);
				pd_OPERATOR.setVisible(false);
			}
		});
		pHRM_SALESMAN.setHorizontalAlignment(SwingConstants.TRAILING);
		pHRM_SALESMAN.setBounds(15, 15, 84, 72);
		pHRManagement.add(pHRM_SALESMAN);

		/*进入送票员管理面板按钮*/
		JButton pHRM_DELIVERYMAN = new JButton("<html>送票员<br>管理<html>");
		/*进入送票员管理面板按钮监听事件*/
		pHRM_DELIVERYMAN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*显示送票员管理面板，隐藏其他面板*/
				pd_SALESMAN.setVisible(false);
				pd_DELIVERYMAN.setVisible(true);
				pd_OPERATOR.setVisible(false);
			}
		});
		pHRM_DELIVERYMAN.setBounds(15, 102, 84, 72);
		pHRManagement.add(pHRM_DELIVERYMAN);
		
		/*进入操作员管理面板按钮*/
		JButton pHRM_OPERATOR = new JButton("<html>操作员<br>管理<html>");
		/*进入操作员管理面板按钮监听事件*/
		pHRM_OPERATOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*显示操作员管理面板，隐藏其他面板*/
				pd_SALESMAN.setVisible(false);
				pd_DELIVERYMAN.setVisible(false);
				pd_OPERATOR.setVisible(true);
			}
		});
		pHRM_OPERATOR.setBounds(15, 189, 84, 72);
		pHRManagement.add(pHRM_OPERATOR);

		/*子面板显示面板*/
		pHRM_Display = new JPanel();
		pHRM_Display.setOpaque(false);
		pHRM_Display.setBounds(119, 15, 1343, 285);
		pHRManagement.add(pHRM_Display);
		pHRM_Display.setLayout(null);

		/*业务员管理面板*/
		pd_SALESMAN = new JPanel();
		pd_SALESMAN.setOpaque(false);
		pd_SALESMAN.setBounds(0, 0, 1343, 285);
		pHRM_Display.add(pd_SALESMAN);
		pd_SALESMAN.setLayout(null);
		pd_SALESMAN.setVisible(false);

		/*要添加的业务员姓名标签*/
		JLabel pSalesman_L_ADD_NAME = new JLabel("姓名");
		pSalesman_L_ADD_NAME.setBounds(28, 26, 81, 21);
		pd_SALESMAN.add(pSalesman_L_ADD_NAME);

		/*要添加的业务员姓名文本域*/
		JTextField pSalesman_T_ADD_NAME = new JTextField();
		pSalesman_T_ADD_NAME.setBounds(123, 23, 96, 27);
		pd_SALESMAN.add(pSalesman_T_ADD_NAME);
		pSalesman_T_ADD_NAME.setColumns(10);

		/*要添加的业务员所属售票点id标签*/
		JLabel pSalesman_L_ADD_TID = new JLabel("售票点ID");
		pSalesman_L_ADD_TID.setBounds(27, 65, 81, 21);
		pd_SALESMAN.add(pSalesman_L_ADD_TID);

		/*要添加的业务员所属售票点id文本域*/
		JTextField pSalesman_T_ADD_TID = new JTextField();
		pSalesman_T_ADD_TID.setColumns(10);
		pSalesman_T_ADD_TID.setBounds(123, 62, 96, 27);
		pd_SALESMAN.add(pSalesman_T_ADD_TID);

		/*添加业务员按钮*/
		JButton pSalesman_ADD = new JButton("确认添加");
		/*添加业务员按钮响应事件*/
		pSalesman_ADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*检查业务员姓名是否为空*/
				if (pSalesman_T_ADD_NAME.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入业务员姓名", "添加业务员", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查业务员所属售票点ID是否为空*/
				if (pSalesman_T_ADD_TID.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入业务员所属售票点ID", "添加业务员", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "24" + pSalesman_T_ADD_NAME.getText().trim() + "-" + pSalesman_T_ADD_TID.getText().trim();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/* 若返回值为3，售票点ID不存在 */
				case "3":
					JOptionPane.showMessageDialog(null, "售票点ID不存在，请检查后输入", "添加业务员", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为0，成功添加业务员，显示新业务员id */
				case "0":
					String output = result.substring(1);
					JOptionPane.showMessageDialog(null, "成功！添加业务员 " + output, "添加业务员", JOptionPane.CLOSED_OPTION);
					pSalesman_T_ADD_NAME.setText("");
					pSalesman_T_ADD_TID.setText("");
					break;
				/* 若返回值为其他数字，添加业务员失败 */
				default:
					JOptionPane.showMessageDialog(null, "添加业务员失败，请重试", "添加业务员", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pSalesman_ADD.setBounds(56, 160, 123, 29);
		pd_SALESMAN.add(pSalesman_ADD);

		/*要删除的业务员id标签*/
		JLabel pSalesman_L_CANCEL_SID = new JLabel("业务员ID");
		pSalesman_L_CANCEL_SID.setBounds(511, 26, 81, 21);
		pd_SALESMAN.add(pSalesman_L_CANCEL_SID);

		/*要删除的业务员id文本域*/
		JTextField pSalesman_T_CANCEL_SID = new JTextField();
		pSalesman_T_CANCEL_SID.setColumns(10);
		pSalesman_T_CANCEL_SID.setBounds(607, 23, 96, 27);
		pd_SALESMAN.add(pSalesman_T_CANCEL_SID);

		/*删除业务员按钮*/
		JButton pSalesman_CANCEL = new JButton("确认删除");
		/*删除业务员按钮响应事件*/
		pSalesman_CANCEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*检查业务员id是否为空*/
				if (pSalesman_T_CANCEL_SID.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入业务员ID", "删除业务员", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "25" + pSalesman_T_CANCEL_SID.getText().trim();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/* 若返回值为2，业务员id不存在 */
				case "2":
					JOptionPane.showMessageDialog(null, "业务员ID不存在，请检查后再输入", "删除业务员", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为0，删除业务员成功 */
				case "0":
					JOptionPane.showMessageDialog(null, "删除业务员成功", "删除业务员", JOptionPane.CLOSED_OPTION);
					pSalesman_T_CANCEL_SID.setText("");
					break;
				/* 若返回值为其他数字，删除业务员失败*/
				default:
					JOptionPane.showMessageDialog(null, "删除业务员失败，请重试", "删除业务员", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pSalesman_CANCEL.setBounds(540, 160, 123, 29);
		pd_SALESMAN.add(pSalesman_CANCEL);

		/*送票员管理面板*/
		pd_DELIVERYMAN = new JPanel();
		pd_DELIVERYMAN.setOpaque(false);
		pd_DELIVERYMAN.setBounds(0, 0, 1343, 285);
		pHRM_Display.add(pd_DELIVERYMAN);
		pd_DELIVERYMAN.setLayout(null);
		pd_DELIVERYMAN.setVisible(false);

		/*要添加的送票员姓名标签*/
		JLabel pDeliveryman_L_ADD_NAME = new JLabel("姓名");
		pDeliveryman_L_ADD_NAME.setBounds(28, 26, 81, 21);
		pd_DELIVERYMAN.add(pDeliveryman_L_ADD_NAME);

		/*要添加的送票员姓名文本域*/
		JTextField pDeliveryman_T_ADD_NAME = new JTextField();
		pDeliveryman_T_ADD_NAME.setBounds(123, 23, 96, 27);
		pd_DELIVERYMAN.add(pDeliveryman_T_ADD_NAME);
		pDeliveryman_T_ADD_NAME.setColumns(10);

		/*要添加的送票员所属售票点id标签*/
		JLabel pDeliveryman_L_ADD_TID = new JLabel("售票点ID");
		pDeliveryman_L_ADD_TID.setBounds(28, 65, 81, 21);
		pd_DELIVERYMAN.add(pDeliveryman_L_ADD_TID);

		/*要添加的送票员所属售票点id文本域*/
		JTextField pDeliveryman_T_ADD_TID = new JTextField();
		pDeliveryman_T_ADD_TID.setColumns(10);
		pDeliveryman_T_ADD_TID.setBounds(123, 62, 96, 27);
		pd_DELIVERYMAN.add(pDeliveryman_T_ADD_TID);

		/*添加送票员按钮*/
		JButton pDeliveryman_ADD = new JButton("确认添加");
		/*添加送票员按钮响应事件*/
		pDeliveryman_ADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* 检查姓名是否为空 */
				if (pDeliveryman_T_ADD_NAME.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入送票员姓名", "添加送票员", JOptionPane.CLOSED_OPTION);
					return;
				}
				/* 检查送票员所属售票点ID是否为空 */
				if (pDeliveryman_T_ADD_TID.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入送票员所属售票点ID", "添加送票员", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "26" + pDeliveryman_T_ADD_NAME.getText().trim() + "-"
						+ pDeliveryman_T_ADD_TID.getText().trim();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/* 若返回值为3，售票点ID不存在 */
				case "3":
					JOptionPane.showMessageDialog(null, "售票点ID不存在，请检查后再输入", "添加送票员", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为0，成功添加送票员，显示新送票员id  */
				case "0":
					String output = result.substring(1);
					JOptionPane.showMessageDialog(null, "成功!添加送票员 " + output, "添加送票员", JOptionPane.CLOSED_OPTION);
					pDeliveryman_T_ADD_NAME.setText("");
					pDeliveryman_T_ADD_TID.setText("");
					break;
				/* 若返回值为其他数字，添加送票员失败 */
				default:
					JOptionPane.showMessageDialog(null, "添加送票员失败，请重试", "添加送票员", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pDeliveryman_ADD.setBounds(56, 160, 123, 29);
		pd_DELIVERYMAN.add(pDeliveryman_ADD);

		/*要删除的送票员id标签*/
		JLabel pDeliveryman_L_CANCEL_SID = new JLabel("送票员ID");
		pDeliveryman_L_CANCEL_SID.setBounds(511, 26, 81, 21);
		pd_DELIVERYMAN.add(pDeliveryman_L_CANCEL_SID);

		/*要删除的送票员id文本域*/
		JTextField pDeliveryman_T_CANCEL_SID = new JTextField();
		pDeliveryman_T_CANCEL_SID.setColumns(10);
		pDeliveryman_T_CANCEL_SID.setBounds(607, 23, 96, 27);
		pd_DELIVERYMAN.add(pDeliveryman_T_CANCEL_SID);

		/*删除送票员按钮*/
		JButton pDeliveryman_CANCEL = new JButton("确认删除");
		/*删除送票员按钮响应事件*/
		pDeliveryman_CANCEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* 检查送票员id是否为空*/
				if (pDeliveryman_T_CANCEL_SID.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入送票员ID", "删除送票员", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "27" + pDeliveryman_T_CANCEL_SID.getText().trim();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/* 若返回值为2，没有匹配航班 */
				case "2":
					JOptionPane.showMessageDialog(null, "送票员ID不存在，请检查后再输入", "删除送票员", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为0，删除送票员成功 */
				case "0":
					JOptionPane.showMessageDialog(null, "删除送票员成功", "删除送票员", JOptionPane.CLOSED_OPTION);
					pDeliveryman_T_CANCEL_SID.setText("");
					break;
				/* 若返回值为其他数字，删除送票员失败*/
				default:
					JOptionPane.showMessageDialog(null, "删除送票员失败，请重试", "删除送票员", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pDeliveryman_CANCEL.setBounds(540, 160, 123, 29);
		pd_DELIVERYMAN.add(pDeliveryman_CANCEL);


		/*操作员管理面板*/
		pd_OPERATOR = new JPanel();
		pd_OPERATOR.setOpaque(false);
		pd_OPERATOR.setBounds(0, 0, 1343, 285);
		pHRM_Display.add(pd_OPERATOR);
		pd_OPERATOR.setLayout(null);
		pd_OPERATOR.setVisible(false);

		/*密码标签*/
		JLabel pOPERATOR_L_ADD_NAME = new JLabel("密码设置");
		pOPERATOR_L_ADD_NAME.setBounds(28, 26, 81, 21);
		pd_OPERATOR.add(pOPERATOR_L_ADD_NAME);

		/*密码文本域*/
		JTextField pOPERATOR_T_ADD_NAME = new JTextField();
		pOPERATOR_T_ADD_NAME.setBounds(123, 23, 96, 27);
		pd_OPERATOR.add(pOPERATOR_T_ADD_NAME);
		pOPERATOR_T_ADD_NAME.setColumns(10);

		/*添加操作员按钮*/
		JButton pOPERATOR_ADD = new JButton("确认添加");
		/*添加操作员按钮响应事件*/
		pOPERATOR_ADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* 检查密码是否为空 */
				if (pOPERATOR_T_ADD_NAME.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入新操作员账号的初始密码", "添加操作员", JOptionPane.CLOSED_OPTION);
					return;
				}
				/* 检查密码格式是否合法 */
				if (pOPERATOR_T_ADD_NAME.getText().trim().length() < 4
						|| pOPERATOR_T_ADD_NAME.getText().trim().length() > 8) {
					JOptionPane.showMessageDialog(null, "密码长度错误，请输入4~8位密码", "添加操作员", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "28" + pOPERATOR_T_ADD_NAME.getText().trim();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);
				switch (sig) {
				/* 若返回值为2，操作员ID不存在 */
				case "2":
					JOptionPane.showMessageDialog(null, "操作员ID不存在，请检查后再输入", "添加操作员", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为0，成功添加操作员，返回新操作员id */
				case "0":
					String output = result.substring(1);
					JOptionPane.showMessageDialog(null, "成功!添加操作员" + output, "添加操作员", JOptionPane.CLOSED_OPTION);
					pOPERATOR_T_ADD_NAME.setText("");
					break;
				/* 若返回值为其他数字，没有添加操作员失败 */
				default:
					JOptionPane.showMessageDialog(null, "添加操作员失败，请重试", "添加操作员", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pOPERATOR_ADD.setBounds(56, 160, 123, 29);
		pd_OPERATOR.add(pOPERATOR_ADD);

		/*要删除的操作员id标签*/
		JLabel pOPERATOR_L_CANCEL_SID = new JLabel("操作员ID");
		pOPERATOR_L_CANCEL_SID.setBounds(511, 26, 81, 21);
		pd_OPERATOR.add(pOPERATOR_L_CANCEL_SID);

		/*要删除的操作员id文本域*/
		JTextField pOPERATOR_T_CANCEL_SID = new JTextField();
		pOPERATOR_T_CANCEL_SID.setColumns(10);
		pOPERATOR_T_CANCEL_SID.setBounds(607, 23, 96, 27);
		pd_OPERATOR.add(pOPERATOR_T_CANCEL_SID);

		/*删除操作员按钮*/
		JButton pOPERATOR_CANCEL = new JButton("确认删除");
		/*删除操作员按钮响应事件*/
		pOPERATOR_CANCEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*检查操作员id是否为空*/
				if (pOPERATOR_T_CANCEL_SID.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入操作员ID", "删除操作员", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "29" + pOPERATOR_T_CANCEL_SID.getText().trim();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/* 若返回值为2，没有匹配航班 */
				case "2":
					JOptionPane.showMessageDialog(null, "操作员ID不存在，请检查后再输入", "删除操作员", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为0，删除操作员成功 */
				case "0":
					JOptionPane.showMessageDialog(null, "删除操作员成功", "删除操作员", JOptionPane.CLOSED_OPTION);
					pOPERATOR_T_CANCEL_SID.setText("");
					break;
				/* 若返回值为其他数字，删除操作员失败 */
				default:
					JOptionPane.showMessageDialog(null, "删除操作员失败，请重试", "删除操作员", JOptionPane.CLOSED_OPTION);
					break;

				}

			}
		});
		pOPERATOR_CANCEL.setBounds(540, 160, 123, 29);
		pd_OPERATOR.add(pOPERATOR_CANCEL);

		/*
		 * 
		 * 售票点管理模块
		 *
		 * 
		 */

		/*售票点管理模块底版*/
		pTicketBoxManagement = new JPanel();
		pTicketBoxManagement.setOpaque(false);
		pTicketBoxManagement.setBounds(12, 30, 1478, 677);
		panel.add(pTicketBoxManagement);
		pTicketBoxManagement.setLayout(null);

		/*进入添加售票点面板按钮*/
		JButton pTBM_ADD = new JButton("<html>添加<br>售票点<html>");
		/*进入添加售票点面板按钮响应事件*/
		pTBM_ADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*显示添加售票点面板面板，隐藏取消售票点面板*/
				pAddTBIn.setVisible(true);
				pCancelTBIn.setVisible(false);
			}
		});
		pTBM_ADD.setBounds(15, 100, 89, 58);
		pTicketBoxManagement.add(pTBM_ADD);

		/*进入取消售票点面板按钮*/
		JButton pTBM_CANCEL = new JButton("<html>取消<br>售票点<html>");
		/*进入取消售票点面板按钮响应事件*/
		pTBM_CANCEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*显示取消售票点面板面板，隐藏添加售票点面板*/
				pAddTBIn.setVisible(false);
				pCancelTBIn.setVisible(true);

			}
		});
		pTBM_CANCEL.setBounds(15, 177, 89, 58);
		pTicketBoxManagement.add(pTBM_CANCEL);

		/*子面板显示面板*/
		JPanel pTBM_Display = new JPanel();
		pTBM_Display.setOpaque(false);
		pTBM_Display.setBounds(119, 15, 797, 220);
		pTicketBoxManagement.add(pTBM_Display);
		pTicketBoxManagement.setVisible(false);
		pTBM_Display.setLayout(null);

		/*添加售票点面板*/
		pAddTBIn = new JPanel();
		pAddTBIn.setOpaque(false);
		pAddTBIn.setBounds(28, 15, 754, 190);
		pTBM_Display.add(pAddTBIn);
		pAddTBIn.setLayout(null);
		pAddTBIn.setVisible(false);

		/*售票点名称标签*/
		JLabel pAdd_L_TBNAME = new JLabel("售票点名称");
		pAdd_L_TBNAME.setBounds(15, 15, 114, 27);
		pAddTBIn.add(pAdd_L_TBNAME);

		/*售票点名称文本域*/
		JTextField pAdd_T_TBNAME = new JTextField();
		pAdd_T_TBNAME.setBounds(124, 15, 193, 27);
		pAddTBIn.add(pAdd_T_TBNAME);
		pAdd_T_TBNAME.setColumns(10);

		/*售票点地址标签*/
		JLabel pAdd_L_TBADD = new JLabel("地址");
		pAdd_L_TBADD.setBounds(15, 57, 81, 21);
		pAddTBIn.add(pAdd_L_TBADD);

		/*售票点地址文本域*/
		JTextField pAdd_T_TBADD = new JTextField();
		pAdd_T_TBADD.setColumns(10);
		pAdd_T_TBADD.setBounds(124, 57, 483, 27);
		pAddTBIn.add(pAdd_T_TBADD);

		/*添加售票点按钮*/
		JButton pAdd_TADD = new JButton("添加售票点");
		/*添加售票点按钮响应事件*/
		pAdd_TADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*检查售票点名称是否为空*/
				if (pAdd_T_TBNAME.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入售票点名称", "添加售票点", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查售票点地址是否为空*/
				if (pAdd_T_TBADD.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入售票点地址", "添加售票点", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "30" + pAdd_T_TBNAME.getText().trim() + "-" + pAdd_T_TBADD.getText().trim();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/* 若返回值为0，成功添加售票点，显示售票点id*/
				case "0":
					String output = result.substring(1);
					JOptionPane.showMessageDialog(null, "成功!添加售票点" + output, "添加售票点", JOptionPane.CLOSED_OPTION);
					pAdd_T_TBNAME.setText("");
					pAdd_T_TBADD.setText("");
					break;
				/* 若返回值为其他数字，添加售票点失败*/
				default:
					JOptionPane.showMessageDialog(null, "添加售票点失败，请重试", "添加售票点", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pAdd_TADD.setBounds(45, 121, 123, 29);
		pAddTBIn.add(pAdd_TADD);

		/*取消售票点面板*/
		pCancelTBIn = new JPanel();
		pCancelTBIn.setOpaque(false);
		pCancelTBIn.setBounds(28, 15, 754, 190);
		pTBM_Display.add(pCancelTBIn);
		pCancelTBIn.setLayout(null);
		pCancelTBIn.setVisible(false);

		/*要取消的售票点id标签*/
		JLabel pCANCEL_L_TBID = new JLabel("售票点ID");
		pCANCEL_L_TBID.setBounds(61, 34, 80, 21);
		pCancelTBIn.add(pCANCEL_L_TBID);

		/*要取消的售票点id文本域*/
		JTextField pCANCEL_T_TBID = new JTextField();
		pCANCEL_T_TBID.setBounds(137, 31, 96, 27);
		pCancelTBIn.add(pCANCEL_T_TBID);
		pCANCEL_T_TBID.setColumns(10);

		/*取消售票点按钮*/
		JButton pCANCEL_TCANCEL = new JButton("取消售票点");
		pCANCEL_TCANCEL.setBounds(72, 93, 123, 29);
		pCancelTBIn.add(pCANCEL_TCANCEL);
		/*取消售票点按钮响应事件*/
		pCANCEL_TCANCEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*检查售票点id是否为空*/
				if (pCANCEL_T_TBID.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入售票点ID", "取消售票点", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "31" + pCANCEL_T_TBID.getText().trim();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/* 若返回值为2，售票点ID不存在 */
				case "2":
					JOptionPane.showMessageDialog(null, "售票点ID不存在，请检查后再输入", "取消售票点", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为0，删除售票点成功 */
				case "0":
					JOptionPane.showMessageDialog(null, "删除售票点成功", "取消售票点", JOptionPane.CLOSED_OPTION);
					pCANCEL_T_TBID.setText("");
					break;
				/* 若返回值为2，删除售票点失败 */
				default:
					JOptionPane.showMessageDialog(null, "删除售票点失败，请重试", "取消售票点", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});

		/*
		 * 
		 * 报表统计功能模块
		 * 
		 * 
		 */
		
		/*报表统计功能模块底版*/
		pStatistics = new JPanel();
		pStatistics.setOpaque(false);
		pStatistics.setBounds(12, 30, 1478, 677);
		panel.add(pStatistics);
		pStatistics.setLayout(null);
		pStatistics.setVisible(false);

		/*报表输出面板*/
		pStatisticsOut = new JPanel();
		pStatisticsOut.setOpaque(false);
		pStatisticsOut.setBounds(25, 15, 910, 252);
		pStatistics.add(pStatisticsOut);
		pStatisticsOut.setLayout(null);
		pStatisticsOut.setVisible(false);

		/*报表表格显示面板*/
		pStatisticsTable = new JScrollPane();
		pStatisticsTable.setBounds(15, 59, 880, 178);
		pStatisticsOut.add(pStatisticsTable);
		pStatisticsTable.setVisible(false);

		/*报表统计输入面板*/
		pStatisticsIn = new JPanel();
		pStatisticsIn.setOpaque(false);
		pStatisticsIn.setBounds(25, 15, 910, 252);
		pStatistics.add(pStatisticsIn);
		pStatisticsIn.setLayout(null);

		/*业务员业绩情况标签*/
		JLabel pSI_L_SDATA = new JLabel("业务员业绩情况");
		pSI_L_SDATA.setBounds(86, 40, 162, 21);
		pStatisticsIn.add(pSI_L_SDATA);

		/*业务员id标签*/
		JLabel pSI_L_SID = new JLabel("业务员ID");
		pSI_L_SID.setBounds(285, 40, 81, 21);
		pStatisticsIn.add(pSI_L_SID);

		/*业务员id文本域*/
		JTextField pSI_T_SID = new JTextField();
		pSI_T_SID.setColumns(10);
		pSI_T_SID.setBounds(368, 37, 96, 27);
		pStatisticsIn.add(pSI_T_SID);

		/*生成业务员业绩报表按钮*/
		JButton pSI_SDATACHART = new JButton("生成报表");
		/*生成业务员业绩报表按钮响应事件*/
		pSI_SDATACHART.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*检验业务员id是否为空*/
				if (pSI_T_SID.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入业务员ID", "生成报表", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "32" + pSI_T_SID.getText().trim();
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/* 若返回值为1，业务员ID不存在*/
				case "1":
					JOptionPane.showMessageDialog(null, "业务员ID不存在，请检查后再输入", "生成报表", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为2，没有业绩 */
				case "2":
					JOptionPane.showMessageDialog(null, "没有业绩", "生成报表", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为3，显示结果 */
				case "3":
					int count = Integer.parseInt(result.substring(1, 4));//结果条数
					JOptionPane.showMessageDialog(null, "共有 " + count + " 条结果", "生成报表", JOptionPane.CLOSED_OPTION);
					String output = result.substring(4);
					/* 将结果串处理成矩阵，后处理成表结构显示 */
					String[][] table = MakeMatrix.makeMatrix(output, count, 4);
					String[][] row_pSO = new String[0][4];
					String[] col_pSO = { "序号", "订单号", "客户ID", "航班号" };
					DefaultTableModel ps_rs = new DefaultTableModel(row_pSO, col_pSO);
					for (int i = 0; i < table.length; i++) {
						ps_rs.addRow(table[i]);
					}
					JTable ps_rs_t = new JTable(ps_rs);
					pStatisticsTable.setViewportView(ps_rs_t);
					pStatisticsOut.add(pStatisticsTable);
					/* 隐藏输入面板，显示输出面板 */
					pStatisticsTable.setVisible(true);
					pStatisticsIn.setVisible(false);
					pStatisticsOut.setVisible(true);
					/* 文本域清空 */
					pSI_T_SID.setText("");
					break;
					/* 若返回值为2，没有匹配航班 */
				default:
					JOptionPane.showMessageDialog(null, "生成报表失败，请重试", "生成报表", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pSI_SDATACHART.setBounds(502, 36, 123, 29);
		pStatisticsIn.add(pSI_SDATACHART);

		/*客户信息报表标签*/
		JLabel pSI_L_CDATA = new JLabel("客户信息报表");
		pSI_L_CDATA.setBounds(86, 102, 162, 21);
		pStatisticsIn.add(pSI_L_CDATA);

		/*生成客户信息报表按钮*/
		JButton pSI_CDATACHART = new JButton("生成报表");
		/*生成客户信息报表按钮响应事件*/
		pSI_CDATACHART.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*编辑文本域输入的信息，发送给服务器*/
				String mes = "33" + "0";
				me.sendMessage(mes);
				/*接收服务器返回的消息*/
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);//提取返回值
				switch (sig) {
				/* 若返回值为2，没有客户 */
				case "2":
					JOptionPane.showMessageDialog(null, "没有客户", "生成报表", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为3，返回的结果串编辑后显示在输出面板上 */
				case "3":
					int count = Integer.parseInt(result.substring(1, 4));//结果条数
					JOptionPane.showMessageDialog(null, "共有 " + count + " 条结果", "生成报表", JOptionPane.CLOSED_OPTION);
					String output = result.substring(4);
					/* 将结果串处理成矩阵，后处理成表结构显示 */
					String[][] table = MakeMatrix.makeMatrix(output, count, 5);
					String[][] row_pSO = new String[0][5];
					String[] col_pSO = { "序号", "客户ID", "姓名", "身份证号", "手机号" };
					DefaultTableModel ps_rs = new DefaultTableModel(row_pSO, col_pSO);
					for (int i = 0; i < table.length; i++) {
						ps_rs.addRow(table[i]);
					}
					JTable ps_rs_t = new JTable(ps_rs);
					pStatisticsTable.setViewportView(ps_rs_t);
					pStatisticsOut.add(pStatisticsTable);
					/* 隐藏输入面板，显示输出面板 */
					pStatisticsTable.setVisible(true);
					pStatisticsIn.setVisible(false);
					pStatisticsOut.setVisible(true);
					break;
				/* 若返回值为其他数字，生成报表失败 */
				default:
					JOptionPane.showMessageDialog(null, "生成报表失败，请重试", "生成报表", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		pSI_CDATACHART.setBounds(502, 98, 123, 29);
		pStatisticsIn.add(pSI_CDATACHART);

		/*进入航班管理功能面板*/
		JButton cpAM = new JButton("<html>航班<br>管理<html>");
		cpAM.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				/*只显示航班管理功能底版，关闭所有其他功能面板和子面板*/
				pAddAirlineIn.setVisible(false);
				pCancelAirlineIn.setVisible(false);
				pSearchAirlineIn.setVisible(false);
				pSearchAirlineOut.setVisible(false);
				pAirlineManagement.setVisible(true);
				pHRManagement.setVisible(false);
				pTicketBoxManagement.setVisible(false);
				pStatistics.setVisible(false);

			}
		});
		cpAM.setBounds(44, 12, 97, 87);
		contentPane.add(cpAM);

		/*进入人事管理功能面板*/
		JButton cpHRM = new JButton("<html>人事<br>管理<html>");
		cpHRM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*只显示人事管理管理功能底版，关闭所有其他功能面板和子面板*/
				pd_SALESMAN.setVisible(false);
				pd_DELIVERYMAN.setVisible(false);
				pd_OPERATOR.setVisible(false);
				pHRManagement.setVisible(true);
				pAirlineManagement.setVisible(false);
				pTicketBoxManagement.setVisible(false);
				pStatistics.setVisible(false);

			}
		});
		cpHRM.setBounds(157, 12, 97, 87);
		contentPane.add(cpHRM);

		/*进入售票点管理功能面板*/
		JButton cpTM = new JButton("<html>售票点<br>管理<html>");
		cpTM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*只显示售票点管理功能底版，关闭所有其他功能面板和子面板*/
				pAddTBIn.setVisible(false);
				pCancelTBIn.setVisible(false);
				pTicketBoxManagement.setVisible(true);
				pAirlineManagement.setVisible(false);
				pHRManagement.setVisible(false);
				pStatistics.setVisible(false);

			}
		});
		cpTM.setBounds(270, 12, 97, 87);
		contentPane.add(cpTM);

		/*进入报表统计功能面板*/
		JButton cpS = new JButton("<html>报表<br>统计<html>");
		cpS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*只显示报表统计功能面板，关闭所有其他功能面板和子面板*/
				pStatisticsOut.setVisible(false);
				pStatisticsIn.setVisible(true);
				pStatistics.setVisible(true);
				pAirlineManagement.setVisible(false);
				pHRManagement.setVisible(false);
				pTicketBoxManagement.setVisible(false);

			}
		});
		cpS.setBounds(384, 12, 97, 87);
		contentPane.add(cpS);

	}
}