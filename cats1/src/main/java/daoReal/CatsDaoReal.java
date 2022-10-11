package daoReal;

import dao.CatsDao;
import hibernate.HibernateSessionFactoryUtil;
import model.Cats;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CatsDaoReal implements CatsDao {
    @Override
    public Cats findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Cats.class, id);
    }

    @Override
    public void save(Cats cats) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(cats);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Cats cats) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(cats);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Cats cats) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(cats);
        tx1.commit();
        session.close();
    }

    @Override
    public Cats findOwnerById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Cats.class, id);
    }

    @Override
    public List findAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Cats").list();
    }
}