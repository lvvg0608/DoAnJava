package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ConnectionDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class fLoaiHang extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaLoai;
	private JTextField txtTenLoai;
	private JTable tblLoaiLK;
	
	ConnectionDB connect;
	PreparedStatement pst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fLoaiHang frame = new fLoaiHang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void loadLoaiLK()
	{
		connect = new ConnectionDB();
		String caulenhSQL ="Select * from LoaiLK";
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

	/**
	 * Create the frame.
	 */
	public fLoaiHang() {
		setTitle("Lo\u1EA1i Linh Ki\u1EC7n");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				loadLoaiLK();
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 863, 471);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("M\u00E3 Lo\u1EA1i");
		lblNewLabel.setBounds(10, 92, 70, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblTnLoi = new JLabel("T\u00EAn Lo\u1EA1i");
		lblTnLoi.setBounds(10, 171, 70, 13);
		contentPane.add(lblTnLoi);
		
		txtMaLoai = new JTextField();
		txtMaLoai.setBounds(107, 89, 124, 19);
		contentPane.add(txtMaLoai);
		txtMaLoai.setColumns(10);
		
		txtTenLoai = new JTextField();
		txtTenLoai.setColumns(10);
		txtTenLoai.setBounds(107, 168, 124, 19);
		contentPane.add(txtTenLoai);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(307, 77, 460, 262);
		contentPane.add(scrollPane);
		
		tblLoaiLK = new JTable();
		tblLoaiLK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblLoaiLK.getSelectedRow();
				txtMaLoai.setText(tblLoaiLK.getModel().getValueAt(row,0).toString());
				txtTenLoai.setText(tblLoaiLK.getModel().getValueAt(row,1).toString());
			}
		});
		scrollPane.setViewportView(tblLoaiLK);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql=  "insert into LoaiLK(MaLoai, TenLoai) values (?,?)";
				connect = new ConnectionDB();
				pst =connect.ThucThi(sql);
				try {
					pst.setString(1,txtMaLoai.getText().toString());
					pst.setString(2,txtTenLoai.getText().toString());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Them Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblLoaiLK.getModel();
				model.setRowCount(0);
				loadLoaiLK();
			}
		});
		btnThem.setBounds(10, 268, 83, 21);
		contentPane.add(btnThem);
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect = new ConnectionDB();
				String sql = "Update LoaiLK set TenLoai=? where MaLoai=?";
				pst =connect.ThucThi(sql);
				try {
					String gt;
					pst.setString(1, txtTenLoai.getText().toString());
					pst.setString(2,txtMaLoai.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Sua Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}	
				DefaultTableModel model = (DefaultTableModel) tblLoaiLK.getModel();
				model.setRowCount(0);
				loadLoaiLK();
			}
		});
		btnSua.setBounds(107, 268, 83, 21);
		contentPane.add(btnSua);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM LoaiLK WHERE MaLoai = ?";
				connect = new ConnectionDB();
				pst =connect.ThucThi(sql);
				try
				{
					pst.setString(1,txtMaLoai.getText().toString());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Xoa Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblLoaiLK.getModel();
				model.setRowCount(0);
				loadLoaiLK();
			}
		});
		btnXoa.setBounds(214, 268, 83, 21);
		contentPane.add(btnXoa);
	}

}
