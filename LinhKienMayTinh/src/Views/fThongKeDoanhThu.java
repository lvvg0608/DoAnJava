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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.text.NumberFormat;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class fThongKeDoanhThu extends JFrame {

	private JPanel contentPane;
	private JTable tblTKHD;
	ConnectionDB connect;
	PreparedStatement pst;
	Date date, date2;
	DateFormat dateformat, dateformat2;
	SimpleDateFormat sdf ,sdf2;
	JLabel lbDoanhThu;
	private JLabel lbDoanhSo;
	private JLabel lbTienVon;
	private JButton btnThongKe;
	private JTable tblHD;
	private JLabel lblNewLabel;
	private JLabel lblHanNhp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fThongKeDoanhThu frame = new fThongKeDoanhThu();
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
	
	public void loadHD()
	{
		connect = new ConnectionDB();
		String caulenhSQL ="Select  CTG.MaHDN ,LK.TenLK,NCC.TenNCC,NV.TenNV,HDNH.NgayLapHD,HDNH.NgayGiao,CTG.DonGia,CTG.SoLuong,CTG.DonGia * CTG.SoLuong AS \"Thanh Tien\" from ChiTietGiao CTG,LinhKien LK,NhaCungCap NCC,NhanVien NV,HoaDonNhapHang HDNH where "
				+ " CTG.MaHDN= HDNH.MaHDN and CTG.MaLK=LK.MaLK and CTG.MaNCC=NCC.MaNCC and CTG.MaNV=NV.MaNV";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {			
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblHD.getModel();
			
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
	
	public fThongKeDoanhThu() {
		setTitle("Th\u1ED1ng K\u00EA Doanh Thu");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				connect = new ConnectionDB();
				String caulenhSQL ="Select HD.MaHD,KH.TenKH,NV.TenNV,LK.TenLK,LK.DonGia,HD.SoLuong,HD.ThanhTien,H.NgayLapHD,H.NgayGiao from CTHD HD, HoaDon H,LinhKien LK, NhanVien NV,KhachHang KH "
						+ "where HD.MaHD=H.MaHD and HD.MaLK=LK.MaLK and HD.MaNV=NV.MaNV and KH.MaKH=HD.MaKH";
				ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
				try {			
					ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) tblTKHD.getModel();
					
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
				loadHD();
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1323, 627);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 33, 606, 317);
		contentPane.add(scrollPane);
		
		tblTKHD = new JTable();
		scrollPane.setViewportView(tblTKHD);
		
		lbDoanhThu = new JLabel("Doanh Thu");
		lbDoanhThu.setBounds(53, 422, 255, 33);
		contentPane.add(lbDoanhThu);
		
		lbDoanhSo = new JLabel("Doanh S\u1ED1");
		lbDoanhSo.setBounds(53, 465, 255, 33);
		contentPane.add(lbDoanhSo);
		
		lbTienVon = new JLabel("Ti\u1EC1n V\u1ED1n");
		lbTienVon.setBounds(53, 508, 255, 33);
		contentPane.add(lbTienVon);
		
		btnThongKe = new JButton("Th\u1ED1ng k\u00EA");
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numrow = tblTKHD.getRowCount();
				int nr = tblHD.getRowCount();
				double ds=0, ds1=0, ds2 = 0;
				
				for(int i = 0; i < numrow; i++) {
					double val = Double.valueOf(tblTKHD.getValueAt(i, 6).toString());
					ds += val;
				}
				
				for(int i = 0; i < nr; i++) {
					double val1 = Double.valueOf(tblHD.getValueAt(i, 8).toString());
					ds1 += val1;
				}
				
				ds2=ds-ds1;
				lbDoanhThu.setText("Doanh Thu: "+Double.toString(ds2));
				lbDoanhSo.setText("Doanh So: "+Double.toString(ds));
				lbTienVon.setText("Tien Von: "+Double.toString(ds1));
			}
		});
		btnThongKe.setBounds(611, 360, 139, 36);
		contentPane.add(btnThongKe);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(693, 34, 606, 317);
		contentPane.add(scrollPane_1);
		
		tblHD = new JTable();
		scrollPane_1.setViewportView(tblHD);
		
		lblNewLabel = new JLabel("H\u00F3a \u0110\u01A1n Kh\u00E1ch H\u00E0ng");
		lblNewLabel.setBounds(242, 10, 178, 13);
		contentPane.add(lblNewLabel);
		
		lblHanNhp = new JLabel("H\u00F3a \u0110\u01A1n Nh\u1EADp H\u00E0ng");
		lblHanNhp.setBounds(943, 11, 178, 13);
		contentPane.add(lblHanNhp);
	}
}
