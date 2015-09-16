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

public class Operator extends JFrame {

	private JPanel contentPane;
	
	private JPanel pSearchAirline;
	private JPanel pBookAirline;
	private JPanel pCancelAirline;
	private JPanel pProfileUpdate;
	private JPanel pPrintSchedule;
	private JPanel pAddClient;
	private JPanel pSearchClient;
	
	private CS.Client me;
	
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client cu=new Client();
					Operator frame = new Operator(cu);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Operator(Client current) {
		me=current;
		
		ImageIcon bg = new ImageIcon("msn_background.jpg"); 
		// 把背景图片显示在一个标签里
		JLabel label = new JLabel(bg); 
		//把标签的大小位置设置为图片刚好填充整个面
		label.setBounds(0,0,1020, 610);
		//添加图片到frame的第二层 
		this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); 
		//获取frame的最上层面板为了设置其背景颜色（JPanel有设置透明的方法）
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 610);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(12, 158, 974, 369);
		contentPane.add(panel);
		panel.setLayout(null);
		
		/*
		 * 
		 * 查询航班界面 输入输出界面都在
		 * 
		 * 
		 * 
		*/
		pSearchAirline = new JPanel();
		pSearchAirline.setOpaque(false);
		pSearchAirline.setBounds(12, 30, 950, 282);
		panel.add(pSearchAirline);
		pSearchAirline.setLayout(null);
		
		pSearchAirline.setVisible(false);	
		
		
		
		JPanel pSearchAirlineIn = new JPanel();
		pSearchAirlineIn.setOpaque(false);
		pSearchAirlineIn.setBounds(47, 32, 270, 205);
		pSearchAirline.add(pSearchAirlineIn);
		pSearchAirlineIn.setLayout(null);
		
		JLabel pSAI_L_DATE = new JLabel("日期");
		pSAI_L_DATE.setBounds(43, 8, 81, 21);
		pSearchAirlineIn.add(pSAI_L_DATE);
		
		JTextField pSAI_T_DATE = new JTextField();
		pSAI_T_DATE.setBounds(144, 5, 96, 27);
		pSAI_T_DATE.setColumns(10);
		pSearchAirlineIn.add(pSAI_T_DATE);
		
		JLabel pSAI_L_ORIGIN = new JLabel("起点");
		pSAI_L_ORIGIN.setBounds(43, 50, 81, 21);
		pSearchAirlineIn.add(pSAI_L_ORIGIN);
		
		JTextField pSAI_T_ORIGIN = new JTextField();
		pSAI_T_ORIGIN.setBounds(144, 47, 96, 27);
		pSAI_T_ORIGIN.setColumns(10);
		pSearchAirlineIn.add(pSAI_T_ORIGIN);
		
		JLabel pSAI_L_DEST = new JLabel("终点");
		pSAI_L_DEST.setBounds(43, 92, 81, 21);
		pSearchAirlineIn.add(pSAI_L_DEST);
		
		JTextField pSAI_T_DEST = new JTextField();
		pSAI_T_DEST.setBounds(144, 89, 96, 27);
		pSAI_T_DEST.setColumns(10);
		pSearchAirlineIn.add(pSAI_T_DEST);
		
		JButton pSAI_SEARCH = new JButton("New button");
		
		pSAI_SEARCH.setBounds(68, 142, 123, 29);
		pSearchAirlineIn.add(pSAI_SEARCH);
		
		
		
		JScrollPane pSearchAirlineOut = new JScrollPane();
		pSearchAirlineOut.setBounds(15, 48, 912, 160);
		pSearchAirline.add(pSearchAirlineOut);
		
		

		pSAI_SEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pSAI_T_DATE.getText().trim().length()!=8){
					JOptionPane.showMessageDialog(null,  "请输入有效日期","查询航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				
				if(pSAI_T_ORIGIN.getText().trim().length()==0){
					JOptionPane.showMessageDialog(null,  "起点不能为空","查询航班", JOptionPane.CLOSED_OPTION);
					return;
				}
				
				if(pSAI_T_DEST.getText().trim().length()==0){
					JOptionPane.showMessageDialog(null,  "终点不能为空","查询航班", JOptionPane.CLOSED_OPTION);
					return;
				}
					
				String mes="07"+pSAI_T_DATE.getText().trim()+"-"+pSAI_T_ORIGIN.getText().trim()+"-"+pSAI_T_DEST.getText().trim();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				
				
				switch(sig){
				case "2":
					JOptionPane.showMessageDialog(null,  "没有匹配航班","查询航班", JOptionPane.CLOSED_OPTION);
					break;
				case "0":
					int count=Integer.parseInt(result.substring(1,3));
					JOptionPane.showMessageDialog(null,  "共有 "+count+" 条结果","查询航班", JOptionPane.CLOSED_OPTION);
					String output=result.substring(3);
					String[][] table=MakeMatrix.makeMatrix(output, count, 10);
					
					
					String[][] prow=new String[0][10];

					String[] pcol={"序号","航班号","起点","终点","日期","时间","头等舱剩余座位","商务舱剩余座位","经济舱剩余座位","机型"};

					DefaultTableModel pmodel=new DefaultTableModel(prow,pcol);
					for(int i=0;i<table.length;i++){
						pmodel.addRow(table[i]);
					}
					JTable ptable = new JTable(pmodel);

					pSearchAirlineOut.setViewportView(ptable);
					pSearchAirlineIn.setVisible(false);
					pSearchAirlineOut.setVisible(true);
					break;
				case "1":
					int count2=Integer.parseInt(result.substring(1,3));
					JOptionPane.showMessageDialog(null,  "没有直航航班，建议您转机前往，共有 "+count2+" 种组合","查询航班", JOptionPane.CLOSED_OPTION);
					String output2=result.substring(3);
					String[][] table2=MakeMatrix.makeMatrix(output2, count2*2, 10);
					
					
					String[][] prow2=new String[0][10];

					String[] pcol2={"序号","航班号","起点","终点","日期","时间","头等舱可选","商务舱可选","经济舱可选","机型"};
					String[] blank2={" "," "," "," "," "," "," "," "," "," "};

					DefaultTableModel pmodel2=new DefaultTableModel(prow2,pcol2);
					for(int i=0;i<table2.length;i++){
						if(i!=0&&i%2==0)
							pmodel2.addRow(blank2);
						pmodel2.addRow(table2[i]);
						
					}
					JTable ptable2 = new JTable(pmodel2);

					pSearchAirlineOut.setViewportView(ptable2);
					pSearchAirlineIn.setVisible(false);
					pSearchAirlineOut.setVisible(true);
					break;
				default:
					JOptionPane.showMessageDialog(null,  "查询失败，请重试","查询航班", JOptionPane.CLOSED_OPTION);
					break;
				
				
				}
				
				
				
			}
		});
		
		 
		
		
		
		/*定航班
		*/
		pBookAirline = new JPanel();
		pBookAirline.setOpaque(false);
		pBookAirline.setBounds(12, 30, 950, 282);
		panel.add(pBookAirline);
		pBookAirline.setLayout(null);
		pBookAirline.setVisible(false);
		
		
		JTextField pBA_T_AID = new JTextField();
		pBA_T_AID.setBounds(432, 34, 114, 22);
		pBookAirline.add(pBA_T_AID);
		pBA_T_AID.setColumns(10);
		
		JLabel pBA_L_AID = new JLabel("航班号");
		pBA_L_AID.setBounds(319, 36, 55, 18);
		pBookAirline.add(pBA_L_AID);
		
		JTextField pBA_T_CID = new JTextField();
		pBA_T_CID.setColumns(10);
		pBA_T_CID.setBounds(432, 63, 114, 22);
		pBookAirline.add(pBA_T_CID);
		
		JLabel pBA_L_CID = new JLabel("客户ID");
		pBA_L_CID.setBounds(319, 63, 55, 18);
		pBookAirline.add(pBA_L_CID);
		
		JLabel pBA_L_SEATTYPE = new JLabel("舱位");
		pBA_L_SEATTYPE.setBounds(319, 93, 55, 18);
		pBookAirline.add(pBA_L_SEATTYPE);
		
		JLabel pBA_L_SEATID = new JLabel("座位");
		pBA_L_SEATID.setBounds(319, 120, 55, 18);
		pBookAirline.add(pBA_L_SEATID);
		
		JTextField pBA_T_SEATTYPE = new JTextField();
		pBA_T_SEATTYPE.setColumns(10);
		pBA_T_SEATTYPE.setBounds(432, 91, 114, 22);
		pBookAirline.add(pBA_T_SEATTYPE);
		
		JTextField pBA_T_SEATID = new JTextField();
		pBA_T_SEATID.setColumns(10);
		pBA_T_SEATID.setBounds(432, 120, 114, 22);
		pBookAirline.add(pBA_T_SEATID);
		
		JScrollPane SEATPIC = new JScrollPane();
		SEATPIC.setBounds(625, 15, 286, 252);
		pBookAirline.add(SEATPIC);
		
		JButton pBA_BOOK = new JButton("预订航班");
		pBA_BOOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(pBA_T_AID.getText().trim().length()==0||pBA_T_CID.getText().trim().length()==0||pBA_T_SEATTYPE.getText().trim().length()==0||pBA_T_SEATID.getText().trim().length()==0){
					JOptionPane.showMessageDialog(null,  "请填入完整的订票信息","订票", JOptionPane.CLOSED_OPTION);
					return;
				}
					
				
				String mes="08"+pBA_T_CID.getText().trim()+"-"+pBA_T_AID.getText().trim()+"-"+pBA_T_SEATTYPE.getText().trim()+"-"+pBA_T_SEATID.getText().trim();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				
				
				switch(sig){
				case "0":
					JOptionPane.showMessageDialog(null,  "航班号不存在，请检查后再输入","订票", JOptionPane.CLOSED_OPTION);
					break;
				case "1":
					JOptionPane.showMessageDialog(null,  "用户账号不存在，请检查后再输入","订票", JOptionPane.CLOSED_OPTION);
					break;
				case "2":
					JOptionPane.showMessageDialog(null,  "座位类型错误，请检查后再输入","订票", JOptionPane.CLOSED_OPTION);
					break;
				case "3":
					JOptionPane.showMessageDialog(null,  "座位号错误，请检查后再输入","订票", JOptionPane.CLOSED_OPTION);
					break;
				case "5":
					String orderid=result.substring(1);
					JOptionPane.showMessageDialog(null,  "成功   订单号为 "+orderid,"订票", JOptionPane.CLOSED_OPTION);
					break;
				default:
					JOptionPane.showMessageDialog(null,  "订票失败，请重试","订票", JOptionPane.CLOSED_OPTION);
					break;
				
				
				}
			}
		});
		pBA_BOOK.setBounds(347, 222, 123, 29);
		pBookAirline.add(pBA_BOOK);
		
		
		
		JButton CHECKSEAT = new JButton("<html>查看可<br>选座位<html>");
		CHECKSEAT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//SEARPIC上贴图
				//调方法
			}
		});
		CHECKSEAT.setForeground(SystemColor.desktop);
		CHECKSEAT.setBackground(SystemColor.activeCaption);
		CHECKSEAT.setFont(new Font("宋体", Font.BOLD, 14));
		CHECKSEAT.setBounds(493, 145, 79, 42);
		pBookAirline.add(CHECKSEAT);
		
		
		
		
		/*
		 *删航班
		 *
		 * 
		 */
		
		pCancelAirline = new JPanel();
		pCancelAirline.setOpaque(false);
		pCancelAirline.setBounds(12, 30, 950, 282);
		panel.add(pCancelAirline);
		
		pCancelAirline.setLayout(null);
		
		pCancelAirline.setVisible(false);
		
		JTextField pCA_T_OID = new JTextField();
		pCA_T_OID.setBounds(490, 34, 114, 22);
		pCancelAirline.add(pCA_T_OID);
		pCA_T_OID.setColumns(10);
		
		JLabel pCA_L_OID = new JLabel("订单号");
		pCA_L_OID.setBounds(389, 35, 65, 20);
		pCancelAirline.add(pCA_L_OID);
		
		JButton pCA_CANCEL = new JButton("取消预订");
		pCA_CANCEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pCA_T_OID.getText().length()==0){
					JOptionPane.showMessageDialog(null,  "订单号不能为空","退票", JOptionPane.CLOSED_OPTION);
					return;
				}
					
				
				String mes="09"+pCA_T_OID.getText();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				
				
				switch(sig){
				case "0":
					JOptionPane.showMessageDialog(null,  "订单号不存在，请检查后再输入","退票", JOptionPane.CLOSED_OPTION);
					break;
				case "1":
					JOptionPane.showMessageDialog(null,  "退票成功","退票", JOptionPane.CLOSED_OPTION);
					break;
				
				default:
					JOptionPane.showMessageDialog(null,  "退票失败，请重试","退票", JOptionPane.CLOSED_OPTION);
					break;
				
			}
			}
		});
		pCA_CANCEL.setBounds(421, 101, 123, 29);
		pCancelAirline.add(pCA_CANCEL);
		
		
		
		/*
		 * 更新信息
		*/
		pProfileUpdate = new JPanel();
		pProfileUpdate.setOpaque(false);
		pProfileUpdate.setBounds(12, 30, 950, 282);
		panel.add(pProfileUpdate);
		pProfileUpdate.setLayout(null);
		
		pProfileUpdate.setVisible(false);
		
		JLabel pPU_L_CID = new JLabel("客户ID");
		pPU_L_CID.setBounds(244, 32, 55, 18);
		pProfileUpdate.add(pPU_L_CID);
		
		JTextField pPU_T_CID = new JTextField();
		pPU_T_CID.setText("");
		pPU_T_CID.setColumns(10);
		pPU_T_CID.setBounds(333, 30, 114, 22);
		pProfileUpdate.add(pPU_T_CID);
		
		JTextField pPU_T_NAME = new JTextField();
		pPU_T_NAME.setText("");
		pPU_T_NAME.setBounds(333, 100, 114, 22);
		pProfileUpdate.add(pPU_T_NAME);
		pPU_T_NAME.setColumns(10);
		
		JTextField pPU_T_IDN = new JTextField();
		pPU_T_IDN.setBounds(333, 146, 114, 22);
		pProfileUpdate.add(pPU_T_IDN);
		pPU_T_IDN.setColumns(10);
		
		JTextField pPU_T_PHONE = new JTextField();
		pPU_T_PHONE.setBounds(333, 191, 114, 22);
		pProfileUpdate.add(pPU_T_PHONE);
		pPU_T_PHONE.setColumns(10);
		
		JLabel pPU_L_NAME = new JLabel("新姓名");
		pPU_L_NAME.setBounds(244, 102, 55, 18);
		pProfileUpdate.add(pPU_L_NAME);
		
		JLabel pPU_L_IDN = new JLabel("新身份证号");
		pPU_L_IDN.setBounds(244, 148, 71, 18);
		pProfileUpdate.add(pPU_L_IDN);
		
		JLabel pPU_L_PHONE = new JLabel("新手机号");
		pPU_L_PHONE.setBounds(244, 193, 55, 18);
		pProfileUpdate.add(pPU_L_PHONE);
		
		JButton pPU_UPDATE_NAME = new JButton("确认更改姓名");
		pPU_UPDATE_NAME.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pPU_T_CID.getText().length()==0){
					JOptionPane.showMessageDialog(null,  "请输入客户ID","客户信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				if(pPU_T_NAME.getText().length()==0){
					JOptionPane.showMessageDialog(null, "请输入新姓名","客户信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
					
				
				String mes="12"+pPU_T_CID.getText()+"-"+pPU_T_NAME.getText();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				switch(sig){
				case "2":
					JOptionPane.showMessageDialog(null,  "客户ID不存在，请检查后再输入","客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				case "3":
					JOptionPane.showMessageDialog(null,  "客户信息修改成功","客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				
				default:
					JOptionPane.showMessageDialog(null,  "客户信息修改失败，请重试","客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				
			}
			}
		});
		pPU_UPDATE_NAME.setBounds(492, 97, 147, 28);
		pProfileUpdate.add(pPU_UPDATE_NAME);
		
		JButton pPU_UPDATE_IDN = new JButton("确认更改身份证号");
		pPU_UPDATE_IDN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(pPU_T_CID.getText().length()==0){
					JOptionPane.showMessageDialog(null,  "请输入客户ID","客户信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				if(pPU_T_IDN.getText().length()==0){
					JOptionPane.showMessageDialog(null, "请输入新身份证号","客户信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				if(pPU_T_IDN.getText().length()!=18){
					JOptionPane.showMessageDialog(null, "请输入有效新身份证号","客户信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
					
				
				String mes="10"+pPU_T_CID.getText()+"-"+pPU_T_IDN.getText();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				switch(sig){
				case "2":
					JOptionPane.showMessageDialog(null,  "客户ID不存在，请检查后再输入","客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				case "0":
					JOptionPane.showMessageDialog(null,  "新身份证号已注册，修改客户信息失败，请重试","客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				case "3":
					JOptionPane.showMessageDialog(null,  "客户信息修改成功","客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				
				default:
					JOptionPane.showMessageDialog(null,  "客户信息修改，请重试","客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				
			}
				
			}
		});
		pPU_UPDATE_IDN.setBounds(492, 143, 147, 28);
		pProfileUpdate.add(pPU_UPDATE_IDN);
		
		JButton pPU_UPDATE_PHONE = new JButton("确认更改手机号");
		pPU_UPDATE_PHONE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pPU_T_CID.getText().length()==0){
					JOptionPane.showMessageDialog(null,  "请输入客户ID","客户信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				if(pPU_T_PHONE.getText().length()==0){
					JOptionPane.showMessageDialog(null, "请输入新手机号","客户信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
					
				
				String mes="11"+pPU_T_CID.getText()+"-"+pPU_T_PHONE.getText();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				switch(sig){
				case "2":
					JOptionPane.showMessageDialog(null,  "客户ID不存在，请检查后再输入","客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				case "1":
					JOptionPane.showMessageDialog(null,  "新手机号已注册，修改客户信息失败，请重试","客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				case "3":
					JOptionPane.showMessageDialog(null,  "客户信息修改成功","客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				
				default:
					JOptionPane.showMessageDialog(null,  "客户信息修改失败，请重试","客户信息修改", JOptionPane.CLOSED_OPTION);
					break;
				
			}
			}
		});
		pPU_UPDATE_PHONE.setBounds(492, 188, 147, 28);
		pProfileUpdate.add(pPU_UPDATE_PHONE);
		
		
		
		
		 /*
		 * 打印行程
		 */
		pPrintSchedule = new JPanel();
		pPrintSchedule.setOpaque(false);
		pPrintSchedule.setBounds(12, 30, 950, 282);
		panel.add(pPrintSchedule);
		pPrintSchedule.setLayout(null);
		
		JScrollPane pPrintScheduleOut = new JScrollPane();
		pPrintScheduleOut.setBounds(102, 23, 753, 173);
		pPrintSchedule.add(pPrintScheduleOut);
		
		
		pPrintSchedule.setVisible(false);
		
		JPanel pPrintScheduleIn = new JPanel();
		pPrintScheduleIn.setOpaque(false);
		pPrintScheduleIn.setBounds(102, 23, 609, 209);
		pPrintSchedule.add(pPrintScheduleIn);
		pPrintScheduleIn.setLayout(null);
		
		JLabel pPSI_L_CID = new JLabel("用客户ID查询：");
		pPSI_L_CID.setBounds(26, 30, 160, 21);
		pPrintScheduleIn.add(pPSI_L_CID);
		
		JTextField pPSI_T_CID = new JTextField();
		pPSI_T_CID.setBounds(176, 27, 237, 24);
		pPrintScheduleIn.add(pPSI_T_CID);
		pPSI_T_CID.setColumns(10);
		
		
		
		
		JLabel pPSI_L_IDN = new JLabel("用身份证号查询：");
		pPSI_L_IDN.setBounds(26, 91, 160, 21);
		pPrintScheduleIn.add(pPSI_L_IDN);
		
		JTextField pPSI_T_IDN = new JTextField();
		pPSI_T_IDN.setColumns(10);
		pPSI_T_IDN.setBounds(176, 88, 237, 24);
		pPrintScheduleIn.add(pPSI_T_IDN);
		
		
		
		JLabel pPSI_L_PHONE = new JLabel("用手机号查询：");
		pPSI_L_PHONE.setBounds(26, 152, 160, 21);
		pPrintScheduleIn.add(pPSI_L_PHONE);
		
		JTextField pPSI_T_PHONE = new JTextField();
		pPSI_T_PHONE.setColumns(10);
		pPSI_T_PHONE.setBounds(176, 149, 237, 24);
		pPrintScheduleIn.add(pPSI_T_PHONE);
		
		JButton pPSI_CIDSEARCH = new JButton("查询");
		pPSI_CIDSEARCH.setBounds(428, 26, 123, 29);
		pPrintScheduleIn.add(pPSI_CIDSEARCH);
		pPSI_CIDSEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(pPSI_T_CID.getText().length()==0){
					JOptionPane.showMessageDialog(null,  "请输入客户ID","打印客户行程", JOptionPane.CLOSED_OPTION);
					return;
				}
				
				String mes="13"+pPSI_T_CID.getText();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				switch(sig){
				case "0":
					JOptionPane.showMessageDialog(null,  "客户ID不存在，请检查后再输入","打印客户行程", JOptionPane.CLOSED_OPTION);
					break;
				case "3":
					
					int count=Integer.parseInt(result.substring(1,3));
					String output=result.substring(3);
					String[][] table=MakeMatrix.makeMatrix(output, count, 9);
					
					
					String[][] prow=new String[0][9];

					String[] pcol={"序号","订单号","航班号","日期","时间","座位类型","座位号","业务员","送票员"};

					DefaultTableModel pmodel=new DefaultTableModel(prow,pcol);
					for(int i=0;i<table.length;i++){
						pmodel.addRow(table[i]);
					}
					JTable ptable = new JTable(pmodel);
					pPrintScheduleOut.setViewportView(ptable);
					pPrintScheduleIn.setVisible(false);
					pPrintScheduleOut.setVisible(true);
					break;
				case "4":
					JOptionPane.showMessageDialog(null,  "没有既定行程","打印客户行程", JOptionPane.CLOSED_OPTION);
					break;
				
				default:
					JOptionPane.showMessageDialog(null,  "失败，请重试","打印客户行程", JOptionPane.CLOSED_OPTION);
					break;
				
			}
				
				
			}
		});
		
		JButton pPSI_IDNSEARCH = new JButton("查询");
		pPSI_IDNSEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pPrintScheduleIn.setVisible(false);
				pPrintScheduleOut.setVisible(true);
			}
		});
		pPSI_IDNSEARCH.setBounds(428, 87, 123, 29);
		pPrintScheduleIn.add(pPSI_IDNSEARCH);
		
		JButton pPSI_PHONESEARCH = new JButton("查询");
		pPSI_PHONESEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pPrintScheduleIn.setVisible(false);
				pPrintScheduleOut.setVisible(true);
			}
		});
		pPSI_PHONESEARCH.setBounds(428, 148, 123, 29);
		pPrintScheduleIn.add(pPSI_PHONESEARCH);
		
		
		
		
		
		
		/*
		 * 
		 * 添加客户
		 */
		pAddClient = new JPanel();
		pAddClient.setOpaque(false);
		pAddClient.setBounds(12, 30, 950, 282);
		panel.add(pAddClient);
		pAddClient.setLayout(null);
		
		pAddClient.setVisible(false);
		
		
		JTextField pAC_T_NAME = new JTextField();
		pAC_T_NAME.setText("");
		pAC_T_NAME.setBounds(432, 36, 114, 22);
		pAddClient.add(pAC_T_NAME);
		pAC_T_NAME.setColumns(10);
		
		JTextField pAC_T_IDN = new JTextField();
		pAC_T_IDN.setBounds(432, 82, 114, 22);
		pAddClient.add(pAC_T_IDN);
		pAC_T_IDN.setColumns(10);
		
		JTextField pAC_T_PHONE = new JTextField();
		pAC_T_PHONE.setBounds(432, 127, 114, 22);
		pAddClient.add(pAC_T_PHONE);
		pAC_T_PHONE.setColumns(10);
		
		JLabel pAC_L_NAME = new JLabel("姓名");
		pAC_L_NAME.setBounds(343, 38, 55, 18);
		pAddClient.add(pAC_L_NAME);
		
		JLabel pAC_L_IDN = new JLabel("身份证号");
		pAC_L_IDN.setBounds(343, 84, 55, 18);
		pAddClient.add(pAC_L_IDN);
		
		JLabel pAC_L_PHONE = new JLabel("手机号");
		pAC_L_PHONE.setBounds(343, 129, 55, 18);
		pAddClient.add(pAC_L_PHONE);
		
		JLabel pAC_L_SID = new JLabel("业务员ID");
		pAC_L_SID.setBounds(343, 169, 71, 18);
		pAddClient.add(pAC_L_SID);
		
		JTextField pAC_T_SID = new JTextField();
		pAC_T_SID.setColumns(10);
		pAC_T_SID.setBounds(432, 167, 114, 22);
		pAddClient.add(pAC_T_SID);
		
		JLabel lblNewLabel = new JLabel("（可为空）");
		lblNewLabel.setBounds(564, 169, 71, 18);
		pAddClient.add(lblNewLabel);
		
		JButton pAC_UPDATE = new JButton("添加客户");
		pAC_UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pAC_T_NAME.getText().length()==0){
					JOptionPane.showMessageDialog(null,  "请输入客户姓名","添加客户", JOptionPane.CLOSED_OPTION);
					return;
				}
				if(pAC_T_IDN.getText().length()==0){
					JOptionPane.showMessageDialog(null, "请输入客户 身份证号","添加客户", JOptionPane.CLOSED_OPTION);
					return;
				}
				if(pAC_T_IDN.getText().length()!=18){
					JOptionPane.showMessageDialog(null, "请输入客户有效身份证号","添加客户", JOptionPane.CLOSED_OPTION);
		    		return ;
		    	}
				if(pAC_T_PHONE.getText().length()==0){
					JOptionPane.showMessageDialog(null, "请输入客户手机号","添加客户", JOptionPane.CLOSED_OPTION);
					return;
				}
				
				String mes="16"+pAC_T_IDN.getText().trim()+"-"+pAC_T_PHONE.getText().trim()+"-"+pAC_T_NAME.getText().trim()+"-"+pAC_T_SID.getText().trim();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				switch(sig){
				case "1":
					JOptionPane.showMessageDialog(null,  "该身份证号已注册","添加客户", JOptionPane.CLOSED_OPTION);
					break;
				case "2":
					JOptionPane.showMessageDialog(null,  "该手机号已注册","添加客户", JOptionPane.CLOSED_OPTION);
					break;
				case "3":
					JOptionPane.showMessageDialog(null,  "该业务员不存在","添加客户", JOptionPane.CLOSED_OPTION);
					break;
				case "4":
					String currentcid=result.substring(1);
					JOptionPane.showMessageDialog(null, "成功添加客户  "+currentcid,"添加客户", JOptionPane.CLOSED_OPTION);
					break;
				default:
					JOptionPane.showMessageDialog(null,  "添加客户失败，请重试","添加客户", JOptionPane.CLOSED_OPTION);
					break;
				
			}
			}
		});
		pAC_UPDATE.setBounds(355, 230, 98, 28);
		pAddClient.add(pAC_UPDATE);
		
		
		
		
		
		/*
		 * 搜索客户
		 * 
		 */
		pSearchClient = new JPanel();
		pSearchClient.setOpaque(false);
		pSearchClient.setBounds(12, 30, 950, 282);
		panel.add(pSearchClient);
		pSearchClient.setLayout(null);
		pSearchClient.setVisible(false);
		
		JPanel pSearchClientOut = new JPanel();
		pSearchClientOut.setOpaque(false);
		pSearchClientOut.setVisible(false);
		pSearchClientOut.setBounds(102, 23, 609, 209);
		pSearchClient.add(pSearchClientOut);
		pSearchClientOut.setLayout(null);
		
		JTextArea pSCO_A_RS = new JTextArea();
		pSCO_A_RS.setLineWrap(true);
		pSCO_A_RS.setRows(20);
		pSCO_A_RS.setBounds(88, 38, 475, 116);
		pSearchClientOut.add(pSCO_A_RS);
		
		JPanel pSearchClientIn = new JPanel();
		pSearchClientIn.setOpaque(false);
		pSearchClientIn.setBounds(102, 23, 609, 209);
		pSearchClient.add(pSearchClientIn);
		pSearchClientIn.setLayout(null);
		
		JLabel pSCI_L_CID = new JLabel("用客户ID查询：");
		pSCI_L_CID.setBounds(26, 30, 160, 21);
		pSearchClientIn.add(pSCI_L_CID);
		
		JTextField pSCI_T_CID = new JTextField();
		pSCI_T_CID.setBounds(176, 27, 237, 24);
		pSearchClientIn.add(pSCI_T_CID);
		pSCI_T_CID.setColumns(10);
		
		
		
		
		JLabel pSCI_L_IDN = new JLabel("用身份证号查询：");
		pSCI_L_IDN.setBounds(26, 91, 160, 21);
		pSearchClientIn.add(pSCI_L_IDN);
		
		JTextField pSCI_T_IDN = new JTextField();
		pSCI_T_IDN.setColumns(10);
		pSCI_T_IDN.setBounds(176, 88, 237, 24);
		pSearchClientIn.add(pSCI_T_IDN);
		
		
		
		JLabel pSCI_L_PHONE = new JLabel("用手机号查询：");
		pSCI_L_PHONE.setBounds(26, 152, 160, 21);
		pSearchClientIn.add(pSCI_L_PHONE);
		
		JTextField pSCI_T_PHONE = new JTextField();
		pSCI_T_PHONE.setColumns(10);
		pSCI_T_PHONE.setBounds(176, 149, 237, 24);
		pSearchClientIn.add(pSCI_T_PHONE);
		
		JButton pSCI_CIDSEARCH = new JButton("查询");
		pSCI_CIDSEARCH.setBounds(428, 26, 123, 29);
		pSearchClientIn.add(pSCI_CIDSEARCH);
		pSCI_CIDSEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pSCI_T_CID.getText().length()==0){
					JOptionPane.showMessageDialog(null,  "请输入客户ID","查询客户信息", JOptionPane.CLOSED_OPTION);
					return;
				}
				
				String mes="17"+pSCI_T_CID.getText();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				switch(sig){
				
				case "0":
					String[] output = result.substring(1).split("-");
					pSCO_A_RS.setText("客户ID: "+output[0]+'\n');
					pSCO_A_RS.append("姓名: "+output[3]+'\n');
					pSCO_A_RS.append("身份证号: "+output[1]+'\n');
					pSCO_A_RS.append("手机号: "+output[2]+'\n');
					pSearchClientIn.setVisible(false);
					pSearchClientOut.setVisible(true);
					break;
				case "1":
					JOptionPane.showMessageDialog(null,  "该客户不存在","查询客户信息", JOptionPane.CLOSED_OPTION);
					break;
				default:
					JOptionPane.showMessageDialog(null,  "添加客户失败，请重试","查询客户信息", JOptionPane.CLOSED_OPTION);
					break;
				
			}
				
				
			}
		});
		
		JButton pSCI_IDNSEARCH = new JButton("查询");
		pSCI_IDNSEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pSCI_T_IDN.getText().length()==0){
					JOptionPane.showMessageDialog(null,  "请输入客户身份证号","查询客户信息", JOptionPane.CLOSED_OPTION);
					return;
				}
				if(pSCI_T_IDN.getText().length()!=18){
					JOptionPane.showMessageDialog(null,  "请输入有效身份证号","查询客户信息", JOptionPane.CLOSED_OPTION);
					return;
		    	}
				
				String mes="18"+pSCI_T_IDN.getText();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				switch(sig){
				
				case "0":
					String[] output = result.substring(1).split("-");
					pSCO_A_RS.setText("客户ID: "+output[0]+"\n");
					pSCO_A_RS.append("姓名: "+output[3]+"\n");
					pSCO_A_RS.append("身份证号: "+output[1]+"\n");
					pSCO_A_RS.append("手机号: "+output[2]+"\n");
					pSearchClientIn.setVisible(false);
					pSearchClientOut.setVisible(true);
					break;
				case "1":
					JOptionPane.showMessageDialog(null,  "该客户不存在","查询客户信息", JOptionPane.CLOSED_OPTION);
					break;
				default:
					JOptionPane.showMessageDialog(null,  "添加客户失败，请重试","查询客户信息", JOptionPane.CLOSED_OPTION);
					break;
				
			}
				
			}
		});
		pSCI_IDNSEARCH.setBounds(428, 87, 123, 29);
		pSearchClientIn.add(pSCI_IDNSEARCH);
		
		JButton pSCI_PHONESEARCH = new JButton("查询");
		pSCI_PHONESEARCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pSCI_T_PHONE.getText().length()==0){
					JOptionPane.showMessageDialog(null,  "请输入客户手机号","查询客户信息", JOptionPane.CLOSED_OPTION);
					return;
				}
				
				String mes="19"+pSCI_T_PHONE.getText();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				switch(sig){
				
				case "0":
					String[] output = result.substring(1).split("-");
					pSCO_A_RS.setText("客户ID: "+output[0]+"\n");
					pSCO_A_RS.append("姓名: "+output[3]+"\n");
					pSCO_A_RS.append("身份证号: "+output[1]+"\n");
					pSCO_A_RS.append("手机号: "+output[2]+"\n");
					pSearchClientIn.setVisible(false);
					pSearchClientOut.setVisible(true);
					break;
				case "1":
					JOptionPane.showMessageDialog(null,  "该客户不存在","查询客户信息", JOptionPane.CLOSED_OPTION);
					break;
				default:
					JOptionPane.showMessageDialog(null,  "添加客户失败，请重试","查询客户信息", JOptionPane.CLOSED_OPTION);
					break;
				
			}
			}
		});
		pSCI_PHONESEARCH.setBounds(428, 148, 123, 29);
		pSearchClientIn.add(pSCI_PHONESEARCH);
		
		
		
		
		
		
		
		JButton cp_SEARCH = new JButton("<html>查询<br>航班<html>");
		cp_SEARCH.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				//panel.add(pSearchAirline);
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
		
		JLabel cp_L_SEARCH = new JLabel("查询航班");
		cp_L_SEARCH.setBounds(60, 99, 81, 30);
		contentPane.add(cp_L_SEARCH);
		
		JButton cp_BOOK = new JButton("订票");
		cp_BOOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
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
		
		JLabel cp_L_BOOK = new JLabel("订票");
		cp_L_BOOK.setBounds(188, 99, 45, 30);
		contentPane.add(cp_L_BOOK);
		
		JButton cp_CANCEL = new JButton("退票");
		cp_CANCEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JLabel cp_L_CANCEL = new JLabel("退票");
		cp_L_CANCEL.setBounds(301, 99, 36, 30);
		contentPane.add(cp_L_CANCEL);
		
		JButton cp_UPDATE = new JButton("<html>客户信<br>息修改<html>");
		cp_UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JLabel cp_L_UPDATE = new JLabel("客户信息修改");
		cp_L_UPDATE.setBounds(394, 99, 113, 30);
		contentPane.add(cp_L_UPDATE);
		
		JButton cp_PRINT = new JButton("<html>打印客<br>户行程<html>");
		cp_PRINT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JLabel cp_L_PRINT = new JLabel("打印客户行程");
		cp_L_PRINT.setBounds(507, 99, 113, 30);
		contentPane.add(cp_L_PRINT);
		
		JButton cp_ADDCLIENT = new JButton("<html>添加<br>客户<html>");
		cp_ADDCLIENT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
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
		
		JLabel cp_L_ADDCLIENT = new JLabel("添加客户");
		cp_L_ADDCLIENT.setBounds(635, 100, 81, 29);
		contentPane.add(cp_L_ADDCLIENT);
		
		JButton cp_SEARCHCLIENT = new JButton("<html>查询客<br>户信息<html>");
		cp_SEARCHCLIENT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JLabel cp_L_SEARCHCLIENT = new JLabel("查询客户信息");
		cp_L_SEARCHCLIENT.setBounds(731, 99, 113, 30);
		contentPane.add(cp_L_SEARCHCLIENT);
		
		
		
		
	}
}