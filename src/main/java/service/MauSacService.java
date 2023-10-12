package service;

import entity.MauSac;

import java.util.List;

public interface MauSacService {
    List<MauSac> getAllMauSacs();
    MauSac getOneMauSac(String id);
    String addMauSac(MauSac mauSac);
    String updateMauSac(MauSac mauSac);
    String deleteMauSac(MauSac mauSac);
}
