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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class fHangSX extends JFrame {

	private JPanel contentPane;
	private JTable tblHangSX;
	private JTextField txtMaHang;
	private JTextField txtTenHang;
	ConnectionDB connect;
	PreparedStatement pst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fHangSX frame = new fHangSX();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void loadHangSX()
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

	/**
	 * Create the frame.
	 */
	public fHangSX() {
		setTitle("H\u00E3ng S\u1EA3n Xu\u1EA5t");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				loadHangSX();
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1056, 531);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(358, 53, 648, 390);
		contentPane.add(scrollPane);
		
		tblHangSX = new JTable();
		tblHangSX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblHangSX.getSelectedRow();
				txtMaHang.setText(tblHangSX.getModel().getValueAt(row,0).toString());
				txtTenHang.setText(tblHangSX.getModel().getValueAt(row,1).toString());
			}
		});
		scrollPane.setViewportView(tblHangSX);
		
		JLabel lblNewLabel = new JLabel("M\u00E3 H\u00E3ng");
		lblNewLabel.setBounds(10, 101, 76, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblTnHng = new JLabel("T\u00EAn H\u00E3ng");
		lblTnHng.setBounds(10, 192, 76, 13);
		contentPane.add(lblTnHng);
		
		txtMaHang = new JTextField();
		txtMaHang.setBounds(96, 98, 164, 19);
		contentPane.add(txtMaHang);
		txtMaHang.setColumns(10);
		
		txtTenHang = new JTextField();
		txtTenHang.setColumns(10);
		txtTenHang.setBounds(96, 186, 164, 19);
		contentPane.add(txtTenHang);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql=  "insert into HangSX(MaHangSX, TenHangSX) values (?,?)";
				connect = new ConnectionDB();
				pst =connect.ThucThi(sql);
				try {
					pst.setString(1,txtMaHang.getText().toString());
					pst.setString(2,txtTenHang.getText().toString());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Them Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblHangSX.getModel();
				model.setRowCount(0);
				loadHangSX();			
			}
		});
		btnThem.setBounds(10, 307, 85, 21);
		contentPane.add(btnThem);
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect = new ConnectionDB();
				String sql = "Update HangSX set TenHangSX=? where MaHangSX=?";
				pst =connect.ThucThi(sql);
				try {
					String gt;
					pst.setString(1, txtTenHang.getText().toString());
					pst.setString(2,txtMaHang.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Sua Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}	
				DefaultTableModel model = (DefaultTableModel) tblHangSX.getModel();
				model.setRowCount(0);
				loadHangSX();			
			}
		});
		btnSua.setBounds(122, 307, 85, 21);
		contentPane.add(btnSua);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM HangSX WHERE MaHangSX = ?";
				connect = new ConnectionDB();
				pst =connect.ThucThi(sql);
				try
				{
					pst.setString(1,txtMaHang.getText().toString());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Xoa Thanh Cong");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) tblHangSX.getModel();
				model.setRowCount(0);
				loadHangSX();
			}
		});
		btnXoa.setBounds(255, 307, 85, 21);
		contentPane.add(btnXoa);
		
		JLabel lblNewLabel_1 = new JLabel("B\u1EA3ng H\u00E3ng S\u00E3n Xu\u1EA5t");
		lblNewLabel_1.setBounds(638, 36, 130, 13);
		contentPane.add(lblNewLabel_1);
	}

}
