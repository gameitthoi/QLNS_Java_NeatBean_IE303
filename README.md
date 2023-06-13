# QLNS_Java_NetBeans_IE303
 
 Chạy file .sql trong thư mực CSDL trên SQL Server để tạo cơ sở dữ liệu.
 
 Thiết lập mật khẩu cho tài khoản sa trong SQL Server
 
 Trong file Connect_sqlServer.java trong thư mục Connect, thay <your-pass> thành mật khẩu của tài khoản sa của bạn.
 
 Thay tên máy chủ "LAPTOP-C560D797\\dat" thành tên máy chủ SQL của mình
 
 Nhớ kiểm tra cổng kết nối của SQL server (mặc định là 1433) bằng cách:
 
 1. Mở SQL Server Configuration Manager
 
 2. Chọn SQL Server Network Configuration

 3. Chọn Protocols for ...

 4. Kiểm tra giá trị của TCP/IP Port trong tab IP Addresses.
  
 Chạy đồ án bằng công cụ NetBeans. Đồ án được tạo với categories là "java with Maven", Projects là "Java Application".

 Với các tính năng xuất file pdf và excel thì cần kiểm tra lại đường dẫn trong máy mình có khớp với đường dẫn tạo file pdf hoặc excel trong đồ án không.

 Đồ án chỉ hỗ trợ file .xls với excel
