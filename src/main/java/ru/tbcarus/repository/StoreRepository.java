package ru.tbcarus.repository;

import org.hibernate.SessionFactory;
import ru.tbcarus.entity.Store;

public class StoreRepository extends AbstractRepository<Store> {

    public StoreRepository(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
