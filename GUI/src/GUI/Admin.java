package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JSplitPane;
import java.awt.Font;

import CS.Client;
import Tools.MakeMatrix;

public class Admin extends JFrame {

	

		private JPanel contentPane;
		
		private JPanel pAirlineManagement;
		private JPanel pAM_Display;
		private JPanel pAddAirlineIn;
		private JPanel pCancelAirlineIn;
		private JPanel pSearchAirlineIn;
		private JScrollPane pSearchAirlineOut;
		
		private JPanel pHRManagement;
		private JPanel pHRM_Display;
		private JPanel pd_SALESMAN;
		private JPanel pd_DELIVERYMAN;
		private JPanel pd_OPERATOR;
		
		private JPanel pTicketBoxManagement;
		private JPanel pTBM_Display;
		private JPanel pAddTBIn;
		private JPanel pCancelTBIn;
		
		private JPanel pStatistics;
		private JPanel pStatisticsIn;
		private JPanel pStatisticsOut;
		private JScrollPane pStatisticsTable;
		
		
		private JPanel pAddClient;
		private JPanel pSearchClient;
		private JTextField pSI_T_SID;
		
		private Client me;
		private JTextField pSalesman_T_ADD_TID;
		private JTextField pDeliveryman_T_ADD_TID;
		
		
		
		

