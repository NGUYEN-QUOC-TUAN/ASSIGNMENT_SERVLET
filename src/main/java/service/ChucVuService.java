package service;

import entity.ChucVu;
import java.util.List;

public interface ChucVuService {
    List<ChucVu> getAllChucVus();
    ChucVu getOneChucVu(String id);
    String addChucVu(ChucVu chucVu);
    String updateChucVu(ChucVu chucVu);
    String deleteChucVu(ChucVu chucVu);
}
