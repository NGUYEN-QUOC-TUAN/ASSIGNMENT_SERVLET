package repository;

import entity.CuaHang;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class CuaHangRepository {
    public List<CuaHang> getAllCuaHangs(){
        List<CuaHang> listCuaHangs = new ArrayList<>();
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            Query query = session.createQuery("FROM CuaHang ", CuaHang.class);
            listCuaHangs = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCuaHangs;
    }
    public CuaHang getOneCuaHang(String id){
        CuaHang cuaHang = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM CuaHang WHERE id =: id", CuaHang.class);
            query.setParameter("id", id);
            cuaHang = (CuaHang) query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return cuaHang;
    }
    public Boolean addCuaHang(CuaHang cuaHang){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(cuaHang);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean deleteCuaHang(CuaHang cuaHang){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(cuaHang);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
