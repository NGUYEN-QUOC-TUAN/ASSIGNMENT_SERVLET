package service.impl;

import entity.ChucVu;
import repository.ChucVuRepository;
import service.ChucVuService;

import java.util.List;

public class ChuVuServiceImpl implements ChucVuService {
    private ChucVuRepository chucVuRepository = new ChucVuRepository();
    @Override
    public List<ChucVu> getAllChucVus() {
        return chucVuRepository.getAllChucVus();
    }

    @Override
    public ChucVu getOneChucVu(String id) {
        return chucVuRepository.getOneChucVu(id);
    }

    @Override
    public String addChucVu(ChucVu chucVu) {
        Boolean check = chucVuRepository.addChucVu(chucVu);
        if(check == true){
            return "ADD THÀNH CÔNG";
        }else {
            return "ADD THẤT BẠI";
        }
    }

    @Override
    public String updateChucVu(ChucVu chucVu) {
        Boolean check = chucVuRepository.addChucVu(chucVu);
        if(check == true){
            return "UPDATE THÀNH CÔNG";
        }else {
            return "UPDATE THẤT BẠI";
        }
    }

    @Override
    public String deleteChucVu(ChucVu chucVu) {
        Boolean check = chucVuRepository.deleteChucVu(chucVu);
        if(check == true){
            return "DELETE THÀNH CÔNG";
        }else {
            return "DELETE THẤT BẠI";
        }
    }
}
