package ru.job4j.tracker;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

@Slf4j
public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            try {
                session.save(item);
                session.getTransaction().commit();
                return item;
            } catch (Exception e) {
                session.getTransaction().rollback();
                log.error("Failed to add item. Error: {}", e.getMessage(), e);
                throw e;
            }
        }
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean isUpdated = false;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            try {
                int updatedCount = session.createQuery(
                                "UPDATE Item SET name = :fName WHERE id = :fId")
                        .setParameter("fName", item.getName())
                        .setParameter("fId", id)
                        .executeUpdate();
                session.getTransaction().commit();
                isUpdated = updatedCount > 0;
            } catch (Exception e) {
                session.getTransaction().rollback();
                log.error("Failed to replace item with id={}. Error: {}", id, e.getMessage(), e);
                throw e;
            }
        }
        return isUpdated;
    }

    @Override
    public void delete(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            try {
                session.createQuery(
                                "DELETE Item WHERE id = :fId")
                        .setParameter("fId", id)
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                log.error("Failed to delete item with id={}.", id, e);
                throw e;
            }
        }
    }

    @Override
    public List<Item> findAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("SELECT DISTINCT i FROM Item i LEFT JOIN FETCH i.participates", Item.class)
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