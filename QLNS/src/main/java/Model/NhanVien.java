package Model;

import java.sql.Date;

public class NhanVien {
	
	private String maNV;
	private String tenNV ;
	private String ngaySinh ;
	private String ngayVaolam;
	private String soChungMinh;
	private String maCV;
	private String SDT ;
	private String email ;
        private double luong;
	public NhanVien() {
		super();
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String string) {
		this.ngaySinh = string;
	}
	public String getNgayVaolam() {
		return ngayVaolam;
	}
	public void setNgayVaolam(String date) {
		this.ngayVaolam = date;
	}
	public String getSoChungMinh() {
		return soChungMinh;
	}
	public void setSoChungMinh(String soChungMinh) {
		this.soChungMinh = soChungMinh;
	}
	public String getMaCV() {
		return maCV;
	}
	public void setMaCV(String maCV) {
		this.maCV = maCV;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
        public double getLuong(){
            return this.luong;
        }
        public void setLuong(double luong){
            this.luong = luong;
        }
	@Override
	public String toString() {
		return this.tenNV;
	}
}
