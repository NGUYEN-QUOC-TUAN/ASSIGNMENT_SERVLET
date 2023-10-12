package repository;

import entity.SanPham;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class SanPhamRepository {
    public List<SanPham> getAllSanPham(){
        List<SanPham> listSanPhams = new ArrayList<>();
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            Query query = session.createQuery("FROM SanPham", SanPham.class);
            listSanPhams = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return listSanPhams;
    }
    public SanPham getOneSanPham(String id){
        SanPham sanPham = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM SanPham WHERE id =: id", SanPham.class);
            query.setParameter("id", id);
            sanPham = (SanPham) query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return sanPham;
    }
    public Boolean addSanPham(SanPham sanPham){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(sanPham);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean deleteSanPham(SanPham sanPham){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(sanPham);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        SanPham sanPham = new SanPham("e2a69fdd-fbd4-4008-a9aa-310371284032", "SP5", "BÚT BI");
        System.out.println(new SanPhamRepository().deleteSanPham(sanPham));
    }
}
