package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;



import Connect.NhanVien_Connect;
import Model.NhanVien;

public class QuanLyNhanVienUI extends JDialog {
	
	private DefaultTableModel dtmNhanVien ;
	private JTable tblNhanVien ;
	private JTextField txtMaNV , txtTenNV , txtNgaySinh , txtNgayVaoLam , txtSCM , txtMaCV , txtSDT , txtTrangThai , txtEMail ;
	private JButton btnThemMoi , btnNhaplai, btnXoa , btnThoat ;
	private ArrayList<NhanVien> nhanViens =null;
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private DateFormat sdfsql = new SimpleDateFormat("yyyy/MM/dd");
	private JTextField txtTim ;	
	private JButton btnTim , btnToanBo;
	private ArrayList<NhanVien> dsnv_tim = null;
	
	public QuanLyNhanVienUI(String title) {
		this.setTitle(title);	
		addControls();
		addEvents();
		
		hienThiToanBoNhanVien();
		
	}

	private void hienThiToanBoNhanVien() {
		NhanVien_Connect nv_conn = new NhanVien_Connect();
		nhanViens = nv_conn.layToanBoNhanVien();		
		dtmNhanVien.setRowCount(0);
		for(NhanVien nv : nhanViens)
		{
			Vector<Object> vec = new Vector<Object>();
			vec.add(nv.getMaNV());
			vec.add(nv.getTenNV());
			vec.add(nv.getNgaySinh());
			vec.add(nv.getNgayVaolam());
			vec.add(nv.getSoChungMinh());
			vec.add(nv.getMaCV());
			vec.add(nv.getSDT());
			vec.add(nv.getTrangThai());
			vec.add(nv.getEmail());
			dtmNhanVien.addRow(vec);
		}
		
		
	}

