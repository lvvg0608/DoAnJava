package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.ConnectionDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.SystemColor;

public class fAllStaff extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTable tblNhanVien;
	
	ConnectionDB connect;
	PreparedStatement pst;
	JRadioButton rdNam;
	JRadioButton rdNu;
	ButtonGroup bg = new ButtonGroup();
	SimpleDateFormat sdf;
	JDateChooser dcNgaySinh;
	Date date;
	DateFormat dateformat;
	JComboBox cmbSearch ;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fAllStaff frame = new fAllStaff();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void loadStaff()
	{
		connect = new ConnectionDB();
		String caulenhSQL ="Select * from NhanVien";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {			
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
			
			int cols =rsmd.getColumnCount();
			String[] colname =new String[cols];
			for(int i=0;i<cols;i++)
			{
				colname[i] =rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colname);
			}
			String manv, tennv,gt,ns,sdt,dc;
			while(rs.next())
			{
				dateformat = new SimpleDateFormat("dd-MM-yyyy");		
				manv=rs.getString(1);
				tennv = rs.getString(2);
				gt=rs.getString(3);
				date =rs.getDate(4);
				ns = dateformat.format(date);
				sdt =rs.getString(5);
				dc =rs.getString(6);
				String[] row = {manv,tennv,gt,ns,sdt,dc};
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
	public fAllStaff() {
		setTitle("Danh S\u00E1ch Nh\u00E2n Vi\u00EAn");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				loadStaff();
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1007, 539);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("M\u00E3 Nh\u00E2n Vi\u00EAn");
		lblNewLabel.setBounds(10, 92, 71, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EAn Nh\u00E2n Vi\u00EAn");
		lblNewLabel_1.setBounds(10, 146, 71, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gi\u00F3i T\u00EDnh");
		lblNewLabel_2.setBounds(10, 200, 71, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Ng\u00E0y Sinh");
		lblNewLabel_3.setBounds(10, 252, 71, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("S\u0110T");
		lblNewLabel_4.setBounds(10, 315, 71, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u0110\u1ECBa Ch\u1EC9");
		lblNewLabel_5.setBounds(10, 368, 71, 13);
		contentPane.add(lblNewLabel_5);
		
		txtMaNV = new JTextField();
		txtMaNV.setBounds(122, 89, 141, 19);
		contentPane.add(txtMaNV);
		txtMaNV.setColumns(10);
		
		txtTenNV = new JTextField();
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(122, 143, 141, 19);
		contentPane.add(txtTenNV);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(122, 312, 141, 19);
		contentPane.add(txtSDT);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(122, 365, 141, 19);
		contentPane.add(txtDiaChi);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(308, 92, 625, 300);
		contentPane.add(scrollPane);
		
		tblNhanVien = new JTable();
		tblNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableModel model =tblNhanVien.getModel();
				int row = tblNhanVien.getSelectedRow();
				txtMaNV.setText(tblNhanVien.getModel().getValueAt(row, 0).toString());
				txtTenNV.setText(tblNhanVien.getModel().getValueAt(row, 1).toString());
				String gt = tblNhanVien.getModel().getValueAt(row,2).toString();
				if(gt.equals("Nam"))
				{
					rdNam.setSelected(true);
				}
				else {
					rdNu.setSelected(true);
				}				
				try {
					int srow = tblNhanVien.getSelectedRow();
					date = new SimpleDateFormat("dd-MM-yyyy").parse((String)model.getValueAt(srow,3));
					dcNgaySinh.setDate(date);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				txtSDT.setText(tblNhanVien.getModel().getValueAt(row, 4).toString());
				txtDiaChi.setText(tblNhanVien.getModel().getValueAt(row, 5).toString());
				
			}
		});
		scrollPane.setViewportView(tblNhanVien);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date;
				String sql=  "insert into NhanVien(MaNV, TenNV, GioiTinh,NgaySinh,DiaChi,SDT) values (?,?,?,?,?,?)";
				connect = new ConnectionDB();
				pst =connect.ThucThi(sql);
				
				try {
					pst.setString(1,txtMaNV.getText().toString());
					pst.setString(2,txtTenNV.getText().toString());
					if(rdNam.isSelected())
					{
						pst.setString(3,"Nam");
					}
					if(rdNu.isSelected())
					{
						pst.setString(3,"Nu");
					}
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					date =sdf.format(dcNgaySinh.getDate());
					pst.setString(4,date);
					pst.setString(5,txtDiaChi.getText().toString());
					pst.setString(6,txtSDT.getText().toString());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Them Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
				model.setRowCount(0);
				loadStaff();
			}
		});
		btnThem.setBounds(-4, 425, 85, 21);
		contentPane.add(btnThem);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM NhanVien WHERE MaNV = ?";
				connect = new ConnectionDB();
				pst =connect.ThucThi(sql);
				try
				{
					pst.setString(1,txtMaNV.getText().toString());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Xoa Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
				model.setRowCount(0);
				loadStaff();
			}
		});
		btnXoa.setBounds(224, 425, 85, 21);
		contentPane.add(btnXoa);
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date;
				connect = new ConnectionDB();
				String sql = "Update NhanVien set TenNV=? ,GioiTinh=? ,NgaySinh=? ,SDT=? ,DiaChi=? where MaNV=?";
				pst =connect.ThucThi(sql);
				String gt;
				try {										
					pst.setString(1, txtTenNV.getText().toString());
					if(rdNam.isSelected())
					{	
						gt ="Nam";
						pst.setString(2,gt);	
					}
					if(rdNu.isSelected())
					{						
						gt="Nu";
						pst.setString(2,gt);	
					}		
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					date =sdf.format(dcNgaySinh.getDate());
					pst.setString(3, date);
					pst.setString(4,txtSDT.getText());
					pst.setString(5,txtDiaChi.getText());
					pst.setString(6,txtMaNV.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Sua Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
				model.setRowCount(0);
				loadStaff();
			}
		});
		btnSua.setBounds(109, 425, 85, 21);
		contentPane.add(btnSua);
		
		rdNam = new JRadioButton("Nam");
		rdNam.setBounds(122, 196, 52, 21);
		contentPane.add(rdNam);
		
		rdNu = new JRadioButton("N\u1EEF");
		rdNu.setBounds(199, 196, 52, 21);
		contentPane.add(rdNu);
		bg.add(rdNam);
		bg.add(rdNu);
		
		dcNgaySinh = new JDateChooser();
		dcNgaySinh.setBounds(122, 246, 135, 19);
		contentPane.add(dcNgaySinh);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(460, 42, 336, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("T\u00ECm Ki\u1EBFm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbSearch.getSelectedItem().equals("MaNV"))
				{
					DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
					model.setRowCount(0);
					connect = new ConnectionDB();
					String caulenhSQL ="Select * from NhanVien NV where NV.MaNV Like  '%" + txtSearch.getText().toString() +"%' ";
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
						String manv, tennv,gt,ns,sdt,dc;
						while(rs.next())
						{
							dateformat = new SimpleDateFormat("dd-MM-yyyy");		
							manv=rs.getString(1);
							tennv = rs.getString(2);
							gt=rs.getString(3);
							date =rs.getDate(4);
							ns = dateformat.format(date);
							sdt =rs.getString(5);
							dc =rs.getString(6);
							String[] row = {manv,tennv,gt,ns,sdt,dc};
							model.addRow(row);
						}
						
					
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				if(cmbSearch.getSelectedItem().equals("TenNV"))
				{
					DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
					model.setRowCount(0);
					connect = new ConnectionDB();
					String caulenhSQL ="Select * from NhanVien NV where NV.TenNV Like  '%" + txtSearch.getText().toString() +"%' ";
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
						String manv, tennv,gt,ns,sdt,dc;
						while(rs.next())
						{
							dateformat = new SimpleDateFormat("dd-MM-yyyy");		
							manv=rs.getString(1);
							tennv = rs.getString(2);
							gt=rs.getString(3);
							date =rs.getDate(4);
							ns = dateformat.format(date);
							sdt =rs.getString(5);
							dc =rs.getString(6);
							String[] row = {manv,tennv,gt,ns,sdt,dc};
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
		btnSearch.setBounds(224, 41, 105, 21);
		contentPane.add(btnSearch);
		
		cmbSearch = new JComboBox();
		cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"MaNV", "TenNV"}));
		cmbSearch.setBounds(352, 41, 71, 21);
		contentPane.add(cmbSearch);
		
		JLabel lblNewLabel_6 = new JLabel("B\u1EA3ng th\u00F4ng tin nh\u00E2n vi\u00EAn");
		lblNewLabel_6.setBounds(542, 71, 174, 13);
		contentPane.add(lblNewLabel_6);
		
	}
}
