package service;


import entity.NhanVien;

import java.util.List;

public interface NhanVienService {
    List<NhanVien> getAllNhanViens();
    NhanVien getOneNhanVien(String id);
    String addNhanVien(NhanVien nhanVien);
    String updateNhanVien(NhanVien nhanVien);
    String deleteNhanVien(NhanVien nhanVien);
}
