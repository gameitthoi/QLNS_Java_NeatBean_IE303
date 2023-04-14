USE [master]
GO
/****** Object:  Database [dbQLNS]    Script Date: 09-Dec-17 9:46:27 AM ******/
CREATE DATABASE [dbQLNS]
 CONTAINMENT = NONE
 ON  PRIMARY 
 /*Tạo file dbQLNS.mdf tại đường dẫn này. Đổi đường dẫn cho phù hợp*/
( NAME = N'dbQLNS', FILENAME = N'D:\java-switch\QLNhaSach\CSDL\dbQLNS.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
 /*Tạo file dbQLNS_log.ldf tại đường dẫn này. Đổi đường dẫn cho phù hợp*/
( NAME = N'dbQLNS_log', FILENAME = N'D:\java-switch\QLNhaSach\CSDL\dbQLNS_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [dbQLNS] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [dbQLNS].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [dbQLNS] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [dbQLNS] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [dbQLNS] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [dbQLNS] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [dbQLNS] SET ARITHABORT OFF 
GO
ALTER DATABASE [dbQLNS] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [dbQLNS] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [dbQLNS] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [dbQLNS] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [dbQLNS] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [dbQLNS] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [dbQLNS] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [dbQLNS] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [dbQLNS] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [dbQLNS] SET  DISABLE_BROKER 
GO
ALTER DATABASE [dbQLNS] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [dbQLNS] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [dbQLNS] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [dbQLNS] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [dbQLNS] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [dbQLNS] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [dbQLNS] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [dbQLNS] SET RECOVERY FULL 
GO
ALTER DATABASE [dbQLNS] SET  MULTI_USER 
GO
ALTER DATABASE [dbQLNS] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [dbQLNS] SET DB_CHAINING OFF 
GO
ALTER DATABASE [dbQLNS] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [dbQLNS] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [dbQLNS] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'dbQLNS', N'ON'
GO
ALTER DATABASE [dbQLNS] SET QUERY_STORE = OFF
GO
USE [dbQLNS]
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [dbQLNS]
GO
/****** Object:  Table [dbo].[CHUCVU]    Script Date: 09-Dec-17 9:46:27 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET DATEFORMAT dmy
CREATE TABLE [dbo].[CHUCVU](
	[MaCV] [nvarchar](50) NOT NULL,
	[ChucVu] [nvarchar](250) NULL,
	[DinhDoanh] [nvarchar](250) NULL,
 CONSTRAINT [PK_CHUCVU] PRIMARY KEY CLUSTERED 
(
	[MaCV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTHD]    Script Date: 09-Dec-17 9:46:27 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTHD](
	[MaHD] [nvarchar](50) NOT NULL,
	[MaSach] [nvarchar](50) NOT NULL,
	[DonGia] [money] NULL,
	[SoLuong] [int] NULL,
	[ThanhTien] [money] NULL,
 CONSTRAINT [PK_CTHD] PRIMARY KEY CLUSTERED 
(
	[MaHD],[MaSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HOADON]    Script Date: 09-Dec-17 9:46:27 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HOADON](
	[MaHD] [nvarchar](50) NOT NULL,
	[MaNV] [nvarchar](50) NULL,
	[NgayLap] [date] NULL,
	[TongTien] [money] NULL,
	[ThanhCong] [int] NULL,
	[NhapSach] [int] DEFAULT 0,
 CONSTRAINT [PK_HOADON] PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 09-Dec-17 9:46:27 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MaNV] [nvarchar](50) NOT NULL,
	[TenNV] [nvarchar](250) NULL,
	[NgaySinh] [date] NULL,
	[NgayVaoLam] [date] NULL,
	[SoChungMinh] [nvarchar](50) NULL,
	[MaCV] [nvarchar](50) NULL,
	[SDT] [nvarchar](50) NULL,
	[Email] [nvarchar](50) NULL,
 CONSTRAINT [PK_NHANVIEN] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NXB]    Script Date: 09-Dec-17 9:46:27 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NXB](
	[MaNXB] [nvarchar](50) NOT NULL,
	[TenNXB] [nvarchar](250) NULL,
	[SDT] [nvarchar](50) NULL,
	[DiaChi] [nvarchar](50) NULL,
	[Email] [nvarchar](50) NULL,
 CONSTRAINT [PK_NXB] PRIMARY KEY CLUSTERED 
(
	[MaNXB] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SACH]    Script Date: 09-Dec-17 9:46:27 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SACH](
	[MaSach] [nvarchar](50) NOT NULL,
	[TenSach] [nvarchar](250) NULL,
	[MaNXB] [nvarchar](50) NULL,
	[TacGia] [nvarchar](250) NULL,
	[GiaBan] [money] NULL,
	[TheLoai] [nvarchar](150) NULL,
	[SoLuong] [int] NULL,
 CONSTRAINT [PK_SACH] PRIMARY KEY CLUSTERED 
(
	[MaSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TAIKHOAN]    Script Date: 09-Dec-17 9:46:27 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TAIKHOAN](
	[MaTk] [nvarchar](50) NOT NULL,
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[MaNV] [nvarchar](50) NULL,
 CONSTRAINT [PK_TAIKHOAN] PRIMARY KEY CLUSTERED 
(
	[MaTk] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[NXB] ([MaNXB], [TenNXB], [SDT], [DiaChi], [Email]) VALUES (N'nxb01', N'Đại học quốc gia tp HCM', N'0197852545', N'Tp HCM', N'dhql@gmal.com')
INSERT [dbo].[NXB] ([MaNXB], [TenNXB], [SDT], [DiaChi], [Email]) VALUES (N'nxb02', N'Kim Đồng', N'0197816153', N'TP HCm', N'kimdong@gmail.com')

INSERT [dbo].[SACH] ([MaSach], [TenSach], [MaNXB], [TacGia], [GiaBan], [TheLoai], [SoLuong]) VALUES (N's01', N'SQL server', N'nxb01', N'thanh', 150000.0000, N'Giáo trình', 49)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [MaNXB], [TacGia], [GiaBan], [TheLoai], [SoLuong]) VALUES (N's02', N'OOP', N'nxb02', N'hiep', 200000.0000, N'Giáo trình', 49)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [MaNXB], [TacGia], [GiaBan], [TheLoai], [SoLuong]) VALUES (N's03', N'java', N'nxb01', N'phuc', 200000.0000, N'Giáo trình', 49)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [MaNXB], [TacGia], [GiaBan], [TheLoai], [SoLuong]) VALUES (N's04', N'ERP', N'nxb02', N'Hữu Phúc', 120000.0000, N'Giáo trình', 19)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [MaNXB], [TacGia], [GiaBan], [TheLoai], [SoLuong]) VALUES (N's05', N'odoo', N'nxb01', N'hoang hiep', 250000.0000, N'Giáo trình', 99)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [MaNXB], [TacGia], [GiaBan], [TheLoai], [SoLuong]) VALUES (N's06', N'English', N'nxb02', N'Gia Lượng', 130000.0000, N'Ngoại Ngữ', 9)

INSERT [dbo].[HOADON] ([MaHD], [MaNV], [NgayLap], [TongTien], [ThanhCong], [NhapSach]) VALUES ('HD01', 'NV01', '01/01/2023', 7500000.0000, 1, 1)
INSERT [dbo].[HOADON] ([MaHD], [MaNV], [NgayLap], [TongTien], [ThanhCong], [NhapSach]) VALUES ('HD02', 'NV01', '01/01/2023', 150000.0000,1, 0)
INSERT [dbo].[HOADON] ([MaHD], [MaNV], [NgayLap], [TongTien], [ThanhCong], [NhapSach]) VALUES ('HD03', 'NV01', '01/02/2023', 10000000.0000,1, 1)
INSERT [dbo].[HOADON] ([MaHD], [MaNV], [NgayLap], [TongTien], [ThanhCong], [NhapSach]) VALUES ('HD04', 'NV01', '01/02/2023', 200000.0000, 1, 0)
INSERT [dbo].[HOADON] ([MaHD], [MaNV], [NgayLap], [TongTien], [ThanhCong], [NhapSach]) VALUES ('HD05', 'NV01', '01/03/2023', 10000000.0000, 1, 1)
INSERT [dbo].[HOADON] ([MaHD], [MaNV], [NgayLap], [TongTien], [ThanhCong], [NhapSach]) VALUES ('HD06', 'NV01', '01/03/2023', 200000.0000, 1, 0)
INSERT [dbo].[HOADON] ([MaHD], [MaNV], [NgayLap], [TongTien], [ThanhCong], [NhapSach]) VALUES ('HD07', 'NV01', '01/04/2023', 2400000.0000, 1, 1)
INSERT [dbo].[HOADON] ([MaHD], [MaNV], [NgayLap], [TongTien], [ThanhCong], [NhapSach]) VALUES ('HD08', 'NV01', '01/04/2023', 120000.0000, 1, 0)
INSERT [dbo].[HOADON] ([MaHD], [MaNV], [NgayLap], [TongTien], [ThanhCong], [NhapSach]) VALUES ('HD09', 'NV01', '01/04/2023', 25000000.0000, 1, 1)
INSERT [dbo].[HOADON] ([MaHD], [MaNV], [NgayLap], [TongTien], [ThanhCong], [NhapSach]) VALUES ('HD10', 'NV01', '01/04/2023', 250000.0000, 1, 0)
INSERT [dbo].[HOADON] ([MaHD], [MaNV], [NgayLap], [TongTien], [ThanhCong], [NhapSach]) VALUES ('HD11', 'NV01', '01/04/2023', 1300000.0000, 1, 1)
INSERT [dbo].[HOADON] ([MaHD], [MaNV], [NgayLap], [TongTien], [ThanhCong], [NhapSach]) VALUES ('HD12', 'NV01', '01/04/2023', 130000.0000, 1, 0)

INSERT [dbo].[CTHD] ([MaHD], [MaSach], [DonGia], [SoLuong], [ThanhTien]) VALUES ('HD01', N's01', 150000.0000, 50, 7500000.0000)
INSERT [dbo].[CTHD] ([MaHD], [MaSach], [DonGia], [SoLuong], [ThanhTien]) VALUES ('HD02', N's01', 150000.0000, 1, 150000.0000)
INSERT [dbo].[CTHD] ([MaHD], [MaSach], [DonGia], [SoLuong], [ThanhTien]) VALUES ('HD03', N's02', 200000.0000, 50, 10000000.0000)
INSERT [dbo].[CTHD] ([MaHD], [MaSach], [DonGia], [SoLuong], [ThanhTien]) VALUES ('HD04', N's02', 120000.0000, 1, 200000.0000)
INSERT [dbo].[CTHD] ([MaHD], [MaSach], [DonGia], [SoLuong], [ThanhTien]) VALUES ('HD05', N's03', 250000.0000, 50, 10000000.0000)
INSERT [dbo].[CTHD] ([MaHD], [MaSach], [DonGia], [SoLuong], [ThanhTien]) VALUES ('HD06', N's03', 130000.0000, 1, 200000.0000)
INSERT [dbo].[CTHD] ([MaHD], [MaSach], [DonGia], [SoLuong], [ThanhTien]) VALUES ('HD07', N's04', 150000.0000, 20, 2400000.0000)
INSERT [dbo].[CTHD] ([MaHD], [MaSach], [DonGia], [SoLuong], [ThanhTien]) VALUES ('HD08', N's04', 200000.0000, 1, 120000.0000)
INSERT [dbo].[CTHD] ([MaHD], [MaSach], [DonGia], [SoLuong], [ThanhTien]) VALUES ('HD09', N's05', 200000.0000, 100, 25000000.0000)
INSERT [dbo].[CTHD] ([MaHD], [MaSach], [DonGia], [SoLuong], [ThanhTien]) VALUES ('HD10', N's05', 120000.0000, 1, 250000.0000)
INSERT [dbo].[CTHD] ([MaHD], [MaSach], [DonGia], [SoLuong], [ThanhTien]) VALUES ('HD11', N's06', 250000.0000, 10, 1300000.0000)
INSERT [dbo].[CTHD] ([MaHD], [MaSach], [DonGia], [SoLuong], [ThanhTien]) VALUES ('HD12', N's06', 130000.0000, 1, 130000.0000)

INSERT [dbo].[TAIKHOAN] ([MaTk], [username], [password], [MaNV]) VALUES (N'tk01', N'admin', N'admin', 'NV01')
INSERT [dbo].[TAIKHOAN] ([MaTk], [username], [password], [MaNV]) VALUES (N'tk02', N'thungan', N'thungan', 'NV02')


INSERT [dbo].CHUCVU ([MaCV], [ChucVu], [DinhDoanh]) VALUES ('CV01', N'Admin', N'Quản lý hệ thống')
INSERT [dbo].CHUCVU ([MaCV], [ChucVu], [DinhDoanh]) VALUES ('CV02', N'Thu ngân', N'Thanh toán hóa đơn cho khách')
INSERT [dbo].CHUCVU ([MaCV], [ChucVu], [DinhDoanh]) VALUES ('CV03', N'Nhân viên mua hàng', N'Lên kế hoạch mua các đầu sách, dụng cụ,...')
INSERT [dbo].CHUCVU ([MaCV], [ChucVu], [DinhDoanh]) VALUES ('CV04', N'Nhân viên IT', N'Sửa chữa, quản lý, hỗ trợ kỹ thuật')
INSERT [dbo].CHUCVU ([MaCV], [ChucVu], [DinhDoanh]) VALUES ('CV05', N'Nhân viên kho', N'Quản lý kho')
INSERT [dbo].CHUCVU ([MaCV], [ChucVu], [DinhDoanh]) VALUES ('CV06', N'Nhân viên an ninh', N'Bảo đảm an ninh')
INSERT [dbo].CHUCVU ([MaCV], [ChucVu], [DinhDoanh]) VALUES ('CV07', N'Nhân viên kế toán', N'Chịu trách nhiệm về các vấn đề liên quan đến tài chính, dòng tiền')

INSERT [dbo].[NHANVIEN]([MaNV], [TenNV], [NgaySinh], [NgayVaoLam], [SoChungMinh],[MaCV], [SDT], [Email]) VALUES (N'NV01',N'Nguyên Văn A', '1/1/2002', '10/12/2021' ,N'012356789',N'CV01',N'012356789',N'anguyenvan@gmai.com')
INSERT [dbo].[NHANVIEN]([MaNV], [TenNV], [NgaySinh], [NgayVaoLam], [SoChungMinh],[MaCV], [SDT], [Email]) VALUES (N'NV02',N'Nguyên Văn B', '1/2/2002', '21/12/2022' ,N'012356789',N'CV02',N'012356789',N'bnguyenvan@gmai.com')
INSERT [dbo].[NHANVIEN]([MaNV], [TenNV], [NgaySinh], [NgayVaoLam], [SoChungMinh],[MaCV], [SDT], [Email]) VALUES (N'NV03',N'Nguyên Văn C', '1/3/2002', '31/12/2023' ,N'012356789',N'CV03',N'012356789',N'cnguyenvan@gmai.com')
INSERT [dbo].[NHANVIEN]([MaNV], [TenNV], [NgaySinh], [NgayVaoLam], [SoChungMinh],[MaCV], [SDT], [Email]) VALUES (N'NV04',N'Nguyên Văn D', '1/4/2002', '20/01/2019' ,N'012356789',N'CV04',N'012356789',N'dnguyenvan@gmai.com')
INSERT [dbo].[NHANVIEN]([MaNV], [TenNV], [NgaySinh], [NgayVaoLam], [SoChungMinh],[MaCV], [SDT], [Email]) VALUES (N'NV05',N'Nguyên Văn E', '1/5/2002', '11/08/2018' ,N'012356789',N'CV05',N'012356789',N'enguyenvan@gmai.com')
INSERT [dbo].[NHANVIEN]([MaNV], [TenNV], [NgaySinh], [NgayVaoLam], [SoChungMinh],[MaCV], [SDT], [Email]) VALUES (N'NV06',N'Nguyên Văn F', '1/6/2002', '01/03/2017' ,N'012356789',N'CV06',N'012356789',N'fnguyenvan@gmai.com')
INSERT [dbo].[NHANVIEN]([MaNV], [TenNV], [NgaySinh], [NgayVaoLam], [SoChungMinh],[MaCV], [SDT], [Email]) VALUES (N'NV07',N'Nguyên Văn G', '1/7/2002', '22/12/2020' ,N'012356789',N'CV07',N'012356789',N'gnguyenvan@gmai.com')
INSERT [dbo].[NHANVIEN]([MaNV], [TenNV], [NgaySinh], [NgayVaoLam], [SoChungMinh],[MaCV], [SDT], [Email]) VALUES (N'NV08',N'Nguyên Văn H', '1/8/2002', '23/11/2021' ,N'012356789',N'CV02',N'012356789',N'hnguyenvan@gmai.com')
INSERT [dbo].[NHANVIEN]([MaNV], [TenNV], [NgaySinh], [NgayVaoLam], [SoChungMinh],[MaCV], [SDT], [Email]) VALUES (N'NV09',N'Nguyên Văn J', '1/9/2002', '24/02/2022' ,N'012356789',N'CV03',N'012356789',N'jnguyenvan@gmai.com')

ALTER TABLE [dbo].[CTHD]  WITH CHECK ADD  CONSTRAINT [fk_cthd_Sach] FOREIGN KEY([MaSach])
REFERENCES [dbo].[SACH] ([MaSach])
GO
ALTER TABLE [dbo].[CTHD] CHECK CONSTRAINT [fk_cthd_Sach]
GO
ALTER TABLE [dbo].[CTHD]  WITH CHECK ADD  CONSTRAINT [kh_HoaDon] FOREIGN KEY([MaHD])
REFERENCES [dbo].[HOADON] ([MaHD])
GO
ALTER TABLE [dbo].[CTHD] CHECK CONSTRAINT [kh_HoaDon]
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD  CONSTRAINT [fk_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
ALTER TABLE [dbo].[HOADON] CHECK CONSTRAINT [fk_NhanVien]
GO
ALTER TABLE [dbo].[NHANVIEN]  WITH CHECK ADD  CONSTRAINT [fk_ChucVu] FOREIGN KEY([MaCV])
REFERENCES [dbo].[CHUCVU] ([MaCV])
GO
ALTER TABLE [dbo].[NHANVIEN] CHECK CONSTRAINT [fk_ChucVu]
GO
ALTER TABLE [dbo].[SACH]  WITH CHECK ADD  CONSTRAINT [fk_NXB] FOREIGN KEY([MaNXB])
REFERENCES [dbo].[NXB] ([MaNXB])
GO
ALTER TABLE [dbo].[SACH] CHECK CONSTRAINT [fk_NXB]
GO
ALTER TABLE [dbo].[TAIKHOAN]  WITH CHECK ADD  CONSTRAINT [fk_TaiKhoan_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
ALTER TABLE [dbo].[TAIKHOAN] CHECK CONSTRAINT [fk_TaiKhoan_NhanVien]
GO
USE [master]
GO
ALTER DATABASE [dbQLNS] SET  READ_WRITE 
GO

CREATE PROCEDURE GetTonKhoThangHienTai
	@nam nvarchar(4), @thang nvarchar(2)
AS
BEGIN
	SELECT S.MaSach, S.TenSach,
	S.SoLuong+SUM(CASE WHEN H.NhapSach=0 THEN C.SoLuong ELSE 0 END)-SUM(CASE WHEN H.NhapSach=1 THEN C.SoLuong ELSE 0 END) AS TonDau,
	SUM(CASE WHEN H.NhapSach=1 THEN C.SoLuong ELSE 0 END) AS Nhap,
	SUM(CASE WHEN H.NhapSach=0 THEN C.SoLuong ELSE 0 END) AS Xuat,
	S.SoLuong AS TonCuoi
	FROM SACH AS S,CTHD AS C,HOADON AS H
	WHERE S.MaSach=C.MaSach AND C.MaHD=H.MaHD AND YEAR(H.NgayLap)=@nam AND MONTH(H.NgayLap)=@thang AND H.ThanhCong=1
	GROUP BY S.MaSach, S.TenSach, S.SoLuong
END

/*EXEC GetTonKhoThangHienTai '2023', '4'*/
DROP TABLE SACH
DROP TABLE HOADON
DROP TABLE CTHD
DROP TABLE NXB
DROP TABLE TAIKHOAN
DROP TABLE NHANVIEN
DROP TABLE CHUCVU