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
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import CS.Client;
import Tools.MakeMatrix;

public class ClientFrame extends JFrame {

	private JPanel contentPane;
	private JPanel pSearchAirline;
	private JPanel pBookAirline;
	private JPanel pCancelAirline;
	private JPanel pProfileUpdate;
	private JPanel pPrintSchedule;
	private CS.Client me;
	private String cid;
	

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client here=new Client();
					ClientFrame frame = new ClientFrame(here,"C00000001");
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
	public ClientFrame(Client current,String currentcid) {
		me=current;
		cid=currentcid;
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
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
		pSearchAirline.setBounds(12, 30, 950, 282);
		panel.add(pSearchAirline);
		pSearchAirline.setLayout(null);
		
		pSearchAirline.setVisible(false);	
		
		
		
		JPanel pSearchAirlineIn = new JPanel();
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
		
		JButton pSAI_SEARCH = new JButton("查询");
		
		pSAI_SEARCH.setBounds(68, 142, 123, 29);
		pSearchAirlineIn.add(pSAI_SEARCH);
		
		
		
		JScrollPane pSearchAirlineOut = new JScrollPane();
		pSearchAirlineOut.setBounds(15, 48, 912, 160);
		pSearchAirline.add(pSearchAirlineOut);
		
		String[][] pSearchAirlineRerow=new String[10][10];

		String[] pSearchAirlineRecolumn={"航班号","起点","终点","日期","时间","头等舱可选","商务舱可选","经济舱可选","机型"};

		DefaultTableModel pSearchAirlineRe=new DefaultTableModel(pSearchAirlineRerow,pSearchAirlineRecolumn);

		JTable pSearchAirlineResultTable = new JTable(pSearchAirlineRe);

		pSearchAirlineOut.setViewportView(pSearchAirlineResultTable);

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

					String[] pcol2={"序号","航班号","起点","终点","日期","时间","头等舱剩余座位","商务舱剩余座位","经济舱剩余座位","机型"};
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
		
		JLabel pBA_L_SEATTYPE = new JLabel("舱位");
		pBA_L_SEATTYPE.setBounds(319, 65, 55, 18);
		pBookAirline.add(pBA_L_SEATTYPE);
		
		JLabel pBA_L_SEATID = new JLabel("座位");
		pBA_L_SEATID.setBounds(319, 92, 55, 18);
		pBookAirline.add(pBA_L_SEATID);
		
		JTextField pBA_T_SEATTYPE = new JTextField();
		pBA_T_SEATTYPE.setColumns(10);
		pBA_T_SEATTYPE.setBounds(432, 63, 114, 22);
		pBookAirline.add(pBA_T_SEATTYPE);
		
		JTextField pBA_T_SEATID = new JTextField();
		pBA_T_SEATID.setColumns(10);
		pBA_T_SEATID.setBounds(432, 92, 114, 22);
		pBookAirline.add(pBA_T_SEATID);
		
		JScrollPane SEATPIC = new JScrollPane();
		SEATPIC.setBounds(625, 15, 286, 252);
		pBookAirline.add(SEATPIC);
		
		JButton pBA_BOOK = new JButton("预订航班");
		pBA_BOOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(pBA_T_AID.getText().trim().length()==0||pBA_T_SEATTYPE.getText().trim().length()==0||pBA_T_SEATID.getText().trim().length()==0){
					JOptionPane.showMessageDialog(null,  "请填入完整的订票信息","订票", JOptionPane.CLOSED_OPTION);
					return;
				}
					
				
				String mes="08"+cid+"-"+pBA_T_AID.getText().trim()+"-"+pBA_T_SEATTYPE.getText().trim()+"-"+pBA_T_SEATID.getText().trim();
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
		CHECKSEAT.setBounds(466, 126, 79, 42);
		pBookAirline.add(CHECKSEAT);
		
		
		
		
		
		/*
		 *删航班
		 *
		 * */
		pCancelAirline = new JPanel();
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
		pProfileUpdate.setBounds(12, 30, 950, 282);
		panel.add(pProfileUpdate);
		pProfileUpdate.setLayout(null);
		
		pProfileUpdate.setVisible(false);
		
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
				
				if(pPU_T_NAME.getText().length()==0){
					JOptionPane.showMessageDialog(null, "请输入新姓名","个人信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
					
				
				String mes="12"+cid+"-"+pPU_T_NAME.getText();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				switch(sig){
				case "2":
					JOptionPane.showMessageDialog(null,  "客户ID不存在，请检查后再输入","个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				case "3":
					JOptionPane.showMessageDialog(null,  "个人信息修改成功","个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				
				default:
					JOptionPane.showMessageDialog(null,  "个人信息修改失败，请重试","个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				
			}
			}
		});
		pPU_UPDATE_NAME.setBounds(492, 97, 147, 28);
		pProfileUpdate.add(pPU_UPDATE_NAME);
		
		JButton pPU_UPDATE_IDN = new JButton("确认更改身份证号");
		pPU_UPDATE_IDN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(pPU_T_IDN.getText().length()==0){
					JOptionPane.showMessageDialog(null, "请输入新身份证号","个人信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
				if(pPU_T_IDN.getText().length()!=18){
					JOptionPane.showMessageDialog(null, "请输入有效新身份证号","个人信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
					
				
				String mes="10"+cid+"-"+pPU_T_IDN.getText();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				switch(sig){
				case "2":
					JOptionPane.showMessageDialog(null,  "客户ID不存在，请检查后再输入","个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				case "0":
					JOptionPane.showMessageDialog(null,  "新身份证号已注册，修改个人信息失败，请重试","个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				case "3":
					JOptionPane.showMessageDialog(null,  "个人信息修改成功","个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				
				default:
					JOptionPane.showMessageDialog(null,  "个人信息修改，请重试","个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				
			}
				
			}
		});
		pPU_UPDATE_IDN.setBounds(492, 143, 147, 28);
		pProfileUpdate.add(pPU_UPDATE_IDN);
		
		JButton pPU_UPDATE_PHONE = new JButton("确认更改手机号");
		pPU_UPDATE_PHONE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(pPU_T_PHONE.getText().length()==0){
					JOptionPane.showMessageDialog(null, "请输入新手机号","个人信息修改", JOptionPane.CLOSED_OPTION);
					return;
				}
					
				
				String mes="11"+cid+"-"+pPU_T_PHONE.getText();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				switch(sig){
				case "2":
					JOptionPane.showMessageDialog(null,  "客户ID不存在，请检查后再输入","个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				case "1":
					JOptionPane.showMessageDialog(null,  "新手机号已注册，修改个人信息失败，请重试","个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				case "3":
					JOptionPane.showMessageDialog(null,  "个人信息修改成功","个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				
				default:
					JOptionPane.showMessageDialog(null,  "个人信息修改失败，请重试","个人信息修改", JOptionPane.CLOSED_OPTION);
					break;
				
			}
			}
		});
		pPU_UPDATE_PHONE.setBounds(492, 188, 147, 28);
		pProfileUpdate.add(pPU_UPDATE_PHONE);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		JButton cp_SEARCH = new JButton("查询航班");
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
			}
		});
		
		
		pPrintSchedule = new JPanel();
		pPrintSchedule.setBounds(12, 30, 950, 282);
		panel.add(pPrintSchedule);
		pPrintSchedule.setLayout(null);
		pPrintSchedule.setVisible(false);
		
		JScrollPane pPrintScheduleOut = new JScrollPane();
		pPrintScheduleOut.setBounds(102, 23, 753, 173);
		pPrintScheduleOut.setVisible(false);
		pPrintSchedule.add(pPrintScheduleOut);
		
		
		
		
		cp_SEARCH.setBounds(112, 12, 141, 87);
		contentPane.add(cp_SEARCH);
		
		JLabel cp_L_SEARCH = new JLabel("查询航班");
		cp_L_SEARCH.setBounds(153, 99, 122, 30);
		contentPane.add(cp_L_SEARCH);
		
		JButton cp_BOOK = new JButton("New button");
		cp_BOOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//panel.add(pBookAirline);
				pBookAirline.setVisible(true);
				pSearchAirline.setVisible(false);				
				pCancelAirline.setVisible(false);
				pProfileUpdate.setVisible(false);
				pPrintSchedule.setVisible(false);
				
			}
		});
		
		cp_BOOK.setBounds(268, 12, 141, 87);
		contentPane.add(cp_BOOK);
		
		JLabel cp_L_BOOK = new JLabel("订票");
		cp_L_BOOK.setBounds(319, 99, 122, 30);
		contentPane.add(cp_L_BOOK);
		
		JButton cp_CANCEL = new JButton("New button");
		cp_CANCEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//panel.add(pCancelAirline);	
				pCancelAirline.setVisible(true);
				pSearchAirline.setVisible(false);	
				pBookAirline.setVisible(false);
				pProfileUpdate.setVisible(false);
				pPrintSchedule.setVisible(false);
			}
			
		});
		cp_CANCEL.setBounds(424, 12, 141, 87);
		contentPane.add(cp_CANCEL);
		
		JLabel cp_L_CANCEL = new JLabel("退票");
		cp_L_CANCEL.setBounds(474, 99, 122, 30);
		contentPane.add(cp_L_CANCEL);
		
		JButton cp_UPDATE = new JButton("New button");
		cp_UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//panel.add(pProfileUpdate);
				pProfileUpdate.setVisible(true);
				pSearchAirline.setVisible(false);
				pBookAirline.setVisible(false);
				pCancelAirline.setVisible(false);
				pPrintSchedule.setVisible(false);
			}
		});
		cp_UPDATE.setBounds(579, 12, 141, 87);
		contentPane.add(cp_UPDATE);
		
		JLabel cp_L_UPDATE = new JLabel("个人信息修改");
		cp_L_UPDATE.setBounds(608, 99, 122, 30);
		contentPane.add(cp_L_UPDATE);
		
		JButton cp_PRINT = new JButton("New button");
		cp_PRINT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//panel.add(pPrintSchedule);
				
				
				
				pPrintSchedule.setVisible(false);
				
				
				String mes="13"+cid;
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				
				String sig=result.substring(0, 1);
				System.out.println("sig is:+++"+sig+"++++");
				switch(sig){
				case "0":
					JOptionPane.showMessageDialog(null,  "客户ID不存在，请检查后再输入","打印个人行程", JOptionPane.CLOSED_OPTION);
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
					pPrintScheduleOut.setVisible(true);
					break;
				case "4":
					JOptionPane.showMessageDialog(null,  "没有既定行程","打印个人行程", JOptionPane.CLOSED_OPTION);
					break;
				
				default:
					JOptionPane.showMessageDialog(null,  "失败，请重试","打印个人行程", JOptionPane.CLOSED_OPTION);
					break;
				
			}
				pPrintSchedule.setVisible(true);
				pSearchAirline.setVisible(false);
				pBookAirline.setVisible(false);
				pCancelAirline.setVisible(false);
				pProfileUpdate.setVisible(false);
				
			}
		});
		cp_PRINT.setBounds(735, 12, 141, 87);
		contentPane.add(cp_PRINT);
		
		JLabel cp_L_PRINT = new JLabel("打印个人行程");
		cp_L_PRINT.setBounds(770, 99, 122, 30);
		contentPane.add(cp_L_PRINT);
		
		
		
		
	}
}
