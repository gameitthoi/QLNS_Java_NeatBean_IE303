/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dat
 */
public class TonKho {
    private String maSP ;
    private String tenSP;
    private int tonDau;
    private int nhap;
    private int xuat;
    private int tonCuoi;
    
    public TonKho(){
        super();
    }
    
    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
    
     public int getTonDau() {
        return tonDau;
    }

    public void setTonDau(int tonDau) {
        this.tonDau = tonDau;
    }
    
    public int getNhap() {
        return nhap;
    }

    public void setNhap(int nhap) {
        this.nhap = nhap;
    }
     public int getXuat() {
        return xuat;
    }

    public void setXuat(int xuat) {
        this.xuat = xuat;
    }
     public int getTonCuoi() {
        return tonCuoi;
    }

    public void setTonCuoi(int tonCuoi) {
        this.tonCuoi = tonCuoi;
    }
}
