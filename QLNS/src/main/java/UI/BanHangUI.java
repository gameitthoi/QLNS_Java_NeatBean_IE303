package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import Connect.CTHD_Connect;
import Connect.HoaDon_Connect;
import Connect.NXB_Connect;
import Connect.Sach_Connect;
import Connect.login_sql;
import Model.CTHD;
import Model.HoaDon;
import Model.NXB;
import Model.Sach;

public class BanHangUI extends JFrame {
	private JMenuBar mnuBar ;
	private JMenu mnuFile , mnuQuanLy , mnuExit , mnuThongKe ;
	private JMenuItem itemQuanLySach  , itemQuanLyNXB , itemDangXuat ,itemExit , itemQuanLyNhanVien;
	private JButton btnTimKiem ;
	private JTextField txtTenMa ;
	private JComboBox<NXB> cboTheLoai;
	private JTable tblSach ,tblHoaDon;
	private DefaultTableModel dtmSach , dtmHoaDon;
	private JButton btnThemVaoHoaDon ;
	private JButton btnThanhToan , btnHuy , btnXoaSach ;
	private JTextField txtTongTien , txtKhachDua , txtTienThua , txtSoLuongMua ;
	private ArrayList<Sach> dss = null;
	
	private String MaNV= null;
	private ArrayList<CTHD> cthd = new ArrayList<CTHD>(); 
	private ArrayList<Sach> dssMaTen = null;
	private ArrayList<Sach> dss_nxb = null;
	private ArrayList<Sach> dss_tensach = null;
	private ArrayList<NXB> dsnxb = null;
	private String MaHD=null;
	
	public BanHangUI(String title,String maNV) {
		this.setTitle(title);
		addControls();
		addEvents();
		hienThiToanBoDoanhSach();
		hienThiToanBoNhaXuatBan();
		MaNV=maNV;
	}
	private void hienThiToanBoNhaXuatBan() {
		NXB_Connect nxbconn = new NXB_Connect();
		dsnxb=nxbconn.layToanBoNhaXuatBan();
//		if(dsnxb!=null) JOptionPane.showMessageDialog(null, "ss");
		cboTheLoai.removeAllItems();
		NXB nll = new NXB();
		nll.setMaNXB("0");
		cboTheLoai.addItem(nll);
		for(NXB s : dsnxb)
		{
			cboTheLoai.addItem(s);
		}	
	}
	
	public void xuLyTim() {
        NXB nxb = (NXB) cboTheLoai.getSelectedItem();
		String ten = txtTenMa.getText();
		
		Sach_Connect sachnxb1 = new Sach_Connect();
		dss_tensach = sachnxb1.laySachTheoMaTen(ten);
		dss_nxb = sachnxb1.laySachTheoNXBTen(nxb.getMaNXB(), txtTenMa.getText());
		dssMaTen = sachnxb1.laySachTheoNXB(nxb.getMaNXB());
		if (txtTenMa.getText().length()==0 && nxb.getMaNXB().length()==1)
		    hienThiToanBoDoanhSach();
		if (txtTenMa.getText().length()!= 0 && nxb.getMaNXB().length()==1)
		{
		//JOptionPane.showMessageDialog(null, "s");
		dtmSach.setRowCount(0);
		for(Sach s : dss_tensach)
		{
		Vector<Object> vec = new Vector<Object>();
			vec.add(s.getMaSach());
			vec.add(s.getTenSach());
			vec.add(s.getSoLuong());
		                        vec.add(s.getTheLoai());
		                        vec.add(s.getGiaBan());
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
                vec.add(s.getSoLuong());
                vec.add(s.getTheLoai());
                vec.add(s.getGiaBan());				
		dtmSach.addRow(vec);
		}
		
		}
		else if (txtTenMa.getText().length()== 0 && nxb.getMaNXB().length()==5)
		{
		//JOptionPane.showMessageDialog(null, "s");
		//if(dssMaTen!= null) JOptionPane.showMessageDialog(null, "s");
		dtmSach.setRowCount(0);
		for(Sach s : dssMaTen)
		{
		Vector<Object> vec = new Vector<Object>();			
			
			vec.add(s.getMaSach());
			vec.add(s.getTenSach());
			vec.add(s.getSoLuong());
		                        vec.add(s.getTheLoai());
		                        vec.add(s.getGiaBan());
						
							
		dtmSach.addRow(vec);
		}
	}
    }
	
	

	

