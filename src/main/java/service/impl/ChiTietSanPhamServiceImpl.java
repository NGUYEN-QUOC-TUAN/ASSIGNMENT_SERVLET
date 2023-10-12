package service.impl;

import entity.ChiTietSanPham;
import repository.ChiTietSanPhamRepository;
import service.ChiTietSanPhamService;

import java.util.List;

public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {
    private ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    @Override
    public List<ChiTietSanPham> getAllChiTietSanPhams() {
        return chiTietSanPhamRepository.getAllChiTietSanPham();
    }

    @Override
    public ChiTietSanPham getOneChiTietSanPham(String id) {
        return chiTietSanPhamRepository.getOneChiTietSanPham(id);
    }

    @Override
    public String addChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        boolean check = chiTietSanPhamRepository.addChiTietSanPham(chiTietSanPham);
        if(check == true){
            return "ADD THÀNH CÔNG";
        }else {
            return "ADD THẤT BẠI";
        }
    }

    @Override
    public String updateChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        boolean check = chiTietSanPhamRepository.addChiTietSanPham(chiTietSanPham);
        if(check == true){
            return "UPDATE THÀNH CÔNG";
        }else {
            return "UPDATE THẤT BẠI";
        }
    }

    @Override
    public String deleteChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        boolean check = chiTietSanPhamRepository.deleteChiTietSanPham(chiTietSanPham);
        if(check == true){
            return "DELETE THÀNH CÔNG";
        }else {
            return "DELETE THẤT BẠI";
        }
    }
}
