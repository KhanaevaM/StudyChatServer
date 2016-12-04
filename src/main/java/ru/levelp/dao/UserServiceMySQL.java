package ru.levelp.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ru.levelp.HibernateManager;
import ru.levelp.User;

import java.util.List;

public class UserServiceMySQL implements UserDAO {
    private Session session;

    public UserServiceMySQL() {
        this.session = HibernateManager.getInstance().getSession();
    }

    public void add(User user) {
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    public User get(long id) {
        return (User) session.get(User.class, id);
    }

    public List<User> getAll() {
        List<User> users = session.createCriteria(User.class).list();
        return users;
    }

    public User getByEmail(String email) {
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq(UserDAO.FIELD_EMAIL, email))
                .uniqueResult();
        return user;
    }
    public User getByName(String name) {
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        return user;
    }
    /*
    Как удалять оптимально по id?
     */
    public User delete(long id) {
        session.beginTransaction();
//        User user = new User();
//        user.setId(id);
//        session.delete(user);
//        session.load(User.class, id);
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        if (user != null) {
            session.delete(user);
        }
        session.getTransaction().commit();
        return user;
    }
}