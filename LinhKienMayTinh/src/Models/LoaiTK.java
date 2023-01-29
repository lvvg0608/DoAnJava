package Models;

public class LoaiTK {

	private int MaloaiTk;
	private String TenLoaiTK;
	
	public int getMaloaiTk() {
		return MaloaiTk;
	}



	public void setMaloaiTk(int maloaiTk) {
		MaloaiTk = maloaiTk;
	}



	public String getTenLoaiTK() {
		return TenLoaiTK;
	}



	public void setTenLoaiTK(String tenLoaiTK) {
		TenLoaiTK = tenLoaiTK;
	}


	public LoaiTK(int MaloaiTk, String TenLoaiTK)
	{
		super();
		this.MaloaiTk=MaloaiTk;
		this.TenLoaiTK=TenLoaiTK;
	}
	
	public LoaiTK() {
		super();
	}
	
	public String toString() {
		return "LoaiTK [MaLoaiTk=" + MaloaiTk + ", TenLoaiTK=" + TenLoaiTK + "]";
	}
}