	private void hienThiToanBoDoanhSach() {
		Sach_Connect sachConn = new Sach_Connect();
		dss = sachConn.layToanBoSach();
		dtmSach.setRowCount(0);
		
		for (Sach s : dss)
		{
			Vector<Object> vec = new Vector<Object>();
			vec.add(s.getMaSach());
			vec.add(s.getTenSach());
			vec.add(s.getSoLuong());
			vec.add(s.getTheLoai());
			vec.add(s.getGiaBan());
			
			dtmSach.addRow(vec);
			
		}
		
		
		
	}

	private void addEvents() {
		//quay lai for login
		mnuExit.addMouseListener(new MouseListener() {
			
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
				int ret =JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muôn thoát chương trình?", "Xác Nhận Thoát", JOptionPane.OK_CANCEL_OPTION);
				if(ret==JOptionPane.OK_OPTION)
				{
					System.exit(0);
				}
				
			}
		});
		//
		mnuThongKe.addMouseListener(new MouseListener() {
			
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
				ThongKeUI thongkeui = new ThongKeUI("");
				thongkeui.showWindow();
				
			}
		});
		//
		
		
		//
		itemQuanLySach.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				QuanLySachUI sachui = new QuanLySachUI("QLS");
				sachui.showWindow();
			}
		});
		itemQuanLyNXB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				QuanLyNXB_UI nxbUi = new QuanLyNXB_UI("Quản lý nhà xuất bản");
				nxbUi.showWindow();
				
			}
		});
		/////////
		itemDangXuat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int ret =JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn đăng xuất?", "Xác Nhận Đăng Xuất", JOptionPane.OK_CANCEL_OPTION);
				if(ret==JOptionPane.OK_OPTION)
				{
					dispose();
					LoginUI loginui = new LoginUI("Đăng Nhâp");
					loginui.showWindow();
				}
				
				
			}
		});
		//
		itemExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ret =JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muôn thoát chương trình?", "Xác Nhận Thoát", JOptionPane.OK_CANCEL_OPTION);
				if(ret==JOptionPane.OK_OPTION)
				{
					System.exit(0);
				}
				
			}
		});
		//Sự kiện cho menu item quản lý nhân viên
		itemQuanLyNhanVien.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				QuanLyNhanVienUI nhanVienui = new QuanLyNhanVienUI("");
				nhanVienui.showWindow();
				
			}
		});
		
		// cài đặt phím tắt cho menuitem
		itemDangXuat.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		itemExit.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemQuanLySach.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemQuanLyNXB.setAccelerator(KeyStroke.getKeyStroke('B', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemQuanLyNhanVien.setAccelerator(KeyStroke.getKeyStroke('K', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		mnuBar.requestFocus();
//		mnuExit.setMnemonic(KeyEvent.VK_F);
//		mnuExit.setAccelerator(KeyStroke.getKeyStroke('C', 0));
//		mnuThongKe.setAccelerator(KeyStroke.getKeyStroke('T', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));	
	
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				int select = cboNXB.getSelectedIndex();				
//				if(txtTenMa.getText().length()==0  && cboNXB.getSelectedItem().toString().length()==0) return ;
//				JOptionPane.showMessageDialog(null, "sss");
				xuLyTim();
				
			}
		});
		
		btnThemVaoHoaDon.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                if ((tblSach.getSelectedRow()<0)||(txtSoLuongMua.getText().equals("")) )
                    JOptionPane.showMessageDialog(null, "Chưa chọn và nhập số lượng");
                else {
                if (MaHD==null) TaoHD();
                ThemCTHD();
                }
                
            }

            private void ThemCTHD() {
                Vector<Object> vec = new Vector<Object>();
                CTHD vec1 = new CTHD();
                vec1.setMaHD(MaHD);
                vec1.setMaSach(tblSach.getValueAt(tblSach.getSelectedRow(), 0).toString());
                vec1.setDonGia(Double.parseDouble(tblSach.getValueAt(tblSach.getSelectedRow(), 4).toString()));
                vec1.setIsDelete(0);
                int j=-1;
                double tien = Long.parseLong(txtSoLuongMua.getText())*Double.parseDouble(tblSach.getValueAt(tblSach.getSelectedRow(), 4).toString()); 
                for (int i=0;i<dtmHoaDon.getRowCount();i++) {
                    if (dtmHoaDon.getValueAt(i, 0).equals(tblSach.getValueAt(tblSach.getSelectedRow(), 1))) j=i;}
                if (j!=-1){
                        vec1.setSoLuong(Integer.parseInt(txtSoLuongMua.getText())+Integer.parseInt(dtmHoaDon.getValueAt(j, 1).toString()));
                        vec1.setThanhTien(tien+Double.parseDouble(dtmHoaDon.getValueAt(j, 2).toString()));
                        dtmHoaDon.setValueAt((tien+Double.parseDouble(dtmHoaDon.getValueAt(j, 2).toString())), j, 2);
                        dtmHoaDon.setValueAt(Integer.parseInt(txtSoLuongMua.getText())+Integer.parseInt(dtmHoaDon.getValueAt(j, 1).toString()), j, 1);                               
                        cthd.set(j, vec1);
                }
                    
                    else{                        
	vec.add(tblSach.getValueAt(tblSach.getSelectedRow(), 1));
	vec.add(txtSoLuongMua.getText());                                                                     
                vec.add(tien);
                dtmHoaDon.addRow(vec);                       
                vec1.setSoLuong(Integer.parseInt(txtSoLuongMua.getText()));                        
                vec1.setThanhTien(tien);                        
                cthd.add(vec1);
                    }
                
                if (!txtTongTien.getText().equals(""))
                tien = Double.parseDouble(txtTongTien.getText())+tien;
                txtTongTien.setText(String.valueOf(tien));                                              
                txtSoLuongMua.setText("");
                 //To change body of generated methods, choose Tools | Templates.
            }

            private void TaoHD() {
                String HD = "HD";
                HoaDon_Connect tHD = new HoaDon_Connect();
                String lastmahd = tHD.LastMaHD();
                if (lastmahd==null) MaHD= "HD01";
                else {
                    int sohd = Integer.parseInt(lastmahd.substring(2))+1;
                    if (sohd<10) MaHD = HD+"0"+String.valueOf(sohd);
                    else MaHD = HD+String.valueOf(sohd);
                }                                               
                HoaDon hd = new HoaDon();
                hd.setMaHD(MaHD);
                hd.setMaNV(MaNV);
                Date date = new Date();
                java.text.SimpleDateFormat sdf = 
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(date);
                hd.setNgaylap(currentTime);
                hd.setIsDelete(0);
                tHD.TaoHD(hd); 
            }


        });
        
        btnXoaSach.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                if (tblHoaDon.getSelectedRow()<0) 
                    JOptionPane.showMessageDialog(null, "Bạn Chưa chọn sách");
                else{
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có chắc chắn muôn xóa?","Warning",JOptionPane.YES_NO_OPTION);
                        if(dialogResult == JOptionPane.YES_OPTION){
                            cthd.remove(tblHoaDon.getSelectedRow());
                            double tien = Double.parseDouble(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 2).toString());
                            dtmHoaDon.removeRow(tblHoaDon.getSelectedRow());                                                                      
                            tien = Double.parseDouble(txtTongTien.getText())-tien;
                            if (tien==0) txtTongTien.setText("");
                            else
                            txtTongTien.setText(String.valueOf(tien)); 
                    }
                }
            }                 
        });
        
        btnHuy.addActionListener(new ActionListener(){
             
            @Override
            public void actionPerformed(ActionEvent e){
                int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có chắc chắn muôn xóa?","Warning",JOptionPane.YES_NO_OPTION);
                        if(dialogResult == JOptionPane.YES_OPTION){
                            cthd.clear();
                            while(dtmHoaDon.getRowCount() > 0)
                                {
                                    dtmHoaDon.removeRow(0);
                                }
                            txtTongTien.setText("");
                        }
            }
        });

