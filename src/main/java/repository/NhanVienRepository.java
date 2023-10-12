package repository;

import entity.NhanVien;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class NhanVienRepository {
    public List<NhanVien> getAllNhanViens(){
        List<NhanVien> listNhanViens = new ArrayList<>();
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            Query query = session.createQuery("FROM NhanVien", NhanVien.class);
            listNhanViens = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return listNhanViens;
    }
    public NhanVien getOneNhanVien(String id){
        NhanVien nhanVien = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM NhanVien WHERE id =: id", NhanVien.class);
            query.setParameter("id", id);
            nhanVien = (NhanVien) query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return nhanVien;
    }
    public Boolean addNhanVien(NhanVien nhanVien){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(nhanVien);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean deleteNhanVien(NhanVien nhanVien){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(nhanVien);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        NhanVien nhanVien = new NhanVienRepository().getOneNhanVien("39a1b99a-93ef-4754-80c5-2d90efcd282f");
        System.out.println(new NhanVienRepository().deleteNhanVien(nhanVien));
    }
}
