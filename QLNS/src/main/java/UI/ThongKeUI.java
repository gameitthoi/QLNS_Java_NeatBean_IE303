package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import Connect.HoaDon_Connect;
import Connect.Sach_Connect;
import Model.HoaDon;
import Model.Sach;

public class ThongKeUI extends JDialog {
	private JComboBox<Integer> cboThang;
	private JTextField txtNam;
	private JButton btnThongKe ;
	private DefaultTableModel dtmHoaDon =null ;
	private JTable tblHoaDon ;
	private JTextField txtTongTien ;
	private ArrayList<HoaDon> dshd_thongke =null;
	private double tongTien =0;
	private DecimalFormat df = new DecimalFormat("###,###,###");
	private DefaultTableModel dtmSach =null;
	private JTable tblSachTon = null;
	private JButton btnPrint , btnPrintHoaDon;
	private ArrayList<Sach> dss ;
	
	public ThongKeUI(String title) {
		this.setTitle(title);
		addControls();
		addEvents();
		
		hienThiThangNam();
		hienThiSachTonKho();
		
	}

	private void hienThiSachTonKho() {
		Sach_Connect sach_conn = new Sach_Connect();
		dss = sach_conn.laySachConDuoiTon();
		dtmSach.setRowCount(0);
		for(Sach s : dss)
		{
			Vector<Object> vec = new Vector<Object>();
			vec.add(s.getMaSach());
			vec.add(s.getTenSach());
			vec.add(s.getMaNXB());
			vec.add(s.getSoLuong());
			
			dtmSach.addRow(vec);
		}
		
	}

	private void hienThiThangNam() {
		cboThang.removeAllItems();
		for (int i=1 ; i<13 ;i++)
		{
			cboThang.addItem(i);
		}
		
		
	}