	private void addEvents() {
		// sự kiện thoát 
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		//sự kiện xóa trắng để nhập lại
		btnNhaplai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtMaNV.setText("");
				txtTenNV.setText("");
				txtNgaySinh.setText("");
				txtNgayVaoLam.setText("");
				txtSCM.setText("");
				txtMaCV.setText("");
				txtSDT.setText("");
				txtTrangThai.setText("");
				txtEMail.setText("");
				
			}
		});
		
		// sự kiện nhấp vào bảng hiển thị thông tin lên các txt
		tblNhanVien.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				/////////////////
				int select = tblNhanVien.getSelectedRow();
				txtMaNV.setText(tblNhanVien.getValueAt(select, 0)+"");
				txtTenNV.setText(tblNhanVien.getValueAt(select, 1)+"");
				txtNgaySinh.setText(tblNhanVien.getValueAt(select, 2)+"");
				txtNgayVaoLam.setText(tblNhanVien.getValueAt(select, 3)+"");
				txtSCM.setText(tblNhanVien.getValueAt(select, 4)+"");
				txtMaCV.setText(tblNhanVien.getValueAt(select, 5)+"");
				txtSDT.setText(tblNhanVien.getValueAt(select, 6)+"");
				txtTrangThai.setText(tblNhanVien.getValueAt(select, 7)+"");
				txtEMail.setText(tblNhanVien.getValueAt(select, 8)+"");
				
			}
		});
		//sự kiện xóa 1 nhân viên
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String manv = txtMaNV.getText();
				NhanVien_Connect nv_conn = new NhanVien_Connect();
				int ret = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.OK_CANCEL_OPTION);
				if(ret==JOptionPane.OK_OPTION)
				{
					int active = nv_conn.xoaNhanVien(manv);
					if(active>0) 
					{
						JOptionPane.showMessageDialog(null, "Xóa thành công!");
						hienThiToanBoNhanVien();
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Xóa thất bại!");
					}
				}				
				
			}
		});
		//sự kiện Thêm mới || chỉnh sửa thông tin nhân viên
		btnThemMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String maNV = txtMaNV.getText();				
				try {
					xuLyThemMoi(maNV);
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
				
				
			}
		});
		//sự kiện tìm nhân viên
		btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String key = txtTim.getText();
				NhanVien_Connect nv_conn = new NhanVien_Connect();
				dsnv_tim = nv_conn.timNhanVien(key);
				dtmNhanVien.setRowCount(0);
				for(NhanVien nv : dsnv_tim)
				{
					Vector<Object> vec = new Vector<Object>();
					vec.add(nv.getMaNV());
					vec.add(nv.getTenNV());
					vec.add(nv.getNgaySinh());
					vec.add(nv.getNgayVaolam());
					vec.add(nv.getSoChungMinh());
					vec.add(nv.getMaCV());
					vec.add(nv.getSDT());
					vec.add(nv.getTrangThai());
					vec.add(nv.getEmail());
					dtmNhanVien.addRow(vec);
				}
				
			}
		});
		//
		btnToanBo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hienThiToanBoNhanVien();
				
			}
		});
		//
		txtTim.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) 
				{ 
					
					if(txtTim.getText()==null ) return ;
					String key = txtTim.getText();
					NhanVien_Connect nv_conn = new NhanVien_Connect();
					dsnv_tim = nv_conn.timNhanVien(key);
					dtmNhanVien.setRowCount(0);
					for(NhanVien nv : dsnv_tim)
					{
						Vector<Object> vec = new Vector<Object>();
						vec.add(nv.getMaNV());
						vec.add(nv.getTenNV());
						vec.add(nv.getNgaySinh());
						vec.add(nv.getNgayVaolam());
						vec.add(nv.getSoChungMinh());
						vec.add(nv.getMaCV());
						vec.add(nv.getSDT());
						vec.add(nv.getTrangThai());
						vec.add(nv.getEmail());
						dtmNhanVien.addRow(vec);
					}
					
				}
				
			}
		});
	}

	protected void xuLyThemMoi(String maNV) throws ParseException {
		java.util.Date date = df.parse(txtNgaySinh.getText());
		java.util.Date datevaolam = df.parse(txtNgayVaoLam.getText());
		
//		JOptionPane.showMessageDialog(null, sdfsql.format(date));
		
		
		NhanVien nv = new NhanVien();
		nv.setMaNV(txtMaNV.getText());
		nv.setTenNV(txtTenNV.getText());
		nv.setNgaySinh( sdfsql.format(date));
		nv.setNgayVaolam(sdfsql.format(datevaolam));
		nv.setSoChungMinh(txtSCM.getText());
		nv.setMaCV(txtMaCV.getText());
		nv.setSDT(txtSDT.getText());
		nv.setTrangThai(txtTrangThai.getText());
		nv.setEmail(txtEMail.getText());
		NhanVien_Connect nv_conn = new NhanVien_Connect();
		if(nv_conn.kiemTraTonTai(maNV)==true)
		{
			
			int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn chỉnh sửa nhân viên?", "xác nhận chỉnh sửa", JOptionPane.OK_CANCEL_OPTION);
			if(ret==JOptionPane.OK_OPTION)
			{
				int activeUpdate = nv_conn.updateNhanVien(nv);
				if(activeUpdate>0)
				{
					JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công!");
					hienThiToanBoNhanVien();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Chỉnh sửa thất bại!");
				}
			}
		}
		else 
		{
			int activeLuu = nv_conn.themNhanVien(nv);
			if(activeLuu>0)
			{
				JOptionPane.showMessageDialog(null, "Thêm mới thành công!");
				hienThiToanBoNhanVien();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Thêm mới thất bại!");
			}
			
			
		}
		
		
	}

	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new FlowLayout());
		JLabel lblTitle = new JLabel("Quản lý nhân viên");
		lblTitle.setFont(new Font("segou ui", Font.BOLD, 30));
		lblTitle.setForeground(Color.BLUE);
		pnTitle.add(lblTitle);
		con.add(pnTitle, BorderLayout.NORTH);
		
		JPanel pnTop = new JPanel();		
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		pnBottom.setPreferredSize(new Dimension(0, 400));
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnTop, pnBottom);
		con.add(sp,BorderLayout.CENTER);
