package ru.tbcarus.repository;

import org.hibernate.SessionFactory;
import ru.tbcarus.entity.Address;

public class AddressRepository extends AbstractRepository<Address> {

    public AddressRepository(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
