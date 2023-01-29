package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ConnectionDB;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class fStaffHangSX extends JFrame {

	private JPanel contentPane;
	private JTable tblHangSX;
	private JTextField txtMaHang;
	private JTextField txtTenHang;
	private JTextField txtSearch;
	
	ConnectionDB connect;
	PreparedStatement pst;
	JComboBox cmbSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fStaffHangSX frame = new fStaffHangSX();
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
	public fStaffHangSX() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				loadHangSX();
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1072, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("M\u00E3 H\u00E3ng");
		lblNewLabel.setBounds(10, 125, 81, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblTnHng = new JLabel("T\u00EAn H\u00E3ng");
		lblTnHng.setBounds(10, 191, 81, 13);
		contentPane.add(lblTnHng);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(319, 101, 661, 342);
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
		
		txtMaHang = new JTextField();
		txtMaHang.setColumns(10);
		txtMaHang.setBounds(101, 122, 142, 19);
		contentPane.add(txtMaHang);
		
		txtTenHang = new JTextField();
		txtTenHang.setColumns(10);
		txtTenHang.setBounds(101, 188, 142, 19);
		contentPane.add(txtTenHang);
		
		JLabel lblBngHngSn = new JLabel("B\u1EA3ng H\u00E3ng S\u00E3n Xu\u1EA5t");
		lblBngHngSn.setBounds(604, 78, 142, 13);
		contentPane.add(lblBngHngSn);
		
		JButton btnSearch = new JButton("T\u00ECm Ki\u1EBFm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbSearch.getSelectedItem().equals("MaHang"))
				{
					DefaultTableModel model = (DefaultTableModel) tblHangSX.getModel();
					model.setRowCount(0);
					connect = new ConnectionDB();
					String caulenhSQL ="Select * from HangSX SX Where SX.MaHangSX Like  '%" + txtSearch.getText().toString() +"%'";
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
				
				if(cmbSearch.getSelectedItem().equals("TenHang"))
				{
					DefaultTableModel model = (DefaultTableModel) tblHangSX.getModel();
					model.setRowCount(0);
					connect = new ConnectionDB();
					String caulenhSQL ="Select * from HangSX SX Where SX.TenHangSX Like  '%" + txtSearch.getText().toString() +"%'";
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
			}
		});
		btnSearch.setBounds(184, 41, 95, 21);
		contentPane.add(btnSearch);
		
		cmbSearch = new JComboBox();
		cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"MaHang", "TenHang"}));
		cmbSearch.setBounds(289, 41, 85, 21);
		contentPane.add(cmbSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(409, 42, 241, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
	}
}
