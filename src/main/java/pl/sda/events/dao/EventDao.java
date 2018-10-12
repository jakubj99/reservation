package pl.sda.events.dao;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import pl.sda.events.model.Event;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@LocalBean
@Singleton
public class EventDao {
    public void save(Event event) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.save(event);

            transaction.commit();
        } catch (SessionException se) {
            transaction.rollback();
            System.out.println("ERROR");
        }
    }

    public List<Event> getAllEvents() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            List<Event> events = session.createQuery("from Event", Event.class).list();

            return events;
        } catch (SessionException se) {
            System.out.println("ERROR");
        }
        return new ArrayList<>();
    }

}
