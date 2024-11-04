package ru.tbcarus.repository;

import org.hibernate.SessionFactory;
import ru.tbcarus.entity.Customer;

public class CustomerRepository extends AbstractRepository<Customer> {

    public CustomerRepository(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
