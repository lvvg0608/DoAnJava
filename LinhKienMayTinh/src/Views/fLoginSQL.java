package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.cConnectSQL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class fLoginSQL extends JFrame {

	private JPanel contentPane;
	private JTextField txtServerName;
	private JTextField txtDatabase;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	JLabel lblThongBao;
	JButton btnLogin;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fLoginSQL frame = new fLoginSQL();
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
	public fLoginSQL() {
		setTitle("K\u1EBFt n\u1ED1i CSDL");
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Server Name");
		lblNewLabel.setBounds(50, 47, 96, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Database");
		lblNewLabel_1.setBounds(50, 97, 66, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("User");
		lblNewLabel_2.setBounds(50, 143, 66, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setBounds(50, 186, 66, 13);
		contentPane.add(lblNewLabel_3);
		
		txtServerName = new JTextField();
		txtServerName.setBounds(209, 44, 96, 19);
		contentPane.add(txtServerName);
		txtServerName.setColumns(10);
		
		txtDatabase = new JTextField();
		txtDatabase.setBounds(209, 94, 96, 19);
		contentPane.add(txtDatabase);
		txtDatabase.setColumns(10);
		
		txtUser = new JTextField();
		txtUser.setBounds(209, 140, 96, 19);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(209, 183, 96, 19);
		contentPane.add(txtPassword);
		
		

		
		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String serverName= txtServerName.getText();
				String database = txtDatabase.getText();
				String user = txtUser.getText();
				String password = String.valueOf(txtPassword.getPassword());
				try {
					if(serverName.equals("")||database.equals("")|| user.equals("")||password.equals("")) {
						JOptionPane.showMessageDialog(rootPane, "Vui long dien day du thong tin","Error",1);
					}
					else {
						if(serverName.equalsIgnoreCase("LVVG") && database.equalsIgnoreCase("LinhKienMayTinh") && user.equalsIgnoreCase("sa") && password.equalsIgnoreCase("123456")) {		
							cConnectSQL cConnect =new cConnectSQL();
							lblThongBao.setText("Ket noi Database thanh cong");
							lblThongBao.setForeground(Color.red);
							btnLogin.setEnabled(true);
							
						}
						else {
							JOptionPane.showMessageDialog(rootPane, "Vui long dien chinh xac thong tin","Error",1);
							lblThongBao.setText("Ket noi Database that bai");
							lblThongBao.setForeground(Color.red);
							
						}
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		btnTest.setBounds(117, 220, 85, 21);
		contentPane.add(btnTest);
		
		btnLogin = new JButton("Login");
		btnLogin.setEnabled(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fLogin f1 = new fLogin();
				f1.setVisible(true);
			}
		});
		btnLogin.setBounds(315, 220, 85, 21);
		contentPane.add(btnLogin);
		
		lblThongBao = new JLabel("");
		lblThongBao.setBounds(10, 240, 295, 13);
		contentPane.add(lblThongBao);
	}
}
