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
	private String trangThai ;
	private String email ;
	private int isDelete ;
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
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public String toString() {
		return this.tenNV;
	}
	
	
	
}
