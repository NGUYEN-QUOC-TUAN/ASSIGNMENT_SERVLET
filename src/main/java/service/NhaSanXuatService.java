package service;

import entity.NhaSanXuat;

import java.util.List;

public interface NhaSanXuatService {
    List<NhaSanXuat> getAllNhaSanXuats();
    NhaSanXuat getOneNhaSanXuat(String id);
    String addNhaSanXuat(NhaSanXuat nhaSanXuat);
    String updateNhaSanXuat(NhaSanXuat nhaSanXuat);
    String deleteNhaSanXuat(NhaSanXuat nhaSanXuat);
}
