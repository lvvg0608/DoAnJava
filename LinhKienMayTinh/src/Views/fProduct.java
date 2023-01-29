package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.ConnectionDB;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;


public class fProduct extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaLK;
	private JTextField txtTenLK;
	private JTextField txtMoTa;
	private JTextField txtBaoHanh;
	private JTable tblLinhKien;
	PreparedStatement pst;
	JComboBox cmbLoai;
	ConnectionDB connect;
	Date date;
	JComboBox cmbHangSX;
	JComboBox cmbNCC;
	SimpleDateFormat sdf;
	private JTable tblLoaiLK;
	private JTable tblHangSX;
	private JTable tblNCC;
	JDateChooser dcNgaySinh;
	DateFormat dateformat;
	JDateChooser dcNgayCapNhat;
	JComboBox cmbLoaiTK;
	private JTextField txtSoLuong;
	private JTextField txtSearch;
	private JTextField txtDonGia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fProduct frame = new fProduct();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public void fillLoai()
	{
		connect = new ConnectionDB();
		String caulenhSQL = "Select * from LoaiLK ";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {
			while(rs.next())
			{
				cmbLoai.addItem(rs.getString("MaLoai"));			
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
	}
	
	public void fillHangSX()
	{
		connect = new ConnectionDB();
		String caulenhSQL = "Select * from HangSX ";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {
			while(rs.next())
			{
				cmbHangSX.addItem(rs.getString("MaHangSX"));			
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void fillNhaCungCap()
	{
		connect = new ConnectionDB();
		String caulenhSQL = "Select * from NhaCungCap ";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {
			while(rs.next())
			{
				cmbNCC.addItem(rs.getString("MaNCC"));			
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void LoadLinhKien()
	{
		connect = new ConnectionDB();
		String caulenhSQL = "Select DISTINCT LinhKien.MaLK, LinhKien.TenLK,LoaiLK.TenLoai,HangSX.TenHangSX,NhaCungCap.TenNCC,LinhKien.DonGia,LinhKien.SoLuong,LinhKien.MoTa,LinhKien.TGBH,LinhKien.NgayCapNhat,LinhKien.Active from LinhKien,LoaiLK,NhaCungCap,HangSX"
				+ " where LinhKien.LoaiLK = LoaiLK.MaLoai and LinhKien.NhaCungCap = NhaCungCap.MaNCC"
				+ " and LinhKien.HangSX = HangSX.MaHangSX";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {			
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblLinhKien.getModel();
			
			int cols =rsmd.getColumnCount();
			String[] colname =new String[cols];
			for(int i=0;i<cols;i++)
			{
				colname[i] =rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colname);
			}
			String malk, tenloai, tenlk ,hangsx,ncc,dongia,soluong,mota,tgbh,ngaycapnhat,active;
			while(rs.next())
			{
				dateformat = new SimpleDateFormat("dd-MM-yyyy");
				malk=rs.getString(1);
				tenlk = rs.getString(2);
				tenloai=rs.getString(3);
				hangsx = rs.getString(4);
				ncc =rs.getString(5);
				dongia = rs.getString(6);
				soluong = rs.getString(7);
				mota =rs.getString(8);
				tgbh =rs.getString(9);
				date =rs.getDate(10);
				ngaycapnhat = dateformat.format(date);
				active= rs.getString(11);
				String[] row = {malk,tenlk,tenloai,hangsx,ncc,dongia,soluong,mota,tgbh,ngaycapnhat,active};
				model.addRow(row);
			}
			
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void loadLoaiLK()
	{
		connect = new ConnectionDB();
		String caulenhSQL ="Select * from LoaiLk ";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {			
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblLoaiLK.getModel();
			
			int cols =rsmd.getColumnCount();
			String[] colname =new String[cols];
			for(int i=0;i<cols;i++)
			{
				colname[i] =rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colname);
			}
			String maloai, tenloai;
			while(rs.next())
			{
				maloai=rs.getString(1);
				tenloai = rs.getString(2);
				String[] row = {maloai,tenloai};
				model.addRow(row);
			}
			
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void LoadHangSX()
	{
		connect = new ConnectionDB();
		String caulenhSQL ="Select * from HangSX ";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {			
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblHangSX.getModel();
			
			int cols =rsmd.getColumnCount();
			String[] colname =new String[cols];
			for(int i=0;i<cols;i++)
			{
				colname[i] =rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colname);
			}
			String mahang, tenhang;
			while(rs.next())
			{
				mahang=rs.getString(1);
				tenhang = rs.getString(2);
				String[] row = {mahang,tenhang};
				model.addRow(row);
			}
			
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	
	public void LoadNCC()
	{
		connect = new ConnectionDB();
		String caulenhSQL ="Select * from NhaCungCap ";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {			
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblNCC.getModel();
			
			int cols =rsmd.getColumnCount();
			String[] colname =new String[cols];
			for(int i=0;i<cols;i++)
			{
				colname[i] =rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colname);
			}
			String mancc, tenncc, diachi, sdt;
			while(rs.next())
			{
				mancc=rs.getString(1);
				tenncc = rs.getString(2);
				diachi = rs.getString(3);
				sdt = rs.getString(4);
				String[] row = {mancc,tenncc,diachi,sdt};
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
	public fProduct() {
		setTitle("Danh S\u00E1ch Linh Ki\u1EC7n");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				LoadLinhKien();
				loadLoaiLK();
				LoadHangSX();
				LoadNCC();
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1379, 805);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("M\u00E3 LK");
		lblNewLabel.setBounds(10, 65, 88, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblTnLinhKin = new JLabel("T\u00EAn Linh ki\u1EC7n");
		lblTnLinhKin.setBounds(10, 107, 88, 13);
		contentPane.add(lblTnLinhKin);
		
		JLabel lblTnLoi = new JLabel("Lo\u1EA1i S\u00E3n Ph\u1EA9m");
		lblTnLoi.setBounds(10, 155, 88, 13);
		contentPane.add(lblTnLoi);
		
		JLabel lblHngSnXut = new JLabel("H\u00E3ng s\u00E3n xu\u1EA5t");
		lblHngSnXut.setBounds(10, 193, 88, 13);
		contentPane.add(lblHngSnXut);
		
		JLabel lblNewLabel_1 = new JLabel("Nh\u00E0 cung c\u1EA5p");
		lblNewLabel_1.setBounds(10, 302, 88, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("M\u00F4 t\u1EA3");
		lblNewLabel_2.setBounds(10, 348, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("B\u1EA3o h\u00E0nh");
		lblNewLabel_3.setBounds(10, 393, 88, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Ng\u00E0y c\u1EADp nh\u1EADt");
		lblNewLabel_4.setBounds(10, 438, 88, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Active");
		lblNewLabel_5.setBounds(10, 485, 66, 13);
		contentPane.add(lblNewLabel_5);
		
		JCheckBox ckActive = new JCheckBox("");
		ckActive.setBounds(108, 477, 21, 21);
		contentPane.add(ckActive);
		
		txtMaLK = new JTextField();
		txtMaLK.setBounds(108, 62, 135, 19);
		contentPane.add(txtMaLK);
		txtMaLK.setColumns(10);
		
		txtTenLK = new JTextField();
		txtTenLK.setColumns(10);
		txtTenLK.setBounds(108, 104, 135, 19);
		contentPane.add(txtTenLK);
		
		txtMoTa = new JTextField();
		txtMoTa.setColumns(10);
		txtMoTa.setBounds(107, 345, 136, 19);
		contentPane.add(txtMoTa);
		
		txtBaoHanh = new JTextField();
		txtBaoHanh.setColumns(10);
		txtBaoHanh.setBounds(108, 390, 135, 19);
		contentPane.add(txtBaoHanh);
		
		cmbLoai = new JComboBox();
		cmbLoai.setBounds(108, 151, 135, 21);
		contentPane.add(cmbLoai);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(284, 63, 667, 378);
		contentPane.add(scrollPane);
		
		tblLinhKien = new JTable();
		tblLinhKien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableModel model =tblLinhKien.getModel();
				int row = tblLinhKien.getSelectedRow();
				txtMaLK.setText(tblLinhKien.getModel().getValueAt(row, 0).toString());
				txtTenLK.setText(tblLinhKien.getModel().getValueAt(row, 1).toString());
				txtDonGia.setText(tblLinhKien.getModel().getValueAt(row, 5).toString());
				txtSoLuong.setText(tblLinhKien.getModel().getValueAt(row, 6).toString());
				txtMoTa.setText(tblLinhKien.getModel().getValueAt(row, 7).toString());
				txtBaoHanh.setText(tblLinhKien.getModel().getValueAt(row, 8).toString());
				try {					
					date = new SimpleDateFormat("dd-MM-yyyy").parse((String)model.getValueAt(row,9));
					dcNgayCapNhat.setDate(date);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				String active = tblLinhKien.getModel().getValueAt(row,10).toString();
				if(active.equals("1"))
				{
					ckActive.setSelected(true);
				}
				else {
					ckActive.setSelected(false);
				}
			}
		});
		scrollPane.setViewportView(tblLinhKien);
		
		JButton btnAdd = new JButton("Th\u00EAm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date;
				String sql=  "insert into LinhKien(MaLK, TenLK, LoaiLK,HangSX,DonGia,NhaCungCap,SoLuong,MoTa,TGBH,NgayCapNhat,Active) values (?,?,?,?,?,?,?,?,?,?,?)";
				connect = new ConnectionDB();
				pst =connect.ThucThi(sql);
				try {
					pst.setString(1,txtMaLK.getText().toString());
					pst.setString(2,txtTenLK.getText().toString());
					pst.setString(3,(String)cmbLoai.getSelectedItem());
					pst.setString(4, (String)cmbHangSX.getSelectedItem());
					pst.setFloat(5,Float.parseFloat(txtDonGia.getText().toString()));
					pst.setString(6,(String)cmbNCC.getSelectedItem());
					pst.setInt(7, Integer.parseInt(txtSoLuong.getText().toString()));
					pst.setString(8,txtMoTa.getText().toString());
					pst.setInt(9,Integer.parseInt(txtBaoHanh.getText().toString()));
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					date =sdf.format(dcNgayCapNhat.getDate());
					pst.setString(10,date);
					pst.setBoolean(11,ckActive.isSelected());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Them Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblLinhKien.getModel();
				model.setRowCount(0);
				LoadLinhKien();
			}
		});
		btnAdd.setBounds(10, 534, 66, 21);
		contentPane.add(btnAdd);
		
		JButton btnEdit = new JButton("S\u1EEDa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect = new ConnectionDB();
				String sql = "Update LinhKien set TenLK=? ,LoaiLK=? ,HangSX=? ,NhaCungCap=?,DonGia=?,SoLuong=? ,MoTa=? ,TGBH=?,NgayCapNhat=?, Active=? where MaLK=?";
				pst =connect.ThucThi(sql);
				int ac;
				String date;
				try {					
					pst.setString(1,txtTenLK.getText());
					pst.setString(2, cmbLoai.getSelectedItem().toString());
					pst.setString(3,cmbHangSX.getSelectedItem().toString());
					pst.setString(4,cmbNCC.getSelectedItem().toString());
					pst.setFloat(5,Float.parseFloat(txtDonGia.getText().toString()));
					pst.setInt(6,Integer.parseInt(txtSoLuong.getText().toString()));
					pst.setString(7,txtMoTa.getText());					
					pst.setInt(8,Integer.parseInt(txtBaoHanh.getText().toString()));
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					date =sdf.format(dcNgayCapNhat.getDate());
					pst.setString(9, date);
					if(ckActive.isSelected())
					{
						byte b = 1;
						pst.setByte(10, b);
					}
					else
					{
						byte b = 0;
						pst.setByte(10, b);
					}
					pst.setString(11,txtMaLK.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"sua thanh cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblLinhKien.getModel();
				model.setRowCount(0);
				LoadLinhKien();
			}
		});
		btnEdit.setBounds(94, 534, 58, 21);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("X\u00F3a");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM LinhKien WHERE MaLK = ?";
				connect = new ConnectionDB();
				pst =connect.ThucThi(sql);
				try
				{
					pst.setString(1,txtMaLK.getText().toString());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Xoa Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblLinhKien.getModel();
				model.setRowCount(0);
				LoadLinhKien();
			}
		});
		btnDelete.setBounds(177, 534, 66, 21);
		contentPane.add(btnDelete);
		
		cmbNCC = new JComboBox();
		cmbNCC.setBounds(108, 294, 135, 21);
		contentPane.add(cmbNCC);
		
		cmbHangSX = new JComboBox();
		cmbHangSX.setBounds(108, 189, 135, 21);
		contentPane.add(cmbHangSX);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(1015, 123, 326, 316);
		contentPane.add(scrollPane_1);
		
		tblLoaiLK = new JTable();
		tblLoaiLK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblLoaiLK.getSelectedRow();
				cmbLoai.setSelectedItem(tblLoaiLK.getModel().getValueAt(row,0));
			}
		});
		scrollPane_1.setViewportView(tblLoaiLK);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(508, 483, 316, 260);
		contentPane.add(scrollPane_2);
		
		tblHangSX = new JTable();
		tblHangSX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblHangSX.getSelectedRow();
				cmbHangSX.setSelectedItem(tblHangSX.getModel().getValueAt(row,0));
			}
		});
		scrollPane_2.setViewportView(tblHangSX);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(980, 515, 333, 243);
		contentPane.add(scrollPane_3);
		
		tblNCC = new JTable();
		tblNCC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblNCC.getSelectedRow();
				cmbNCC.setSelectedItem(tblNCC.getModel().getValueAt(row,0));
			}
		});
		scrollPane_3.setViewportView(tblNCC);
		
		JLabel lblNewLabel_6 = new JLabel("H\u00E3ng S\u1EA3n Xu\u1EA5t");
		lblNewLabel_6.setBounds(592, 468, 104, 13);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Lo\u1EA1i S\u1EA3n Ph\u1EA9m");
		lblNewLabel_7.setBounds(1120, 107, 111, 13);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Nh\u00E0 Cung C\u1EA5p");
		lblNewLabel_8.setBounds(1102, 499, 104, 13);
		contentPane.add(lblNewLabel_8);
		
		dcNgayCapNhat = new JDateChooser();
		dcNgayCapNhat.setBounds(108, 432, 135, 19);
		contentPane.add(dcNgayCapNhat);
		
		JLabel lblNewLabel_9 = new JLabel("S\u1ED1 l\u01B0\u1EE3ng");
		lblNewLabel_9.setBounds(10, 259, 66, 13);
		contentPane.add(lblNewLabel_9);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(108, 256, 135, 19);
		contentPane.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		JButton btnSearch = new JButton("T\u00ECm Ki\u1EBFm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbLoaiTK.getSelectedItem().equals("MaLK"))
				{
					DefaultTableModel model = (DefaultTableModel) tblLinhKien.getModel();
					model.setRowCount(0);
					String caulenhSQL = "Select LinhKien.MaLK, LinhKien.TenLK,LoaiLK.TenLoai,HangSX.TenHangSX,NhaCungCap.TenNCC,LinhKien.DonGia,LinhKien.SoLuong,LinhKien.MoTa,LinhKien.TGBH,LinhKien.NgayCapNhat,LinhKien.Active from LinhKien,LoaiLK,NhaCungCap,HangSX"
							+ " where LinhKien.LoaiLK = LoaiLK.MaLoai and LinhKien.NhaCungCap = NhaCungCap.MaNCC"
							+ " and LinhKien.HangSX = HangSX.MaHangSX and  LinhKien.MaLK LIKE '%" + txtSearch.getText() +"%'";
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
						String malk, tenloai, tenlk ,hangsx,ncc,dongia,soluong,mota,tgbh,ngaycapnhat,active;
						while(rs.next())
						{
							dateformat = new SimpleDateFormat("dd-MM-yyyy");
							malk=rs.getString(1);
							tenlk = rs.getString(2);
							tenloai=rs.getString(3);
							hangsx = rs.getString(4);
							ncc =rs.getString(5);
							dongia=rs.getString(6);
							soluong = rs.getString(7);
							mota =rs.getString(8);
							tgbh =rs.getString(9);
							date =rs.getDate(10);
							ngaycapnhat = dateformat.format(date);
							active= rs.getString(11);
							String[] row = {malk,tenlk,tenloai,hangsx,ncc,dongia,soluong,mota,tgbh,ngaycapnhat,active};
							model.addRow(row);
						}
						
					
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				if(cmbLoaiTK.getSelectedItem().equals("TenLK"))
				{
					DefaultTableModel model = (DefaultTableModel) tblLinhKien.getModel();
					model.setRowCount(0);
					String caulenhSQL = "Select LinhKien.MaLK, LinhKien.TenLK,LoaiLK.TenLoai,HangSX.TenHangSX,NhaCungCap.TenNCC,LinhKien.DonGia,LinhKien.SoLuong,LinhKien.MoTa,LinhKien.TGBH,LinhKien.NgayCapNhat,LinhKien.Active from LinhKien,LoaiLK,NhaCungCap,HangSX"
							+ " where LinhKien.LoaiLK = LoaiLK.MaLoai and LinhKien.NhaCungCap = NhaCungCap.MaNCC"
							+ " and LinhKien.HangSX = HangSX.MaHangSX and  LinhKien.TenLK LIKE '%" + txtSearch.getText() +"%'";
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
						String malk, tenloai, tenlk ,hangsx,ncc,dongia,soluong,mota,tgbh,ngaycapnhat,active;
						while(rs.next())
						{
							dateformat = new SimpleDateFormat("dd-MM-yyyy");
							malk=rs.getString(1);
							tenlk = rs.getString(2);
							tenloai=rs.getString(3);
							hangsx = rs.getString(4);
							ncc =rs.getString(5);
							dongia=rs.getString(6);
							soluong = rs.getString(7);
							mota =rs.getString(8);
							tgbh =rs.getString(9);
							date =rs.getDate(10);
							ngaycapnhat = dateformat.format(date);
							active= rs.getString(11);
							String[] row = {malk,tenlk,tenloai,hangsx,ncc,dongia,soluong,mota,tgbh,ngaycapnhat,active};
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
		btnSearch.setBounds(142, 21, 101, 21);
		contentPane.add(btnSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(376, 22, 448, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("\u0110\u01A1n Gi\u00E1");
		lblNewLabel_10.setBounds(10, 236, 88, 13);
		contentPane.add(lblNewLabel_10);
		
		txtDonGia = new JTextField();
		txtDonGia.setBounds(108, 227, 135, 19);
		contentPane.add(txtDonGia);
		txtDonGia.setColumns(10);
		
		cmbLoaiTK = new JComboBox();
		cmbLoaiTK.setModel(new DefaultComboBoxModel(new String[] {"MaLK", "TenLK"}));
		cmbLoaiTK.setBounds(264, 21, 72, 21);
		contentPane.add(cmbLoaiTK);
		fillLoai();
		fillNhaCungCap();
		fillHangSX();
	}
}
