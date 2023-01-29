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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class fStaffAccount extends JFrame {

	private JPanel contentPane;
	private JTable tblAccount;
	private JTextField txtSearch;
	PreparedStatement pst;
	ConnectionDB connect;
	private JTable tblChucVu;
	private JTextField txtID;
	private JTextField txtTaiKhoan;
	private JTextField txtMatKhau;
	JComboBox cmbChucVu;
	JComboBox cmbSearch;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fStaffAccount frame = new fStaffAccount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void fillChucVu()
	{
		connect = new ConnectionDB();
		String caulenhSQL = "Select * from LoaiTK ";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {
			while(rs.next())
			{
				cmbChucVu.addItem(rs.getString("TenLoaiTK"));			
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void LoadingAccount()
	{
		connect = new ConnectionDB();
		String caulenhSQL = "Select TaiKhoan.ID ,TaiKhoan.TaiKhoan, TaiKhoan.MatKhau,LoaiTK.TenLoaiTK from TaiKhoan,LoaiTK where TaiKhoan.MaLoaiTK =LoaiTK.MaLoaiTk";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {			
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblAccount.getModel();
			
			int cols =rsmd.getColumnCount();
			String[] colname =new String[cols];
			for(int i=0;i<cols;i++)
			{
				colname[i] =rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colname);
			}
			String id, tk, mk,loai;
			while(rs.next())
			{
				id=rs.getString(1);
				tk=rs.getString(2);
				mk = rs.getString(3);
				loai =rs.getString(4);
				String[] row = {id,tk,mk,loai};
				model.addRow(row);
			}
			
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
				
	}
	
	public void LodingChucVu()
	{
		connect = new ConnectionDB();
		String sql = "Select * from LoaiTK";
		ResultSet rs = connect.ThucThiCauLenh(sql);
		try {			
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblChucVu.getModel();
			
			int cols =rsmd.getColumnCount();
			String[] colname =new String[cols];
			for(int i=0;i<cols;i++)
			{
				colname[i] =rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colname);
			}
			String loai, tenloai;
			while(rs.next())
			{
				loai=rs.getString(1);
				tenloai = rs.getString(2);
				String[] row = {loai,tenloai};
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
	public fStaffAccount() {		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				LoadingAccount();
				LodingChucVu();
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1084, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(268, 106, 502, 289);
		contentPane.add(scrollPane);
		
		tblAccount = new JTable();
		tblAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblAccount.getSelectedRow();
				txtID.setText(tblAccount.getModel().getValueAt(row, 0).toString());
				txtTaiKhoan.setText(tblAccount.getModel().getValueAt(row, 1).toString());
				txtMatKhau.setText(tblAccount.getModel().getValueAt(row, 2).toString());
				cmbChucVu.setSelectedItem(tblAccount.getModel().getValueAt(row,3));
			}
		});
		scrollPane.setViewportView(tblAccount);
		
		JButton btnSearch = new JButton("T\u00ECm ki\u1EBFm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbSearch.getSelectedItem().equals("ID"))
				{
					DefaultTableModel model = (DefaultTableModel) tblAccount.getModel();
					model.setRowCount(0);
					connect = new ConnectionDB();
					String caulenhSQL = "Select TaiKhoan.ID ,TaiKhoan.TaiKhoan, TaiKhoan.MatKhau,LoaiTK.TenLoaiTK from TaiKhoan,LoaiTK"
							+ " where TaiKhoan.MaLoaiTK =LoaiTK.MaLoaiTk and TaiKhoan.ID LIKE '%" + txtSearch.getText() +"%'";
					ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
					try {			
						ResultSetMetaData rsmd = rs.getMetaData();
						
						int cols =rsmd.getColumnCount();
						String[] colname =new String[cols];
						for(int i=0;i<cols;i++)
						{
							colname[i] =rsmd.getColumnName(i+1);
							model.setColumnIdentifiers(colname);
						}
						String id, tk, mk,loai;
						while(rs.next())
						{
							id=rs.getString(1);
							tk=rs.getString(2);
							mk = rs.getString(3);
							loai =rs.getString(4);
							String[] row = {id,tk,mk,loai};
							model.addRow(row);
						}
											
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				if(cmbSearch.getSelectedItem().equals("TaiKhoan")) {
					DefaultTableModel model = (DefaultTableModel) tblAccount.getModel();
					model.setRowCount(0);
					connect = new ConnectionDB();
					String caulenhSQL = "Select TaiKhoan.ID ,TaiKhoan.TaiKhoan, TaiKhoan.MatKhau,LoaiTK.TenLoaiTK from TaiKhoan,LoaiTK"
							+ " where TaiKhoan.MaLoaiTK =LoaiTK.MaLoaiTk and TaiKhoan.TaiKhoan LIKE '%" + txtSearch.getText() +"%'";
					ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
					try {			
						ResultSetMetaData rsmd = rs.getMetaData();
						
						int cols =rsmd.getColumnCount();
						String[] colname =new String[cols];
						for(int i=0;i<cols;i++)
						{
							colname[i] =rsmd.getColumnName(i+1);
							model.setColumnIdentifiers(colname);
						}
						String id, tk, mk,loai;
						while(rs.next())
						{
							id=rs.getString(1);
							tk=rs.getString(2);
							mk = rs.getString(3);
							loai =rs.getString(4);
							String[] row = {id,tk,mk,loai};
							model.addRow(row);
						}
											
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
		});
		btnSearch.setBounds(164, 38, 85, 21);
		contentPane.add(btnSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(363, 39, 320, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnAdd = new JButton("Th\u00EAm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql=  "insert into TaiKhoan(ID, TaiKhoan, MatKhau,MaLoaiTK) values (?,?,?,?)";
				connect = new ConnectionDB();
				pst =connect.ThucThi(sql);
				try {
					pst.setString(1,txtID.getText().toString());
					pst.setString(2,txtTaiKhoan.getText().toString());
					pst.setString(3,txtMatKhau.getText().toString());
					if(cmbChucVu.getSelectedItem().toString().equalsIgnoreCase("Admin"))
					{
						pst.setInt(4,1);
					}
					if(cmbChucVu.getSelectedItem().toString().equalsIgnoreCase("Staff"))
					{
						pst.setInt(4,2);
					}

					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Them Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblAccount.getModel();
				model.setRowCount(0);
				LoadingAccount();
			}
		});
		btnAdd.setBounds(14, 404, 67, 21);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("S\u1EEDa");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				connect = new ConnectionDB();
				String sql = "Update TaiKhoan set TaiKhoan=? ,MatKhau=?, MaLoaiTK=? where ID=?";
				pst =connect.ThucThi(sql);
				try {					
					pst.setString(1,txtTaiKhoan.getText().toString());
					pst.setString(2,txtMatKhau.getText().toString());
					if(cmbChucVu.getSelectedItem().toString().equalsIgnoreCase("Admin"))
					{
						pst.setInt(3,1);
					}
					if(cmbChucVu.getSelectedItem().toString().equalsIgnoreCase("Staff"))
					{
						pst.setInt(3,2);
					}
					pst.setString(4,txtID.getText().toString());					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"sua thanh cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblAccount.getModel();
				model.setRowCount(0);
				LoadingAccount();
			}
		});
		btnUpdate.setBounds(91, 404, 59, 21);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("X\u00F3a");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM TaiKhoan WHERE ID = ?";
				connect = new ConnectionDB();
				pst =connect.ThucThi(sql);
				try
				{
					pst.setString(1,txtID.getText().toString());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Xoa Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblAccount.getModel();
				model.setRowCount(0);
				LoadingAccount();
			}
		});
		btnDelete.setBounds(164, 404, 67, 21);
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(832, 226, 205, 250);
		contentPane.add(scrollPane_1);
		
		tblChucVu = new JTable();
		scrollPane_1.setViewportView(tblChucVu);
		
		JLabel lblNewLabel = new JLabel("B\u1EA3ng ch\u1EE9c v\u1EE5");
		lblNewLabel.setBounds(900, 211, 85, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(14, 151, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("T\u00E0i Kho\u1EA3n");
		lblNewLabel_2.setBounds(14, 211, 67, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("M\u1EADt Kh\u1EA9u");
		lblNewLabel_3.setBounds(14, 265, 67, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Ch\u1EE9c v\u1EE5");
		lblNewLabel_4.setBounds(14, 332, 67, 13);
		contentPane.add(lblNewLabel_4);
		
		txtID = new JTextField();
		txtID.setBounds(93, 148, 120, 19);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setBounds(93, 208, 120, 19);
		contentPane.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		
		txtMatKhau = new JTextField();
		txtMatKhau.setBounds(93, 262, 120, 19);
		contentPane.add(txtMatKhau);
		txtMatKhau.setColumns(10);
		
		cmbChucVu = new JComboBox();
		cmbChucVu.setBounds(91, 328, 122, 21);
		contentPane.add(cmbChucVu);
		
		cmbSearch = new JComboBox();
		cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"ID", "TaiKhoan"}));
		cmbSearch.setBounds(268, 38, 85, 21);
		contentPane.add(cmbSearch);
		
		fillChucVu();
	}
}
