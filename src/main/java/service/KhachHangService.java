package service;


import entity.KhachHang;

import java.util.List;

public interface KhachHangService {
    List<KhachHang> getAllKhachHangs();
    KhachHang getOneKhachHang(String id);
    String addKhachHang(KhachHang khachHang);
    String updateKhachHang(KhachHang khachHang);
    String deleteKhachHang(KhachHang khachHang);
}
