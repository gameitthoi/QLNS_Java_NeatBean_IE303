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
import javax.swing.ImageIcon;

public class ThemMoiNXB extends JDialog {
	private JTextField txtMaNXB, txtTenNXB , txtSDT , txtDiaChi,txtEmail ;
	private JButton btnThemMoi , btnNhapLai ;
	private ArrayList<NXB> dsnxb;
	
	public ThemMoiNXB(String title) {
		this.setTitle(title);
		addControls();
		addEvents();
		
	}
	
	private void addEvents() 
	{
		btnNhapLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtMaNXB.setText("");
				txtTenNXB.setText("");
				txtSDT.setText("");
				txtDiaChi.setText("");
				txtEmail.setText("");
				txtMaNXB.requestFocus();
			}
		});
		
		//ham them moi nxb
		btnThemMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtMaNXB.getText().trim().length()==0) return ;
				
				xuLyThemMoiNXB();
				
				
			}
		});
	}


	protected void xuLyThemMoiNXB() {
		NXB nxb = new NXB();
		nxb.setMaNXB(txtMaNXB.getText());
		nxb.setTenNXB(txtTenNXB.getText());
		nxb.setSDT(txtSDT.getText());
		nxb.setDiaChi(txtDiaChi.getText());
		nxb.setEmail(txtEmail.getText());
		nxb.setIsDelete(0);			
		
		
		NXB_Connect nxb_con = new NXB_Connect();
		int active = nxb_con.ThemMoiNXB(nxb);
		if(active>0)
		{
			JOptionPane.showMessageDialog(null, "Thêm mới thành công");
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
		
		JPanel pnMaNXb = new JPanel();
		pnMaNXb.setLayout(new FlowLayout());
		JLabel lblMaNXB = new JLabel("Mã NXB");		
		txtMaNXB = new JTextField(20);
		
		pnMaNXb.add(lblMaNXB);
		pnMaNXb.add(txtMaNXB);
		pnMain.add(pnMaNXb);
		
		JPanel pnTenNXB = new JPanel();
		pnTenNXB.setLayout(new FlowLayout());
		JLabel lblTenNXB = new JLabel("Tên NXB");
		txtTenNXB = new JTextField(20);
		pnTenNXB.add(lblTenNXB);
		pnTenNXB.add(txtTenNXB);
		pnMain.add(pnTenNXB);
		
		JPanel pnSDT = new JPanel();
		pnSDT.setLayout(new FlowLayout());
		JLabel lblSDT = new JLabel("SĐT");
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
		JLabel lblEmail = new JLabel("Email");
		txtEmail = new JTextField(20);
		pnEmail.add(lblEmail);
		pnEmail.add(txtEmail);
		pnMain.add(pnEmail);	
		
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
		
		lblMaNXB.setPreferredSize(lblTenNXB.getPreferredSize());
		lblSDT.setPreferredSize(lblTenNXB.getPreferredSize());
		lblDiaChi.setPreferredSize(lblTenNXB.getPreferredSize());
		lblEmail.setPreferredSize(lblTenNXB.getPreferredSize());
		
		
		
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
