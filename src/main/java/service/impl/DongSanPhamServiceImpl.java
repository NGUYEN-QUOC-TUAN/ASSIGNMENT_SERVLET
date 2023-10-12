package service.impl;

import entity.DongSanPham;
import repository.DongSanPhamRepository;
import service.DongSanPhamService;

import java.util.List;

public class DongSanPhamServiceImpl implements DongSanPhamService {
    private DongSanPhamRepository dongSanPhamRepository = new DongSanPhamRepository();
    @Override
    public List<DongSanPham> getAllDongSanPham() {
        return dongSanPhamRepository.getAllDongSanPham();
    }

    @Override
    public DongSanPham getOneDongSanPham(String id) {
        return dongSanPhamRepository.getOneDongSanPham(id);
    }

    @Override
    public String addDongSanPham(DongSanPham dongSanPham) {
        Boolean check = dongSanPhamRepository.addDongSanPham(dongSanPham);
        if(check == true){
            return "ADD THÀNH CÔNG";
        }else {
            return "ADD THẤT BẠI";
        }
    }

    @Override
    public String updateDongSanPham(DongSanPham dongSanPham) {
        Boolean check = dongSanPhamRepository.addDongSanPham(dongSanPham);
        if(check == true){
            return "UPDATE THÀNH CÔNG";
        }else {
            return "UPDATE THẤT BẠI";
        }
    }

    @Override
    public String deleteDongSanPham(DongSanPham dongSanPham) {
        Boolean check = dongSanPhamRepository.deleteDongSanPham(dongSanPham);
        if(check == true){
            return "DELETE THÀNH CÔNG";
        }else {
            return "DELETE THẤT BẠI";
        }
    }
}
