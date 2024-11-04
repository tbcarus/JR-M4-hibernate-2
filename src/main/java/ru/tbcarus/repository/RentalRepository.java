package ru.tbcarus.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.tbcarus.entity.Rental;

import java.util.List;

public class RentalRepository extends AbstractRepository<Rental> {

    public RentalRepository(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public Rental findAnyFilmToReturn() {
        Session session = getSessionFactory().getCurrentSession();
        Query<Rental> query = session.createQuery("SELECT r FROM Rental r where r.returnDate IS NULL", Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public List<Rental> getByInventoryId(int inventoryId) {
        Session session = getSessionFactory().getCurrentSession();
        Query<Rental> query = session.createQuery("SELECT r FROM Rental r WHERE r.inventory.id = :inventoryId", Rental.class);
        query.setParameter("inventoryId", inventoryId);
        return query.list();
    }
}
