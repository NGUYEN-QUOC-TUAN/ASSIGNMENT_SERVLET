package service.impl;

import entity.NhanVien;
import repository.NhanVienRepository;
import service.NhanVienService;

import java.util.List;

public class NhanVienServiceImpl implements NhanVienService {
    private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    @Override
    public List<NhanVien> getAllNhanViens() {
        return nhanVienRepository.getAllNhanViens();
    }

    @Override
    public NhanVien getOneNhanVien(String id) {
        return nhanVienRepository.getOneNhanVien(id);
    }

    @Override
    public String addNhanVien(NhanVien nhanVien) {
        Boolean check = nhanVienRepository.addNhanVien(nhanVien);
        if(check == true){
            return "ADD THÀNH CÔNG";
        }else {
            return "ADD THẤT BẠI";
        }
    }

    @Override
    public String updateNhanVien(NhanVien nhanVien) {
        Boolean check = nhanVienRepository.addNhanVien(nhanVien);
        if(check == true){
            return "UPDATE THÀNH CÔNG";
        }else {
            return "UPDATE THẤT BẠI";
        }
    }

    @Override
    public String deleteNhanVien(NhanVien nhanVien) {
        Boolean check = nhanVienRepository.deleteNhanVien(nhanVien);
        if(check == true){
            return "DELETE THÀNH CÔNG";
        }else {
            return "DELETE THẤT BẠI";
        }
    }

    public static void main(String[] args) {
        NhanVien nhanVien = new NhanVienServiceImpl().getOneNhanVien("fe979443-1190-4bc0-a48a-b93120c62080");
        new NhanVienServiceImpl().deleteNhanVien(nhanVien);
    }
}