	private void addEvents() {
		btnThongKe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HoaDon_Connect hd_connect = new HoaDon_Connect();
				int thang  = (int) cboThang.getSelectedItem();
				String nam = txtNam.getText();
				String dauthang = nam+"-"+thang+"-"+"01"  ;
				String cuoithang = null ;
				if(thang==1 || thang == 3 || thang==5 || thang==7 || thang==8 || thang==10 || thang==12 )
				{
					cuoithang = nam+"-"+thang+"-"+"31"  ;
				}
				else if (thang == 2 )
				{
					cuoithang = nam+"-"+thang+"-"+"28"  ;
				}
				else cuoithang = nam+"-"+thang+"-"+"30"  ;
				
				dshd_thongke = hd_connect.layToanBoHoaDonTheoThangNam(dauthang, cuoithang);
//				if(dshd_thongke!=null) JOptionPane.showMessageDialog(null, "ssss");
				
				dtmHoaDon.setRowCount(0);
				for(HoaDon hd : dshd_thongke)
				{
					tongTien = tongTien + hd.getTongTien();
					
					Vector<Object> vec = new Vector<Object>();
					vec.add(hd.getMaHD());
					vec.add(hd.getMaNV());
					vec.add(hd.getNgaylap());
					vec.add(df.format(hd.getTongTien()));
					dtmHoaDon.addRow(vec);
				}
				txtTongTien.setText(df.format(tongTien));
				
			}
		});
		
		//kết nối với máy in
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					MessageFormat header = new MessageFormat("Danh sách loại sách cần nhập");
					MessageFormat footer = new MessageFormat("Page{1,number,interger}");
					tblSachTon.print(JTable.PrintMode.FIT_WIDTH, header, footer);
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			
			}
		});
		
		btnPrintHoaDon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MessageFormat header = new MessageFormat("Danh sách hóa đơn tháng")  ;
					MessageFormat footer = new MessageFormat("Page{1,number,interger} ")  ;
					tblHoaDon.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		
	}

	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new FlowLayout(FlowLayout.LEFT));
		con.add(pnTop,BorderLayout.NORTH);
		
		JPanel pnTitle = new JPanel();		
		JLabel lblTitle = new JLabel("THỐNG KÊ");
		lblTitle.setFont(new Font("segoe ui",Font.BOLD , 24));
		lblTitle.setForeground(Color.BLUE);
		pnTitle.add(lblTitle);
		pnTop.add(pnTitle);		
		
		JTabbedPane tab = new JTabbedPane();
		JPanel pnTab1 = new JPanel();
		JPanel pnTab2 = new JPanel();
		
		tab.add(pnTab1, "Thống kê hóa đơn");
		tab.add(pnTab2, "Thống kê sách cần nhập");
		con.add(tab,BorderLayout.CENTER);
		
		pnTab1.setLayout(new BorderLayout());
		
		JPanel pnTopOfTab1 = new JPanel();
		pnTopOfTab1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnTab1.add(pnTopOfTab1,BorderLayout.NORTH);
		
		JLabel lblChonThang = new JLabel("Chọn Tháng");
		lblChonThang.setPreferredSize(new Dimension(70, 30));
		cboThang = new JComboBox<Integer>();
		cboThang.setPreferredSize(new Dimension(70, 30));
		JLabel lblChonNam = new JLabel("Chọn Năm");
		lblChonNam.setPreferredSize(new Dimension(70, 30));
		txtNam = new JTextField();
		txtNam.setPreferredSize(new Dimension(100, 30));
		btnThongKe = new JButton("Thống Kê");
		btnThongKe.setPreferredSize(new Dimension(150, 30));
		
		pnTopOfTab1.add(lblChonThang);
		pnTopOfTab1.add(cboThang);
		pnTopOfTab1.add(lblChonNam);
		pnTopOfTab1.add(txtNam);
		pnTopOfTab1.add(btnThongKe);
		
		JPanel pnCenterOfTab1 = new JPanel();
		pnCenterOfTab1.setLayout(new BorderLayout());
		pnTab1.add(pnCenterOfTab1,BorderLayout.CENTER);
		
		dtmHoaDon = new DefaultTableModel();
		dtmHoaDon.addColumn("Mã hóa Đơn");
		dtmHoaDon.addColumn("Mã Nhân Viên");
		dtmHoaDon.addColumn("Ngày lập");
		dtmHoaDon.addColumn("Tổng tiền");
		
		tblHoaDon = new JTable(dtmHoaDon);
		JScrollPane scHoaDon = new JScrollPane(tblHoaDon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenterOfTab1.add(scHoaDon);
		
		JPanel pnBottomOfTab1 = new JPanel();
		pnBottomOfTab1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnTab1.add(pnBottomOfTab1,BorderLayout.SOUTH);
		
		JLabel lblTongTienTheoThang =  new JLabel("Tổng tiền bán được của tháng ");
		lblTongTienTheoThang.setFont(new Font("segou ui", Font.ITALIC, 24));
		txtTongTien = new JTextField();
		txtTongTien.setPreferredSize(new Dimension(150, 30));
		btnPrintHoaDon = new JButton("Print");
		btnPrintHoaDon.setPreferredSize(new Dimension(150, 30));
		pnBottomOfTab1.add(btnPrintHoaDon);
		pnBottomOfTab1.add(lblTongTienTheoThang);
		pnBottomOfTab1.add(txtTongTien);
		
		
		//Thống kê sách có sô lượng dưới 5
		pnTab2.setLayout(new BorderLayout());
		
		JPanel pnTopOfTab2 =new JPanel();
		JLabel lblTitleTab2 = new JLabel("Tất cả sách có số lượng tồn dưới 10");
		lblTitleTab2.setFont(new Font("segou ui", Font.ITALIC, 20));
		lblTitleTab2.setForeground(Color.BLUE);
		pnTopOfTab2.add(lblTitleTab2);
		pnTab2.add(pnTopOfTab2,BorderLayout.NORTH);
		
		JPanel pnCenterOfTab2 = new JPanel();
		pnCenterOfTab2.setLayout(new BorderLayout());
		dtmSach = new DefaultTableModel();
		dtmSach.addColumn("Mã sách");
		dtmSach.addColumn("Tên sách");
		dtmSach.addColumn("Tên nhà xuất bản");
		dtmSach.addColumn("Số lượng tồn");
		
		tblSachTon = new JTable(dtmSach);
		JScrollPane scSach = new JScrollPane(tblSachTon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenterOfTab2.add(scSach,BorderLayout.CENTER);
		
		pnTab2.add(pnCenterOfTab2,BorderLayout.CENTER);
		
		JPanel pnBottomOfTab2 = new JPanel();
		pnBottomOfTab2.setLayout(new FlowLayout());
		btnPrint = new JButton("Print");
		btnPrint.setPreferredSize(new Dimension(100, 40));
		pnBottomOfTab2.add(btnPrint);
		pnTab2.add(pnBottomOfTab2,BorderLayout.SOUTH);
		
		
		
		
		
		
	}
	public void showWindow()
	{
		this.setSize(1000, 700);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

}
