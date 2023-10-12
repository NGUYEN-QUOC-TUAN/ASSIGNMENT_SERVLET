package service.impl;

import entity.SanPham;
import repository.SanPhamRepository;
import service.SanPhamService;

import java.util.List;

public class SanPhamServiceImpl implements SanPhamService {
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    @Override
    public List<SanPham> getAllSanPhams() {
        return sanPhamRepository.getAllSanPham();
    }

    @Override
    public SanPham getOneSanPham(String id) {
        return sanPhamRepository.getOneSanPham(id);
    }

    @Override
    public String addSanPham(SanPham sanPham) {
        boolean check = sanPhamRepository.addSanPham(sanPham);
        if(check == true){
            return "ADD THÀNH CÔNG";
        }else {
            return "ADD THẤT BẠI";
        }
    }

    @Override
    public String updateSanPham(SanPham sanPham) {
        boolean check = sanPhamRepository.addSanPham(sanPham);
        if(check == true){
            return "UPDATE THÀNH CÔNG";
        }else {
            return "UPDATE THẤT BẠI";
        }
    }

    @Override
    public String deleteSanPham(SanPham sanPham) {
        boolean check = sanPhamRepository.deleteSanPham(sanPham);
        if(check == true){
            return "DELETE THÀNH CÔNG";
        }else {
            return "DELETE THẤT BẠI";
        }
    }
}
