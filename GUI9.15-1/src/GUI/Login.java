package GUI;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import CS.Client;

public class Login extends JFrame {

	private JPanel contentPane;
	private CS.Client me;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		
					Login frame = new Login();
					
					frame.setVisible(true);
					
					
				
	}
	

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 1));
		
		
		
		
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		contentPane.add(panel);
		JTextArea textArea = new JTextArea();
		textArea.setBounds(268, 173, 173, 40);
		panel.add(textArea);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Dialog", Font.BOLD, 19));
		lblId.setBounds(196, 173, 42, 40);
		panel.add(lblId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 19));
		lblPassword.setBounds(137, 225, 101, 40);
		panel.add(lblPassword);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(268, 225, 173, 40);
		panel.add(textArea_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String mes="03"+textArea.getText().trim()+"-"+textArea_1.getText().trim();
				me.sendMessage(mes);
				String result=me.receiveMessage();
				System.out.println("i am client i receive++++"+result+"++++");
				switch(result){
				case "0":
					JOptionPane.showMessageDialog(null,  "账户不存在","登录", JOptionPane.CLOSED_OPTION);
					
					break;
				case "1":
					JOptionPane.showMessageDialog(null,  "WELCOME 客户"+textArea.getText(),"登录", JOptionPane.CLOSED_OPTION);
					setVisible(false);
					new ClientFrame(me,textArea.getText().trim());
					break;
				case "2":
					JOptionPane.showMessageDialog(null,  "WELCOME 操作员"+textArea.getText(),"登录", JOptionPane.CLOSED_OPTION);
					new Operator(me);
					setVisible(false);
					break;
				case "3":
					JOptionPane.showMessageDialog(null,  "WELCOME 管理员"+textArea.getText(),"登录", JOptionPane.CLOSED_OPTION);
					setVisible(false);
					new Admin(me);
					break;
				
				default:
					JOptionPane.showMessageDialog(null,  "登录失败，请重试","注册", JOptionPane.CLOSED_OPTION);
					break;
					
				

				}
				
				
				
				
				
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 18));
		btnNewButton.setBounds(170, 297, 133, 48);
		panel.add(btnNewButton);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Register(me);
			}
		});
		btnRegister.setFont(new Font("Dialog", Font.BOLD, 18));
		btnRegister.setBounds(344, 297, 133, 48);
		panel.add(btnRegister);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(24, 24, -13, 4);
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(38, 24, 592, 111);
		panel.add(panel_2);
		
		
		me=new Client();
		
		
		
	}
}
