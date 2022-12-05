package daoReal;

import dao.FriendsDao;
import hibernate.HibernateSessionFactoryUtil;
import model.Friends;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FriendsDaoReal implements FriendsDao {
    @Override
    public Friends findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Friends.class, id);
    }

    @Override
    public void save(Friends friend) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(friend);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Friends friend) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(friend);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Friends friend) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(friend);
        tx1.commit();
        session.close();
    }

    @Override
    public List findAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Friends").list();
    }
}