package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connect.NXB_Connect;
import Connect.Sach_Connect;
import Model.NXB;
import Model.NhanVien;
import Model.Sach;
import javax.swing.ImageIcon;


public class QuanLySachUI extends JDialog {
	
	private JButton btnThemMoi , btnChinhSua, btnXoa  , btnTim;
	private JTextField txtTenMa ;
	private JComboBox<NXB> cboNXB;
	private DefaultTableModel dtmSach ;
	private JTable tblSach ;
	private ArrayList<NXB> dsnxb = null;
	private ArrayList<Sach> dss = null;
	private ArrayList<Sach> dssMaTen = null;
	private ArrayList<Sach> dss_nxb = null;
	private ArrayList<Sach> dss_tensach = null;
	private DecimalFormat df = new DecimalFormat("###,###,###");
	private DecimalFormat dfnull = new DecimalFormat("#########");
	
	public QuanLySachUI(String title ) {
		this.setTitle(title);
		addControls();
		addEvents();
		
		hienThiToanBoNhaXuatBan();
		hienThiToanBoSach();
	}

	private void hienThiToanBoSach() {
		Sach_Connect sachConn = new Sach_Connect();
		dss = sachConn.layToanBoSach();
		dtmSach.setRowCount(0);
		
		for (Sach s : dss)
		{
			Vector<Object> vec = new Vector<Object>();
			vec.add(s.getMaSach());
			vec.add(s.getTenSach());
			vec.add(s.getMaNXB());
			vec.add(s.getTacGia());
			vec.add(s.getGiaBan());
			vec.add(s.getTheLoai());
			vec.add(s.getSoLuong());	
						
			dtmSach.addRow(vec);
			
		}
		
	}

	private void hienThiToanBoNhaXuatBan() {
		NXB_Connect nxbconn = new NXB_Connect();
		dsnxb=nxbconn.layToanBoNhaXuatBan();
//		if(dsnxb!=null) JOptionPane.showMessageDialog(null, "ss");
		cboNXB.removeAllItems();
		NXB nll = new NXB();
		nll.setMaNXB("0");
		cboNXB.addItem(nll);
		for(NXB s : dsnxb)
		{
			cboNXB.addItem(s);
		}
		
		
	}

	private void addEvents() {
		//btntimkiem 
		btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				int select = cboNXB.getSelectedIndex();				
//				if(txtTenMa.getText().length()==0  && cboNXB.getSelectedItem().toString().length()==0) return ;
//				JOptionPane.showMessageDialog(null, "sss");
				xuLyTim();
				
			}
		});
		
		//btn Them Moi
		btnThemMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ThemMoiSachUI themmoi = new ThemMoiSachUI("Thêm mới sản phẩm");
				themmoi.showWindow();
				hienThiToanBoSach();
				
			}
		});
	
		// xoa sach
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int select = tblSach.getSelectedRow();
				if(select==-1)  return ;
				String maSach = (String) tblSach.getValueAt(select, 0);