btnThanhToan.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có chắc muốn thanh toán?","Warning",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    for (CTHD ct : cthd ){
                        CTHD_Connect taoCT = new CTHD_Connect();
                        Sach_Connect sach = new Sach_Connect();
                        taoCT.ThemCT(ct);
                        for (Sach s : dss){
                            if (s.getMaSach()==ct.getMaSach())
                                sach.updateSL(s.getMaSach(), s.getSoLuong()-ct.getSoLuong());
                        	
                        }
                        
                    }
                    cthd.clear();
                    while(dtmHoaDon.getRowCount() > 0)
                        {
                            dtmHoaDon.removeRow(0);
                        }
                    txtTongTien.setText("");
                    HoaDon_Connect HD = new HoaDon_Connect();
                    HD.ThanhToan(MaHD);
                    MaHD = null;
                    xuLyTim();
                }
            }
        });
        

        txtKhachDua.addKeyListener(new KeyAdapter(){
            
            @Override
            public void keyReleased(KeyEvent e){
                if (!txtKhachDua.getText().isEmpty()){
                double tienthua = Double.parseDouble(txtKhachDua.getText())-Double.parseDouble(txtTongTien.getText());
                txtTienThua.setText(String.valueOf(tienthua));
                }
                else 
                    txtTienThua.setText("");
            }
        });
		
	}

	
	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		// MENU
		JPanel pnMenu =new JPanel();
		pnMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
		mnuBar = new JMenuBar();
		mnuFile = new JMenu("File");
		mnuQuanLy = new JMenu("Quản lý");
		mnuThongKe = new JMenu("Thống Kê");
		mnuExit = new JMenu("Exit");	
		
		itemQuanLySach = new JMenuItem("Quản lý sách");
		itemQuanLyNXB = new JMenuItem("Quản lý NXB");
		itemQuanLyNhanVien = new JMenuItem("Quản lý nhân viên");
		itemDangXuat = new JMenuItem("Đăng Xuất");
		itemExit = new JMenuItem("Thoát");
		
		mnuQuanLy.add(itemQuanLySach);
		mnuQuanLy.add(itemQuanLyNXB);
		mnuQuanLy.add(itemQuanLyNhanVien);
		mnuFile.add(itemDangXuat);
		mnuFile.add(itemExit);
		
		mnuBar.add(mnuFile);
		mnuBar.add(mnuQuanLy);
		mnuBar.add(mnuThongKe);
		mnuBar.add(mnuExit);
		pnMenu.add(mnuBar);
		con.add(pnMenu,BorderLayout.NORTH);
		
		//Phan ban hang
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		con.add(pnMain,BorderLayout.CENTER);
		
		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(700, 0));
		JPanel pnRight = new JPanel();
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT , pnLeft , pnRight);
		pnMain.add(sp,BorderLayout.CENTER);
		
		
		pnLeft.setLayout(new BorderLayout());
		// pn TopOfLeft
		JPanel pnTopOfLeft = new JPanel();
		pnTopOfLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		txtTenMa = new JTextField(30); 
		txtTenMa.setPreferredSize(new Dimension(200, 40));	
		cboTheLoai = new JComboBox<NXB>();
		cboTheLoai.setEditable(false);
		cboTheLoai.setPreferredSize(new Dimension(200, 40));
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setPreferredSize(new Dimension(140, 40));
		btnTimKiem.setIcon(new ImageIcon("images/search.png"));
		pnTopOfLeft.add(txtTenMa);
		pnTopOfLeft.add(cboTheLoai);
		pnTopOfLeft.add(btnTimKiem);
		pnLeft.add(pnTopOfLeft,BorderLayout.NORTH);
		
		JPanel pnCenterOfLeft = new JPanel();
		pnCenterOfLeft.setLayout(new BorderLayout());
		
		dtmSach = new DefaultTableModel();
		dtmSach.addColumn("Mã sách");
		dtmSach.addColumn("Tên Sách");
		dtmSach.addColumn("Số lượng");
