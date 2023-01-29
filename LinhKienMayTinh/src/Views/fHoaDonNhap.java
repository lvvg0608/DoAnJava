package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Controller.ConnectionDB;

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
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class fHoaDonNhap extends JFrame {

	private JPanel contentPane;
	private JTextField txtMHD;
	
	ConnectionDB connect;
	PreparedStatement pst;
	Date date, date2;
	DateFormat dateformat, dateformat2;
	SimpleDateFormat sdf ,sdf2;
	JDateChooser dcNgayLap;
	JDateChooser dcNgayGiao;
	JComboBox cmbLK;
	JComboBox cmbNCC;
	JLabel lbThanhTien;
	JComboBox cmbSearch;
	private JTable tblCTHD;
	private JTextField txtMNV;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JTable tblLK;
	private JTable tblNCC;
	private JTextField txtSearch;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fHoaDonNhap frame = new fHoaDonNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
		
		
		public void LoadLinhKien()
		{
			connect = new ConnectionDB();
			String caulenhSQL = "Select LinhKien.MaLK, LinhKien.TenLK from LinhKien,LoaiLK,NhaCungCap,HangSX"
					+ " where LinhKien.LoaiLK = LoaiLK.MaLoai and LinhKien.NhaCungCap = NhaCungCap.MaNCC"
					+ " and LinhKien.HangSX = HangSX.MaHangSX";
			ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
			try {			
				ResultSetMetaData rsmd = rs.getMetaData();
				DefaultTableModel model = (DefaultTableModel) tblLK.getModel();
				
				int cols =rsmd.getColumnCount();
				String[] colname =new String[cols];
				for(int i=0;i<cols;i++)
				{
					colname[i] =rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colname);
				}
				String malk,tenlk;
				while(rs.next())
				{
					malk=rs.getString(1);
					tenlk = rs.getString(2);
	
					String[] row = {malk,tenlk};
					model.addRow(row);
				}
				
			
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
	}
	
	public void AddHoaDon()
	{
		String sql=  "insert into ChiTietGiao( MaHDN,MaLK,MaNCC,MaNV,SoLuong,DonGia,ThanhTien) values (?,?,?,?,?,?,?)";
		connect = new ConnectionDB();
		pst =connect.ThucThi(sql);
		String date ,date2;
		int soluong;
		float dongia;
		try {	
			pst.setString(1,txtMHD.getText().toString());
			pst.setString(2,cmbLK.getSelectedItem().toString());
			pst.setString(3,cmbNCC.getSelectedItem().toString());
			pst.setString(4,txtMNV.getText().toString());
			pst.setInt(5,Integer.parseInt(txtSoLuong.getText().toString()));
			pst.setFloat(6,Float.parseFloat(txtDonGia.getText().toString()));
			pst.setFloat(7,Float.parseFloat(lbThanhTien.getText().toString()));
			pst.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}
	
	public void UpdateHoaDon()
	{
		connect = new ConnectionDB();
		String sql = "Update ChiTietGiao set MaLK=?,MaNCC=?,MaNV=?,SoLuong=? ,DonGia=?,ThanhTien=? where MaHDN=?";
		pst =connect.ThucThi(sql);
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			pst.setString(1,cmbLK.getSelectedItem().toString());
			pst.setString(2,cmbNCC.getSelectedItem().toString());
			pst.setString(3,txtMNV.getText().toString());
			pst.setInt(4,Integer.parseInt(txtSoLuong.getText().toString()));
			pst.setFloat(5,Float.parseFloat(txtDonGia.getText().toString()));
			pst.setFloat(6,Float.parseFloat(lbThanhTien.getText().toString()));
			pst.setString(7,txtMHD.getText().toString());
			pst.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void loadCTHD()
	{
		connect = new ConnectionDB();
		String caulenhSQL ="Select  CTG.MaHDN ,LK.TenLK,NCC.TenNCC,NV.TenNV,HDNH.NgayLapHD,HDNH.NgayGiao,CTG.DonGia,CTG.SoLuong,CTG.DonGia * CTG.SoLuong AS \"Thanh Tien\" from ChiTietGiao CTG,LinhKien LK,NhaCungCap NCC,NhanVien NV,HoaDonNhapHang HDNH where "
				+ " CTG.MaHDN= HDNH.MaHDN and CTG.MaLK=LK.MaLK and CTG.MaNCC=NCC.MaNCC and CTG.MaNV=NV.MaNV";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {			
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblCTHD.getModel();
			
			int cols =rsmd.getColumnCount();
			String[] colname =new String[cols];
			for(int i=0;i<cols;i++)
			{
				colname[i] =rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colname);
			}
			String mahdn, tenlk,tenncc,tennv,dongia,ngaylap,ngaygiao,soluong,thanhtien;
			while(rs.next())
			{	
				dateformat = new SimpleDateFormat("dd-MM-yyyy");
				dateformat2 = new SimpleDateFormat("dd-MM-yyyy");
				mahdn=rs.getString(1);
				tenlk= rs.getString(2);
				tenncc= rs.getString(3);
				tennv= rs.getString(4);
				
				date =rs.getDate(5);
				ngaylap = dateformat.format(date);
				date2 =rs.getDate(6);
				ngaygiao =dateformat2.format(date2);				
				soluong= rs.getString(7);
				dongia = rs.getString(8);
				thanhtien = rs.getString(9);
				String[] row = {mahdn,tenlk,tenncc,tennv,ngaylap,ngaygiao,soluong,dongia,thanhtien};
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
	public void fillMaLK()
	{
		connect = new ConnectionDB();
		String caulenhSQL = "Select * from LinhKien ";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {
			while(rs.next())
			{
				cmbLK.addItem(rs.getString("MaLK"));			
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
	}
	public void DeleteHoaDon()
	{
		String sql = "DELETE FROM ChiTietGiao WHERE MaHDN = ?";
		connect = new ConnectionDB();
		pst =connect.ThucThi(sql);
		try
		{
			pst.setString(1,txtMHD.getText().toString());
			pst.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}
	
	public void loadNhaCungCap()
	{
		connect = new ConnectionDB();
		String caulenhSQL = "Select NCC.MaNCC, NCC.TenNCC From NhaCungCap NCC";
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
			String mancc,tenncc;
			while(rs.next())
			{
				mancc=rs.getString(1);
				tenncc = rs.getString(2);

				String[] row = {mancc,tenncc};
				model.addRow(row);
			}
			
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void fillNCC()
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
	
	public fHoaDonNhap() {
		setTitle("H\u00F3a \u0110\u01A1n Nh\u1EADp H\u00E0ng");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				loadCTHD();
				LoadLinhKien();
				loadNhaCungCap();
			}
						
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1320, 792);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("M\u00E3 H\u00F3a \u0110\u01A1n");
		lblNewLabel.setBounds(10, 108, 88, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNgyLp = new JLabel("Ng\u00E0y L\u1EADp");
		lblNgyLp.setBounds(10, 393, 88, 13);
		contentPane.add(lblNgyLp);
		
		JLabel lblNgyGiao = new JLabel("Ng\u00E0y Giao");
		lblNgyGiao.setBounds(10, 437, 88, 13);
		contentPane.add(lblNgyGiao);
		
		txtMHD = new JTextField();
		txtMHD.setBounds(108, 105, 137, 19);
		contentPane.add(txtMHD);
		txtMHD.setColumns(10);
		
		dcNgayLap = new JDateChooser();
		dcNgayLap.setBounds(108, 387, 137, 19);
		contentPane.add(dcNgayLap);
		
		dcNgayGiao = new JDateChooser();
		dcNgayGiao.setBounds(108, 431, 137, 19);
		contentPane.add(dcNgayGiao);
		
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql=  "insert into HoaDonNhapHang(MaHDN, NgayLapHD,NgayGiao) values (?,?,?)";
				connect = new ConnectionDB();
				pst =connect.ThucThi(sql);
				String date ,date2;
				try {
					pst.setString(1,txtMHD.getText().toString());				
					try {
						sdf = new SimpleDateFormat("yyyy-MM-dd");
						date =sdf.format(dcNgayLap.getDate());
						pst.setString(2,date);
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					try {
						sdf2 = new SimpleDateFormat("yyyy-MM-dd");
						date2 =sdf.format(dcNgayGiao.getDate());
						pst.setString(3,date2);	
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}					
							
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Them Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}	
				AddHoaDon();
				DefaultTableModel model = (DefaultTableModel) tblCTHD.getModel();
				model.setRowCount(0);
				loadCTHD();
				
			}
		});
		btnThem.setBounds(10, 480, 59, 21);
		contentPane.add(btnThem);
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date;
				connect = new ConnectionDB();
				String sql = "Update HoaDonNhapHang set NgayLapHD=? ,NgayGiao=? where MaHDN=?";
				pst =connect.ThucThi(sql);
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				try {
					try {
						date =sdf.format(dcNgayLap.getDate());
						pst.setString(1, date);
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					try {
						date =sdf.format(dcNgayGiao.getDate());
						pst.setString(2, date);
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					pst.setString(3, txtMHD.getText().toString());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Sua Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				UpdateHoaDon();
				DefaultTableModel model = (DefaultTableModel) tblCTHD.getModel();
				model.setRowCount(0);
				loadCTHD();
				
			}
		});
		btnSua.setBounds(97, 480, 59, 21);
		contentPane.add(btnSua);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteHoaDon();
				String sql = "DELETE FROM HoaDonNhapHang WHERE MaHDN = ?";
				connect = new ConnectionDB();
				pst =connect.ThucThi(sql);
				try
				{
					pst.setString(1,txtMHD.getText().toString());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Xoa Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblCTHD.getModel();
				model.setRowCount(0);
				loadCTHD();
			}
			
		});
		btnXoa.setBounds(186, 480, 59, 21);
		contentPane.add(btnXoa);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(363, 106, 563, 359);
		contentPane.add(scrollPane_1);
		
		tblCTHD = new JTable();
		tblCTHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblCTHD.getSelectedRow();
				TableModel model =tblCTHD.getModel();
				txtMHD.setText(model.getValueAt(row, 0).toString());
				txtDonGia.setText(model.getValueAt(row, 6).toString());
				try {
					date = new SimpleDateFormat("dd-MM-yyyy").parse((String)model.getValueAt(row,4));
					dcNgayLap.setDate(date);
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				try {

					date2 = new SimpleDateFormat("dd-MM-yyyy").parse((String)model.getValueAt(row,5));
					dcNgayGiao.setDate(date2);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				txtSoLuong.setText(model.getValueAt(row, 7).toString());
				lbThanhTien.setText(""+model.getValueAt(row,8));
				
			}
		});
		scrollPane_1.setViewportView(tblCTHD);
		
		JLabel lblNewLabel_1 = new JLabel("M\u00E3 linh ki\u1EC7n");
		lblNewLabel_1.setBounds(10, 159, 59, 13);
		contentPane.add(lblNewLabel_1);
		
		cmbLK = new JComboBox();
		cmbLK.setBounds(108, 155, 137, 21);
		contentPane.add(cmbLK);
		
		JLabel lblNewLabel_1_1 = new JLabel("M\u00E3 nh\u00E0 cung c\u1EA5p");
		lblNewLabel_1_1.setBounds(10, 219, 105, 13);
		contentPane.add(lblNewLabel_1_1);
		
		cmbNCC = new JComboBox();
		cmbNCC.setBounds(108, 215, 137, 21);
		contentPane.add(cmbNCC);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("M\u00E3 nh\u00E2n vi\u00EAn");
		lblNewLabel_1_1_1.setBounds(10, 267, 105, 13);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtMNV = new JTextField();
		txtMNV.setColumns(10);
		txtMNV.setBounds(108, 264, 137, 19);
		contentPane.add(txtMNV);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("\u0110\u01A1n gi\u00E1");
		lblNewLabel_1_1_1_1.setBounds(10, 317, 98, 13);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		txtDonGia = new JTextField();		
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(108, 314, 137, 19);
		contentPane.add(txtDonGia);
		
		JLabel lblNewLabel_2 = new JLabel("S\u1ED1 l\u01B0\u1EE3ng");
		lblNewLabel_2.setBounds(10, 359, 88, 13);
		contentPane.add(lblNewLabel_2);
		
		txtSoLuong = new JTextField();	
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(108, 358, 137, 19);
		contentPane.add(txtSoLuong);
		
		lbThanhTien = new JLabel("");
		lbThanhTien.setBounds(279, 523, 222, 21);
		contentPane.add(lbThanhTien);
		
		JButton btnThanhTien = new JButton("Th\u00E0nh Ti\u1EC1n");
		btnThanhTien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int soluong;
				float dongia,tong;
				if(txtDonGia.getText().equalsIgnoreCase("")||txtSoLuong.getText().equalsIgnoreCase(""))
				{
					lbThanhTien.setText("0 VND");
				}
				else {
					dongia= Float.parseFloat(txtDonGia.getText().toString());
					soluong=Integer.parseInt(txtSoLuong.getText().toString());
					tong=dongia * soluong;
					lbThanhTien.setText(""+tong);
				}
				
				
			}
		});
		btnThanhTien.setBounds(166, 523, 105, 21);
		contentPane.add(btnThanhTien);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1006, 266, 255, 235);
		contentPane.add(scrollPane);
		
		tblLK = new JTable();
		tblLK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblLK.getSelectedRow();
				cmbLK.setSelectedItem(tblLK.getModel().getValueAt(row,0));
			}
		});
		scrollPane.setViewportView(tblLK);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(671, 510, 255, 235);
		contentPane.add(scrollPane_2);
		
		tblNCC = new JTable();
		tblNCC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblNCC.getSelectedRow();
				cmbNCC.setSelectedItem(tblNCC.getModel().getValueAt(row,0));
			}
		});
		scrollPane_2.setViewportView(tblNCC);
		
		JButton btnSearch = new JButton("T\u00ECm Ki\u1EBFm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbSearch.getSelectedItem().equals("MaHD"))
				{
					DefaultTableModel model = (DefaultTableModel) tblCTHD.getModel();
					model.setRowCount(0);
					connect = new ConnectionDB();
					String caulenhSQL ="Select  CTG.MaHDN ,LK.TenLK,NCC.TenNCC,NV.TenNV,HDNH.NgayLapHD,HDNH.NgayGiao,CTG.DonGia,CTG.SoLuong,CTG.DonGia * CTG.SoLuong AS \"Thanh Tien\" from ChiTietGiao CTG,LinhKien LK,NhaCungCap NCC,NhanVien NV,HoaDonNhapHang HDNH where "
							+ " CTG.MaHDN= HDNH.MaHDN and CTG.MaLK=LK.MaLK and CTG.MaNCC=NCC.MaNCC and CTG.MaNV=NV.MaNV and CTG.MaHDN LIKE '%" + txtSearch.getText() +"%'";
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
						String mahdn, tenlk,tenncc,tennv,dongia,ngaylap,ngaygiao,soluong,thanhtien;
						while(rs.next())
						{	
							dateformat = new SimpleDateFormat("dd-MM-yyyy");
							dateformat2 = new SimpleDateFormat("dd-MM-yyyy");
							mahdn=rs.getString(1);
							tenlk= rs.getString(2);
							tenncc= rs.getString(3);
							tennv= rs.getString(4);
							
							date =rs.getDate(5);
							ngaylap = dateformat.format(date);
							date2 =rs.getDate(6);
							ngaygiao =dateformat2.format(date2);				
							soluong= rs.getString(7);
							dongia = rs.getString(8);
							thanhtien = rs.getString(9);
							String[] row = {mahdn,tenlk,tenncc,tennv,ngaylap,ngaygiao,soluong,dongia,thanhtien};
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
		btnSearch.setBounds(251, 43, 98, 21);
		contentPane.add(btnSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(462, 44, 306, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		cmbSearch = new JComboBox();
		cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"MaHD"}));
		cmbSearch.setBounds(363, 43, 68, 21);
		contentPane.add(cmbSearch);
		
		JLabel lblNewLabel_3 = new JLabel("B\u1EA3ng h\u00F3a \u0111\u01A1n nh\u1EADp");
		lblNewLabel_3.setBounds(572, 83, 118, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("B\u1EA3ng linh ki\u1EC7n");
		lblNewLabel_3_1.setBounds(1090, 243, 118, 13);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4 = new JLabel("B\u1EA3ng nh\u00E0 cung c\u1EA5p");
		lblNewLabel_4.setBounds(745, 488, 118, 13);
		contentPane.add(lblNewLabel_4);
		fillMaLK();	
		fillNCC();
	}
}
