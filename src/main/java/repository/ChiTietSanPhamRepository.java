package repository;

import entity.ChiTietSanPham;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamRepository {
    public List<ChiTietSanPham> getAllChiTietSanPham(){
        List<ChiTietSanPham> listChiTietSanPhams = new ArrayList<>();
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            Query query = session.createQuery("FROM ChiTietSanPham", ChiTietSanPham.class);
            listChiTietSanPhams = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return listChiTietSanPhams;
    }
    public ChiTietSanPham getOneChiTietSanPham(String id){
        ChiTietSanPham chiTietSanPham = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM ChiTietSanPham WHERE id =: id", ChiTietSanPham.class);
            query.setParameter("id", id);
            chiTietSanPham = (ChiTietSanPham) query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return chiTietSanPham;
    }
    public Boolean addChiTietSanPham(ChiTietSanPham chiTietSanPham){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(chiTietSanPham);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean deleteChiTietSanPham(ChiTietSanPham chiTietSanPham){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(chiTietSanPham);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
//        SanPham sanPham = new SanPham().builder()
//                .id("b929efaa-e65f-44fa-a9bd-026c661294d2")
//                .build();
//        NhaSanXuat nhaSanXuat = new NhaSanXuat().builder()
//                .id("794d361d-0c08-4086-b285-0ec6475e4eee")
//                .build();
//        MauSac mauSac = new MauSac().builder()
//                .id("5e6b02f0-1a05-4779-9207-b2ca0eeb853a")
//                .build();
//        DongSanPham dongSanPham = new DongSanPham().builder()
//                .id("c63bf16c-794d-459c-9e93-5ae7eff694e1")
//                .build();
//        ChiTietSanPham chietSanPham = new ChiTietSanPham("4921976b-f740-4fd8-8b6f-56859f42332b", sanPham, nhaSanXuat, mauSac, dongSanPham, 2023,"TỐT ĐẸP",333, BigDecimal.valueOf(32323), BigDecimal.valueOf(32323));
////        ChiTietSanPhamRepository chietRepository = new ChiTietSanPhamRepository();
////        chietRepository.addChiTietSanPham(chietSanPham);
//        System.out.println(new ChiTietSanPhamRepository().deleteChiTietSanPham(chietSanPham));
    }
}
