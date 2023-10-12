package service.impl;

import entity.KhachHang;
import repository.KhachHangRepository;
import service.KhachHangService;

import java.util.List;

public class KhachHangServiceImpl implements KhachHangService {
    private KhachHangRepository khachHangRepository = new KhachHangRepository();
    @Override
    public List<KhachHang> getAllKhachHangs() {
        return khachHangRepository.getAllKhachHangs();
    }

    @Override
    public KhachHang getOneKhachHang(String id) {
        return khachHangRepository.getOneKhachHang(id);
    }

    @Override
    public String addKhachHang(KhachHang khachHang) {
        Boolean check = khachHangRepository.addKhachHang(khachHang);
        if(check == true){
            return "ADD THÀNH CÔNG";
        }else {
            return "ADD THẤT BẠI";
        }
    }

    @Override
    public String updateKhachHang(KhachHang khachHang) {
        Boolean check = khachHangRepository.addKhachHang(khachHang);
        if(check == true){
            return "UPDATE THÀNH CÔNG";
        }else {
            return "UPDATE THẤT BẠI";
        }
    }

    @Override
    public String deleteKhachHang(KhachHang khachHang) {
        Boolean check = khachHangRepository.deleteKhachHang(khachHang);
        if(check == true){
            return "DELETE THÀNH CÔNG";
        }else {
            return "DELETE THẤT BẠI";
        }
    }
}
