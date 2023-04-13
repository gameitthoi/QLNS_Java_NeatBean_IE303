package Model;

import java.util.Date;

public class HoaDon {
	private String maHD ;
	private String maNV ;
	private String ngaylap ;
	private double tongTien;
	private int isDelete ;
        private int nhapSach ;
	public HoaDon() {
		super();
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getNgaylap() {
		return ngaylap;
	}
	public void setNgaylap(String ngaylap) {
		this.ngaylap = ngaylap;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public int getNhapSach() {
		return nhapSach;
	}
	public void setNhapSach(int nhapSach) {
		this.nhapSach = nhapSach;
	}

}
