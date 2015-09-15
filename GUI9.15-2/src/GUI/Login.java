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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextArea;
import java.awt.Font;
import CS.Client;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private JPanel contentPane;
	private CS.Client me;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				
				try {
					/*
					 * 想要修改皮肤的话，只需要更改，下面这个函数的参数，具体改成什么样，可以打开，Referenced Libraries -> 点击substance.jar -> 找到org.jvnet.substance.skin这个包  -> 将下面的SubstanceDustCoffeeLookAndFeel 替换成 刚刚打开的包下的任意一个“Substance....LookAndFeel”即可
					 */
					//UIManager
							//.setLookAndFeel(new org.jvnet.substance.skin.SubstanceDustCoffeeLookAndFeel());
					//例如按照上面的步骤，可以看见一个叫，"SubstanceOfficeBlue2007LookAndFeel.class"，想要替换成这个皮肤，就可以向下面这样写
					UIManager
					.setLookAndFeel(new org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel());
					// 运行一下，皮肤就可以换了
					// 想要详细了解的同学，可以去百度这个第三方包：substance.jar
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Login frame = new Login();
				ImageIcon bg = new ImageIcon("msn_background.jpg"); 
				// 把背景图片显示在一个标签里
				JLabel label = new JLabel(bg); 
				//把标签的大小位置设置为图片刚好填充整个面
				label.setBounds(0,0,703, 505);
				//添加图片到frame的第二层 
				frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); 
				//获取frame的最上层面板为了设置其背景颜色（JPanel有设置透明的方法）
				JPanel jp=(JPanel)frame.getContentPane();
				jp.setOpaque(false);//设置透明 
				
				frame.setVisible(true);
				
				
				
				
				
			}
		});

	

		
		
		
			/*		
		Login frame = new Login();
					
		frame.setVisible(true);
		*/
					
					
				
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
		JTextField textArea = new JTextField();
		textArea.setBounds(268, 173, 173, 40);
		panel.add(textArea);
		panel.setOpaque(false);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Dialog", Font.BOLD, 19));
		lblId.setBounds(196, 173, 42, 40);
		panel.add(lblId);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 19));
		lblPassword.setBounds(137, 225, 101, 40);
		panel.add(lblPassword);
		
		JTextField textArea_1 = new JTextField();
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(38, 24, 592, 137);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setOpaque(false);
		
		JLabel label = new JLabel("航空票务管理系统");
		label.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		label.setFont(new Font("Dialog", Font.BOLD, 33));
		label.setBounds(149, 41, 327, 60);
		panel_2.add(label);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon plane=new ImageIcon("plane.png");
		lblNewLabel.setIcon(plane);
		//lblNewLabel.setBounds(235, 63, plane.getIconWidth()8361,plane.getIconHeight());
		//panel_2.add(lblNewLabel);
		System.out.println(plane.getIconWidth());
		System.out.println(plane.getIconHeight());
		
		
		me=new Client();
		
		
		
	}
}
