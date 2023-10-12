package service;

import entity.ChiTietSanPham;

import java.util.List;

public interface ChiTietSanPhamService {
    List<ChiTietSanPham> getAllChiTietSanPhams();
    ChiTietSanPham getOneChiTietSanPham(String id);
    String addChiTietSanPham(ChiTietSanPham chiTietSanPham);
    String updateChiTietSanPham(ChiTietSanPham chiTietSanPham);
    String deleteChiTietSanPham(ChiTietSanPham chiTietSanPham);
}
