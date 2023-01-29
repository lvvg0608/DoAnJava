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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

public class fAllCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTable tblAllCustomer;
	
	ConnectionDB connect;
	PreparedStatement pst;
	JRadioButton rtdNam;
	JRadioButton rtdNu;
	ButtonGroup bg = new ButtonGroup();
	SimpleDateFormat sdf;
	JDateChooser dcNgaySinh;
	Date date;
	DateFormat dateformat;
	private JTextField txtSearch;
	JComboBox cmbLoai;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fAllCustomer frame = new fAllCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void loadCustomer() {
		connect = new ConnectionDB();
		String caulenhSQL ="Select * from KhachHang";
		ResultSet rs = connect.ThucThiCauLenh(caulenhSQL);
		try {			
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblAllCustomer.getModel();
			
			int cols =rsmd.getColumnCount();
			String[] colname =new String[cols];
			for(int i=0;i<cols;i++)
			{
				colname[i] =rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colname);
			}
			String makh, tenkh,gt,ns,sdt,dc;
								
			while(rs.next())
			{
				dateformat = new SimpleDateFormat("dd-MM-yyyy");						
				makh=rs.getString(1);
				tenkh = rs.getString(2);
				gt=rs.getString(3);
				date =rs.getDate(4);
				ns = dateformat.format(date);
				sdt =rs.getString(5);
				dc =rs.getString(6);
				String[] row = {makh,tenkh,gt,ns,sdt,dc};
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
	public fAllCustomer() {
		setTitle("Danh S\u00E1ch Kh\u00E1ch H\u00E0ng");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				loadCustomer();
				
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1207, 589);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("M\u00E3 Kh\u00E1ch H\u00E0ng");
		lblNewLabel.setBounds(10, 118, 98, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblTnKhchHng = new JLabel("T\u00EAn Kh\u00E1ch H\u00E0ng");
		lblTnKhchHng.setBounds(10, 174, 98, 13);
		contentPane.add(lblTnKhchHng);
		
		JLabel lblaCh = new JLabel("\u0110\u1ECBa Ch\u1EC9 ");
		lblaCh.setBounds(10, 349, 98, 13);
		contentPane.add(lblaCh);
		
		JLabel lblSt = new JLabel("S\u0110T");
		lblSt.setBounds(10, 396, 98, 13);
		contentPane.add(lblSt);
		
		JLabel lblMKhchHng = new JLabel("Ng\u00E0y Sinh");
		lblMKhchHng.setBounds(10, 282, 98, 13);
		contentPane.add(lblMKhchHng);
		
		txtMaKH = new JTextField();
		txtMaKH.setBounds(132, 115, 138, 19);
		contentPane.add(txtMaKH);
		txtMaKH.setColumns(10);
		
		txtTenKH = new JTextField();
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(132, 171, 138, 19);
		contentPane.add(txtTenKH);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(132, 346, 138, 19);
		contentPane.add(txtDiaChi);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(132, 393, 138, 19);
		contentPane.add(txtSDT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(340, 111, 792, 355);
		contentPane.add(scrollPane);
		
		tblAllCustomer = new JTable();
		tblAllCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row = tblAllCustomer.getSelectedRow();
				TableModel model =tblAllCustomer.getModel();
				txtMaKH.setText(model.getValueAt(row, 0).toString());
				txtTenKH.setText(model.getValueAt(row, 1).toString());
				String gt = model.getValueAt(row,2).toString();
				if(gt.equals("Nam"))
				{
					rtdNam.setSelected(true);
				}
				else {
					rtdNu.setSelected(true);
				}				
				try {
					int srow = tblAllCustomer.getSelectedRow();
					date = new SimpleDateFormat("dd-MM-yyyy").parse((String)model.getValueAt(srow,3));
					dcNgaySinh.setDate(date);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				txtDiaChi.setText(model.getValueAt(row, 4).toString());
				txtSDT.setText(model.getValueAt(row, 5).toString());
				
			}
		});
		scrollPane.setViewportView(tblAllCustomer);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date;
				String sql=  "insert into KhachHang(MaKH, TenKH, GioiTinh,NgaySinh,DiaChi,SDT) values (?,?,?,?,?,?)";
				connect = new ConnectionDB();
				pst =connect.ThucThi(sql);
				try {
					pst.setString(1,txtMaKH.getText().toString());
					pst.setString(2,txtTenKH.getText().toString());
					if(rtdNam.isSelected())
					{
						pst.setString(3,"Nam");
					}
					if(rtdNu.isSelected())
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
				DefaultTableModel model = (DefaultTableModel) tblAllCustomer.getModel();
				model.setRowCount(0);
				loadCustomer();
			}
		});
		btnThem.setBounds(10, 463, 85, 21);
		contentPane.add(btnThem);
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date;
				connect = new ConnectionDB();
				String sql = "Update KhachHang set TenKH=? ,GioiTinh=? ,NgaySinh=? ,SDT=? ,DiaChi=? where MaKH=?";
				pst =connect.ThucThi(sql);
				try {
					String gt;
					pst.setString(1, txtTenKH.getText().toString());
					if(rtdNam.isSelected())
					{	
						gt ="Nam";
						pst.setString(2,gt);	
					}
					if(rtdNu.isSelected())
					{						
						gt="Nu";
						pst.setString(2,gt);	
					}
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					date =sdf.format(dcNgaySinh.getDate());
					pst.setString(3, date);
					pst.setString(4,txtSDT.getText());
					pst.setString(5,txtDiaChi.getText());
					pst.setString(6,txtMaKH.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Sua Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}	
				DefaultTableModel model = (DefaultTableModel) tblAllCustomer.getModel();
				model.setRowCount(0);
				loadCustomer();
			}
		});
		btnSua.setBounds(122, 463, 85, 21);
		contentPane.add(btnSua);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM KhachHang WHERE MaKH = ?";
				connect = new ConnectionDB();
				pst =connect.ThucThi(sql);
				try
				{
					pst.setString(1,txtMaKH.getText().toString());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Xoa Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblAllCustomer.getModel();
				model.setRowCount(0);
				loadCustomer();
			}
		});
		btnXoa.setBounds(232, 463, 85, 21);
		contentPane.add(btnXoa);
		
		JLabel lblGiiTnh = new JLabel("Gi\u1EDBi T\u00EDnh");
		lblGiiTnh.setBounds(10, 233, 98, 13);
		contentPane.add(lblGiiTnh);
		
		rtdNam = new JRadioButton("Nam");
		rtdNam.setBounds(125, 229, 56, 21);
		contentPane.add(rtdNam);
		
		rtdNu = new JRadioButton("N\u1EEF");
		rtdNu.setBounds(183, 229, 56, 21);
		contentPane.add(rtdNu);
		
		bg.add(rtdNam);
		bg.add(rtdNu);
		
		dcNgaySinh = new JDateChooser();
		dcNgaySinh.setBounds(134, 282, 136, 19);
		contentPane.add(dcNgaySinh);
		
		JButton btnSearch = new JButton("T\u00ECm Ki\u1EBFm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbLoai.getSelectedItem().equals("MaKH"))
				{
					DefaultTableModel model = (DefaultTableModel) tblAllCustomer.getModel();
					model.setRowCount(0);
					connect = new ConnectionDB();
					String caulenhSQL ="Select * from KhachHang WHERE MaKH LIKE '%" + txtSearch.getText().toString() +"%' ";
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
							String makh, tenkh,gt,ns,sdt,dc;
												
							while(rs.next())
							{
								dateformat = new SimpleDateFormat("dd-MM-yyyy");						
								makh=rs.getString(1);
								tenkh = rs.getString(2);
								gt=rs.getString(3);
								date =rs.getDate(4);
								ns = dateformat.format(date);
								sdt =rs.getString(5);
								dc =rs.getString(6);
								String[] row = {makh,tenkh,gt,ns,sdt,dc};
								model.addRow(row);
							}
											
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
				}
				if(cmbLoai.getSelectedItem().equals("TenKH"))
				{
					DefaultTableModel model = (DefaultTableModel) tblAllCustomer.getModel();
					model.setRowCount(0);
					connect = new ConnectionDB();
					String caulenhSQL ="Select * from KhachHang WHERE TenKH LIKE '%" + txtSearch.getText().toString() +"%' ";
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
							String makh, tenkh,gt,ns,sdt,dc;
												
							while(rs.next())
							{
								dateformat = new SimpleDateFormat("dd-MM-yyyy");						
								makh=rs.getString(1);
								tenkh = rs.getString(2);
								gt=rs.getString(3);
								date =rs.getDate(4);
								ns = dateformat.format(date);
								sdt =rs.getString(5);
								dc =rs.getString(6);
								String[] row = {makh,tenkh,gt,ns,sdt,dc};
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
		btnSearch.setBounds(183, 55, 111, 21);
		contentPane.add(btnSearch);
		
		cmbLoai = new JComboBox();
		cmbLoai.setModel(new DefaultComboBoxModel(new String[] {"MaKH", "TenKH"}));
		cmbLoai.setBounds(318, 55, 65, 21);
		contentPane.add(cmbLoai);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(410, 56, 254, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		lblNewLabel_1 = new JLabel("B\u1EA3ng th\u00F4ng tin kh\u00E1ch h\u00E0ng");
		lblNewLabel_1.setBounds(674, 88, 162, 13);
		contentPane.add(lblNewLabel_1);
		
	}
}
