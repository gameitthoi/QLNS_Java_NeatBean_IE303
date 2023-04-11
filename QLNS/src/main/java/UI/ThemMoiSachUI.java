package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Connect.NXB_Connect;
import Connect.Sach_Connect;
import Model.NXB;
import Model.Sach;
import javax.swing.ImageIcon;

public class ThemMoiSachUI extends JDialog {
	
	private JTextField txtMaSach, txtTenSach, txtMaNXB , txtTacGia,txtGiaBan,txtTheLoai,txtSoLuong ;
	private JComboBox<NXB> cboNXB;
	private JButton btnThemMoi ,btnNhapLai;
	private ArrayList<NXB> dsnxb;
	public ThemMoiSachUI(String title) {
		this.setTitle(title);
		
		addControls();
		addEvents();
		hienThiToanBoNhaXuatban();
	}

	private void hienThiToanBoNhaXuatban() {
		NXB_Connect nxbconn = new NXB_Connect();
		dsnxb =nxbconn.layToanBoNhaXuatBan();
//		if(dsnxb!=null) JOptionPane.showMessageDialog(null, "ss");
		cboNXB.removeAllItems();		
		
		for(NXB s : dsnxb)
		{
			cboNXB.addItem(s);
		}
		
	}

	private void addEvents() {
		btnNhapLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtMaSach.setText("");
				txtTenSach.setText("");
				txtMaNXB.setText("");
				txtTacGia.setText("");
				txtGiaBan.setText("");
				txtTheLoai.setText("");
				txtSoLuong.setText("");
				
				txtMaSach.requestFocus();
				
			}
		});
		btnThemMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtMaSach.getText().length()==0 ||  
						txtTenSach.getText().length()==0 || txtTacGia.getText().length()==0 || txtGiaBan.getText().length()==0 ||
						txtTheLoai.getText().length()==0 || txtSoLuong.getText().length()==0) return ;
				
				NXB nxb = (NXB) cboNXB.getSelectedItem();
				Sach s = new Sach();
				s.setMaSach(txtMaSach.getText());
				s.setTenSach(txtTenSach.getText());
				s.setMaNXB(nxb.getMaNXB());
				s.setTacGia(txtTacGia.getText());
				s.setGiaBan(Double.parseDouble(txtGiaBan.getText()));
				s.setTheLoai(txtTheLoai.getText());
				s.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
				s.setIsDelete(0);
				int x =JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muôn thêm sách", "Xác nhận thêm",JOptionPane.OK_CANCEL_OPTION);
				if(x==JOptionPane.OK_OPTION)
				{
					xuLyThemMoi(s);	
				}
				else return;
				
				
				
			}
		});
		
	}

	protected void xuLyThemMoi(Sach s) {
		Sach_Connect themsach = new Sach_Connect();
		int active = themsach.themSachMoi(s);
		if(active > 0 )
		{
			JOptionPane.showMessageDialog(null, "Thêm mới thành công");
			txtMaSach.setText("");
			txtTenSach.setText("");
			txtMaNXB.setText("");
			txtTacGia.setText("");
			txtGiaBan.setText("");
			txtTheLoai.setText("");
			txtSoLuong.setText("");			
			txtMaSach.requestFocus();
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Thêm mới thất bại");
		}
		
	}

	private void addControls() {
		Container con = getContentPane();
		
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain);
		
		JPanel pnTitle = new JPanel();
		JLabel lblTitle = new JLabel("Thêm Mới Sản Phẩm");
		lblTitle.setFont(new Font("segou ui", Font.BOLD, 20));
		lblTitle.setForeground(Color.blue);
		pnTitle.add(lblTitle);		
		pnMain.add(pnTitle);
		
		JPanel pnMaSach = new JPanel();
		pnMaSach.setLayout(new FlowLayout());
		JLabel lblMaSach = new JLabel("Mã Sách");		
		txtMaSach = new JTextField(20);
		
		pnMaSach.add(lblMaSach);
		pnMaSach.add(txtMaSach);
		pnMain.add(pnMaSach);
		
		JPanel pnTenSach = new JPanel();
		pnTenSach.setLayout(new FlowLayout());
		JLabel lblTenSach = new JLabel("Tên Sách");
		txtTenSach = new JTextField(20);
		pnTenSach.add(lblTenSach);
		pnTenSach.add(txtTenSach);
		pnMain.add(pnTenSach);
		
		//nâng cấp
		JPanel pnMaNXB = new JPanel();
		pnMaNXB.setLayout(new FlowLayout());
		JLabel lblMaNXB = new JLabel("Mã Nhà Xuất Bản");
		txtMaNXB = new JTextField(20);
		cboNXB = new JComboBox<NXB>();
		cboNXB.setPreferredSize(new Dimension(220, 40));
		pnMaNXB.add(lblMaNXB);
		pnMaNXB.add(cboNXB);
		pnMain.add(pnMaNXB);
		
		JPanel pnTacGia = new JPanel();
		pnTacGia.setLayout(new FlowLayout());
		JLabel lblTacGia = new JLabel("Tác giả");
		txtTacGia = new JTextField(20);
		pnTacGia.add(lblTacGia);
		pnTacGia.add(txtTacGia);
		pnMain.add(pnTacGia);
		
		JPanel pnGiaBan = new JPanel();
		pnGiaBan.setLayout(new FlowLayout());
		JLabel lblGiaBan = new JLabel("Giá Bán");
		txtGiaBan = new JTextField(20);		
		pnGiaBan.add(lblGiaBan);
		pnGiaBan.add(txtGiaBan);
		pnMain.add(pnGiaBan);
		
		JPanel pnTheLoai = new JPanel();
		pnTheLoai.setLayout(new FlowLayout());
		JLabel lblTheLoai = new JLabel("Thể Loại");
		txtTheLoai = new JTextField(20);
		pnTheLoai.add(lblTheLoai);
		pnTheLoai.add(txtTheLoai);
		pnMain.add(pnTheLoai);
		
		JPanel pnSoLuong = new JPanel();
		pnSoLuong.setLayout(new FlowLayout());
		JLabel lblSoLuong = new JLabel("Số Lượng");
		txtSoLuong = new JTextField(20);
		pnSoLuong.add(lblSoLuong);
		pnSoLuong.add(txtSoLuong);
		pnMain.add(pnSoLuong);
		
		JPanel pnBtn = new JPanel();
		pnBtn.setLayout(new FlowLayout());
		btnThemMoi = new JButton("Thêm mới");
		btnThemMoi.setPreferredSize(new Dimension(120, 40));
                btnThemMoi.setIcon(new ImageIcon("images/plus.png"));
		btnNhapLai = new JButton("Nhập lại");
		btnNhapLai.setPreferredSize(new Dimension(120, 40));
		pnBtn.add(btnThemMoi);
		pnBtn.add(btnNhapLai);
		pnMain.add(pnBtn);
		
		lblGiaBan.setPreferredSize(lblMaNXB.getPreferredSize());
		lblMaSach.setPreferredSize(lblMaNXB.getPreferredSize());
		lblTenSach.setPreferredSize(lblMaNXB.getPreferredSize());
		lblTacGia.setPreferredSize(lblMaNXB.getPreferredSize());
		lblTheLoai.setPreferredSize(lblMaNXB.getPreferredSize());
		lblSoLuong.setPreferredSize(lblMaNXB.getPreferredSize());
		
		
	}
	
	public void showWindow()
	{
		this.setSize(600, 600);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

}
