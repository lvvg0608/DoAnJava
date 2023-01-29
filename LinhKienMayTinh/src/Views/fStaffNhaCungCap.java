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
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class fStaffNhaCungCap extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTable tblNCC;
	JComboBox cmbSearch;
	
	ConnectionDB connect;
	PreparedStatement pst;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fNhaCungCap frame = new fNhaCungCap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void loadNCC()
	{
		connect = new ConnectionDB();
		String caulenhSQL ="Select * from NhaCungCap";
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
			String mancc, tenncc,dc,sdt;
			while(rs.next())
			{							
				mancc=rs.getString(1);
				tenncc = rs.getString(2);
				dc=rs.getString(3);
				sdt =rs.getString(4);
				String[] row = {mancc,tenncc,dc,sdt};
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
	public fStaffNhaCungCap() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				loadNCC();
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 895, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("M\u00E3 Nh\u00E0 Cung C\u1EA5p");
		lblNewLabel.setBounds(10, 127, 107, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblTnNhCung = new JLabel("T\u00EAn Nh\u00E0 Cung C\u1EA5p");
		lblTnNhCung.setBounds(10, 184, 107, 13);
		contentPane.add(lblTnNhCung);
		
		JLabel lblaCh = new JLabel("\u0110\u1ECBa Ch\u1EC9");
		lblaCh.setBounds(10, 251, 80, 13);
		contentPane.add(lblaCh);
		
		JLabel lblSt = new JLabel("S\u0110T");
		lblSt.setBounds(10, 324, 80, 13);
		contentPane.add(lblSt);
		
		txtMaNCC = new JTextField();
		txtMaNCC.setBounds(138, 124, 114, 19);
		contentPane.add(txtMaNCC);
		txtMaNCC.setColumns(10);
		
		txtTenNCC = new JTextField();
		txtTenNCC.setColumns(10);
		txtTenNCC.setBounds(138, 181, 114, 19);
		contentPane.add(txtTenNCC);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(138, 248, 114, 19);
		contentPane.add(txtDiaChi);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(138, 321, 114, 19);
		contentPane.add(txtSDT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(273, 105, 575, 299);
		contentPane.add(scrollPane);
		
		tblNCC = new JTable();
		tblNCC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblNCC.getSelectedRow();
				TableModel model =tblNCC.getModel();
				txtMaNCC.setText(model.getValueAt(row, 0).toString());
				txtTenNCC.setText(model.getValueAt(row, 1).toString());				
				txtDiaChi.setText(model.getValueAt(row, 2).toString());
				txtSDT.setText(model.getValueAt(row, 3).toString());
			}
		});
		scrollPane.setViewportView(tblNCC);
		
		JButton btnSearch = new JButton("T\u00ECm Ki\u1EBFm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbSearch.getSelectedItem().equals("MaNCC"))
				{
					DefaultTableModel model = (DefaultTableModel) tblNCC.getModel();
					model.setRowCount(0);
					connect = new ConnectionDB();
					String caulenhSQL ="Select * from NhaCungCap NCC Where NCC.MaNCC Like  '%" + txtSearch.getText().toString() +"%'";
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
						String mancc, tenncc,dc,sdt;
						while(rs.next())
						{							
							mancc=rs.getString(1);
							tenncc = rs.getString(2);
							dc=rs.getString(3);
							sdt =rs.getString(4);
							String[] row = {mancc,tenncc,dc,sdt};
							model.addRow(row);
						}
						
					
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				if(cmbSearch.getSelectedItem().equals("TenNCC"))
				{
					DefaultTableModel model = (DefaultTableModel) tblNCC.getModel();
					model.setRowCount(0);
					connect = new ConnectionDB();
					String caulenhSQL ="Select * from NhaCungCap NCC Where NCC.TenNCC Like  '%" + txtSearch.getText().toString() +"%'";
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
						String mancc, tenncc,dc,sdt;
						while(rs.next())
						{							
							mancc=rs.getString(1);
							tenncc = rs.getString(2);
							dc=rs.getString(3);
							sdt =rs.getString(4);
							String[] row = {mancc,tenncc,dc,sdt};
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
		btnSearch.setBounds(151, 50, 107, 21);
		contentPane.add(btnSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(362, 51, 340, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		cmbSearch = new JComboBox();
		cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"MaNCC", "TenNCC"}));
		cmbSearch.setBounds(274, 50, 71, 21);
		contentPane.add(cmbSearch);
	}
}
