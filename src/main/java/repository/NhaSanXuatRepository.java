package repository;

import entity.NhaSanXuat;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class NhaSanXuatRepository {
    public List<NhaSanXuat> getAllNhaSanXuat(){
        List<NhaSanXuat> listNhaSanXuats = new ArrayList<>();
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            Query query = session.createQuery("FROM NhaSanXuat", NhaSanXuat.class);
            listNhaSanXuats = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return listNhaSanXuats;
    }
    public NhaSanXuat getOneNhaSanXuat(String id){
        NhaSanXuat nhaSanXuat = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM NhaSanXuat WHERE id =: id", NhaSanXuat.class);
            query.setParameter("id", id);
            nhaSanXuat = (NhaSanXuat) query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return nhaSanXuat;
    }
    public Boolean addNhaSanXuat(NhaSanXuat nhaSanXuat){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(nhaSanXuat);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean deleteNhaSanXuat(NhaSanXuat nhaSanXuat){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(nhaSanXuat);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        NhaSanXuat nhaSanXuat = new NhaSanXuat("3b4701de-6f9e-49a0-b421-9e63d9594cd6", "NSX5", "GIA BẢO");
        System.out.println(new NhaSanXuatRepository().getOneNhaSanXuat("17dd657a-4c77-457a-8109-8b10cc02c478"));
    }
}
