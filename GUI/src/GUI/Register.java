package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CS.Client;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	
	private Client me;
	private JTextField password;

	/**
	 * Launch the application.
	 
	
	public void openRegister(Client current) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register(current);
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
	public Register(Client current) {
		me=current;
		setVisible(true);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 709, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel REGI_NOTE = new JLabel("注册：请填写以下信息");
		REGI_NOTE.setBounds(57, 41, 368, 53);
		contentPane.add(REGI_NOTE);
		
		JLabel REGI_L_NAME = new JLabel("姓名");
		REGI_L_NAME.setBounds(67, 109, 111, 39);
		contentPane.add(REGI_L_NAME);
		
		JTextField name = new JTextField();
		name.setBounds(170, 115, 219, 27);
		contentPane.add(name);
		name.setColumns(10);
		
		JTextField idn = new JTextField();
		idn.setColumns(10);
		idn.setBounds(170, 169, 219, 27);
		contentPane.add(idn);
		
		JLabel REGI_L_IDN = new JLabel("身份证号");
		REGI_L_IDN.setBounds(67, 163, 111, 39);
		contentPane.add(REGI_L_IDN);
		
		JTextField phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(170, 230, 219, 27);
		contentPane.add(phone);
		
		JLabel REGI_L_PHONE = new JLabel("手机号");
		REGI_L_PHONE.setBounds(67, 224, 111, 39);
		contentPane.add(REGI_L_PHONE);
		
		JButton REGI_REGI = new JButton("注册");
		REGI_REGI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String mes="04"+name.getText()+"-"+idn.getText()+"-"+phone.getText()+"-"+password.getText();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				switch(result){
				case "0":
					JOptionPane.showMessageDialog(null,  "请正确输入身份证号","注册", JOptionPane.CLOSED_OPTION);
					break;
				case "1":
					JOptionPane.showMessageDialog(null,  "该身份证号已注册","注册", JOptionPane.CLOSED_OPTION);
					break;
				case "2":
					JOptionPane.showMessageDialog(null,  "该手机号已注册","注册", JOptionPane.CLOSED_OPTION);
					break;
				case "3":
					JOptionPane.showMessageDialog(null,  "密码长度有误，请输入4~10位密码","注册", JOptionPane.CLOSED_OPTION);
					break;
				case "4":
					JOptionPane.showMessageDialog(null,  "注册成功","注册", JOptionPane.CLOSED_OPTION);
					setVisible(false);
					break;
				default:
					JOptionPane.showMessageDialog(null,  "注册失败，请重试","注册", JOptionPane.CLOSED_OPTION);
					break;
					
				

				}
				
				
				/*
				if(result=="3"){
					JOptionPane.showMessageDialog(null,  "注册成功","注册", JOptionPane.CLOSED_OPTION);
					setVisible(false);
				}
				else{
					if(result=="0"){
						JOptionPane.showMessageDialog(null,  "请正确输入身份证号该手机号已注册","注册", JOptionPane.CLOSED_OPTION);
					}
					else{
						if(result=="1"){
							JOptionPane.showMessageDialog(null,  "该身份证号已注册","注册", JOptionPane.CLOSED_OPTION);
						}
						else{
							if(result=="2"){
								JOptionPane.showMessageDialog(null,  "该手机号已注册","注册", JOptionPane.CLOSED_OPTION);
							}
							else{
								if(result=="3"){
									JOptionPane.showMessageDialog(null,  "密码长度有误，请输入4~10位密码","注册", JOptionPane.CLOSED_OPTION);
								}
								else{
									JOptionPane.showMessageDialog(null,  "注册1失败，请重试","注册", JOptionPane.CLOSED_OPTION);
								}
							}
							
						}
					}
					
				}
					*/
				
				
			}
		});
		REGI_REGI.setBounds(100, 346, 123, 29);
		contentPane.add(REGI_REGI);
		
		JLabel REGI_L_PASSWORD = new JLabel("密码");
		REGI_L_PASSWORD.setBounds(67, 292, 111, 39);
		contentPane.add(REGI_L_PASSWORD);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(170, 298, 219, 27);
		contentPane.add(password);
	}
}
