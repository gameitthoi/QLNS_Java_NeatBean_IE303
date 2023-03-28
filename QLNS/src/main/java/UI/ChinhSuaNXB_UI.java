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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Connect.NXB_Connect;
import Model.NXB;
import Model.Sach;

public class ChinhSuaNXB_UI extends JDialog {
	private JTextField txtMaNXB, txtTenNXB , txtSDT,txtDiaChi,txtEmail ;
	private JButton btnUpdate ,btnNhapLai;
	private ArrayList<NXB> dsnxb;
	
	
	public ChinhSuaNXB_UI(String title , NXB nxb) {
		this.setTitle("Chỉnh sửa sách");
		addControls();
		addEvents();		
		hienThiThongTinBanDauCuaSach(nxb);
	}
	//hàm hiển thị thông tin sách cần chỉnh sửa
	private void hienThiThongTinBanDauCuaSach(NXB s) {
		
		txtMaNXB.setText(s.getMaNXB());
		txtTenNXB.setText(s.getTenNXB());
		txtSDT.setText(s.getSDT());
		txtDiaChi.setText(s.getDiaChi());
		txtEmail.setText(s.getEmail());
	}
	

	private void addEvents() {
		btnNhapLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				txtMaSach.setText("");
				txtTenNXB.setText("");
//				txtMaNXB.setText("");
				txtSDT.setText("");
				txtDiaChi.setText("");
				txtEmail.setText("");
				
				
				txtTenNXB.requestFocus();
				
			}
		});
		
		// 
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NXB nxb = new NXB();
				nxb.setMaNXB(txtMaNXB.getText());
				nxb.setTenNXB(txtTenNXB.getText());
				nxb.setSDT(txtSDT.getText());
				nxb.setDiaChi(txtDiaChi.getText());
				nxb.setEmail(txtEmail.getText());
				NXB_Connect nxb_conn = new NXB_Connect();
				int active = nxb_conn.updateNXB(nxb);
				if(active>0 ) 
				{
					JOptionPane.showMessageDialog(null, "Chỉnh sửa thông tin nhà xuất bản thành công !");
				
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Chỉnh sửa thất bại");
				}
				
			}
		});
		
	}

	private void addControls() {
		Container con = getContentPane();
		
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain);
		
		JPanel pnTitle = new JPanel();
		JLabel lblTitle = new JLabel("Chỉnh sửa nhà xuất bản");
		lblTitle.setFont(new Font("segou ui", Font.BOLD, 20));
		lblTitle.setForeground(Color.blue);
		pnTitle.add(lblTitle);		
		pnMain.add(pnTitle);
		
		JPanel pnMaNXB = new JPanel();
		pnMaNXB.setLayout(new FlowLayout());
		JLabel lblMaNXB = new JLabel("Mã NXB");		
		txtMaNXB = new JTextField(20);
		txtMaNXB.setEnabled(false);
		
		pnMaNXB.add(lblMaNXB);
		pnMaNXB.add(txtMaNXB);
		pnMain.add(pnMaNXB);
		
		JPanel pnTenNXB = new JPanel();
		pnTenNXB.setLayout(new FlowLayout());
		JLabel lblTenNXb = new JLabel("Tên NXB");
		txtTenNXB = new JTextField(20);
		pnTenNXB.add(lblTenNXb);
		pnTenNXB.add(txtTenNXB);
		pnMain.add(pnTenNXB);
		
		//nâng cấp
		JPanel pnSDT = new JPanel();
		pnSDT.setLayout(new FlowLayout());
		JLabel lblSDT = new JLabel("Số điện thoại");		
		txtSDT = new JTextField(20);
		pnSDT.add(lblSDT);
		pnSDT.add(txtSDT);
		pnMain.add(pnSDT);
		
		JPanel pnDiaChi = new JPanel();
		pnDiaChi.setLayout(new FlowLayout());
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		txtDiaChi = new JTextField(20);
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(txtDiaChi);
		pnMain.add(pnDiaChi);
		
		JPanel pnEmail = new JPanel();
		pnEmail.setLayout(new FlowLayout());
		JLabel lblEmail = new JLabel("Giá Bán");
		txtEmail = new JTextField(20);
		pnEmail.add(lblEmail);
		pnEmail.add(txtEmail);
		pnMain.add(pnEmail);
		
		JPanel pnBtn = new JPanel();
		pnBtn.setLayout(new FlowLayout());
		btnUpdate = new JButton("Chỉnh sửa");
		btnUpdate.setPreferredSize(new Dimension(120, 40));
		btnNhapLai = new JButton("Nhập lại");
		btnNhapLai.setPreferredSize(new Dimension(120, 40));
		pnBtn.add(btnUpdate);
		pnBtn.add(btnNhapLai);
		pnMain.add(pnBtn);
		
		lblMaNXB.setPreferredSize(lblSDT.getPreferredSize());
		lblTenNXb.setPreferredSize(lblSDT.getPreferredSize());
		lblDiaChi.setPreferredSize(lblSDT.getPreferredSize());
		lblEmail.setPreferredSize(lblSDT.getPreferredSize());
		
		
		
	}
	public void showWindow()
	{
		this.setSize(500, 500);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

}
