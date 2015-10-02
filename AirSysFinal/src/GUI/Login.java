package GUI;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

/**
 * 登录界面
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 
 * 
 */
public class Login extends JFrame {

	private JPanel contentPane;// 窗体的contentPane对象
	private CS.Client me;// 客户端对象，用于与服务器交互
	
	/**
	 * 
	 * 客户端main方法
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				/*美化窗体*/
				JFrame.setDefaultLookAndFeelDecorated(true);
				try {
					UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				/*新建登录窗体*/
				Login frame = new Login();
				
				/*添加背景图片 */
				ImageIcon bg = new ImageIcon("msn_background.jpg");//获取背景图片
				JLabel label = new JLabel(bg);// 把背景图片显示在一个标签里
				label.setBounds(0, 0, 703, 505);// 把标签的大小位置设置为图片刚好填充整个面
				frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));// 添加图片到frame的第二层
				JPanel jp = (JPanel) frame.getContentPane();//获取frame的最上层面板为了设置其背景颜色（JPanel有设置透明的方法）
				jp.setOpaque(false);// 设置透明

				frame.setVisible(true);//显示窗体
			}
		});
	}

	/**
	 * 
	 * 构造方法
	 * 
	 * 创建登录窗体
	 * 
	 * 
	 */
	public Login() {

		/* 设置Login窗体 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 1));

		/* 新建面板 */
		JPanel panel = new JPanel();
		panel.setLayout(null);
		contentPane.add(panel);

		/* ID标签 */
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Dialog", Font.BOLD, 19));
		lblId.setBounds(196, 173, 42, 40);
		panel.add(lblId);

		/* 密码标签 */
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 19));
		lblPassword.setBounds(137, 225, 101, 40);
		panel.add(lblPassword);

		/* ID文本域 */
		JTextField textArea = new JTextField();
		textArea.setBounds(268, 173, 173, 40);
		panel.add(textArea);
		panel.setOpaque(false);

		/* 密码文本域 */
		JPasswordField textArea_1 = new JPasswordField();
		textArea_1.setEchoChar('*');
		textArea_1.setBounds(268, 225, 173, 40);
		panel.add(textArea_1);

		/* 登录按钮 */
		JButton btnNewButton = new JButton("Login");
		/* 登录按钮的监听事件 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textArea.getText().trim().length()==0||textArea.getText().trim().length()==0){
					JOptionPane.showMessageDialog(null, "请输入id和密码", "登录", JOptionPane.CLOSED_OPTION);
					return;
				}
				/* 获取文本域输入的id和密码啊，编辑消息，发送给服务器 */
				String mes = "03" + textArea.getText().trim() + "-" + textArea_1.getText().trim();
				me.sendMessage(mes);
				/* 接收服务器消息，解析判断 */
				String result = me.receiveMessage();
				switch (result) {
				/* 若返回值为0，账户不存在 */
				case "0":
					JOptionPane.showMessageDialog(null, "账户不存在", "登录", JOptionPane.CLOSED_OPTION);

					break;
				/* 若返回值为1，客户登录成功，新建客户窗体 */
				case "1":
					JOptionPane.showMessageDialog(null, "WELCOME 客户" + textArea.getText(), "登录",
							JOptionPane.CLOSED_OPTION);
					setVisible(false);
					new ClientFrame(me, textArea.getText().trim());
					break;
				/* 若返回值为2，操作员登录成功，新建操作员窗体 */
				case "2":
					JOptionPane.showMessageDialog(null, "WELCOME 操作员" + textArea.getText(), "登录",
							JOptionPane.CLOSED_OPTION);
					new Operator(me);
					setVisible(false);
					break;
				/* 若返回值为3，管理员登录成功，新建管理员窗体 */
				case "3":
					JOptionPane.showMessageDialog(null, "WELCOME 管理员" + textArea.getText(), "登录",
							JOptionPane.CLOSED_OPTION);
					setVisible(false);
					new Admin(me);
					break;
				/* 其他返回值，登录失败 */
				default:
					JOptionPane.showMessageDialog(null, "登录失败，请重试", "登录", JOptionPane.CLOSED_OPTION);
					break;
				}

			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 18));
		btnNewButton.setBounds(170, 297, 133, 48);
		panel.add(btnNewButton);

		/* 注册按钮 */
		JButton btnRegister = new JButton("Register");
		/* 注册按钮监听事件 */
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Register(me);// 新建注册窗体
			}
		});
		btnRegister.setFont(new Font("Dialog", Font.BOLD, 18));
		btnRegister.setBounds(344, 297, 133, 48);
		panel.add(btnRegister);

		/* 放系统名的panel */
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(38, 24, 592, 137);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setOpaque(false);

		/* 系统名标签 */
		JLabel label = new JLabel("航空票务管理系统");
		label.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		label.setFont(new Font("Dialog", Font.BOLD, 33));
		label.setBounds(149, 41, 327, 60);
		panel_2.add(label);

		// 实例化客户端对象
		me = new Client();

	}
}
