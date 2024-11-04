package ru.tbcarus.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.tbcarus.entity.Inventory;

public class InventoryRepository extends AbstractRepository<Inventory> {

    public InventoryRepository(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }

}
