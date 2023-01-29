package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class fStaff extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fStaff frame = new fStaff();
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
	public fStaff() {
		setTitle("VG Computer");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 724, 394);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnKhachHang = new JButton("Kh\u00E1ch H\u00E0ng");
		btnKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fAllCustomer fAllCus = new fAllCustomer();
				fAllCus.setLocationRelativeTo(null);
				fAllCus.setVisible(true);
			}
		});
		
		JMenu mnHangHoa = new JMenu("Linh Ki\u1EC7n");
		menuBar.add(mnHangHoa);
		
		JButton btnKhoHang = new JButton("Kho H\u00E0ng");
		btnKhoHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fProduct fpro = new fProduct();
				fpro.setLocationRelativeTo(null);
				fpro.setVisible(true);
			}
		});
		mnHangHoa.add(btnKhoHang);
		
		JButton btnLoaiHang = new JButton("Lo\u1EA1i H\u00E0ng");
		btnLoaiHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fLoaiHang flh = new fLoaiHang();
				flh.setLocationRelativeTo(null);
				flh.setVisible(true);
			}
		});
		mnHangHoa.add(btnLoaiHang);
		
		JMenu mnHoaDon = new JMenu("H\u00F3a \u0110\u01A1n");
		menuBar.add(mnHoaDon);
		
		JButton btnHoaDonNhap = new JButton("H\u00F3a \u0111\u01A1n nh\u1EADp");
		btnHoaDonNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fHoaDonNhap fHDN = new fHoaDonNhap();
				fHDN.setLocationRelativeTo(null);
				fHDN.setVisible(true);
			}
		});
		mnHoaDon.add(btnHoaDonNhap);
		
		JButton btnHoaDonXuat = new JButton("H\u00F3a \u0111\u01A1n xu\u1EA5t");
		btnHoaDonXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fHoaDonKH fHDKH = new fHoaDonKH();
				fHDKH.setLocationRelativeTo(null);
				fHDKH.setVisible(true);
			}
		});
		mnHoaDon.add(btnHoaDonXuat);
		menuBar.add(btnKhachHang);
		
		JButton btnNhaCungCap = new JButton("Nh\u00E0 Cung C\u1EA5p");
		btnNhaCungCap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fStaffNhaCungCap fncc = new fStaffNhaCungCap();
				fncc.setLocationRelativeTo(null);
				fncc.setVisible(true);
				}
		});
		menuBar.add(btnNhaCungCap);
		
		JButton btnHangSX = new JButton("H\u00E3ng S\u00E3n Xu\u1EA5t");
		btnHangSX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fStaffHangSX fhangsx = new fStaffHangSX();
				fhangsx.setLocationRelativeTo(null);
				fhangsx.setVisible(true);
			}
		});
		menuBar.add(btnHangSX);
		
		JButton btnTKHoaDon = new JButton("Th\u1ED1ng k\u00EA h\u00F3a \u0111\u01A1n");
		btnTKHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fThongKeDoanhThu ftkdt = new fThongKeDoanhThu();
				ftkdt.setLocationRelativeTo(null);
				ftkdt.setVisible(true);
			}
		});
		menuBar.add(btnTKHoaDon);
		
		JButton btnLogOut = new JButton("\u0110\u0103ng Xu\u1EA5t");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Ban Co Muon Thoat Khong ?", "Confirm Exit",
                        JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION)
                    System.exit(0);
			}
		});
		menuBar.add(btnLogOut);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ACER\\OneDrive\\Hi\u0300nh a\u0309nh\\IMG_2927.JPG"));
		lblNewLabel.setBounds(10, 0, 700, 334);
		contentPane.add(lblNewLabel);
	}
}
