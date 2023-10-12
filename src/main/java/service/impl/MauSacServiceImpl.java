package service.impl;

import entity.MauSac;
import repository.MauSacRepository;
import service.MauSacService;

import java.util.List;

public class MauSacServiceImpl implements MauSacService {
    private MauSacRepository mauSacRepository = new MauSacRepository();
    @Override
    public List<MauSac> getAllMauSacs() {
        return mauSacRepository.getAllMauSacs();
    }

    @Override
    public MauSac getOneMauSac(String id) {
        return mauSacRepository.getOneMauSac(id);
    }

    @Override
    public String addMauSac(MauSac mauSac) {
        Boolean check = mauSacRepository.addMauSac(mauSac);
        if(check == true){
            return "ADD THÀNH CÔNG";
        }else {
            return "ADD THẤT BẠI";
        }
    }

    @Override
    public String updateMauSac(MauSac mauSac) {
        Boolean check = mauSacRepository.addMauSac(mauSac);
        if(check == true){
            return "UPDATE THÀNH CÔNG";
        }else {
            return "UPDATE THẤT BẠI";
        }
    }

    @Override
    public String deleteMauSac(MauSac mauSac) {
        Boolean check = mauSacRepository.deleteMauSac(mauSac);
        if(check == true){
            return "DELETE THÀNH CÔNG";
        }else {
            return "DELETE THẤT BẠI";
        }
    }
}