//		pnTop.setPreferredSize(new Dimension(600 , 0));
		
		JPanel pnBang = new JPanel();
		pnBang.setLayout(new BorderLayout());
		pnBottom.setLayout(new BorderLayout());		
		dtmNhanVien = new DefaultTableModel();
		dtmNhanVien.addColumn("Mã nhân viên");
		dtmNhanVien.addColumn("Tên nhân viên");
		dtmNhanVien.addColumn("Ngày Sinh");
		dtmNhanVien.addColumn("Ngày vào làm");
		dtmNhanVien.addColumn("Số chứng minh");
		dtmNhanVien.addColumn("Mã CV");
		dtmNhanVien.addColumn("Số điện thoại");
		dtmNhanVien.addColumn("Trạng thái");
		dtmNhanVien.addColumn("Email");
		tblNhanVien = new JTable(dtmNhanVien);
		JScrollPane scNhanVien = new JScrollPane(tblNhanVien,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnBang.add(scNhanVien ,BorderLayout.CENTER);
		pnBottom.add(pnBang,BorderLayout.CENTER);
		
		////////
		pnTop.setLayout(new BorderLayout());
		JPanel pnLeft = new JPanel();	
//		pnLeft.setPreferredSize(new Dimension(500, 500));
		pnTop.add(pnLeft, BorderLayout.CENTER);

		
		pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));
		Border boderLeft = BorderFactory.createLineBorder(Color.red);
		TitledBorder titleBorderLeft = new TitledBorder(boderLeft,"Thông tin Nhân Viên");
		pnLeft.setBorder(titleBorderLeft);
		
		JPanel pndong1 = new JPanel();
		pndong1.setLayout(new FlowLayout());
		JLabel lblMaNV = new JLabel("Mã Nhân Viên");
		lblMaNV.setFont(new Font("segou ui", Font.BOLD, 16));
		txtMaNV = new JTextField(15);
		txtMaNV.setPreferredSize(new Dimension(100, 40));
		JLabel lblTenNV = new JLabel("Tên Nhân Viên");
		lblTenNV.setFont(new Font("segou ui", Font.BOLD, 16));
		txtTenNV = new JTextField(15);
		txtTenNV.setPreferredSize(new Dimension(100, 40));
		JLabel lblNgaySinh = new JLabel("Ngày Sinh");
		lblNgaySinh.setPreferredSize(new Dimension(100, 40));
		txtNgaySinh = new JTextField(15);
		txtNgaySinh.setPreferredSize(new Dimension(100, 40));
		pndong1.add(lblMaNV);
		pndong1.add(txtMaNV);
		pndong1.add(lblTenNV);
		pndong1.add(txtTenNV);
		pndong1.add(lblNgaySinh);
		pndong1.add(txtNgaySinh);
		pnLeft.add(pndong1);
		
		JPanel pndong2 = new JPanel();
		pndong2.setLayout(new FlowLayout());
		JLabel lblNgayVaoLam= new JLabel("Ngày vào làm");
		txtNgayVaoLam = new JTextField(15);
		txtNgayVaoLam.setPreferredSize(new Dimension(100, 40));
		JLabel lblSCM = new JLabel("Số chứng minh");
		txtSCM = new JTextField(15);
		txtSCM.setPreferredSize(new Dimension(100, 40));
		JLabel lblMaCV = new JLabel("Tên công việc");
		txtMaCV = new JTextField(15);
		txtMaCV.setPreferredSize(new Dimension(100, 40));
		pndong2.add(lblNgayVaoLam);
		pndong2.add(txtNgayVaoLam);
		pndong2.add(lblSCM);
		pndong2.add(txtSCM);
		pndong2.add(lblMaCV);
		pndong2.add(txtMaCV);
		pnLeft.add(pndong2);
		
		JPanel pndong3 = new JPanel();
		pndong3.setLayout(new FlowLayout());
		JLabel lblSDT = new JLabel("Số điện thoại");
		txtSDT = new JTextField(15);
		txtSDT.setPreferredSize(new Dimension(100, 40));
		JLabel lblTrangThai = new JLabel("Trạng thái");
		txtTrangThai = new JTextField(15);
		txtTrangThai.setPreferredSize(new Dimension(100, 40));
		JLabel lblEmail = new JLabel("Email");
		txtEMail = new JTextField(15);
		txtEMail.setPreferredSize(new Dimension(100, 40));
		pndong3.add(lblSDT);
		pndong3.add(txtSDT);
		pndong3.add(lblTrangThai);
		pndong3.add(txtTrangThai);
		pndong3.add(lblEmail);
		pndong3.add(txtEMail);
		pnLeft.add(pndong3);
		
		JPanel pnBtn = new JPanel();
		pnBtn.setLayout(new FlowLayout());
		btnThemMoi = new JButton("Thêm mới");
		btnThemMoi.setPreferredSize(new Dimension(100, 40));
		btnXoa = new JButton("Xóa");
		btnXoa.setPreferredSize(new Dimension(100, 40));
		btnNhaplai = new JButton("Nhập lại");
		btnNhaplai.setPreferredSize(new Dimension(100, 40));
		btnThoat = new JButton("Thoát");
		btnThoat.setPreferredSize(new Dimension(100, 40));
		pnBtn.add(btnThemMoi);
		pnBtn.add(btnXoa);
		pnBtn.add(btnNhaplai);
		pnBtn.add(btnThoat);
		pnLeft.add(pnBtn);
		
		lblNgaySinh.setFont(new Font("segou ui", Font.BOLD, 16));
		lblNgayVaoLam.setFont(new Font("segou ui", Font.BOLD, 16));
		lblSCM.setFont(new Font("segou ui", Font.BOLD, 16));
		lblMaCV.setFont(new Font("segou ui", Font.BOLD, 16));
		lblSDT.setFont(new Font("segou ui", Font.BOLD, 16));
		lblTrangThai.setFont(new Font("segou ui", Font.BOLD, 16));
		lblEmail.setFont(new Font("segou ui", Font.BOLD, 16));
		
		lblSDT.setPreferredSize(lblSCM.getPreferredSize());
		lblMaNV.setPreferredSize(lblSCM.getPreferredSize());
		lblTenNV.setPreferredSize(lblSCM.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblSCM.getPreferredSize());
		lblNgayVaoLam.setPreferredSize(lblSCM.getPreferredSize());
		lblMaCV.setPreferredSize(lblSCM.getPreferredSize());
		lblSDT.setPreferredSize(lblSCM.getPreferredSize());
		lblTrangThai.setPreferredSize(lblSCM.getPreferredSize());
		lblEmail.setPreferredSize(lblSCM.getPreferredSize());
		
		JPanel pnRight = new JPanel();
		pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));
		pnTop.add(pnRight,BorderLayout.WEST);
		Border boderRight = BorderFactory.createLineBorder(Color.RED);
		TitledBorder titleborderRight = new TitledBorder(boderRight, "Tìm Nhân Viên");
		pnRight.setBorder(titleborderRight);
		
		JPanel pnTim = new JPanel();
		pnTim.setLayout(new FlowLayout());
		JLabel lblTim = new JLabel("Nhập tên hoặc mã");
		lblTim.setFont(new Font("segou ui", Font.BOLD, 15));
		txtTim = new JTextField(15);
		txtTim.setPreferredSize(new Dimension(100, 40));
		pnTim.add(lblTim);
		pnTim.add(txtTim);
		pnRight.add(pnTim);
		
		JPanel pnBtnTim = new JPanel();
		pnBtnTim.setLayout(new FlowLayout());
		btnTim = new JButton("Tìm Kiếm");
		btnTim.setPreferredSize(new Dimension(100, 40));
		btnToanBo =new JButton("Tất cả");
		btnToanBo.setPreferredSize(new Dimension(100, 40));
		pnBtnTim.add(btnTim);
		pnBtnTim.add(btnToanBo);		
		pnRight.add(pnBtnTim);
		
		
		
	}
	public void showWindow()
	{
		this.setSize(1250, 800);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
}