//				JOptionPane.showMessageDialog(null,tblSach.getValueAt(select, 0) );
				int active = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa", "Xác Nhận Xóa", JOptionPane.OK_CANCEL_OPTION);
				if(active==JOptionPane.OK_OPTION)
				{
					xuLyXoa(maSach);
//					JOptionPane.showMessageDialog(null,maSach );
				}
				
			}
		});
		// chinh sua thong tin sach
		btnChinhSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int select = tblSach.getSelectedRow();
				if(select==-1) return ;
				
				Sach s = new Sach();
				s.setMaSach((String) tblSach.getValueAt(select, 0));
				s.setTenSach((String) tblSach.getValueAt(select, 1));
				s.setMaNXB((String) tblSach.getValueAt(select, 2));
				s.setTacGia((String) tblSach.getValueAt(select, 3));
				s.setGiaBan( Double.parseDouble(tblSach.getValueAt(select, 4)+"") );
				s.setTheLoai((String) tblSach.getValueAt(select, 5));
				s.setSoLuong( (int) tblSach.getValueAt(select, 6));
				s.setIsDelete(0);
				
				ChinhSuaSach_UI chinhsua= new ChinhSuaSach_UI("Chỉnh sửa sách" ,s);
				chinhsua.showWindow();
				hienThiToanBoSach();
				
			}
		});
		
	}
	
	
	protected void xuLyXoa(String maSach) {
		Sach_Connect sachXoa = new Sach_Connect();
		int active= sachXoa.XoaSach(maSach);
		if(active > 0)
		{
			JOptionPane.showMessageDialog(null, "Xóa thành công sách:");
			hienThiToanBoSach();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Xóa thất bại");	
		}
		
	}

	// hàm xử lý tìm sách
	protected void xuLyTim() 
	{
		NXB nxb = (NXB) cboNXB.getSelectedItem();
		String ten = txtTenMa.getText();

		Sach_Connect sachnxb1 = new Sach_Connect();
		dss_tensach = sachnxb1.laySachTheoMaTen(ten);
		dss_nxb = sachnxb1.laySachTheoNXBTen(nxb.getMaNXB(), txtTenMa.getText());
		dssMaTen = sachnxb1.laySachTheoNXB(nxb.getMaNXB());
		
		if (txtTenMa.getText().length()!= 0 && nxb.getMaNXB().length()==1)
		{
//			JOptionPane.showMessageDialog(null, "s");
			dtmSach.setRowCount(0);
			for(Sach s : dss_tensach)
			{
				Vector<Object> vec = new Vector<Object>();			
					
					vec.add(s.getMaSach());
					vec.add(s.getTenSach());
					vec.add(s.getMaNXB());
					vec.add(s.getTacGia());
					vec.add(s.getGiaBan());
					vec.add(s.getTheLoai());
					vec.add(s.getSoLuong());
					
				
				dtmSach.addRow(vec);
			}
		}
		else if (txtTenMa.getText().length()!= 0 && nxb.getMaNXB().length()==5)	
		{
			
			dtmSach.setRowCount(0);
			for(Sach s : dss_nxb)
			{
				Vector<Object> vec = new Vector<Object>();			
					
						vec.add(s.getMaSach());
						vec.add(s.getTenSach());
						vec.add(s.getMaNXB());
						vec.add(s.getTacGia());
						vec.add(s.getGiaBan());
						vec.add(s.getTheLoai());
						vec.add(s.getSoLuong());
								
									
				dtmSach.addRow(vec);
			}
			
		}
		else if (txtTenMa.getText().length()== 0 && nxb.getMaNXB().length()==5)
		{
//			JOptionPane.showMessageDialog(null, "s");
//			if(dssMaTen!= null) JOptionPane.showMessageDialog(null, "s");
			dtmSach.setRowCount(0);
			for(Sach s : dssMaTen)
			{
				Vector<Object> vec = new Vector<Object>();			
					
						vec.add(s.getMaSach());
						vec.add(s.getTenSach());
						vec.add(s.getMaNXB());
						vec.add(s.getTacGia());
						vec.add(s.getGiaBan());
						vec.add(s.getTheLoai());
						vec.add(s.getSoLuong());
								
									
				dtmSach.addRow(vec);
			}
		}
		else if(txtTenMa.getText().length()== 0 && nxb.getMaNXB().length()==1)
		{
			hienThiToanBoSach();
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
		JLabel lblTitle = new JLabel("Quản Lý Sách");
		lblTitle.setFont(new Font("Segoe UI",Font.BOLD, 24));
		lblTitle.setPreferredSize(new Dimension(500, 40));
		lblTitle.setForeground(Color.BLUE);
		pnTitleLeft.add(lblTitle);
		pnTitle.add(pnTitleLeft);
		
		JPanel pnTitleRight = new JPanel();
		pnTitleRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnThemMoi = new JButton("Thêm Sách Mới");
		btnThemMoi.setPreferredSize(new Dimension(150, 40));
		btnChinhSua = new JButton("Chỉnh sửa Sách");
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
		
		txtTenMa = new JTextField(30);
		txtTenMa.setPreferredSize(new Dimension(300, 40));
		cboNXB = new JComboBox<NXB>();
		cboNXB.setPreferredSize(new Dimension(200, 40));
		btnTim = new JButton("Tìm Kiếm");
                btnTim.setIcon(new ImageIcon("images/search.png"));
		btnTim.setPreferredSize(new Dimension(150, 40));
		JLabel lblKhoangTrang1 =new JLabel("") ;
		lblKhoangTrang1.setPreferredSize(new Dimension(50, 40));
		JLabel lblKhoangTrang2 =new JLabel("") ;
		lblKhoangTrang2.setPreferredSize(new Dimension(50, 40));
		
		pnTimKiem.add(txtTenMa);
		pnTimKiem.add(lblKhoangTrang1);
		pnTimKiem.add(cboNXB);
		pnTimKiem.add(lblKhoangTrang2);
		pnTimKiem.add(btnTim);
		pnTop.add(pnTimKiem);
		
		
		// panel bang sach
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		con.add(pnCenter,BorderLayout.CENTER);
		
		dtmSach = new DefaultTableModel();
		dtmSach.addColumn("Mã Sách");
		dtmSach.addColumn("Tên Sách");
		dtmSach.addColumn("Mã NXB");
		dtmSach.addColumn("Tác Giả");
		dtmSach.addColumn("Giá Bán");
		dtmSach.addColumn("Thể loại");
		dtmSach.addColumn("Số lượng");
	
		tblSach = new JTable(dtmSach);
		JScrollPane scSach = new JScrollPane(tblSach, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(scSach, BorderLayout.CENTER);
		
		
		
		
		
		
	}
	public void showWindow()
	{
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
