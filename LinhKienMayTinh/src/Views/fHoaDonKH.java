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

import com.toedter.calendar.JDateChooser;

import Controller.ConnectionDB;

import javax.swing.JTextField;
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
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class fHoaDonKH extends JFrame {

	private JPanel contentPane;
	private JTextField txtMHD;
	private JTextField txtMKH;
	private JTextField txtMNV;
	private JTable tblHoaDon;
	
	ConnectionDB connect;
	PreparedStatement pst;
	Date date, date2;
	DateFormat dateformat, dateformat2;
	SimpleDateFormat sdf ,sdf2;
	JDateChooser dcNgayLap;
	JDateChooser dcNgayGiao;
	JComboBox cmbMaLK;
	JLabel lbThanhTien;
	JComboBox cmbSearch;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JTable tblLK;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fHoaDonKH frame = new fHoaDonKH();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void loadLK()
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
	
	public void fillLK()
	{
		connect = new ConnectionDB();
		String caulenhSQL = "Select * from LinhKien ";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {
			while(rs.next())
			{
				cmbMaLK.addItem(rs.getString("MaLK"));			
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
	}
	
	public void loadHDKH()
	{
		connect = new ConnectionDB();
		String caulenhSQL ="Select HD.MaHD,KH.TenKH,NV.TenNV,LK.TenLK,LK.DonGia,HD.SoLuong,HD.ThanhTien,H.NgayLapHD,H.NgayGiao from CTHD HD, HoaDon H,LinhKien LK, NhanVien NV,KhachHang KH "
				+ "where HD.MaHD=H.MaHD and HD.MaLK=LK.MaLK and HD.MaNV=NV.MaNV and KH.MaKH=HD.MaKH";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {			
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
			
			int cols =rsmd.getColumnCount();
			String[] colname =new String[cols];
			for(int i=0;i<cols;i++)
			{
				colname[i] =rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colname);
			}
			String mahd, makh,malk,manv, dongia,soluong, ngaylap,ngaygiao,thanhtien;
			while(rs.next())
			{	
				dateformat = new SimpleDateFormat("dd-MM-yyyy");
				dateformat2 = new SimpleDateFormat("dd-MM-yyyy");
				mahd=rs.getString(1);
				makh = rs.getString(2);
				manv = rs.getString(3);
				malk=rs.getString(4);
				dongia=rs.getString(5);
				soluong=rs.getString(6);
				thanhtien = rs.getString(7);
				date =rs.getDate(8);				
				ngaylap = dateformat.format(date);
				date2 =rs.getDate(9);
				ngaygiao =dateformat2.format(date2);				
				String[] row = {mahd,makh,manv,malk,dongia,soluong,thanhtien,ngaylap,ngaygiao};
				model.addRow(row);
			}
			
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void DeleteHDKH()
	{
		String sql = "DELETE FROM CTHD WHERE MaHD = ?";
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
	public void UpdateHoaDon()
	{
		connect = new ConnectionDB();
		String sql = "Update CTHD set MaKH=?,MaLK=?,MaNV=?,SoLuong=?,ThanhTien=? where MaHD=?";
		pst =connect.ThucThi(sql);
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			pst.setString(1,txtMKH.getText().toString());
			pst.setString(2,cmbMaLK.getSelectedItem().toString());
			pst.setString(3,txtMNV.getText().toString());
			pst.setInt(4,Integer.parseInt(txtSoLuong.getText().toString()));
			pst.setFloat(5,Float.parseFloat(lbThanhTien.getText().toString()));			
			pst.setString(6,txtMHD.getText().toString());
			pst.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void InsertCTHD()
	{
		String sql=  "insert into CTHD(MaHD,MaKH,MaLK,MaNV,SoLuong,ThanhTien) values (?,?,?,?,?,?)";
		connect = new ConnectionDB();
		pst =connect.ThucThi(sql);
		try {
			pst.setString(1,txtMHD.getText().toString());
			pst.setString(2,txtMKH.getText().toString());
			pst.setString(3,cmbMaLK.getSelectedItem().toString());
			pst.setString(4,txtMNV.getText().toString());
			pst.setInt(5,Integer.parseInt(txtSoLuong.getText().toString()));						
			pst.setFloat(6, Float.parseFloat(lbThanhTien.getText().toString()));		
			pst.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public fHoaDonKH() {
		setTitle("H\u00F3a \u0110\u01A1n Kh\u00E1ch H\u00E0ng");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				loadHDKH();
				loadLK();
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1316, 672);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("M\u00E3 H\u00F3a \u0110\u01A1n");
		lblNewLabel.setBounds(10, 115, 92, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblMKhchHng = new JLabel("M\u00E3 Kh\u00E1ch H\u00E0ng");
		lblMKhchHng.setBounds(10, 184, 92, 13);
		contentPane.add(lblMKhchHng);
		
		JLabel lblMNhnVin = new JLabel("M\u00E3 Nh\u00E2n Vi\u00EAn");
		lblMNhnVin.setBounds(10, 245, 92, 13);
		contentPane.add(lblMNhnVin);
		
		JLabel lblNgyLp = new JLabel("Ng\u00E0y L\u1EADp");
		lblNgyLp.setBounds(10, 469, 92, 13);
		contentPane.add(lblNgyLp);
		
		JLabel lblNgyGiao = new JLabel("Ng\u00E0y Giao");
		lblNgyGiao.setBounds(10, 527, 92, 13);
		contentPane.add(lblNgyGiao);
		
		dcNgayGiao = new JDateChooser();
		dcNgayGiao.setBounds(103, 521, 140, 19);
		contentPane.add(dcNgayGiao);
		
		dcNgayLap = new JDateChooser();
		dcNgayLap.setBounds(103, 463, 140, 19);
		contentPane.add(dcNgayLap);
		
		txtMHD = new JTextField();
		txtMHD.setBounds(103, 112, 140, 19);
		contentPane.add(txtMHD);
		txtMHD.setColumns(10);
		
		txtMKH = new JTextField();
		txtMKH.setColumns(10);
		txtMKH.setBounds(103, 178, 140, 19);
		contentPane.add(txtMKH);
		
		txtMNV = new JTextField();
		txtMNV.setColumns(10);
		txtMNV.setBounds(103, 242, 140, 19);
		contentPane.add(txtMNV);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 98, 663, 356);
		contentPane.add(scrollPane);
		
		tblHoaDon = new JTable();
		tblHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblHoaDon.getSelectedRow();
				TableModel model =tblHoaDon.getModel();
				txtMHD.setText(model.getValueAt(row, 0).toString());
				txtSoLuong.setText(model.getValueAt(row,5).toString());
				txtDonGia.setText(model.getValueAt(row,6).toString());
				try {
					date = new SimpleDateFormat("dd-MM-yyyy").parse((String)model.getValueAt(row,7));
					dcNgayLap.setDate(date);
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				try {

					date2 = new SimpleDateFormat("dd-MM-yyyy").parse((String)model.getValueAt(row,8));
					dcNgayGiao.setDate(date2);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		scrollPane.setViewportView(tblHoaDon);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql=  "insert into HoaDon(MaHD,NgayLapHD,NgayGiao) values (?,?,?)";
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
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				InsertCTHD();
				DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
				model.setRowCount(0);
				loadHDKH();
			}
		});
		btnThem.setBounds(10, 581, 79, 21);
		contentPane.add(btnThem);
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date;
				connect = new ConnectionDB();
				String sql = "Update HoaDon set NgayLapHD=? ,NgayGiao=? where MaHD=?";
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
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				UpdateHoaDon();
				DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
				model.setRowCount(0);
				loadHDKH();
			}
		});
		btnSua.setBounds(103, 581, 79, 21);
		contentPane.add(btnSua);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteHDKH();
				String sql = "DELETE FROM HoaDon WHERE MaHD = ?";
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
				DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
				model.setRowCount(0);
				loadHDKH();
			}
		});
		btnXoa.setBounds(192, 581, 78, 21);
		contentPane.add(btnXoa);
		
		JLabel lblMLinhKin = new JLabel("M\u00E3 linh ki\u1EC7n");
		lblMLinhKin.setBounds(10, 312, 92, 13);
		contentPane.add(lblMLinhKin);
		
		cmbMaLK = new JComboBox();
		cmbMaLK.setBounds(103, 308, 134, 21);
		contentPane.add(cmbMaLK);
		
		JLabel lblnGi = new JLabel("\u0110\u01A1n gi\u00E1");
		lblnGi.setBounds(10, 376, 92, 13);
		contentPane.add(lblnGi);
		
		txtDonGia = new JTextField();
		txtDonGia.setBounds(103, 373, 134, 19);
		contentPane.add(txtDonGia);
		txtDonGia.setColumns(10);
		
		JLabel lblSLung = new JLabel("S\u1ED1 l\u01B0\u1EE3ng");
		lblSLung.setBounds(10, 420, 92, 13);
		contentPane.add(lblSLung);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(103, 417, 134, 19);
		contentPane.add(txtSoLuong);
		
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
		btnThanhTien.setBounds(280, 465, 107, 21);
		contentPane.add(btnThanhTien);
		
		lbThanhTien = new JLabel("");
		lbThanhTien.setBounds(386, 463, 79, 21);
		contentPane.add(lbThanhTien);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(1001, 244, 291, 297);
		contentPane.add(scrollPane_1);
		
		tblLK = new JTable();
		tblLK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblLK.getSelectedRow();
				cmbMaLK.setSelectedItem(tblLK.getModel().getValueAt(row,0));
			}
		});
		scrollPane_1.setViewportView(tblLK);
		
		JButton btnSearch = new JButton("T\u00ECm Ki\u1EBFm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbSearch.getSelectedItem().equals("MaHD"))
				{
					DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
					model.setRowCount(0);
					connect = new ConnectionDB();
					String caulenhSQL ="Select HD.MaHD,KH.TenKH,NV.TenNV,LK.TenLK,LK.DonGia,HD.SoLuong,HD.ThanhTien,H.NgayLapHD,H.NgayGiao from CTHD HD, HoaDon H,LinhKien LK, NhanVien NV,KhachHang KH "
							+ "where HD.MaHD=H.MaHD and HD.MaLK=LK.MaLK and HD.MaNV=NV.MaNV and KH.MaKH=HD.MaKH and HD.MaHD LIKE '%" + txtSearch.getText() +"%'";
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
						String mahd, makh,malk,manv, dongia,soluong, ngaylap,ngaygiao,thanhtien;
						while(rs.next())
						{	
							dateformat = new SimpleDateFormat("dd-MM-yyyy");
							dateformat2 = new SimpleDateFormat("dd-MM-yyyy");
							mahd=rs.getString(1);
							makh = rs.getString(2);
							manv = rs.getString(3);
							malk=rs.getString(4);
							dongia=rs.getString(5);
							soluong=rs.getString(6);
							thanhtien = rs.getString(7);
							date =rs.getDate(8);				
							ngaylap = dateformat.format(date);
							date2 =rs.getDate(9);
							ngaygiao =dateformat2.format(date2);				
							String[] row = {mahd,makh,manv,malk,dongia,soluong,thanhtien,ngaylap,ngaygiao};
							model.addRow(row);
						}
						
					
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				if(cmbSearch.getSelectedItem().equals("TenKH"))
				{
					DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
					model.setRowCount(0);
					connect = new ConnectionDB();
					String caulenhSQL ="Select HD.MaHD,KH.TenKH,NV.TenNV,LK.TenLK,LK.DonGia,HD.SoLuong,HD.ThanhTien,H.NgayLapHD,H.NgayGiao from CTHD HD, HoaDon H,LinhKien LK, NhanVien NV,KhachHang KH "
							+ "where HD.MaHD=H.MaHD and HD.MaLK=LK.MaLK and HD.MaNV=NV.MaNV and KH.MaKH=HD.MaKH and KH.TenKH LIKE '%" + txtSearch.getText() +"%'";
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
						String mahd, makh,malk,manv, dongia,soluong, ngaylap,ngaygiao,thanhtien;
						while(rs.next())
						{	
							dateformat = new SimpleDateFormat("dd-MM-yyyy");
							dateformat2 = new SimpleDateFormat("dd-MM-yyyy");
							mahd=rs.getString(1);
							makh = rs.getString(2);
							manv = rs.getString(3);
							malk=rs.getString(4);
							dongia=rs.getString(5);
							soluong=rs.getString(6);
							thanhtien = rs.getString(7);
							date =rs.getDate(8);				
							ngaylap = dateformat.format(date);
							date2 =rs.getDate(9);
							ngaygiao =dateformat2.format(date2);				
							String[] row = {mahd,makh,manv,malk,dongia,soluong,thanhtien,ngaylap,ngaygiao};
							model.addRow(row);
						}
						
					
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				if(cmbSearch.getSelectedItem().equals("MaNV"))
				{
					DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
					model.setRowCount(0);
					connect = new ConnectionDB();
					String caulenhSQL ="Select HD.MaHD,KH.TenKH,NV.TenNV,LK.TenLK,LK.DonGia,HD.SoLuong,HD.ThanhTien,H.NgayLapHD,H.NgayGiao from CTHD HD, HoaDon H,LinhKien LK, NhanVien NV,KhachHang KH "
							+ "where HD.MaHD=H.MaHD and HD.MaLK=LK.MaLK and HD.MaNV=NV.MaNV and KH.MaKH=HD.MaKH and NV.MaNV LIKE '%" + txtSearch.getText() +"%'";
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
						String mahd, makh,malk,manv, dongia,soluong, ngaylap,ngaygiao,thanhtien;
						while(rs.next())
						{	
							dateformat = new SimpleDateFormat("dd-MM-yyyy");
							dateformat2 = new SimpleDateFormat("dd-MM-yyyy");
							mahd=rs.getString(1);
							makh = rs.getString(2);
							manv = rs.getString(3);
							malk=rs.getString(4);
							dongia=rs.getString(5);
							soluong=rs.getString(6);
							thanhtien = rs.getString(7);
							date =rs.getDate(8);				
							ngaylap = dateformat.format(date);
							date2 =rs.getDate(9);
							ngaygiao =dateformat2.format(date2);				
							String[] row = {mahd,makh,manv,malk,dongia,soluong,thanhtien,ngaylap,ngaygiao};
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
		btnSearch.setBounds(192, 48, 85, 21);
		contentPane.add(btnSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(375, 49, 267, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		cmbSearch = new JComboBox();
		cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"MaHD", "TenKH", "MaNV"}));
		cmbSearch.setBounds(290, 48, 63, 21);
		contentPane.add(cmbSearch);
		
		JLabel lblNewLabel_1 = new JLabel("B\u1EA3ng H\u00F3a \u0110\u01A1n Kh\u00E1ch H\u00E0ng");
		lblNewLabel_1.setBounds(505, 85, 164, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("B\u1EA3ng Linh Ki\u1EC7n");
		lblNewLabel_1_1.setBounds(1103, 225, 140, 13);
		contentPane.add(lblNewLabel_1_1);
		fillLK();
	}
}
