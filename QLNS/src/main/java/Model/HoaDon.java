package Model;

import java.util.Date;

public class HoaDon {
	private String maHD ;
	private String maNV ;
	private String ngaylap ;
	private double tongTien;
	private int ThanhCong ;
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
	public int getTrangThai() {
		return ThanhCong;
	}
	public void setTrangThai(int ThanhCong) {
		this.ThanhCong = ThanhCong;
	}
	public int getNhapSach() {
		return nhapSach;
	}
	public void setNhapSach(int nhapSach) {
		this.nhapSach = nhapSach;
	}

}
