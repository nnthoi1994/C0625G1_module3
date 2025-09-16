create database quan_ly_hang_hoa;
use quan_ly_hang_hoa;

create table nha_cung_cap(
ma_nha_cung_cap varchar(20) primary key,
ten_nha_cung_cap varchar(20),
dia_chi varchar(50)
);
create table phieu_xuat(
so_phieu_xuat varchar(20) primary key,
ngay_xuat datetime
);

create table vat_tu(
ma_vat_tu varchar(20) primary key,
ten_vat_tu varchar(20)
);

create table phieu_nhap(
so_phieu_nhap varchar(20) primary key,
ngay_nhap datetime
);

create table don_dat_hang(
so_don_hang varchar(20) primary key,
ngay_dat_hang datetime,
ma_nha_cung_cap varchar(20),
foreign key (ma_nha_cung_cap) references nha_cung_cap(ma_nha_cung_cap)
);



create table chi_tiet_phieu_xuat(
so_phieu_xuat varchar(20),
ma_vat_tu varchar(20),
don_gia_xuat int,
so_luong_xuat int,
primary key (so_phieu_xuat, ma_vat_tu),
foreign key (so_phieu_xuat) references phieu_xuat(so_phieu_xuat),
foreign key (ma_vat_tu) references vat_tu(ma_vat_tu)
);

create table chi_tiet_phieu_nhap(
so_phieu_nhap varchar(20),
ma_vat_tu varchar(20),
don_gia_nhap int,
so_luong_nhap int,
primary key (so_phieu_nhap, ma_vat_tu),
foreign key (so_phieu_nhap) references phieu_nhap(so_phieu_nhap),
foreign key (ma_vat_tu) references vat_tu(ma_vat_tu)
);

create table chi_tiet_don_hang(
so_don_hang varchar(20),
ma_vat_tu varchar(20),
primary key (so_don_hang, ma_vat_tu),
foreign key (so_don_hang) references don_dat_hang(so_don_hang),
foreign key (ma_vat_tu) references vat_tu(ma_vat_tu)
);

create table dien_thoai(
ma_nha_cung_cap varchar(20),
so_dien_thoai varchar(30),
primary key(ma_nha_cung_cap, so_dien_thoai),
foreign key (ma_nha_cung_cap) references nha_cung_cap(ma_nha_cung_cap)
);

