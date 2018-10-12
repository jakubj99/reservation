package pl.sda.events.dao;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.events.model.AppUser;


import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@LocalBean
@Singleton
public class UserDao {


    public void add(AppUser newUser) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(newUser);
            transaction.commit();
        } catch (SessionException sessionException) {
            transaction.rollback();
            System.out.println("Error");
        }
    }

    public List<AppUser> getAllUsers() {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<AppUser> list = session.createQuery("from AppUser ", AppUser.class).list();
            return list;
        } catch (SessionException sessionException) {
            transaction.rollback();
            System.out.println("Error");
        }

        return new ArrayList<>();
    }

    public void removeUserWithId(int id) {
        Optional<AppUser> userOpt = findUserWithId(id);

        if (userOpt.isPresent()) {
            AppUser user = userOpt.get();

            Transaction transaction = null;

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.delete(user);

            } catch (SessionException sessionException) {
                transaction.rollback();
                System.out.println("Error");
            }
        }

    }

    public Optional<AppUser> findUserWithId(long userId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<AppUser> query = session.createQuery("from AppUser where id= :id", AppUser.class);
            query.setParameter("id", userId);

            AppUser result = query.uniqueResult();
            return Optional.of(result);
        } catch (SessionException sessionException) {
            transaction.rollback();
            System.out.println("Error");
        }

        return Optional.empty();
    }

    public void updateUser(AppUser userToModify) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
//           Query<AppUser> userQuery = session.createQuery("update AppUser set login=:login, password=:password where id= :id", AppUser.class);
//           userQuery.setParameter("login", userToModify.getLogin());
//           userQuery.setParameter("password", userToModify.getPassword());
//           userQuery.setParameter("id", userToModify.getId());
//
//            System.out.println("Changed: " + userQuery.executeUpdate() + " records");
            session.saveOrUpdate(userToModify);
            transaction.commit();
        } catch (SessionException sessionException) {
            transaction.rollback();
            System.out.println("Error");
        }

    }

    public Optional<AppUser> getUserWithLoginAndPassword(String login, String password) {

        Optional<AppUser> appUserOptional = checkIfUserNameExists(login);
        if (appUserOptional.isPresent()) {
            AppUser appUser = appUserOptional.get();

            if (appUser.getPassword().equals(password)) {
                return appUserOptional;
            }
        }
        return Optional.empty();
    }

    public Optional<AppUser> checkIfEmailExists(String email){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<AppUser> query = session.createQuery
                    ("from AppUser where email= :email", AppUser.class);
            query.setParameter("email", email);

            AppUser result = query.uniqueResult();

            return Optional.ofNullable(result);
        } catch (SessionException sessionException) {
            System.out.println("Error");
        }

        return Optional.empty();
    }

    public Optional<AppUser> checkIfUserNameExists(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<AppUser> query = session.createQuery
                    ("from AppUser where username= :username", AppUser.class);
            query.setParameter("username", username);

            AppUser result = query.uniqueResult();

            return Optional.ofNullable(result);
        } catch (SessionException sessionException) {
            System.out.println("Error");
        }

        return Optional.empty();
    }
}
