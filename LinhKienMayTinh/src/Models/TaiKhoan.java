package Models;

public class TaiKhoan {
	private String maLoaiTK;
	private String taiKhoan;
	private String matKhau;
	public String getMaLoaiTK() {
		return maLoaiTK;
	}
	public void setMaLoaiTK(String maLoaiTK) {
		this.maLoaiTK = maLoaiTK;
	}
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	
	public TaiKhoan(String taiKhoan, String matKhau)
	{
		super();
		this.taiKhoan=taiKhoan;
		this.matKhau=matKhau;
	}
	
	public TaiKhoan() {
		super();
	}
	
	@Override
	public String toString() {
		return "TaiKhoan [tenTaiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", loaiTaiKhoan=" + maLoaiTK + "]";
	}
	
}
