package repository;

import entity.DongSanPham;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class DongSanPhamRepository {
    public List<DongSanPham> getAllDongSanPham(){
        List<DongSanPham> listDongSanPhams = new ArrayList<>();
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            Query query = session.createQuery("FROM DongSanPham", DongSanPham.class);
            listDongSanPhams = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return listDongSanPhams;
    }
    public DongSanPham getOneDongSanPham(String id){
        DongSanPham dongSanPham = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM DongSanPham WHERE id =: id", DongSanPham.class);
            query.setParameter("id", id);
            dongSanPham = (DongSanPham) query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return dongSanPham;
    }
    public Boolean addDongSanPham(DongSanPham dongSanPham){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(dongSanPham);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean deleteDongSanPham(DongSanPham dongSanPham){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(dongSanPham);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        DongSanPham dongSanPham = new DongSanPham("350c6799-dd24-354e-93cd-d0f84dd35307", "DSP5", "BIM BIM TUẤN");
        System.out.println(new DongSanPhamRepository().deleteDongSanPham(dongSanPham));
    }
}