		/**
		 * Launch the application.
		 */
		/*
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Admin frame = new Admin();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
*/
		/**
		 * Create the frame.
		 */
		public Admin(Client current) {
			me=current;
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
			 * 航线管理
			 * 
			 * 
			 
			
			pAirlineManagement = new JPanel();
			pAirlineManagement.setBounds(12, 30, 950, 282);
			panel.add(pAirlineManagement);
			pAirlineManagement.setLayout(null);
			
			JButton pAM_SELECT = new JButton("<html>查询<br>航班<html>");
			pAM_SELECT.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					pAddAirlineIn.setVisible(false);
					pCancelAirlineIn.setVisible(false);
					pSearchAirlineIn.setVisible(true);
				}
			});
			pAM_SELECT.setBounds(15, 15, 70, 67);
			pAirlineManagement.add(pAM_SELECT);
			
			JButton pAM_ADD = new JButton("<html>添加<br>航班<html>");
			pAM_ADD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pAddAirlineIn.setVisible(true);
					pCancelAirlineIn.setVisible(false);
					pSearchAirlineIn.setVisible(false);
				}
			});
			pAM_ADD.setBounds(15, 91, 70, 67);
			pAirlineManagement.add(pAM_ADD);
			
			JButton pAM_CANCEL = new JButton("<html>取消<br>航班<html>");
			pAM_CANCEL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pAddAirlineIn.setVisible(false);
					pCancelAirlineIn.setVisible(true);
					pSearchAirlineIn.setVisible(false);
				}
			});
			pAM_CANCEL.setBounds(15, 169, 70, 67);
			pAirlineManagement.add(pAM_CANCEL);
			
			JPanel pAM_Display = new JPanel();
			pAM_Display.setBounds(119, 15, 797, 220);
			pAirlineManagement.add(pAM_Display);
			
			pAirlineManagement.setVisible(false);	
			pAM_Display.setLayout(null);
			
			
			
			pAddAirlineIn = new JPanel();
			pAddAirlineIn.setBounds(28, 15, 754, 190);
			pAM_Display.add(pAddAirlineIn);
			pAddAirlineIn.setLayout(null);
			pAddAirlineIn.setVisible(false);
			
			JLabel pAdd_L_AID = new JLabel("航班号");
			pAdd_L_AID.setBounds(15, 15, 81, 21);
			pAddAirlineIn.add(pAdd_L_AID);
			
			JTextField pAdd_T_AID = new JTextField();
			pAdd_T_AID.setBounds(15, 40, 81, 27);
			pAddAirlineIn.add(pAdd_T_AID);
			pAdd_T_AID.setColumns(10);
			
			JLabel pAdd_L_ORIGIN = new JLabel("起点");
			pAdd_L_ORIGIN.setBounds(103, 15, 81, 21);
			pAddAirlineIn.add(pAdd_L_ORIGIN);
			
			JTextField pAdd_T_ORIGIN = new JTextField();
			pAdd_T_ORIGIN.setColumns(10);
			pAdd_T_ORIGIN.setBounds(103, 40, 81, 27);
			pAddAirlineIn.add(pAdd_T_ORIGIN);
			
			JLabel pAdd_L_DEST = new JLabel("终点");
			pAdd_L_DEST.setBounds(190, 15, 81, 21);
			pAddAirlineIn.add(pAdd_L_DEST);
			
			JTextField pAdd_T_DEST = new JTextField();
			pAdd_T_DEST.setColumns(10);
			pAdd_T_DEST.setBounds(190, 40, 81, 27);
			pAddAirlineIn.add(pAdd_T_DEST);
			
			JLabel pAdd_L_DATE = new JLabel("日期");
			pAdd_L_DATE.setBounds(278, 15, 81, 21);
			pAddAirlineIn.add(pAdd_L_DATE);
			
			JTextField pAdd_T_DATE = new JTextField();
			pAdd_T_DATE.setColumns(10);
			pAdd_T_DATE.setBounds(278, 40, 81, 27);
			pAddAirlineIn.add(pAdd_T_DATE);
			
			JLabel pAdd_L_TIME = new JLabel("时间");
			pAdd_L_TIME.setBounds(362, 15, 81, 21);
			pAddAirlineIn.add(pAdd_L_TIME);
			
			JTextField pAdd_T_TIME = new JTextField();
			pAdd_T_TIME.setColumns(10);
			pAdd_T_TIME.setBounds(362, 40, 81, 27);
			pAddAirlineIn.add(pAdd_T_TIME);
			
			JLabel pAdd_L_TYPE = new JLabel("机型");
			pAdd_L_TYPE.setBounds(450, 15, 81, 21);
			pAddAirlineIn.add(pAdd_L_TYPE);
			
			JTextField pAdd_T_TYPE = new JTextField();
			pAdd_T_TYPE.setColumns(10);
			pAdd_T_TYPE.setBounds(450, 40, 81, 27);
			pAddAirlineIn.add(pAdd_T_TYPE);
			
			JButton pAdd_ADD = new JButton("添加航班");
			pAdd_ADD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(pAdd_T_AID.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "请输入航班号","添加航班", JOptionPane.CLOSED_OPTION);
						return;
					}
					if(pAdd_T_ORIGIN.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "请输入起点","添加航班", JOptionPane.CLOSED_OPTION);
						return;
					}
					if(pAdd_T_DEST.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "请输入终点","添加航班", JOptionPane.CLOSED_OPTION);
						return;
					}
					
					if(pAdd_T_DATE.getText().trim().length()!=8){
						JOptionPane.showMessageDialog(null,  "请输入有效日期","添加航班", JOptionPane.CLOSED_OPTION);
						return;
					}
					if(pAdd_T_TIME.getText().trim().length()!=4){
						JOptionPane.showMessageDialog(null,  "请输入有效时间","添加航班", JOptionPane.CLOSED_OPTION);
						return;
					}
					if(pAdd_T_TYPE.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "请输入机型","添加航班", JOptionPane.CLOSED_OPTION);
						return;
					}
					
					
					String mes="22"+pAdd_T_AID.getText().trim()+"-"+pAdd_T_ORIGIN.getText().trim()+"-"+pAdd_T_DEST.getText().trim()+"-"+pAdd_T_DATE.getText().trim()+"-"+pAdd_T_TIME.getText().trim().substring(0, 2)+"-"+pAdd_T_TYPE.getText().trim();
					me.sendMessage(mes);
					String result=me.receiveMessage();
					System.out.println("i am client i receive++++"+result+"++++");
					
					String sig=result.substring(0, 1);
					System.out.println("sig is:+++"+sig+"++++");
					switch(sig){
					
					case "0":
						JOptionPane.showMessageDialog(null,  "机型不存在，请检查后再输入","添加航班", JOptionPane.CLOSED_OPTION);
						break;
					case "1":
						JOptionPane.showMessageDialog(null,  "成功添加航班 "+pAdd_T_AID,"添加航班", JOptionPane.CLOSED_OPTION);
						break;
					default:
						JOptionPane.showMessageDialog(null,  "添加航班失败，请重试","添加航班", JOptionPane.CLOSED_OPTION);
						break;
					
				}
				}
			});
			pAdd_ADD.setBounds(52, 120, 123, 29);
			pAddAirlineIn.add(pAdd_ADD);
			
			pCancelAirlineIn = new JPanel();
			pCancelAirlineIn.setBounds(28, 15, 754, 190);
			pAM_Display.add(pCancelAirlineIn);
			pCancelAirlineIn.setLayout(null);
			pCancelAirlineIn.setVisible(false);
			
			JLabel pCANCEL_L_AID = new JLabel("航班号");
			pCANCEL_L_AID.setBounds(61, 34, 61, 21);
			pCancelAirlineIn.add(pCANCEL_L_AID);
			
			JTextField pCANCEL_T_AID = new JTextField();
			pCANCEL_T_AID.setBounds(137, 31, 96, 27);
			pCancelAirlineIn.add(pCANCEL_T_AID);
			pCANCEL_T_AID.setColumns(10);
			
			JButton pCANCEL_CANCEL = new JButton("New button");
			pCANCEL_CANCEL.setBounds(72, 93, 123, 29);
			pCancelAirlineIn.add(pCANCEL_CANCEL);
			pCANCEL_CANCEL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(pCANCEL_CANCEL.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "航班号不能为空","取消航班", JOptionPane.CLOSED_OPTION);
						return;
					}
						
					
					String mes="23"+pCANCEL_T_AID.getText().trim();
					me.sendMessage(mes);
					String result=me.receiveMessage();
					System.out.println("i am client i receive++++"+result+"++++");
					
					String sig=result.substring(0, 1);
					System.out.println("sig is:+++"+sig+"++++");
					
					
					switch(sig){
					case "0":
						JOptionPane.showMessageDialog(null,  "航班号不存在，请检查后再输入","取消航班", JOptionPane.CLOSED_OPTION);
						break;
					case "1":
						JOptionPane.showMessageDialog(null,  "取消航班成功","取消航班", JOptionPane.CLOSED_OPTION);
						break;
					
					default:
						JOptionPane.showMessageDialog(null,  "取消航班失败，请重试","取消航班", JOptionPane.CLOSED_OPTION);
						break;
					
				}
				}
			});
			
			
			
			
			
			
			
			
			
			
			pSearchAirlineIn = new JPanel();
			pSearchAirlineIn.setBounds(28, 15, 754, 190);
			pAM_Display.add(pSearchAirlineIn);
			pSearchAirlineIn.setLayout(null);
			pSearchAirlineIn.setVisible(false);
			
			JLabel pSearch_L_DATE = new JLabel("日期");
			pSearch_L_DATE.setBounds(43, 8, 81, 21);
			pSearchAirlineIn.add(pSearch_L_DATE);
			
			JTextField pSearch_T_DATE = new JTextField();
			pSearch_T_DATE.setBounds(144, 5, 96, 27);
			pSearch_T_DATE.setColumns(10);
			pSearchAirlineIn.add(pSearch_T_DATE);
			
			JLabel pSearch_L_ORIGIN = new JLabel("起点");
			pSearch_L_ORIGIN.setBounds(43, 50, 81, 21);
			pSearchAirlineIn.add(pSearch_L_ORIGIN);
			
			JTextField pSearch_T_ORIGIN = new JTextField();
			pSearch_T_ORIGIN.setBounds(144, 47, 96, 27);
			pSearch_T_ORIGIN.setColumns(10);
			pSearchAirlineIn.add(pSearch_T_ORIGIN);
			
			JLabel pSearch_L_DEST = new JLabel("终点");
			pSearch_L_DEST.setBounds(43, 92, 81, 21);
			pSearchAirlineIn.add(pSearch_L_DEST);
			
			JTextField pSearch_T_DEST = new JTextField();
			pSearch_T_DEST.setBounds(144, 89, 96, 27);
			pSearch_T_DEST.setColumns(10);
			pSearchAirlineIn.add(pSearch_T_DEST);
			
			JButton pSearch_SEARCH = new JButton("查询");
			
			pSearch_SEARCH.setBounds(68, 142, 123, 29);
			pSearchAirlineIn.add(pSearch_SEARCH);
			
			
			
			pSearchAirlineOut = new JScrollPane();
			pSearchAirlineOut.setBounds(15, 48, 912, 160);
			pAM_Display.add(pSearchAirlineOut);
			pSearchAirlineOut.setVisible(false);
			
			String[][] pSearchAirlineRerow=new String[10][9];

			String[] pSearchAirlineRecolumn={"航班号","起点","终点","日期","时间","头等舱剩余座位数","商务舱剩余座位数","经济舱剩余座位数","机型"};

			DefaultTableModel pSearchAirlineRe=new DefaultTableModel(pSearchAirlineRerow,pSearchAirlineRecolumn);

			JTable pSearchAirlineResultTable = new JTable(pSearchAirlineRe);

			pSearchAirlineOut.setViewportView(pSearchAirlineResultTable);

			pSearch_SEARCH.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(pSearch_T_DATE.getText().trim().length()!=8){
						JOptionPane.showMessageDialog(null,  "请输入有效日期","查询航班", JOptionPane.CLOSED_OPTION);
						return;
					}
					
					if(pSearch_T_ORIGIN.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "起点不能为空","查询航班", JOptionPane.CLOSED_OPTION);
						return;
					}
					
					if(pSearch_T_DEST.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "终点不能为空","查询航班", JOptionPane.CLOSED_OPTION);
						return;
					}
						
					String mes="07"+pSearch_T_DATE.getText().trim()+"-"+pSearch_T_ORIGIN.getText().trim()+"-"+pSearch_T_DEST.getText().trim();
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
			*/
			 
			/*
			 * 人事管理
			 * 
			 *
			*/
			pHRManagement = new JPanel();
			pHRManagement.setBounds(12, 30, 950, 282);
			panel.add(pHRManagement);
			pHRManagement.setLayout(null);
			pHRManagement.setVisible(false);
			
			JButton pHRM_SALESMAN = new JButton("<html>业务员<br>管理<html>");
			pHRM_SALESMAN.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pd_SALESMAN.setVisible(true);
					pd_DELIVERYMAN.setVisible(false);
					pd_OPERATOR.setVisible(false);
				}
			});
			pHRM_SALESMAN.setHorizontalAlignment(SwingConstants.TRAILING);
			pHRM_SALESMAN.setFont(new Font("宋体", Font.PLAIN, 17));
			pHRM_SALESMAN.setBounds(15, 15, 84, 72);
			pHRManagement.add(pHRM_SALESMAN);
			
			JButton pHRM_DELIVERYMAN = new JButton("<html>送票员<br>管理<html>");
			pHRM_DELIVERYMAN.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pd_SALESMAN.setVisible(false);
					pd_DELIVERYMAN.setVisible(true);
					pd_OPERATOR.setVisible(false);
				}
			});
			pHRM_DELIVERYMAN.setFont(new Font("宋体", Font.PLAIN, 17));
			pHRM_DELIVERYMAN.setBounds(15, 102, 84, 72);
			pHRManagement.add(pHRM_DELIVERYMAN);
			
			pHRM_Display = new JPanel();
			pHRM_Display.setBounds(119, 15, 797, 220);
			pHRManagement.add(pHRM_Display);
			pHRM_Display.setLayout(null);
			
			pd_SALESMAN = new JPanel();
			pd_SALESMAN.setBounds(0, 0, 797, 220);
			//pHRM_Display.add(pd_SALESMAN);
			pd_SALESMAN.setLayout(null);
			pd_SALESMAN.setVisible(false);
			
			JLabel pSalesman_L_ADD_NAME = new JLabel("姓名");
			pSalesman_L_ADD_NAME.setBounds(28, 26, 81, 21);
			pd_SALESMAN.add(pSalesman_L_ADD_NAME);
			
			JTextField pSalesman_T_ADD_NAME = new JTextField();
			pSalesman_T_ADD_NAME.setBounds(123, 23, 96, 27);
			pd_SALESMAN.add(pSalesman_T_ADD_NAME);
			pSalesman_T_ADD_NAME.setColumns(10);
			
			JLabel pSalesman_L_ADD_TID = new JLabel("售票点ID");
			pSalesman_L_ADD_TID.setBounds(27, 65, 81, 21);
			pd_SALESMAN.add(pSalesman_L_ADD_TID);
			
			pSalesman_T_ADD_TID = new JTextField();
			pSalesman_T_ADD_TID.setColumns(10);
			pSalesman_T_ADD_TID.setBounds(123, 62, 96, 27);
			pd_SALESMAN.add(pSalesman_T_ADD_TID);
			
			JButton pSalesman_ADD = new JButton("确认添加");
			pSalesman_ADD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(pSalesman_T_ADD_NAME.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "请输入业务员姓名","添加业务员", JOptionPane.CLOSED_OPTION);
						return;
					}
					if(pSalesman_T_ADD_TID.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "请输入业务员所属售票点ID","添加业务员", JOptionPane.CLOSED_OPTION);
						return;
					}
					
					String mes="24"+pSalesman_T_ADD_NAME.getText().trim()+"-"+pSalesman_T_ADD_TID.getText().trim();
					me.sendMessage(mes);
					String result=me.receiveMessage();
					System.out.println("i am client i receive++++"+result+"++++");
					
					String sig=result.substring(0, 1);
					System.out.println("sig is:+++"+sig+"++++");
					switch(sig){
					
					case "3":
						JOptionPane.showMessageDialog(null,  "售票点ID不存在，请检查后输入","添加业务员", JOptionPane.CLOSED_OPTION);
						break;
					case "1":
						JOptionPane.showMessageDialog(null,  "添加业务员成功","添加业务员", JOptionPane.CLOSED_OPTION);
						break;
					default:
						JOptionPane.showMessageDialog(null,  "添加业务员失败，请重试","添加业务员", JOptionPane.CLOSED_OPTION);
						break;
					
				}
				}
			});
			pSalesman_ADD.setBounds(56, 160, 123, 29);
			pd_SALESMAN.add(pSalesman_ADD);
			
			JLabel pSalesman_L_CANCEL_SID = new JLabel("业务员ID");
			pSalesman_L_CANCEL_SID.setBounds(511, 26, 81, 21);
			pd_SALESMAN.add(pSalesman_L_CANCEL_SID);
			
			JTextField pSalesman_T_CANCEL_SID = new JTextField();
			pSalesman_T_CANCEL_SID.setColumns(10);
			pSalesman_T_CANCEL_SID.setBounds(607, 23, 96, 27);
			pd_SALESMAN.add(pSalesman_T_CANCEL_SID);
			
			
			
			JButton pSalesman_CANCEL = new JButton("确认删除");
			pSalesman_CANCEL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(pSalesman_T_CANCEL_SID.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "请输入业务员ID","删除业务员", JOptionPane.CLOSED_OPTION);
						return;
					}
					
					
					
					String mes="25"+pSalesman_T_CANCEL_SID.getText().trim();
					me.sendMessage(mes);
					String result=me.receiveMessage();
					System.out.println("i am client i receive++++"+result+"++++");
					
					String sig=result.substring(0, 1);
					System.out.println("sig is:+++"+sig+"++++");
					switch(sig){
					case "2":
						JOptionPane.showMessageDialog(null,  "业务员ID不存在，请检查后再输入","删除业务员", JOptionPane.CLOSED_OPTION);
						break;
					
					case "1":
						JOptionPane.showMessageDialog(null,  "删除业务员成功","删除业务员", JOptionPane.CLOSED_OPTION);
						break;
					default:
						JOptionPane.showMessageDialog(null,  "删除业务员失败，请重试","删除业务员", JOptionPane.CLOSED_OPTION);
						break;
					
				}
				}
			});
			pSalesman_CANCEL.setBounds(540, 160, 123, 29);
			pd_SALESMAN.add(pSalesman_CANCEL);
			
			
			
			
			
			pd_DELIVERYMAN = new JPanel();
			pd_DELIVERYMAN.setBounds(0, 0, 797, 220);
			pHRM_Display.add(pd_DELIVERYMAN);
			pd_DELIVERYMAN.setLayout(null);
			pd_DELIVERYMAN.setVisible(false);
			
			JLabel pDeliveryman_L_ADD_NAME = new JLabel("姓名");
			pDeliveryman_L_ADD_NAME.setBounds(28, 26, 81, 21);
			pd_DELIVERYMAN.add(pDeliveryman_L_ADD_NAME);
			
			JTextField pDeliveryman_T_ADD_NAME = new JTextField();
			pDeliveryman_T_ADD_NAME.setBounds(123, 23, 96, 27);
			pd_DELIVERYMAN.add(pDeliveryman_T_ADD_NAME);
			pDeliveryman_T_ADD_NAME.setColumns(10);
			
			JLabel pDeliveryman_L_ADD_TID = new JLabel("售票点ID");
			pDeliveryman_L_ADD_TID.setBounds(28, 65, 81, 21);
			pd_DELIVERYMAN.add(pDeliveryman_L_ADD_TID);
			
			pDeliveryman_T_ADD_TID = new JTextField();
			pDeliveryman_T_ADD_TID.setColumns(10);
			pDeliveryman_T_ADD_TID.setBounds(123, 62, 96, 27);
			pd_DELIVERYMAN.add(pDeliveryman_T_ADD_TID);
			
			JButton pDeliveryman_ADD = new JButton("确认添加");
			pDeliveryman_ADD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(pDeliveryman_T_ADD_NAME.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "请输入送票员姓名","添加送票员", JOptionPane.CLOSED_OPTION);
						return;
					}
					if(pDeliveryman_T_ADD_TID.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "请输入送票员所属售票点ID","添加送票员", JOptionPane.CLOSED_OPTION);
						return;
					}
					
					String mes="26"+pDeliveryman_T_ADD_NAME.getText().trim()+"-"+pDeliveryman_T_ADD_TID.getText().trim();
					me.sendMessage(mes);
					String result=me.receiveMessage();
					System.out.println("i am client i receive++++"+result+"++++");
					
					String sig=result.substring(0, 1);
					System.out.println("sig is:+++"+sig+"++++");
					switch(sig){
					case "2":
						JOptionPane.showMessageDialog(null,  "售票点ID不存在，请检查后再输入","添加送票员", JOptionPane.CLOSED_OPTION);
						break;
					
					case "1":
						JOptionPane.showMessageDialog(null,  "添加送票员成功","添加送票员", JOptionPane.CLOSED_OPTION);
						break;
					default:
						JOptionPane.showMessageDialog(null,  "添加送票员失败，请重试","添加送票员", JOptionPane.CLOSED_OPTION);
						break;
					
				}
				}
			});
			pDeliveryman_ADD.setBounds(56, 160, 123, 29);
			pd_DELIVERYMAN.add(pDeliveryman_ADD);
			
			JLabel pDeliveryman_L_CANCEL_SID = new JLabel("送票员ID");
			pDeliveryman_L_CANCEL_SID.setBounds(511, 26, 81, 21);
			pd_DELIVERYMAN.add(pDeliveryman_L_CANCEL_SID);
			
			JTextField pDeliveryman_T_CANCEL_SID = new JTextField();
			pDeliveryman_T_CANCEL_SID.setColumns(10);
			pDeliveryman_T_CANCEL_SID.setBounds(607, 23, 96, 27);
			pd_DELIVERYMAN.add(pDeliveryman_T_CANCEL_SID);
			
			
			
			JButton pDeliveryman_CANCEL = new JButton("确认删除");
			pDeliveryman_CANCEL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(pDeliveryman_T_CANCEL_SID.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "请输入送票员ID","删除送票员", JOptionPane.CLOSED_OPTION);
						return;
					}
					
					String mes="27"+pDeliveryman_T_CANCEL_SID.getText().trim();
					me.sendMessage(mes);
					String result=me.receiveMessage();
					System.out.println("i am client i receive++++"+result+"++++");
					
					String sig=result.substring(0, 1);
					System.out.println("sig is:+++"+sig+"++++");
					switch(sig){
					case "2":
						JOptionPane.showMessageDialog(null,  "送票员ID不存在，请检查后再输入","删除送票员", JOptionPane.CLOSED_OPTION);
						break;
					
					case "1":
						JOptionPane.showMessageDialog(null,  "删除送票员成功","删除送票员", JOptionPane.CLOSED_OPTION);
						break;
					default:
						JOptionPane.showMessageDialog(null,  "删除送票员失败，请重试","删除送票员", JOptionPane.CLOSED_OPTION);
						break;
					
				}
				}
			});
			pDeliveryman_CANCEL.setBounds(540, 160, 123, 29);
			pd_DELIVERYMAN.add(pDeliveryman_CANCEL);
			
			
			
			JButton pHRM_OPERATOR = new JButton("<html>操作员<br>管理<html>");
			pHRM_OPERATOR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pd_SALESMAN.setVisible(false);
					pd_DELIVERYMAN.setVisible(false);
					pd_OPERATOR.setVisible(true);
				}
			});
			pHRM_OPERATOR.setFont(new Font("宋体", Font.PLAIN, 17));
			pHRM_OPERATOR.setBounds(15, 189, 84, 72);
			pHRManagement.add(pHRM_OPERATOR);
			
			pd_OPERATOR = new JPanel();
			pd_OPERATOR.setBounds(0, 0, 797, 220);
			pHRM_Display.add(pd_OPERATOR);
			pd_OPERATOR.setLayout(null);
			pd_OPERATOR.setVisible(false);
			
			JLabel pOPERATOR_L_ADD_NAME = new JLabel("密码设置");
			pOPERATOR_L_ADD_NAME.setBounds(28, 26, 81, 21);
			pd_OPERATOR.add(pOPERATOR_L_ADD_NAME);
			
			JTextField pOPERATOR_T_ADD_NAME = new JTextField();
			pOPERATOR_T_ADD_NAME.setBounds(123, 23, 96, 27);
			pd_OPERATOR.add(pOPERATOR_T_ADD_NAME);
			pOPERATOR_T_ADD_NAME.setColumns(10);
			
			JButton pOPERATOR_ADD = new JButton("确认添加");
			pOPERATOR_ADD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(pOPERATOR_T_ADD_NAME.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "请输入新操作员账号的初始密码","添加操作员", JOptionPane.CLOSED_OPTION);
						return;
					}
					
					String mes="28"+pOPERATOR_T_ADD_NAME.getText().trim();
					me.sendMessage(mes);
					String result=me.receiveMessage();
					System.out.println("i am client i receive++++"+result+"++++");
					
					String sig=result.substring(0, 1);
					System.out.println("sig is:+++"+sig+"++++");
					switch(sig){
					case "2":
						JOptionPane.showMessageDialog(null,  "操作员ID不存在，请检查后再输入","添加操作员", JOptionPane.CLOSED_OPTION);
						break;
					
					case "1":
						JOptionPane.showMessageDialog(null,  "添加操作员成功","添加操作员", JOptionPane.CLOSED_OPTION);
						break;
					default:
						JOptionPane.showMessageDialog(null,  "添加操作员失败，请重试","添加操作员", JOptionPane.CLOSED_OPTION);
						break;
					
				}
				}
			});
			pOPERATOR_ADD.setBounds(56, 160, 123, 29);
			pd_OPERATOR.add(pOPERATOR_ADD);
			
			JLabel pOPERATOR_L_CANCEL_SID = new JLabel("操作员ID");
			pOPERATOR_L_CANCEL_SID.setBounds(511, 26, 81, 21);
			pd_OPERATOR.add(pOPERATOR_L_CANCEL_SID);
			
			JTextField pOPERATOR_T_CANCEL_SID = new JTextField();
			pOPERATOR_T_CANCEL_SID.setColumns(10);
			pOPERATOR_T_CANCEL_SID.setBounds(607, 23, 96, 27);
			pd_OPERATOR.add(pOPERATOR_T_CANCEL_SID);
			
			JButton pOPERATOR_CANCEL = new JButton("确认删除");
			pOPERATOR_CANCEL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(pOPERATOR_T_CANCEL_SID.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,  "请输入操作员ID","删除操作员", JOptionPane.CLOSED_OPTION);
						return;
					}
					
					String mes="29"+pOPERATOR_T_CANCEL_SID.getText().trim();
					me.sendMessage(mes);
					String result=me.receiveMessage();
					System.out.println("i am client i receive++++"+result+"++++");
					
					String sig=result.substring(0, 1);
					System.out.println("sig is:+++"+sig+"++++");
					switch(sig){
					case "2":
						JOptionPane.showMessageDialog(null,  "操作员ID不存在，请检查后再输入","删除操作员", JOptionPane.CLOSED_OPTION);
						break;
					
					case "1":
						JOptionPane.showMessageDialog(null,  "删除操作员成功","删除操作员", JOptionPane.CLOSED_OPTION);
						break;
					default:
						JOptionPane.showMessageDialog(null,  "删除操作员失败，请重试","删除操作员", JOptionPane.CLOSED_OPTION);
						break;
					
				}
					
				}
			});
			pOPERATOR_CANCEL.setBounds(540, 160, 123, 29);
			pd_OPERATOR.add(pOPERATOR_CANCEL);
			
			

		
			

			
			
			
			
			
			
			
			/*
			 *售票点管理
			 *
			 * 
			 * 
			 
			
			pTicketBoxManagement = new JPanel();
			pTicketBoxManagement.setBounds(12, 30, 950, 282);
			panel.add(pTicketBoxManagement);
			pTicketBoxManagement.setLayout(null);
			
			
			
			JButton pTBM_ADD = new JButton("New button");
			pTBM_ADD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pAddTBIn.setVisible(true);
					pCancelTBIn.setVisible(false);
					
				}
			});
			pTBM_ADD.setBounds(15, 91, 70, 67);
			pTicketBoxManagement.add(pTBM_ADD);
			
			JButton pTBM_CANCEL = new JButton("New button");
			pTBM_CANCEL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pAddTBIn.setVisible(false);
					pCancelTBIn.setVisible(true);
					
				}
			});
			pTBM_CANCEL.setBounds(15, 169, 70, 67);
			pTicketBoxManagement.add(pTBM_CANCEL);
			
			JPanel pTBM_Display = new JPanel();
			pTBM_Display.setBounds(119, 15, 797, 220);
			pTicketBoxManagement.add(pTBM_Display);
			
			pTicketBoxManagement.setVisible(false);	
			pTBM_Display.setLayout(null);
			
			
			
			
			pAddTBIn = new JPanel();
			pAddTBIn.setBounds(28, 15, 754, 190);
			pTBM_Display.add(pAddTBIn);
			pAddTBIn.setLayout(null);
			pAddTBIn.setVisible(false);
			
			JLabel pAdd_L_TBNAME = new JLabel("售票点名称");
			pAdd_L_TBNAME.setBounds(15, 15, 114, 27);
			pAddTBIn.add(pAdd_L_TBNAME);
			
			JTextField pAdd_T_TBNAME = new JTextField();
			pAdd_T_TBNAME.setBounds(124, 15, 193, 27);
			pAddTBIn.add(pAdd_T_TBNAME);
			pAdd_T_TBNAME.setColumns(10);
			
			JLabel pAdd_L_TBADD = new JLabel("地址");
			pAdd_L_TBADD.setBounds(15, 57, 81, 21);
			pAddTBIn.add(pAdd_L_TBADD);
			
			JTextField pAdd_T_TBADD = new JTextField();
			pAdd_T_TBADD.setColumns(10);
			pAdd_T_TBADD.setBounds(124, 57, 483, 27);
			pAddTBIn.add(pAdd_T_TBADD);
			
			JButton pAdd_TADD = new JButton("添加售票点");
			pAdd_TADD.setBounds(45, 121, 123, 29);
			pAddTBIn.add(pAdd_TADD);
						
			
			pCancelTBIn = new JPanel();
			pCancelTBIn.setBounds(28, 15, 754, 190);
			pTBM_Display.add(pCancelTBIn);
			pCancelTBIn.setLayout(null);
			pCancelTBIn.setVisible(false);
			
			JLabel pCANCEL_L_TBID = new JLabel("售票点ID");
			pCANCEL_L_TBID.setBounds(61, 34, 61, 21);
			pCancelTBIn.add(pCANCEL_L_TBID);
			
			JTextField pCANCEL_T_TBID = new JTextField();
			pCANCEL_T_TBID.setBounds(137, 31, 96, 27);
			pCancelTBIn.add(pCANCEL_T_TBID);
			pCANCEL_T_TBID.setColumns(10);
			
			JButton pCANCEL_TCANCEL = new JButton("删除售票点");
			pCANCEL_TCANCEL.setBounds(72, 93, 123, 29);
			pCancelTBIn.add(pCANCEL_TCANCEL);
			*/
			
			
			
			
			/*
			 * 报表统计
			 
			pStatistics = new JPanel();
			pStatistics.setBounds(12, 30, 950, 282);
			panel.add(pStatistics);
			pStatistics.setLayout(null);
			pStatistics.setVisible(false);
			
			pStatisticsOut = new JPanel();
			pStatisticsOut.setBounds(25, 15, 910, 252);
			pStatistics.add(pStatisticsOut);
			pStatisticsOut.setLayout(null);
			pStatisticsOut.setVisible(false);
			
			pStatisticsTable = new JScrollPane();
			pStatisticsTable.setBounds(15, 59, 880, 178);
			pStatisticsOut.add(pStatisticsTable);
			
			JTextArea pSO_A_Note = new JTextArea();
			pSO_A_Note.setBounds(15, 0, 880, 45);
			pStatisticsOut.add(pSO_A_Note);
			
			String[][] row_pSO=new String[10][7];

			String[] column_pSO={"订单号","航班号","客户id","客户姓名","日期","时间","业务员",};

			DefaultTableModel ps_rs=new DefaultTableModel(row_pSO,column_pSO);

			JTable ps_rs_t = new JTable(ps_rs);
	 
			pStatisticsTable.setViewportView(ps_rs_t);
			
			pStatisticsIn = new JPanel();
			pStatisticsIn.setBounds(25, 15, 910, 252);
			pStatistics.add(pStatisticsIn);
			pStatisticsIn.setLayout(null);
			
			
			pSI_T_SID = new JTextField();
			pSI_T_SID.setBounds(192, 48, 96, 27);
			pStatisticsIn.add(pSI_T_SID);
			pSI_T_SID.setColumns(10);
			
			JLabel pSI_L_SID = new JLabel("业务员ID");
			pSI_L_SID.setBounds(96, 51, 81, 21);
			pStatisticsIn.add(pSI_L_SID);
			
			JButton pSI_CHART = new JButton("生成报表");
			pSI_CHART.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pStatisticsIn.setVisible(false);
					pStatisticsOut.setVisible(true);
				}
			});
			pSI_CHART.setBounds(96, 120, 123, 29);
			pStatisticsIn.add(pSI_CHART);
			
			*/
			
			
			
			
			
			
			
			
			
			
			
			JButton cpAM = new JButton("");
			cpAM.addActionListener(new ActionListener() {
			
				public void actionPerformed(ActionEvent arg0) {
					//panel.add(pSearchAirline);
					
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
			
			JLabel cp_L_AM = new JLabel("航班管理");
			cp_L_AM.setBounds(54, 99, 82, 30);
			contentPane.add(cp_L_AM);
			
			JButton cpHRM = new JButton("New button");
			cpHRM.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					
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
			
			JLabel cp_L_HRM = new JLabel("人事管理");
			cp_L_HRM.setBounds(167, 99, 72, 30);
			contentPane.add(cp_L_HRM);
			
			JButton cpTM = new JButton("New button");
			cpTM.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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
			
			JLabel cp_L_TM = new JLabel("售票点管理");
			cp_L_TM.setBounds(270, 99, 97, 30);
			contentPane.add(cp_L_TM);
			
			JButton cpS = new JButton("New button");
			cpS.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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
			
			JLabel cp_L_S = new JLabel("报表统计");
			cp_L_S.setBounds(394, 99, 97, 30);
			contentPane.add(cp_L_S);
			
			 
			
			
		}
	}