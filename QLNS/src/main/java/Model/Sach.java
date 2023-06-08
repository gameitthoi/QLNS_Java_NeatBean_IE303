package Model;

public class Sach {
	private String maSach ;
	private String tenSach;
	private String maNXB ;
	private String tacGia ;
	private double giaBan ;
	private String theLoai ;
	private int soLuong ;
        private int discount;
	public Sach() {
		super();
	}
	
	
	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public String getMaNXB() {
		return maNXB;
	}

	public void setMaNXB(String maNXB) {
		this.maNXB = maNXB;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public String getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

		@Override
	public String toString() {
		return this.tenSach;
	}
	
        public int getDiscount(){
            return this.discount;
        }
        
	public void setDiscount(int discount){
            this.discount = discount;
        }
        
	

}
