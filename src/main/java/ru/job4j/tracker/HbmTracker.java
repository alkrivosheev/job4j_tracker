package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        boolean isUpdated = false;
        try {
            session.beginTransaction();
            int updatedCount = session.createQuery(
                            "UPDATE Item SET name = :fName WHERE id = :fId")
                    .setParameter("fName", item.getName())
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            isUpdated = updatedCount > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return isUpdated;
    }

    @Override
    public void delete(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public List<Item> findAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Item ", Item.class)
                    .list();
        }
    }

    @Override
    public List<Item> findByName(String key) {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Item WHERE name = :searchKey", Item.class)
                    .setParameter("searchKey", key)
                    .list();
        }
    }

    @Override
    public Item findById(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Item WHERE id = :fId", Item.class)
                    .setParameter("fId", id)
                    .uniqueResult();
        }
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}