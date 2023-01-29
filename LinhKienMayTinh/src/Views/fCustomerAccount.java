package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ConnectionDB;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class fCustomerAccount extends JFrame {

	private JPanel contentPane;
	private JTable tblCustomerAccount;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTextField txtSearch;
	ConnectionDB connect;
	PreparedStatement pst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fCustomerAccount frame = new fCustomerAccount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void loadCustomerAccount()
	{
		connect = new ConnectionDB();
		String caulenhSQL = "Select * from KhachHang";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {			
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblCustomerAccount.getModel();
			
			int cols =rsmd.getColumnCount();
			String[] colname =new String[cols];
			for(int i=0;i<cols;i++)
			{
				colname[i] =rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colname);
			}
			String id, ten, diachi,sdt;
			while(rs.next())
			{
				id=rs.getString(1);
				ten=rs.getString(2);
				diachi = rs.getString(3);
				sdt =rs.getString(4);
				String[] row = {id,ten,diachi,sdt};
				model.addRow(row);
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
	public fCustomerAccount() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				loadCustomerAccount();
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1093, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(303, 101, 677, 351);
		contentPane.add(scrollPane);
		
		tblCustomerAccount = new JTable();
		tblCustomerAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblCustomerAccount.getSelectedRow();
				txtMaKH.setText(tblCustomerAccount.getModel().getValueAt(row, 0).toString());
				txtTenKH.setText(tblCustomerAccount.getModel().getValueAt(row, 1).toString());
				txtDiaChi.setText(tblCustomerAccount.getModel().getValueAt(row, 2).toString());
				txtSDT.setText(tblCustomerAccount.getModel().getValueAt(row, 3).toString());
			}
		});
		scrollPane.setViewportView(tblCustomerAccount);
		
		JLabel lblNewLabel = new JLabel("M\u00E3 Kh\u00E1ch H\u00E0ng");
		lblNewLabel.setBounds(10, 130, 112, 13);
		contentPane.add(lblNewLabel);
		
		txtMaKH = new JTextField();
		txtMaKH.setBounds(132, 127, 110, 19);
		contentPane.add(txtMaKH);
		txtMaKH.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EAn Kh\u00E1ch H\u00E0ng");
		lblNewLabel_1.setBounds(10, 207, 101, 13);
		contentPane.add(lblNewLabel_1);
		
		txtTenKH = new JTextField();
		txtTenKH.setBounds(132, 204, 110, 19);
		contentPane.add(txtTenKH);
		txtTenKH.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u0110\u1ECBa Ch\u1EC9");
		lblNewLabel_2.setBounds(10, 293, 85, 13);
		contentPane.add(lblNewLabel_2);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(132, 290, 110, 19);
		contentPane.add(txtDiaChi);
		txtDiaChi.setColumns(10);
		
		txtSDT = new JTextField();
		txtSDT.setBounds(132, 368, 110, 19);
		contentPane.add(txtSDT);
		txtSDT.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("S\u0110T");
		lblNewLabel_3.setBounds(10, 371, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Th\u00EAm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtMaKH.getText().toString());
				String ten = txtTenKH.getText();
				String diachi = txtDiaChi.getText();
				String sdt = txtSDT.getText();
				String sql = "Insert into KhachHang values ('"+id+"','"+ten+"' , '"+diachi+"' ,'"+sdt+"')";
				connect = new ConnectionDB();
				connect.ThucThiCauLenh(sql);
				try {

					JOptionPane.showMessageDialog(null,"Them thanh cong");					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblCustomerAccount.getModel();
				model.setRowCount(0);
				loadCustomerAccount();
			}
		});
		btnNewButton.setBounds(26, 457, 72, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("X\u00F3a");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblCustomerAccount.getSelectedRow();
				String cell = tblCustomerAccount.getModel().getValueAt(row, 0).toString();
				String sql ="DELETE FROM KhachHang WHERE MaKH= " +cell;
				connect = new ConnectionDB();		
				connect .ThucThiCauLenh(sql);
				try {					
					JOptionPane.showMessageDialog(null,"Xoa thanh cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblCustomerAccount.getModel();
				model.setRowCount(0);
				loadCustomerAccount();

			}
		});
		btnNewButton_1.setBounds(204, 457, 65, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("S\u1EEDa");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect = new ConnectionDB();
				String sql = "Update KhachHang set TenKH=?,DiaChi=?,SDT=? where MaKH=?";
				pst =connect.ThucThi(sql);
				try {					
					pst.setInt(1, Integer.parseInt(txtMaKH.getText().toString()));
					pst.setString(2,txtTenKH.getText().toString());
					pst.setString(3,txtDiaChi.getText().toString());
					pst.setString(4,txtSDT.getText().toString());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"sua thanh cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblCustomerAccount.getModel();
				model.setRowCount(0);
				loadCustomerAccount();
			}
		});
		btnNewButton_2.setBounds(114, 457, 65, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnSearch = new JButton("T\u00ECm ki\u1EBFm");
		btnSearch.setBounds(231, 34, 85, 21);
		contentPane.add(btnSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(347, 35, 349, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnExit = new JButton("Tho\u00E1t");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Xac Nhan Thoat");
			}
		});
		btnExit.setBounds(984, 10, 85, 21);
		contentPane.add(btnExit);
	}
}
