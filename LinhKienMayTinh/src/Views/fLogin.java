package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.ConnectionDB;
import Controller.cConnectSQL;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

public class fLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtTaiKhoan;
	private JTextField txtMatKhau;
	JComboBox comboBox;
	ConnectionDB connect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fLogin frame = new fLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void Login()
	{	
		String tk = txtTaiKhoan.getText(); 
		String mk = txtMatKhau.getText();
		String option = String.valueOf(comboBox.getSelectedItem());
		connect = new ConnectionDB();
		String sql = "Select * from TaiKhoan,LoaiTK where TaiKhoan.TaiKhoan ='"+ tk+"'and TaiKhoan.MatKhau='"+mk+"'and TaiKhoan.MaLoaiTK =LoaiTK.MaLoaiTk";
		ResultSet rs = connect.ThucThiCauLenh(sql);
		try {
						
			if(tk.equals("")||mk.equals("")||option.equals(""))
			{
				JOptionPane.showMessageDialog(rootPane, "Vui long dien day du thong tin","Error",1);
			}
			else {
				if(rs.next()) {
					String s1 = rs.getString("TenLoaiTK");
					if(option.equalsIgnoreCase("Admin") && s1.equalsIgnoreCase("admin"))
					{
						fAdmin f1 = new fAdmin();
						f1.setVisible(true);
						setVisible(false);
					}
					if(option.equalsIgnoreCase("Staff") && s1.equalsIgnoreCase("staff"))
					{
						fStaff f2 = new fStaff();
						f2.setVisible(true);
						setVisible(false);
					}
				}else {
					JOptionPane.showMessageDialog(rootPane, "tai khoan hoac mat khau khong dung","Error",1);
				}
			}
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}
	/**
	 * Create the frame.
	 */
	public fLogin() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(43, 68, 64, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(43, 123, 64, 13);
		contentPane.add(lblNewLabel_1);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setBounds(143, 65, 96, 19);
		contentPane.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		
		txtMatKhau = new JTextField();
		txtMatKhau.setBounds(143, 120, 96, 19);
		contentPane.add(txtMatKhau);
		txtMatKhau.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login();
		
			}
		});
		btnLogin.setBounds(66, 212, 96, 21);
		contentPane.add(btnLogin);
		
		JButton btbnExit = new JButton("Exit");
		btbnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btbnExit.setBounds(242, 212, 96, 21);
		contentPane.add(btbnExit);
		
		JLabel lblNewLabel_2 = new JLabel("User");
		lblNewLabel_2.setBounds(43, 168, 90, 13);
		contentPane.add(lblNewLabel_2);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Staff", "Admin"}));
		comboBox.setBounds(143, 164, 96, 21);
		contentPane.add(comboBox);
	}
}
