package service;

import entity.CuaHang;

import java.util.List;

public interface CuaHangService {
    List<CuaHang> getAllCuaHangs();
    CuaHang getOneCuaHang(String id);
    String addCuaHang(CuaHang cuaHang);
    String updateCuaHang(CuaHang cuaHang);
    String deleteCuaHang(CuaHang cuaHang);
}
