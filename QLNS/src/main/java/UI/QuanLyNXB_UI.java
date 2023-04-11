package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connect.NXB_Connect;
import Model.NXB;
import javax.swing.ImageIcon;

public class QuanLyNXB_UI extends JDialog {
	private JButton btnThemMoi , btnChinhSua , btnXoa , btnTim , btnThoat;
	private JTextField txtTim ;
	private JComboBox<NXB> cboNXB ;
	private DefaultTableModel dtmNXB ;
	private JTable tblNXB ;
	private ArrayList<NXB> dsnxb=null;
	private ArrayList<NXB> dsnxb_Tim =null;
	
	
	public QuanLyNXB_UI(String title) {
		this.setTitle(title);
		addControls();
		addEvents();
		hienThiToanBoNhaXuatBan();
	}
//hàm hiển thị toàn bộ nhà xuất bản vào bảng
	private void hienThiToanBoNhaXuatBan() {
		NXB_Connect nxbconn = new NXB_Connect();
		dsnxb=nxbconn.layToanBoNhaXuatBan();
		dtmNXB.setRowCount(0);
		for(NXB nxb : dsnxb)
		{
			Vector<Object> vec = new Vector<Object>();
			vec.add(nxb.getMaNXB());
			vec.add(nxb.getTenNXB());
			vec.add(nxb.getSDT());
			vec.add(nxb.getDiaChi());
			vec.add(nxb.getEmail());
			dtmNXB.addRow(vec);
		}
		
		
	}

	private void addEvents() {
		// sự kiện cho btn tìm
		btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtTim.getText().trim().length()==0) return ;
				xuLyTim();
								
			}
		});
		 
		// hàm sự kiện cho btn thoát // ?? 
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		//sự kiện cho btn thêm mới
		btnThemMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ThemMoiNXB themmoinxb = new ThemMoiNXB("Thêm mới NXb");
				themmoinxb.showWindow();
				hienThiToanBoNhaXuatBan();
				
			}
		});
		
		// Sự kiện xóa 1 nxb
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int select = tblNXB.getSelectedRow();
				if(select==-1) return ;
				int ret = JOptionPane.showConfirmDialog(null, "Bạn Chắc chắn muốn xóa NXB" + tblNXB.getValueAt(select, 0) , 
						"Xác nhận Xóa", JOptionPane.YES_NO_OPTION);
				if(ret==JOptionPane.YES_OPTION)
				{
					xuLyXoa(select);
					hienThiToanBoNhaXuatBan();
				}
				
				
				
			}
		});
		//Hàm chỉnh sửa NXB 
		btnChinhSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int select = tblNXB.getSelectedRow();
				NXB nxb = new NXB();
				nxb.setMaNXB( tblNXB.getValueAt(select, 0)+"");
				nxb.setTenNXB(tblNXB.getValueAt(select, 1)+"");
				nxb.setSDT(tblNXB.getValueAt(select, 2)+"");
				nxb.setDiaChi(tblNXB.getValueAt(select, 3)+"");
				nxb.setEmail(tblNXB.getValueAt(select, 4)+"");
				nxb.setIsDelete(0);
				ChinhSuaNXB_UI chinhsuaUI = new ChinhSuaNXB_UI("", nxb);
				chinhsuaUI.showWindow();
				hienThiToanBoNhaXuatBan();
			}
		});
	}
	
	protected void xuLyXoa(int select) {
		String manxb = (String) tblNXB.getValueAt(select, 0);
		NXB_Connect nxb_conn = new NXB_Connect();
		int active = nxb_conn.XoaNXB(manxb);
		if(active>0)
		{
			JOptionPane.showMessageDialog(null, "Xóa nhà xuất bản thành công");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Xóa Thất bại");
		}
		
	}
	// hàm xử lý tìm kiếm theo tên
	protected void xuLyTim() {
		String ten = txtTim.getText();
		NXB_Connect nxb_con = new NXB_Connect();
		dsnxb_Tim = nxb_con.layNhaXuatBanTheoTen(ten);
//		if(dsnxb_Tim!=null) JOptionPane.showMessageDialog(null, "sss");
		dtmNXB.setRowCount(0);
		for(NXB nxb : dsnxb_Tim)
		{
			Vector<Object> vec = new Vector<Object>();
			vec.add(nxb.getMaNXB());
			vec.add(nxb.getTenNXB());
			vec.add(nxb.getSDT());
			vec.add(nxb.getDiaChi());
			vec.add(nxb.getEmail());
			dtmNXB.addRow(vec);
		}
					
		
	}

	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.Y_AXIS));
		con.add(pnTop,BorderLayout.NORTH);
		
		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new BoxLayout(pnTitle, BoxLayout.X_AXIS));
		
		JPanel pnTitleLeft = new JPanel();
		pnTitleLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTitle = new JLabel("Quản Lý Nhà Xuất Bản");
		lblTitle.setFont(new Font("Segoe UI",Font.BOLD, 24));
		lblTitle.setPreferredSize(new Dimension(500, 40));
		lblTitle.setForeground(Color.BLUE);
		pnTitleLeft.add(lblTitle);
		pnTitle.add(pnTitleLeft);
		
		JPanel pnTitleRight = new JPanel();
		pnTitleRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnThemMoi = new JButton("Thêm NXB");
		btnThemMoi.setPreferredSize(new Dimension(150, 40));
		btnChinhSua = new JButton("Chỉnh sửa NXB");
                btnChinhSua.setIcon(new ImageIcon("images/edit.png"));
		btnChinhSua.setPreferredSize(new Dimension(150, 40));
		btnXoa = new JButton("Xóa");
		btnXoa.setPreferredSize(new Dimension(150, 40));
		pnTitleRight.add(btnThemMoi);
		pnTitleRight.add(btnChinhSua);
		pnTitleRight.add(btnXoa);
		pnTitle.add(pnTitleRight);
		
		pnTop.add(pnTitle);
		
		// panel tim kiem
		JPanel pnTimKiem = new JPanel() ;
		pnTimKiem.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		txtTim = new JTextField(30);
		txtTim.setPreferredSize(new Dimension(300, 40));

		btnThoat = new JButton("Thoát");
		btnThoat.setPreferredSize(new Dimension(150, 40));
		btnTim = new JButton("Tìm Kiếm");
                btnTim.setIcon(new ImageIcon("images/search.png"));
		btnTim.setPreferredSize(new Dimension(150, 40));
		JLabel lblKhoangTrang1 =new JLabel("") ;
		lblKhoangTrang1.setPreferredSize(new Dimension(50, 40));
		JLabel lblKhoangTrang2 =new JLabel("") ;
		lblKhoangTrang2.setPreferredSize(new Dimension(50, 40));
		
		pnTimKiem.add(txtTim);
//		pnTimKiem.add(lblKhoangTrang1);		
//		pnTimKiem.add(lblKhoangTrang2);
		pnTimKiem.add(btnTim);
		pnTimKiem.add(btnThoat);
		pnTop.add(pnTimKiem);
		
		
		// panel bang sach
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		con.add(pnCenter,BorderLayout.CENTER);
		
		dtmNXB = new DefaultTableModel();
		dtmNXB.addColumn("Mã NXB");
		dtmNXB.addColumn("Tên NXB");
		dtmNXB.addColumn("SĐT");
		dtmNXB.addColumn("Địa Chỉ");
		dtmNXB.addColumn("Email");
		
	
		tblNXB = new JTable(dtmNXB);
		JScrollPane scSach = new JScrollPane(tblNXB, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(scSach, BorderLayout.CENTER);
		
	}
	public void showWindow()	{
	
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
