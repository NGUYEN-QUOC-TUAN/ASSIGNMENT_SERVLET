package service;

import entity.DongSanPham;

import java.util.List;

public interface DongSanPhamService {
    List<DongSanPham> getAllDongSanPham();
    DongSanPham getOneDongSanPham(String id);
    String addDongSanPham(DongSanPham dongSanPham);
    String updateDongSanPham(DongSanPham dongSanPham);
    String deleteDongSanPham(DongSanPham dongSanPham);
}
