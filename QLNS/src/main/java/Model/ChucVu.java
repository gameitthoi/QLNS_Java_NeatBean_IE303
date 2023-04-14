/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dat
 */
public class ChucVu {
	private String maCV;
	private String chucVu;
	private String dinhDoanh;
	public ChucVu() {
		super();
	}
	public String getMaCV() {
		return maCV;
	}
	public void setMaCV(String maCV) {
		this.maCV = maCV;
	}
	public String getChuVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public String getDinhDoanh() {
		return dinhDoanh;
	}
	public void setDinhDoanh(String dinhDoanh) {
		this.dinhDoanh = dinhDoanh;
	}
	@Override
	public String toString() {
		return this.chucVu;
	}	
}
