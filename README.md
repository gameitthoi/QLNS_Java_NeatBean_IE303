# QLNS_Java_NeatBean_IE303
 
 Chạy file .sql trên SQL Server để tạo CSDL.
 
 Thiết lập mật khẩu cho tài khoản sa trong SQL Server
 
 Trong file Connect_sqlServer.java trong thư mục Connect, thay <your-pass> thành mật khẩu của tài khoản sa.
 
 Thay tên máy chủ "LAPTOP-C560D797\\dat" thành tên máy chủ của mình
 
 Nhớ kiểm tra cổng kết nối của SQL server (mặc định là 1433) bằng cách:
 
 1. Mở SQL Server Configuration Manager
 
 2. Chọn SQL Server Network Configuration

 3. Chọn Protocols for ...

 4. Kiểm tra giá trị của TCP/IP Port trong tab IP Addresses.
  
 Chạy đồ án bằng công cụ NetBeans. Đồ án được tạo với categories là "java with Maven", Projects là "Java Application".
