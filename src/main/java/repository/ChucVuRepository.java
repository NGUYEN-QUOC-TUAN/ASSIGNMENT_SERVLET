package repository;

import entity.ChucVu;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ChucVuRepository {
    public List<ChucVu> getAllChucVus(){
        List<ChucVu> listChucVus = new ArrayList<>();
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            Query query = session.createQuery("FROM ChucVu", ChucVu.class);
            listChucVus = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return listChucVus;
    }
    public ChucVu getOneChucVu(String id){
        ChucVu chucVu = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM ChucVu WHERE id =: id", ChucVu.class);
            query.setParameter("id", id);
            chucVu = (ChucVu) query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return chucVu;
    }
    public Boolean addChucVu(ChucVu chucVu){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(chucVu);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean deleteChucVu(ChucVu chucVu){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(chucVu);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
