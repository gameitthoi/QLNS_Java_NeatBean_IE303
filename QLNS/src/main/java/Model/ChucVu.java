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
	private String chuVu;
	private String dinhDoanh;
	private int isDelete ;
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
		return chuVu;
	}
	public void setChuVu(String chuVu) {
		this.chuVu = chuVu;
	}
	public String getDinhDoanh() {
		return dinhDoanh;
	}
	public void setDinhDoanh(String dinhDoanh) {
		this.dinhDoanh = dinhDoanh;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	@Override
	public String toString() {
		return this.chuVu;
	}	
}
