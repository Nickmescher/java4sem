import model.*;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import service.Service;

import javax.persistence.metamodel.EntityType;
import java.sql.Date;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();

        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }

            }
            Owner owner = new Owner("Missssha",java.sql.Date.valueOf("2011-05-02"));
            Cats kotik = new Cats("Kot111", java.sql.Date.valueOf("2051-05-02"), Breed.HOMELESS, Color.BLACK);
            Cats kotik2 = new Cats("Kot2", java.sql.Date.valueOf("2001-06-07"), Breed.BRITTSH, Color.WHITE);
            Cats kotik3 = new Cats("Kot3", java.sql.Date.valueOf("2010-01-03"), Breed.BALD, Color.WHITE);
            Service service = new Service();

            service.saveOwner(owner);
            service.saveCat(kotik);
            service.saveCat(kotik2);
            service.saveCat(kotik3);

            owner.addCat(kotik);
            owner.addCat(kotik2);

            service.updateOwner(owner);

            Friends friend = service.addFriend(kotik, kotik2);

            service.updateKotik(kotik);
            service.updateKotik(kotik2);

        } finally {
            session.close();
        }
    }
}