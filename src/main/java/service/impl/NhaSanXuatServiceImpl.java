package service.impl;

import entity.NhaSanXuat;
import repository.NhaSanXuatRepository;
import service.NhaSanXuatService;

import java.util.List;

public class NhaSanXuatServiceImpl implements NhaSanXuatService {
    private NhaSanXuatRepository nhaSanXuatRepository = new NhaSanXuatRepository();
    @Override
    public List<NhaSanXuat> getAllNhaSanXuats() {
        return nhaSanXuatRepository.getAllNhaSanXuat();
    }

    @Override
    public NhaSanXuat getOneNhaSanXuat(String id) {
        return nhaSanXuatRepository.getOneNhaSanXuat(id);
    }

    @Override
    public String addNhaSanXuat(NhaSanXuat nhaSanXuat) {
        Boolean check = nhaSanXuatRepository.addNhaSanXuat(nhaSanXuat);
        if(check == true){
            return "ADD THÀNH CÔNG";
        }else {
            return "ADD THẤT BẠI";
        }
    }

    @Override
    public String updateNhaSanXuat(NhaSanXuat nhaSanXuat) {
        Boolean check = nhaSanXuatRepository.addNhaSanXuat(nhaSanXuat);
        if(check == true){
            return "UPDATE THÀNH CÔNG";
        }else {
            return "UPDATE THẤT BẠI";
        }
    }

    @Override
    public String deleteNhaSanXuat(NhaSanXuat nhaSanXuat) {
        Boolean check = nhaSanXuatRepository.deleteNhaSanXuat(nhaSanXuat);
        if(check == true){
            return "DELETE THÀNH CÔNG";
        }else {
            return "DELETE THẤT BẠI";
        }
    }
}
