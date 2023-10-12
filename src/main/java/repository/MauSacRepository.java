package repository;

import entity.MauSac;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class MauSacRepository {
    public List<MauSac> getAllMauSacs(){
        List<MauSac> listMauSacs = new ArrayList<>();
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            Query query = session.createQuery("FROM MauSac", MauSac.class);
            listMauSacs = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return listMauSacs;
    }
    public MauSac getOneMauSac(String id){
        MauSac mauSac = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM MauSac WHERE id =: id", MauSac.class);
            query.setParameter("id", id);
            mauSac = (MauSac) query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return mauSac;
    }
    public Boolean addMauSac(MauSac mauSac){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(mauSac);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean deleteMauSac(MauSac mauSac){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(mauSac);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        MauSac mauSac = new MauSac("5e6b02f0-1a05-4779-9207-b2ca0eeb853a", "MS4", "VÀNG ĐỎ");
        System.out.println(new MauSacRepository().addMauSac(mauSac));
    }
}
