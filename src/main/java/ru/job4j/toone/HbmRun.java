package ru.job4j.toone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            var role = new Role();
            role.setName("ADMIN");
            create(role, sf);

            var user = new User();
            user.setName("Admin Admin");
            user.setRole(role);

            var messenger1 = new UserMessenger("tg", "@tg");
            var messenger2 = new UserMessenger("wu", "@wu");

            user.addMessenger(messenger1);
            user.addMessenger(messenger2);

            create(user, sf);

            var stored = sf.openSession()
                    .createQuery("FROM User WHERE name = :userName", User.class)
                    .setParameter("userName", "Admin Admin")
                    .getSingleResult();

            System.out.println("Created user: " + stored);
            stored.getMessengers().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static <T> void create(T model, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.persist(model);
        session.getTransaction().commit();
        session.close();
    }

    public static <T> List<T> findAll(Class<T> cl, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<T> list = session.createQuery("FROM " + cl.getName(), cl).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}