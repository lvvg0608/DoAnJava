package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class fAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fAdmin frame = new fAdmin();
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
	public fAdmin() {
		setTitle("Trang Ch\u1EE7 Admin");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1063, 486);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnTaiKhoan = new JMenu("T\u00E0i Kho\u1EA3n");
		mnTaiKhoan.setBackground(Color.WHITE);
		mnTaiKhoan.setForeground(Color.BLACK);
		mnTaiKhoan.setIcon(null);
		menuBar.add(mnTaiKhoan);
		
		JButton btnTKNhanVien = new JButton("T\u00E0i kho\u1EA3n nh\u00E2n vi\u00EAn");
		btnTKNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fStaffAccount fStaff = new fStaffAccount();
				fStaff.setLocationRelativeTo(null);
				fStaff.setVisible(true);
			}
		});
		mnTaiKhoan.add(btnTKNhanVien);
		
		JMenu mnSanPham = new JMenu("S\u1EA3n ph\u1EA9m");
		menuBar.add(mnSanPham);
		
		JButton btnLoaiHang = new JButton("Lo\u1EA1i h\u00E0ng");
		btnLoaiHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fLoaiHang fLH = new fLoaiHang();
				fLH.setVisible(true);
			}
		});
		mnSanPham.add(btnLoaiHang);
		
		JButton btnHangHoa = new JButton("Danh s\u00E1ch linh ki\u1EC7n");
		btnHangHoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fProduct fpro = new fProduct();
				fpro.setLocationRelativeTo(null);
				fpro.setVisible(true);
			}
		});
		mnSanPham.add(btnHangHoa);
		
		JMenu mnHoaDon = new JMenu("H\u00F3a \u0110\u01A1n");
		menuBar.add(mnHoaDon);
		
		JButton btnHoaDonNhap = new JButton("H\u00F3a \u0111\u01A1n nh\u1EADp");
		btnHoaDonNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fHoaDonNhap fhdn = new fHoaDonNhap();
				fhdn.setLocationRelativeTo(null);
				fhdn.setVisible(true);
			}
		});
		mnHoaDon.add(btnHoaDonNhap);
		
		JButton btnNewButton = new JButton("H\u00F3a \u0111\u01A1n xu\u1EA5t");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fHoaDonKH fHDKH = new fHoaDonKH();
				fHDKH.setLocationRelativeTo(null);
				fHDKH.setVisible(true);
			}
		});
		mnHoaDon.add(btnNewButton);
		
		JButton btnNhanVien = new JButton("Nh\u00E2n Vi\u00EAn");
		btnNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fAllStaff fall = new fAllStaff();
				fall.setLocationRelativeTo(null);
				fall.setVisible(true);
			}
		});
		menuBar.add(btnNhanVien);
		
		JButton btnKhachHang = new JButton("Kh\u00E1ch H\u00E0ng");
		btnKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fAllCustomer fAllCus = new fAllCustomer();
				fAllCus.setLocationRelativeTo(null);
				fAllCus.setVisible(true);
			}
		});
		menuBar.add(btnKhachHang);
		
		JButton btnNhaCungCap = new JButton("Nh\u00E0 Cung C\u1EA5p");
		btnNhaCungCap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fNhaCungCap fncc = new fNhaCungCap();
				fncc.setLocationRelativeTo(null);
				fncc.setVisible(true);
			}
		});
		
		JButton btnHangSX = new JButton("H\u00E3ng S\u00E3n Xu\u1EA5t");
		btnHangSX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fHangSX fSX = new fHangSX();
				fSX.setLocationRelativeTo(null);
				fSX.setVisible(true);
			}
		});
		menuBar.add(btnHangSX);
		menuBar.add(btnNhaCungCap);
		
		JButton btnTKDoanhThu = new JButton("Th\u1ED1ng k\u00EA doanh thu");
		menuBar.add(btnTKDoanhThu);
		btnTKDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fThongKeDoanhThu fTKDT = new fThongKeDoanhThu();
				fTKDT.setLocationRelativeTo(null);
				fTKDT.setVisible(true);
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u0110\u0103ng Xu\u1EA5t");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Ban Co Muon Thoat Khong ?", "Confirm Exit",
                        JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION)
                    System.exit(0);
			}
		});
		menuBar.add(btnNewButton_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ACER\\OneDrive\\Hi\u0300nh a\u0309nh\\IMG_2927.JPG"));
		lblNewLabel.setBounds(10, 10, 1029, 406);
		contentPane.add(lblNewLabel);
	}
}
