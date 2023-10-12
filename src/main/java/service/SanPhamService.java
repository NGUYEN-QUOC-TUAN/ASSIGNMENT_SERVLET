package service;

import entity.SanPham;

import java.util.List;

public interface SanPhamService {
    List<SanPham> getAllSanPhams();
    SanPham getOneSanPham(String id);
    String addSanPham(SanPham sanPham);
    String updateSanPham(SanPham sanPham);
    String deleteSanPham(SanPham sanPham);
}