//		dtmSach.addColumn("Nhà xuất bản");		
//		dtmSach.addColumn("Tác giả");
		dtmSach.addColumn("Thể loại");
		dtmSach.addColumn("Giá Bán");
		tblSach = new JTable(dtmSach);
		
		JScrollPane scSach = new JScrollPane(tblSach, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		pnCenterOfLeft.add(scSach);
		pnLeft.add(pnCenterOfLeft,BorderLayout.CENTER);
		
		
		JPanel pnBottomOfLeft = new JPanel();
		pnBottomOfLeft.setLayout(new FlowLayout());
		
		JLabel lblSoLuongMua = new JLabel("Nhập Số Lượng");
		lblSoLuongMua.setPreferredSize(new Dimension(100, 40));
		pnBottomOfLeft.add(lblSoLuongMua);
		
		txtSoLuongMua = new JTextField(20);
		txtSoLuongMua.setPreferredSize(new Dimension(200, 40));
		pnBottomOfLeft.add(txtSoLuongMua);
		
		JLabel lblKhoangTrang = new JLabel("");
		lblKhoangTrang.setPreferredSize(new Dimension(50, 40));
		pnBottomOfLeft.add(lblKhoangTrang);
		
		btnThemVaoHoaDon = new JButton("Thêm vào hóa đơn");
		btnThemVaoHoaDon.setPreferredSize(new Dimension(150, 40));
		pnBottomOfLeft.add(btnThemVaoHoaDon);
		
		
		
		pnLeft.add(pnBottomOfLeft,BorderLayout.SOUTH);
		
		// pnRight 
		pnRight.setLayout(new BorderLayout());
		
		JPanel pnTitle = new JPanel();
		JLabel lblTitle = new JLabel("Chi Tiết Hóa Đơn");
		lblTitle.setFont(new Font("Segoe UI",Font.BOLD, 24));
		lblTitle.setForeground(Color.BLUE);
		pnTitle.add(lblTitle);
		pnRight.add(pnTitle,BorderLayout.NORTH);
		
		JPanel pnCenterOfRight = new JPanel();
		pnCenterOfRight.setLayout(new BorderLayout());
		dtmHoaDon = new DefaultTableModel();
		dtmHoaDon.addColumn("Tên sách");
//		dtmHoaDon.addColumn("Đơn Giá");
		dtmHoaDon.addColumn("Số lượng");
		dtmHoaDon.addColumn("Thành tiền");
		tblHoaDon = new JTable(dtmHoaDon);
		JScrollPane scHoaDon = new JScrollPane(tblHoaDon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenterOfRight.add(scHoaDon,BorderLayout.CENTER);		
		pnRight.add(pnCenterOfRight,BorderLayout.CENTER);
		
		JPanel pnBottomOfRight = new JPanel();
		pnBottomOfRight.setLayout(new BoxLayout(pnBottomOfRight, BoxLayout.Y_AXIS));
		//panel tong tien
		JPanel pnTongTien = new JPanel();
		pnTongTien.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTongTien= new JLabel("Tổng Tiền ");
		lblTongTien.setFont(new Font("Segoe UI",Font.BOLD, 18));
		txtTongTien = new JTextField(30);
		txtTongTien.setPreferredSize(new Dimension(500, 40));
		txtTongTien.setEditable(false);
		pnTongTien.add(lblTongTien);
		pnTongTien.add(txtTongTien);
		pnBottomOfRight.add(pnTongTien);
		
		JPanel pnKhachDua = new JPanel();
		pnKhachDua.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblKhachDua= new JLabel("Khách đưa ");
		lblKhachDua.setFont(new Font("Segoe UI",Font.BOLD, 18));
		txtKhachDua = new JTextField(30);
		txtKhachDua.setPreferredSize(new Dimension(0, 40));
		pnKhachDua.add(lblKhachDua);
		pnKhachDua.add(txtKhachDua);
		pnBottomOfRight.add(pnKhachDua);
		
		JPanel pnTienThua = new JPanel() ;
		pnTienThua.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTienThua= new JLabel("Tiền thừa ");
		lblTienThua.setFont(new Font("Segoe UI",Font.BOLD, 18));
		txtTienThua = new JTextField(30);
		txtTienThua.setPreferredSize(new Dimension(0, 40));
		txtTienThua.setEditable(false);
		pnTienThua.add(lblTienThua);
		pnTienThua.add(txtTienThua);
		pnBottomOfRight.add(pnTienThua);
		
		lblTongTien.setPreferredSize(lblKhachDua.getPreferredSize());
		lblTienThua.setPreferredSize(lblKhachDua.getPreferredSize());
		
		
		JPanel pnBtnOfRight = new JPanel();
		pnBtnOfRight.setLayout(new FlowLayout());
		
		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setPreferredSize(new Dimension(160, 40));
		btnHuy = new JButton("Hủy Hóa Đơn");		
                btnHuy.setIcon(new ImageIcon("images/cancel.png"));

		btnHuy.setPreferredSize(new Dimension(160, 40));
		btnXoaSach = new JButton("Xóa Sản phẩm");
		btnXoaSach.setPreferredSize(new Dimension(160, 40));	
				
		pnBtnOfRight.add(btnThanhToan);
		pnBtnOfRight.add(btnHuy);
		pnBtnOfRight.add(btnXoaSach);
		pnBottomOfRight.add(pnBtnOfRight);
		pnRight.add(pnBottomOfRight,BorderLayout.SOUTH);
		
		
		
									
	}
	
	public void showWindow()
	{
		this.setSize(1400, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
