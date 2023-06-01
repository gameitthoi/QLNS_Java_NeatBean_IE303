/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nguye
 */
public class VPP {
    	private String maVPP ;
	private String tenVPP;
	private String maNCC ;
	private double giaBan ;
	private String danhMuc ;
	private int soLuong ;
        private int discount;

        
        public VPP() {
		super();
	}
    /**
     * @return the maVPP
     */
        
    public String getMaVPP() {
        return maVPP;
    }

    /**
     * @param maVPP the maVPP to set
     */
    public void setMaVPP(String maVPP) {
        this.maVPP = maVPP;
    }

    /**
     * @return the tenVPP
     */
    public String getTenVPP() {
        return tenVPP;
    }

    /**
     * @param tenVPP the tenVPP to set
     */
    public void setTenVPP(String tenVPP) {
        this.tenVPP = tenVPP;
    }

    /**
     * @return the maNCC
     */
    public String getMaNCC() {
        return maNCC;
    }

    /**
     * @param maNCC the maNCC to set
     */
    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    /**
     * @return the giaBan
     */
    public double getGiaBan() {
        return giaBan;
    }

    /**
     * @param giaBan the giaBan to set
     */
    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    /**
     * @return the danhMuc
     */
    public String getDanhMuc() {
        return danhMuc;
    }

    /**
     * @param danhMuc the danhMuc to set
     */
    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    @Override
    public String toString(){
        return this.danhMuc;
    }
}
