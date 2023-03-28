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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Connect.login_sql;
import Model.TaiKhoan;

public class LoginUI extends JFrame{
	
	private JTextField txtUserName  ;
	private JPasswordField txtPassWord ;
	private JButton btnLogin , btnThoat;
	public LoginUI(String title) {
		this.setTitle(title);
		addControls();
		addEvents();
		
	}

	private void addEvents() {
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtUserName.getText()==null || txtPassWord.getText()==null) return ;
				xuLyDangNhap();
				
			}
		});
		btnLogin.addKeyListener(new KeyListener() {
			
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
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					if(txtUserName.getText()==null || txtPassWord.getText()==null) return ;
					xuLyDangNhap();
				}
				
			}
		});
		
		//set nút enter == btn đăng nhập
		txtPassWord.addKeyListener(new KeyListener() {
			
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
					
					if(txtUserName.getText()==null || txtPassWord.getText()==null) return ;
							xuLyDangNhap();
							
					
				}
				
			}
		});
		
		
	}

	protected void xuLyDangNhap() {
		login_sql login = new login_sql();
		TaiKhoan active = login.login(txtUserName.getText(), txtPassWord.getText());
		if(active == null) 
		{
			JOptionPane.showMessageDialog(null, "Đăng nhập thất bại ");
		}
		else
		{
//			JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
			dispose();	
//			QuanLySachUI qls = new QuanLySachUI("Quản lý sách");
//			qls.showWindow();
			BanHangUI ui = new BanHangUI("Bán hàng",active.getMaNV());//,active.getMaNV()
			ui.showWindow();
			
		}
		
	}

	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		Font font = new Font("Segoe UI",Font.BOLD, 15);
		
		JPanel pnTop = new JPanel();
		JLabel lblTitle = new JLabel("LOGIN");
		lblTitle.setFont(new Font("Segoe UI",Font.BOLD, 24));
		lblTitle.setForeground(Color.BLUE);
		pnTop.add(lblTitle);
//		pnTop.setBackground(Color.getHSBColor(183,10,96));
		
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		
		JPanel pnUserName = new JPanel() ;
		pnUserName.setLayout(new FlowLayout());
		JLabel lblUserName = new JLabel("User name ");
		lblUserName.setFont(new Font("sedou ui", Font.BOLD, 15));
		txtUserName = new JTextField(20);
		txtUserName.setPreferredSize(new Dimension(150, 40));
		txtUserName.setFont(font);
		pnUserName.add(lblUserName);
		pnUserName.add(txtUserName);
		pnCenter.add(pnUserName);
		txtUserName.requestFocus();
		
		JPanel pnPassWord = new JPanel() ;
		pnPassWord.setLayout(new FlowLayout());
		JLabel lblPassWord = new JLabel("Pass word ");
		lblPassWord.setFont(new Font("sedou ui", Font.BOLD, 15));
		txtPassWord = new JPasswordField(20);
		txtPassWord.setPreferredSize(new Dimension(150, 40));
		txtPassWord.setFont(font);
		pnPassWord.add(lblPassWord);
		pnPassWord.add(txtPassWord);
		pnCenter.add(pnPassWord);
		
		JPanel pnBtn = new JPanel();
		pnBtn.setLayout(new FlowLayout());
		btnLogin = new JButton("Đăng Nhập");
		btnLogin.setPreferredSize(new Dimension(100, 40));
		btnThoat = new JButton("Thoát");
		btnThoat.setPreferredSize(new Dimension(100, 40));
		pnBtn.add(btnLogin);
		pnBtn.add(btnThoat);
		
		con.add(pnTop);
		con.add(pnCenter);
		con.add(pnBtn);
	}
	
	public void showWindow()
	{
		this.setSize(400, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
