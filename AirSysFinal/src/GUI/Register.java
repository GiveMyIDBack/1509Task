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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 注册界面
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 
 * 
 */
public class Register extends JFrame {

	private JPanel contentPane;// 窗体的contentPane对象
	private Client me;// 客户端对象，用于与服务器交互

	/**
	 * 
	 * 构造方法
	 * 
	 * 创建注册窗体
	 * 
	 * 
	 */
	public Register(Client current) {
		me = current;// 获取客户端对象

		/* 设置窗体属性 */
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 709, 463);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/* 设置背景图片 */
		ImageIcon bg = new ImageIcon("msn_background.jpg");
		JLabel label = new JLabel(bg);
		label.setBounds(0, 0, 1020, 610);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		/* 提示信息标签 */
		JLabel REGI_NOTE = new JLabel("注册：请填写以下信息");
		REGI_NOTE.setBounds(57, 41, 368, 53);
		contentPane.add(REGI_NOTE);

		/* 姓名标签 */
		JLabel REGI_L_NAME = new JLabel("姓名");
		REGI_L_NAME.setBounds(67, 109, 111, 39);
		contentPane.add(REGI_L_NAME);

		/* 姓名文本域 */
		JTextField name = new JTextField();
		name.setBounds(170, 115, 219, 27);
		contentPane.add(name);
		name.setColumns(10);

		/* 身份证号标签 */
		JLabel REGI_L_IDN = new JLabel("身份证号");
		REGI_L_IDN.setBounds(67, 163, 111, 39);
		contentPane.add(REGI_L_IDN);

		/* 身份证号文本域 */
		JTextField idn = new JTextField();
		idn.setColumns(10);
		idn.setBounds(170, 169, 219, 27);
		contentPane.add(idn);

		/* 手机号标签 */
		JLabel REGI_L_PHONE = new JLabel("手机号");
		REGI_L_PHONE.setBounds(67, 224, 111, 39);
		contentPane.add(REGI_L_PHONE);

		/* 手机号文本域 */
		JTextField phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(170, 230, 219, 27);
		contentPane.add(phone);

		/* 密码标签 */
		JLabel REGI_L_PASSWORD = new JLabel("密码");
		REGI_L_PASSWORD.setBounds(67, 292, 111, 39);
		contentPane.add(REGI_L_PASSWORD);

		/* 密码文本域 */
		JTextField password = new JTextField();
		password.setColumns(10);
		password.setBounds(170, 298, 219, 27);
		contentPane.add(password);

		/* 注册按钮 */
		JButton REGI_REGI = new JButton("注册");
		/* 注册按钮监听事件 */
		REGI_REGI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*检查姓名文本域是否为空*/
				if(name.getText().trim().length()==0){
					JOptionPane.showMessageDialog(null, "姓名不能为空", "注册", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查身份证号文本域是否为空*/
				if(idn.getText().trim().length()==0){
					JOptionPane.showMessageDialog(null, "身份证号不能为空", "注册", JOptionPane.CLOSED_OPTION);
					return;
				}
				/*检查手机号文本域是否为空*/
				if(phone.getText().trim().length()==0){
					JOptionPane.showMessageDialog(null, "手机号不能为空", "注册", JOptionPane.CLOSED_OPTION);
					return;
				}
				/* 编辑文本域中获取的信息，发送给服务器 */
				String mes = "04" + name.getText() + "-" + idn.getText() + "-" + phone.getText() + "-"
						+ password.getText();
				me.sendMessage(mes);
				/* 接收服务器返回的消息，解析 */
				String result = me.receiveMessage();
				String sig = result.substring(0, 1);// 返回值
				String output = result.substring(1);// 返回的信息串

				switch (sig) {
				/* 若返回值为0，身份证号格式错误 */
				case "0":
					JOptionPane.showMessageDialog(null, "请正确输入身份证号", "注册", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为1，身份证号已注册 */
				case "1":
					JOptionPane.showMessageDialog(null, "该身份证号已注册", "注册", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为2，手机号已注册 */
				case "2":
					JOptionPane.showMessageDialog(null, "该手机号已注册", "注册", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为3，密码长度有误 */
				case "3":
					JOptionPane.showMessageDialog(null, "密码长度有误，请输入4~10位密码", "注册", JOptionPane.CLOSED_OPTION);
					break;
				/* 若返回值为4，注册成功，显示服务器分配的客户id */
				case "4":
					JOptionPane.showMessageDialog(null, "注册成功!您的ID为" + output, "注册", JOptionPane.CLOSED_OPTION);
					setVisible(false);
					break;
				/* 若返回值为其他数字，注册失败 */
				default:
					JOptionPane.showMessageDialog(null, "注册失败，请重试", "注册", JOptionPane.CLOSED_OPTION);
					break;

				}
			}
		});
		REGI_REGI.setBounds(100, 346, 123, 29);
		contentPane.add(REGI_REGI);
	}
}
