package service.impl;

import entity.CuaHang;
import repository.CuaHangRepository;
import service.CuaHangService;

import java.util.List;

public class CuaHangServiceImpl implements CuaHangService {
    private CuaHangRepository cuaHangRepository = new CuaHangRepository();
    @Override
    public List<CuaHang> getAllCuaHangs() {
        return cuaHangRepository.getAllCuaHangs();
    }

    @Override
    public CuaHang getOneCuaHang(String id) {
        return cuaHangRepository.getOneCuaHang(id);
    }

    @Override
    public String addCuaHang(CuaHang cuaHang) {
        Boolean check = cuaHangRepository.addCuaHang(cuaHang);
        if(check == true){
            return "ADD THÀNH CÔNG";
        }else {
            return "ADD THẤT BẠI";
        }
    }

    @Override
    public String updateCuaHang(CuaHang cuaHang) {
        Boolean check = cuaHangRepository.addCuaHang(cuaHang);
        if(check == true){
            return "UPDATE THÀNH CÔNG";
        }else {
            return "UPDATE THẤT BẠI";
        }
    }

    @Override
    public String deleteCuaHang(CuaHang cuaHang) {
        Boolean check = cuaHangRepository.deleteCuaHang(cuaHang);
        if(check == true){
            return "DELETE THÀNH CÔNG";
        }else {
            return "DELETE THẤT BẠI";
        }
    }
}
