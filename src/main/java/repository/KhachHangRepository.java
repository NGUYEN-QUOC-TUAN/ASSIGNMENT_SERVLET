package repository;

import entity.KhachHang;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;

public class KhachHangRepository {
    public List<KhachHang> getAllKhachHangs(){
        List<KhachHang> listKhachHangs = new ArrayList<>();
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            Query query = session.createQuery("FROM KhachHang", KhachHang.class);
            listKhachHangs = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return listKhachHangs;
    }
    public KhachHang getOneKhachHang(String id){
        KhachHang khachHang = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM KhachHang WHERE id =: id", KhachHang.class);
            query.setParameter("id", id);
            khachHang = (KhachHang) query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return khachHang;
    }
    public Boolean addKhachHang(KhachHang khachHang){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(khachHang);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean deleteKhachHang(KhachHang khachHang){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(khachHang);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
